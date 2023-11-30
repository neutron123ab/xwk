import request from '@/utils/request'

// 创建竞赛报名
export function createCompInfo(data) {
  return request({
    url: '/enroll/comp-info/create',
    method: 'post',
    data: data
  })
}

// 更新竞赛报名
export function updateCompInfo(data) {
  return request({
    url: '/enroll/comp-info/update',
    method: 'put',
    data: data
  })
}

// 删除竞赛报名
export function deleteCompInfo(id) {
  return request({
    url: '/enroll/comp-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得竞赛报名
export function getCompInfo(id) {
  return request({
    url: '/enroll/comp-info/get?id=' + id,
    method: 'get'
  })
}


export function getCompInfoByEnrollInfo(data) {
  return request({
    url: '/enroll/comp-info/getCompInfoByEnrollInfo',
    method: 'post',
    data
  })
}
export function getCompInfoByEnrollInfoList(data) {
  return request({
    url: '/enroll/comp-info/getCompInfoByEnrollInfoList',
    method: 'post',
    data
  })
}


// 获得竞赛报名分页
export function getCompInfoPage(query) {
  return request({
    url: '/enroll/comp-info/page',
    method: 'get',
    params: query
  })
}

// 导出竞赛报名 Excel
export function exportCompInfoExcel(query) {
  return request({
    url: '/enroll/comp-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
