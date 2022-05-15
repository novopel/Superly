package Domain.Business.Objects;
import Domain.DAL.Controllers.ShiftDataMappers.ShiftDataMapper;
import Domain.Service.ServiceShiftFactory;
import Globals.Enums.ShiftTypes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public  class EveningShift extends Shift {

    public EveningShift(LocalDate workday, String managerId, int carrierCount, int cashierCount, int storekeeperCount, int sorterCount, int hr_managerCount, int logistics_managerCount,int transportManagersCount) throws Exception {
        super(workday, managerId, carrierCount, cashierCount, storekeeperCount, sorterCount, hr_managerCount,logistics_managerCount,transportManagersCount);
    }

    public EveningShift(LocalDate workday, String managerId, int carrierCount, int cashierCount, int storekeeperCount, int sorterCount, int hr_managerCount, int logistics_managerCount,int transportManagersCount
                         , Set<String> carrierIDs, Set<String> cashierIDs, Set<String> storekeeperIDs, Set<String> sorterIDs, Set<String> hr_managerIDs, Set<String> logistics_managerIDs, Set<String> transportManagersIDs
    ) throws Exception {
        super(workday, managerId, carrierCount, cashierCount, storekeeperCount, sorterCount, hr_managerCount,logistics_managerCount,transportManagersCount,carrierIDs,
                cashierIDs,
                storekeeperIDs,
                sorterIDs,
                hr_managerIDs,
                logistics_managerIDs,
                 transportManagersIDs );
    }

    public ShiftTypes getType() {
        return ShiftTypes.Evening;
    }

    @Override
    public Domain.Service.Objects.EveningShift accept(ServiceShiftFactory factory) {
        return factory.createServiceShift(this);
    }


    @Override
    public void setHr_managersCount(int hr_managersCount) throws Exception {
        validateManagerialCount(hr_managersCount);
        super.setHr_managersCount(hr_managersCount);
    }

    @Override
    public void setLogistics_managersCount(int logistics_managersCount) throws Exception {
        validateManagerialCount(logistics_managersCount);
        super.setLogistics_managersCount(logistics_managersCount);
    }

    private void validateManagerialCount(int count) throws Exception {
        if (count > 0)
            throw new Exception("Evening shifts don't have managerial slots");
    }

    @Override
    public String printDayAndType() {
        return "Evening- " + getWorkday().toString();
    }

    @Override
    public void save(ShiftDataMapper shiftDataMapper) throws SQLException {
        shiftDataMapper.save(this);
    }

    @Override
    public void update(ShiftDataMapper shiftDataMapper) throws SQLException {
        shiftDataMapper.update(this);
    }
}
