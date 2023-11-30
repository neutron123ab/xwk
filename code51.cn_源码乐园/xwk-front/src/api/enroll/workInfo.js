import request from '@/utils/request'

// 创建参赛作品
export function createWorkInfo(data) {
  return request({
    url: '/enroll/work-info/create',
    method: 'post',
    data: data
  })
}

// 更新参赛作品
export function updateWorkInfo(data) {
  return request({
    url: '/enroll/work-info/update',
    method: 'put',
    data: data
  })
}

// 删除参赛作品
export function deleteWorkInfo(id) {
  return request({
    url: '/enroll/work-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得参赛作品
export function getWorkInfo(id) {
  return request({
    url: '/enroll/work-info/get?id=' + id,
    method: 'get'
  })
}

// 获得参赛作品分页
export function getWorkInfoPage(query) {
  return request({
    url: '/enroll/work-info/page',
    method: 'get',
    params: query
  })
}

// 导出参赛作品 Excel
export function exportWorkInfoExcel(query) {
  return request({
    url: '/enroll/work-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
