package com.lucky.SwiftLinkMaster.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.SwiftLinkMaster.project.common.convention.exception.ServiceException;
import com.lucky.SwiftLinkMaster.project.dao.entity.ShortLinkDO;
import com.lucky.SwiftLinkMaster.project.dao.mapper.ShortLinkMapper;
import com.lucky.SwiftLinkMaster.project.dto.req.ShortLinkCreateReqDTO;
import com.lucky.SwiftLinkMaster.project.dto.req.ShortLinkPageReqDTO;
import com.lucky.SwiftLinkMaster.project.dto.resp.ShortLinkCreateRespDTO;
import com.lucky.SwiftLinkMaster.project.dto.resp.ShortLinkPageRespDTO;
import com.lucky.SwiftLinkMaster.project.service.ShortLinkService;
import com.lucky.SwiftLinkMaster.project.toolkit.HashUtil;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/25 22:51
 * @Description: 短链接服务实现层
 * @Position: com.lucky.SwiftLinkMaster.project.service.impl
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    private final RBloomFilter<String> shortUriRegisterCachePenetrationBloomFilter;

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setFullShortUrl(fullShortUrl);
        ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .fullShortUrl(fullShortUrl)
                .build();
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException ex) {
            // 存在一种情况，短链接入库成功，但是并没有添加到布隆过滤器中
            // （可能因为进程挂掉等等原因，由于没加事务，短链接入库不会回滚）。
            // 也就是说实际上入库了，但布隆过滤器显示短链不存在，
            // 此时再次插入该短链不就越过布隆过滤器，然后被唯一索引给拦截了。
            // 因为这种情况出现的概率极低，所以把唯一索引称为兜底策略。
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO!=null){
                log.warn("短链接:"+fullShortUrl+" 重复入库");
                throw new ServiceException("短链接生成重复");
            }
        }
        shortUriRegisterCachePenetrationBloomFilter.add(fullShortUrl);
        // builder() 方法通常返回一个该类的内部静态类（Builder 类）的实例。
        // 这个 Builder 类包含与目标类相同的字段，并提供了一系列的方法来设置这些字段的值。
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build(); // build() 方法是 Builder 类中的一个方法，它负责根据当前 Builder
        // 实例中设置的所有字段值，创建并返回一个新的目标类实例。
        // 用途：用于完成对象的构建过程，并返回一个完全配置好的对象实例。
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getDelFlag, 0)
                .eq(ShortLinkDO::getEnableStatus, 0)
                .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each->BeanUtil.toBean(each,ShortLinkPageRespDTO.class));
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam){
        int customGenerateCount = 0;
        String originUrl ;
        String shorUri;
        // 防止重复
        while (true){
            if ( customGenerateCount >10 ){
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            originUrl = requestParam.getOriginUrl();
            originUrl += System.currentTimeMillis();
            shorUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriRegisterCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + shorUri)){
                // 不存在就一定不存在，可能不存在被误判为存在
                break;
            }
            customGenerateCount++;
        }
        return shorUri;
    }
}
