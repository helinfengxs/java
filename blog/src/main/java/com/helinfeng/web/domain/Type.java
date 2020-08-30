package com.helinfeng.web.domain;

import lombok.Data;

@Data
public class Type {
    private String id;
    private String name;
    private String insertTime;
    /**
     *
     * @param id   分类id
     * @param name 分类名称
     */
    public Type(String id, String name,String insetTime) {
        this.id = id;
        this.name = name;
        this.insertTime = insetTime;
    }

    public Type() {
    }
}
