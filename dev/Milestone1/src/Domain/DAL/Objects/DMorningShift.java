package Domain.DAL.Objects;

import Domain.Business.BusinessShiftFactory;
import Domain.Business.Objects.Shift;
import Domain.DAL.Controllers.DEmployeeShiftController;
import Globals.Enums.ShiftTypes;

import java.util.Date;
import java.util.Set;

public class DMorningShift extends DShift{


    public DMorningShift(Date workday, String shiftManagerId, int carrierCount, int cashierCount, int storekeeperCount, int sorterCount, int hr_managersCount, int logistics_managersCount, Set<String> employeesId) {
        super(workday,ShiftTypes.Morning,shiftManagerId, carrierCount, cashierCount, storekeeperCount, sorterCount, hr_managersCount, logistics_managersCount, employeesId);
    }

    public DMorningShift(Date workday,String shiftManagerId, int carrierCount, int cashierCount, int storekeeperCount, int sorterCount, int hr_managersCount, int logistics_managersCount) {
        super(workday, ShiftTypes.Morning, shiftManagerId, carrierCount, cashierCount, storekeeperCount, sorterCount, hr_managersCount, logistics_managersCount);
    }

    @Override
    public void save() {

    }

    @Override
    public Shift accept(BusinessShiftFactory businessShiftFactory, Set<String> carrierIDs, Set<String> cashierIDs, Set<String> storekeeperIDs, Set<String> sorterIDs, Set<String> hr_managerIDs, Set<String> logistics_managerIDs) {
        return businessShiftFactory.createServiceShift(this,carrierIDs,cashierIDs,storekeeperIDs,sorterIDs,hr_managerIDs,logistics_managerIDs);
    }

    @Override
    public ShiftTypes getType() {
        return ShiftTypes.Morning;
    }
}
