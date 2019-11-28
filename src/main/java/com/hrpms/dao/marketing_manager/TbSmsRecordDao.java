package com.hrpms.dao.marketing_manager;

import com.hrpms.pojo.TbEmailRecord;
import com.hrpms.pojo.TbSmsRecord;

import java.util.List;
import java.util.Map;

public interface TbSmsRecordDao {
    //动态模糊分页查询短信发件箱
    public List<TbSmsRecord> selectSmsRecoredByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectSmsRecoredCount(String hql, Map map);

    //添加短信发件箱信息
    public void addSmsRecored(TbSmsRecord tbSmsRecord);

    //根据id查找短信发件箱
    public TbSmsRecord selectSmsRecoredById(int id);


    //根据id删除短信发件箱中信息
    public void deleteSmsRecoredById(int id);

}
