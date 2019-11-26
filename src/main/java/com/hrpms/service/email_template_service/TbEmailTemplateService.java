package com.hrpms.service.email_template_service;

import com.hrpms.pojo.TbEmailTemplate;
import com.hrpms.utils.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface TbEmailTemplateService {

    //动态模糊分页查询邮件模板信息
    public Page<TbEmailTemplate> selectEmailTemplateByDuo(Integer currentPage, Map map);

    //添加邮件模板信息
    public void addEmailTemplate(TbEmailTemplate tbEmailTemplate);

    //根据id查找邮件模板新
    public TbEmailTemplate selectEmailTemplateById(int id);

    //根据id修改邮件模板
    public void updateEmailTemplate(TbEmailTemplate tbEmailTemplate);

    //根据id删除邮件模板
    public void deleteEmailTemplate(int id);
}
