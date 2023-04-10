package com.portfolio.boatstation.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String passportData;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;
}
