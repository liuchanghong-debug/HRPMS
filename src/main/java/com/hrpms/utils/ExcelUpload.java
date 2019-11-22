package com.hrpms.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.utils > ExcelUpload
 * @description TODO
 * @create 2019/11/22  15:10
 * @versiion 1.0
 * @Description: 返回值格式
 * List<Map<表头字段, 值>>  Map为每一行的数据，不包括表头   解析时需要根据键来判断，键为excel表中的表头字段  表头字段和模板中的字段相同
 */
public class ExcelUpload {
    public static List<Map<Object, Object>> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException {
        List<Map<Object, Object>> list = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        //得到表空间
        Sheet sheetAt = workbook.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        //得到表头
        Row rowHead = sheetAt.getRow(0);
        short rowHeadLength = rowHead.getLastCellNum();
        List headDate = new ArrayList();
        for (int i = 1; i < rowHeadLength; i++) {
            Cell cell = rowHead.getCell(i);
            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date dateCellValue = cell.getDateCellValue();
                    String format = new SimpleDateFormat("yyyy-MM-dd").format(dateCellValue);
                    headDate.add(format);
                    continue;
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
            headDate.add(cell.getStringCellValue());
        }

        //忽略编号
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheetAt.getRow(i);
            short lastCellNum = row.getLastCellNum();
            Map<Object, Object> map = new HashMap<>();
            for (int j = 1; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        Date dateCellValue = cell.getDateCellValue();
                        String format = new SimpleDateFormat("yyyy-MM-dd").format(dateCellValue);
                        map.put(headDate.get(j - 1), format);
                        continue;
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                }
                map.put(headDate.get(j - 1), cell.getStringCellValue());
            }
            list.add(map);
        }
        return list;
    }
}
