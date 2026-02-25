package com.mapnaom.foodapp.utils;
import com.mapnaom.foodapp.exceptions.ExcelProcessingException;

import jakarta.validation.constraints.NotNull;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExcelUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final int DEFAULT_SHEET_INDEX = 0;
    private static final int DEFAULT_HEADER_ROW_INDEX = 0;

    // ==================== IMPORT METHODS ====================

    /**
     * Process Excel file and map to list of DTOs
     */
    public <T> List<T> processExcel(MultipartFile file, Class<T> clazz, int sheetIndex, int headerRowIndex)
            throws IOException, ExcelProcessingException {

        validateFileInput(file);

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = createWorkbook(inputStream, file.getOriginalFilename());
            Sheet sheet = getSheet(workbook, sheetIndex);
            Map<String, Integer> headerMap = validateAndMapHeaders(sheet, clazz, headerRowIndex);
            return processDataRows(sheet, clazz, headerMap, headerRowIndex + 1);
        }
    }

    public <T> List<T> processExcel(MultipartFile file, Class<T> clazz)
            throws IOException, ExcelProcessingException {
        return processExcel(file, clazz, DEFAULT_SHEET_INDEX, DEFAULT_HEADER_ROW_INDEX);
    }

    public <T> List<T> processExcel(InputStream inputStream, String filename, Class<T> clazz,
                                    int sheetIndex, int headerRowIndex)
            throws IOException, ExcelProcessingException {

        Workbook workbook = createWorkbook(inputStream, filename);
        Sheet sheet = getSheet(workbook, sheetIndex);
        Map<String, Integer> headerMap = validateAndMapHeaders(sheet, clazz, headerRowIndex);
        return processDataRows(sheet, clazz, headerMap, headerRowIndex + 1);
    }

    // ==================== EXPORT METHODS ====================

    /**
     * Generate Excel file from list of DTOs
     */
    public <T> byte[] generateExcel(List<T> data, Class<T> clazz) {
        return generateExcel(data, clazz, "Data", false, null);
    }

    public <T> byte[] generateExcel(List<T> data, Class<T> clazz, String sheetName) {
        return generateExcel(data, clazz, sheetName, false, null);
    }

    public <T> byte[] generateExcel(List<T> data, Class<T> clazz, String sheetName,
                                    boolean rightToLeft, Map<String, String> customHeaders) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            List<Field> fields = getAllFields(clazz);
            validateFields(fields, clazz);

            Sheet sheet = createStyledSheet(workbook, sheetName, rightToLeft);
            ExcelStyleUtil styleUtil = new ExcelStyleUtil(workbook);

            createHeaderRow(sheet, fields, styleUtil, customHeaders);
            createDataRows(sheet, data, fields, styleUtil, rightToLeft);
            autoSizeColumns(sheet, fields.size());

            workbook.write(out);
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating Excel file: " + e.getMessage(), e);
        }
    }

    // ... rest of file omitted for brevity ...
}

