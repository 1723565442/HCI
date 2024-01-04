<template>
    <div class="registry_container">
        <div class="registry_form">
            <p class="registry_title">个人信息</p>
            <el-form :model="form" :rules="rules" ref="form" label-width="100px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone">
                    <el-input v-model="form.phone" placeholder="请输入手机号码"></el-input>
                </el-form-item>
                <el-form-item label="修改密码" prop="password">
                    <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                    <el-input type="password" v-model="form.confirmPassword" placeholder="请再次输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm">修改</el-button>
                    <el-button @click.native="open">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
  
<script>
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
    name: 'User',
    data() {
        return {
            user: this.$store.getters.user,
            form: {
                username: '',
                phone: '',
                password: '',
                confirmPassword: ''
            },
            regForm: {
                id: '',
                username: '',
                password: '',
                phone: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 4, max: 16, message: '用户名长度在 4 到 16 个字符', trigger: 'blur' },
                    { pattern: /^[a-z0-9_-]+$/, message: '用户名只能包含小写字母,数字,下划线和连字符', trigger: 'blur' },
                ],
                phone: [
                    { required: true, message: '请输入手机号码', trigger: 'blur' },
                    { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' },
                ],
                password: [
                    { required: false, message: '请输入密码', trigger: 'blur' },
                    { min: 8, max: 16, message: '密码长度必须在 8 到 16 个字符', trigger: 'blur' },
                    { pattern: /^[a-zA-Z0-9!@#$%^&*()_+\-=[\]{};':"\\|,.<>\/?]*$/, message: '密码只能包含数字、字母和符号', trigger: 'blur' },
                    { pattern: /(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+/, message: '密码必须同时包含大小写字母和数字', trigger: 'blur' },
                ],
                confirmPassword: [
                    { required: false, message: '请再次输入密码', trigger: 'blur' },
                    { validator: this.validateConfirmPassword, trigger: 'blur' }
                ],
            }
        };
    },
    created() {
        this.user = this.$store.getters.user;
        this.form = {
                username: this.user.username,
                phone: this.user.phone,
                password: null,
                confirmPassword: null
            }
    },
    methods: {
        submitForm() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    this.regForm = {
                        id: this.user.id,
                        username: this.form.username,
                        password: this.form.password === '' ? null : this.form.password, 
                        phone: this.form.phone
                    }
                    this.$axios.post('http://localhost:8080/user/edit', this.regForm)
                        .then(response => {
                            if (response.data.code === 0) {
                                this.$store.dispatch('login', response.data.data);
                                this.$notify({
                                    title: '修改成功',
                                    message: '继续浏览酒店吧',
                                    type: 'success'
                                });
                            } else {
                                this.$notify({
                                    title: '修改失败',
                                    message: response.data.msg,
                                    type: 'error'
                                });
                            }
                        });
                }
            });
        },
        resetForm() {
            this.form = {
                username: this.user.username,
                phone: this.user.phone,
                password: null,
                confirmPassword: null
            }
        },
        open() {
            ElMessageBox.confirm('此操作将重置所有输入的信息，是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.resetForm();
                ElMessage({
                    type: 'success',
                    message: '重置成功!',
                });
            }).catch(() => {
            });
        },
        validateConfirmPassword(rule, value, callback) {
            if (value !== this.form.password) {
                callback(new Error('两次输入的密码不一致'));
            } else {
                callback();
            }
        },
        checkUsernameAvailability(rule, value, callback) {
            if (value === '已存在的用户名') {
                callback(new Error('该用户名已被占用'));
            } else {
                callback();
            }
        }
    }
}
</script>
  
  
<style scoped>
.registry_container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 87vh;
    background-image: url(../assets/backg.png);
}

.registry_form {
    width: 400px;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.registry_title {
    font-size: 24px;
    text-align: center;
    margin-bottom: 20px;
}
</style>
  