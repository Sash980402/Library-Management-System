package org.example.library_management_system.tm;

import javafx.scene.control.CheckBox;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryTMWithBox {
    private int id;
    private String name;
    private CheckBox checkBox;


}

