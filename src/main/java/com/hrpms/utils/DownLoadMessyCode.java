package com.hrpms.utils;


import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.utils > DownLoadMessyCode
 * @description TODO
 * @create 2019/11/22  10:07
 * @versiion 1.0
 * @Description:解决下载乱码
 */
public class DownLoadMessyCode {
    public static String setDownloadFileName(String fileName,HttpServletRequest request) throws  Exception {
        try {
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {//火狐浏览器
                fileName = "=?UTF-8?B?"+(new String(Base64.encode(fileName.getBytes("UTF-8")))) + "?=";
            } else {				//其它浏览器
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

}
