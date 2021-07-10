package com.gdrc.gamehall.server.domain.dto;

import java.util.HashMap;

public class DefaultResponse {
    private boolean ok;
    private HashMap<String, Object> data = new HashMap<>();

    public DefaultResponse() {}

    public DefaultResponse(boolean ok, HashMap<String, Object> data) {
        this.ok = ok;
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void setDataProperty(String key, Object value) {
        this.data.put(key, value);
    }
}
