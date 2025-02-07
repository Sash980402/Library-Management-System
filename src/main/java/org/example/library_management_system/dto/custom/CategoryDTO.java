package org.example.library_management_system.dto.custom;


import lombok.*;
import org.example.library_management_system.dto.SuperDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO implements SuperDTO {
    private int id;
    private String name;
}
