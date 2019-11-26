package com.hrpms.dao.email_template_dao;

import com.hrpms.pojo.TbEmailTemplate;

import java.util.List;
import java.util.Map;

public interface TbEmailTemplateDao {

    //动态模糊分页查询邮件模板信息
    public List<TbEmailTemplate> selectEmailTemplateByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectEmailTemplateCount(String hql,Map map);

    //添加邮件模板信息
    public void addEmailTemplate(TbEmailTemplate tbEmailTemplate);

    //根据id查找邮件模板新
    public TbEmailTemplate selectEmailTemplateById(int id);

    //根据id修改邮件模板
    public void updateEmailTemplate(TbEmailTemplate tbEmailTemplate);

    //根据id删除邮件模板
    public void deleteEmailTemplate(int id);
}
