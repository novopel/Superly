package Frontend;

import java.util.Scanner;

import Backend.Globals.Enums.LicenseTypes;
import Backend.ServiceLayer.Service;
import Frontend.Objects.TransportOrder;

public class Cli {
    private static Scanner reader = new Scanner(System.in);
    private static Service service = new Service();
    public static void main(String[] args)
    {
        boolean closeProgram = false;
        while(!closeProgram) {
            switch (mainMenu()) {
                case 1:
                    driverSystemManagement();
                    break;
                case 2:
                    truckSystemManagement();
                    break;
                case 3:
                    transportSystemManagement();
                    break;
                case 4:
                    documentSystemManagement();
                    break;
                case 5:
                    closeProgram = true;
                    System.out.println("Good Bye!!!");
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
    private static void transportSystemManagement()
    {
        boolean back = false;
        while(!back) {
            switch (menuTransSM()) {
                case 1:
                    //TODO: service.addTransportOrder();
                    break;
                case 2:
                    createNewTransport();
                    break;
                case 3:
                    //TODO: service.getWaitingTransports();
                    break;
                case 4:
                    //TODO: service.getInProgressTransports();
                    break;
                case 5:
                    //TODO: service.getRedesignTransports();
                    break;
                case 6:
                    updateTransport();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private static void updateTransport() {
        boolean back = false;
        while(!back) {
            switch (menuCNT()) {
                case 1:
                    //TODO: service.placeDriver();
                    break;
                case 2:
                    //TODO: service.placeTruck();
                    break;
                case 3:
                    //TODO: service.startTransport();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private static void createNewTransport() {
        boolean back = false;
        while(!back) {
            switch (menuCNT()) {
                case 1:
                    //TODO: service.getOrders();
                    break;
                case 2:
                    //TODO: service.getOrdersInTransportArea();
                    break;
                case 3:
                    //TODO: service.addOrderToTransport();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private static int menuCNT() {
        System.out.println("Create Transport\n" +
                "Please enter option number:\n" +
                "1. View orders\n" +
                "2. View orders in same areas\n" +
                "3. Add order\n" +
                "4. Back");
        return getChoice(1, 4);
    }

    private static int menuTransSM() {
        System.out.println("Transport system management\n" +
                "Please enter option number:\n" +
                "1. Add transport order\n" +
                "2. Create new transport\n" +
                "3. Get waiting transport\n" +
                "4. Get in progress transport\n" +
                "5. Get redesign transport\n" +
                "6. Update transport\n" +
                "7. Back");
        return getChoice(1, 7);
    }

    private static void documentSystemManagement() {
        boolean back = false;
        while(!back) {
            switch (menuDocSM()) {
                case 1:
                    //TODO: service.getTransportDocument(int docSN);
                    break;
                case 2:
                    //TODO: service.getDestinationDocument(int docSN);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private static int menuDocSM() {
        System.out.println("Document system management\n" +
                "Please enter option number:\n" +
                "1. Get transport document\n" +
                "2. Get destination document\n" +
                "3. Back");
        return getChoice(1, 3);
    }

    private static void driverSystemManagement() {
        boolean back = false;
        while(!back) {
            switch (menuDSM()) {
                case 1:
                    //TODO: service.addDriver();
                    break;
                case 2:
                    //TODO: service.removeDriver();
                    break;
                case 3:
                    //TODO: service.updateDriver();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    //TODO:
                    System.out.println("Invalid input!");
            }
        }
    }

    private static int mainMenu()
    {
        System.out.println("Welcome to Transport's system\n" +
                "Please enter option number:\n" +
                "1. Driver system management\n" +
                "2. Truck system management\n" +
                "3. Transport system management\n" +
                "4. Document system management\n" +
                "5. Exit");
        return getChoice(1, 5);
    }

    private static int getChoice(int a, int b)
    {
        int choice = 0;
        do {
            System.out.println("Enter a value in the range between " + a + " and " + b);
            choice = reader.nextInt();
        }while(choice > b | choice < a);
        return choice;
    }

    private static int menuDSM()
    {
        System.out.println("Driver system management\n" +
                "Please enter option number:\n" +
                "1. Add driver\n" +
                "2. Delete driver\n" +
                "3. Update driver lisence type\n" +
                "4. Back");
        return getChoice(1, 4);
    }

    private static int menuTSM()
    {
        System.out.println("Truck system management:\n" +
                "1. Add truck\n" +
                "2. Delete truck\n" +
                "3. Back");
        return getChoice(1, 3);
    }
    private static void truckSystemManagement()
    {
        boolean back = false;
        while(!back) {
            switch (menuTSM())
            {
                case 1:
                    addTruck();
                    break;
                case 2:
                    removeTruck();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
    private static void addTruck()
    {
        //TODO: Check validation of the input
        System.out.println("Enter truck license number:");
        int licenseNumber = reader.nextInt();
        LicenseTypes truckModel = getTruckModel();
        System.out.println("Enter the weight of the truck:");
        int netWeight = reader.nextInt();
        System.out.println("Enter the maximum weight that a truck can load:");
        int maxCapacityWeight = reader.nextInt();
        service.addTruck(licenseNumber, truckModel, netWeight, maxCapacityWeight);
        //TODO: Take the result
    }
    private static void removeTruck()
    {
        //TODO: Check validation of the input
        System.out.println("Enter truck license number:");
        int licenseNumber = reader.nextInt();
        service.removeTruck(licenseNumber);
        //TODO: Take the result
    }
    private static LicenseTypes getTruckModel()
    {
        String[] truckModel = {"B", "C1", "C", "CE"};
        System.out.println("Enter truck model:\n" +
                "1. B\n" +
                "2. C1\n" +
                "3. C\n" +
                "4. C+E");

        return LicenseTypes.valueOf(truckModel[getChoice(1, 4) - 1]);
    }


    //-----------------------------------------------------
    /*
    private static void transportManagement()
    {

        switch (1)
        {
            case 1:
                TransportOrder to = getTransportOrder();
                service.addTransportOrder(to.getSrcID(), to.getDstID(), to.getProductList());
                break;
            case 2:
                transportSystemManagement();
                break;
            default:
        }
    }

    private static TransportOrder getTransportOrder()
    {
        //TODO: Check validation of the input and enable to get input of many dests and srcs
        System.out.println("Get new transport order:");
        System.out.println("Enter source ID:");
        int srcID = reader.nextInt();
        System.out.println("Enter destination ID:");
        int dstID = reader.nextInt();
        TransportOrder to = new TransportOrder(srcID, dstID);
        getProductsList(to);
        if(to.isValidOrder())
        {
            return to;
        }
        System.out.println("The order of the shipment is invalid!");
        return null;
    }
    private static void getProductsAndCount(TransportOrder to)
    {
        System.out.println("Enter the product name: ");
        String productName = reader.next();
        System.out.println("Enter the required quantity of the product: ");
        int productCount = reader.nextInt();
        //TODO: add input validation
        to.addProduct(productName, productCount);
    }
    private static int productListMenu()
    {
        System.out.println("Product lists:\n" +
                "1. Add product\n" +
                "2. Remove product\n" +
                "3. Update product quantity\n" +
                "4. Close order");
        return getChoice(1, 4);
    }
    private static void getProductsList(TransportOrder to)
    {
        boolean close = false;
        do {
            switch (productListMenu())
            {
                case 1:
                    getProductsAndCount(to);
                    break;
                case 2:
                    //TODO: Feature - getProductsAndCount(to);
                    break;
                case 3:
                    //TODO: Feature - getProductsAndCount(to);
                    break;
                case 4:
                    close = true;
                    break;
                default:
            }
        }while(!close);
    }




    private int mainMenuTD()
    {
        System.out.println("Truck driver menu:\n" +
                "1. Weight report");
        return getChoice(1, 1);
    }
    private void truckDriver()
    {
        switch (mainMenuTD())
        {
            case 1:
                weightReport();
                break;
            case 2:
                //TODO: Truck system management
                break;
            default:
        }
    }
    private void weightReport()
    {

    }
    */
}