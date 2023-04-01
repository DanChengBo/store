package com.cy.store.util;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by Intellij IDEA.
 * User: 17129
 * Date: 2023/2/14
 * Json格式的数据进行响应
 */
@Data
public class JsonResult<E> implements Serializable {
    //状态码
    private Integer state;
    //描述信息
    private String message;
    //数据
   private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message=e.getMessage();
    }
    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }
}
