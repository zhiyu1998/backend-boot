package cn.zhiyucs.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取真实地址
 *
 * @author zhiyu1998
 */
@Slf4j
public class AddressUtils {

    private AddressUtils(){}

    // 实时查询
    public static final String ADDRESS_URL = "https://whois.pconline.com.cn/ipJson.jsp";
    public static final String UNKNOWN = "未知";

    public static String getAddressByIP(String ip) {
        // 内网
        if (NetUtil.isInnerIP(ip)) {
            return "内网IP";
        }

        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("ip", ip);
            paramMap.put("json", true);
            String response = HttpUtil.get(ADDRESS_URL, paramMap);
            if (StringUtils.isBlank(response)) {
                log.error("根据IP获取地址异常 {}", ip);
                return UNKNOWN;
            }
            Address address = JsonUtils.parseObject(response, Address.class);
            return String.format("%s %s", address.getPro(), address.getCity());
        } catch (Exception e) {
            log.error("根据IP获取地址异常 {}", ip);
        }

        return UNKNOWN;
    }
}
