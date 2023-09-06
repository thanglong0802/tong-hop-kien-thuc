package com.dagoras.edu.api.service.impl;

import com.dagoras.edu.api.entity.VoucherDetails;
import com.dagoras.edu.api.repository.VoucherDetailsRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

@Service
public class VoucherDetailsService {
    private final VoucherDetailsRepository voucherDetailsRepository;

    public VoucherDetailsService(VoucherDetailsRepository voucherDetailsRepository) {
        this.voucherDetailsRepository = voucherDetailsRepository;
    }

    public void voucherDetailsImportFileExcel(String filePath) throws IOException {
        List<VoucherDetails> voucherDetailsList = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(filePath));

        // Get workbook
        Workbook workbook = new XSSFWorkbook(inputStream);

        // Get sheet đầu tiên trong workbook
        Sheet sheet = workbook.getSheetAt(0);

//        // Get all rows
//        Iterator<Row> rowIterator = sheet.iterator();
//        while(rowIterator.hasNext()) {
//            Row nextRow = rowIterator.next();
//            if (nextRow.getRowNum() == 0) {
//                continue;
//            }
//
//            // Get all cells
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//
//            // Read cells and set value for VoucherDetails Object
//            VoucherDetails voucherDetails = new VoucherDetails();
//            while (cellIterator.hasNext()) {
//                // Read cell
//                Cell cell = cellIterator.next();
//                Object cellValue = getCell
//            }
//        }

        // Lặp qua từng hàng trong sheet và từng ô trong hàng để hiển thị dữ liệu
        VoucherDetails voucherDetails = new VoucherDetails();
        for (Row row : sheet) {
            for (Cell cell : row) {
                // Kiểm tra kiểu dữ liệu của ô
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    default:
                        System.out.print("");
                }
                System.out.print("\t"); // Tạo một tab để ngăn cách giữa các ô
            }
            System.out.println(); // Xuống dòng khi kết thúc một hàng
        }

        // Đóng luồng đọc tệp Excel
        inputStream.close();
        workbook.close();
    }
}
