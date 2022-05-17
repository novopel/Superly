package Presentation.Screens;

public class Transport_Manager extends Employee{
    private static final String[] extraMenuOptions  = {
            "Exit"              //9
    };
    public Transport_Manager(Screen caller, Domain.Service.Objects.Employee sEmployee) {
        super(caller, sEmployee, extraMenuOptions);
    }

    @Override
    public void run() {
        System.out.println("\nWelcome to the Management Menu of " + name + "!");
        int option = 0;
        while (option != 9) {
            option = runMenu();
            try {
                if (option <= 8)
                    handleBaseOptions(option);
                else if (option == 9)
                    endRun();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Please try again");
            }
        }
    }
}