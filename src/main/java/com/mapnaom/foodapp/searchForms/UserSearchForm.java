package com.mapnaom.foodapp.searchForms;

import lombok.Data;

@Data
public class UserSearchForm {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Boolean enabled;
}
