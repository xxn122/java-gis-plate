<template>
<div>
      <el-container>
        <el-header class="header">
          <div>
            <div class="wrapper">
              <div class="logo" >
              </div>
              <div class="text">
                智慧校园平台管理员端
              </div>
              <div>
                 <el-avatar class="user">
                  user 
                 </el-avatar>
              </div>
              
            </div>
          </div>
        </el-header>
        
      </el-container>
    </div>  
    <ul class="sidebar">
    <li v-for="(item, index) in sidebar" :key="index" class="sidebar-item" @click="item.visible = !item.visible">{{ item.name }}</li>
    </ul> 
    <users-display v-if="sidebar.usersdisplay.visible"></users-display>
</template>
<script>
import { reactive  } from 'vue'
import { onMounted,provide } from 'vue'
import UsersDisplay from'@/views/UsersDisplay.vue'
import axios from 'axios'
  export default{
    name: 'app',
    components: {
        UsersDisplay,
    },
    setup(){
      let sidebar = reactive({
      usersdisplay: {
        name: '用户信息展示',
        visible: false,
      },
      admindisplay: {
        name: '管理信息展示',
        visible: false,
      },
    })
    return {
       sidebar,
    }
    },
    
  }
</script>
<style scoped>
   html,body {
    margin: 0;
    height: 100%;
  }
  .header {
    background-color: rgb(31, 73, 158);
    color: #fff;
    text-align: center;
    height: 100px;
  }
  .wrapper {
  
    margin: 0 auto 20px;
    height: 65px;
    line-height: 65px;
    text-align: center;
  }
  
  .logo{
    margin-top: 15px;
    margin-bottom: 15px;
    background: #000;
    width: 283px;
    background: url('@/assets/topbg.png') no-repeat;
    height: 65px;
    float: left;
  
  }
  .text {
    margin: 25px 0px 5px 30px;
    float: left;
    line-height: 65px;
  }
  .user{
    margin: 25px 0px 5px 30px;
    float: right;
    line-height: 65px;
  }
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    height: 100% auto;
  }
  .panels {
    position: absolute;
    top: 0;
    left: 200px;
    z-index: 999;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  .sidebar {
    float: left;
    z-index: 999;
    height: 100vh;
    width: 100px;
    margin: 0;
    background: rgb(31, 73, 158);
    color: #fff;
  }
  .sidebar-item {
    cursor: pointer;
    transition: .3s;
    padding: 10px 5px;
  }
  .sidebar-item:hover {
    background: blue;
  }
  #map{
	margin: 0px ;padding: 0; width: 93%;height:100vh; float: right ;
 }
 .query {
  position: absolute;
  background: #fff;
  padding: 0%;
  color: #000;
  top: 100px;
  left: 200px;
  z-index: 999;
  border-radius: 2px;
  text-align: right;
  border: 1px solid rgb(87, 87, 87, .6);
}
.query {
  position: absolute;
  background: #fff;
  padding: 10px;
  top: 155px;
  left: 200px;
  z-index: 999;
  border-radius: 5px;
  text-align: right;
  border: 1px solid rgb(87, 87, 87, .6);
}
</style>