package com.lucky.SwiftLinkMaster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.SwiftLinkMaster.admin.dao.entity.UserDO;
import com.lucky.SwiftLinkMaster.admin.dto.req.UserLoginReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.req.UserRegisterReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.req.UserUpdateReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserLoginRespDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserRespDTO;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/11 21:55
 * @Description: 用户服务层
 * @Position: com.lucky.SwiftLinkMaster.admin.service
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 用户名存在返回 False，与后续注册时的 if 搭配，不存在返回 True
     */
    boolean hasUsername(String username);

    /**
     * 注册用户
     * @param requestParam 注册用户请求参数
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名修改用户信息
     * @param requestParam
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     * @param requestParam
     * @return
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     * @param token
     * @return
     */
    Boolean checkLogin(String username,String token);

    /**
     * 用户退出登录
     * @param username
     * @param token
     */
    void logout(String username, String token);
}
