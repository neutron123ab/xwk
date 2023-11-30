import  request from '@/utils/request';

// 班级信息
export  function  queryAllClassinfo(params) {
   return  request({
      url:'/classinfo/loadAllClassinfo',
      method:'post',
      data:params
   })
}
export  function queryClassinfoById(id) {
  return request({
     url:'/classinfo/queryClassinfoById?id='+id,
    method:'post',
  })
}

export  function addClassinfo(params) {
  return request({
    url:'/classinfo/addClassinfo?',
    method:'post',
    data:params
  })
}

export function  updateClassinfo(params) {

   return request({
      url:'/classinfo/updateClassinfo',
      method:'post',
      data:params
   })

}

export  function deleteClassinfo(id) {
  return request({
    url:'/classinfo/deleteClassinfo',
    method:'post',
    data:{id:id}
  })

}

//课程信息
export  function  queryAllCourseinfo(params) {
  return  request({
    url:'/courseinfo/loadAllCourseinfo',
    method:'post',
    data:params
  })
}
export  function queryCourseinfoById(id) {
  return request({
    url:'/courseinfo/queryCourseinfoById?id='+id,
    method:'post',
  })
}

export  function addCourseinfo(params) {
  return request({
    url:'/courseinfo/addCourseinfo?',
    method:'post',
    data:params
  })
}

export function  updateCourseinfo(params) {

  return request({
    url:'/courseinfo/updateCourseinfo',
    method:'post',
    data:params
  })

}

export  function deleteCourseinfo(id) {
  return request({
    url:'/courseinfo/deleteCourseinfo',
    method:'post',
    data:{id:id}
  })

}



//毕设信息
export  function  queryAllGradesign(params) {
  return  request({
    url:'/gradesign/loadAllGradesign',
    method:'post',
    data:params
  })
}
export  function queryGradesignById(id) {
  return request({
    url:'/gradesign/queryGradesignById?id='+id,
    method:'post',
  })
}

export  function addGradesign(params) {
  return request({
    url:'/gradesign/addGradesign?',
    method:'post',
    data:params
  })
}

export function  updateGradesign(params) {

  return request({
    url:'/gradesign/updateGradesign',
    method:'post',
    data:params
  })

}

export  function deleteGradesign(id) {
  return request({
    url:'/gradesign/deleteGradesign',
    method:'post',
    data:{id:id}
  })

}


//课程教学
export  function  queryAllTeacourse(params) {
  return  request({
    url:'/teacourse/loadAllTeacourse',
    method:'post',
    data:params
  })
}
export  function queryTeacourseById(id) {
  return request({
    url:'/teacourse/queryTeacourseById?id='+id,
    method:'post',
  })
}

export  function addTeacourse(params) {
  return request({
    url:'/teacourse/addTeacourse?',
    method:'post',
    data:params
  })
}

export function  updateTeacourse(params) {

  return request({
    url:'/teacourse/updateTeacourse',
    method:'post',
    data:params
  })

}

export  function deleteTeacourse(id) {
  return request({
    url:'/teacourse/deleteTeacourse',
    method:'post',
    data:{id:id}
  })

}

export  function  queryUser(params) {

  return request({
    url:'/teacourse/queryUserInfo',
    method:'post',
    data:params
  })

}


//竞赛信息
export  function  queryAllCompinfo(params) {
  return  request({
    url:'/compinfo/loadAllCompinfo',
    method:'post',
    data:params
  })
}
export  function queryCompinfoById(id) {
  return request({
    url:'/compinfo/queryCompinfoById?id='+id,
    method:'post',
  })
}

export  function addCompinfo(params) {
  return request({
    url:'/compinfo/addCompinfo?',
    method:'post',
    data:params
  })
}

export function  updateCompinfo(params) {

  return request({
    url:'/compinfo/updateCompinfo',
    method:'post',
    data:params
  })

}

export  function deleteCompinfo(id) {
  return request({
    url:'/compinfo/deleteCompinfo',
    method:'post',
    data:{id:id}
  })

}


export  function compCheckInfo(params) {
   return request({
     url:'/compinfo/changeChecked',
     method:'post',
     data:params
   })
}

export  function researchCheckInfo(params) {
   return request({
     url:'/searchresult/changeChecked',
     method:'post',
     data:params
   })
}


//科研 成果
export  function  queryAllSearchresult(params) {
  return  request({
    url:'/searchresult/loadAllSearchresult',
    method:'post',
    data:params
  })
}
export  function querySearchresultById(id) {
  return request({
    url:'/searchresult/querySearchresultById?id='+id,
    method:'post',
  })
}

export  function addSearchresult(params) {
  return request({
    url:'/searchresult/addSearchresult?',
    method:'post',
    data:params
  })
}

export function  updateSearchresult(params) {

  return request({
    url:'/searchresult/updateSearchresult',
    method:'post',
    data:params
  })

}

export  function deleteSearchresult(id) {
  return request({
    url:'/searchresult/deleteSearchresult',
    method:'post',
    data:{id:id}
  })

}




export  function getUserByUserName(username) {
  return request({
    url:'/system/user/getUserByUsername?username='+username,

    method:'post'
  })

}
export  function getUserPagesByUserName(params) {
  return request({
    url:'/system/user/getUserPagesByUserName',
    data:params,
    method:'post'
  })

}

export function registerUser(data) {
 return request({
   url:'/system/user/register',
   method:'post',
   data:data
 })
}




//教学进度
export  function  queryAllTeaJob(params) {
  return  request({
    url:'/teajob/loadAllTeaJob',
    method:'post',
    data:params
  })
}
export  function queryTeaJobById(id) {
  return request({
    url:'/teajob/queryTeaJobById?id='+id,
    method:'post',
  })
}

export  function addTeaJob(params) {
  return request({
    url:'/teajob/addTeaJob?',
    method:'post',
    data:params
  })
}

export function  updateTeaJob(params) {

  return request({
    url:'/teajob/updateTeaJob',
    method:'post',
    data:params
  })

}

export  function deleteTeaJob(id) {
  return request({
    url:'/teajob/deleteTeaJob',
    method:'post',
    data:{id:id}
  })

}


// teajobdetail
export function queryAllTeaJobDetail(data) {
 return request({
   url:'/teajobdetail/loadAllTeaJobDetail',
   method:'post',
   data:data
 })
}
export  function queryTeaJobDetailById(id) {
  return request({
    url:'/teajobdetail/queryTeaJobDetailById?id='+id,
    method:'post',
  })
}

export  function addTeaJobDetail(params) {
  return request({
    url:'/teajobdetail/addTeaJobDetail?',
    method:'post',
    data:params
  })
}

export function  updateTeaJobDetail(params) {

  return request({
    url:'/teajobdetail/updateTeaJobDetail',
    method:'post',
    data:params
  })

}

export  function deleteTeaJobDetail(id) {
  return request({
    url:'/teajobdetail/deleteTeaJobDetail',
    method:'post',
    data:{id:id}
  })

}



//教学工作量
export  function  queryAllTeachwork(params) {
  return  request({
    url:'/teachwork/loadAllTeachwork',
    method:'post',
    data:params
  })
}
export  function queryTeachworkById(id) {
  return request({
    url:'/teachwork/queryTeachworkById?id='+id,
    method:'post',
  })
}

export  function addTeachwork(params) {
  return request({
    url:'/teachwork/addTeachwork?',
    method:'post',
    data:params
  })
}

export function  updateTeachwork(params) {

  return request({
    url:'/teachwork/updateTeachwork',
    method:'post',
    data:params
  })

}

export  function deleteTeachwork(id) {
  return request({
    url:'/teachwork/deleteTeachwork',
    method:'post',
    data:{id:id}
  })

}

export  function teachworkCheckInfo(params) {
  return request({
    url:'/teachwork/changeChecked',
    method:'post',
    data:params
  })
}

export function exportTeachWorkExcel(params) {
  return request({
    url:'/teachwork/exportTeachWorkExcel',
    data:params,
    method:'post',
    responseType:'blob'
  })

}


//工作量
export  function  queryAllTeawork(params) {
  return  request({
    url:'/teawork/loadAllTeawork',
    method:'post',
    data:params
  })
}
export  function queryTeaworkById(id) {
  return request({
    url:'/teawork/queryTeaworkById?id='+id,
    method:'post',
  })
}

export  function addTeawork(params) {
  return request({
    url:'/teawork/addTeawork?',
    method:'post',
    data:params
  })
}

export function  updateTeawork(params) {

  return request({
    url:'/teawork/updateTeawork',
    method:'post',
    data:params
  })

}

export  function deleteTeawork(id) {
  return request({
    url:'/teawork/deleteTeawork',
    method:'post',
    data:{id:id}
  })
}

export  function teaworkCheckInfo(params) {
  return request({
    url:'/teawork/changeChecked',
    method:'post',
    data:params
  })
}

export function exportTeaworkExcel(params) {
    return request({
      url:'/teawork/exportTeaworkExcel',
      method:'post',
      data:params,
      responseType:'blob',
      contentType:'application/xml'
    })
}

export function queryProjectName(params) {
 return  request({
   url:'/teawork/queryProjectName',
   data:params,
   method:'post'
 })
}

//教师信息
export function queryTeachInfoByName(params) {
  return request({
    url:'/teacherInfo/queryTeachInfoByName',
    data:params,
    method:'post',
  })
}

export function queryAllRooms(params) {
  return request({
    url:'/classroom/list',
    data:params,
  })

}
