package com.lucky.SwiftLinkMaster.project.common.convention.errorcode;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/13 21:15
 * @Description: 平台错误码
 * @Position: com.lucky.SwiftLinkMaster.admin.common.convention.errorcode
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */

public interface IErrorCode {

    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}
