package com.lucky.SwiftLinkMaster.admin.common.biz.user;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 20:40
 * @Description: 过滤器，验证用户信息；也可以用拦截器，拦截器还有放行的部分
 * @Position: com.lucky.SwiftLinkMaster.admin.common.biz.user
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.lucky.SwiftLinkMaster.admin.common.constant.RedisCacheConstant;
import com.lucky.SwiftLinkMaster.admin.common.convention.exception.ClientException;
import com.lucky.SwiftLinkMaster.admin.common.convention.result.Results;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import static com.lucky.SwiftLinkMaster.admin.common.enums.UserErrorCodeEnum.USER_TOKEN_FAIL;

/**
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {
    private final StringRedisTemplate stringRedisTemplate;
    private static final List<String> IGNORE_URI = Lists.newArrayList(
            "/api/SwiftLinkMaster/admin/v1/login",
            "/api/SwiftLinkMaster/admin/v1/user/has-username"
//            "/api/SwiftLinkMaster/admin/v1/user"
    );
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if (!IGNORE_URI.contains(requestURI)) {
            if (!(Objects.equals(requestURI,"/api/SwiftLinkMaster/admin/v1/user") && Objects.equals(httpServletRequest.getMethod(),"POST"))){
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if (!StrUtil.isAllNotBlank(username,token)) {
                    returnJson((HttpServletResponse) servletResponse,JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));// 与前端约定，跳转到登录页，让用户去登录
                    return;
                }
                Object userInfoJsonStr;
                try{
                    userInfoJsonStr = stringRedisTemplate.opsForHash().get(RedisCacheConstant.USER_LOGIN + username, token);
                    if (userInfoJsonStr == null){
                        returnJson((HttpServletResponse) servletResponse,JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                        return;
                    }
                }catch (Exception ex){
                    returnJson((HttpServletResponse) servletResponse,JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
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

    private void returnJson(HttpServletResponse response,String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try{
            writer = response.getWriter();
            writer.print(json);
        }catch (IOException e){

        }finally {
            if (writer!=null){
                writer.close();
            }
        }
    }
}
