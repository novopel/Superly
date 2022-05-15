package Presentation.Screens;

import Presentation.BackendController;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class Screen implements Runnable{
    protected final static Scanner scanner = new Scanner(System.in);
    protected final BackendController controller;
    private final String[] menuOptions;
    private final Screen caller;


    public Screen(BackendController controller, String[] menuOptions){
        this.controller = controller;
        this.menuOptions = menuOptions;
        caller = null;
    }

    public Screen(Screen caller, String[] menuOptions){
        this.controller = caller.controller;
        this.menuOptions = menuOptions;
        this.caller = caller;
    }

    protected void printMenu(){
        for (int i = 0 ; i < menuOptions.length; i++)
            System.out.println((i + 1) + " -- " + menuOptions[i]);
    }

    protected int runMenu(){
        System.out.println("\nWhat would you like to do?");
        int option = 0;
        while (option <= 0 || option > menuOptions.length){
            printMenu();
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option <= 0 || option >menuOptions.length)
                    System.out.println("Please enter an integer value between 1 and " + menuOptions.length);
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter an integer value between 1 and " + menuOptions.length);
            }
            catch (Exception ex){
                System.out.println("An unexpected error happened. Please try again");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return option;
    }

    protected void endRun(){
        if (caller != null)
            new Thread(caller).start();
        else
            System.out.println("Thanks for using software by Group_L!\nBye bye!");
    }

    protected static boolean areYouSure(){
        System.out.println("Are you sure?");
        return yesOrNo();
    }

    protected static boolean yesOrNo(){
        int ans = 0;
        while (ans != 1 && ans != 2){
            System.out.println("1 -- yes\n2 -- no");
            try {
                ans = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter an integer value (1 or 2)");
            }
            catch (Exception ex){
                System.out.println("An unexpected error happened. Please try again");
            }
        }
        return ans == 1;
    }

    public static LocalDate buildDate()  {
        boolean success = false;
        int day =0;
        int month =0;
        int year =0;
        while (!success) {
            System.out.println("Enter day");
            try {
                day = Integer.parseInt(scanner.nextLine());
                if (day == -1) {
                    System.out.println("Operation Canceled");
                    return null;
                } else if (day < 1 || day > 31) {
                    System.out.println("Enter valid day");
                } else {
                    success = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter an integer between 1 and 31");
            } catch (Exception ex) {
                System.out.println("Unexpected error occurred");
                System.out.println("Please try again");
            }
        }
        success = false;
        while (!success) {
            System.out.println("Enter month");
            try {
                month = Integer.parseInt(scanner.nextLine());
                if (month == -1) {
                    System.out.println("Operation Canceled");
                    return null;
                } else if (month < 1 || month > 12) {
                    System.out.println("Enter valid month");
                } else {
                    success = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter an integer between 1 and 12");
            } catch (Exception ex) {
                System.out.println("Unexpected error occurred");
                System.out.println("Please try again");
            }
        }
        success = false;
        while (!success) {
            System.out.println("Enter year");
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year == -1) {
                    System.out.println("Operation Canceled");
                    return null;
                } else if (year < 1900 || year > 2030) {
                    System.out.println("Enter valid year");
                } else {
                    success = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter an integer between 1900 and 2030");
            } catch (Exception ex) {
                System.out.println("Unexpected error occurred");
                System.out.println("Please try again");
            }
        }

        return LocalDate.of(year, month, day);
    }
}