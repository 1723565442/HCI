<template>
  <div class="helloworld">
    <el-container style="margin: 0;padding: 0">
      <el-header height="10vh" style="margin: 0%;padding: 0 position: fixed; width: 100%; ">
        <Header :isAuth="isAuth"></Header>
      </el-header>
      <el-main style="margin: 0; padding: 0; ">
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
      isAuth: this.$store.getters.isAuthenticated
    }
  },
  components: {
    Header,
    Footer
  }
}
</script>

<style scoped>
.el-main {
  text-align: center;
  max-height: 87vh;
}
</style>