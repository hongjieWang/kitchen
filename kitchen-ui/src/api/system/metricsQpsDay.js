import request from "@/utils/request";

// 查询应用埋点列表
export function listDay(query) {
  return request({
    url: "/system/metricsQpsDay/list",
    method: "get",
    params: query,
  });
}

// 查询应用埋点详细
export function getDay(id) {
  return request({
    url: "/system/metricsQpsDay/" + id,
    method: "get",
  });
}

// 新增应用埋点
export function addDay(data) {
  return request({
    url: "/system/metricsQpsDay",
    method: "post",
    data: data,
  });
}

// 修改应用埋点
export function updateDay(data) {
  return request({
    url: "/system/metricsQpsDay",
    method: "put",
    data: data,
  });
}

// 删除应用埋点
export function delDay(id) {
  return request({
    url: "/system/metricsQpsDay/" + id,
    method: "delete",
  });
}
