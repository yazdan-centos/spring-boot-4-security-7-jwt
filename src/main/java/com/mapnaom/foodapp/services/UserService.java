package com.mapnaom.foodapp.services;

import com.mapnaom.foodapp.dtos.UserDto;
import com.mapnaom.foodapp.entities.User;
import com.mapnaom.foodapp.mappers.UserMapper;
import com.mapnaom.foodapp.repository.UserRepository;
import com.mapnaom.foodapp.searchForms.UserSearchForm;
import com.mapnaom.foodapp.specifications.UserSpecification;
import com.mapnaom.foodapp.utils.ExcelUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExcelUtil excelUtil;

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userMapper.toEntity(userDto); // Map updated fields
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public Page<UserDto> searchUsers(UserSearchForm searchForm, int page, int size, String sortBy, String order) {
        Specification<User> specification = UserSpecification.fromSearchForm(searchForm);
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return userRepository.findAll(specification, pageRequest).map(userMapper::toDto);
    }

    public byte[] exportUsersToExcel() throws IOException {
        List<User> users = userRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Users");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("email");
            headerRow.createCell(2).setCellValue("user Name");
            headerRow.createCell(3).setCellValue("Email");

            int rowIdx = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getEmail());
                row.createCell(2).setCellValue(user.getUsername());
                row.createCell(3).setCellValue(user.getEmail());
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }

    public void importUsersFromExcel(MultipartFile file) throws IOException, ExcelUtil.ExcelProcessingException {
        List<User> users = excelUtil.processExcel(file, User.class);
        userRepository.saveAll(users);
    }
}
