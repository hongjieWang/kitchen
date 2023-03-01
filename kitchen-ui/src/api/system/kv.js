import request from "@/utils/request";

// 查询数据监控键值列表
export function listKv(query) {
  return request({
    url: "/system/kv/list",
    method: "get",
    params: query,
  });
}

// 查询数据监控键值详细
export function getKv(id) {
  return request({
    url: "/system/kv/" + id,
    method: "get",
  });
}

// 新增数据监控键值
export function addKv(data) {
  return request({
    url: "/system/kv",
    method: "post",
    data: data,
  });
}

// 修改数据监控键值
export function updateKv(data) {
  return request({
    url: "/system/kv",
    method: "put",
    data: data,
  });
}

// 删除数据监控键值
export function delKv(id) {
  return request({
    url: "/system/kv/" + id,
    method: "delete",
  });
}
