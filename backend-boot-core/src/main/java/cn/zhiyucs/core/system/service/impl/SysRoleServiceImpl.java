package cn.zhiyucs.core.system.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.zhiyucs.common.basic.service.impl.BaseServiceImpl;
import cn.zhiyucs.common.enums.system.DataScopeEnum;
import cn.zhiyucs.core.system.convert.SysRoleConvert;
import cn.zhiyucs.core.system.dao.SysRoleDao;
import cn.zhiyucs.core.system.entity.SysRoleEntity;
import cn.zhiyucs.core.system.query.SysRoleQuery;
import cn.zhiyucs.core.system.service.SysRoleDataScopeService;
import cn.zhiyucs.core.system.service.SysRoleMenuService;
import cn.zhiyucs.core.system.service.SysRoleService;
import cn.zhiyucs.core.system.service.SysUserRoleService;
import cn.zhiyucs.core.system.vo.SysRoleDataScopeVO;
import cn.zhiyucs.core.system.vo.SysRoleVO;
import cn.zhiyucs.common.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 角色
 *
 * @author zhiyu1998
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysRoleDataScopeService sysRoleDataScopeService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageResult<SysRoleVO> page(SysRoleQuery query) {
        IPage<SysRoleEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysRoleConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<SysRoleVO> getList(SysRoleQuery query) {
        List<SysRoleEntity> entityList = baseMapper.selectList(getWrapper(query));

        return SysRoleConvert.INSTANCE.convertList(entityList);
    }

    private Wrapper<SysRoleEntity> getWrapper(SysRoleQuery query) {
        LambdaQueryWrapper<SysRoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(CharSequenceUtil.isNotBlank(query.getName()), SysRoleEntity::getName, query.getName());

        // 数据权限
        dataScopeWrapper(wrapper);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleVO vo) {
        SysRoleEntity entity = SysRoleConvert.INSTANCE.convert(vo);

        // 保存角色
        entity.setDataScope(DataScopeEnum.SELF.getValue());
        baseMapper.insert(entity);

        // 保存角色菜单关系
        sysRoleMenuService.saveOrUpdate(entity.getId(), vo.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleVO vo) {
        SysRoleEntity entity = SysRoleConvert.INSTANCE.convert(vo);

        // 更新角色
        updateById(entity);

        // 更新角色菜单关系
        sysRoleMenuService.saveOrUpdate(entity.getId(), vo.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dataScope(SysRoleDataScopeVO vo) {
        SysRoleEntity entity = getById(vo.getId());
        entity.setDataScope(vo.getDataScope());
        // 更新角色
        updateById(entity);

        // 更新角色数据权限关系
        if (vo.getDataScope().equals(DataScopeEnum.CUSTOM.getValue())) {
            sysRoleDataScopeService.saveOrUpdate(entity.getId(), vo.getOrgIdList());
        } else {
            sysRoleDataScopeService.deleteByRoleIdList(Collections.singletonList(vo.getId()));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        // 删除角色
        removeByIds(idList);

        // 删除用户角色关系
        sysUserRoleService.deleteByRoleIdList(idList);

        // 删除角色菜单关系
        sysRoleMenuService.deleteByRoleIdList(idList);

        // 删除角色数据权限关系
        sysRoleDataScopeService.deleteByRoleIdList(idList);
    }

}