package gym;

import gym.controllers.MemberController;
import gym.controllers.TrainerController;
import gym.data.PostgresDB;
import gym.data.interfaces.IDB;
import gym.repositories.MemberRepository;
import gym.repositories.TrainerRepository;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB(
                "jdbc:postgresql://localhost:5432", "notsomedb", "postgres", "0000");

        // Репозитории
        MemberRepository memberRepo = new MemberRepository(db);
        TrainerRepository trainerRepo = new TrainerRepository(db);

        // Контроллеры
        MemberController memberController = new MemberController(memberRepo);
        TrainerController trainerController = new TrainerController(trainerRepo, memberRepo);

        // Приложение
        MyApplication app = new MyApplication(memberController, trainerController);
        app.start();

        db.close();
        System.out.println("Application closed.");
    }
}
