package org.example.appDonovan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.example.appDonovan.service.Color.randColorBack;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department implements EntityInterface {
    private Integer id;
    private String code;
    private String name;
    private Region region;

    public Department(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                String.format(randColorBack(this, "id") + "id=%-4s",id) +
                String.format(randColorBack(this, "code") + ", code=%-3s",code) +
                String.format(randColorBack(this, "name") + ", name=%-19s",name) +
                String.format(randColorBack(this, "region") + ", region=%-19s",region == null ? null : region.getName()) +
                "}\u001B[0m";
    }
}
