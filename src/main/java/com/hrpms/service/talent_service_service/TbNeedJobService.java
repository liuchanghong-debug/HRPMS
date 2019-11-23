package com.hrpms.service.talent_service_service;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.utils.Page;

import java.util.Map;

public interface TbNeedJobService {
    //动态模糊分页查询招聘信息
    public Page<TbNeedJob> selectNeedJobByDuo(Integer currentPage, Map map);

    //添加招聘信息
    public void addNeedJob(TbNeedJob tbNeedJob);

    //根据id查看招聘信息
    public TbNeedJob selectNeedJobById(int id);

    //根据id修改招聘信息
    public void updateNeedJob(TbNeedJob tbNeedJob);

    //根据id删除招聘信息(实则修改招聘状态)
    public void deleteNeedJob(int id);

}
