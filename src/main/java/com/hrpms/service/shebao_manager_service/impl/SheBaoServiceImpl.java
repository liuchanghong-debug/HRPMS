package com.hrpms.service.shebao_manager_service.impl;

import com.hrpms.dao.shebao_manager_dao.SheBaoDao;
import com.hrpms.pojo.*;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.customer_client_service.CustomerService;
import com.hrpms.service.salary_manager_service.TbSalaryService;
import com.hrpms.service.shebao_manager_service.SheBaoService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.shebao_manager_service.impl > SheBaoServiceImpl
 * @description TODO
 * @create 2019/11/23  16:32
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class SheBaoServiceImpl implements SheBaoService {
    @Autowired
    private DataDictService dataDictService;
    @Autowired
    private SheBaoDao sheBaoDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyClientService companyClientService;
    @Autowired
    private TbSalaryService salaryService;


    @Override
    public Page<TbSocialInsurance> socialInsuranceQueryByOperation(Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation) {
        String shebaoStatus = dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常");
        StringBuffer hql = new StringBuffer(" from TbSocialInsurance where status = " + shebaoStatus);
        String oldNameQuery = "";
        String oldIdCardQuery = "";
        String oldSbCardQuery = "";
        if(!"".equals(socialInsuranceOperation.getNameQuery()) && socialInsuranceOperation.getNameQuery() != null){
            //从客户表中获取值
            oldNameQuery = socialInsuranceOperation.getNameQuery();
            socialInsuranceOperation.setNameQuery("%" + oldNameQuery + "%");
            hql.append(" and name like :nameQuery ");

        }
        if(!"".equals(socialInsuranceOperation.getIdCardQuery()) && socialInsuranceOperation.getIdCardQuery() != null){
            oldIdCardQuery = socialInsuranceOperation.getIdCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldIdCardQuery + "%");
            hql.append(" and idCard like :idCardQuery");
        }
        if(!"".equals(socialInsuranceOperation.getSbCardQuery()) && socialInsuranceOperation.getSbCardQuery() != null){
            oldSbCardQuery = socialInsuranceOperation.getSbCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldSbCardQuery + "%");
            hql.append(" and idCard like :sbCardQuery");
        }
        Long count = sheBaoDao.socialInsuranceQueryCountByOperation("select count(1) " + hql.toString(), socialInsuranceOperation);
        Page<TbSocialInsurance> page = new Page(currentPage, 3, count);
        socialInsuranceOperation.setStartIndex(page.getStartIndex());
        socialInsuranceOperation.setPageSize(page.getPageSize());
        List<TbSocialInsurance> socialInsuranceList = sheBaoDao.socialInsuranceQueryByOperationPaging(hql.toString(), socialInsuranceOperation);
        //根据idCard在社保缴费记录表中查寻
        for (TbSocialInsurance socialInsurance : socialInsuranceList){
            Object object = sheBaoDao.shebaoRecordByIdCard(socialInsurance.getIdCard());
            if(object == null){
                socialInsurance.setCreateBy(0);
            }
        }
        page.setDataList(socialInsuranceList);

        socialInsuranceOperation.setNameQuery(oldNameQuery);
        socialInsuranceOperation.setIdCardQuery(oldIdCardQuery);
        socialInsuranceOperation.setSbCardQuery(oldSbCardQuery);

        return page;
    }

    @Override
    public TbSocialInsurance getSheBaoById(Integer id) {
        return sheBaoDao.getSheBaoById(id);
    }

    @Override
    public void shebaoUpdate(TbSocialInsurance socialInsurance, Integer updateBy) {
        TbSocialInsurance sheBaoById = getSheBaoById(socialInsurance.getId());
        sheBaoById.setBasePay(socialInsurance.getBasePay());
        sheBaoById.setMustPay(socialInsurance.getMustPay());
        sheBaoById.setYangLao(socialInsurance.getYangLao());
        sheBaoById.setYiLiao(socialInsurance.getYiLiao());
        sheBaoById.setShiYe(socialInsurance.getShiYe());
        sheBaoById.setGongShang(socialInsurance.getGongShang());
        sheBaoById.setShengYu(socialInsurance.getShengYu());
        sheBaoById.setStatus(socialInsurance.getStatus());
        sheBaoById.setRemark(socialInsurance.getRemark());
        sheBaoById.setUpdateBy(updateBy);
        sheBaoById.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        sheBaoDao.shebaoUpdate(sheBaoById);
    }

    @Override
    public void shebaoDelete(Integer id, Integer updateBy) {
        TbSocialInsurance sheBaoById = getSheBaoById(id);
        String deleteStatus = dataDictService.getDataDictValueByNameAndLabel("社保状态", "删除");
        sheBaoById.setStatus(deleteStatus);
        sheBaoById.setUpdateBy(updateBy);
        sheBaoById.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sheBaoDao.shebaoUpdate(sheBaoById);
    }

    @Override
    public TbCustomer getCustomerByIdCard(String idCard) {
        return customerService.getCustomerByIdCard(idCard);
    }

    @Override
    public List<Object[]> getCompanys() {
        return companyClientService.getAllCompanyOfIdAndName();
    }

    @Override
    public void shebaoAdd(TbSocialInsurance socialInsurance, Integer createBy) {
        socialInsurance.setCreateBy(createBy);
        socialInsurance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        socialInsurance.setId(1);
        socialInsurance.setStatus(dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常"));

        sheBaoDao.shebaoAdd(socialInsurance);
    }

    @Override
    public void shebaoRecordAdd(TbSocialInsuranceRecord socialInsuranceRecord, Integer createBy) {
        socialInsuranceRecord.setId(1);
        socialInsuranceRecord.setCreateBy(createBy);
        socialInsuranceRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sheBaoDao.shebaoRecordAdd(socialInsuranceRecord);
    }

    @Override
    public List<Map> getNotInSociaOfCustomer() {
        //得到字典中 社保状态  可用的value
        String dictValue = dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常");
        //得到已存在的社保信息中的客户idCard 值
        List<String> normalSociaOfCustomer = sheBaoDao.getUserableInSheBaoOfIdCard("select idCard from TbSocialInsurance where status = ?", dictValue);
        //在customer中查询不属于此list集合中的对象信息
        List<Object[]> dataOfNotInList = customerService.getDataOfNotInList(normalSociaOfCustomer);
        List<Map> list = new ArrayList<>();
        for (Object[] objects : dataOfNotInList){
            Map map = new HashMap(2);
            map.put("id", objects[0]);
            map.put("name", objects[1]);
            list.add(map);
        }

        return list;
    }

    @Override
    public Map getDetailOfCustomerById(Integer id) {
        TbCustomer customer = customerService.customerById(id);
        Map map = new HashMap(12);
        map.put("idCard", customer.getIdCard());
        map.put("sex", customer.getSex());
        map.put("name", customer.getName());
        map.put("birthday", new SimpleDateFormat("yyyy-MM-dd").format(customer.getBirthday()));
        map.put("phone", customer.getPhone());
        map.put("email", customer.getEmail());
        map.put("address", customer.getAddress());
        map.put("zipCode", customer.getZipCode());
        map.put("school", customer.getSchool());
        map.put("specialty", customer.getSpecialty());
        map.put("graduation", customer.getGraduation());
        map.put("companyId", customer.getCompanyId());
        //通过公司id 查询公司信息
        TbCompany companyById = companyClientService.getCompanyById(customer.getCompanyId());
        map.put("companyName", companyById.getName());
        map.put("salary", salaryService.getSalaryByIdCard(customer.getIdCard()));

        return map;
    }

    @Override
    public List<TbSystemDict> getDictBySheBao() {
        return dataDictService.getDataDictByName("社保状态");
    }

    @Override
    public List<String> getSbCardBySocialInsuranceRecord() {
        String normalStatus = dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常");
        String hql = "select sbCard from TbSocialInsuranceRecord where status = " + normalStatus;
        return sheBaoDao.getSbCardBySocialInsuranceRecord(hql);
    }

    @Override
    public List<TbSocialInsurance> getNotPayOfTbSocialInsurance() {
        List<String> alreatdyExist = getSbCardBySocialInsuranceRecord();
        return getNotInListBySbCard(alreatdyExist);
    }

    @Override
    public List<TbSocialInsurance> getNotInListBySbCard(List<String> sbCards) {
        String normalStatus = dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常");
        if(sbCards.isEmpty()){
            String hql = "from TbSocialInsurance where status = " + normalStatus;
            return sheBaoDao.getSocialInsurances(hql);
        }else {
            String hql = "from TbSocialInsurance where status = " + normalStatus + " and sbCard not in :sbCards";
            return sheBaoDao.getSocialInsurancesByList(hql, sbCards);
        }
    }

    @Override
    public Map shebaoRecordDetailMess(Integer id) {
        //查询出idCard, 根据idCard查询customer 根据customer中的companyId查询company
        TbSocialInsuranceRecord sheBaoRecordById = getSheBaoRecordById(id);
        TbCustomer customerByIdCard = customerService.getCustomerByIdCard(sheBaoRecordById.getIdCard());
        TbCompany companyById = companyClientService.getCompanyById(customerByIdCard.getCompanyId());
        Map map = new HashMap(3);
        map.put("shebaoRecord", sheBaoRecordById);
        map.put("customer", customerByIdCard);
        map.put("company", companyById);
        return map;
    }

    @Override
    public TbSocialInsuranceRecord getSheBaoRecordById(Integer id) {
        return sheBaoDao.getSheBaoRecordById(id);
    }

    @Override
    public void shebaoRecordUpdate(TbSocialInsuranceRecord socialInsuranceRecord, Integer updateBy) {
        //先查询再修改
        TbSocialInsuranceRecord shebaoRecord = sheBaoDao.getSheBaoRecordById(socialInsuranceRecord.getId());
        shebaoRecord.setPayMonth(socialInsuranceRecord.getPayMonth());
        shebaoRecord.setStatus(socialInsuranceRecord.getStatus());
        shebaoRecord.setRemark(socialInsuranceRecord.getRemark());
        shebaoRecord.setUpdateBy(updateBy);
        shebaoRecord.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sheBaoDao.shebaoRecordUpdate(shebaoRecord);
    }

    @Override
    public Map getDetailMessBySocialInsuranceId(Integer id) {
        Map map = new HashMap(3);
        TbSocialInsurance sheBaoById = getSheBaoById(id);
        TbCustomer customerByIdCard = customerService.getCustomerByIdCard(sheBaoById.getIdCard());
        TbCompany companyById = companyClientService.getCompanyById(customerByIdCard.getCompanyId());

        map.put("socialInsurance", sheBaoById);
        map.put("customer", customerByIdCard);
        map.put("company", companyById);
        return map;
    }

    @Override
    public List<TbSystemDict> getSheBaoPayStatus() {
        return dataDictService.getDataDictByName("社保缴费状态");
    }

    @Override
    public List<TbSystemDict> getDictBySheBaoPay() {
        return dataDictService.getDataDictByName("社保缴费状态");
    }

    @Override
    public Page<TbSocialInsuranceRecord> socialInsuranceRecordQueryByOperation(Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation) {
        //查询社保缴费状态已交的状态值
        String alreadyPay = dataDictService.getDataDictValueByNameAndLabel("社保缴费状态", "删除");
        StringBuffer hql = new StringBuffer("from TbSocialInsuranceRecord where status != " + alreadyPay);
        String oldNameQuery = "";
        String oldIdCardQuery = "";
        String oldSbCardQuery = "";
        if(!"".equals(socialInsuranceOperation.getNameQuery()) && socialInsuranceOperation.getNameQuery() != null){
            //从客户表中获取值
            oldNameQuery = socialInsuranceOperation.getNameQuery();
            socialInsuranceOperation.setNameQuery("%" + oldNameQuery + "%");
            hql.append(" and name like :nameQuery ");

        }
        if(!"".equals(socialInsuranceOperation.getIdCardQuery()) && socialInsuranceOperation.getIdCardQuery() != null){
            oldIdCardQuery = socialInsuranceOperation.getIdCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldIdCardQuery + "%");
            hql.append(" and idCard like :idCardQuery");
        }
        if(!"".equals(socialInsuranceOperation.getSbCardQuery()) && socialInsuranceOperation.getSbCardQuery() != null){
            oldSbCardQuery = socialInsuranceOperation.getSbCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldSbCardQuery + "%");
            hql.append(" and idCard like :sbCardQuery");
        }
        //查询总条数
        Long count = sheBaoDao.socialInsuranceQueryCountByOperation("select count(1) " + hql.toString(), socialInsuranceOperation);
        Page<TbSocialInsuranceRecord> page = new Page(currentPage, 3, count);
        socialInsuranceOperation.setStartIndex(page.getStartIndex());
        socialInsuranceOperation.setPageSize(page.getPageSize());
        List<TbSocialInsuranceRecord> socialInsuranceList = sheBaoDao.socialInsuranceQueryByOperationPaging(hql.toString(), socialInsuranceOperation);
        page.setDataList(socialInsuranceList);

        socialInsuranceOperation.setNameQuery(oldNameQuery);
        socialInsuranceOperation.setIdCardQuery(oldIdCardQuery);
        socialInsuranceOperation.setSbCardQuery(oldSbCardQuery);

        return page;
    }

    @Override
    public void shebaoRecordDelete(Integer id, Integer updateBy) {
        //获取社保缴费状态  删除的状态值
        String deleteStatus = dataDictService.getDataDictValueByNameAndLabel("社保缴费状态", "删除");
        TbSocialInsuranceRecord sheBaoRecordById = sheBaoDao.getSheBaoRecordById(id);
        sheBaoRecordById.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sheBaoRecordById.setUpdateBy(updateBy);
        sheBaoRecordById.setStatus(deleteStatus);
        sheBaoDao.shebaoRecordUpdate(sheBaoRecordById);
    }

    @Override
    public void shebaoTemplateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Download.fileTemplateDownload(name, request, response);
    }

    @Override
    public void dataOutOfExcel(TbSocialInsuranceOperation socialInsuranceOperation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String shebaoStatus = dataDictService.getDataDictValueByNameAndLabel("社保状态", "正常");
        StringBuffer hql = new StringBuffer(" from TbSocialInsurance where status = " + shebaoStatus);
        String oldNameQuery = "";
        String oldIdCardQuery = "";
        String oldSbCardQuery = "";
        if(!"".equals(socialInsuranceOperation.getNameQuery()) && socialInsuranceOperation.getNameQuery() != null){
            //从客户表中获取值
            oldNameQuery = socialInsuranceOperation.getNameQuery();
            socialInsuranceOperation.setNameQuery("%" + oldNameQuery + "%");
            hql.append(" and name like :nameQuery ");

        }
        if(!"".equals(socialInsuranceOperation.getIdCardQuery()) && socialInsuranceOperation.getIdCardQuery() != null){
            oldIdCardQuery = socialInsuranceOperation.getIdCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldIdCardQuery + "%");
            hql.append(" and idCard like :idCardQuery");
        }
        if(!"".equals(socialInsuranceOperation.getSbCardQuery()) && socialInsuranceOperation.getSbCardQuery() != null){
            oldSbCardQuery = socialInsuranceOperation.getSbCardQuery();
            socialInsuranceOperation.setIdCardQuery("%" + oldSbCardQuery + "%");
            hql.append(" and idCard like :sbCardQuery");
        }

        List<TbSocialInsurance> tbSocialInsurances = sheBaoDao.socialInsuranceQueryByOperation(hql.toString(), socialInsuranceOperation);
        socialInsuranceOperation.setNameQuery(oldNameQuery);
        socialInsuranceOperation.setIdCardQuery(oldIdCardQuery);
        socialInsuranceOperation.setSbCardQuery(oldSbCardQuery);
        //封装为List<List>
        Map map = new HashMap(2);
        map.put("fileName", "社保信息模板");
        List<List> lists = new ArrayList<>();
        for (TbSocialInsurance socialInsurance : tbSocialInsurances){
            List list = new ArrayList();
            list.add(socialInsurance.getName());
            list.add(socialInsurance.getIdCard());
            list.add(socialInsurance.getSbCard());
            list.add(socialInsurance.getBasePay());
            list.add(socialInsurance.getMustPay());
            list.add(socialInsurance.getPersonRatio());
            list.add(socialInsurance.getCompanyRatio());
            list.add(socialInsurance.getYangLao());
            list.add(socialInsurance.getYiLiao());
            list.add(socialInsurance.getGongShang());
            list.add(socialInsurance.getShiYe());
            list.add(socialInsurance.getShengYu());
            list.add(socialInsurance.getPayDate());
            list.add(socialInsurance.getProxyFee());
            list.add(socialInsurance.getStatus());
            list.add(socialInsurance.getRemark());
            lists.add(list);
        }
        map.put("dataList", lists);
        DataOutOfExcel.dataOut(map, request, response);
    }

    @Override
    public void shebaoInOfExcel(InputStream inputStream, Integer createBy) throws IOException, InvalidFormatException, ParseException {
        List<Map<Object, Object>> list = ExcelUpload.parseExcel(inputStream);
        for (Map map : list){
            TbSocialInsurance socialInsurance = new TbSocialInsurance();
            socialInsurance.setId(1);
            socialInsurance.setName(String.valueOf(map.get("客户名称")));
            socialInsurance.setIdCard(String.valueOf(map.get("身份证号")));
            socialInsurance.setSbCard(String.valueOf(map.get("社保卡号")));
            socialInsurance.setBasePay(Double.valueOf(String.valueOf(map.get("缴费基数"))));
            socialInsurance.setMustPay(Double.valueOf(String.valueOf(map.get("应缴金额"))));
            socialInsurance.setPersonRatio(String.valueOf(map.get("个人比率")));
            socialInsurance.setCompanyRatio(String.valueOf(map.get("单位比率")));
            socialInsurance.setYangLao(Double.valueOf(String.valueOf(map.get("养老保险"))));
            socialInsurance.setYiLiao(Double.valueOf(String.valueOf(map.get("医疗保险"))));
            socialInsurance.setGongShang(Double.valueOf(String.valueOf(map.get("工伤保险"))));
            socialInsurance.setShiYe(Double.valueOf(String.valueOf(map.get("失业保险"))));
            socialInsurance.setShengYu(Double.valueOf(String.valueOf(map.get("生育保险"))));
            socialInsurance.setPayDate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(map.get("预交款日"))));
            socialInsurance.setProxyFee(Double.valueOf(String.valueOf(map.get("代理费用"))));
            socialInsurance.setStatus(String.valueOf(map.get("社保状态")));
            socialInsurance.setRemark(String.valueOf(map.get("备注信息")));
            socialInsurance.setCreateBy(createBy);
            socialInsurance.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //添加
            sheBaoDao.shebaoAdd(socialInsurance);
        }
    }
}
