package org.example.appDonovan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static org.example.appDonovan.service.Color.randColorBack;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Digimon implements EntityInterface {
    private Integer id;
    private String name;
    private String level;
    private String img;

    @Override
    public String toString() {
        return "Digimon{" +
                String.format(randColorBack(this, "id") + "id=%-4s",id) +
                String.format(randColorBack(this, "name") + ", name=%-20s",name) +
                String.format(randColorBack(this, "level") + ", level=%-15s",level) +
                String.format(randColorBack(this, "img") + ", img=%-70s",img) +
                "}\u001B[0m";
    }
}
