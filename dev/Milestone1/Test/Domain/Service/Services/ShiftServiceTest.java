package Domain.Service.Services;
import Domain.Service.Objects.Result;
import Domain.Service.Objects.Shift;
import Globals.Enums.Certifications;
import Globals.Enums.JobTitles;
import Globals.Enums.ShiftTypes;
import org.junit.Test;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ShiftServiceTest {

    @Test
    public void shiftManagerTest() {
        ShiftService shiftService = new ShiftService();
        try {
            shiftService.createShift(LocalDate.parse("1998-07-25"), ShiftTypes.Morning,"5",6,6,6,4,4,4,6)
            Result<Shift> result = shiftService.getShift(LocalDate.parse("1998-07-25"), ShiftTypes.Morning);
            if (result.isError())
                fail(result.getError());
            Shift shift = result.getValue();
            assertEquals(6, shift.cashierCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createAndManageShift() {
        ShiftService shiftService = new ShiftService();
        shiftService.createShift(LocalDate.parse("2021-07-25"), ShiftTypes.Morning,"5",6,6,6,4,4,4);
        Set<String> ids = new HashSet<>();
        ids.add("6");
        ids.add("20");
        if(shiftService.editShiftCarrierIDs(LocalDate.parse("2021-07-25"),ShiftTypes.Morning,ids).isError())
            fail();
    }

    @Test
    public void getEmployeeShiftsForCurrentMonth(){
        ShiftService service = new ShiftService();
        EmployeeService service1 = new EmployeeService();
        Set<Certifications> certificationsSet=new HashSet<>();
        certificationsSet.add(Certifications.ShiftManagement);
        service1.registerEmployee(JobTitles.Cashier,"206618175","Ofek","***",10,"we just want to fire you but cant...",LocalDate.parse("2021-07-25"),certificationsSet);
        Result<String> a = service.getEmployeeWorkDetailsForCurrentMonth("206618175");
        if (a.isOk()) {
            System.out.println(a.getValue());
        } else {
            fail();
        }
    }

    @Test
    public void addEmployeeToShift(){
        ShiftService shiftService = new ShiftService();
        shiftService.createShift(LocalDate.parse("1998-07-25"), ShiftTypes.Morning,"5",6,6,6,4,4,4,6);
        shiftService.loadData();
        try {
            assertTrue(shiftService.getShift(LocalDate.parse("1999-07-25"), ShiftTypes.Morning).isError());
            assertTrue(shiftService.createShift(LocalDate.parse("1999-07-25"), ShiftTypes.Morning,"5",6,6,6,4,4,4).isOk());
            Result<Shift> result = shiftService.getShift(LocalDate.parse("1999-07-25"), ShiftTypes.Morning);
            if (result.isError())
                fail(result.getError());
            Shift shift = result.getValue();
            Set<String>  a = new HashSet<>();
            a.add("12");
            assertTrue(shiftService.editShiftHR_ManagerIDs(shift.date,ShiftTypes.Morning,a).isOk());
            assertTrue(shiftService.getShift(LocalDate.parse("1999-07-25"), ShiftTypes.Morning).getValue().hr_managerIDs.size()>0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}