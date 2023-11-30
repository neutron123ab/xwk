<template>
  <el-select
    v-model="params.tid"
    filterable
    remote
    reserve-keyword
    placeholder="输入教师姓名来查找"
    :remote-method="remoteMethod"
    :loading="loading"
    :change="changed"
    :blur="changed"
    :clearable="true"
  >
    <el-option
      v-for="item in options"
      :key="item.id"
      :label="item.username+'-'+item.id"
      :value="item.id">
    </el-option>
  </el-select>
</template>
<script>

  import {queryTeachInfoByName} from '@/api/functions';

  export  default {
    name:'teaSelect',
    props:{
      params:{
        type:Object,
        default:{}
      }
    },
    data(){
      return {
        queryParam:{
          pageNo: 1,
          pageSize: 15,
        },
        options: [],
        value: [],
        list: [],
        loading: false,
      }
    },
    methods:{
      remoteMethod(query){
        if (query !== '') {
          this.loading = true;
          setTimeout(() => {
            this.loading = false;
            queryTeachInfoByName({username:query}).then(response=>{
              console.log(response);
              this.options=response.data;
              this.loading=false;
            }).catch(()=>{
               this.$message({
                 message:'加载出错',
                 type:'error'
               })
              this.loading=false;
            })
          }, 200);
        } else {
          this.options = [];
        }
      },
      changed(){
        this.$emit("update:params",this.params);
      }
    },
    created() {
      if(this.params.tid){
        this.remoteMethod(this.params.tid);
      }
    }

  }

</script>
