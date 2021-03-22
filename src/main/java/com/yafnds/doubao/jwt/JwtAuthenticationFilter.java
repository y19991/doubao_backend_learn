package com.yafnds.doubao.jwt;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: doubao
 * @description: 游客请求过滤器
 * @author: y19991
 * @create: 2021-03-04 11:42
 **/

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        try {
            if (isProtectedUrl(request)) {
                //判断请求的方法是否是 options(预检)
                if (!request.getMethod().equals("OPTIONS")) {
                    request = JwtUtil.validateTokenAndAddUserIdToHeader(request);
                }
            }
        } catch (Exception e) {
            // 抛出错误信息到前端并拒绝请求
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        // 允许请求通过该过滤器
        filterChain.doFilter(request,response);
    }

    /**
     * 判断传入的链接是否是被保护的
     * @param request
     * @return
     */
    private boolean isProtectedUrl(HttpServletRequest request) {

        List<String> protectedPaths = new ArrayList<>();

        // 受保护的请求
        // 用户信息
        protectedPaths.add("/ums/user/info");

        // 创建帖子
        protectedPaths.add("/post/create");
        // 修改帖子
        protectedPaths.add("/post/update");
        // 删除帖子
        protectedPaths.add("/post/delete/*");

        // 添加评论
        protectedPaths.add("/comment/add_comment");

        // 关注用户
        protectedPaths.add("/relationship/subscribe/*");
        // 取消关注
        protectedPaths.add("/relationship/unsubscribe/*");
        // 判断是否关注该作者
        protectedPaths.add("/relationship/validate/*");

        /*
            判断传进来的路径是否受到保护
            并返回相应结果
         */
        boolean bFind = false;
        for (String passedPath : protectedPaths) {
            // 判断路径是否匹配
            bFind = pathMatcher.match(passedPath, request.getServletPath());
            if (bFind) {
                break;
            }
        }
        return bFind;
    }


}
