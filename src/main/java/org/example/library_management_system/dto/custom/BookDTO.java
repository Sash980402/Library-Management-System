package org.example.library_management_system.dto.custom;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.library_management_system.dto.SuperDTO;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class BookDTO implements SuperDTO {
    private int id;
    private String name;
    private String isbn;
    private double price;
    private String author;
    private int publisherId;
    private int mainCategoryId;


}
