package io.github.gtang94.springboot.office.service;

import cn.hutool.core.util.RandomUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoSizeColumnService {

    public void download(String filename) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFCellStyle cellStyle = cellStyle(xssfWorkbook);

        XSSFSheet sheet = xssfWorkbook.createSheet();

        XSSFRow head = sheet.createRow(0);
        String[] heads = {"1111111111", "222222222", "3333333333333333", "44444444444", "5555555555", "66666666666", "777777777777", "88888888888888",
                "99999999999", "101010101010100110", "121212121212121212121212", "1414141414141414141414141414 "};
        for (int i = 0; i < heads.length; i++) {
            createCell(head, i, heads[i], cellStyle);
        }

        for (int i = 0; i < 10000; i++) {

            List<String> data = new ArrayList<>();
            int col1 = 0;
            do {
                data.add(RandomUtil.randomString(16));
            } while (col1++ < 11);

            XSSFRow sheetRow = sheet.createRow(i);
            int col2 = 0;
            do {
                createCell(sheetRow, col2, data.get(col2), cellStyle);

                sheet.autoSizeColumn(i);
            } while (col2++ < 11);

        }

        xssfWorkbook.write(fileOutputStream);


    }

    public void createCell(XSSFRow sheetRow, int column, String value, XSSFCellStyle cellStyle) {
        XSSFCell cell = sheetRow.createCell(column);
        cell.setCellValue(value);
    }

    public XSSFCellStyle cellStyle(XSSFWorkbook xssfWorkbook) {
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }
}
