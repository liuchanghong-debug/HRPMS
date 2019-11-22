package com.hrpms.utils;


import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.utils > Download
 * @description TODO
 * @create 2019/11/22  11:06
 * @versiion 1.0
 * @Description:下载模板
 */
public class Download {
    public static void fileTemplateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File(request.getRealPath("template\\" + name + ".xls"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        response.setHeader("Content-Disposition", "attachement;filename="+ DownLoadMessyCode.setDownloadFileName(name, request) + ".xls");
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(bufferedInputStream, outputStream);
    }
}
