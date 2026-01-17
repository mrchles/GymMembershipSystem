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

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "notsomedb", "postgres", "0000");

        MemberRepository memberRepo = new MemberRepository(db);
        IMemberController memberController = new MemberController(memberRepo);
        TrainerRepository trainerRepo = new TrainerRepository(db);
        TrainerController trainerController = new TrainerController(trainerRepo, memberRepo);


        MyApplication app = new MyApplication(memberController, trainerController);
        app.start();

        db.close();
    }
}
