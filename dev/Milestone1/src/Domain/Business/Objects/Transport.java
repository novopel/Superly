package Domain.Business.Objects;

import Domain.Business.Objects.Document.TransportDocument;
import Domain.Business.Objects.Site.Destination;
import Domain.Business.Objects.Site.Source;
import Globals.Enums.ShiftTypes;
import Globals.Enums.ShippingAreas;
import Globals.Enums.TransportStatus;
import Globals.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transport {
    private static int incSN = 0;
    private int SN;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String driverName;
    private String driverID;
    private  int truckNumber;
    private  int truckWeight;
    private List<Integer> sourcesID;
    private List<Integer> destinationsID;
    private List<Integer> transportOrders;
    private HashMap<ShippingAreas, Integer> shippingAreas;
    private TransportStatus status;
    //TODO need to change the shift restart
    private Pair<LocalDate, ShiftTypes> shift = null;
    //
    //TODO maybe add filed of current site visited to check when the transport is over

    public Transport() {
        SN = incSN++;
        driverID = "";
        driverName = "";
        truckNumber = -1;
        truckWeight = -1;
        sourcesID = new ArrayList<>();
        destinationsID = new ArrayList<>();
        transportOrders = new ArrayList<>();
        status = TransportStatus.padding;
    }

    public int getSN() {
        return SN;
    }

    public void startTransport() throws Exception {
        if (status == TransportStatus.padding)
        {
            startTime = LocalDateTime.now();
            status = TransportStatus.inProgress;
        }
        throw new Exception("transport already started");
    }
    public void endTransport() throws Exception {
        if (status == TransportStatus.inProgress)
        {
            endTime = LocalDateTime.now();
            status = TransportStatus.done;
        }
        throw new Exception("transport is not in Progress");
    }
    public boolean isDoneTransport(){
        //TODO need to be implemented
        return false;
    }

    public boolean placeTruck(int licenseNumber)
    {
        if(truckNumber==-1){
            truckNumber = licenseNumber;
            return true;
        }
        return false;
    }

    public void placeDriver(String driverId,String driverName)
    {
        this.driverID = driverId;
        this.driverName = driverName;
    }
    public boolean driverPlaced()
    {
        return driverName != null;
    }

    public boolean truckPlaced()
    {
        return driverName != null;
    }

    public boolean readyToGo()
    {
        return !sourcesID.isEmpty() && !destinationsID.isEmpty() && driverPlaced() && truckPlaced();
    }
    public Pair<LocalDate,ShiftTypes> getShift(){
        return shift;
    }

    private void addShippingArea(ShippingAreas sa)
    {
        if(!shippingAreas.containsKey(sa))
        {
            shippingAreas.put(sa, 1);
        }
        else {
            shippingAreas.replace(sa, shippingAreas.get(sa) + 1);
        }
    }
    public boolean isPlacedTruck(){
        return truckNumber==-1;
    }
    public boolean isPlacedCarrier(){
        return !(driverName==""&&driverID=="");
    }
    private void removeShippingArea(ShippingAreas sa)
    {
        if(shippingAreas.get(sa) > 1)
        {
            shippingAreas.replace(sa, shippingAreas.get(sa) - 1);
        }
        else {
            shippingAreas.remove(sa);
        }
    }

    public String getDriverName() {
        return driverName;
    }

    public int getTruckNumber() {
        return truckNumber;
    }

    private List<Integer> getSrcIDs()
    {
        return sourcesID;
    }

    private List<Integer> getDstIDs()
    {
        return destinationsID;
    }
    public TransportDocument toDocument() {
        return new TransportDocument(startTime, truckNumber, driverName, getSrcIDs(), getDstIDs());
    }

    public void addOrder(TransportOrder order,ShippingAreas src,ShippingAreas dst)
    {
        sourcesID.add(order.getSrc());
        destinationsID.add(order.getDst());
        addShippingArea(src);
        addShippingArea(dst);
        transportOrders.add(order.getID());
    }

    public boolean updateWeight(int newWeight, int maxCapacityWeight) throws Exception {
        if(truckWeight+newWeight > maxCapacityWeight){
            status = TransportStatus.redesign;
            return false;
        }
        else{
            truckWeight = truckWeight+newWeight;
            return true;
        }
    }
    public TransportStatus getStatus(){
        return status;
    }
    public void changeStatus(TransportStatus stat){
        status = stat;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getTruckWeight() {
        return truckWeight;
    }
}
