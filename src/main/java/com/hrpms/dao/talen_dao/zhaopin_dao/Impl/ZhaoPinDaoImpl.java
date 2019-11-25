package com.hrpms.dao.talen_dao.zhaopin_dao.Impl;

import com.hrpms.dao.talen_dao.zhaopin_dao.ZhaoPinDao;
import com.hrpms.pojo.TbNeedJob;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.talen_dao.zhaopin_dao.Impl > ZhaoPinDaoImpl
 * @description TODO
 * @create 2019/11/25  17:48
 * @versiion 1.0
 * @Description:
 */
@Repository
public class ZhaoPinDaoImpl implements ZhaoPinDao {
    @Override
    public List<TbNeedJob> selectNeedJobByDuo(String hql, Map map) {
        return null;
    }

    @Override
    public Long selectNeedJobCount(String hql, Map map) {
        return null;
    }

    @Override
    public void addNeedJob(TbNeedJob tbNeedJob) {

    }

    @Override
    public TbNeedJob selectNeedJobById(int id) {
        return null;
    }
}
