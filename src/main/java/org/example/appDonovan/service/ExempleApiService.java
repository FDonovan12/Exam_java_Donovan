package org.example.appDonovan.service;

import org.example.appDonovan.entity.ClassExemple;
import org.example.appDonovan.repository.ExempleRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ExempleApiService {

    private HttpClientService httpClientService = new HttpClientService();

    private ExempleRepository exempleRepository = ExempleRepository.getRepository();

    public ClassExemple getPokemon(int id) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + id;
        String json = httpClientService.get(url);
        if (json.isEmpty()) {
            return null;
        }
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject object = new JSONObject(tokener);

            // Obligatoire pour aller dans other
            JSONObject sprites = object.getJSONObject("sprites");
            JSONObject other = sprites.getJSONObject("other");
            JSONObject home = other.getJSONObject("home");

            ClassExemple pokemon = new ClassExemple();
            pokemon.setId(object.getLong("id"));
            pokemon.setName(object.getString("name"));

            // Récupération des types
//            JSONArray types = object.getJSONArray("types");
//            for (int i = 0; i < types.length(); i++) {
//                pokemon.addType(new Type(getNameFromJson(types.getJSONObject(i), "type")));
//            }
//
//            JSONArray abilities = object.getJSONArray("abilities");
//            for (int i = 0; i < abilities.length(); i++) {
//                pokemon.addAbility(new Ability(getNameFromJson(abilities.getJSONObject(i), "ability")));
//            }
//
//            JSONArray jsonStats = object.getJSONArray("stats");
//            for (int i = 0; i < jsonStats.length(); i++) {
//                JSONObject stats = jsonStats.getJSONObject(i);
//                pokemon.addStat(new Stat(getNameFromJson(stats, "stat"), stats.getInt("base_stat")));
//            }

            return exempleRepository.save(pokemon);
        } catch (JSONException e) {
            System.out.println(json);
        }
        return null;
    }

    private String getNameFromJson(JSONObject firstObj, String key) {
        JSONObject secondObj = firstObj.getJSONObject(key);
        return secondObj.getString("name");
    }

}
