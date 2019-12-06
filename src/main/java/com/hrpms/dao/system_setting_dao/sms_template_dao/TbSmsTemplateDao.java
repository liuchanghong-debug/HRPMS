package com.hrpms.dao.system_setting_dao.sms_template_dao;

import com.hrpms.pojo.TbSmsTemplate;

import java.util.List;
import java.util.Map;

public interface TbSmsTemplateDao {

    //动态模糊分页查询短信模板信息
    public List<TbSmsTemplate> selectSmsTemplateByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectSmsTemplateCount(String hql, Map map);

    //添加短信模板信息
    public void addSmsTemplate(TbSmsTemplate tbSmsTemplate);

    //根据id查找短信模板新
    public TbSmsTemplate selectSmsTemplateById(int id);

    //根据id修改短信模板
    public void updateSmsTemplate(TbSmsTemplate tbSmsTemplate);

    //根据id删除短信模板
    public void deleteSmsTemplate(int id);

    //短信模板编码唯一验证
    public TbSmsTemplate TemplateCodeIsOne(String template_code);

}
