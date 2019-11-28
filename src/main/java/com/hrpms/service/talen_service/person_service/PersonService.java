package com.hrpms.service.talen_service.person_service;

import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import com.hrpms.utils.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.talen_service.person > PersonService
 * @description TODO
 * @create 2019/11/25  17:39
 * @versiion 1.0
 * @Description:
 */
public interface PersonService {

    /**
     * 多条件分页查询人才信息
     * @param 
     * @return 
     **/
    Page<TbPerson> personList(Integer currentPage, TbPersonOperation personOperation);

    /**
     * 根据id得到详细信息
     * @param 
     * @return 
     **/
    TbPerson personDetailById(Integer id);

    /**
     * 添加人才信息
     * @param
     * @return
     **/
    void personAdd(MultipartFile resumeFile, TbPerson person, Integer createBy, HttpServletRequest request) throws IOException;

    /**
     * 简历预览
     * @param
     * @return
     **/
    void resumeUrlPreview(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 得到字典信息
     * @param
     * @return
     **/
    List<TbSystemDict> getDictsByName(String name);
    /**
     * 修改客户信息
     * @param
     * @return 
     **/
    void personUpdate(TbPerson person, MultipartFile file, Integer updateBy, HttpServletRequest request, HttpServletResponse response) throws IOException;
    /**
     * 删除
     * @param
     * @return
     **/
    void personDelete(Integer id, Integer updateBy);
    /**
     * 获取所有客户名称和id
     * @param
     * @return
     **/
    List<Object[]> getAllIdAndName();


    /**
     * 通过工资范围得到求职信息
     * @param
     * @return
     **/
    List<TbPerson> getPersonsByPrice(Double price);

}
