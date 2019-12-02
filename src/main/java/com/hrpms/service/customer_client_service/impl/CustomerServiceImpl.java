package com.hrpms.service.customer_client_service.impl;

import com.hrpms.dao.customer_client_dao.CustomerDao;
import com.hrpms.pojo.*;
import com.hrpms.pojo.operaton_select.TbCustomerOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.service.customer_client_service.CustomerService;
import com.hrpms.service.gongjijin_manager_service.TbAccumulationFundService;
import com.hrpms.service.salary_manager_service.TbSalaryService;
import com.hrpms.service.shebao_manager_service.SheBaoService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.service.talen_service.laowu_service.LaoWuService;
import com.hrpms.service.talen_service.person_service.PersonService;
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
 * @package HRPMS > com.hrpms.service.customer_service.impl > CustomerServiceImpl
 * @description TODO
 * @create 2019/11/22  16:55
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private DataDictService dataDictService;
    @Autowired
    private CompanyClientService companyClientService;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private TbSalaryService tbSalaryService;
    @Autowired
    private SheBaoService sheBaoService;
    @Autowired
    private TbAccumulationFundService tbAccumulationFundService;
    @Autowired
    private PersonService personService;
    @Autowired
    private LaoWuService laoWuService;


    @Override
    public Page<TbCustomer> customerAllByOperationAndPaging(Integer currentPage, TbCustomerOperation customerOperation) {
        StringBuffer hql = new StringBuffer("from TbCustomer where delFlag = 0 ");

        //用于还原数据
        String oldNameQuery = "";
        String oldIdCardQuery = "";
        //拼接hql语句
        if(customerOperation.getNameQuery() != null && !"".equals(customerOperation.getNameQuery())){
            oldNameQuery = customerOperation.getNameQuery();
            customerOperation.setNameQuery("%" + oldNameQuery + "%");
            hql.append(" and name like :nameQuery ");
        }
        if(customerOperation.getIdCardQuery() != null && !"".equals(customerOperation.getIdCardQuery())){
            oldIdCardQuery = customerOperation.getIdCardQuery();
            customerOperation.setIdCardQuery("%" + oldIdCardQuery + "%");
            hql.append(" and idCard like :idCardQuery ");
        }
        if(customerOperation.getCompanyIdQuery() != null && !"".equals(customerOperation.getCompanyIdQuery())){
            hql.append(" and companyId = :companyIdQuery ");
        }
        //查询总条数
        Long customerCount = customerDao.customerCountByOperation("select count(1) " + hql.toString(), customerOperation);
        //分页信息
        Page<TbCustomer> page = new Page<>(currentPage, 3, customerCount);

        customerOperation.setStartIndex(page.getStartIndex());
        customerOperation.setPageSize(page.getPageSize());
        //最终数据
        List<TbCustomer> tbCustomers = customerDao.customerAllByOperationAndPaging(hql.toString(), customerOperation);
        page.setDataList(tbCustomers);
        //还原数据
        customerOperation.setNameQuery(oldNameQuery);
        customerOperation.setIdCardQuery(oldIdCardQuery);

        return page;
    }

    @Override
    public TbCustomer customerById(Integer id) {
        return customerDao.customerById(id);
    }

    @Override
    public void customerUpdate(TbCustomer customer, Integer updateBy) throws IOException {
        //从数据库中获取值  非页面字段为 创建时间，创建者，删除标志
        TbCustomer byId = customerDao.customerById(customer.getId());
        String idCard = byId.getIdCard();
        //如果代发工资， 代缴社保， 代缴公积金 为空，默认值为1；
        if(customer.getIsSalary() == null && !"".equals(customer.getIsSalary())){
            customer.setIsSalary("1");
        }
        if(customer.getIsSheBao() == null && !"".equals(customer.getIsSheBao())){
            customer.setIsSheBao("1");
        }
        if(customer.getIsGongJiJin() == null && !"".equals(customer.getIsGongJiJin())){
            customer.setIsGongJiJin("1");
        }
        //修改时间
        byId.setUpdateBy(updateBy);
        byId.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        byId.setIsSalary(customer.getIsSalary());
        byId.setIsSheBao(customer.getIsSheBao());
        byId.setIsGongJiJin(customer.getIsGongJiJin());
        byId.setName(customer.getName());
        byId.setIdCard(customer.getIdCard());
        byId.setSex(customer.getSex());
        byId.setBirthday(customer.getBirthday());
        byId.setPhone(customer.getPhone());
        byId.setEmail(customer.getEmail());
        byId.setAddress(customer.getAddress());
        byId.setZipCode(customer.getZipCode());
        byId.setSchool(customer.getSchool());
        byId.setSpecialty(customer.getSpecialty());
        byId.setGraduation(customer.getGraduation());
        byId.setCompanyId(customer.getCompanyId());
        byId.setCustomerType(customer.getCustomerType());
        byId.setRemark(customer.getRemark());
        byId.setStatus(customer.getStatus());
        customerDao.customerUpdate(byId);


        //修改工资表中的信息
        TbSalary tbSalaryByIdCard = tbSalaryService.getTbSalaryByIdCard(idCard);
        if(tbSalaryByIdCard !=  null){
            tbSalaryByIdCard.setName(byId.getName());
            tbSalaryByIdCard.setIdCard(byId.getIdCard());
            tbSalaryService.updateSalaryById(tbSalaryByIdCard);
        }
        //修改社保记录表中的信息
        TbSocialInsurance sheBaoByIdCard = sheBaoService.getSheBaoByIdCard(idCard);
        if(sheBaoByIdCard != null){
            sheBaoByIdCard.setName(byId.getName());
            sheBaoByIdCard.setIdCard(byId.getIdCard());
            sheBaoService.shebaoUpdate(sheBaoByIdCard, null);
        }
        //修改社保缴费表中的信息
        TbSocialInsuranceRecord sheBaoRecordByIdCard = sheBaoService.getSheBaoRecordByIdCard(idCard);
        if(sheBaoRecordByIdCard != null){
            sheBaoRecordByIdCard.setName(byId.getName());
            sheBaoRecordByIdCard.setIdCard(byId.getIdCard());
            sheBaoService.shebaoRecordUpdate(sheBaoRecordByIdCard, null);
        }
        //修改公积金表中的信息
        TbAccumulationFund accumulationFundByIdCard = tbAccumulationFundService.getAccumulationFundByIdCard(idCard);
        if(accumulationFundByIdCard != null){
            accumulationFundByIdCard.setName(byId.getName());
            accumulationFundByIdCard.setIdCard(byId.getIdCard());
            tbAccumulationFundService.updateAccumulationById(accumulationFundByIdCard);
        }
        //修改人才表中的信息  获取
        TbPerson tbPerson = personService.PersonByIdCard(idCard);
        if(tbPerson != null){
            tbPerson.setName(byId.getName());
            tbPerson.setIdCard(byId.getIdCard());
            personService.personUpdate(tbPerson, null, updateBy, null, null);
        }
        //修改劳务表中的信息
        TbPersonJob tbPersonJob = laoWuService.personJobByIdCard(idCard);
        if(tbPersonJob != null){
            tbPersonJob.setName(byId.getName());
            tbPersonJob.setIdCard(byId.getIdCard());
            laoWuService.personJobUpdate(null, tbPersonJob, null, null, updateBy);
        }

    }

    @Override
    public List<TbSystemDict> getDataDictByName(String name) {
        return dataDictService.getDataDictByName(name);
    }

    @Override
    public List<TbCompany> getAllCompanyOfIdAndName() {
        List<Object[]> allCompanyOfIdAndName = companyClientService.getAllCompanyOfIdAndName();
        List<TbCompany> list = new ArrayList<>();

        for (Object[] objects : allCompanyOfIdAndName){
            TbCompany tbCompany = new TbCompany();
            tbCompany.setId((Integer) objects[0]);
            tbCompany.setName((String) objects[1]);
            list.add(tbCompany);
        }
        return list;
    }

    @Override
    public void customerToAdd(TbCustomer customer) {
        //设置id自增
        customer.setId(1);
        //如果代发工资， 代缴社保， 代缴公积金 为空，默认值为1；
        if(customer.getIsSalary() == null && !"".equals(customer.getIsSalary())){
            customer.setIsSalary("1");
        }
        if(customer.getIsSheBao() == null && !"".equals(customer.getIsSheBao())){
            customer.setIsSheBao("1");
        }
        if(customer.getIsGongJiJin() == null && !"".equals(customer.getIsGongJiJin())){
            customer.setIsGongJiJin("1");
        }
        //创建时间
        customer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //删除标志默认0 正常
        customer.setDelFlag("0");

        customerDao.customerToAdd(customer);
    }

    @Override
    public void customerDelete(Integer id, Integer updateBy) {
        TbCustomer customer = customerDao.customerById(id);
        customer.setUpdateBy(updateBy);
        customer.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        customer.setDelFlag("1");
        //调用修改方法
        customerDao.customerUpdate(customer);
    }

    @Override
    public void customerTemplateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Download.fileTemplateDownload(name, request, response);
    }

    @Override
    public void customerDataOutOfExcel(TbCustomerOperation customerOperation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer hql = new StringBuffer("from TbCustomer where delFlag = 0 ");
        //拼接hql语句
        if(customerOperation.getNameQuery() != null && !"".equals(customerOperation.getNameQuery())){
            customerOperation.setNameQuery("%" + customerOperation.getNameQuery() + "%");
            hql.append(" and name like :nameQuery ");
        }
        if(customerOperation.getIdCardQuery() != null && !"".equals(customerOperation.getIdCardQuery())){
            customerOperation.setIdCardQuery("%" + customerOperation.getIdCardQuery() + "%");
            hql.append(" and idCard like :idCardQuery ");
        }
        if(customerOperation.getCompanyIdQuery() != null && !"".equals(customerOperation.getCompanyIdQuery())){
            hql.append(" and companyId = :companyIdQuery ");
        }
        List<TbCustomer> tbCustomers = customerDao.costomerByOperationOutOfExcel(hql.toString(), customerOperation);
        //处理数据  和excel 表中字段顺序一致
        List dataList = new ArrayList();
        for (TbCustomer customer : tbCustomers){
            List list = new ArrayList();
            list.add(customer.getName());
            list.add(customer.getIdCard());
            list.add(customer.getSex());
            list.add(customer.getBirthday());
            list.add(customer.getPhone());
            list.add(customer.getEmail());
            list.add(customer.getAddress());
            list.add(customer.getZipCode());
            list.add(customer.getSchool());
            list.add(customer.getSpecialty());
            list.add(customer.getGraduation());
            list.add(customer.getCompanyId());
            list.add(customer.getCustomerType());
            list.add(customer.getIsSalary());
            list.add(customer.getIsSheBao());
            list.add(customer.getIsGongJiJin());
            list.add(customer.getStatus());
            list.add(customer.getRemark());
            dataList.add(list);
        }
        Map map = new HashMap(2);
        map.put("fileName", "个人客户模板");
        map.put("dataList", dataList);

        DataOutOfExcel.dataOut(map, request, response);
    }

    @Override
    public void customerUploadOfExcel(InputStream inputStream, Integer createBy) throws IOException, InvalidFormatException, ParseException {
        List<Map<Object, Object>> list = ExcelUpload.parseExcel(inputStream);
        for (Map map : list){
            TbCustomer customer = new TbCustomer();
            customer.setId(1);
            customer.setName((String) map.get("客户名称"));
            customer.setIdCard((String) map.get("身份证号码"));
            customer.setSex((String) map.get("客户性别"));
            customer.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get("出生年月")));
            customer.setPhone((String) map.get("手机号码"));
            customer.setEmail((String) map.get("电子邮件"));
            customer.setAddress((String) map.get("现在住址"));
            customer.setZipCode((String) map.get("邮政编码"));
            customer.setSchool((String) map.get("毕业学校"));
            customer.setSpecialty((String) map.get("所学专业"));
            customer.setGraduation((String) map.get("毕业时间"));
            customer.setCompanyId(Integer.valueOf((String) map.get("所属公司")));
            customer.setCustomerType((String) map.get("客户类别"));
            customer.setIsSalary((String) map.get("代发工资"));
            customer.setIsSheBao((String) map.get("代缴社保"));
            customer.setIsGongJiJin((String) map.get("代缴公积金"));
            customer.setStatus((String) map.get("状态"));
            customer.setRemark((String) map.get("备注"));
            customer.setCreateTime(new Timestamp(System.currentTimeMillis()));
            customer.setDelFlag("0");

            //向数据库添加数据
            customerDao.customerToAdd(customer);
        }
    }

    @Override
    public List<Object[]> getDataOfNotInList(List list) {
        if(list.isEmpty()){
            String hql = "select id, name from TbCustomer where delFlag = 0";
            return customerDao.getData(hql);
        }else {
            String hql = "select id, name from TbCustomer where idCard not in :idCard and delFlag = 0";
            return customerDao.getDataOfNotInList(hql, list);
        }
    }

    @Override
    public TbCustomer getCustomerByIdCard(String idCard) {
        String hql = "from TbCustomer where idCard = ?";
        return customerDao.getCustomerByIdCard(hql, idCard);
    }

    @Override
    public List<TbCustomer> selectAllCustomerName() {
        return customerDao.selectAllCustomerName();
    }

    @Override
    public boolean customerIdCardIsOnly(String idCard) {
        String hql = "from TbCustomer where idCard = ?";
        Object object = customerDao.customerIdCardIsOnly(hql, idCard);
        if(object == null){
            return  true;
        }else {
            return false;
        }
    }

    @Override
    public boolean customerIdCardIsOnlyUpdate(Integer id, String idCard) {
        String hql = "from TbCustomer where id != ? and idCard = ?";
        Object object = customerDao.customerIdCardIsOnlyUpdate(hql, id, idCard);
        if(object == null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public TbCustomer selectCustomerByEmail(String email) {
        return customerDao.selectCustomerByEmail(email);
    }

    @Override
    public TbCustomer selectCustomerByPhone(String phone) {
        return customerDao.selectCustomerByPhone(phone);
    }

    @Override
    public List<TbCustomer> normalCustomerOfStatus(String hql, List<String> idCardList, List<String> statusList) {
        return customerDao.normalCustomerOfStatus(hql, idCardList, statusList);
    }

}
