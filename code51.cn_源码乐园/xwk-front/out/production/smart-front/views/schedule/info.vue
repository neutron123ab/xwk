<template>
  <div class="app-container">
    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="50">
      <el-form-item  v-if="!isTeacher"   label="教师" prop="tId">
        <tea-select :params="queryParams"></tea-select>
      </el-form-item>

      <el-form-item label="学期" prop="term">
        <el-select :clearable="true" v-model="queryParams.term">
          <el-option
            v-for="item in term"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="周次" prop="week">
        <el-select :clearable="true" v-model="queryParams.week">
          <el-option
            v-for="item in weeks"
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


    <el-card style="padding:20px">
      <div>
        <div id="class-table" class="class-table">
          <!--        表头-->
          <div class="ct-header">
          </div>

          <!--        表格主体-->
          <div class="ct-body">
          </div>
        </div>
      </div>

    </el-card>


  </div>
</template>


<script>
  import { createSchedule, updateSchedule, deleteSchedule, getSchedule, getSchedulePage, exportScheduleExcel ,getClassTable} from "@/api/system/schedule";
  import TeaSelect from './teaSelect'

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
    name: "ScheduleInfo",
    components: { TeaSelect },
    data() {
      return {
        isTeacher:false,
        deptOptions:[],
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
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          tId: null,
          collegeId: null,
          period: null,
          courseId: null,
          term: "2020-2021学年",
          week: "第一周",
          weekDay: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        },
        periodOptions,
        Config: {
          max_day: 7,//最大天数
          session: 10,//多少节课
          selector: "#class-time-table",
          data:undefined,
        },
        ColorList:[
          'bg-color-1',
          'bg-color-2',
          'bg-color-3',
          'bg-color-4',
          'bg-color-5',
          'bg-color-6',
          'bg-color-7',
          'bg-color-8',
          'bg-color-9',
          'bg-color-10',
        ],
        Week2Cn:{
          1: "周一",
          2: "周二",
          3: "周三",
          4: "周四",
          5: "周五",
          6: "周六",
          7: "周日"
        }
      };
    },
    created() {

      debugger
      var deptid=this.$store.getters.deptId;
      this.isTeacher=deptid == 112;
      if(this.isTeacher){
         this.queryParams.tId=this.$store.getters.userId;
      }
      this.getList();
    },
    methods: {
      getRandomNumber(){
        return parseInt(Math.random()*10+"");
      },
      setConfig: function (config = {}) {
        this.Config=Object.assign(this.Config,config);
      },
      creatBase: function () {

        this.emptyTable();

        var ct_header = document.querySelector(this.Config.selector + " > .ct-header"),
          ct_body = document.querySelector(this.Config.selector + " > .ct-body"),
          week_group_date = this.getDates(),//列出本周“月日”数组
          html = '';

        //生成基础表格
        for (let day = 0; day <= this.Config['max_day']; day++) {
          if (day === 0) {
            html = '<div class="ct-th" style="font-size: 14px;">'  +'节次\星期'+ '</div>';
          } else {
            var active = "";
            if (day === new Date().getDay()) active = " active";

            html = '<div class="ct-th ' + active + '">\n' +
              '    <span class="title">' + this.Week2Cn[day] + '</span>\n' +
              // '    <span class="day">' + week_group_date[day] + '</span>\n' +
              '</div>';
          }
          ct_header.innerHTML += html;//插入 header

          html = '<div class="ct-cr">';
          for (let session = 0; session < this.Config['session']; session++) {
            if (day === 0) {
              html += '<div class="ct-td">' + (session + 1) + '</div>';
            }
          }
          html += '</div>';
          ct_body.innerHTML += html;//插入 body
        }
      },
      fillInfo: function () {
        var color_arr = {}, color_list =this.ColorList,
          next, kong;
        //填充课程信息
        for (let day in this.Config['data']) {
          next = 1;
          for (let item in this.Config['data'][day]) {
            let html;
            html = '';
            let day_item = this.Config['data'][day][item];
            item = parseInt(item);//当前节次

            if (!color_arr[day_item['courseName']]) {
              color_arr[day_item['courseName']] = color_list[this.getRandomNumber()];
              // color_list.splice(0, 1);//删除用掉了的颜色数组
            }

            if (item > next) {
              //填充空白区域（就是没有课的区域）
              kong = item - next;
              html = '<div class="ct-td kongbai col-' + kong + '"></div>';
            }

            var uuid = function (n) { // 生成n位长度的字符串
              var str = "abcdefghijklmnopqrstuvwxyz-_"; // 可以作为常量放到random外面
              var result = "";
              for (var i = 0; i < n; i++) {
                result += str[parseInt(Math.random() * str.length)];
              }
              return result;
            }(12);

            html += '<div data-index="' + day + ',' + item + '" class="ct-td col-' + day_item['length'] + ' ' + color_arr[day_item['courseName']] + '">' +
              '    <div class="name">' +
              day_item['courseName'] +
              '    </div>' +
              '<div class="teacher">' +
              'by ' + day_item['teacherName'] +
              '</div>' +
              '    <div class="address">' +
              '@' + day_item['room']['bname'] + ' ' + day_item['room']['rname'] +
              '    </div>' +
              '</div>';

            document.querySelectorAll(this.Config['selector'] + " > .ct-body .ct-cr")[day].innerHTML += html;

            next = item + day_item['length'];
          }
        }
      },
      updateColCss: function () {
        let one = document.querySelector(this.Config['selector'] + " > div.ct-body > div:nth-child(1) > div:nth-child(1)").offsetHeight;
        var css = '.ct-body .ct-cr .ct-td.col-1 {height: ' + (one - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-2 {height: ' + (one * 2 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-3 {height: ' + (one * 3 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-4 {height: ' + (one * 4 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-5 {height: ' + (one * 5 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-6 {height: ' + (one * 6 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-7 {height: ' + (one * 7 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-8 {height: ' + (one * 8 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-9 {height: ' + (one * 9 - 10) + 'px}' +
          '.ct-body .ct-cr .ct-td.col-10 {height: ' + (one * 10 - 10) + 'px}';

        var head = document.head || document.getElementsByTagName('head')[0];
        var style = document.createElement('style');
        style.type = 'text/css';
        if (style.styleSheet) {
          style.styleSheet.cssText = css;
        } else {
          style.appendChild(document.createTextNode(css));
        }
        head.appendChild(style);
      },
      //填充课程信息
      //获取本周 月日 数组
      getDates: function () {
        var currentDate = new Date();
        var timesStamp = currentDate.getTime();
        var currenDay = currentDate.getDay();
        var dates = {}, _Date, date;

        for (var i = 0; i < 7; i++) {
          _Date = new Date(timesStamp + 24 * 60 * 60 * 1000 * (i - (currenDay + 6) % 7));

          let month
          month = _Date.getMonth() + 1;
          date = _Date.getDate();

          if (month < 10) {
            month = "0" + month;
          }
          if (date < 10) {
            date = "0" + date;
          }

          dates[i + 1] = month + "-" + date;
        }
        return dates
      },
      emptyTable(){

        let elementById = document.getElementById("class-table")
        elementById.innerHTML=" <!--        表头-->\n" +
          "          <div class=\"ct-header\">\n" +
          "          </div>\n" +
          "\n" +
          "          <!--        表格主体-->\n" +
          "          <div class=\"ct-body\">\n" +
          "          </div>";
      },
      initTable(){

         //构建表格数据
         var dataObj={};
         var getList=this.list;
         for (let i = 0; i < getList.length; i++) {
            var getObj=getList[i];
            var getIndex=getObj.week;
            var getChildren=getObj.children;
            var eachDayObj={};
            for (let j = 0; j <getChildren.length ; j++) {
              var each=getChildren[j];
              debugger
              var period=JSON.parse(each.period);
              var classStart=period[0];
              var length=period.length;
              var classObj={
                courseName:each.courseName,
                teacherName:each.teacherName,
                room:each.room,
                length:length
              };
              //添加元素
              eachDayObj[classStart]=classObj;
            }

            dataObj[getIndex]=eachDayObj;

        }
         this.setConfig({
          selector: "#class-table",
          // data: {
          //   1: {
          //     //表示从第 1 节课开始
          //     3: {
          //       courseName: "高等数学",
          //       teacherName: "彭璇",
          //       room: {
          //         bname: "A教学楼",
          //         rname: '702'
          //       },
          //       length: 1,//节次有多长
          //     },
          //
          //     1: {
          //       courseName: "自习",
          //       teacherName: null,
          //       room: {
          //         bname: "A教学楼",
          //         rname: 606
          //       },
          //       length: 1
          //     },
          //
          //     //表示从第 3 节课开始
          //     7:{
          //       courseName: "大学英语",
          //       teacherName: "王珏",
          //       room: {
          //         bname: "B教学楼",
          //         rname: 701
          //       },
          //       length: 2,//节次有多长
          //     }
          //   },
          //   3: {
          //     1: {
          //       name: "动态网页技术",
          //       teacherName: "元芳",
          //       room: {
          //         bname: "B综合楼",
          //         rname: 303
          //       },
          //       length: 4
          //     },
          //   },
          // }
          data:dataObj
        });
         this.creatBase();//建立基础表格框架
         this.updateColCss();//更新CSS样式，以适应各种屏幕
         this.fillInfo();//填充课程信息
      },
      /** 查询列表 */
      getList() {
        this.loading = true;
        // 处理查询参数
        let params = {...this.queryParams};
        // 执行查询
        getClassTable(params).then(response => {
          this.list = response.data;
          this.loading = false;
          this.$nextTick(()=>{
            this.initTable();
          })
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
          tId: undefined,
          collegeId: undefined,
          period: undefined,
          courseId: undefined,
          term: undefined,
          week: undefined,
          weekDay: undefined,
        };
        this.resetForm("form");
      },

      /** 搜索按钮操作 */
      handleQuery() {
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        var objc={
          tId: null,
          collegeId: null,
          period: null,
          courseId: null,
          term: "2020-2021学年",
          week: "第一周",
          weekDay: null,
        };
        if(this.isTeacher){
          objc.tId=this.$store.getters.userId;
        }
         this.queryParams=Object.assign(this.queryParams,objc);
        // this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id;
        this.form = row;
        this.open = true;
        this.title = "修改";
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (!valid) {
            return;
          }
          // 修改的提交
          if (this.form.id != null) {
            updateSchedule(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
            return;
          }
          // 添加的提交
          createSchedule(this.form).then(response => {
            this.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const id = row.id;
        this.$confirm('是否确认删除编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteSchedule(id);
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
        // 执行导出
        this.$confirm('是否确认导出所有数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportScheduleExcel(params);
        }).then(response => {
          this.downloadExcel(response, '.xls');
        })
      }
    },
    filters:{
      weekDayFilter(value){
        const weekdays=[
          {value:'monday',label:'星期一'},
          {value:'Tuesday',label:'星期二'},
          {value:'Wednesday',label:'星期三'},
          {value:'Thursday',label:'星期四'},
          {value:'Friday',label:'星期五'},
          {value:'Saturday',label:'星期六'},
          {value:'Sunday',label:'星期日'},
        ];
       let find = weekdays.find(each=>{ return  each.value==value.row.weekDay})
       return  find.label;
      }

    },



  };
</script>




<style>


  /*课程表表头*/
  .ct-header{
    display: flex;
  }

  /*课程表表头 > 子盒子*/
  .ct-header .ct-th{
    width: 100px;
    min-height: 42px;
    text-align: center;
    border-right: 2px solid #fff;
    background-color: #f2f6f7;
    padding: 4px 0;
    border-radius: 4px;
  }
  .ct-header .ct-th:first-child{
    width: 40px;
  }
  .ct-header .ct-th:last-child{
    border-left: none;
  }
  /*设置激活后的样式*/
  .ct-header .ct-th.active{
    height: auto;
    /*box-sizing: border-box;!*设置边框为 内边框*!*/
    border-bottom: 3px solid #23b7e5;
    color: #23b7e5;
  }

  /*子盒子 span 设置块*/
  .ct-header .ct-th span{
    display: block;
  }
  /*设置周几*/
  .ct-header .ct-th .title{
    font-weight: 600;
    letter-spacing: 3px;/*设置字符间距*/
    padding-left: 3px;
  }
  /*设置月日*/
  .ct-header .ct-th .day{
    font-size: 13px;
  }


  .ct-body{
    display: flex;
  }
  /*设置每列垂直排列*/
  .ct-body .ct-cr{
    display: flex;
    flex-direction: column;
    width: 102px;
  }
  .ct-body .ct-cr:first-child{
    width: 40px;
    background-color: #f2f6f7;
    border-right: 2px solid #fff;
  }

  /*设置表格 小格子*/
  .ct-body .ct-cr .ct-td{
    border-top: 2px solid #fff;
    border-right: 2px solid #fff;

    padding: 4px;
    min-height: 78px;

    /*使小格子内 元素 居中*/
    display: flex;
    justify-content: center;
    flex-flow: column;
    text-align: center;

    cursor: pointer;
    width: auto;
    border-radius: 8px;
  }
  .ct-body .ct-cr .ct-td.kongbai{
    cursor: unset;
  }

  /*.ct-body .ct-cr .ct-td.kongbai{*/
  /*    border-radius: unset;*/
  /*}*/
  /*跨1行的*/
  .ct-body .ct-cr .ct-td.col-1{
    height: 88px;
  }
  /*跨2行的*/
  .ct-body .ct-cr .ct-td.col-2{
    height: 177px;
  }
  /*跨3行的*/
  .ct-body .ct-cr .ct-td.col-3{
    height: 254px;
  }
  /*跨4行的*/
  .ct-body .ct-cr .ct-td.col-4{
    height: 354px;
  }

  /*“课程名字”*/
  .ct-body .ct-cr .ct-td .name{
    font-size: 14px;
    font-weight: 600;
  }
  /*上课地点*/
  .ct-body .ct-cr .ct-td .address{
    /*font-weight: 300;*/
    font-size: 12px;
  }
  .ct-body .ct-cr .ct-td .teacher{
    font-size: 13px;
  }



  /*
  课程颜色表
  可以自定义，只需要按照格式添加、修改样式即可
  添加了新的样式后，在 classTimeTable.js 42行 按照格式添加类名即可
  */
  div[class*="bg-color-"]{
    color: #fff;
  }
  .bg-color-1{
    background-color: rgb(240, 82, 97);
  }
  .bg-color-2{
    background-color: rgb(72, 168, 228);
  }
  .bg-color-3{
    background-color: rgb(82, 219, 154);
  }
  .bg-color-4{
    background-color: rgb(255, 208, 97);
  }
  .bg-color-6{
    background-color: rgb(0, 150, 136);
  }
  .bg-color-5{
    background-color: rgb(63, 81, 181);
  }
  .bg-color-7{
    background-color: rgb(121, 86, 73);
  }
  .bg-color-8{
    background-color: rgb(96, 125, 139);
  }
  .bg-color-9{
    background-color: rgb(103, 58, 183);
  }
  .bg-color-10{
    background-color: rgb(180, 149, 225);
  }

</style>
