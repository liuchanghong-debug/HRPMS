package com.hrpms.service.talen_service.person_service.impl;

import com.hrpms.dao.talen_dao.person_dao.PersonDao;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import com.hrpms.service.customer_client_service.CustomerService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.service.talen_service.person_service.PersonService;
import com.hrpms.utils.Download;
import com.hrpms.utils.Page;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.talen_service.person.impl > PersonService
 * @description TODO
 * @create 2019/11/25  17:40
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional

public class PersonServiceImpl implements PersonService {
    @Autowired
    private DataDictService dataDictService;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private CustomerService customerService;

    @Override
    public Page<TbPerson> personList(Integer currentPage, TbPersonOperation personOperation) {
        Integer pageSize = 3;
        StringBuffer hql = new StringBuffer("from TbPerson where 1=1 ");
        String oldNameQuery = "";
        String oldIdCardQuery = "";
        String oldJobInterentsionQuery = "";
        String oldForAddress = "";

        if(!"".equals(personOperation.getNameQuery()) && personOperation.getNameQuery() != null){
            oldNameQuery = personOperation.getNameQuery();
            personOperation.setNameQuery("%" + oldNameQuery + "%");
            hql.append(" and name like :nameQuery ");
        }
        if(!"".equals(personOperation.getIdCardQuery()) && personOperation.getIdCardQuery() != null){
            oldIdCardQuery = personOperation.getIdCardQuery();
            personOperation.setIdCardQuery("%" + oldIdCardQuery + "%");
            hql.append(" and idCard like :idCardQuery ");
        }
        if(!"".equals(personOperation.getJobInterentsionQuery()) && personOperation.getJobInterentsionQuery() != null){
            oldJobInterentsionQuery = personOperation.getJobInterentsionQuery();
            personOperation.setJobInterentsionQuery("%" + oldJobInterentsionQuery + "%");
            hql.append(" and jonInterentsion like :jobInterentsionQuery ");
        }
        if(!"".equals(personOperation.getForAddressQuery()) && personOperation.getForAddressQuery() != null){
            oldForAddress = personOperation.getForAddressQuery();
            personOperation.setForAddressQuery("%" + oldForAddress + "%");
            hql.append(" and forAddress like :forAddressQuery ");
        }
        Long count = personDao.personCount("select count(1) " + hql.toString(), personOperation);
        Page<TbPerson> page = new Page<TbPerson>(currentPage, pageSize, count);
        personOperation.setStartIndex(page.getStartIndex());
        personOperation.setPageSize(page.getPageSize());
        page.setDataList(personDao.personList(hql.toString(), personOperation));

        //还原查询值
        personOperation.setNameQuery(oldNameQuery);
        personOperation.setIdCardQuery(oldIdCardQuery);
        personOperation.setJobInterentsionQuery(oldJobInterentsionQuery);
        personOperation.setForAddressQuery(oldForAddress);

        return page;
    }

    @Override
    public TbPerson personDetailById(Integer id) {
        return personDao.personDetailById(id);
    }

    @Override
    public void personAdd(MultipartFile resumeFile, TbPerson person, Integer createBy, HttpServletRequest request) throws IOException {
        if(!resumeFile.isEmpty()){
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + resumeFile.getOriginalFilename();
            File inFile = new File(request.getRealPath("resume"), fileName);
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(inFile));
            BufferedInputStream bin = new BufferedInputStream(resumeFile.getInputStream());
            IOUtils.copy(bin, bout);
            bin.close();
            bout.close();

            person.setResumeUrl(fileName);
        }
        person.setCreateBy(createBy);
        person.setCreateTime(new Timestamp(System.currentTimeMillis()));
        person.setId(1);

        personDao.personAdd(person);
    }

    @Override
    public void resumeUrlPreview(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Download.preview(fileName, request, response);
    }

    @Override
    public List<TbSystemDict> getDictsByName(String name) {
        return dataDictService.getDataDictByName(name);
    }

    @Override
    public void personUpdate(TbPerson person, MultipartFile file, Integer updateBy, HttpServletRequest request, HttpServletResponse response) throws IOException {
        TbPerson person1 = personDao.personDetailById(person.getId());
        person1.setUpdateBy(updateBy);
        person1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        person1.setName(person.getName());
        person1.setIdCard(person.getIdCard());
        person1.setJobIntentsion(person.getJobIntentsion());
        person1.setJobType(person.getJobType());
        person1.setForPrice(person.getForPrice());
        person1.setForAddress(person.getForAddress());
        person1.setWorked(person.getWorked());
        person1.setStatus(person.getStatus());
        person1.setPersonInfo(person.getPersonInfo());
        person1.setRemark(person.getRemark());
        if(!file.isEmpty()){
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            File inFile = new File(request.getRealPath("resume"), fileName);
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(inFile));
            BufferedInputStream bin = new BufferedInputStream(file.getInputStream());
            IOUtils.copy(bin, bout);
            bin.close();
            bout.close();

            person1.setResumeUrl(fileName);
        }
        personDao.personUpdate(person1);
    }

    @Override
    public void personDelete(Integer id, Integer updateBy) {
        //获取删除状态
        String personDeleteStatus = dataDictService.getDataDictValueByNameAndLabel("人才状态", "删除");
        TbPerson person = personDao.personDetailById(id);
        person.setStatus(personDeleteStatus);
        person.setUpdateBy(updateBy);
        person.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        personDao.personUpdate(person);
    }

    @Override
    public List<Object[]> getAllIdAndName() {
        //获取正常状态值
        List list = new ArrayList();
        list.add(dataDictService.getDataDictValueByNameAndLabel("人才状态", "在职"));
        list.add(dataDictService.getDataDictValueByNameAndLabel("人才状态", "离职"));
        return personDao.getAllIdAndName(list);
    }

    @Override
    public List<TbPerson> getPersonsByPrice(StringBuffer hql, Map map) {
        hql.append(" and status in :status");
        List list = new ArrayList();
        list.add(dataDictService.getDataDictValueByNameAndLabel("人才状态", "在职"));
        list.add(dataDictService.getDataDictValueByNameAndLabel("人才状态", "离职"));
        map.put("list", list);
        return personDao.getPersonsByPrice(hql.toString(), map);
    }

    @Override
    public boolean personIdCardIsOnly(String idCard) {
        String hql = "from TbPerson where idCard = ?";
        Object object = personDao.personIdCardIsOnly(hql, idCard);
        if(object == null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean personIdCardIsOnlyUpdate(Integer id, String idCard) {
        String hql = "from TbPerson where id != ? and idCard = ?";
        Object object = personDao.personIdCardIsOnlyUpdate(hql, id, idCard);
        if(object == null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Map> normalCustomerOfIdAndName() {
        //得到客户表中正常的个人客户信息， 并且人才表中已经有的不添加
        //先获取人才表中正常的idCard  获取正常状态信息
        List<String> normalPersonStatusList = new ArrayList<>();
        String normalStatus1 = dataDictService.getDataDictValueByNameAndLabel("人才状态", "在职");
        String normalStatus2 = dataDictService.getDataDictValueByNameAndLabel("人才状态", "离职");
        normalPersonStatusList.add(normalStatus1);
        normalPersonStatusList.add(normalStatus2);
        //查询语句
        String hql1 = "select idCard from TbPerson where status in :status";
        List<String> normalIdCardList = personDao.normalPersonOfIdCard(hql1, normalPersonStatusList);
        //查询个人客户表中正常的数据，不包含人才表中在职的客户信息
        //获取状态
        List<String> normalCustomerStatusList = new ArrayList<>();
        String normalCustomerStatus = dataDictService.getDataDictValueByNameAndLabel("客户状态", "正常");
        normalCustomerStatusList.add(normalCustomerStatus);
        //查询语句
        String hql2 = "from TbCustomer where idCard not in :idCards and status in :status";
        List<TbCustomer> tbCustomers = customerService.normalCustomerOfStatus(hql2, normalIdCardList, normalCustomerStatusList);
        List<Map> list = new ArrayList<>();
        for (TbCustomer tbCustomer : tbCustomers){
            Map <String, String> map = new HashMap<>();
            map.put("id", String.valueOf(tbCustomer.getId()));
            map.put("name", tbCustomer.getName());
            list.add(map);
        }
        return list;
    }

    @Override
    public String customerIdCardById(Integer id) {
        TbCustomer customer = customerService.customerById(id);
        return customer.getIdCard();
    }

    @Override
    public TbPerson PersonByIdCard(String idCard) {
        return personDao.personByIdCard(idCard);
    }
}
