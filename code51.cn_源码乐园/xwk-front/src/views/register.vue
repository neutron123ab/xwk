<template>
  <div class="register">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form">
      <h3 class="title">用户注册</h3>

      <el-form-item    label="登录账户" prop="username">
        <el-input v-model="registerForm.username" type="text" auto-complete="off" placeholder="登录账号">
<!--          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />-->
        </el-input>
      </el-form-item>

      <el-form-item   label="昵称" prop="nickname">
        <el-input v-model="registerForm.nickname" type="text" auto-complete="off" placeholder="昵称">
<!--          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />-->
        </el-input>
      </el-form-item>


      <el-form-item  label="密码" prop="password">
        <el-input
          v-model="registerForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
        >

        </el-input>
      </el-form-item>
      <el-form-item label="确认密码"   prop="confirmPassword">
        <el-input
          v-model="registerForm.confirmPassword"
          type="password"
          auto-complete="off"
          placeholder="确认密码"
        >
        </el-input>
      </el-form-item>

      <el-form-item  label="性别"  prop="sex">
         <el-select  v-model="registerForm.sex">
           <el-option   v-for="each in sexs" :key="each.value" :label="each.name" :value="each.value" ></el-option>
         </el-select>
      </el-form-item>

      <el-form-item label="所属角色">
        <el-select v-model="registerForm.roleId"  filterable >
          <el-option v-for="item in roles"
                     :key="item.id" :value="item.id"  :label="item.name" >

          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="所属学院">
        <el-select v-model="registerForm.deptId"  filterable >
          <el-option v-for="item in depts"
                     :key="item.id" :value="item.id"  :label="item.name" >

          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleRegister"
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>

      </el-form-item>
    </el-form>

  </div>
</template>

<script>


  import {
    addUser
  } from "@/api/system/user";
  import Cookies from "js-cookie";
  import { encrypt, decrypt } from '@/utils/jsencrypt'

  import {getSubDepts} from '@/api/system/dept';
  import {listRole} from '@/api/system/role';

  import {getUserByUserName} from '@/api/functions'
  import { registerUser } from "@/api/functions";
  export default {
    name: "Register",
    data() {
      const validatePass=(rule,value,callback)=>{
            if(this.registerForm.password!==value){
              callback(new Error("两次密码不一致"))
            }else{
              callback();
            }
       };
      const validateAsync=(rule,value,callback)=>{
        if(value){
          getUserByUserName(value).then(response=>{

             if(response.data!=null && response.data.id!=undefined){
                 callback(new Error("用户名已存在"))
             }else{
               callback();
             }
          })
        }

      };
      return {
        roles:[],
        codeUrl: "",
        cookiePassword: "",
        registerForm: {
          username: "",
          password: "",
          roleId: undefined,
          uuid: "",
          sex:1,
          confirmPassword:'',
          nickname:'',
        },
        sexs:[
          {value:1,name:"男"},
          {value:2,name:"女"},
        ],
        depts:[],
        registerRules: {
          username: [
            { required: true, trigger: "blur", message: "用户名不能为空" },
            {
              required: true,trigger: 'blur',validator:validateAsync
            }
          ],
          password: [
            { required: true, trigger: "blur", message: "密码不能为空" }
          ],
          confirmPassword: [{
            required:true,trigger:'blur',message:'确认密码不能为空'
          },
            {
              required: true, trigger: 'blur', validator: validatePass
            }
          ]

        },
        loading: false,
        redirect: undefined,
        searchList:[]
      };
    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirect = route.query && route.query.redirect;
        },
        immediate: true
      }
    },
    created() {
      this.initRoles();
      this.initDepts();
    },
    methods: {
      initRoles(){
        listRole().then(response=>{
          debugger;
          this.roles=response.data.list;
        })
      },
      initDepts(){
        getSubDepts().then(response=>{
          var getdata=response.data;
          for (var each of getdata){
             this.depts.push({
               id:each.id,
               name:each.name
             })
          }
        })
      },


      handleRegister() {
        this.$refs.registerForm.validate(valid => {
          if (valid) {
            this.loading = true;

            registerUser(this.registerForm).then(response=>{
              if(response.data!=0){
                this.loading=false;
                this.msgSuccess("注册成功");
                setTimeout( ()=>{
                  this.$router.push("/login").catch(()=>{
                    this.loading = false;
                  });
                },1000);
              }

            }).catch(()=>{
               this.loading=false;
            })
          }
        });
      }
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss">
  .register {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/images/login_bg.jpg");
    background-size: cover;
  }
  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #707070;
  }

  .register-form {
    border-radius: 6px;
    background: #ffffff;
    width: 400px;
    padding: 25px 25px 5px 25px;
    .el-input {
      height: 38px;
      input {
        height: 38px;
      }
    }
    .input-icon {
      height: 39px;
      width: 14px;
      margin-left: 2px;
    }
  }
  .register-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }
  .register-code {
    width: 33%;
    height: 38px;
    float: right;
    img {
      cursor: pointer;
      vertical-align: middle;
    }
  }
  .el-register-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
  }
  .register-code-img {
    height: 38px;
  }
</style>
