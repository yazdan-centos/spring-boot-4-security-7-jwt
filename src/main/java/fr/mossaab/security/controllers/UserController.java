package fr.mossaab.security.controllers;

import fr.mossaab.security.dtos.UserDto;
import fr.mossaab.security.searchForms.UserSearchForm;
import fr.mossaab.security.services.UserService;
import fr.mossaab.security.utils.ExcelUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserDto>> searchUsers(UserSearchForm searchForm,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "ASC") String order) {
        Page<UserDto> users = userService.searchUsers(searchForm, page, size, sortBy, order);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportUsersToExcel() throws IOException {
        byte[] excelData = userService.exportUsersToExcel();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=users.xlsx")
                .body(excelData);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/import")
    public ResponseEntity<Void> importUsersFromExcel(@RequestParam("file") MultipartFile file) throws IOException, ExcelUtil.ExcelProcessingException {
        userService.importUsersFromExcel(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
