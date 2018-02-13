package com.google.base.util;


import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by zengxiangyuan on 2018/2/12.
 * Description
 */
public class ImportExcelUtil {

    private final static String EXCEL_2003_L = ".xls";    //2003- 版本的excel
    private final static String EXCEL_2007_U = ".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     *
     * @param is
     * @param  fileName
     * @return
     * @throws IOException
     */
    public static List<List<Object>> getBankListByExcel(InputStream is, String fileName) throws Exception {

        // 创建Excel工作薄
        Workbook work = getWorkbook(is, fileName);
        if (Objects.isNull(work)) {
            throw new Exception("创建Excel工作薄为空！");
        }

        Sheet sheet;
        Row row;
        Cell cell;

        List<List<Object>> list = new ArrayList<>();
        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            // 遍历当前sheet中的所有行
            for (int j = 3; j < sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j || StringUtils.isEmpty(row.getCell(1).getStringCellValue())) {
                    continue;
                }

                // 遍历所有的列
                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    if (!Objects.isNull(cell)) {
                        li.add(getCellValue(cell));
                    }
                }
                list.add(li);
            }
        }
        is.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    private static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (Objects.equals(fileType, EXCEL_2003_L)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (Objects.equals(fileType, EXCEL_2007_U)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        if (!Objects.isNull(cell)) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                        value = df.format(cell.getNumericCellValue());
                    } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdf.format(cell.getDateCellValue());
                    } else {
                        value = df2.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    break;
            }
        }
        return value;
    }


}