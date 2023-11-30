import request from '@/utils/request'

// 查询学院列表
export function listDept(query) {
  return request({
    url: '/system/dept/list',
    method: 'get',
    params: query
  })
}

// 查询学院列表（排除节点）
export function listDeptExcludeChild(deptId) {
  return request({
    url: '/system/dept/list/exclude/' + deptId,
    method: 'get'
  })
}

// 查询学院详细
export function getDept(deptId) {
  return request({
    url: '/system/dept/get?id=' + deptId,
    method: 'get'
  })
}

// 获取学院精简信息列表
export function listSimpleDepts() {
  return request({
    url: '/system/dept/list-all-simple',
    method: 'get'
  })
}

// 新增学院
export function addDept(data) {
  return request({
    url: '/system/dept/create',
    method: 'post',
    data: data
  })
}

// 修改学院
export function updateDept(data) {
  return request({
    url: '/system/dept/update',
    method: 'put',
    data: data
  })
}

// 删除学院
export function delDept(id) {
  return request({
    url: '/system/dept/delete?id=' + id,
    method: 'delete'
  })
}

export function getSubDepts() {
  return request({
    url: '/system/dept/getSubDepts',
    method: 'get'
  })
}
