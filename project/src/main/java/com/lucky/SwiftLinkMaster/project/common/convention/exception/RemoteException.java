package com.lucky.SwiftLinkMaster.project.common.convention.exception;

import com.lucky.SwiftLinkMaster.project.common.convention.errorcode.BaseErrorCode;
import com.lucky.SwiftLinkMaster.project.common.convention.errorcode.IErrorCode;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/13 22:10
 * @Description: 远程服务调用异常
 * @Position: com.lucky.SwiftLinkMaster.admin.common.convention.exception
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */



public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
