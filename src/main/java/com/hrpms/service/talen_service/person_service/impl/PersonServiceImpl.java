package com.hrpms.service.talen_service.person_service.impl;

import com.hrpms.dao.talen_dao.person_dao.PersonDao;
import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
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
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + resumeFile.getOriginalFilename();
        File inFile = new File(request.getRealPath("resume"), fileName);
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(inFile));
        BufferedInputStream bin = new BufferedInputStream(resumeFile.getInputStream());
        IOUtils.copy(bin, bout);
        bin.close();
        bout.close();

        person.setResumeUrl(fileName);
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
    public List<TbPerson> getPersonsByPrice(Double price) {
        Map map = new HashMap(3);
        String hql = "from TbPerson where forPrice >= :minPrice and forPrice <= :maxPrice and status in :statu";
        map.put("minPrice", price - 1000);
        map.put("maxPrice", price + 1000);
        List list = new ArrayList();
        list.add(dataDictService.getDataDictValueByNameAndLabel("人才状态", "在职"));
        list.add(dataDictService.getDataDictValueByNameAndLabel("人才状态", "离职"));
        map.put("status", list);

        return personDao.getPersonsByPrice(hql, map);
    }
}
