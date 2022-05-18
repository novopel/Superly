package Presentation.Screens;

public class DocumentMenu extends Screen{
    private static final String[] menuOptions = {
            "Get transport document",    //1
            "Get destination document",  //2
            "Exit"                       //3
    };
    public DocumentMenu(Screen caller) {
        super(caller, menuOptions);
    }

    @Override
    public void run() {
        System.out.println("\nWelcome to the Document Management Menu!");
        int option = 0;
        while (option != 4 && option != 5) {
            option = runMenu();
            try {
                switch (option) {
                    case 1:
                        getTransportDocument();
                        break;
                    case 2:
                        getDestinationDocument();
                        break;
                    case 3:
                        endRun();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again");
            }
        }
    }

    private void getDestinationDocument() {
        int ddSN = 0;
        try {
            ddSN = getSNOfDocument("Destination");
            //DestinationDocument dd = controller.getDestinationDocument(ddSN);
            //TODO: td.display();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getTransportDocument() {
        int tdSN = 0;
        try {
            tdSN = getSNOfDocument("Transport");
            //TransportDocument td = controller.getTransportDocument(tdSN);
            //TODO: td.display();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private int getSNOfDocument(String docType)
    {
        System.out.println("Enter " + docType + " document serial number:");
        int serialNumber = scanner.nextInt();
        while(serialNumber > 0){
            System.out.println("Please insert legal serial number:");
            serialNumber = scanner.nextInt();
        }
        return serialNumber;
    }
}
