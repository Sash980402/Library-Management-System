package org.example.library_management_system.tm;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorTM {
    private int id;
    private String name;
    private String contact;
}
