package Presentation.WebPresentation.Screens.ViewModels.Suppliers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SupplierMainMenuStoreManager extends SupplierMainMenu {

    private static final String greet = "Supplier's Main Menu for Store Manager!";
    private static final String button = "View Supplier";

    public SupplierMainMenuStoreManager() {
        super(greet);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        header(resp);
        greet(resp);
        printMenu(resp, new String[]{"Manage Suppliers", "View/Remove Orders"});

         printSupplierIds(resp, req);
        printForm(resp, new String[] {"ID"}, new String[]{"Supplier ID"}, new String[]{button});

        handleError(resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);
        if (isButtonPressed(req, button)){
            viewSupplier(req, resp);
        }

        switch (getIndexOfButtonPressed(req)){
            case 0:
                redirect(resp, ManageSuppliers.class);
                break;
            case 1:
                // TODO: Supplier : can he remove orders? I think he needs only to view
                redirect(resp, OrderStoreManager.class);
                break;
        }
    }


}
