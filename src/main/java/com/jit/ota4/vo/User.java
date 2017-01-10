package com.jit.ota4.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.jit.ota4.jsonview.Views;

/**
 * Created by Administrator on 2017/1/4.
 */
public class User {
    private Integer id;
    @JsonView(Views.Public.class)
    private String name;
    @JsonView(Views.Public.class)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
