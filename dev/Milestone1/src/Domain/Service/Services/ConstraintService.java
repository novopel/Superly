package Domain.Service.Services;

import Domain.Business.Controllers.ConstraintController;
import Domain.Service.Objects.*;
import Globals.Enums.ShiftTypes;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintService {

    private final ConstraintController controller = new ConstraintController();

    public Result<Object> loadData() {
        try {
            controller.loadData();
        }
        catch (Exception e){
            return Result.makeError(e.getMessage());
        }
        return Result.makeOk(null);
    }

    public void deleteData() {
    }

    public Result<Constraint> getConstraint(LocalDate workday, ShiftTypes shift){
        try {
            return Result.makeOk(new Constraint(controller.getConstraint(workday, shift)));
        }
        catch (Exception e){
            return Result.makeError(e.getMessage());
        }
    }

    public Result<Object> registerToConstraint(String id, LocalDate workday, ShiftTypes shift) {
        try {
            controller.registerToConstraint(id, workday, shift);
        }
        catch (Exception e){
            return Result.makeError(e.getMessage());
        }
        return Result.makeOk(null);
    }

    public Result<Object> unregisterFromConstraint(String id, LocalDate workday, ShiftTypes shift) {
        try {
            controller.unregisterFromConstraint(id, workday, shift);
        }
        catch (Exception e){
            return Result.makeError(e.getMessage());
        }
        return Result.makeOk(null);
    }

    public Result<Set<Constraint>> getEmployeeConstraintsBetween(String id, LocalDate today, LocalDate nextMonth) {
        try {
            Set<Constraint> constraints = controller.getEmployeeConstraintsBetween(id, today, nextMonth).stream().map(Constraint::new).collect(Collectors.toSet());
            return Result.makeOk(constraints);
        }catch (Exception e){
            return Result.makeError(e.getMessage());
        }
    }
}