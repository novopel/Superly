package Presentation.WebPresentation.Screens.InventoryScreens;

import Domain.Service.Objects.SupplierObjects.ServiceOrderObject;
import Presentation.WebPresentation.Screens.Screen;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Catalog extends Screen{

    private static final String greet = "Welcome to the products' catalog of Superly";

    public Catalog() {
        super(greet);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        header(resp);
        greet(resp);


        /*printOrderIds(resp);
        printForm(resp, new String[] {"supplierId","storeId"}, new String[]{"Supplier ID", "Store ID"}, new String[]{"Add Order"});
        printForm(resp, new String[] {"orderId1"}, new String[]{"Order ID"}, new String[]{"Remove Order"});
        printForm(resp, new String[] {"orderId2"}, new String[]{"Order ID"}, new String[]{"Edit Order"});
        printForm(resp, new String[] {"orderId3"}, new String[]{"Order ID"}, new String[]{"View Order"});*/


        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);

        /*if (isButtonPressed(req, "Add Order")){
            addOrder(req, resp);
        }
        else if(isButtonPressed(req, "Remove Order")){
            removeOrder(req, resp);
        }
        else if(isButtonPressed(req, "Edit Order")){
            //get the order id...
            editOrder(req, resp);

        }
        else if(isButtonPressed(req, "View Order")){
            printOrder(req, resp);
        }*/

    }
}
