package com.yafnds.doubao.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: doubao
 * @description: 用户
 * @author: y19991
 * @create: 2021-03-03 09:22
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("ums_user")
public class UmsUser implements Serializable {

    private static final long serialVersionUID = -5051120337175047163L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 昵称，别名
     */
    @TableField("alias")
    private String alias;

    /**
     * 用户密码
     * JsonIgnore注解：在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口。
     */
    @JsonIgnore
    @TableField("password")
    private String password;

    /**
     * 用户头像（有默认头像）
     */
    @Builder.Default
    @TableField("avatar")
    private String avatar = "https://s3.ax1x.com/2020/12/01/DfHNo4.jpg";

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 个人简介
     */
    @Builder.Default
    @TableField("bio")
    private String bio = "自由职业者";

    @Builder.Default
    @TableField("score")
    private Integer score = 0;

    @JsonIgnore
    @TableField("token")
    private String token;

    @Builder.Default
    @TableField("active")
    private Boolean active = true;

    /**
     * 状态
     * 1：使用，0：已停用
     */
    @Builder.Default
    @TableField("`status`")
    private Boolean status = true;

    /**
     * 用户角色
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

}
