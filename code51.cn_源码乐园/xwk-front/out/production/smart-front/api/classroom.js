import request from '@/utils/request'

// 创建
export function create(data) {
  return request({
    url: '/classroom/create',
    method: 'post',
    data: data
  })
}

// 更新
export function update(data) {
  return request({
    url: '/classroom/update',
    method: 'put',
    data: data
  })
}



// 获得
export function get(id) {
  return request({
    url: '/classroom/get?id=' + id,
    method: 'get'
  })
}

// 获得分页
export function getPage(query) {
  return request({
    url: '/classroom/page',
    method: 'get',
    params: query
  })
}

// 导出 Excel
export function exportExcel(query) {
  return request({
    url: '/classroom/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}


// 删除
export function deleted(id){
  return request({
    url: '/classroom/delete?id=' + id,
    method: 'delete'
  })
}
