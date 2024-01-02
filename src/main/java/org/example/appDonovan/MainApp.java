package org.example.appDonovan;

import org.example.appDonovan.repository.ExempleRepository;

import java.util.HashMap;
import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("test");
        Map<String, Boolean> order = new HashMap<>();
        order.put("name", true);
        order.put("id", false);
        ExempleRepository.getRepository().test(order);
//        String query = "SELECT *, COUNT(uog.game_id) as total_ventes FROM game ";
//        query += "join user_own_game as uog on uog.game_id = game.id ";
//        query += "group by game.name order by total_ventes DESC limit 5";
//        GameRepository.getRepository().createQuery(query).forEach(System.out::println);
        ExempleRepository.getRepository().createQuery("select * from country").forEach(System.out::println);

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Où veux tu aller ?");
//        String goTo = sc.nextLine();
//        ExempleController exempleController = new ExempleController();
//        while (!goTo.isEmpty()) {
//            if (goTo.equals("index")) {
//                exempleController.index();
//            }
//            if (goTo.equals("show")) {
//                exempleController.show(sc.nextLong());
//            }
//            if (goTo.equalsIgnoreCase("create")) {
//                exempleController.create(new ClassExemple());
//            }
//            if (goTo.equalsIgnoreCase("q")) {
//                return;
//            }
//            goTo = sc.nextLine();
//        }
    }
}
