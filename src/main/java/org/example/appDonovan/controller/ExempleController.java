package org.example.appDonovan.controller;


import org.example.appDonovan.entity.Region;
import org.example.appDonovan.service.entity.ExempleService;
import org.example.appDonovan.view.ExempleView;

public class ExempleController {

    private ExempleService exempleService = new ExempleService();

    private ExempleView exempleView = new ExempleView(this);

    public void index() {
        exempleView.index(exempleService.findAll());
    }

    public void show(Long id) {
        exempleView.show(exempleService.findOneBy("id", id));
    }

    public void create(Region object) {
        if (object.getName() == null) {
            exempleView.create(object);
        } else {
            Region c = exempleService.save(object);
            exempleView.show(c);
        }
    }

}
