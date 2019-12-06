package com.hrpms.service.system_setting_service.user_manager_service.impl;

import com.hrpms.dao.system_setting_dao.role_manager_dao.TbSystemRoleDao;
import com.hrpms.dao.system_setting_dao.user_manager_dao.SystemUserDao;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.system_setting_service.user_manager_service.SystemUserService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    private TbSystemRoleDao tbSystemRoleDao;

    @Override
    public Page<TbSystemUser> selectSystemUserByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbSystemUser where 1=1 ");
        hql2.append("from TbSystemUser where 1=1 ");
        if(map.get("username")!=null && !"".equals(map.get("username"))){
            map.put("username","%"+map.get("username")+"%");
            hql1.append("and username like :username ");
            hql2.append("and username like :username ");
        }
        if(map.get("phone")!=null && !"".equals(map.get("phone"))){
            map.put("phone","%"+map.get("phone")+"%");
            hql1.append("and phone like :phone ");
            hql2.append("and phone like :phone ");
        }
        if(map.get("status")!=null && !"".equals(map.get("status"))){
            hql1.append("and status = :status ");
            hql2.append("and status = :status ");
        }
        Long count = systemUserDao.selectSystemUserCount(hql1.toString(), map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbSystemUser> list = systemUserDao.selectSystemUserByDuo(hql2.toString(), map);
        page.setDataList(list);
        return page;
    }

    @Override
    public void addSystemUser(TbSystemUser tbSystemUser,String[] userRoleList) {
        //添加用户的同时添加用户角色
        for(int i=0;i<userRoleList.length;i++){
            tbSystemUser.getTbSystemRoles().add(tbSystemRoleDao.selectSystemRoleById(Integer.parseInt(userRoleList[i])));
        }
        systemUserDao.addSystemUser(tbSystemUser);
    }

    @Override
    public TbSystemUser selectSystemUserById(int id) {
        TbSystemUser tbSystemUser = systemUserDao.selectSystemUserById(id);
        //tbSystemUser.getTbUserRole();
        return tbSystemUser;
    }

    @Override
    public void updateSystemUserById(TbSystemUser tbSystemUser,String[] userRoleList) {
        //修改用户的同时修改用户角色
        for(int i=0;i<userRoleList.length;i++){
            tbSystemUser.getTbSystemRoles().add(tbSystemRoleDao.selectSystemRoleById(Integer.parseInt(userRoleList[i])));
        }
        systemUserDao.updateSystemUserById(tbSystemUser);
    }

    @Override
    public void deleteSystemUserById(int id) {
        systemUserDao.deleteSystemUserById(id);
    }

    @Override
    public TbSystemUser isOneUsername(String username) {
        return systemUserDao.isOneUsername(username);
    }

    @Override
    public List<TbSystemUser> selectAllUserName() {
        return systemUserDao.selectAllUserName();
    }

    @Override
    public TbSystemUser isOneUserPhone(String phone) {
        return systemUserDao.isOneUserPhone(phone);
    }

    @Override
    public TbSystemUser isOneUserEmail(String email) {
        return systemUserDao.isOneUserEmail(email);
    }
}
