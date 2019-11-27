package com.hrpms.service.talen_service.laowu_service.impl;

import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.service.talen_service.laowu_service.LaoWuService;
import com.hrpms.service.talen_service.person_service.PersonService;
import com.hrpms.service.talen_service.zhaopin_service.ZhaoPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.talen_service.laowu.impl > LaoWuServiceImpl
 * @description TODO
 * @create 2019/11/25  17:39
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class LaoWuServiceImpl implements LaoWuService {
    @Autowired
    private CompanyClientService companyService;
    @Autowired
    private ZhaoPinService zhaoPinService;
    @Autowired
    private PersonService personService;
    @Autowired
    private DataDictService dataDictService;



    @Override
    public List<TbSystemDict> getDictsByName(String name) {
        return dataDictService.getDataDictByName(name);
    }

    @Override
    public List<Object[]> getAllPersonIdAndName() {
        return personService.getAllIdAndName();
    }

    @Override
    public Map getPersonAndCompanyById(Integer id) {
        Map map = new HashMap(2);
        TbPerson person = personService.personDetailById(id);
        List<TbCompany> companys = getNeedJobsByJobType(person.getForPrice());
        map.put("person", person);
        map.put("companys", companys);
        return map;
    }

    @Override
    public List<Integer> getAllCompanyIdAndName() {
        return zhaoPinService.getNormalZhaoPinCompanyId();
    }

    @Override
    public List<Object[]> getAllCompanys() {
        return zhaoPinService.getAllCompanyOfIdAndName();
    }

    @Override
    public List<TbCompany> getNeedJobsByJobType(Double price) {
        List<Integer> companyId = zhaoPinService.getNeedJobsByJobType(price);
        List<TbCompany> list = new ArrayList<>();
        for (Integer id : companyId) {
            list.add(companyService.getCompanyById(id));
        }
        return list;
    }

    @Override
    public List<TbNeedJob> getAllJobByCompanyId(Integer id) {
        return zhaoPinService.getAllJobByCompanyId(id);
    }

    @Override
    public TbNeedJob getDetailById(Integer id) {
        return zhaoPinService.selectNeedJobById(id);
    }
}
