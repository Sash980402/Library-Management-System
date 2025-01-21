package org.example.library_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MemberDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String mobileNumber;
}
