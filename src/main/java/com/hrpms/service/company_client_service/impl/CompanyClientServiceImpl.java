package com.hrpms.service.company_client_service.impl;

import com.hrpms.dao.company_client_dao.CompanyClientDao;
import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbCompanyOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.utils.DataOutOfExcel;
import com.hrpms.utils.Download;
import com.hrpms.utils.ExcelUpload;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void templateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Download.fileTemplateDownload(name, request, response);
    }

    @Override
    public void getCompanyByOperationNoPaging(TbCompanyOperation companyOperation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer stringBuffer = new StringBuffer(" from TbCompany where 1 = 1 ");
        if(companyOperation.getNameQuery() != null && !"".equals(companyOperation.getNameQuery())){
            companyOperation.setNameQuery("%" + companyOperation.getNameQuery() + "%");
            stringBuffer.append(" and name like :nameQuery ");
        }
        if(companyOperation.getCompanyNoQuery() != null && !"".equals(companyOperation.getCompanyNoQuery())){
            companyOperation.setCompanyNoQuery("%" + companyOperation.getCompanyNoQuery() + "%");
            stringBuffer.append(" and companyNo like :companyNoQuery");
        }
        if(companyOperation.getIdCardQuery() != null && !"".equals(companyOperation.getIdCardQuery())){
            companyOperation.setIdCardQuery("%" + companyOperation.getIdCardQuery() + "%");
            stringBuffer.append(" and idCard like :idCardQuery");
        }
        List<TbCompany> companies = companyClientDao.getCompanyByOperationNoPaging(stringBuffer.toString(), companyOperation);
        List resultList = new ArrayList();
        for(TbCompany company : companies){
            List dataList = new ArrayList();
            dataList.add(company.getName());
            dataList.add(company.getCompanyNo());
            dataList.add(company.getTelPhone());
            dataList.add(company.getZipCode());
            dataList.add(company.getOwner());
            dataList.add(company.getIdCard());
            dataList.add(company.getPhone());
            dataList.add(company.getSex());
            dataList.add(company.getEmail());
            dataList.add(company.getOwnerShip());
            dataList.add(company.getStatus());
            dataList.add(company.getCompanyType());
            dataList.add(company.getAddress());
            dataList.add(company.getRemark());
            resultList.add(dataList);
        }
        Map map = new HashMap(2);
        map.put("fileName", "公司客户模板");
        map.put("dataList", resultList);
        DataOutOfExcel.dataOut(map, request, response);
    }

    @Override
    public void fileUpload(InputStream file, Integer createBy) throws IOException, InvalidFormatException {
        List<Map<Object, Object>> dataList = ExcelUpload.parseExcel(file);
        int size = dataList.size();
        for (int i = 0; i < size; i++) {
            TbCompany company = new TbCompany();
            Map map = dataList.get(i);
            company.setName((String) map.get("公司名称"));
            company.setCompanyNo((String) map.get("统一信用号"));
            company.setTelPhone((String) map.get("公司电话"));
            company.setZipCode((String) map.get("邮编"));
            company.setOwner((String) map.get("法人"));
            company.setIdCard((String) map.get("身份证号"));
            company.setPhone((String) map.get("法人手机"));
            company.setSex((String) map.get("法人性别"));
            company.setEmail((String) map.get("电子邮件"));
            company.setOwnerShip((String) map.get("公司性质"));
            company.setStatus((String) map.get("状态"));
            company.setCompanyType((String) map.get("公司类别"));
            company.setAddress((String) map.get("公司地址"));
            company.setRemark((String) map.get("备注"));
            company.setCreateBy(createBy);
            company.setCreateTime(new Timestamp(System.currentTimeMillis()));
            companyToAdd(company);
        }
    }

    @Override
    public List<Object[]> getAllCompanyOfIdAndName() {
        return companyClientDao.getAllCompanyOfIdAndName();
    }
}
