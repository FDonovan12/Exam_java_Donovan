package org.example.appDonovan.service.entity;

import org.example.appDonovan.dao.DAOService;
import org.example.appDonovan.entity.ClassExemple;
import org.example.appDonovan.repository.ExempleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExempleService implements DAOService<ClassExemple> {

    private ExempleRepository exempleRepository = ExempleRepository.getRepository();

    @Override
    public List<ClassExemple> findAll() {
        return exempleRepository.findAll();
    }

    @Override
    public List<ClassExemple> findBy(Map<String, Object> fields, Integer limit, Map<String, Boolean> orders) {
        return exempleRepository.findBy(fields, limit, orders);
    }

    @Override
    public ClassExemple findOneBy(String field, Object value) {
        Map<String, Object> fields = new HashMap<>();
        fields.put(field, value);
        return exempleRepository.findOneBy(fields);
    }

    @Override
    public ClassExemple save(ClassExemple o) {
        return exempleRepository.save(o);
    }

    public ClassExemple handleForm(String name, String code, String nationality) {
        ClassExemple object = new ClassExemple();
        object.setCode(code);
        object.setName(name);
        object.setNationality(nationality);
        return save(object);
    }

}
