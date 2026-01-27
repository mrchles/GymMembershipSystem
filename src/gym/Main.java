package gym;

import gym.controllers.MemberController;
import gym.controllers.interfaces.IMemberController;
import gym.data.PostgresDB;
import gym.data.interfaces.IDB;
import gym.repo.MemberRepository;
import gym.repo.interfaces.IMemberRepository;
import gym.repo.TrainerRepository;
import gym.models.Trainers;
import gym.controllers.TrainerController;

public class Main {

   public static void main(String[] args) {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");

        IDB db = new PostgresDB(url, dbName, user, password);


        MemberRepository memberRepo = new MemberRepository(db);
        IMemberController memberController = new MemberController(memberRepo);
        TrainerRepository trainerRepo = new TrainerRepository(db);
        TrainerController trainerController = new TrainerController(trainerRepo, memberRepo);


        MyApplication app = new MyApplication(memberController, trainerController);
        app.start();

        db.close();
    }
}
