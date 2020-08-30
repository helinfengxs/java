package com.helinfeng.web.domain;

import lombok.Data;

@Data
public class Tag {
    private String id;
    private String name;
    private String insertTime;
    public Tag(String id, String name,String insertTime) {
        this.id = id;
        this.name = name;
        this.insertTime = insertTime;
    }

    public Tag() {
    }
}
