package org.example.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.app.service.Color;

import static org.example.app.service.Color.randColorBack;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassExemple implements EntityInterface {
    private Long id;
    private String code;
    private String name;
    private String nationality;
    private String slug;
    private String urlFlag;

    public ClassExemple(Long id) {
        this.id = id;
    }

    public ClassExemple(Long id, String code, String name, String nationality, String slug) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.nationality = nationality;
        this.slug = slug;
        setUrlBasedOnCode(code);
    }

    public ClassExemple(String code, String name, String nationality, String slug) {
        this.code = code;
        this.name = name;
        this.nationality = nationality;
        this.slug = slug;
        setUrlBasedOnCode(code);
    }

    public ClassExemple(Long id, String code, String name, String nationality) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.nationality = nationality;
        this.slug = Color.setSlugBasedOnString(nationality);
        setUrlBasedOnCode(code);
    }

    public ClassExemple(String code, String name, String nationality) {
        this.code = code;
        this.name = name;
        this.nationality = nationality;
        this.slug = Color.setSlugBasedOnString(nationality);
        setUrlBasedOnCode(code);
    }

    public void setUrlBasedOnCode(String code) {
        this.urlFlag = "https://flagcdn.com/32x24/" + code + ".png";
    }

    @Override
    public String toString() {
        return "Country{" +
                String.format(randColorBack(this, "id") + "id=%-4s",id) +
                String.format(randColorBack(this, "code") + ", code=%-3s",code) +
                String.format(randColorBack(this, "name") + ", name=%-19s",name) +
                String.format(randColorBack(this, "nationality") + ", nationality=%-16s",nationality) +
                String.format(randColorBack(this, "slug") + ", slug=%-16s",slug) +
                String.format(randColorBack(this, "urlFlag") + ", urlFlag=%s",urlFlag) +
                "}\u001B[0m";
    }
}
