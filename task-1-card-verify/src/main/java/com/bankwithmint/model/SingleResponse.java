package com.bankwithmint.model;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:47 AM
 */
public class SingleResponse<T> {

    boolean success;

    T payload;

    public SingleResponse(boolean success, T payload) {
        this.success = success;
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
