<template>
  <el-card id="bar">
    <div>
      <el-row>
        <el-menu :default-active="activeIndex" class="el-menu-demo" :router="true" mode="horizontal"
                 @select="handleSelect">


<!--          <el-menu-item index="1" route="/">首页</el-menu-item>-->
<!--          <el-menu-item index="2" style="margin-left: 2%" route="/message">留言板</el-menu-item>-->
<!--          <el-menu-item index="3" style="margin-left: 2%" route="/announcement">本站公告</el-menu-item>-->


<!--          <el-menu-item id="space" index="" @click="egg" class="hidden-xs-only"/>-->
<!--          &lt;!&ndash;<el-menu-item/>&ndash;&gt;-->

          <div style="width: 20%;float: left;margin: 10px 2% 0px -5%" class="hidden-xs-only">
            <el-input style="left: 30%" placeholder="搜索博客" v-if="showSearch" v-model="searchTxt" suffix-icon="el-icon-search"
                      @keyup.enter.native="searchSubmit"/>
          </div>

          <el-menu-item style="left: 30%">
            <span>
              <router-link to="/" style="text-decoration: none;font-size: 25px">博客客客客</router-link>
            </span>
          </el-menu-item>
          <el-menu-item class="hidden-xs-only"  style="left: 60%;  ">
            <el-button v-if="this.$store.state.token==''" type="text" @click="toLogin">登录</el-button>

            <el-submenu class="hidden-xs-only" v-if="this.$store.state.token!==''" :router="true" index="4" style="width:120px">
              <template  slot="title">&nbsp;&nbsp;{{this.$store.state.name}}&nbsp;&nbsp;</template>

              <el-menu-item route="/newBlog" index="4-1">&nbsp;&nbsp;&nbsp;
                <i class="el-icon-edit"></i>
                写博客
              </el-menu-item>
              <el-menu-item route="/myBlog" index="4-3">&nbsp;&nbsp;&nbsp;
                <i class="el-icon-s-home"></i>
                我的博客
              </el-menu-item>
              <el-menu-item route="/account" index="4-2">&nbsp;&nbsp;&nbsp;
                <i class="el-icon-s-tools"></i>
                账号设置
              </el-menu-item>


              <el-menu-item route="/admins/userManage" index="4-4" v-if="this.$store.state.roles.indexOf('ADMIN') > -1">
                &nbsp;&nbsp;&nbsp;
                <i class="el-icon-loading"></i>管理后台
              </el-menu-item>

              <el-menu-item @click="logout">&nbsp;&nbsp;&nbsp;
                <i class="el-icon-switch-button"/>退出登录
              </el-menu-item>
            </el-submenu>
          </el-menu-item>

          <el-menu-item class="hidden-xs-only" v-if="this.$store.state.token==''" style="left: 60%">
            <el-button type="text" @click="toRegister">注册</el-button>
          </el-menu-item>

        </el-menu>
      </el-row>

    </div>

  </el-card>
</template>
<script>
  import user from '@/api/user'

  import 'element-ui/lib/theme-chalk/display.css';

  export default {
    name: 'bar',
    data() {
      return {
        searchTxt: '',//搜索框
        activeIndex: '1',//默认选中第一项->首页
        showSearch:true,
      }
    },
    watch: {
      // 监控当前页面path，防止刷新页面显示错误
      '$route.path': {
        deep: true,
        immediate: true,
        handler(to, from) {
          if (to === '/') {
            this.showSearch = true
          }
          // else if (to === '/message') {
          //   this.activeIndex = '2'
          // } else if (to === '/announcement') {
          //   this.activeIndex = '3'
          // } else {
          //   this.activeIndex = '4'
          // }
        }
      }
    },
    methods: {
      toLogin() {
        this.$router.push({path: '/login'})
        this.showSearch = false
      },
      toRegister(){
        this.$router.push({path: '/register'})
      },
      handleSelect(key, keyPath) {
//        console.log(key, keyPath);
        if (key != null && key !== '')
          this.activeIndex = key
      },

      egg() {
        this.$notify({
          title: '彩蛋',
          message: '耐心等待 十分钟后获得一枚邀请码',
          type: 'success',
          duration: 120
        });
      },
      logout() {  //退出登录
        user.logout().then(res => {
          this.$store.commit('logout')//清除token等信息
          this.$message({
            message: '退出成功',
            type: 'success'
          });
          this.$router.push({ //路由跳转
            path: '/'
          })
        })

      },
      searchSubmit() {
        if (this.searchTxt.length <= 0)
          return;
        this.$router.push({ //路由跳转
          path: '/searchBlog/' + this.searchTxt
        })
        this.searchTxt = '';//清空搜索框
      }
    }
  }
</script>
<style>
  #bar {
    margin-top: -50px;
    margin-bottom: 0px;
  }

  #space {
    margin: 0 21% 0 20%;
  }
</style>
