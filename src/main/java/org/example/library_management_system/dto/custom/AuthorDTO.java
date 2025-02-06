package org.example.library_management_system.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.library_management_system.dto.SuperDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO implements SuperDTO {
    private int id;
    private String name;
    private String contact;

}
