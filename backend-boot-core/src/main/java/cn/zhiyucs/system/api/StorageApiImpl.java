package cn.zhiyucs.system.api;

import cn.zhiyucs.api.system.StorageApi;
import cn.zhiyucs.storage.service.StorageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * 存储服务Api
 *
 * @author zhiyu1998
 */
@Component
public class StorageApiImpl implements StorageApi {

    @Resource
    private StorageService storageService;

    @Override
    public String getNewFileName(String fileName) {
        return storageService.getNewFileName(fileName);
    }

    @Override
    public String getPath() {
        return storageService.getPath();
    }

    @Override
    public String getPath(String fileName) {
        return storageService.getPath(fileName);
    }

    @Override
    public String upload(byte[] data, String path) {
        return storageService.upload(data, path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        return storageService.upload(inputStream, path);
    }
}
