package com.hrpms.dao.talent_service_dao;

import com.hrpms.pojo.TbNeedJob;

import java.util.List;
import java.util.Map;

public interface TbNeedJobDao {

    //动态模糊分页查询招聘信息
    public List<TbNeedJob> selectNeedJobByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectNeedJobCount(String hql, Map map);

    //添加招聘信息
    public void addNeedJob(TbNeedJob tbNeedJob);

    //根据id查看招聘信息
    public TbNeedJob selectNeedJobById(int id);

    //根据id修改招聘信息
    public void updateNeedJob(TbNeedJob tbNeedJob);

    //根据id删除招聘信息(实则修改招聘状态)
    public void deleteNeedJob(int id);
}
