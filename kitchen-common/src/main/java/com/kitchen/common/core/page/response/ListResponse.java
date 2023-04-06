package com.kitchen.common.core.page.response;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: yaoll
 * @date: 2020-09-04
 * @verison: 1.0
 */
public class ListResponse<T> extends BaseResponse<List<T>> {

	public void add(T t) {
		if (data == null) {
			data = new LinkedList<>();
		}
		data.add(t);
	}

	@Override
	public List<T> getData() {
		List<T> data = super.getData();
		if (data == null) {
			data = new LinkedList<>();
		}
		return data;
	}
}
