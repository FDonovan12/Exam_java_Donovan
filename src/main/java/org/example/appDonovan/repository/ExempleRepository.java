package org.example.appDonovan.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.appDonovan.entity.ClassExemple;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExempleRepository extends AbstractRepository <ClassExemple>{
    private static ExempleRepository exempleRepository;
    private ExempleRepository() {
        tableName = "country";//ClassExemple.class.getSimpleName().toLowerCase();
    }
    public static ExempleRepository getRepository() {
        if (exempleRepository == null) {
            exempleRepository = new ExempleRepository();
        }
        return exempleRepository;
    }

    protected ClassExemple getObject(ResultSet resultSet) {
        try {
            ClassExemple country = new ClassExemple();
            country.setId(resultSet.getLong("id"));
            country.setCode(resultSet.getString("code"));
            country.setName(resultSet.getString("name"));
            country.setNationality(resultSet.getString("nationality"));
            country.setSlug(resultSet.getString("slug"));
            country.setUrlFlag(resultSet.getString("url_flag"));
            return country;
        } catch (Exception ignored) {}
        return null;
    }

    @Override
    protected String insert(ClassExemple object) {
        String sb = "INSERT INTO " + tableName;
        sb += " (code, name, nationality, slug, url_flag) VALUES ";
        sb += String.format("('%s'", object.getCode());
        sb += String.format(", '%s'", object.getName());
        sb += String.format(", '%s'", object.getNationality());
        sb += String.format(", '%s'", object.getSlug());
        sb += String.format(", '%s')", object.getUrlFlag());
        return sb;

    }

    @Override
    protected String update(ClassExemple object) {
        String sb = "UPDATE " + tableName;
        sb += String.format(" Set code = '%s'", object.getCode());
        sb += String.format(", name = '%s'", object.getName());
        sb += String.format(", nationality = '%s'", object.getNationality());
        sb += String.format(", slug = '%s'", object.getSlug());
        sb += String.format(", url_flag = '%s'", object.getUrlFlag());
        sb += String.format(" WHERE id= %s", object.getId());
        return sb;
    }

    @Override
    public void test(Map<String, Boolean> order) {
        super.test(order);
        Map<String, Object> mapFind2 = new HashMap<>();
        mapFind2.put("id", 28L);
        ClassExemple country = findOneBy(mapFind2);
        if (country.getName().contains("new")){
            country.setName(country.getName().replaceAll("new ",""));
        } else {
            country.setName("new "+ country.getName());
        }
        save(country);
        System.out.println(findOneBy(mapFind2));
    }
}
