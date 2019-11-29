package com.hrpms.service.marketing_information_service.impl;

import com.hrpms.dao.customer_client_dao.CustomerDao;
import com.hrpms.dao.marketing_manager.TbSmsRecordDao;
import com.hrpms.dao.user_manager_dao.SystemUserDao;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSmsRecord;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.marketing_information_service.TbSmsRecoredService;
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
public class TbSmsRecoredServoceImpl implements TbSmsRecoredService {

    @Autowired
    private TbSmsRecordDao tbSmsRecordDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private SystemUserDao systemUserDao;


    @Override
    public Page<TbSmsRecord> selectSmsRecoredByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbSmsRecord where 1=1 ");
        hql2.append("from TbSmsRecord where 1=1 ");
        if(map.get("user_id")!=null && !"".equals(map.get("user_id"))){
            hql1.append("and user_id =:user_id ");
            hql2.append("and user_id =:user_id ");
        }
        if(map.get("telephone")!=null && !"".equals(map.get("telephone"))){
            hql1.append("and telephone =:telephone ");
            hql2.append("and telephone =:telephone ");
        }

        Long count = tbSmsRecordDao.selectSmsRecoredCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbSmsRecord> list =tbSmsRecordDao.selectSmsRecoredByDuo(hql2.toString(), map);
        List list1 = new ArrayList();
        for(TbSmsRecord li:list){
            TbSystemUser user = systemUserDao.selectSystemUserById(li.getUser_id());
            TbCustomer customer = customerDao.selectCustomerByPhone(li.getTelephone());
            Map map1 = new HashMap();
            map1.put("user",user);
            map1.put("sms",li);
            map1.put("customer",customer);
            list1.add(map1);
        }
        page.setDataList(list1);
        return page;
    }

    @Override
    public void addSmsRecored(TbSmsRecord tbSmsRecord) {
        tbSmsRecordDao.addSmsRecored(tbSmsRecord);

    }

    @Override
    public TbSmsRecord selectSmsRecoredById(int id) {
        return tbSmsRecordDao.selectSmsRecoredById(id);
    }

    @Override
    public void deleteSmsRecoredById(int id) {
        tbSmsRecordDao.deleteSmsRecoredById(id);
    }
}
