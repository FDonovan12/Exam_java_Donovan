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
public class PostalCode implements EntityInterface {
    private Integer id;
    private String code;
//    private City city;

    public PostalCode(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostalCode{" +
                String.format(randColorBack(this, "id") + "id=%-4s",id) +
                String.format(randColorBack(this, "code") + ", code=%-3s",code) +
//                String.format(randColorBack(this, "city") + ", city=%-19s",city == null ? null : city.getName()) +
                "}\u001B[0m";
    }
}
