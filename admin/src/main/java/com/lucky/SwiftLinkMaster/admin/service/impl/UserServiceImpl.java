package com.lucky.SwiftLinkMaster.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.SwiftLinkMaster.admin.common.convention.exception.ClientException;
import com.lucky.SwiftLinkMaster.admin.common.enums.UserErrorCodeEnum;
import com.lucky.SwiftLinkMaster.admin.dao.entity.UserDO;
import com.lucky.SwiftLinkMaster.admin.dao.mapper.UserMapper;
import com.lucky.SwiftLinkMaster.admin.dto.req.UserRegisterReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserRespDTO;
import com.lucky.SwiftLinkMaster.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.lucky.SwiftLinkMaster.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/11 21:56
 * @Description: 用户接口实现层
 * @Position: com.lucky.SwiftLinkMaster.admin.service.impl
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO,result);
        return result;
    }

    @Override
    public boolean hasUsername(String username) {
//        // 直接查 DB
//        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
//        UserDO userDO = baseMapper.selectOne(queryWrapper);
//        return userDO!=null;
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        // 1. 判断用户名是否存在
        if (!hasUsername(requestParam.getUsername())){
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY+requestParam.getUsername());
        if (lock.tryLock()){ // 尝试获取锁，成功则进行数据库和缓存的插入
            try {
                // 2. 用户名不存在，则将新用户存入数据库中
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                // 3. 判断插入是否成功
                if (inserted<1){
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
            }finally {
                lock.unlock();
            }
        }else {
            // 否则抛出用户记录新增失败的信息
            throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
        }
    }
}
