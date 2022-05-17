package Domain.ServiceLayer.InventoryObjects;

import Globals.Defect;

import java.time.LocalDate;
import java.util.Date;

public class DefectiveItemReport {
    private final int productID;
    private final int storeID;
    private final int amount;
    private final int employeeID;
    private final boolean inWarehouse;
    private final String description;
    private final LocalDate date;
    private final Defect defect;
    public DefectiveItemReport(Domain.BusinessLayer.Inventory.DefectiveItems report) {
        this.productID=report.getProductID();
        this.storeID=report.getStoreID();
        this.amount=report.getAmount();
        this.employeeID = report.getEmployeeID();
        this.inWarehouse = report.getInWarehouse();
        this.description=report.getDescription();
        this.date=report.getDate();
        this.defect=report.getDefect();
    }

    @Override
    public String toString() {
        return "DefectiveItemReport{" +
                "productID=" + productID +
                ", storeID=" + storeID +
                ", amount=" + amount +
                ", employeeID=" + employeeID +
                ", inWarehouse=" + inWarehouse +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", defect=" + defect +
                '}';
    }
}