<template>
  <el-select
    v-model="params.courseId"
    filterable
    remote
    reserve-keyword
    placeholder="输入课程名称"
    :remote-method="remoteMethod"
    :loading="loading"
    :change="changed"
    :blur="changed"
    clearable="true"
  >
    <el-option
      v-for="item in options"
      :key="item.id"
      :label="item.name+'-'+item.id"
      :value="item.id">
    </el-option>
  </el-select>
</template>
<script>

  import {queryAllCourseinfo} from '@/api/functions';
  export  default {
    name:'courseSelect',
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
      loadData(query){
        debugger
        var getres=this.options.filter(each=>{return each.id == this.params.courseId});
        if(this.options.length==0 || getres.length==0){
          queryAllCourseinfo({name:query}).then(response=>{

            console.log(response);
            this.options=response.data.list;
            this.loading=false;
          }).catch(()=>{
            this.$message({
              message:'加载出错',
              type:'error'
            })
            this.loading=false;
          })
        }
      },
      remoteMethod(query){
        if (query !== '') {
          this.loading = true;
          setTimeout(() => {
            this.loadData(query);
          }, 200);
        } else {
          this.loadData(null);
        }
      },
      changed(){
        this.$emit("update:params",this.params);
      }
    },
    created() {
      this.remoteMethod(this.params.courseId);
    },
    watch:{
      'params.courseId':function(value) {
        this.remoteMethod(value);
      }
    }
  }

</script>
