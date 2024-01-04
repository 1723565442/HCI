<template>
  <div class="container">
    
    <div class="order-detail-container"
      style="background-color: #f5f5f5; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      ">
      <el-card class="order-details-card" :shadow="hoverShadow" :body-style="{ padding: '20px' }">
        <div slot="header" class="order-card-header">
          <h2>您的订单</h2>
        </div>
        <div class="order-card-content" style="font-size: large;text-align: left;">
          <p @click="gotoPage" style="cursor: pointer; font-size: larger;"><strong>酒店名: </strong> {{ orderDetailsArray.hotelName }}</p>
          <p><strong>房间号:</strong> {{ orderDetailsArray.roomNumber }}</p>
          <p><strong>入住人:</strong> {{ orderDetailsArray.guestName }}</p>
          <p><strong>入住人数:</strong> {{ orderDetailsArray.guestsCount }}</p>
          <p><strong>入住日期:</strong> {{ orderDetailsArray.checkInDate }}</p>
          <p><strong>离店日期:</strong> {{ orderDetailsArray.checkOutDate }}</p>
          <p><strong>订单状态:</strong> {{ orderDetailsArray.status }}</p>
          <p><strong>房型:</strong> {{ orderDetailsArray.roomType }}</p>
          <p><strong>预留联系方式:</strong> {{ orderDetailsArray.phoneNumber }}</p>
          <p><strong>价格:</strong> {{ orderDetailsArray.price }}</p>
          <p><strong>备注:</strong> {{ orderDetailsArray.orderNote }}</p>
        </div>
      </el-card>
    </div>
  </div>
</template>
  
<script>
import { ElMessageBox } from 'element-plus';
export default {
  name: 'OrderDetail',
  data() {
    return {
      balance: 1000,
      detailForm: {
        id: ''
      },
      orderDetailsArray: []
    };
  },
  created() {
    const m = this.$route.params.id;
    console.log('id', m)
    this.detailForm = {
      id: m
    }
    this.$axios.post('http://localhost:8080/order/details', this.detailForm)
      .then(response => {
        this.orderDetailsArray = response.data.data;
      })
      .catch(error => {
        console.error('Error fetching hotels data:', error);
      });
  },
  methods: {
    goHome() {
      this.$router.push('/Home');
    },
    gotoPage(){
      this.$router.push({ name: 'Detail', params: { a: this.orderDetailsArray.hotelName } });
    },
    gotoOrder() {
      this.$router.push('/Order');
    }
  },
};
</script>
  
<style scoped>
.container {
  position: relative;
  padding: 20px;
  box-sizing: border-box;
  display: flex;
  margin-top: 20px;
  justify-content: center;
  align-items: center;
}



.order-detail-container {
  width: 100%;
  max-width: 1000px;
}
</style>


  