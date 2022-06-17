package Presentation.WebPresentation.Screens.ViewModels.InventoryScreens;

import Presentation.WebPresentation.Screens.Models.HR.Employee;
import Presentation.WebPresentation.Screens.Screen;
import Presentation.WebPresentation.Screens.ViewModels.HR.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class InventoryMainMenu extends Screen {

    private static final String greet = "Inventory's Main Menu - TO REMEMBER: FIX CREATE ORDERS\n";
    public static final Set<Class<? extends Employee>> ALLOWED = new HashSet<>();

    public InventoryMainMenu() {
        super(greet, ALLOWED);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!isAllowed(req, resp)) {
            redirect(resp, Login.class);
        }
        header(resp);
        greet(resp);
        printMenu(resp, new String[]{"Manage Products", "Manage Categories", "Manage sales", "Manage inventory"});
        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);
        switch (getIndexOfButtonPressed(req)){
            case 0:
                redirect(resp, Products.class);
                break;
            case 1:
                redirect(resp, Categories.class);
                break;
            case 2:
                redirect(resp, Sales.class);
                break;
            case 3:
                redirect(resp, ManageInventory.class);
            default:
                redirect(resp, InventoryMainMenu.class);
        }
    }
}
