<template>
  <div>
      <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="login-ruleForm">
        <el-form-item label="账号" prop="username">
          <el-input v-model="ruleForm.username" placeholder="用户名" prefixIcon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password" placeholder="密码" type="password" @keyup.enter.native="userLogin"></el-input>
          如果您忘记了密码，请点击这里<i class="el-icon-right"></i>
          <router-link to="/forgetPwd">
            忘记密码
          </router-link>
        </el-form-item>
        <el-form-item prop="region">
          <el-button type="primary" @click="userLogin">登 录</el-button>
          <el-button type="primary" @click="toRegister">注 册</el-button>
        </el-form-item>
      </el-form>

  </div>
</template>

<script>
import user from '@/api/user'
export default {
  name: "login",
  data() {
    return {
      ruleForm: {
        username: '',
        password: '',
        sendMailFlag: false,
        isLogin: false //判断是否登录
      },
    }
  },
  methods: {
    userLogin() { //登录方法
      if (this.ruleForm.username.length <= 0 || this.ruleForm.password.length <= 0) {
        this.$message({
          message: '字段不完整',
          type: 'error'
        });
        return;
      }
      user.login(this.ruleForm.username, this.ruleForm.password).then(response => {
        this.ruleForm.password = ''
        this.$store.commit('login', response.data)//存储token
        this.$message({
          message: '登录成功',
          type: 'success'
        });
        this.$router.replace("/")
        this.isLogin = true
      })
    },

    //跳转到注册页面
    toRegister(){
      this.$router.push({path: '/register'})
    }

  }
}
</script>

<style scoped>
.login-ruleForm{
  padding: 200px;
  width: 400px;
  margin: auto;
  text-align: center;
}

</style>