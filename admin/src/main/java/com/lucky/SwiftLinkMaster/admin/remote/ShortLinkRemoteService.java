package com.lucky.SwiftLinkMaster.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucky.SwiftLinkMaster.admin.common.convention.result.Result;
import com.lucky.SwiftLinkMaster.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.lucky.SwiftLinkMaster.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.lucky.SwiftLinkMaster.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.lucky.SwiftLinkMaster.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/28 16:13
 * @Description: 后管中远程调用短链接服务接口
 * @Position: com.lucky.SwiftLinkMaster.admin.remote.dto
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public interface ShortLinkRemoteService {

    /**
     * 创建短链接
     * @param requestParam
     * @return
     */
    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        String originalValidDate = sdf1.format(requestParam.getValidDate());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedValidDate = sdf2.format(requestParam.getValidDate());
        String requestString = JSON.toJSONString(requestParam);
        requestString = requestString.replace(originalValidDate, formattedValidDate);
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/SwiftLinkMaster/short-link/v1/create",requestString);
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {});
    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("gid",requestParam.getGid());
        requestMap.put("current",requestParam.getCurrent());
        requestMap.put("size",requestParam.getSize());
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/SwiftLinkMaster/short-link/v1/page", requestMap);
        return JSON.parseObject(resultPageStr,new TypeReference<>(){});

    }
}
