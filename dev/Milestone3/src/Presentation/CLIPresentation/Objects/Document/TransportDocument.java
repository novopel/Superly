package Presentation.CLIPresentation.Objects.Document;

import java.util.List;

public class TransportDocument extends Document {
    private int transportID;
    private String date;
    private int truckNumber;
    private String driverName;
    private List<Integer> destinationDocs;
    private boolean doRedesign;
    private String redesign;
    public TransportDocument(Domain.Service.Objects.Document.TransportDocument transportDocument) {
        super(transportDocument.getDocumentSN());
        transportID = transportDocument.getTransportID();
        date = transportDocument.getStartTime();
        truckNumber = transportDocument.getTruckNumber();
        driverName = transportDocument.getDriverName();
        destinationDocs = transportDocument.getDestinationDocuments();
        doRedesign = transportDocument.isDoRedesign();
        redesign = transportDocument.getRedesign();
    }

    @Override
    public void display() {
        System.out.println("Transport Document:\n" +
                "Document SN: " + getDocumentSN() + "\n" +
                "Transport ID: " + transportID + "\n" +
                "Date: " + date + "\n" +
                "Truck Number: " + truckNumber + "\n" +
                "Driver Name: " + driverName);
        printDestDocsList();
        if(doRedesign)
        {
            System.out.println("Redesign:\n" + redesign);
        }
    }
    public String webDisplay(){
        String format = "Transport Document:\n" +
                "Document SN: " + getDocumentSN() + "\n" +
                "Transport ID: " + transportID + "\n" +
                "Date: " + date + "\n" +
                "Truck Number: " + truckNumber + "\n" +
                "Driver Name: " + driverName + "\n";
        format = format+"Destination Documents SN:"+"\n";
        for(int i = 0; i < destinationDocs.size(); i++)
        {
            format = format + "\t" + (i + 1) + " - " + destinationDocs.get(i)+"\n";
        }
        return format;
    }
    private void printDestDocsList()
    {
        System.out.println("Destination Documents SN:");
        for(int i = 0; i < destinationDocs.size(); i++)
        {
            System.out.println("\t" + (i + 1) + " - " + destinationDocs.get(i));
        }
    }
}
