package com.lucky.SwiftLinkMaster.admin.common.convention.exception;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/13 22:10
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.admin.common.convention.exception
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */

import com.lucky.SwiftLinkMaster.admin.common.convention.errorcode.BaseErrorCode;
import com.lucky.SwiftLinkMaster.admin.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * 服务端异常
 */
public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}

