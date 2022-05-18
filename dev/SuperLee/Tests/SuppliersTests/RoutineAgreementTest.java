package SuppliersTests;

import Domain.BusinessLayer.Supplier.Agreement.RoutineAgreement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RoutineAgreementTest {

    private RoutineAgreement agreement;
    private List<Integer> days;

    @BeforeEach
    public void setUp(){
        days = new ArrayList<>();
        days.add(1); days.add(2); days.add(3); days.add(4); days.add(5); days.add(6); days.add(7);
        agreement = new RoutineAgreement(days);
    }

    @Test
    public void test_isTransporting(){
        assertTrue(agreement.isTransporting());
    }

    @Test
    public void test_getDaysOfDelivery(){
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3); list.add(4); list.add(5); list.add(6); list.add(7);

        assertEquals(list, agreement.getDaysOfDelivery());
    }

    @Test
    public void test_setDaysOfDelivery(){
        String s = "1 2 3 8";
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);

        try{
            agreement.setDaysOfDelivery(s, 1, null);

            assertEquals(list, agreement.getDaysOfDelivery());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_addDaysOfDelivery(){
        String s = "5 6";
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(5); list.add(6);

        try{
            agreement.setDaysOfDelivery("1 2", 1, null);

            agreement.addDaysOfDelivery(s);

            assertEquals(list, agreement.getDaysOfDelivery());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_removeDayOfDelivery(){
        agreement.removeDayOfDelivery(5);

        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3); list.add(4); list.add(6); list.add(7);

        assertEquals(list, agreement.getDaysOfDelivery());
    }
}
