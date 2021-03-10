package com.yafnds.doubao.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.common.exception.ApiAsserts;
import com.yafnds.doubao.jwt.JwtUtil;
import com.yafnds.doubao.mapper.UmsUserMapper;
import com.yafnds.doubao.model.dto.LoginDTO;
import com.yafnds.doubao.model.dto.RegisterDTO;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.service.IUmsUserService;
import com.yafnds.doubao.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: doubao
 * @description: 用户
 * @author: y19991
 * @create: 2021-03-03 11:08
 **/

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IUmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser>
        implements IUmsUserService {

    /**
     * 执行注册
     * @param registerDTO
     * @return 注册对象
     */
    @Override
    public UmsUser executeRegister(RegisterDTO registerDTO) {

        // 查询是否有相同用户名或邮箱的用户
        LambdaQueryWrapper<UmsUser> userWrapper = new LambdaQueryWrapper<>();
        /*
            判断
                UmsUser 类中的 username 是否等于 RegisterDTO 类中的 name
                或
                UmsUser 类中的 email 是否等于 RegisterDTO 类中的 email
         */
        userWrapper.eq(UmsUser::getUsername, registerDTO.getName()).or().eq(UmsUser::getEmail, registerDTO.getEmail());
        UmsUser user = baseMapper.selectOne(userWrapper);

        if (!ObjectUtil.isEmpty(user)) {
            ApiAsserts.fail("用户名或邮箱已存在！");
        }

        // 把注册用户的数据存入库
        UmsUser addUser = UmsUser.builder()
                .username(registerDTO.getName())
                .alias(registerDTO.getName())
                .password(MD5Utils.getPwd(registerDTO.getPass()))
                .email(registerDTO.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);

        return addUser;

    }

    /**
     * 根据用户名查询对应的用户
     * @param name
     * @return
     */
    @Override
    public UmsUser getUserByUsername(String name) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, name));
    }

    /**
     * 判断用户名和密码是否正确
     *  正确：返回加密后的用户令牌
     *  错误：返回错误提示
     * @param loginDTO
     * @return
     */
    @Override
    public String executeLogin(LoginDTO loginDTO) {
        String token = null;

        try {
            // 判断用户名和密码是否正确
            // 根据用户名查出对应的用户
            UmsUser user = getUserByUsername(loginDTO.getUsername());
            // 用MD5加密前端传进来的用户登录密码
            String encodePwd = MD5Utils.getPwd(loginDTO.getPassword());
            // 判断登录密码与库中是否相等
            if (!encodePwd.equals(user.getPassword())) {
                throw new Exception("密码错误");
            }

            // 加密登录用户信息，并准备传回给用户
            token = JwtUtil.generateToken(loginDTO.getUsername());

        } catch (Exception e) {
            log.warn("用户不存在或者密码错误===>{}", loginDTO.getUsername());
        }

        return token;

    }

}