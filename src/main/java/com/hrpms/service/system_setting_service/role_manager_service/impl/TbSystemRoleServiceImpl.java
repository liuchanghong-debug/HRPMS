package com.hrpms.service.system_setting_service.role_manager_service.impl;

import com.hrpms.dao.system_setting_dao.menu_manager_dao.TbSystemFunctionDao;
import com.hrpms.dao.system_setting_dao.role_manager_dao.TbSystemRoleDao;
import com.hrpms.pojo.TbSystemRole;
import com.hrpms.service.system_setting_service.role_manager_service.TbSystemRoleService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TbSystemRoleServiceImpl implements TbSystemRoleService {

    @Autowired
    private TbSystemRoleDao tbSystemRoleDao;

    @Autowired
    private TbSystemFunctionDao tbSystemFunctionDao;


    @Override
    public Page<TbSystemRole> selectSystemRoleByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbSystemRole where 1=1 ");
        hql2.append("from TbSystemRole where 1=1 ");
        if(map.get("roleName")!=null && !"".equals(map.get("roleName"))){
            map.put("roleName","%"+map.get("roleName")+"%");
            hql1.append("and roleName like :roleName ");
            hql2.append("and roleName like :roleName ");
        }

        Long count = tbSystemRoleDao.selectSystemRloeCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbSystemRole> list =tbSystemRoleDao.selectSystemRoleByDuo(hql2.toString(), map);
        page.setDataList(list);
        return page;
    }

    @Override
    public void addSystemRole(TbSystemRole tbSystemRole,String menuIds) {
        if(menuIds!=null && !"".equals(menuIds)){
            String menuids[] = menuIds.split(",");
            for(int i=0;i<menuids.length;i++){
                tbSystemRole.getTbSystemFunctions().add(tbSystemFunctionDao.selectSystemFunctionById(Integer.parseInt(menuids[i])));
            }
        }
        tbSystemRoleDao.addSystemRole(tbSystemRole);
    }


    @Override
    public TbSystemRole selectSystemRoleById(int id) {
        TbSystemRole tbSystemRole = tbSystemRoleDao.selectSystemRoleById(id);
        return tbSystemRole;
    }

    @Override
    public void updateSystemRoleById(TbSystemRole tbSystemRole,String menuIds) {
        if(menuIds!=null && !"".equals(menuIds)){
            String menuids[] = menuIds.split(",");
            tbSystemRole.getTbSystemFunctions().clear();
            for(int i=0;i<menuids.length;i++){
                tbSystemRole.getTbSystemFunctions().add(tbSystemFunctionDao.selectSystemFunctionById(Integer.parseInt(menuids[i])));
            }
        }
        tbSystemRoleDao.updateSystemRoleById(tbSystemRole);
    }


    @Override
    public void deleteSystemRoleById(int id) {
        tbSystemRoleDao.deleteSystemRoleById(id);
    }

    @Override
    public List<TbSystemRole> selectAllRoleName() {
        return tbSystemRoleDao.selectAllRoleName();
    }

    @Override
    public boolean roleNameIsOne(String roleName) {
        TbSystemRole role = tbSystemRoleDao.roleNameIsOne(roleName);
        boolean bo = true;
        if(role!=null && role.getId()!=0){  //不唯一
            bo = false;
        }
        return bo;
    }
}
