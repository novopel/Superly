package Presentation.WebPresentation.Screens.ViewModels.Transport.Truck;

import Presentation.WebPresentation.Screens.InventoryScreens.InventoryMainMenu;
import Presentation.WebPresentation.Screens.Models.HR.Employee;
import Presentation.WebPresentation.Screens.Screen;
import Presentation.WebPresentation.Screens.ViewModels.HR.EmployeeServlet;
import Presentation.WebPresentation.Screens.ViewModels.Suppliers.SupplierMainMenuStorekeeper;
import Presentation.WebPresentation.Screens.ViewModels.Transport.TransportMainMenu;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeleteTruck extends Screen {
    private static final String greet = "Add Truck:";

    public DeleteTruck() {
        super(greet);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        header(resp);
        greet(resp);
        printForm(resp, new String[]{"LN"}, new String[]{"License number"}, new String[]{"Delete", "Cancel"});
        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);

        if (isButtonPressed(req, "Delete")){
            int ln = Integer.parseInt(req.getParameter("LN"));
            try {
                controller.removeTruck(ln);
            } catch (Exception e) {
                setError(e.getMessage());
                refresh(req, resp);
            }
        }
        else if(isButtonPressed(req, "Cancel"))
            redirect(resp, TransportMainMenu.class);
    }

}