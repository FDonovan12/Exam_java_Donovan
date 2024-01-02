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
public class City implements EntityInterface {
    private Integer id;
    private String code;
    private String name;
    private String siren;
    private int population;
    private Department department;
    private List<PostalCode> postalCodeList;

    public City(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                String.format(randColorBack(this, "id") + "id=%-4s",id) +
                String.format(randColorBack(this, "code") + ", code=%-10s",code) +
                String.format(randColorBack(this, "name") + ", name=%-19s",name) +
                String.format(randColorBack(this, "siren") + ", siren=%-25s",siren) +
                String.format(randColorBack(this, "population") + ", population=%-9s",population) +
                String.format(randColorBack(this, "department") + ", department=%-19s",department == null ? null : department.getName()) +
                String.format(randColorBack(this, "postalCodeList") + ", postalCodeList=%-19s",postalCodeList.stream().map(element -> element.getCode()).toList()) +
                "}\u001B[0m";
    }
}
