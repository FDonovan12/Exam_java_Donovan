package org.example.appDonovan.view;

import org.example.appDonovan.controller.ExempleController;
import org.example.appDonovan.entity.Region;

import java.util.List;
import java.util.Scanner;

public class ExempleView {

    private final Scanner sc = new Scanner(System.in);

    private ExempleController cc;

    public ExempleView(ExempleController cc) {
        this.cc = cc;
    }

    public void index(List<Region> objects) {
        objects.forEach(System.out::println);
    }

    public void show(Region object) {
        System.out.println(object);
    }

    public void create(Region object) {
        System.out.println("Quel est le nom du pays ?");
        object.setName(sc.nextLine());
        System.out.println("Quel est le code du pays ?");
        object.setCode(sc.nextLine());
        System.out.println("Quel est la nationalité du pays ?");
        cc.create(object);
    }
}
