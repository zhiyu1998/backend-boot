package cn.zhiyucs.core.storage.properties;

import lombok.Data;

/**
 * 七牛云存储配置项
 *
 * @author zhiyu1998
 */
@Data
public class QiniuStorageProperties {
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
