package com.hrpms.service.marketing_information_service;

import com.hrpms.pojo.TbEmailRecord;
import com.hrpms.utils.Page;

import java.util.List;
import java.util.Map;

public interface TbEmailRecoredService {
    //动态模糊分页查询邮件发件箱
    public Page<TbEmailRecord> selectEmailRecoredByDuo(Integer currentPage, Map map);


    //添加邮件发件箱信息
    public void addEmailRecored(TbEmailRecord tbEmailRecord);

    //根据id查找邮件发件箱
    public TbEmailRecord selectEmailRecoredById(int id);


    //根据id删除邮件发件箱中信息
    public void deleteEmailRecored(int id);

}
