package com.lucky.SwiftLinkMaster.admin.common.serialize;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/14 21:04
 * @Description: 手机号脱敏反序列化
 * @Position: com.lucky.SwiftLinkMaster.admin.common.serialize
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
public class PhoneDesensitizationSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String phone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String phoneDesensitization = DesensitizedUtil.mobilePhone(phone);
        jsonGenerator.writeString(phoneDesensitization);
    }
}
