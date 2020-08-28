package com.bankwithmint.model;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:47 AM
 */
public class PagedResponse<T> {

    boolean success;

    int start;

    int limit;

    int size;

    T payload;

    public PagedResponse(boolean success, T payload) {
        this.success = success;
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
