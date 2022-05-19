package Domain.Business.Objects.Document;

import Domain.Service.ServiceDocumentFactory;

import java.time.LocalDateTime;
import java.util.List;
//TODO the transport document need to hold the destination documents
public class TransportDocument extends Document{
    private int transportID;
    private LocalDateTime startTime;

    private int truckNumber;
    private String driverName;
    private List<Integer> srcsID;
    private List<Integer> destsID;
    private boolean doRedesign;
    private String redesign;//Write what do?

    public TransportDocument(LocalDateTime startTime, int truckNumber, String driverName, List<Integer> srcsID, List<Integer> destsID) {
        this.startTime = startTime;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.srcsID = srcsID;
        this.destsID = destsID;
        this.doRedesign = false;
        this.redesign = "";
    }

    public TransportDocument(int transportID, LocalDateTime startTime, int truckNumber, String driverName, List<Integer> srcsID, List<Integer> destsID) {
        this.transportID = transportID;
        this.startTime = startTime;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.srcsID = srcsID;
        this.destsID = destsID;
        this.doRedesign = false;
        this.redesign = "";
    }

    public TransportDocument(int transportID, LocalDateTime startTime, int truckNumber, String driverName, List<Integer> srcsID, List<Integer> destsID, String redesign) {
        this.transportID = transportID;
        this.startTime = startTime;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.srcsID = srcsID;
        this.destsID = destsID;
        this.doRedesign = true;
        this.redesign = redesign;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(int truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public List<Integer> getSrcsID() {
        return srcsID;
    }

    public void setSrcsID(List<Integer> srcsID) {
        this.srcsID = srcsID;
    }

    public List<Integer> getDestsID() {
        return destsID;
    }

    public void setDestsID(List<Integer> destsID) {
        this.destsID = destsID;
    }

    public boolean isDoRedesign() {
        return doRedesign;
    }

    public void setDoRedesign(boolean doRedesign) {
        this.doRedesign = doRedesign;
    }

    public String getRedesign() {
        return redesign;
    }

    public void setRedesign(String redesign) {
        this.redesign = redesign;
    }

    @Override
    public Domain.Service.Objects.Document.Document accept(ServiceDocumentFactory serviceDocumentFactory) {
        return serviceDocumentFactory.createServiceDocument(this);
    }
}
