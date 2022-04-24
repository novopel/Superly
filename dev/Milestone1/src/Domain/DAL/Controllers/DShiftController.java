package Domain.DAL.Controllers;
import Domain.DAL.Abstract.DalController;
import Domain.DAL.Objects.DShift;
import Globals.Enums.ShiftTypes;

import java.util.*;

public class  DShiftController extends DalController<DShift> {
    // properties
    private DEmployeeShiftController dEmployeeShiftController;
    private DMorningShiftController dMorningShiftController;
    private DEveningShiftController dEveningShiftController;

    // constructor
    public DShiftController() {
        super("placeHolder");
        this.dEmployeeShiftController = new DEmployeeShiftController();
        this.dMorningShiftController = new DMorningShiftController();
        this.dEveningShiftController = new DEveningShiftController();
    }


    // functions
    @Override
    public Set<DShift> loadData() {
        Set<DShift> shifts= new HashSet<>();
        Set<? extends DShift> morningShifts = dMorningShiftController.loadData();
        Set<? extends DShift> eveningShifts = dEveningShiftController.loadData();
        shifts.addAll(morningShifts);
        shifts.addAll(eveningShifts);
        loadEmployees(morningShifts,ShiftTypes.Morning);
        loadEmployees(eveningShifts,ShiftTypes.Evening);
        persistShifts(shifts);
        return shifts;
    }

    private void persistShifts(Set<DShift> shifts) {
        shifts.forEach((dShift -> dShift.setPersist(true)));
    }

    private void loadEmployees(Set<? extends DShift> shifts,ShiftTypes type) {
        Map<Date,DShift> shiftsMap = new HashMap<>();
        for(DShift shift:shifts)
            shiftsMap.put(shift.getWorkday(),shift);
        dEmployeeShiftController.loadData().stream().filter((pair)->pair.getKey().getValue().equals(type)).forEach((pair)->shiftsMap.get(pair.getKey().getKey()).setEmployeesId(pair.getValue()));
    }

    @Override
    public void deleteAll() {

    }
}

