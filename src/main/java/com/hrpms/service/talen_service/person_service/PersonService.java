package com.hrpms.service.talen_service.person_service;

import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import com.hrpms.utils.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * 添加人才信息
     * @param
     * @return
     **/
    void personAdd(MultipartFile resumeFile, TbPerson person, Integer createBy, HttpServletRequest request) throws IOException;



    List<TbSystemDict> getDictsByName(String name);
}
