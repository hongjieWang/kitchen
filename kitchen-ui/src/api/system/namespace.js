import request from "@/utils/request";

// 查询命名空间列表
export function listNamespace(query) {
  return request({
    url: "/system/namespace/list",
    method: "get",
    params: query,
  });
}

// 查询命名空间详细
export function getNamespace(id) {
  return request({
    url: "/system/namespace/" + id,
    method: "get",
  });
}

// 新增命名空间
export function addNamespace(data) {
  return request({
    url: "/system/namespace",
    method: "post",
    data: data,
  });
}

// 修改命名空间
export function updateNamespace(data) {
  return request({
    url: "/system/namespace",
    method: "put",
    data: data,
  });
}

// 删除命名空间
export function delNamespace(id) {
  return request({
    url: "/system/namespace/" + id,
    method: "delete",
  });
}
