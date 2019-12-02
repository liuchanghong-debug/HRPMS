package com.hrpms.service.talen_service.laowu_service.impl;

import com.hrpms.dao.talen_dao.laowu_dao.LaoWuDao;
import com.hrpms.pojo.*;
import com.hrpms.pojo.operaton_select.TbPersonJobOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.service.talen_service.laowu_service.LaoWuService;
import com.hrpms.service.talen_service.person_service.PersonService;
import com.hrpms.service.talen_service.zhaopin_service.ZhaoPinService;
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
    @Autowired
    private LaoWuDao laoWuDao;


    @Override
    public Page<TbPersonJob> getPersonJobByOperation(Integer currentPage, TbPersonJobOperation personJobOperation) {
        StringBuffer hql = new StringBuffer("from TbPersonJob where 1=1 ");
        String oldNameQuery = "";
        String oldIdCardQuery = "";
        if(!"".equals(personJobOperation.getNameQuery()) && personJobOperation.getNameQuery() != null){
            oldNameQuery = personJobOperation.getNameQuery();
            personJobOperation.setNameQuery("%" + oldNameQuery + "%");
            hql.append(" and name like :nameQuery");
        }
        if(!"".equals(personJobOperation.getIdCardQuery()) && personJobOperation.getIdCardQuery() != null){
            oldIdCardQuery = personJobOperation.getIdCardQuery();
            personJobOperation.setIdCardQuery("%" + oldIdCardQuery + "%");
            hql.append(" and idCard like :idCardQuery");
        }
        if(!"".equals(personJobOperation.getCompanyIdQuery()) && personJobOperation.getCompanyIdQuery() != null){
            hql.append(" and companyId = :companyIdQuery");
        }
        Long count = laoWuDao.personJobCount("select count(1) " + hql.toString(), personJobOperation);
        Page<TbPersonJob> page = new Page<>(currentPage, 3, count);
        personJobOperation.setStartIndex(page.getStartIndex());
        personJobOperation.setPageSize(page.getPageSize());
        List<TbPersonJob> tbPersonJobs = laoWuDao.personList(hql.toString(), personJobOperation);
        page.setDataList(tbPersonJobs);

        personJobOperation.setNameQuery(oldNameQuery);
        personJobOperation.setIdCardQuery(oldIdCardQuery);

        return page;
    }

    @Override
    public TbPersonJob laowuDetailById(Integer id) {
        return laoWuDao.getPersonJobById(id);
    }

    @Override
    public void contractUrlPreview(String personResumeUrl, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Download.contractUrlPreview(personResumeUrl, request, response);
    }

    @Override
    public void personJobUpdate(MultipartFile file, TbPersonJob personJob, HttpServletRequest request, HttpServletResponse response, Integer updateBy) throws IOException {
        TbPersonJob personJobById = laoWuDao.getPersonJobById(personJob.getId());
        if(!file.isEmpty()){
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            File inFile = new File(request.getRealPath("contract"), fileName);
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(inFile));
            BufferedInputStream bin = new BufferedInputStream(file.getInputStream());
            IOUtils.copy(bin, bout);
            bin.close();
            bout.close();

            personJobById.setContractUrl(fileName);
        }
        personJobById.setCompanyId(personJob.getCompanyId());
        personJobById.setJobContent(personJob.getJobContent());
        personJobById.setStartTime(personJob.getStartTime());
        personJobById.setEndTime(personJob.getEndTime());
        personJobById.setStatus(personJob.getStatus());
        personJobById.setUpdateBy(updateBy);
        personJobById.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        personJobById.setRemark(personJob.getRemark());

        laoWuDao.personOperation(personJobById);
    }


    @Override
    public List<TbSystemDict> getDictsByName(String name) {
        return dataDictService.getDataDictByName(name);
    }

    @Override
    public List<Object[]> getAllPersonIdAndName() {
        return personService.getAllIdAndName();
    }

    @Override
    public List<TbCompany> getCompanysByZhaoPin() {
        List<Integer> normalZhaoPinCompanyId = zhaoPinService.getNormalZhaoPinCompanyId();
        List<TbCompany> list = new ArrayList<>();
        for (Integer id : normalZhaoPinCompanyId){
            list.add(companyService.getCompanyById(id));
        }
        return list;
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
    public Set<Integer> getAllCompanyIdAndName() {
        List<Integer> normalZhaoPinCompanyId = zhaoPinService.getNormalZhaoPinCompanyId();
        Set<Integer> set = new HashSet<>();
        for (Integer id : normalZhaoPinCompanyId){
            set.add(id);
        }
        return set;
    }

    @Override
    public List<Object[]> getAllCompanys() {
        return zhaoPinService.getAllCompanyOfIdAndName();
    }

    @Override
    public List<TbNeedJob> getAllNeedJobs() {
        String hql = "from TbNeedJob";
        return laoWuDao.getAllNeedJobs(hql);
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
    public List<TbNeedJob> getAllJobByCompanyId(Integer id, Integer personId) {
        TbPerson detailPersonById = getDetailPersonById(personId);
        return zhaoPinService.getAllJobByCompanyIdAndPrice(id, detailPersonById.getForPrice());
    }

    @Override
    public List<TbPerson> getPersonsByNeedJobPrice(Double price) {
        StringBuffer hql = new StringBuffer("from TbPerson where forPrice >= :minPrice and forPrice <= :maxPrice");
        Map map = new HashMap(2);
        map.put("maxPrice", price + 1000);
        map.put("minPrice", price - 1000);
        return  personService.getPersonsByPrice(hql, map);
    }

    @Override
    public Map getPersonByCompanyIdForPrice(Integer companyId) {
        Map map = new HashMap(2);
        //根据公司id获取公司职位信息
        List<TbNeedJob> needJobs = zhaoPinService.getAllJobByCompanyId(companyId);
        //根据公司职位信息生成sql语句来查询人才
        StringBuffer hql = new StringBuffer("from TbPerson where 1 = 1 ");
        Map map1 = new HashMap();
        if(needJobs != null){
            hql.append(" and (");
            for (int i = 0; i < needJobs.size(); i++) {
                String minPrice = "minPrice" + i;
                String maxPrice = "maxPrice" + i;
                hql.append("(forPrice >= :minPrice" + i + " and forPrice <= :maxPrice" + i + ")");
                if(i < needJobs.size() - 1){
                    hql.append(" or ");
                }
                map1.put(minPrice, needJobs.get(i).getPrice() - 1000);
                map1.put(maxPrice, needJobs.get(i).getPrice() + 1000);
            }
            hql.append(")");
        }
        List<TbPerson> personsByPrice = personService.getPersonsByPrice(hql, map1);

        map.put("needJobs", needJobs);
        map.put("persons", personsByPrice);
        return map;
    }

    @Override
    public void personJobAdd(MultipartFile file, TbPersonJob personJob, Integer createBy, HttpServletRequest request) throws IOException {
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
        File inFile = new File(request.getRealPath("contract"), fileName);
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(inFile));
        BufferedInputStream bin = new BufferedInputStream(file.getInputStream());
        IOUtils.copy(bin, bout);
        bin.close();
        bout.close();

        personJob.setId(1);
        personJob.setContractUrl(fileName);
        personJob.setCreateBy(createBy);
        personJob.setCreateTime(new Timestamp(System.currentTimeMillis()));
        laoWuDao.personJobAdd(personJob);
    }

    @Override
    public void personJobDelete(Integer id, Integer updateBy) {
        TbPersonJob personJobById = laoWuDao.getPersonJobById(id);
        personJobById.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        personJobById.setUpdateBy(updateBy);
        personJobById.setStatus(dataDictService.getDataDictValueByNameAndLabel("合作状态", "结束"));
        laoWuDao.personOperation(personJobById);
    }

    @Override
    public TbPerson getDetailPersonById(Integer id) {
        return personService.personDetailById(id);
    }

    @Override
    public List<TbNeedJob> getNeedJobByCompanyIdAndPersonPrice(Integer companyId, Double price) {
        List<TbNeedJob> allJobByCompanyId = zhaoPinService.getAllJobByCompanyId(companyId);
        List<TbNeedJob> list = new ArrayList<>();
        for (TbNeedJob needJob : allJobByCompanyId){
            if(needJob.getPrice() + 1000 >= price && needJob.getPrice() - 1000 <= price){
                list.add(needJob);
            }
        }
        return list;
    }

    @Override
    public TbNeedJob getDetailNeedJobById(Integer id) {
        return zhaoPinService.selectNeedJobById(id);
    }

    @Override
    public List<TbNeedJob> getNeedJobsByPersonJobId(Integer id) {
        TbPersonJob tbPersonJob = laowuDetailById(id);
        TbNeedJob detailNeedJobById = getDetailNeedJobById(tbPersonJob.getCompanyId());
        return getNeedJobByCompanyIdAndPersonPrice(detailNeedJobById.getCompanyId(), tbPersonJob.getPersonPrice());
    }

    @Override
    public List<TbNeedJob> getNeedJobByCompanyIdAndPrice(Integer companyId, Double price) {
        return zhaoPinService.getAllJobByCompanyIdAndPrice(companyId, price);
    }

    @Override
    public TbPersonJob personJobByIdCard(String idCard) {
        return laoWuDao.personJobByIdCard(idCard);
    }

}
