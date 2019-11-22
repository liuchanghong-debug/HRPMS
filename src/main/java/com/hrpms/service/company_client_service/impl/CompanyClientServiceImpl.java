package com.hrpms.service.company_client_service.impl;

import com.hrpms.dao.company_client_dao.CompanyClientDao;
import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbCompanyOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.company_client_service.impl > CompanyClientServiceImpl
 * @description TODO
 * @create 2019/11/21  19:56
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class CompanyClientServiceImpl implements CompanyClientService {
    @Autowired
    private DataDictService dataDictService;
    @Autowired
    private CompanyClientDao companyClientDao;

    @Override
    public Page<TbCompany> getCompanyByOperation(Integer currentPage, TbCompanyOperation companyOperation) {
        StringBuffer stringBuffer = new StringBuffer(" from TbCompany where 1 = 1 ");
        String oldName = "";
        String oldCompanyNo = "";
        String oldIdCard = "";
        if(companyOperation.getNameQuery() != null && !"".equals(companyOperation.getNameQuery())){
            oldName = companyOperation.getNameQuery();
            companyOperation.setNameQuery("%" + oldName + "%");
            stringBuffer.append(" and name like :nameQuery ");
        }
        if(companyOperation.getCompanyNoQuery() != null && !"".equals(companyOperation.getCompanyNoQuery())){
            oldCompanyNo = companyOperation.getCompanyNoQuery();
            companyOperation.setCompanyNoQuery("%" + oldCompanyNo + "%");
            stringBuffer.append(" and companyNo like :companyNoQuery");
        }
        if(companyOperation.getIdCardQuery() != null && !"".equals(companyOperation.getIdCardQuery())){
            oldIdCard = companyOperation.getIdCardQuery();
            companyOperation.setIdCardQuery("%" + oldIdCard + "%");
            stringBuffer.append(" and idCard like :idCardQuery");
        }
        //查询总条数
        Long count = companyClientDao.getCountCompanyByOperation("select count(1) " + stringBuffer.toString(), companyOperation);
        Page<TbCompany> page = new Page(currentPage, 3, count);
        companyOperation.setStartIndex(page.getStartIndex());
        companyOperation.setPageSize(page.getPageSize());
        //分页查询
        List<TbCompany> companyList = companyClientDao.getCompanyByOperation(stringBuffer.toString(), companyOperation);
        page.setDataList(companyList);

        companyOperation.setNameQuery(oldName);
        companyOperation.setCompanyNoQuery(oldCompanyNo);
        companyOperation.setIdCardQuery(oldIdCard);

        return page;
    }

    @Override
    public TbCompany getCompanyById(Integer id) {
        return companyClientDao.getCompanyById(id);
    }

    @Override
    public List<TbSystemDict> getDataDictByName(String name) {
        return dataDictService.getDataDictByName(name);
    }

    @Override
    public void companyToAdd(TbCompany tbCompany) {
        tbCompany.setId(1);
        companyClientDao.companyToAdd(tbCompany);
    }

    @Override
    public void companyToUpdate(TbCompany company) {
        companyClientDao.companyToUpdate(company);
    }

    @Override
    public void companyDelete(Integer id, Integer updateById) {
        TbCompany companyById = companyClientDao.getCompanyById(id);
        companyById.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        companyById.setUpdateBy(updateById);
        String delStatus = dataDictService.getDataDictValueByNameAndLabel("公司客户状态", "删除");
        companyById.setStatus(delStatus);
        companyClientDao.companyToUpdate(companyById);
    }
}
