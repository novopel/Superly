package BusinessTests;

import BusinessLayer.DiscountsAndSales.SaleToCustomer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Date;
import java.util.LinkedList;

public class SaleToCustomerTests {
    //Tests formula
    /*@Test
    public void testX() {
        try
        {

        }
        catch (Exception e)
        {

        }
    }*/
    @Test
    public void testIsUpcoming() {
        try {
            Date today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);

            Date tommorow = new Date();
            tommorow.setHours(0);
            tommorow.setMinutes(0);
            tommorow.setSeconds(0);
            tommorow.setHours(24);

            Date yesterday = new Date();
            yesterday.setHours(0);
            yesterday.setMinutes(0);
            yesterday.setSeconds(0);
            yesterday.setHours(-24);

            Date beforeTwoDays = new Date();
            beforeTwoDays.setHours(0);
            beforeTwoDays.setMinutes(0);
            beforeTwoDays.setSeconds(0);
            beforeTwoDays.setHours(-48);

            Date afterTwoDays = new Date();
            afterTwoDays.setHours(0);
            afterTwoDays.setMinutes(0);
            afterTwoDays.setSeconds(0);
            afterTwoDays.setHours(48);

            SaleToCustomer sale1 = new SaleToCustomer(0, yesterday, tommorow, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale2 = new SaleToCustomer(1, today, tommorow, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale3 = new SaleToCustomer(2, yesterday, today, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale4 = new SaleToCustomer(3, today, today, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale5 = new SaleToCustomer(4, beforeTwoDays, yesterday, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale6 = new SaleToCustomer(5, tommorow, afterTwoDays, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale7 = new SaleToCustomer(6, yesterday, yesterday, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale8 = new SaleToCustomer(7, tommorow, tommorow, 30, new LinkedList<>(), new LinkedList<>());

            Assertions.assertFalse(sale1.isUpcoming());
            Assertions.assertFalse(sale2.isUpcoming());
            Assertions.assertFalse(sale3.isUpcoming());
            Assertions.assertFalse(sale4.isUpcoming());
            Assertions.assertFalse(sale5.isUpcoming());
            Assertions.assertTrue(sale6.isUpcoming());
            Assertions.assertFalse(sale7.isUpcoming());
            Assertions.assertTrue(sale8.isUpcoming());
        } catch (Exception e) {
            Assertions.fail("isUpcoming isn't working");
        }
    }

    @Test
    public void testIsPassed() {
        try {
            Date today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);

            Date tommorow = new Date();
            tommorow.setHours(0);
            tommorow.setMinutes(0);
            tommorow.setSeconds(0);
            tommorow.setHours(24);

            Date yesterday = new Date();
            yesterday.setHours(0);
            yesterday.setMinutes(0);
            yesterday.setSeconds(0);
            yesterday.setHours(-24);

            Date beforeTwoDays = new Date();
            beforeTwoDays.setHours(0);
            beforeTwoDays.setMinutes(0);
            beforeTwoDays.setSeconds(0);
            beforeTwoDays.setHours(-48);

            Date afterTwoDays = new Date();
            afterTwoDays.setHours(0);
            afterTwoDays.setMinutes(0);
            afterTwoDays.setSeconds(0);
            afterTwoDays.setHours(48);

            SaleToCustomer sale1 = new SaleToCustomer(0, yesterday, tommorow, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale2 = new SaleToCustomer(1, today, tommorow, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale3 = new SaleToCustomer(2, yesterday, today, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale4 = new SaleToCustomer(3, today, today, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale5 = new SaleToCustomer(4, beforeTwoDays, yesterday, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale6 = new SaleToCustomer(5, tommorow, afterTwoDays, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale7 = new SaleToCustomer(6, yesterday, yesterday, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale8 = new SaleToCustomer(7, tommorow, tommorow, 30, new LinkedList<>(), new LinkedList<>());

            Assertions.assertFalse(sale1.isPassed());
            Assertions.assertFalse(sale2.isPassed());
            Assertions.assertFalse(sale3.isPassed());
            Assertions.assertFalse(sale4.isPassed());
            Assertions.assertTrue(sale5.isPassed());
            Assertions.assertFalse(sale6.isPassed());
            Assertions.assertTrue(sale7.isPassed());
            Assertions.assertFalse(sale8.isPassed());
        } catch (Exception e) {
            Assertions.fail("isPassed isn't working");
        }
    }

    @Test
    public void testIsActive() {
        try {
            Date today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);

            Date tommorow = new Date();
            tommorow.setHours(0);
            tommorow.setMinutes(0);
            tommorow.setSeconds(0);
            tommorow.setHours(24);

            Date yesterday = new Date();
            yesterday.setHours(0);
            yesterday.setMinutes(0);
            yesterday.setSeconds(0);
            yesterday.setHours(-24);

            Date beforeTwoDays = new Date();
            beforeTwoDays.setHours(0);
            beforeTwoDays.setMinutes(0);
            beforeTwoDays.setSeconds(0);
            beforeTwoDays.setHours(-48);

            Date afterTwoDays = new Date();
            afterTwoDays.setHours(0);
            afterTwoDays.setMinutes(0);
            afterTwoDays.setSeconds(0);
            afterTwoDays.setHours(48);

            SaleToCustomer sale1 = new SaleToCustomer(0, yesterday, tommorow, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale2 = new SaleToCustomer(1, today, tommorow, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale3 = new SaleToCustomer(2, yesterday, today, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale4 = new SaleToCustomer(3, today, today, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale5 = new SaleToCustomer(4, beforeTwoDays, yesterday, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale6 = new SaleToCustomer(5, tommorow, afterTwoDays, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale7 = new SaleToCustomer(6, yesterday, yesterday, 30, new LinkedList<>(), new LinkedList<>());
            SaleToCustomer sale8 = new SaleToCustomer(7, tommorow, tommorow, 30, new LinkedList<>(), new LinkedList<>());

            Assertions.assertTrue(sale1.isActive());
            Assertions.assertTrue(sale2.isActive());
            Assertions.assertTrue(sale3.isActive());
            Assertions.assertTrue(sale4.isActive());
            Assertions.assertFalse(sale5.isActive());
            Assertions.assertFalse(sale6.isActive());
            Assertions.assertFalse(sale7.isActive());
            Assertions.assertFalse(sale8.isActive());
        } catch (Exception e) {
            Assertions.fail("isActive isn't working");
        }
    }
}
