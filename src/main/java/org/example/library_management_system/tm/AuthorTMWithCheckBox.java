package org.example.library_management_system.tm;

import javafx.scene.control.CheckBox;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorTMWithCheckBox {
    private int id;
    private String name;
    private CheckBox checkBox;
}
