package org.example.appDonovan.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.appDonovan.entity.Department;
import org.example.appDonovan.entity.PostalCode;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import static org.example.appDonovan.service.Color.ANSI_BACKGROUND_RED;
import static org.example.appDonovan.service.Color.ANSI_RESET;

@Getter
@Setter
public class PostalCodeRepository extends AbstractRepository <PostalCode>{
    private static PostalCodeRepository postalCodeRepository;
    private PostalCodeRepository() {
        tableName = "postal_code";
    }
    public static PostalCodeRepository getRepository() {
        if (postalCodeRepository == null) {
            postalCodeRepository = new PostalCodeRepository();
        }
        return postalCodeRepository;
    }

    protected PostalCode getObject(ResultSet resultSet) {
        try {
            PostalCode postalCode = new PostalCode();
            postalCode.setId(resultSet.getInt("id"));
            postalCode.setCode(resultSet.getString("code"));

//            Map<String, Object> mapFindCity = new HashMap<>();
//            mapFindCity.put("id", resultSet.getLong("city_id"));
//            postalCode.setCity(CityRepository.getRepository().findOneBy(mapFindCity));
            return postalCode;
        } catch (Exception e) {
            System.out.println(ANSI_BACKGROUND_RED + "PostalCode getObject " + e + ANSI_RESET);
        }
        return null;
    }

    protected String insert(PostalCode object) {
        return "";
    }
    protected String update(PostalCode object) {
        return "";
    }
}
