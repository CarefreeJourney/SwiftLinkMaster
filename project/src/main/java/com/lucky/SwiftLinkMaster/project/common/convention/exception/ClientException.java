package com.lucky.SwiftLinkMaster.project.common.convention.exception;

import com.lucky.SwiftLinkMaster.project.common.convention.errorcode.BaseErrorCode;
import com.lucky.SwiftLinkMaster.project.common.convention.errorcode.IErrorCode;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/13 22:09
 * @Description: 客户端异常
 * @Position: com.lucky.SwiftLinkMaster.admin.common.convention.exception
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */


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
