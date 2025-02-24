package com.lucky.SwiftLinkMaster.admin.common.biz.user;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 20:40
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.admin.common.biz.user
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */

import com.alibaba.fastjson2.JSON;
import com.lucky.SwiftLinkMaster.admin.common.constant.RedisCacheConstant;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.Objects;

/**
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {
    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if (!Objects.equals(requestURI,"/api/SwiftLinkMaster/v1/login")) {
            String username = httpServletRequest.getHeader("username");
            String token = httpServletRequest.getHeader("token");
            Object userInfoJsonStr = stringRedisTemplate.opsForHash().get(RedisCacheConstant.USER_LOGIN + username, token);
            if (userInfoJsonStr!=null){
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
}
