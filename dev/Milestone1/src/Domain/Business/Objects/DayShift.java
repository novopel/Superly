package Domain.Business.Objects;

import Domain.DAL.Objects.DShift;

import java.util.Date;
import java.util.Set;

public class DayShift extends Shift {
    public DayShift(DShift dShift, Set<Employee> employees) {
        super(dShift,employees);
    }

    public DayShift(Date date,  Employee shiftManager){
        super(date,"Day",shiftManager);
    }
}
