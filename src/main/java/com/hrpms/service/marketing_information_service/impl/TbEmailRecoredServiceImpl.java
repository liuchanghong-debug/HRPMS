package com.hrpms.service.marketing_information_service.impl;

import com.hrpms.dao.customer_client_dao.CustomerDao;
import com.hrpms.dao.marketing_manager.TbEmailRecordDao;
import com.hrpms.dao.user_manager_dao.SystemUserDao;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbEmailRecord;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.marketing_information_service.TbEmailRecoredService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class TbEmailRecoredServiceImpl implements TbEmailRecoredService {
    @Autowired
    private TbEmailRecordDao tbEmailRecordDao;

    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Page<TbEmailRecord> selectEmailRecoredByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbEmailRecord where 1=1 ");
        hql2.append("from TbEmailRecord where 1=1 ");
        if(map.get("user_id")!=null && !"".equals(map.get("user_id"))){
            hql1.append("and user_id =:user_id ");
            hql2.append("and user_id =:user_id ");
        }
        if(map.get("to_addr")!=null && !"".equals(map.get("to_addr"))){
            hql1.append("and to_addr =:to_addr ");
            hql2.append("and to_addr =:to_addr ");
        }

        Long count = tbEmailRecordDao.selectEmailRecoredCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbEmailRecord> list =tbEmailRecordDao.selectEmailRecoredByDuo(hql2.toString(), map);
        List list1 = new ArrayList();
        for(TbEmailRecord li:list){
            TbSystemUser user = systemUserDao.selectSystemUserById(li.getUser_id());
            TbCustomer customer = customerDao.selectCustomerByEmail(li.getTo_addr());
            Map map1 = new HashMap();
            map1.put("user",user);
            map1.put("email",li);
            map1.put("customer",customer);
            list1.add(map1);
        }
        page.setDataList(list1);
        return page;
    }

    @Override
    public void addEmailRecored(TbEmailRecord tbEmailRecord) {
        tbEmailRecordDao.addEmailRecored(tbEmailRecord);

    }

    @Override
    public TbEmailRecord selectEmailRecoredById(int id) {
        return tbEmailRecordDao.selectEmailRecoredById(id);
    }

    @Override
    public void deleteEmailRecored(int id) {
        tbEmailRecordDao.deleteEmailRecored(id);
    }
}
