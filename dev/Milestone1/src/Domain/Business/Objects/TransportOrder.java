package Domain.Business.Objects;

import Domain.Business.Objects.Site.Destination;
import Domain.Business.Objects.Site.Source;

import java.util.HashMap;

public class TransportOrder {
    private static int incID = 0;
    private int ID;
    private int srcID;
    private int dstID;
    private HashMap<Integer, Integer> productList;
    private int transportID;

    public TransportOrder(int src, int dst) {
        ID = incID++;
        this.srcID = src;
        this.dstID = dst;
        transportID = -1;
        this.productList = new HashMap<>();
    }

    public TransportOrder(int src, int dst, HashMap<Integer, Integer> productList) {
        ID = incID++;
        this.srcID = src;
        this.dstID = dst;
        this.productList = productList;
    }

    public TransportOrder(int ID, int srcID, int dstID, int transportID) {
        this.ID = ID;
        this.srcID = srcID;
        this.dstID = dstID;
        this.transportID = transportID;
    }

    public int getID() {
        return ID;
    }

    public int getSrc() {
        return srcID;
    }

    public void setSrc(int src) {
        this.srcID = src;
    }

    public int getDst() {
        return dstID;
    }

    public void setDst(int dst) {
        this.dstID = dst;
    }

    public HashMap<Integer, Integer> getProductList() {
        return productList;
    }

    public void setProductList(HashMap<Integer, Integer> productList) {
        this.productList = productList;
    }

    public int getTransportID() {
        return transportID;
    }

    public void setTransportID(int transportID) {
        this.transportID = transportID;
    }

    //TODO need to see if delete the function
    private HashMap<Integer, Integer> splitProductList()
    {
        HashMap<Integer, Integer> newProductList = new HashMap<>();
        for (Integer item: productList.keySet()) {
            if (productList.size() / 2 > newProductList.size())
            {
                newProductList.put(item, productList.get(item));
            }
        }
        productList.remove(newProductList);
        return newProductList;
    }


}
