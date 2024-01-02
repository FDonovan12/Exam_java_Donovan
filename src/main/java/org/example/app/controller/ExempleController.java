package org.example.app.controller;


import org.example.app.entity.ClassExemple;
import org.example.app.service.entity.ExempleService;
import org.example.app.view.ExempleView;

public class ExempleController {

    private ExempleService exempleService = new ExempleService();

    private ExempleView exempleView = new ExempleView(this);

    public void index() {
        exempleView.index(exempleService.findAll());
    }

    public void show(Long id) {
        exempleView.show(exempleService.findOneBy("id", id));
    }

    public void create(ClassExemple object) {
        if (object.getName() == null) {
            exempleView.create(object);
        } else {
            ClassExemple c = exempleService.save(object);
            exempleView.show(c);
        }
    }

}
