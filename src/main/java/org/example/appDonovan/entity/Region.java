package org.example.appDonovan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.appDonovan.service.Color;

import static org.example.appDonovan.service.Color.randColorBack;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Region implements EntityInterface {
    private Integer id;
    private String code;
    private String name;

    public Region(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Region{" +
                String.format(randColorBack(this, "id") + "id=%-4s",id) +
                String.format(randColorBack(this, "code") + ", code=%-3s",code) +
                String.format(randColorBack(this, "name") + ", name=%-19s",name) +
                "}\u001B[0m";
    }
}
