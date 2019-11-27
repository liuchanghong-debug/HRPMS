package com.hrpms.service.marketing_information_service;

import com.hrpms.pojo.TbSmsRecord;
import com.hrpms.utils.Page;

import java.util.List;
import java.util.Map;

public interface TbSmsRecoredService {

    //动态模糊分页查询短信发件箱
    public Page<TbSmsRecord> selectSmsRecoredByDuo(Integer currentPage, Map map);

    //添加短信发件箱信息
    public void addSmsRecored(TbSmsRecord tbSmsRecord);

    //根据id查找短信发件箱
    public TbSmsRecord selectSmsRecoredById(int id);


    //根据id删除短信发件箱中信息
    public void deleteSmsRecoredById(int id);
}
