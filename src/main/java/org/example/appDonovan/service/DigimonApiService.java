package org.example.appDonovan.service;

import com.mysql.cj.xdevapi.JsonArray;
import org.example.appDonovan.entity.Digimon;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.appDonovan.service.Color.ANSI_BACKGROUND_RED;
import static org.example.appDonovan.service.Color.ANSI_RESET;

public class DigimonApiService {

    private HttpClientService httpClientService = new HttpClientService();

    public List<Digimon> getDigimons() {
        String url = "https://digimon-api.vercel.app/api/digimon";
        String json = httpClientService.get(url);
        if (json.isEmpty()) {
            return null;
        }
        try {
            json = json.replace("[", "");
            json = json.replace("]", "");
            json = json.replaceAll("},", "}£");
            List<String> array = Arrays.stream(json.split("£")).toList();
            List<Digimon> digimons = new ArrayList<>();

            for (int i = 0 ; i < array.size() ; i++) {
                JSONTokener tokener = new JSONTokener(array.get(i));
                JSONObject object = new JSONObject(tokener);
                Digimon digimon = new Digimon();
                digimon.setName(object.getString("name"));
                digimon.setLevel(object.getString("level"));
                digimon.setImg(object.getString("img"));
                digimon.setId(i);
                digimons.add(digimon);
            }
            return digimons;
        } catch (JSONException e) {
            System.out.println(ANSI_BACKGROUND_RED + "ApiService " + e + ANSI_RESET);
        }
        return null;
    }

    private String getNameFromJson(JSONObject firstObj, String key) {
        JSONObject secondObj = firstObj.getJSONObject(key);
        return secondObj.getString("name");
    }

}
