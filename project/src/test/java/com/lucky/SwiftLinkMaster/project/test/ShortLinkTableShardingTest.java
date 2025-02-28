package com.lucky.SwiftLinkMaster.project.test;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/27 19:42
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.project.test
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public class ShortLinkTableShardingTest {
    public static final String SQL = "CREATE TABLE `t_link_%d` (\n" +
            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `domain` varchar(128) DEFAULT NULL COMMENT '域名',\n" +
            "  `short_uri` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接，注意字符集选 utf8，排序规则选 utf8_bin，否则选 utf8mb4，mysql 不区分大小写',\n" +
            "  `full_short_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '完整短链接，注意字符集选 utf8，排序规则选 utf8_bin，否则选 utf8mb4，mysql 不区分大小写',\n" +
            "  `origin_url` varchar(1024) DEFAULT NULL COMMENT '原始链接',\n" +
            "  `click_num` int DEFAULT '0' COMMENT '点击量',\n" +
            "  `gid` varchar(32) DEFAULT NULL COMMENT '分组标识',\n" +
            "  `enable_status` tinyint(1) DEFAULT '0' COMMENT '启用标识 （0：启用）（1：未启用）',\n" +
            "  `created_type` tinyint(1) DEFAULT NULL COMMENT '创建类型 1：控制台 0：接口',\n" +
            "  `valid_date_type` tinyint(1) DEFAULT NULL COMMENT '有效期类型 0：永久有效 1：用户自定义',\n" +
            "  `valid_date` datetime DEFAULT NULL COMMENT '有效期',\n" +
            "  `describe` varchar(1024) DEFAULT NULL COMMENT '描述',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
            "  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',\n" +
            "  `favicon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '网站图标',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `idx_unique_full_short_url` (`full_short_url`) USING BTREE\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1895027695156023299 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL)+"%n",i);// %n：这个格式化符号会根据运行程序的操作系统自动插入相应的新行字符。例如，在Windows系统上它会被替换为\r\n，而在Unix/Linux/Mac系统上则被替换为\n。
            // 使用%n而不是直接使用\n的好处是确保了代码的跨平台兼容性。
        }
    }
}
