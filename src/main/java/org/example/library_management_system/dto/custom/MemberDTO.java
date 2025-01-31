package org.example.library_management_system.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.library_management_system.dto.SuperDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MemberDTO implements SuperDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String mobileNumber;
}
