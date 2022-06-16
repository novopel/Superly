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

public class Sales extends Screen{

    private static final String greet = "Sales";
    public static final Set<Class<? extends Employee>> ALLOWED = new HashSet<>();

    public Sales() {
        super(greet,ALLOWED);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!isAllowed(req, resp)) {
            redirect(resp, Login.class);
        }
        header(resp);
        greet(resp);


        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);

    }
}