import request from "@/utils/request";

// 查询key翻译列表
export function listConfig(query) {
  return request({
    url: "/system/keyConfig/list",
    method: "get",
    params: query,
  });
}

// 查询key翻译详细
export function getConfig(id) {
  return request({
    url: "/system/keyConfig/" + id,
    method: "get",
  });
}

// 新增key翻译
export function addConfig(data) {
  return request({
    url: "/system/keyConfig",
    method: "post",
    data: data,
  });
}

// 修改key翻译
export function updateConfig(data) {
  return request({
    url: "/system/keyConfig",
    method: "put",
    data: data,
  });
}

// 删除key翻译
export function delConfig(id) {
  return request({
    url: "/system/keyConfig/" + id,
    method: "delete",
  });
}
