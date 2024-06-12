<template>
    <el-row class="login-page">

      <el-col :span="6" :offset="9" class="form">
<!-- 登录界面 -->
      <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      :rules="rules"
      label-width="auto"
      class="demo-ruleForm"
      v-if="type==1"
    >
    
    <el-form-item label="用户名" prop="username">
      <el-input v-model="ruleForm.username"></el-input>
    </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" type="password" autocomplete="off" />
      </el-form-item>
      
      
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)"
          >登录</el-button
        >
        <el-button @click="resetForm(ruleFormRef)">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-link type="primary" @click="type=2" style="float: left;">
              注册
        </el-link>
        <el-link type="success" @click="type=3"style="left: 20px;">
                管理员端
        </el-link>
      </el-form-item>
    </el-form>


    <!-- 注册界面 -->
    <el-form
    ref="regFornRef"
    :model = "registerForm"
    status-icon
    :rules="rules"
    label-width="auto"
    class="demo-registerForm"
    v-if="type==2"
    >
    <el-form-item label="用户名" prop="username">
      <el-input v-model="registerForm.username"></el-input>
    </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="registerForm.password" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item label="重复密码" prop="checkPass">
        <el-input
          v-model="registerForm.checkPass"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" @click="submitForm2(regFornRef)"
          >登录</el-button
        >
        <el-button @click="resetForm(regFornRef)">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-link type="primary" @click="type=1" style="float: left;">
              返回
        </el-link>
        <el-link type="success" @click="type=3"style="left: 20px;">
                管理员端
        </el-link>
      </el-form-item>
    </el-form>


    <!-- 管理员界面 -->
      <el-form
      ref="adminRef"
      :model="Adimn"
      status-icon
      :rules="rules"
      label-width="auto"
      class="demo-adminForm"
      v-if="type==3"
    >
    
    <el-form-item label="用户名" prop="username">
      <el-input v-model="Adimn.username"></el-input>
    </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="Adimn.password" type="password" autocomplete="off" />
      </el-form-item>
      
      
      <el-form-item>
        <el-button type="primary" @click="submitForm1(adminRef)"
          >登录</el-button
        >
        <el-button @click="resetForm(adminRef)">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-link type="primary" @click="type=2" style="float: left;">
              注册
        </el-link>
        <el-link type="success" @click="type=1"style="left: 20px;">
              普通用户端
        </el-link>
      </el-form-item>
    </el-form>

   </el-col>
  </el-row>
    
    
  </template>
  
  <script lang="ts" setup>
  import { reactive, ref,provide  } from 'vue'
  import type { FormInstance, FormRules } from 'element-plus'
  import { useRouter } from 'vue-router'
  import axios from 'axios'
  import isloaded from '@/Global'  

  let parent = { isloaded: false };
  provide('isloaded', parent);
  
  const router = useRouter()
  const ruleFormRef = ref<FormInstance>()
  const regFornRef = ref<FormInstance>()
  const adminRef = ref<FormInstance>()
  const type = ref(1) 

  
  //用户名规则
  const validateuser =  (rule: any, value: any, callback: any) =>{
    if (value === '') {
      callback(new Error('请输入用户名')) 
  }else{
    callback()
  }
  }
  //规则
  const validatePass = (rule: any, value: any, callback: any) => {
    if (value === '') {
      callback(new Error('请输入密码'))
    } else {
      if (ruleForm.checkPass !== '') {
        if (!ruleFormRef.value) return
        ruleFormRef.value.validateField('checkPass', () => null)
      }
      callback()
    }
  }
  const validatePass2 = (rule: any, value: any, callback: any) => {
    if (value === '') {
      callback(new Error('请再次输入密码'))
    } else if (value !== ruleForm.password) {
      callback(new Error("两次密码不匹配"))
    } else {
      callback()
    }
  }
  //创建合适的变量
  var  ruleForm = reactive({
    password: '',
    checkPass: '',
    username: '',
  })
  var registerForm = reactive({
    password: '',
    checkPass: '',
    username: '',
  })
  var  Adimn = reactive({
    password: '',
    username: '',
  })
  //判定表单内容的规则
  const rules = reactive<FormRules<typeof ruleForm>>({
    password: [{ validator: validatePass, trigger: 'blur' }],
    checkPass: [{ validator: validatePass2, trigger: 'blur' }],
    username: [{ validator: validateuser, trigger: 'blur' }],
  })
  //普通用户提交用户名，并登录跳转
  const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate((valid) => {
      if (valid) {
        axios({
          method: "POST",
          url: "http://localhost:8080/user/login", 
          headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
          },
          data: {
               username: ruleForm.username,
               password: ruleForm.password,
                  },
          }).then(response => {
            console.log(response.data.code);

            if (response.data.code==0){
            router.push({ path: 'layout' }),
            isloaded.setloaded(true),
            console.log(isloaded.isloaded);}
            else  {
              alert("账号未注册或者密码错误");
            }
                 
          }).catch(function (e) {
        console.log(e)
      }) 
       } 
       else 
       {
        return false
      }
    })
  }
  const submitForm1 = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate((valid) => {
      if (valid) {
        axios({
          method: "POST",
          url: "http://localhost:8080/admin/login", 
          headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
          },
          data: {
               username: Adimn.username,
               password: Adimn.password,
                  },
          }).then(response => {
            console.log(response.data);

                if (response.data.code==0){
                  router.push({ path: 'admin' })
                  isloaded.setloaded(true),
                  console.log(isloaded.isloaded);
                }
                  else  {
              alert("账号未注册或者密码错误");
            }
          }).catch(function (e) {
        console.log(e)
      }) 
       } 
       else 
       {
        return false
      }
    })
  }
  const submitForm2 = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate((valid) => {
      if (valid) {
        axios({
          method: "POST",
          url: "http://localhost:8080/user/register", 
          headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
          },
          data: {
               username: registerForm.username,
               password: registerForm.password,
                  },
          }).then(response => {
            console.log(response.data.code);

                if (response.data.code==0){
                  isloaded.setloaded(true),
                  console.log(isloaded.isloaded);
                  router.push({ path: 'layout' }) }
                
          }).catch(function (e) {
        console.log(e)
      })
       } 
       else 
       {
        return false
      }
    })
  }
  //重置用户信息
  const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
  }
  
  
  </script>
  <style scoped>
  .login-page {
    width: 100%;
    height: 100vh;
    background: url('@/assets/BF540C8EF940056F30837C4AE3911538.jpg') no-repeat ;
    .form {
      display: flex;
      flex-direction: column;
      justify-content: center;
      user-select: none;
      
      
    }}
  
  </style>
  