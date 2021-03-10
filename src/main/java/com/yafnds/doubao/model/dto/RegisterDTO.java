package com.yafnds.doubao.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @program: doubao
 * @description: 用户注册 与前端交互的字段
 * @author: y19991
 * @create: 2021-03-03 10:44
 **/

@Data
public class RegisterDTO {

    /**
     * 用户名
     */
    @NotEmpty(message = "请输入用户名")
    @Length(min = 2, max = 15, message = "长度在2-15之间")
    private String name;

    /**
     * 用户密码
     */
    @NotEmpty(message = "请输入密码")
    @Length(min = 6, max = 20, message = "长度在6-20之间")
    private String pass;

    /**
     * 再次确认密码
     */
    @NotEmpty(message = "请再次输入密码")
    @Length(min = 6, max = 20, message = "长度在6-20之间")
    private String checkPass;

    /**
     * 邮箱
     */
    @NotEmpty(message = "请输入电子邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

}
