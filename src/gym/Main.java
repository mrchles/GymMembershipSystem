package gym;

import gym.controllers.MemberController;
import gym.controllers.interfaces.IMemberController;
import gym.data.PostgresDB;
import gym.data.interfaces.IDB;
import gym.repo.MemberRepository;
import gym.repo.interfaces.IMemberRepository;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "notsomedb", "postgres", "0000");

        IMemberRepository repo = new MemberRepository(db);
        IMemberController controller = new MemberController(repo);

        MyApplication app = new MyApplication(controller);
        app.start();

        db.close();
    }
}
