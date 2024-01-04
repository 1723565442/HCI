<template>
  <div class="header">
    <el-row>
      <el-col :span="20">
        <div class="l-content">
          <img src="@/assets/title.png"
            style="cursor: pointer; margin-left: 40px; margin-top: 8px; width: 222px; height: 60px"
            @click="goToPage('/Home')">
        </div>
      </el-col>
      <el-col :span="2">
        <div class="order" v-if="isAuth">
          <el-button style="margin-top: 15px;" icon="list" @click="goToPage('/Order')" round>我的订单</el-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="r-content" v-if="isAuth">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              <img style="margin-top: 15px; margin-left: 40px;cursor: pointer;" class="user_img"
                src="@/assets/logo.jpg" />
            </span>
            <template v-slot:dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="a">个人信息</el-dropdown-item>
                <el-dropdown-item command="b" @click.native="open">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
        <div class="r-content" v-else>
          <el-avatar style="margin-top: 13px; margin-left: 30px;cursor: pointer;" @click="goToPage('/Login')"> 登录
          </el-avatar>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { useRouter, useRoute } from 'vue-router';
import { ElMessageBox, ElMessage } from 'element-plus';

export default {
  name: 'Header',
  props: ['isAuth'],
  setup() {
    const router = useRouter();
    const route = useRoute();

    const goToPage = (targetRoute) => {
      if (route.path !== targetRoute && !(route.path === '/Home' && targetRoute === '/')) {
        router.push(targetRoute);
      }
    };

    const handleCommand = (command) => {
      if (command === 'a')
        router.push('/User');
    };

    const open = () => {
      ElMessageBox.confirm('是否确定退出登录，下次登录依然可以使用本账号', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        ElMessage({
          type: 'success',
          message: '登出成功!',
        });
        router.push('/Login');
      }).catch(() => {
      });
    };

    return {
      goToPage,
      handleCommand,
      open
    };
  }
}
</script>
  
<style lang="less" scoped>
.header {
  height: 100%;
  width: 100%;
  padding: 0;
}

.r-content {
  padding-right: 20px;

  .user_img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }
}
</style>