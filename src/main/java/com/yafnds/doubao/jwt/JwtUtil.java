package com.yafnds.doubao.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @program: doubao
 * @description: token加密工具类
 * @author: y19991
 * @create: 2021-03-03 16:15
 **/

public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 1000小时
     */
    public static final long EXPIRATION_TIME = 3600_000_000L;

    /**
     * 加密密匙
     */
    public static final String SECRET = "ThisIsASecret";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String USER_NAME = "userName";

    /**
     * 生成令牌
     * @param userId
     * @return 加密后的令牌
     */
    public static String generateToken(String userId) {

        Map<String, Object> map = new HashMap<>();
        // 放入需要加密的数据
        map.put(USER_NAME, userId);

        /*
            加密数据并返回
                setClaims() 放入要加密的数据
                setExpiration() 超期时间
                signWith() 指定 加密算法 和 对应的密匙
                compact() 压缩
         */
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析令牌
     *  成功：返回含有解析后数据的定制请求
     *  失败：抛出错误信息
     * @param request
     * @return 含有解析后数据的定制请求
     */
    public static HttpServletRequest validateTokenAndAddUserIdToHeader(HttpServletRequest request) {
        // 返回含有常量 HEADER_STRING 值的请求头，如果找不到，则返回 null
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {

            /*
                解析令牌
                    setSigningKey(): 判断传入的key是否是JWS数字签名的令牌
                    parseClaimsJws(): 解析令牌（JWS字符串）
                    getBody(): 返回解析后数据
             */
            try {
                Map<String, Object> body = Jwts.parser()
                        .setSigningKey(token)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
                System.out.println(body);
                return new CustomHttpServletRequest(request,body);
            } catch (Exception e) {
                logger.info(e.getMessage());
                throw new TokenValidationException(e.getMessage());
            }
        } else {
            throw new TokenValidationException("令牌为空！");
        }
    }

    /**
     * 根据解析好的令牌生成定制的 request
     */
    public static class CustomHttpServletRequest extends HttpServletRequestWrapper {

        private Map<String,String> claims;

        /**
         * 接收解析好的令牌数据，并传入内部私有的map
         * @param request
         * @param claims 解析好的令牌
         */
        public CustomHttpServletRequest(HttpServletRequest request, Map<String, ?> claims) {
            super(request);
            this.claims = new HashMap<>();
            claims.forEach((k, v) -> this.claims.put(k, String.valueOf(v)));
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (claims != null && claims.containsKey(name)) {
                return Collections.enumeration(Arrays.asList(claims.get(name)));
            }
            return super.getHeaders(name);
        }
    }

    /**
     * 令牌验证异常
     */
    static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String msg) {
            super(msg);
        }
    }

}
