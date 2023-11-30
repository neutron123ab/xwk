<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="报名条件" prop="conditionInfo">
        <el-input v-model="queryParams.conditionInfo" placeholder="请输入报名条件" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="所属学院" prop="deptId">
        <el-input v-model="queryParams.deptId" placeholder="请输入所属学院" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="审核状态" prop="checkStatus">
        <el-select v-model="queryParams.checkStatus" placeholder="请选择审核状态" clearable size="small">
          <el-option label="待审核" value="待审核" />
          <el-option label="审核通过" value="审核通过" />
          <el-option label="审核不通过" value="审核不通过" />
        </el-select>
      </el-form-item>
      <el-form-item label="竞赛描述信息" prop="descInfo">
        <el-input v-model="queryParams.descInfo" placeholder="请输入竞赛描述信息" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="竞赛名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入竞赛名称" clearable size="small" @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['comp:info:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['comp:info:export']">导出</el-button>
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
      <el-table-column label="报名条件" align="center" prop="conditionInfo" />
      <el-table-column label="学科类别" align="center" prop="xkType" />
      <el-table-column label="所属学院" align="center" prop="deptId" />
      <el-table-column label="审核状态" align="center" prop="checkStatus" />
      <el-table-column label="竞赛描述信息" align="center" prop="descInfo" />
      <el-table-column label="竞赛名称" align="center" prop="name" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['comp:info:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['comp:info:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报名条件" prop="conditionInfo">
          <el-input v-model="form.conditionInfo" placeholder="请输入报名条件" />
        </el-form-item>
        <el-form-item label="学科类别" prop="xkType">
          <el-input v-model="form.xkType" type="text" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="所属学院" prop="deptId">

          <el-select v-model="form.deptId"  filterable >
            <el-option v-for="item in depts"
                       :key="item.id" :value="item.id"  :label="item.name" >

            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核状态" prop="checkStatus">
          <el-select v-model="form.checkStatus" placeholder="请选择审核状态">
            <el-option label="待审核" value="待审核" />
            <el-option label="审核通过" value="审核通过" />
            <el-option label="审核不通过" value="审核不通过" />
          </el-select>
        </el-form-item>
        <el-form-item label="竞赛描述信息" prop="descInfo">
          <el-input v-model="form.descInfo" placeholder="请输入竞赛描述信息" />
        </el-form-item>
        <el-form-item label="竞赛名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入竞赛名称" />
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
  import { createInfo, updateInfo, deleteInfo, getInfo, getInfoPage, exportInfoExcel } from "@/api/comp/info";
  import {getSubDepts} from '@/api/system/dept';
  export default {
    name: "Info",
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
        // 竞赛列表
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
          conditionInfo: null,
          xkType: null,
          deptId: null,
          checkStatus: null,
          descInfo: null,
          name: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        },
        depts:[]
      };
    },
    created() {
      this.getList();
      this.initDepts();
    },
    methods: {
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
      /** 查询列表 */
      getList() {
        this.loading = true;
        // 处理查询参数
        let params = {...this.queryParams};
        this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
        // 执行查询
        getInfoPage(params).then(response => {
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
          conditionInfo: undefined,
          xkType: undefined,
          deptId: undefined,
          checkStatus: undefined,
          descInfo: undefined,
          name: undefined,
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
        this.title = "添加竞赛";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id;
        getInfo(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改竞赛";
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
            updateInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
            return;
          }
          // 添加的提交
          createInfo(this.form).then(response => {
            this.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const id = row.id;
        this.$confirm('是否确认删除竞赛编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteInfo(id);
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
        this.$confirm('是否确认导出所有竞赛数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInfoExcel(params);
        }).then(response => {
          this.downloadExcel(response, '竞赛.xls');
        })
      }
    }
  };
</script>
