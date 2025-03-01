package com.lucky.SwiftLinkMaster.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.SwiftLinkMaster.admin.common.convention.exception.ClientException;
import com.lucky.SwiftLinkMaster.admin.common.enums.UserErrorCodeEnum;
import com.lucky.SwiftLinkMaster.admin.dao.entity.UserDO;
import com.lucky.SwiftLinkMaster.admin.dao.mapper.UserMapper;
import com.lucky.SwiftLinkMaster.admin.dto.req.UserLoginReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.req.UserRegisterReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.req.UserUpdateReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserLoginRespDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserRespDTO;
import com.lucky.SwiftLinkMaster.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.lucky.SwiftLinkMaster.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.lucky.SwiftLinkMaster.admin.common.constant.RedisCacheConstant.USER_LOGIN;

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
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        // Wrappers 类的静态方法 lambdaQuery() 创造 LambdaQueryWrapper VS 手动 newXXXWarper() ，感觉两者其实差不多
//        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
//        UserDO userDO = baseMapper.selectOne(queryWrapper);
        UserDO userDO = lambdaQuery().eq(UserDO::getUsername, username).one();// 若是将所有结果查找出来，则使用 list()
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
            } catch (DuplicateKeyException ex){
                throw new ClientException(UserErrorCodeEnum.USER_EXIST);
            } finally {
                lock.unlock();
            }
        }else {
            // 否则抛出用户记录新增失败的信息
            throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        // TODO 验证当前用户名是否为登录用户，防止横向越权（依赖网关）
        // 通过用户名更新 update 字段 from 表(实体) where 条件
        // 写法1：将构建条件和执行更新分离，使得代码逻辑更加清晰。
//        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
//                .eq(UserDO::getUsername, requestParam.getUsername());
//        baseMapper.update(BeanUtil.toBean(requestParam,UserDO.class),updateWrapper);
        // 写法2：将所有操作合并到一个链式调用中，使得代码更紧凑，但可读性低。如果要更新整个对象，需要在 update()
        // 方法中明确指定对象；如果只更新部分字段，则需要使用 set 方法来指定每个字段的新值。
        lambdaUpdate().eq(UserDO::getUsername,requestParam.getUsername())
                .update(BeanUtil.toBean(requestParam,UserDO.class));
    }

//    @Override
//    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
//        // 1. 验证用户名和密码是否存在数据库，注意得包括没有注销的条件
//        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
//                .eq(UserDO::getUsername, requestParam.getUsername())
//                .eq(UserDO::getPassword, requestParam.getPassword())
//                .eq(UserDO::getDelFlag, 0);
//        UserDO userDO = baseMapper.selectOne(queryWrapper);
//        if (userDO==null){
//            throw new ClientException("用户不存在或者用户密码错误");
//        }
//        // 2. 将结果存入 redis 中，有效期设置为 30 minutes，以 UUID 为键，用户信息为值
//        String uuid = UUID.randomUUID().toString().replaceAll("-","");
//        stringRedisTemplate.opsForValue().set(uuid, JSON.toJSONString(userDO),30L, TimeUnit.MINUTES);
//        return new UserLoginRespDTO(uuid);
//    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        // 原本的 login 方法有坑，如果通过同一个用户名重复登录，则会生成新的 UUID 作为 redis 中的 key，
        // 而对应的值却是相同的用户信息，若恶意请求，则会将所有请求打到数据库中（这个没办法，有新登录得将旧登录挤掉），
        // 同时在 redis 中也会有大量的
        // 缓存被浪费，我们需要让 redis 的 key 对于同一个用户信息是一样的，则使用 hash
        /**
         * Hash
         * Key：login_用户名
         * Value：
         *  Key：token 标识
         *  Val：JSON 字符串（用户信息）
         */

        // 1. 验证用户名和密码是否存在数据库，注意得包括没有注销的条件。
        // 若恶意请求，则会将所有请求打到数据库中（这个没办法，有新登录得将旧登录挤掉）
        // 所以一般在登录时，需要输入验证码等。
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO==null){
            throw new ClientException("用户不存在或者用户密码错误");
        } else if (stringRedisTemplate.hasKey(USER_LOGIN+requestParam.getUsername())) {
            // 有人已经登录了，将上一次登录挤掉，并重新设置到新的到缓存中，因此此处不 return
            stringRedisTemplate.expire(USER_LOGIN+requestParam.getUsername(),0,TimeUnit.MINUTES);
        }

        // 2. 将结果存入 redis 中，有效期设置为 30 minutes，以 UUID 为键，用户信息为值
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // 也可以将用户信息加密成 jwt，在前后端之间进行传输，更加安全。
        stringRedisTemplate.opsForHash().put(USER_LOGIN+requestParam.getUsername(), uuid, JSON.toJSONString(userDO));
        // 非原子性操作，可能存在设置好 key 和 value 后，卡住了，其他同个用户登录的线程进来后，挤掉，就将
        // 这个的设置在 redis 中删除掉，然后它卡住了，上个线程继续，想重置有效期，结果，找不到 key，
        // 抛出异常，然后被全局异常处理器处理，返回给前端系统出错的信息。解决方法：使用 lua 脚本，但是，感觉没有必要，
        // 这样会变成单线程，所有线程到这里得等待，成本高。。。
        stringRedisTemplate.expire(USER_LOGIN+requestParam.getUsername(),30L, TimeUnit.MINUTES);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username,String token) {
        return stringRedisTemplate.opsForHash().get(USER_LOGIN+username,token)!=null;
    }

    @Override
    public void logout(String username, String token) {
        if (checkLogin(username,token)){ // 必须先判断，否则直接根据用户名删掉 redis 中的用户信息，
            // 可能会有恶意用户调用删除某个其他用户的信息。判断可防止横向越权
            stringRedisTemplate.delete(USER_LOGIN+username);
            return;
        }
        throw new ClientException("用户Token有误或者用户未登录");
    }
}
