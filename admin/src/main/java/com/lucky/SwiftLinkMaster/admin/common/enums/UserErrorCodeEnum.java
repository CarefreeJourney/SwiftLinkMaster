package com.lucky.SwiftLinkMaster.admin.common.enums;

import com.lucky.SwiftLinkMaster.admin.common.convention.errorcode.IErrorCode;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/13 21:17
 * @Description: 用户错误码枚举
 * @Position: com.lucky.SwiftLinkMaster.admin.common.enums
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public enum UserErrorCodeEnum implements IErrorCode {
    USER_NULL("B000200","用户记录不存在"),
    USER_EXIST("B000201","用户记录已存在");
    private final String code;
    private final String message;
    UserErrorCodeEnum(String code,String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public String code(){
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
