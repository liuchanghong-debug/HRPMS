package com.hrpms.service.talen_service.person_service.impl;

import com.hrpms.dao.talen_dao.person_dao.PersonDao;
import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.service.talen_service.person_service.PersonService;
import com.hrpms.utils.Page;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
        personDao.personList(hql.toString(), personOperation);

        //还原查询值
        personOperation.setNameQuery(oldNameQuery);
        personOperation.setIdCardQuery(oldIdCardQuery);
        personOperation.setJobInterentsionQuery(oldJobInterentsionQuery);
        personOperation.setForAddressQuery(oldForAddress);

        return page;
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
    public List<TbSystemDict> getDictsByName(String name) {
        return dataDictService.getDataDictByName(name);
    }
}
