package com.yafnds.doubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.dto.LoginDTO;
import com.yafnds.doubao.model.dto.RegisterDTO;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.yafnds.doubao.jwt.JwtUtil.USER_NAME;

/**
 * @program: doubao
 * @description: 用户
 * @author: y19991
 * @create: 2021-03-03 11:51
 **/

@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController{

    @Resource
    private IUmsUserService umsUserService;

    /**
     * 用户注册
     * @param registerDTO
     * @return 用户信息与成功提示
     */
    @PostMapping("/register")
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO registerDTO) {
        // 执行注册
        UmsUser user = umsUserService.executeRegister(registerDTO);
        // 如果前端传入的数据为空，返回失败信息
        if (ObjectUtil.isEmpty(registerDTO)) {
            return ApiResult.failed("账号注册失败");
        }

        // 封装注册用户信息，准备传回前端
        Map<String, Object> map = new HashMap<>(16);
        map.put("user",user);

        // 把用户信息传入成功方法，与成功信息一同传回前端
        return ApiResult.success(map, "注册成功");
    }

    /**
     * 用户登录
     * @param loginDTO
     * @return 用户令牌与成功提示
     */
    @PostMapping("/login")
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO loginDTO) {

        // 执行登录方法并接收返回的加密令牌
        String token = umsUserService.executeLogin(loginDTO);

        // 判断令牌是否为空
        if (ObjectUtil.isEmpty(token)) {
            return ApiResult.failed("账号密码错误");
        }

        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);

        return ApiResult.success(map, "登录成功");
    }

    /**
     * 退出登录
     * @return 注销成功信息
     */
    @GetMapping("/logout")
    public ApiResult<Object> logout() {
        return ApiResult.success(null ,"注销成功");
    }

    /**
     * 传给前台用户信息
     * @param userName
     * @return 用户信息与成功提示
     */
    @GetMapping("/info")
    public ApiResult<UmsUser> getUser(@RequestHeader(USER_NAME) String userName) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

}
