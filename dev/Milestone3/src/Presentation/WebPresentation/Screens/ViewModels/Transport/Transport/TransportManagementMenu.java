package Presentation.WebPresentation.Screens.ViewModels.Transport.Transport;

import Presentation.WebPresentation.Screens.Screen;
import Presentation.WebPresentation.Screens.ViewModels.Transport.DocumentManagementMenu;
import Presentation.WebPresentation.Screens.ViewModels.Transport.TransportMainMenu;
import Presentation.WebPresentation.Screens.ViewModels.Transport.Truck.TruckManagementMenu;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransportManagementMenu extends Screen {
    private static final String greet = "Transport Management Menu";

    private static final String[] menuOptions = {
            "Add transport order",          //1
            "Create new transport",         //2
            "Update transport",             //3
            "Get pending transport",        //4
            "Get in progress transport",    //5
            "Get complete transport",       //6
            "Exit"                          //7
    };
    public TransportManagementMenu() {
        super(greet);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        header(resp);
        greet(resp);
        printMenu(resp, menuOptions);
        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);
        switch (getIndexOfButtonPressed(req)) {
            case 0:
                redirect(resp, TransportManagementMenu.class);
                break;
            case 1:
                redirect(resp, TruckManagementMenu.class);
                break;
            case 2:
                redirect(resp, DocumentManagementMenu.class);
                break;
            default:
                redirect(resp, TransportMainMenu.class);
        }
    }
}
