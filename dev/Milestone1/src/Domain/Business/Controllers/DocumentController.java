package Domain.Business.Controllers;


import Domain.Business.Objects.Document.DestinationDocument;
import Domain.Business.Objects.Document.TransportDocument;

import java.util.HashMap;

public class DocumentController {
    private HashMap<Integer, TransportDocument> transportDocuments;
    private HashMap<Integer, DestinationDocument> destinationDocuments;

    public DocumentController() {
        transportDocuments = new HashMap<>();
        destinationDocuments = new HashMap<>();
    }



    public void uploadDestinationDocument(DestinationDocument document) throws Exception {
        if(document != null && !destinationDocuments.containsKey(document.getSN()))
        {
            destinationDocuments.put(document.getSN(), document);
        }
        throw new Exception("The document already exist!");
    }

    public void updateDestinationDocument(DestinationDocument document) throws Exception {
        if(document != null && destinationDocuments.containsKey(document.getSN()))
        {
            destinationDocuments.remove(document.getSN());
            destinationDocuments.put(document.getSN(), document);
        }
        throw new Exception("The document you requested does not exist!");
    }

    public DestinationDocument getDestinationDocument(int destinationDocumentSN) throws Exception {
        if(destinationDocuments.containsKey(destinationDocumentSN))
        {
            return destinationDocuments.get(destinationDocumentSN);
        }
        throw new Exception("The document you requested does not exist!");
    }

    public void uploadTransportDocument(TransportDocument document) throws Exception {
        if(document != null && !transportDocuments.containsKey(document.getSN()))
        {
            transportDocuments.put(document.getSN(), document);
        }
        throw new Exception("The document already exist!");
    }

    public void updateTransportDocument(TransportDocument document) throws Exception {
        if(document != null && transportDocuments.containsKey(document.getSN()))
        {
            transportDocuments.remove(document.getSN());
            transportDocuments.put(document.getSN(), document);
        }
        throw new Exception("The document you requested does not exist!");
    }

    public TransportDocument getTransportDocument(int transportDocumentSN) throws Exception {
        if(transportDocuments.containsKey(transportDocumentSN))
        {
            return transportDocuments.get(transportDocumentSN);
        }
        throw new Exception("The document you requested does not exist!");
    }
}
