package Presentation.WebPresentation.Screens.Suppliers.Screens;

import Domain.Service.Objects.SupplierObjects.ServiceItemObject;
import Presentation.WebPresentation.Screens.Screen;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class ShowAgreement extends Screen {

    private final int supplierId;

    public ShowAgreement(String greet, int supplierId) {
        super(greet);
        this.supplierId = supplierId;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        header(resp);
        greet(resp);


        printMenu(resp, new String[]{"Show All Items", "Add item to agreement"});
        printForm(resp, new String[] {"itemId"}, new String[]{"Item ID"}, new String[]{"Remove Item"});
        printForm(resp, new String[] {"itemId"}, new String[]{"Item ID"}, new String[]{"View Item"});

        // TODO: 11/06/2022 Should we do it? maybe it can cause problems...
        printForm(resp, new String[] {"agreementType", "agreementDays" }, new String[]{"Agreement Type", "Agreement Days"}, new String[]{"Change Agreement Type"});

        //print info about agreement Type
        if(!(this instanceof ShowNotTransportingAgreement)){
            printForm(resp, new String[] {"agreementDays" }, new String[]{"Delivery Days"}, new String[]{"Change Delivery Days"});
            //print how to enter this days
        }

        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);


        if (isButtonPressed(req, "Remove Item")) {

        }
        else if(isButtonPressed(req, "View Item")){
            // TODO: Suppliers pass itemId, supplierId
            redirect(resp, ShowAgreementItem.class);
        }
        else if(isButtonPressed(req, "Change Agreement Type")) {

        }
        else if(isButtonPressed(req, "Change Delivery Days")){
            //could be the same for routine and ByOrder?
        }


        switch (getIndexOfButtonPressed(req)){
            case 0:
                showAllItems(req, resp);
                break;
            case 1:
                // TODO: Suppliers pass supplierId
                redirect(resp, AddItemToAgreement.class);
                break;

        }
    }

    private void showAllItems(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
         List<ServiceItemObject> list = new ArrayList<>();
        try {
            list = controller.itemsFromOneSupplier(supplierID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


         */
        try {
            List<ServiceItemObject> list = controller.itemsFromOneSupplier(supplierId);
            if(list.isEmpty()){
                setError("[NO ITEMS ARE IN THE AGREEMENT]");
                refresh(req, resp);
            }

            for(ServiceItemObject item : list){
                setError(item.toString());
                refresh(req, resp);
            }
        }
        catch (Exception e) {
            setError(e.getMessage());
            refresh(req, resp);
        }
    }
}
