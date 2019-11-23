package com.hrpms.service.talent_service_service.impl;

import com.hrpms.dao.talent_service_dao.TbNeedJobDao;
import com.hrpms.pojo.TbNeedJob;
import com.hrpms.service.talent_service_service.TbNeedJobService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class TbNeedJobServiceImpl implements TbNeedJobService {
    @Autowired
    private TbNeedJobDao tbNeedJobDao;

    @Override
    public Page<TbNeedJob> selectNeedJobByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbNeedJob where 1=1 ");
        hql2.append("from TbNeedJob where 1=1 ");
        if(map.get("jobname")!=null && !"".equals(map.get("jobname"))){
            map.put("jobname","%"+map.get("jobname")+"%");
            hql1.append("and jobname like :jobname ");
            hql2.append("and jobname like :jobname ");
        }
        if(map.get("jobType")!=null && !"".equals(map.get("jobType"))){
            map.put("jobType","%"+map.get("jobType")+"%");
            hql1.append("and jobType like :jobType ");
            hql2.append("and jobType like :jobType ");
        }
        if(map.get("industry")!=null && !"".equals(map.get("industry"))){
            hql1.append("and industry = :industry ");
            hql2.append("and industry = :industry ");
        }
        if(map.get("companyId")!=null && !"".equals(map.get("companyId"))){
            hql1.append("and companyId = :companyId ");
            hql2.append("and companyId = :companyId ");
        }
        Long count = tbNeedJobDao.selectNeedJobCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbNeedJob> list = tbNeedJobDao.selectNeedJobByDuo(hql2.toString(), map);
        page.setDataList(list);
        return page;
    }

    @Override
    public void addNeedJob(TbNeedJob tbNeedJob) {
        tbNeedJobDao.addNeedJob(tbNeedJob);
    }

    @Override
    public TbNeedJob selectNeedJobById(int id) {
        TbNeedJob tbNeedJob = tbNeedJobDao.selectNeedJobById(id);
        return tbNeedJob;
    }

    @Override
    public void updateNeedJob(TbNeedJob tbNeedJob) {
        tbNeedJobDao.updateNeedJob(tbNeedJob);
    }

    @Override
    public void deleteNeedJob(int id) {
        tbNeedJobDao.deleteNeedJob(id);
    }
}
