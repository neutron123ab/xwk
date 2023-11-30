import request from '@/utils/request'

// 创建竞赛
export function createInfo(data) {
  return request({
    url: '/comp/info/create',
    method: 'post',
    data: data
  })
}

// 更新竞赛
export function updateInfo(data) {
  return request({
    url: '/comp/info/update',
    method: 'put',
    data: data
  })
}

// 删除竞赛
export function deleteInfo(id) {
  return request({
    url: '/comp/info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得竞赛
export function getInfo(id) {
  return request({
    url: '/comp/info/get?id=' + id,
    method: 'get'
  })
}

// 获得竞赛分页
export function getInfoPage(query) {
  return request({
    url: '/comp/info/page',
    method: 'get',
    params: query
  })
}

// 导出竞赛 Excel
export function exportInfoExcel(query) {
  return request({
    url: '/comp/info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
