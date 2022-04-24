package Domain.Business.Objects;

import Domain.DAL.Objects.DShift;
import Domain.Service.ServiceShiftFactory;
import Globals.Enums.JobTitles;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Shift {
    private static final String employeeAlreadyInShiftErrorMsg = "this employee already in this shift";
    private static final String employeeNotInShiftErrorMsg = "Employee %o was not in the shift";

    private static final int MIN_CARRIERS= 1;
    private static final int MIN_CASHIERS = 1;
    private static final int MIN_STOREKEEPERS= 1;
    private static final int MIN_SORTERS = 1;
    private static final int MIN_HR_MANAGERS = 0;
    private static final int MIN_LOGISTICS_MANAGERS = 0;
    private final DShift dShift; // represent of this object in the DAL
    // properties
    private final Date workday;
    private int shiftManagerId;
    private int carrierCount;
    private int cashierCount;
    private int storekeeperCount;
    private int sorterCount;
    private int hr_managersCount;
    private int logistics_managersCount;

    private Set<Integer> carrierIDs;
    private Set<Integer> cashierIDs;
    private Set<Integer> storekeeperIDs;
    private Set<Integer> sorterIDs;
    private Set<Integer> hr_managerIDs;
    private Set<Integer> logistics_managerIDs;

    // constructors
    public Shift(Date workday, int shiftManagerId,
                 int carrierCount, int cashierCount, int storekeeperCount, int sorterCount, int hr_managersCount, int logistics_managersCount,
                 Set<Integer> carrierIDs, Set<Integer> cashierIDs, Set<Integer> storekeeperIDs, Set<Integer> sorterIDs, Set<Integer> hr_managerIDs, Set<Integer> logistics_managerIDs) throws Exception {
        this.workday = workday;
        this.shiftManagerId = shiftManagerId;

        checkCountValidity(carrierCount, MIN_CARRIERS, JobTitles.Carrier);
        this.carrierCount = carrierCount;
        checkCountValidity(cashierCount, MIN_CASHIERS, JobTitles.Cashier);
        this.cashierCount = cashierCount;
        checkCountValidity(storekeeperCount, MIN_STOREKEEPERS, JobTitles.Storekeeper);
        this.storekeeperCount = storekeeperCount;
        checkCountValidity(sorterCount, MIN_SORTERS, JobTitles.Sorter);
        this.sorterCount = sorterCount;
        checkCountValidity(hr_managersCount, MIN_HR_MANAGERS, JobTitles.HR_Manager);
        this.hr_managersCount = hr_managersCount;
        checkCountValidity(logistics_managersCount, MIN_LOGISTICS_MANAGERS, JobTitles.Logistics_Manager);
        this.logistics_managersCount = logistics_managersCount;

        this.carrierIDs = new HashSet<>(cashierIDs);
        this.cashierIDs = new HashSet<>(cashierIDs);
        this.storekeeperIDs = new HashSet<>(storekeeperIDs);
        this.sorterIDs = new HashSet<>(sorterIDs);
        this.hr_managerIDs = new HashSet<>(hr_managerIDs);
        this.logistics_managerIDs = new HashSet<>(logistics_managerIDs);

        this.dShift = new DShift(workday, employeesId, shiftManager.getId());
    }

    public Shift(Date workday) throws Exception {
        this(workday, -1,
                MIN_CARRIERS, MIN_CASHIERS, MIN_STOREKEEPERS, MIN_SORTERS, MIN_HR_MANAGERS, MIN_LOGISTICS_MANAGERS,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    public Shift(DShift dShift) {
        this.dShift = dShift;

        this.workday = dShift.getWorkday();
        this.shiftManagerId = dShift.getShiftManagerId();

        this.carrierCount = dShift.getCarrierCount();
        this.cashierCount= dShift.getCashierCount();
        this.storekeeperCount = dShift.getStorekeeperCount();
        this.sorterCount = dShift.getSorterCount();
        this.hr_managersCount = dShift.getHr_managersCount();
        this.logistics_managersCount = dShift.getLogistics_managersCount();

        this.carrierIDs = new HashSet<>(dShift.getCarrierIDs());
        this.cashierIDs = new HashSet<>(dShift.getCashierIDs());
        this.storekeeperIDs = new HashSet<>(dShift.getStorekeeperIDs());
        this.sorterIDs = new HashSet<>(dShift.getSorterIDs);
        this.hr_managerIDs = new HashSet<>(dShift.getHr_managerIDs());
        this.logistics_managerIDs = new HashSet<>(dShift.getLogistics_managerIDs());
    }

    public Date getDate() {
        return workday;
    }

    public int getShiftManagerId() {
        return shiftManagerId;
    }

    public void setShiftManagerId(int shiftManagerId) {
        this.shiftManagerId = shiftManagerId;
    }

    public DShift getdShift() {
        return dShift;
    }

    public int getCarrierCount() {
        return carrierCount;
    }

    public void setCarrierCount(int carrierCount) throws Exception {
        checkCountValidity(carrierCount, MIN_CARRIERS, JobTitles.Carrier);
        checkSizeValidity(carrierCount, carrierIDs.size());
        this.carrierCount = carrierCount;
    }

    public int getCashierCount() {
        return cashierCount;
    }

    public void setCashierCount(int cashierCount) throws Exception {
        checkCountValidity(cashierCount, MIN_CASHIERS, JobTitles.Cashier);
        checkSizeValidity(cashierCount, cashierIDs.size());
        this.cashierCount = cashierCount;
    }

    public int getStorekeeperCount() {
        return storekeeperCount;
    }

    public void setStorekeeperCount(int storekeeperCount) throws Exception {
        checkCountValidity(storekeeperCount, MIN_STOREKEEPERS, JobTitles.Storekeeper);
        checkSizeValidity(storekeeperCount, storekeeperIDs.size());
        this.storekeeperCount = storekeeperCount;
    }

    public int getSorterCount() {
        return sorterCount;
    }

    public void setSorterCount(int sorterCount) throws Exception {
        checkCountValidity(sorterCount, MIN_SORTERS, JobTitles.Sorter);
        checkSizeValidity(sorterCount, sorterIDs.size());
        this.sorterCount = sorterCount;
    }

    public int getHr_managersCount() {
        return hr_managersCount;
    }

    public void setHr_managersCount(int hr_managersCount) throws Exception {
        checkCountValidity(hr_managersCount, MIN_HR_MANAGERS, JobTitles.HR_Manager);
        checkSizeValidity(hr_managersCount, hr_managerIDs.size());
        this.hr_managersCount = hr_managersCount;
    }

    public int getLogistics_managersCount() {
        return logistics_managersCount;
    }

    public void setLogistics_managersCount(int logistics_managersCount) throws Exception {
        checkCountValidity(logistics_managersCount, MIN_LOGISTICS_MANAGERS, JobTitles.Logistics_Manager);
        checkSizeValidity(logistics_managersCount, logistics_managerIDs.size());
        this.logistics_managersCount = logistics_managersCount;
    }

    public Set<Integer> getCarrierIDs() {
        return carrierIDs;
    }

    public void setCarrierIDs(Set<Integer> carrierIDs) throws Exception {
        checkSizeValidity(carrierCount, carrierIDs.size());
        this.carrierIDs = new HashSet<>(carrierIDs);
    }

    public Set<Integer> getCashierIDs() {
        return cashierIDs;
    }

    public void setCashierIDs(Set<Integer> cashierIDs) throws Exception {
        checkSizeValidity(cashierCount, cashierIDs.size());
        this.cashierIDs = new HashSet<>(cashierIDs);
    }

    public Set<Integer> getStorekeeperIDs() {
        return storekeeperIDs;
    }

    public void setStorekeeperIDs(Set<Integer> storekeeperIDs) throws Exception {
        checkSizeValidity(storekeeperCount, storekeeperIDs.size());
        this.storekeeperIDs = new HashSet<>(storekeeperIDs);
    }

    public Set<Integer> getSorterIDs() {
        return sorterIDs;
    }

    public void setSorterIDs(Set<Integer> sorterIDs) throws Exception {
        checkSizeValidity(sorterCount, sorterIDs.size());
        this.sorterIDs = new HashSet<>(sorterIDs);
    }

    public Set<Integer> getHr_managerIDs() {
        return hr_managerIDs;
    }

    public void setHr_managerIDs(Set<Integer> hr_managerIDs) throws Exception {
        checkSizeValidity(hr_managersCount, hr_managerIDs.size());
        this.hr_managerIDs = new HashSet<>(hr_managerIDs);
    }

    public Set<Integer> getLogistics_managerIDs() {
        return logistics_managerIDs;
    }

    public void setLogistics_managerIDs(Set<Integer> logistics_managerIDs) throws Exception {
        checkSizeValidity(logistics_managersCount, logistics_managerIDs.size());
        this.logistics_managerIDs = new HashSet<>(logistics_managerIDs);
    }

    public abstract Domain.Service.Objects.Shift accept(ServiceShiftFactory factory);

    private void checkCountValidity(int count, int minimum, JobTitles type) throws Exception {
        if (count < minimum)
            throw new Exception(String.format("A shift can't have less than %s %s(s)", minimum, type));
    }

    private void checkSizeValidity(int count, int size) throws Exception {
        if (count < size)
            throw new Exception("A shift can't hold more employees more than configured count");
    }
}
