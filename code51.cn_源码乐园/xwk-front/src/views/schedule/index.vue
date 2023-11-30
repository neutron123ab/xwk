<template>
  <div class="app-container">
    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="50">
      <el-form-item label="教师" prop="tId">
        <tea-select :params="queryParams"></tea-select>
      </el-form-item>
      <el-form-item label="课程信息" prop="courseId">
        <CourseSelect :params="queryParams"></CourseSelect>
      </el-form-item>
      <el-form-item label="学期" prop="term">
        <el-select :clearable="true" v-model="queryParams.term">
          <el-option
            v-for="item in term"
            :key="item.label"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
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
                   v-hasPermi="['course:schedule:create']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['course:schedule:export']">导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <!--      <el-table-column label="" align="center" prop="id" />-->
      <el-table-column label="教师" align="center" prop="teacherName"/>
      <el-table-column label="学院" align="center" prop="collegeName"/>
      <el-table-column label="时刻时间段" align="center" prop="period">
        <template  slot-scope="text,scope">
          {{text|periodFilter}}
        </template>
      </el-table-column>
      <el-table-column label="课程信息" align="center" prop="courseName"/>
      <el-table-column label="学期" align="center" prop="term"/>
      <el-table-column label="周次" align="center" prop="week"/>
      <el-table-column label="星期信息" align="center" prop="weekDay">
        <template slot-scope="text,scope">
          {{text|weekDayFilter}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>

      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="教师信息" prop="tId">
          <!--          <el-input v-model="form.tId" placeholder="请输入教师编号" />-->
          <tea-select :params.sync="form"></tea-select>
        </el-form-item>
        <!--        <el-form-item label="学院编号" prop="collegeId">-->
        <!--          <el-input v-model="form.collegeId" placeholder="请输入学院编号" />-->
        <!--        </el-form-item>-->
        <el-form-item label="时刻时间段" prop="period" label-width="90px">
          <el-select  multiple  :clearable="true" v-model="form.period">
            <el-option
              v-for="item in periodOptions"
              :key="item.label"
              :label="'第'+item.value+'节'"
              :value="item.value">
            </el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="课程信息" prop="courseId" label-width="90px">

          <course-select      :params="form"></course-select>

        </el-form-item>
        <el-form-item label="学期" prop="term" label-width="90px">
          <el-select :clearable="true" v-model="form.term">
            <el-option
              v-for="item in term"
              :key="item.label"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="周次" prop="week" label-width="90px">
          <el-select :clearable="true" v-model="form.week">
            <el-option
              v-for="item in weeks"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="星期信息" prop="weekDay" label-width="90px">
          <el-select :clearable="true" v-model="form.weekDay">
            <el-option
              v-for="item in weekdays"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
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
  import {
    createSchedule,
    updateSchedule,
    deleteSchedule,
    getSchedule,
    getSchedulePage,
    exportScheduleExcel
  } from '@/api/system/schedule'
  import { queryAllCourseinfo } from '@/api/functions'
  import { listSimpleDepts } from '@/api/system/dept'
  import TeaSelect from './teaSelect'
  import CourseSelect from './courseSelect'
  import Treeselect from '@riophae/vue-treeselect'
  import '@riophae/vue-treeselect/dist/vue-treeselect.css'

  const periodOptions = [
    {
      value: '1',
      label: '1'
    },
    {
      value: '2',
      label: '2'
    },
    {
      value: '3',
      label: '3'
    },
    {
      value: '4',
      label: '4'
    },
    {
      value: '5',
      label: '5'
    },
    {
      value: '6',
      label: '6'
    },
    {
      value: '7',
      label: '7'
    },
    {
      value: '8',
      label: '8'
    },
    {
      value: '9',
      label: '9'
    },
    {
      value: '10',
      label: '10'
    },


  ]
  const term = [
    { value: 1, label: '2020-2021学年' },
    { value: 2, label: '2019-2020学年' },
    { value: 3, label: '2018-2019学年' },
    { value: 4, label: '2017-2018学年' },
    { value: 5, label: '2016-2017学年' }
  ]
  const weekdays = [
    { value: '1', label: '星期一' },
    { value: '2', label: '星期二' },
    { value: '3', label: '星期三' },
    { value: '4', label: '星期四' },
    { value: '5', label: '星期五' },
    { value: '6', label: '星期六' },
    { value: '7', label: '星期日' }
  ]
  const weeks = [
    { value: '第一周', label: '第一周' },
    { value: '第二周', label: '第二周' },
    { value: '第三周', label: '第三周' },
    { value: '第四周', label: '第四周' },
    { value: '第五周', label: '第五周' },
    { value: '第六周', label: '第六周' },
    { value: '第七周', label: '第七周' },
    { value: '第八周', label: '第八周' },
    { value: '第九周', label: '第九周' },
    { value: '第十周', label: '第十周' },
    { value: '第十一周', label: '第十一周' },
    { value: '第十二周', label: '第十二周' },
    { value: '第十三周', label: '第十三周' },
    { value: '第十四周', label: '第十四周' },
    { value: '第十五周', label: '第十五周' },
    { value: '第十六周', label: '第十六周' }
  ]
  export default {
    name: 'Schedule',
    components: {
      CourseSelect,
      TeaSelect
    },
    data() {
      return {
        deptOptions: [],
        weeks,
        term,
        weekdays,
        // 遮罩层
        loading: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 列表
        list: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNo: 1,
          pageSize: 10,
          tId: null,
          collegeId: null,
          period: null,
          courseId: null,
          term: null,
          week: null,
          weekDay: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {},
        periodOptions
      }
    },
    created() {
      this.getList()
      this.getTreeselect()
    },
    methods: {
      getTreeselect() {
        listSimpleDepts().then(response => {
          // 处理 deptOptions 参数
          this.deptOptions = []
          this.deptOptions.push(...this.handleTree(response.data, 'id'))
        })

      },
      /** 查询列表 */
      getList() {
        this.loading = true
        // 处理查询参数
        let params = { ...this.queryParams }
        // 执行查询
        getSchedulePage(params).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.loading = false
        })
      },
      /** 取消按钮 */
      cancel() {
        this.open = false
        this.reset()
      },
      /** 表单重置 */
      reset() {
        this.form = {
          id: undefined,
          tId: undefined,
          collegeId: undefined,
          period: undefined,
          courseId: undefined,
          term: undefined,
          week: undefined,
          weekDay: undefined
        }
        this.resetForm('form')
      },
      /** 转换学院数据结构 */
      normalizer(node) {
        if (node.children && !node.children.length) {
          delete node.children
        }
        return {
          id: node.id,
          label: node.name,
          children: node.children
        }
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNo = 1
        this.getList()
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm('queryForm')
        this.handleQuery()
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        this.form = row;

        this.form.period=JSON.parse(row.period);
        // this.form.weekDay=this.weekdays[this.form.weekDay];
        this.title = '修改'
        this.showOpen();
      },
      showOpen(){
        this.open = true;
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (!valid) {
            return
          }

          this.form.period=JSON.stringify(this.form.period);

          // 修改的提交
          if (this.form.id != null) {
            updateSchedule(this.form).then(response => {
              this.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
            return
          }

          // 添加的提交
          createSchedule(this.form).then(response => {
            this.msgSuccess('新增成功')
            this.open = false
            this.getList()
          })
        })
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const id = row.id
        this.$confirm('是否确认删除编号为"' + id + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return deleteSchedule(id)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        // 处理查询参数
        let params = { ...this.queryParams }
        params.pageNo = undefined
        params.pageSize = undefined
        // 执行导出
        this.$confirm('是否确认导出所有数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return exportScheduleExcel(params)
        }).then(response => {
          this.downloadExcel(response, '.xls')
        })
      }
    },
    filters: {
      weekDayFilter(value) {
        const weekdays = [
          { value: '1', label: '星期一' },
          { value: '2', label: '星期二' },
          { value: '3', label: '星期三' },
          { value: '4', label: '星期四' },
          { value: '5', label: '星期五' },
          { value: '6', label: '星期六' },
          { value: '7', label: '星期日' }
        ]
        let find = weekdays.find(each => {
          return each.value == value.row.weekDay
        })
        return find.label
      },
      periodFilter(value){

        var arr=JSON.parse(value.row.period);
        var str="";
        for (let i = 0; i < arr.length; i++) {
           str=str+"第"+arr[i]+"节";
        }
        return  str ;
      }
    }
  }
</script>
