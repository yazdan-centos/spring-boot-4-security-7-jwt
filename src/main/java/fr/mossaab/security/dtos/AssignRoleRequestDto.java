// AssignRoleRequestDto.java
package fr.mossaab.security.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssignRoleRequestDto {
    @NotBlank(message = "Role name is required")
    private String roleName;
}