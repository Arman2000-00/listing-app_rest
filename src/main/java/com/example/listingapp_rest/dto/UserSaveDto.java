package com.example.listingapp_rest.dto;

import com.example.listingapp_rest.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole userRole;
}
