package com.cy.store.entity;

import lombok.Data;

import java.rmi.server.UID;

/**
 * Created by Intellij IDEA.
 * User: 17129
 * Date: 2023/1/31
 * 用户实体类:SpringBoot约定大于配置
 */

/**
 *
 */
@Data
public class User extends BaseEntity {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}
