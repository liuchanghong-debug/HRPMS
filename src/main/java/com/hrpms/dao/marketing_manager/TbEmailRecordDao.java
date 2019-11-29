package com.hrpms.dao.marketing_manager;

import com.hrpms.pojo.TbEmailRecord;

import java.util.List;
import java.util.Map;

public interface TbEmailRecordDao {
    //动态模糊分页查询邮件发件箱
    public List<TbEmailRecord> selectEmailRecoredByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectEmailRecoredCount(String hql,Map map);

    //添加邮件发件箱信息
    public void addEmailRecored(TbEmailRecord tbEmailRecord);

    //根据id查找邮件发件箱
    public TbEmailRecord selectEmailRecoredById(int id);


    //根据id删除邮件发件箱中信息
    public void deleteEmailRecored(int id);

}
