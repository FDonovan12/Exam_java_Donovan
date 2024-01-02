package org.example.appDonovan.service.entity;

import org.example.appDonovan.dao.DAOService;
import org.example.appDonovan.entity.Region;
import org.example.appDonovan.repository.RegionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExempleService implements DAOService<Region> {

    private RegionRepository regionRepository = RegionRepository.getRepository();

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public List<Region> findBy(Map<String, Object> fields, Integer limit, Map<String, Boolean> orders) {
        return regionRepository.findBy(fields, limit, orders);
    }

    @Override
    public Region findOneBy(String field, Object value) {
        Map<String, Object> fields = new HashMap<>();
        fields.put(field, value);
        return regionRepository.findOneBy(fields);
    }

    @Override
    public Region save(Region o) {
        return regionRepository.save(o);
    }

    public Region handleForm(String name, String code, String nationality) {
        Region object = new Region();
        object.setCode(code);
        object.setName(name);
        return save(object);
    }

}
