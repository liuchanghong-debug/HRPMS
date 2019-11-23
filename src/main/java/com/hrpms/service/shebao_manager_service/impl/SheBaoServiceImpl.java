package com.hrpms.service.shebao_manager_service.impl;

import com.hrpms.dao.shebao_manager_dao.SheBaoDao;
import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.customer_client_service.CustomerService;
import com.hrpms.service.shebao_manager_service.SheBaoService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.shebao_manager_service.impl > SheBaoServiceImpl
 * @description TODO
 * @create 2019/11/23  16:32
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class SheBaoServiceImpl implements SheBaoService {
    @Autowired
    private DataDictService dataDictService;
    @Autowired
    private SheBaoDao sheBaoDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyClientService companyClientService;


    @Override
    public Page<TbSocialInsurance> socialInsuranceQueryByOperation(Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation) {
        String shebaoStatus = dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常");
        StringBuffer hql = new StringBuffer(" from TbSocialInsurance where status = " + shebaoStatus);
        String oldNameQuery = "";
        String oldIdCardQuery = "";
        String oldSbCardQuery = "";
        if(!"".equals(socialInsuranceOperation.getNameQuery()) && socialInsuranceOperation.getNameQuery() != null){
            //从客户表中获取值
            oldNameQuery = socialInsuranceOperation.getNameQuery();
            socialInsuranceOperation.setNameQuery("%" + oldNameQuery + "%");
            hql.append(" and name like :nameQuery ");

        }
        if(!"".equals(socialInsuranceOperation.getIdCardQuery()) && socialInsuranceOperation.getIdCardQuery() != null){
            oldIdCardQuery = socialInsuranceOperation.getIdCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldIdCardQuery + "%");
            hql.append(" and idCard like :idCardQuery");
        }
        if(!"".equals(socialInsuranceOperation.getSbCardQuery()) && socialInsuranceOperation.getSbCardQuery() != null){
            oldSbCardQuery = socialInsuranceOperation.getSbCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldSbCardQuery + "%");
            hql.append(" and idCard like :sbCardQuery");
        }
        Long count = sheBaoDao.socialInsuranceQueryCountByOperation("select count(1) " + hql.toString(), socialInsuranceOperation);
        Page<TbSocialInsurance> page = new Page(currentPage, 3, count);
        List<TbSocialInsurance> socialInsuranceList = sheBaoDao.socialInsuranceQueryByOperation(hql.toString(), socialInsuranceOperation);
        page.setDataList(socialInsuranceList);

        socialInsuranceOperation.setNameQuery(oldNameQuery);
        socialInsuranceOperation.setIdCardQuery(oldIdCardQuery);
        socialInsuranceOperation.setSbCardQuery(oldSbCardQuery);

        return page;
    }

    @Override
    public TbSocialInsurance getSheBaoByIdCard(Integer id) {
        return sheBaoDao.getSheBaoByIdCard(id);
    }

    @Override
    public TbCustomer getCustomerByIdCard(String idCard) {
        return customerService.getCustomerByIdCard(idCard);
    }

    @Override
    public List<Object[]> getCompanys() {
        return companyClientService.getAllCompanyOfIdAndName();
    }

    @Override
    public void shebaoAdd(TbSocialInsurance socialInsurance, Integer createBy) {
        socialInsurance.setCreateBy(createBy);
        socialInsurance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        socialInsurance.setId(1);
        socialInsurance.setStatus(dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常"));

        sheBaoDao.shebaoAdd(socialInsurance);
    }

    @Override
    public List<Map> getNotInSociaOfCustomer() {
        //得到字典中 社保状态  可用的value
        String dictValue = dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常");
        //得到已存在的社保信息中的客户idCard 值
        List<Object> normalSociaOfCustomer = sheBaoDao.getUserableInSheBaoOfIdCard("select idCard from TbSocialInsurance where status = ?", dictValue);
        //封装为list集合
        List<String> dataList = new ArrayList<>();
        if(normalSociaOfCustomer != null){
            for (Object objects : normalSociaOfCustomer){
                dataList.add(String.valueOf(objects));
            }
        }
        //在customer中查询不属于此list集合中的对象信息
        List<Object[]> dataOfNotInList = customerService.getDataOfNotInList(dataList);
        List<Map> list = new ArrayList<>();
        for (Object[] objects : dataOfNotInList){
            Map map = new HashMap(2);
            map.put("id", objects[0]);
            map.put("name", objects[1]);
            list.add(map);
        }

        return list;
    }

    @Override
    public Map getDetailOfCustomerById(Integer id) {
        TbCustomer customer = customerService.customerById(id);
        Map map = new HashMap(12);
        map.put("idCard", customer.getIdCard());
        map.put("sex", customer.getSex());
        map.put("name", customer.getName());
        map.put("birthday", new SimpleDateFormat("yyyy-MM-dd").format(customer.getBirthday()));
        map.put("phone", customer.getPhone());
        map.put("email", customer.getEmail());
        map.put("address", customer.getAddress());
        map.put("zipCode", customer.getZipCode());
        map.put("school", customer.getSchool());
        map.put("specialty", customer.getSpecialty());
        map.put("graduation", customer.getGraduation());
        map.put("companyId", customer.getCompanyId());
        //通过公司id 查询公司信息
        TbCompany companyById = companyClientService.getCompanyById(customer.getCompanyId());
        map.put("companyName", companyById.getName());

        return map;
    }

    @Override
    public List<TbSystemDict> getDictBySheBao() {
        return dataDictService.getDataDictByName("社保状态");
    }
}
