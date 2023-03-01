import request from "@/utils/request";

// 查询数据监控JVM参数列表
export function listJvm(query) {
  return request({
    url: "/system/jvm/list",
    method: "get",
    params: query,
  });
}

// 查询数据监控JVM参数详细
export function getJvm(id) {
  return request({
    url: "/system/jvm/" + id,
    method: "get",
  });
}

// 新增数据监控JVM参数
export function addJvm(data) {
  return request({
    url: "/system/jvm",
    method: "post",
    data: data,
  });
}

// 修改数据监控JVM参数
export function updateJvm(data) {
  return request({
    url: "/system/jvm",
    method: "put",
    data: data,
  });
}

// 删除数据监控JVM参数
export function delJvm(id) {
  return request({
    url: "/system/jvm/" + id,
    method: "delete",
  });
}
