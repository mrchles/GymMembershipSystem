package gym;

import gym.controllers.AuthController;
import gym.controllers.MemberController;
import gym.controllers.TrainerController;
import gym.controllers.interfaces.IMemberController;
import gym.data.PostgresDB;
import gym.data.interfaces.IDB;
import gym.models.User;
import gym.repo.MemberRepository;
import gym.repo.TrainerRepository;
import gym.repo.UserRepository;

import java.util.Scanner;

public class Main {

   public static void main(String[] args) {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");

        IDB db = new PostgresDB(url, dbName, user, password);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Login: ");
        String loginInput = scanner.nextLine();

        System.out.print("Password: ");
        String passwordInput = scanner.nextLine();

        UserRepository userRepo = new UserRepository(db);
        AuthController auth = new AuthController(userRepo);

        User currentUser = auth.login(loginInput, passwordInput);
        System.out.println("Logged in as: " + currentUser.getRole());

        MemberRepository memberRepo = new MemberRepository(db);
        IMemberController memberController = new MemberController(memberRepo);
        TrainerRepository trainerRepo = new TrainerRepository(db);
        TrainerController trainerController = new TrainerController(trainerRepo, memberRepo);


        MyApplication app = new MyApplication(memberController, trainerController,currentUser);
        app.start();

        db.close();
    }
}
