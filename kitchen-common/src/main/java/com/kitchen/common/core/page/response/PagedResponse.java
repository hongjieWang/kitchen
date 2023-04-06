package com.kitchen.common.core.page.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.poi.ss.formula.functions.T;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: wanghongjie
 * @date: 2023-04-04
 * @verison: 1.0
 */
public class PagedResponse extends BaseResponse<PagedResponse.PageData> {

    public PagedResponse() {
        super();
        this.data = new PageData();
    }

    public PagedResponse(Integer result, String message) {
        super(result, message);
        this.data = new PageData();
    }

    public void setCount(Long count) {
        this.getData().setCount(count);
    }

    @JsonIgnore
    public Long getCount() {
        return this.getData().getCount();
    }

    public void setPagedData(List<?> list) {
        this.getData().setData(list);
    }

    @JsonIgnore
    public List<?> getPagedData() {
        return this.getData().getData();
    }

    @Override
    public PageData getData() {
        return super.getData();
    }

    public static class PageData {
        private Long count;
        private List<?> data = new LinkedList<>();

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public List<?> getData() {
            return data;
        }

        public void setData(List<?> data) {
            this.data = data;
        }
    }
}
