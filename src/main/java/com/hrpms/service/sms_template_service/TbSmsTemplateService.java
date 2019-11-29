package com.hrpms.service.sms_template_service;

import com.hrpms.pojo.TbSmsTemplate;
import com.hrpms.utils.Page;

import java.util.Map;

public interface TbSmsTemplateService {
    //动态模糊分页查询短信模板信息
    public Page<TbSmsTemplate> selectSmsTemplateByDuo(Integer currentPage, Map map);

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
