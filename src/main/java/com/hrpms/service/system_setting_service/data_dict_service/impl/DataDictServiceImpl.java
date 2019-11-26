package com.hrpms.service.system_setting_service.data_dict_service.impl;

import com.hrpms.dao.system_setting_dao.data_dict_dao.DataDictDao;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbSystemDictOperation;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.system_setting_service.data_dict_service.impl > DataDictServiceImpl
 * @description TODO
 * @create 2019/11/21  15:05
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class DataDictServiceImpl implements DataDictService {
    @Autowired
    private DataDictDao dataDictDao;

    /**
     * 添加数据字典  将session中获取的用户数据放入添加字典中
     * @param
     * @return
     **/
    @Override
    public void dataDictAdd(TbSystemDict tbSystemDict, TbSystemUser tbSystemUser) {
        tbSystemDict.setId(0);
        tbSystemDict.setCreateBy(tbSystemUser.getId());
        tbSystemDict.setCreateTime(new Timestamp(System.currentTimeMillis()));
        dataDictDao.dataDictAdd(tbSystemDict);
    }

    /**
     * 得到数据字典中的字典状态  正常， 删除
     * @param
     * @return
     **/
    @Override
    public List<TbSystemDict> getDataDictByName(String name) {
        return dataDictDao.getDataDictByName(name);
    }

    @Override
    public Page<TbSystemDict> getDataDictByOperation(Integer currentPage, TbSystemDictOperation dataDictOperation) {
        StringBuffer stringBuffer = new StringBuffer("from TbSystemDict where 1=1 ");
        String oldName = "";
        if(dataDictOperation.getNameQuery() != null && !"".equals(dataDictOperation.getNameQuery())){
            oldName = dataDictOperation.getNameQuery();
            dataDictOperation.setNameQuery("%" + oldName + "%");
            stringBuffer.append(" and name like :nameQuery ");
        }
        if(dataDictOperation.getStatusQuery() != null && !"".equals(dataDictOperation.getStatusQuery())){
            stringBuffer.append(" and status = :statusQuery ");
        }

        //条件查询总条数
        Long count = dataDictDao.getCountDataDictByOperation("select count(1) " + stringBuffer.toString(), dataDictOperation);

        Page page = new Page(currentPage, 3, count);

        dataDictOperation.setStartIndexQuery(page.getStartIndex());
        dataDictOperation.setPageSize(page.getPageSize());
        List<TbSystemDict> dataDicts = dataDictDao.getDataDIctByOperation(stringBuffer.toString(), dataDictOperation);
        dataDictOperation.setNameQuery(oldName);
        page.setDataList(dataDicts);

        return page;
    }

    @Override
    public TbSystemDict getDataDictById(Integer id) {
        TbSystemDict dataDictById = dataDictDao.getDataDictById(id);
        return dataDictById;
    }

    @Override
    public void dataDictUpdate(TbSystemDict tbSystemDict) {
        dataDictDao.dataDictUpdate(tbSystemDict);
    }

    @Override
    public void dataDictDelete(Integer id) {
        //根据字典名称， 字典显示值查询存储值
        String deleteStatus = getDataDictValueByNameAndLabel("字典状态", "删除");
        TbSystemDict dataDictById = getDataDictById(id);
        dataDictById.setStatus(deleteStatus);
        dataDictDao.dataDictDelete(dataDictById);
    }

    @Override
    public String getDataDictValueByNameAndLabel(String name, String label) {
        String hql = "from TbSystemDict where name = :name and label = :label ";
        Map map = new HashMap(3);
        map.put("name", name);
        map.put("label", label);
        map.put("hql", hql);

        return dataDictDao.getDataDictValueByNameAndLabel(map);
    }
}
