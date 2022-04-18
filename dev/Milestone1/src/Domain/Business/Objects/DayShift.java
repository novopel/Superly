package Domain.Business.Objects;

import Domain.Business.Objects.Enums.ShiftType;
import Domain.DAL.Objects.DShift;

import java.util.Date;
import java.util.Set;

public class DayShift extends Shift {
    public DayShift(DShift dShift, Set<Employee> employees) {
        super(dShift,employees);
    }

    public DayShift(Date date,  Cashier shiftManager){
        super(date,shiftManager);
    }

    @Override
    public ShiftType getType() {
        return ShiftType.Day;
    }
}