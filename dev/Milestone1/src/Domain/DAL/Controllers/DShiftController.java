package Domain.DAL.Controllers;
import Domain.DAL.Abstract.DTO;
import Domain.DAL.Abstract.DalController;
import Domain.DAL.Objects.DShift;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class  DShiftController extends DalController<DShift> {
    // properties
    private DEmployeeController dEmployeeController;
    private DEmployeeShiftController dEmployeeShiftController;

    // constructor
    public DShiftController() {
        super("placeHolder");
        this.dEmployeeController = new DEmployeeController();
        this.dEmployeeShiftController = new DEmployeeShiftController();
    }


    // functions
    @Override
    public Set<DShift> createFakeDTOs() {
        Set<DShift> shifts= new HashSet<>();
        try {
            shifts.add(new DShift(new SimpleDateFormat("dd-MM-yyyy").parse("14-07-1198"),"Day", dEmployeeController.createFakeDTOs().stream().map((DTO::getId)).collect(Collectors.toSet()),12));
            shifts.add(new DShift(new SimpleDateFormat("dd-MM-yyyy").parse("14-07-1198"),"night", dEmployeeController.createFakeDTOs().stream().map((DTO::getId)).collect(Collectors.toSet()),12));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return shifts;
    }

    @Override
    public Set<DShift> select() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
