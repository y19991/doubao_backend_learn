package com.yafnds.doubao.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @program: doubao
 * @description: 用户登录 与前端交互的字段
 * @author: y19991
 * @create: 2021-03-03 15:12
 **/

@Data
public class LoginDTO {

    /**
     * 登录用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 15, message = "登录用户名长度在2-15之间")
    private String username;

    /**
     * 登录密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "登录密码长度在6-20之间")
    private String password;

    /**
     * 判断是否记住我
     */
    private String rememberMe;
}
