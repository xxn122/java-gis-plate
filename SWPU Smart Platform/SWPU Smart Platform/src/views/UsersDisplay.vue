<template>
  <div class="div">
    <el-input border
    style="width: 90%" type="search" placeholder="请输入内容" v-model="seachname">
    </el-input>
    <el-button type="primary" class="button" @click="search">搜索</el-button>
  </div>
  
    <el-table
    :data="a"
    border
    style="width: 1310px"
    v-if="display">
    <el-input>sff</el-input>
    <el-table-column
      fixed
      prop="id"
      label="id"
      width="250">
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户名"
      width="220">
    </el-table-column>
    <el-table-column
      prop="password"
      label="密码"
      width="220">
    </el-table-column>
    <el-table-column
      prop="email"
      label="邮箱"
      width="220">
    </el-table-column>
    
    <el-table-column
      fixed="right"
      label="操作"
      width="400">
      <el-button link type="primary" size="small">编辑</el-button>
      <el-button link type="warning" size="small">删除</el-button>
    </el-table-column>
  </el-table>
  <div class="result" v-if="result"> 
      <div>
        <div class="label">id: 
          <div class="inner"><el-input  v-model="id"></el-input></div>
        </div>
        <div class="label">用户名：
          <div class="inner"><el-input  v-model="username"></el-input></div>
        </div >
        <div class="label">密码：
          <div class="inner"><el-input  v-model="password"></el-input></div>
        </div >
        <div class="label">邮箱：
          <div class="inner"><el-input  v-model="email"></el-input></div>
        </div >
        <div class="label">
          <el-button link type="primary" size="small" @click="update">更改</el-button>
        <el-button link type="warning" size="small" @click="close">关闭</el-button>
        </div>
        
      </div>
    </div>
    
</template>
<script>
import axios from 'axios'
    export default {

        data(){
            return {
                a:[],
                seachname: '',
                searchdata:[],
                id:'',
                username:'',
                password:'',
                email:'',
                result:false,
                display:true,
            }
        },
        mounted(){
            this.test()
        },
        methods:{
            test(){
                axios({
                    method: "POST",
                    url: "http://localhost:8080/user/displayuser",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(response => {
                    console.log(response);
                    let arr= response.data;
                    console.log(arr);
                    this.a=arr;
                    
                    this.dialogVisible=true;
                }).catch(function (e) {
                    console.log(e)})
            },
            search(){
               console.log(this.seachname);
               axios({
                    method: "POST",
                    url: "http://localhost:8080/user/search",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: {
                         username: this.seachname
                  },
                }).then(response => {
                  let arr= response.data.data;
                    console.log(arr);
                    this.searchdata=arr;
                    console.log(this.searchdata);
                    if(this.searchdata==null) {
                      this.result=false;
                      this.display=true;
                      alert("搜索失败");
                      this.test();
                      return;
                    }
                    this.id=response.data.data.id;
                    this.username=response.data.data.username;
                    this.password=response.data.data.password;
                    this.email=response.data.data.email;
                    this.result=true;
                    this.display=false;
                    console.log(this.username)
                }).catch(function (e) {
                    console.log(e)})
            },
            update(){
              axios({
                    method: "POST",
                    url: "http://localhost:8080/user/update",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: {
                         username: this.seachname,
                         password:  this.password,
                         email: this.email,
                  },
                }).then(response =>{
                  console.log(response);
                  this.result=false;
                  this.display=true;
                  this.test();
                })
            },
            close(){
                this.result=false;
                this.display=true;
                this.test();
            }
        }
    }
</script>
<style>
     .button{
        width: 131px;;
     }
     .div{
      width:1310px ;
      
      float: left;
     }
     .result {
  position: absolute;
  background: #fff;
  width: 1310px;
  padding: 10px;
  top: 130px;
  left: 100px;
  z-index: 999;
  border-radius: 5px;
  text-align:left;
  border: 1px solid rgb(87, 87, 87, .6);
}
.inner{
  width: 1220px;
  float: right;
}
.label{
  width: 1290px;
  height: 40px;
}

</style>