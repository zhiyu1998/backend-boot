package cn.zhiyucs.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据范围
 *
 * @author zhiyu1998
 */
@Data
@AllArgsConstructor
public class DataScope {

    private String sqlFilter;
}