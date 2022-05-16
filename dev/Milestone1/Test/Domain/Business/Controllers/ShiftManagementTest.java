package Domain.Business.Controllers;

import Carrier;
import Domain.DAL.Controllers.EmployeeMappers.EmployeeDataMapper;
import Domain.DAL.Controllers.ShiftDataMappers.ShiftDataMapper;
import Globals.Enums.Certifications;
import Globals.Enums.JobTitles;
import Globals.Enums.ShiftTypes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ShiftManagementTest {
    static LocalDate date=LocalDate.parse("2021-06-19");
    static ShiftController shiftController = new ShiftController();
    static EmployeeController employeeController = new EmployeeController();
    static Set<Certifications> certifications = new HashSet<>();
    static EmployeeDataMapper employeeDataMapper =new EmployeeDataMapper();
    static ShiftDataMapper shiftDataMapper = new ShiftDataMapper();

    @BeforeClass
    public static void beforeAll() throws Exception {
        employeeDataMapper.delete("206618175");
        employeeDataMapper.delete("2061");
        shiftDataMapper.delete(date,ShiftTypes.Morning);
    }

    @AfterClass
    public static void afterAll() throws Exception {
        employeeDataMapper.delete("206618175");
        employeeDataMapper.delete("2061");
        shiftDataMapper.delete(date,ShiftTypes.Morning);
    }

    @Before
    public void before() throws Exception {
        employeeController.registerEmployee(JobTitles.Cashier,"206618175","ofek","d",10,"con",date,certifications);
        employeeController.registerEmployee(JobTitles.Carrier,"1","ofek","d",10,"con",date,certifications);
        shiftController.createShift(date,ShiftTypes.Morning,"206618175",2,2,2,2,2,2,2);
    }

    @After
    public void after() throws Exception {
        employeeDataMapper.delete("206618175");
        employeeDataMapper.delete("2061");
        shiftDataMapper.delete(date, ShiftTypes.Morning);
    }

    @org.junit.Test
    public void EditShiftCarriers() {
        try {
            certifications.add(Certifications.ShiftManagement);
            Set<String> carriersId = new HashSet<>();
            carriersId.add("1");
            shiftController.editShiftCarrierIDs(date,ShiftTypes.Morning,carriersId);
            assertEquals(shiftController.getShift(date,ShiftTypes.Morning).getCarrierIDs().size(),1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @org.junit.Test
    public void RegisterWorkDay() {
        try {
            assertNotNull(shiftController.getShift(date,ShiftTypes.Morning));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void RemoveWorkDay() {
        ShiftController shiftController = new ShiftController();
        shiftController.loadData();
        try {
            assertNotNull(shiftController.getShift(date,ShiftTypes.Morning));
            shiftController.removeShift(date,ShiftTypes.Morning);
            assertNull(shiftDataMapper.get(date,ShiftTypes.Morning));
        } catch (Exception e) {
            assertTrue(true);
        }
    }

}