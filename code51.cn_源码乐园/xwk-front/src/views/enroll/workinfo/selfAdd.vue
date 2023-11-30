<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="竞赛名称" prop="compName">
        <el-input v-model="queryParams.compName" placeholder="请输入竞赛名称" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="参赛作品" prop="workInfo">
        <el-input v-model="queryParams.workInfo" placeholder="请输入参赛作品" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                 >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                  >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="竞赛名称" align="center" prop="compName" />
      <el-table-column label="参赛人编号" align="center" prop="enrollerId" />
      <el-table-column label="参赛人姓名" align="center" prop="enrollerName" />
      <el-table-column label="状态" align="center" prop="statusInfo" />
      <el-table-column label="参赛作品" align="center" prop="workInfo" />
      <el-table-column label="评分" align="center" prop="getScore" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['enroll:work-info:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['enroll:work-info:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="竞赛名称" prop="compName">
          <el-autocomplete    :trigger-on-focus="false"   placeholder="键入开始搜索"
                              v-model="form.compName"
                              :fetch-suggestions="queryEnrollCompNameAysnc">
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="参赛人编号" prop="enrollerId">
          <el-input v-model="form.enrollerId" placeholder="请输入参赛人编号" />
        </el-form-item>
        <el-form-item label="参赛人姓名" prop="enrollerName">
          <el-input v-model="form.enrollerName" placeholder="请输入参赛人姓名" />
        </el-form-item>
        <el-form-item label="参赛作品" prop="workInfo">
          <el-input v-model="form.workInfo"   type="textarea" placeholder="请输入参赛作品" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createWorkInfo, updateWorkInfo, deleteWorkInfo, getWorkInfo, getWorkInfoPage, exportWorkInfoExcel } from "@/api/enroll/workInfo";

import {getCompInfoByEnrollInfo,getCompInfoByEnrollInfoList} from "@/api/enroll/compInfo"
import { mapGetters } from 'vuex'

export default {
  name: "selfAdd",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 参赛作品列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        compName: null,
        enrollerId: null,
        enrollerName: null,
        statusInfo: null,
        workInfo: null,
        getScore: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  computed:{
    ...mapGetters(['userId','name'])
  },
  created() {
    this.queryParams.enrollerId=this.userId;
    this.getList();
  },
  methods: {
    queryEnrollCompNameAysnc(queryString,cb){
      getCompInfoByEnrollInfoList({compName:queryString,enrollerId:this.userId}).then(response=>{
        debugger
        var resultArr=[];
        var  list=response.data;
        for (let item of list){
          resultArr.push({
            value:item.compName,
            id:item.id
          })
        }
        cb(resultArr);
      })
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getWorkInfoPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        compName: undefined,
        enrollerName: undefined,
        statusInfo: undefined,
        workInfo: undefined,
        getScore: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加参赛作品";
      this.form.enrollerId=this.userId;
      this.form.enrollerName=this.name;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getWorkInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改参赛作品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateWorkInfo(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createWorkInfo(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除参赛作品编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteWorkInfo(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有参赛作品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportWorkInfoExcel(params);
        }).then(response => {
          this.downloadExcel(response, '参赛作品.xls');
        })
    }
  }
};
</script>
