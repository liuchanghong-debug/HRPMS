package com.hrpms.service.sms_template_service.impl;

import com.hrpms.dao.sms_template_dao.TbSmsTemplateDao;
import com.hrpms.pojo.TbSalary;
import com.hrpms.pojo.TbSmsTemplate;
import com.hrpms.service.salary_manager_service.TbSalaryService;
import com.hrpms.service.sms_template_service.TbSmsTemplateService;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TbSmsTemplateServiceImpl implements TbSmsTemplateService {

    @Autowired
    private TbSmsTemplateDao tbSmsTemplateDao;

    @Override
    public Page<TbSmsTemplate> selectSmsTemplateByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbSmsTemplate where 1=1 ");
        hql2.append("from TbSmsTemplate where 1=1 ");
        if(map.get("subject")!=null && !"".equals(map.get("subject"))){
            map.put("subject","%"+map.get("subject")+"%");
            hql1.append("and subject like :subject ");
            hql2.append("and subject like :subject ");
        }
        if(map.get("template_code")!=null && !"".equals(map.get("template_code"))){
            map.put("template_code","%"+map.get("template_code")+"%");
            hql1.append("and template_code like :template_code ");
            hql2.append("and template_code like :template_code ");
        }

        Long count = tbSmsTemplateDao.selectSmsTemplateCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbSmsTemplate> list =tbSmsTemplateDao.selectSmsTemplateByDuo(hql2.toString(), map);
        page.setDataList(list);
        return page;
    }

    @Override
    public void addSmsTemplate(TbSmsTemplate tbSmsTemplate) {
        tbSmsTemplateDao.addSmsTemplate(tbSmsTemplate);
    }

    @Override
    public TbSmsTemplate selectSmsTemplateById(int id) {
        return tbSmsTemplateDao.selectSmsTemplateById(id);
    }

    @Override
    public void updateSmsTemplate(TbSmsTemplate tbSmsTemplate) {
        tbSmsTemplateDao.updateSmsTemplate(tbSmsTemplate);
    }

    @Override
    public void deleteSmsTemplate(int id) {
        tbSmsTemplateDao.deleteSmsTemplate(id);
    }
}
