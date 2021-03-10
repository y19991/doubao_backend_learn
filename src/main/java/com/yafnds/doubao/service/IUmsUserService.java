package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.dto.LoginDTO;
import com.yafnds.doubao.model.dto.RegisterDTO;
import com.yafnds.doubao.model.entity.UmsUser;

/**
 * @program: doubao
 * @description: 用户
 * @author: y19991
 * @create: 2021-03-03 10:56
 **/

public interface IUmsUserService extends IService<UmsUser> {

    /**
     * 用户注册功能
     * @param registerDTO
     * @return 注册对象
     */
    UmsUser executeRegister(RegisterDTO registerDTO);

    /**
     * 用户登录功能
     * @param loginDTO
     * @return 加密后的登录对象信息 token
     */
    String executeLogin(LoginDTO loginDTO);

    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    UmsUser getUserByUsername(String name);
}
