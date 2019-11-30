package com.hrpms.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
    
    /**
     * 统计数据导出
     * @param Map key:fileName val:导出文件名; key:sheetName val:导出表格空间名； key:name  value:头List  ；key:dataList, value: List<List>
     * @return
     **/
    public static void dataCountOut(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet((String) map.get("sheetName"));
        //表头
        List<String> name = (List<String>) map.get("headName");
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("编号");
        for (int i = 0; i < name.size(); i++) {
            HSSFCell cell = row1.createCell(i + 1);
            cell.setCellValue(name.get(i));
        }
        //数据
        List<List> data = (List<List>) map.get("dataList");
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            for (int j = 0; j < data.get(i).size(); j++) {
                row.createCell(j + 1).setCellValue(String.valueOf(data.get(i).get(j)));
            }
        }
        response.setContentType("application/octer-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + DownLoadMessyCode.setDownloadFileName(String.valueOf(map.get("fileName")), request) + ".xls");
        ServletOutputStream outputStream = response.getOutputStream();

        hssfWorkbook.write(outputStream);
        outputStream.close();
    }
}
