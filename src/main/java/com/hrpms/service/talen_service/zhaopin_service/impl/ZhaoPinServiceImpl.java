package com.hrpms.service.talen_service.zhaopin_service.impl;

import com.hrpms.dao.talen_dao.zhaopin_dao.ZhaoPinDao;
import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbNeedJobOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.service.talen_service.zhaopin_service.ZhaoPinService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.talen_service.zhaopin.impl > ZhaoPinServiceImpl
 * @description TODO
 * @create 2019/11/25  17:40
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class ZhaoPinServiceImpl implements ZhaoPinService {
    @Autowired
    private CompanyClientService companyService;
    @Autowired
    private DataDictService dataDictService;
    @Autowired
    private ZhaoPinDao zhaoPinDao;

    @Override
    public Page<TbNeedJob> zhaopinList(Integer currentPage, TbNeedJobOperation needJobOperation) {
        int pageSize = 3;
        StringBuffer hql = new StringBuffer("from TbNeedJob where 1=1 ");
        String oldJobNameQuery = "";
        String oldJobTypeQuery = "";
        if(!"".equals(needJobOperation.getJobNameQuery()) && needJobOperation.getJobNameQuery() != null){
            oldJobNameQuery = needJobOperation.getJobNameQuery();
            needJobOperation.setJobNameQuery("%" + oldJobNameQuery + "%");
            hql.append("and jobName like :jobNameQuery ");
        }
        if(!"".equals(needJobOperation.getJobTypeQuery()) && needJobOperation.getJobTypeQuery() != null){
            oldJobTypeQuery = needJobOperation.getJobTypeQuery();
            needJobOperation.setJobTypeQuery("%" + oldJobTypeQuery + "%");
            hql.append("and jobType like :jobTypeQuery ");
        }
        if(!"".equals(needJobOperation.getIndustryQuery()) && needJobOperation.getIndustryQuery() != null){
            hql.append("and industry = :industryQuery ");
        }
        if(!"".equals(needJobOperation.getCompanyIdQuery()) && needJobOperation.getCompanyIdQuery() != null){
            hql.append("and companyId = :companyIdQuery ");
        }
        Long count = zhaoPinDao.zhaopinCount("select count(1) " + hql.toString(), needJobOperation);
        Page<TbNeedJob> page = new Page<TbNeedJob>(currentPage, pageSize, count);
        needJobOperation.setStartIndex(page.getStartIndex());
        needJobOperation.setPageSize(page.getPageSize());
        List<TbNeedJob> list = zhaoPinDao.zhaopinList(hql.toString(), needJobOperation);
        page.setDataList(list);

        needJobOperation.setJobNameQuery(oldJobNameQuery);
        needJobOperation.setJobTypeQuery(oldJobTypeQuery);
        return page;
    }

    @Override
    public void zhaopinAdd(TbNeedJob tbNeedJob, Integer createBy) {
        tbNeedJob.setId(1);
        tbNeedJob.setCreateBy(createBy);
        tbNeedJob.setCreateTime(new Timestamp(System.currentTimeMillis()));
        zhaoPinDao.zhaopinAdd(tbNeedJob);
    }

    @Override
    public TbNeedJob selectNeedJobById(int id) {
        return zhaoPinDao.selectNeedJobById(id);
    }

    @Override
    public void updateNeedJob(TbNeedJob tbNeedJob) {
        zhaoPinDao.updateNeedJob(tbNeedJob);
    }

    @Override
    public void deleteNeedJob(int id) {
        zhaoPinDao.deleteNeedJob(id);
    }

    @Override
    public List<TbSystemDict> getDictsByName(String name) {
        return dataDictService.getDataDictByName(name);
    }

    @Override
    public String getDictByNameAndLabel(String name, String label) {
        return dataDictService.getDataDictValueByNameAndLabel(name, label);
    }

    @Override
    public List<Object[]> getAllCompanyOfIdAndName() {
        return companyService.getAllCompanyOfIdAndName();
    }
}
