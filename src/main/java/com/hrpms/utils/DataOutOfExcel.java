package com.hrpms.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.utils > DataOutOfExcel
 * @description TODO
 * @create 2019/11/22  11:22
 * @versiion 1.0
 * @Description:数据导出
 *  fileName:模板名， dataLIst:数据，   得和模板中的字段顺序一致
 *  格式：map{"fileName":String, "dataList":List<List<data 对应fileHead中的数据>>}
 */
public class DataOutOfExcel {
    public static void dataOut(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //模板流
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream(new File(request.getRealPath("template\\" + map.get("fileName") + ".xls"))));
        Workbook workbook = WorkbookFactory.create(bf);
        Sheet sheet = workbook.getSheetAt(0);
        //数据
        List<List> data = (List) map.get("dataList");
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            for (int j = 0; j < data.get(i).size(); j++) {
                row.createCell(j + 1).setCellValue(String.valueOf(data.get(i).get(j)));
            }
        }
        response.setContentType("application/octer-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + DownLoadMessyCode.setDownloadFileName(String.valueOf(map.get("fileName")).replace("模板", "数据"), request) + ".xls");
        ServletOutputStream outputStream = response.getOutputStream();

        workbook.write(outputStream);
        outputStream.close();

    }
}
