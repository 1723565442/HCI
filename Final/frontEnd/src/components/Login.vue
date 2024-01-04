<template>
  <div class="login_container">
    <div class="login_form">
      <p class="login_title">轻旅行</p>
      <el-form :model="form" ref="form" :rules="rules" status-icon label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 120px;">
          <el-button type="primary" size="medium" @click="loginCheck">登录</el-button>
          <el-button style="margin-left: 120px" size="medium" @click="goToRegistry">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
  
<script>
export default {
  name: 'Login',
  data() {
    return {
      form: {
        username: "",
        password: "",
      }, rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ], password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    this.logout;
  },
  methods: {
    loginCheck() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.$axios.post('http://localhost:8080/user/login', this.form)
            .then((res) => {
              if (res.data.code === 0) {
                this.$store.dispatch('login', res.data.data);
                this.$router.push('/');
              } else {
                this.$notify({
                  title: '登录失败',
                  message: res.data.msg,
                  type: 'error'
                });
              }
            })
            .catch((error) => {
            });
        }
      });
    },
    goToRegistry() {
      this.$router.push('/Registry')
    },
    logout() {
      this.$store.dispatch('logout');
    }
  }
}
</script>
  
<style scoped>
.login_container {
  width: 100%;
  height: 97vh;
  background-color: rgba(242, 242, 242, 1);
  background-image: url(../assets/backg.png);
  background-position: center bottom;
  background-repeat: no-repeat;
  background-size: 1278px 559px;
  border: none;
  border-radius: 0px;
  display: flex;
  justify-content: center;
  align-items: center;

  .login_form {
    width: 510px;
    margin: 0px auto;
    padding: 0 55px 15px 35px;
    background-color: #fff;
    border: none;
    border-radius: 5px;
    box-shadow: 0 0 25px #cac6c6;

    .login_title {
      font-family: '微软雅黑 Bold', '微软雅黑';
      font-weight: 700;
      text-decoration: none;
      color: rgb(0, 121, 254);
      font-size: 32px;
      margin-top: 50px;
      margin-bottom: 30px;
      text-align: center;
    }
  }
}
</style>