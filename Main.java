import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Main.main()");

        Controller controller;

        controller = new Controller();

        controller.InitializeDatabase();
        controller.CreateModel();
        controller.CreateUI();
    }
}