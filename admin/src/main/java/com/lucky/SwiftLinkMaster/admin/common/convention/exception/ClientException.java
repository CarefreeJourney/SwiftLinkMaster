package com.lucky.SwiftLinkMaster.admin.common.convention.exception;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/13 22:09
 * @Description: 客户端异常
 * @Position: com.lucky.SwiftLinkMaster.admin.common.convention.exception
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */

import com.lucky.SwiftLinkMaster.admin.common.convention.errorcode.BaseErrorCode;
import com.lucky.SwiftLinkMaster.admin.common.convention.errorcode.IErrorCode;

public class ClientException extends AbstractException {

    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
