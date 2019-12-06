package com.hrpms.service.system_setting_service.email_template_service.impl;

import com.hrpms.dao.system_setting_dao.email_template_dao.TbEmailTemplateDao;
import com.hrpms.pojo.TbEmailTemplate;
import com.hrpms.service.system_setting_service.email_template_service.TbEmailTemplateService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class TbEmialTemplateServiceImpl implements TbEmailTemplateService {

    @Autowired
    private TbEmailTemplateDao tbEmailTemplateDao;

    @Override
    public Page<TbEmailTemplate> selectEmailTemplateByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbEmailTemplate where 1=1 ");
        hql2.append("from TbEmailTemplate where 1=1 ");
        if(map.get("subject")!=null && !"".equals(map.get("subject"))){
            map.put("subject","%"+map.get("subject")+"%");
            hql1.append("and subject like :subject ");
            hql2.append("and subject like :subject ");
        }

        Long count = tbEmailTemplateDao.selectEmailTemplateCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbEmailTemplate> list =tbEmailTemplateDao.selectEmailTemplateByDuo(hql2.toString(), map);
        page.setDataList(list);
        return page;
    }

    @Override
    public void addEmailTemplate(TbEmailTemplate tbEmailTemplate) {
        tbEmailTemplateDao.addEmailTemplate(tbEmailTemplate);
    }

    @Override
    public TbEmailTemplate selectEmailTemplateById(int id) {
        TbEmailTemplate tbEmailTemplate = tbEmailTemplateDao.selectEmailTemplateById(id);
        return tbEmailTemplate;
    }

    @Override
    public void updateEmailTemplate(TbEmailTemplate tbEmailTemplate) {
        tbEmailTemplateDao.updateEmailTemplate(tbEmailTemplate);

    }

    @Override
    public void deleteEmailTemplate(int id) {
        tbEmailTemplateDao.deleteEmailTemplate(id);
    }
}
