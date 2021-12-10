<template>
  <div>
    <el-form :model="form" title="注册" label-width="100px" class="register">
      <el-form-item :label-width="formLabelWidth">
        若您已有账号，可点击这里<router-link style="cursor: pointer;color: blue" to="/login">返回登录</router-link>。<br>
        <el-input v-model="form.registerName" placeholder="用户名" prefixIcon="el-icon-user-solid"/>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth">
        <el-input v-model="form.registerPwd" placeholder="密码" :show-password="form.loginShowPwd"
            prefixIcon="el-icon-lock"/>
      </el-form-item>

      <el-form-item :label-width="formLabelWidth">
        <el-input v-model="form.registerConfirmPwd" placeholder="确认密码" :show-password="form.loginShowPwd"
            prefixIcon="el-icon-bell"/>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth">
        <el-input v-model="form.registerInviteCode" placeholder="邀请码" prefixIcon="el-icon-s-promotion"/>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth">
        <el-input v-model="form.registerMail" placeholder="邮箱" prefixIcon="el-icon-message"/>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth">
        <el-input v-model="form.registerMailCode" placeholder="邮箱验证码" prefixIcon="el-icon-key" style="width: 58%"/>
        <el-button  @click="sendMail">
          发送验证码&nbsp;&nbsp;
          <i class="el-icon-loading" v-if="sendMailFlag"/>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 100%" type="primary" @click="userRegister">注册&nbsp;&nbsp;<i class="el-icon-check"></i></el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
export default {
  name: "register",
  data () {
    return {
      form: { //表单
        loginName: '',
        loginPwd: '',
        loginShowPwd: true, //是否显示密码
        registerName: '',//注册
        registerPwd: '',
        registerConfirmPwd: '',
        registerMail: '',
        registerMailCode: '',
        registerInviteCode: ''
      },
    }
  },
  methods: {
    sendMail() {//发送邮件
      const reg = new RegExp(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/);
      if (!reg.test(this.form.registerMail)) {//检测字符串是否符合正则表达式
        this.$message({
          message: '邮箱格式不正确',
          type: 'error'
        });
        return;
      }
      this.sendMailFlag = true;
      const mail = this.form.registerMail;
      const _this = this;
      user.sendMail(mail).then(res => {
        //成功调用接口后设置定时器，每隔600ms查询一次邮件状态
        var intervalId = setInterval(function () {
          user.getMailSendState(mail).then(res => {
            //邮件状态发生改变时
            if (res.data !== '0') {
              //清除定时器
              clearInterval(intervalId);
              if (res.data === '1') {
                _this.$message.success('发送成功');
              } else {
                _this.$message.error('发送失败');
              }
              _this.sendMailFlag = false;
            }
          });
        }, 600);

      }).catch(() => {
        this.sendMailFlag = false;
        this.$message.error('发送失败');
      })
    },
    userRegister() {//用户注册
      //判断是否输入字段
      if (this.form.registerName.length <= 0 || this.form.registerPwd.length <= 0 || this.form.registerConfirmPwd.length <= 0 ||
          this.form.registerMail.length <= 0 || this.form.registerMailCode.length <= 0 || this.form.registerInviteCode.length <= 0) {
        this.$message({
          message: '字段不符合规范',
          type: 'error'
        });
        return;
      }
      if (this.form.registerPwd !== this.form.registerConfirmPwd) {
        this.$message({
          message: '两次密码不一致',
          type: 'error'
        });
        return;
      }
      if (this.form.registerPwd.length < 6) {
        this.$message({
          message: '密码长度需大于6位',
          type: 'error'
        });
        return;
      }
      var reg = new RegExp(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/);

      if (!reg.test(this.form.registerMail)) {//检测字符串是否符合正则表达式
        this.$message({
          message: '邮箱格式不正确',
          type: 'error'
        });
        return;
      }
      user.register(this.form.registerName, this.form.registerPwd, this.form.registerMail, this.form.registerMailCode, this.form.registerInviteCode).then(res => {
        this.$message({
          message: '注册成功',
          type: 'success'
        })
        this.registerFormVisible = false
      })
    },
  }
}
</script>

<style scoped>
.register{
  padding: 200px;
  width: 400px;
  margin: auto;
  text-align: center;
}
</style>