package cn.zhiyucs.core.system.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.zhiyucs.common.basic.service.impl.BaseServiceImpl;
import cn.zhiyucs.core.system.convert.SysLogLoginConvert;
import cn.zhiyucs.core.system.dao.SysLogLoginDao;
import cn.zhiyucs.core.system.entity.SysLogLoginEntity;
import cn.zhiyucs.core.system.query.SysLogLoginQuery;
import cn.zhiyucs.core.system.service.SysLogLoginService;
import cn.zhiyucs.core.system.vo.SysLogLoginVO;
import cn.zhiyucs.core.utils.HttpContextUtils;
import cn.zhiyucs.core.utils.AddressUtils;
import cn.zhiyucs.common.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录日志
 *
 * @author zhiyu1998
 */
@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, SysLogLoginEntity> implements SysLogLoginService {

    @Override
    public PageResult<SysLogLoginVO> page(SysLogLoginQuery query) {
        IPage<SysLogLoginEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysLogLoginConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<SysLogLoginEntity> getWrapper(SysLogLoginQuery query) {
        LambdaQueryWrapper<SysLogLoginEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(CharSequenceUtil.isNotBlank(query.getUsername()), SysLogLoginEntity::getUsername, query.getUsername());
        wrapper.like(CharSequenceUtil.isNotBlank(query.getAddress()), SysLogLoginEntity::getAddress, query.getAddress());
        wrapper.like(query.getStatus() != null, SysLogLoginEntity::getStatus, query.getStatus());
        wrapper.orderByDesc(SysLogLoginEntity::getId);

        return wrapper;
    }

    @Override
    public void save(String username, Integer status, Integer operation) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        assert request != null;
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        String ip = ServletUtil.getClientIP(request);
        String address = AddressUtils.getAddressByIP(ip);

        SysLogLoginEntity entity = new SysLogLoginEntity();
        entity.setUsername(username);
        entity.setStatus(status);
        entity.setOperation(operation);
        entity.setIp(ip);
        entity.setAddress(address);
        entity.setUserAgent(userAgent);

        baseMapper.insert(entity);
    }

}