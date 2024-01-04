<template>
  <div class="order_container">
    <p class="order_title">我的订单</p>
    <el-tabs v-model="activeTab" @tab-click="handleTabClick">
      <el-tab-pane label="全部订单" name="all"></el-tab-pane>
      <el-tab-pane label="已支付（待入住）" name="paid"></el-tab-pane>
      <el-tab-pane label="已完成" name="completed"></el-tab-pane>
      <el-tab-pane label="已取消" name="canceled"></el-tab-pane>

    </el-tabs>
    <div v-if="activeTab === 'all'" class="order_list">
      <el-table :data="filteredOrders" stripe style="width: 100%">
        <el-table-column prop="id" label="订单号" width="160"></el-table-column>
        <el-table-column prop="roomType" label="房间类型" width="180"></el-table-column>
        <el-table-column prop="guestsCount" label="入住人数" width="160"></el-table-column>
        <el-table-column prop="checkInDate" format="yyyy/MM/dd" label="入住日期" width="230"></el-table-column>
        <el-table-column prop="checkOutDate" label="退房日期" width="230"></el-table-column>
        <el-table-column prop="price" label="价格" width="160"></el-table-column>
        <el-table-column prop="status" label="订单状态" width="160"></el-table-column>
        <el-table-column label="操作">
          <template v-slot="{ row, $index }">
            <el-button size="small" @click="viewOrderDetail(row)">详情</el-button>
            <el-button size="small" type="danger" @click="confirmDelete(row)">删除
            </el-button>
            <el-button size="small" type="primary" v-if="row.status === '待支付'" @click="viewOrderDetail(row)">支付
            </el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>

    <div v-else class="order_list">
      <el-table :data="filteredOrders" stripe style="width: 100%">
        <el-table-column prop="id" label="订单号" width="180"></el-table-column>
        <el-table-column prop="roomType" label="房间类型" width="200"></el-table-column>
        <el-table-column prop="guestsCount" label="入住人数" width="180"></el-table-column>
        <el-table-column prop="checkInDate" format="yyyy/MM/dd" label="入住日期" width="250"></el-table-column>
        <el-table-column prop="checkOutDate" label="退房日期" width="250"></el-table-column>
        <el-table-column prop="price" label="价格" width="180"></el-table-column>
        <el-table-column label="操作">
          <template v-slot="{ row, $index }">
            <el-button size="small" @click="viewOrderDetail(row)">详情</el-button>
            <el-button size="small" type="danger" @click="confirmDelete(row)">删除
            </el-button>
            <el-button size="small" type="primary" v-if="row.status === '待支付'" @click="viewOrderDetail(row)">支付
            </el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>
  </div>
</template>

<script>
import { ElMessageBox } from 'element-plus';
import axios from "axios";
import { ElNotification } from 'element-plus'


export default {
  data() {
    return {
      orders: [],
      activeTab: 'all',
      getOrdersForm: {
        id: this.$store.getters.user.id
      },
      deleteForm: {
        id: ''
      }
    };
  },
  created() {
    console.log('用户id', this.getOrdersForm)
    this.$axios.post('http://localhost:8080/order/orders', this.getOrdersForm)
      .then(response => {
        this.orders = response.data.data;
      })
      .catch(error => {
        console.error('Error fetching hotels data:', error);
      });

  },
  computed: {
    filteredOrders() {
      if (this.activeTab === 'all') {
        return this.orders;
      }
      return this.orders.filter(order => {
        switch (this.activeTab) {
          case 'unpaid':
            return order.status === '待支付';
          case 'paid':
            return order.status === '已支付（待入住）';
          case 'completed':
            return order.status === '已完成';
          case 'canceled':
            return order.status === '已取消';
        }
      });
    }
  },

  methods: {
    fetchOrders() {
      this.$axios.post('http://localhost:8080/order/orders', this.getOrdersForm)
        .then(response => {
          this.orders = response.data.data;
        })
        .catch(error => {
          console.error('Error fetching hotels data:', error);
        });
    },
    viewOrderDetail(row) {
      console.log('即将detail', row.id)
      this.$router.push({ name: 'OrderDetail', params: { id: row.id } });
    },
    confirmDelete(row) {
      ElMessageBox.confirm(
        '确定要删除此订单吗?',
        '删除订单',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        this.deleteForm = {
          id: row.id
        }
        this.$axios.post(`http://localhost:8080/order/delete`, this.deleteForm)
          .then(() => {
            this.fetchOrders()
            ElNotification({
              title: '删除成功',
              message: '订单已成功删除，期待您的下次使用！',
              type: 'success',
              offset: 200,
            })
          })
          .catch(error => {
            console.error('删除订单失败:', error);
          });
      }).catch(() => {
      });
    },
  }
};
</script>

<style scoped>
.order_container {
  padding: 20px;
}

.order_title {
  font-size: 24px;
  margin-bottom: 40px;
}

.order_list {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.el-table .cell {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
