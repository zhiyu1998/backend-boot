package cn.zhiyucs.storage.properties;

import lombok.Data;

/**
 * 阿里云存储配置项
 *
 * @author zhiyu1998
 */
@Data
public class AliyunStorageProperties {
    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
