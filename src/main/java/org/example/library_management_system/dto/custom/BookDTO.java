package org.example.library_management_system.dto.custom;


import lombok.*;
import org.example.library_management_system.dto.SuperDTO;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class BookDTO implements SuperDTO {
    private int id;
    private String name;
    private String isbn;
    private double price;
    private int publisherId;
    private int mainCategoryId;
    private List<Integer> subCategoryIds;
    private List<Integer> authors;


}
