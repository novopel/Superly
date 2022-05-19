package InventoryTests;

import Domain.BusinessLayer.Inventory.Product;
import Domain.BusinessLayer.InventoryController;
import Domain.PersistenceLayer.Controllers.CategoryDataMapper;
import Domain.PersistenceLayer.Controllers.ProductDataMapper;
import Domain.PersistenceLayer.Controllers.StockReportDataMapper;
import Domain.PersistenceLayer.Controllers.StoreDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static java.util.Collections.max;
import static org.junit.jupiter.api.Assertions.*;

class InventoryControllerTest {
    private static InventoryController is;
    private static int maxStoreCount;

    @BeforeAll
    public static void getMaxStoreCount() {
        is = new InventoryController();
        maxStoreCount = max(is.getStoreIDs());
    }

    @AfterAll
    public static void removeData() {
        StockReportDataMapper srdm = new StockReportDataMapper();
        ProductDataMapper pdm = new ProductDataMapper();
        pdm.removeTestProducts();
        CategoryDataMapper cdm = new CategoryDataMapper();
        cdm.removeTestCategories();
        StoreDAO storeDAO = new StoreDAO();
        Collection<Integer> stores = storeDAO.getAll();
        for (int store : stores) {
            if (store>maxStoreCount) {
                try {
                    srdm.remove(store);
                    storeDAO.remove(store);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void addStore() {
        //empty
        int currStore = max(is.getStoreIDs());
        List<Integer> currStores = new ArrayList<>(is.getStoreIDs());
//        assertIterableEquals(new ArrayList<>(), is.getStoreIDs());
        assertEquals(currStore+1, is.addStore());
        Integer[] actual = is.getStoreIDs().toArray(new Integer[0]);
        currStores.add(currStore+1);
        Integer[] expected = currStores.toArray(new Integer[0]);
        assertArrayEquals(actual, expected);
        assertThrows(IllegalArgumentException.class, ()->is.getAmountInStore(currStore+1, 1));

        assertEquals(currStore+2, is.addStore());
        actual = is.getStoreIDs().toArray(new Integer[0]);
        currStores.add(currStore+2);
        expected = currStores.toArray(new Integer[0]);
        assertArrayEquals(actual, expected);
        assertThrows(IllegalArgumentException.class, ()->is.getAmountInStore(currStore+2, 2));

        is.removeStore(currStore+2);
        assertEquals(currStore+3, is.addStore());
        actual = is.getStoreIDs().toArray(new Integer[0]);
        currStores.remove(Integer.valueOf(currStore+2));
        currStores.add(currStore+3);
        expected = currStores.toArray(new Integer[0]);
        assertArrayEquals(actual, expected);
        assertThrows(IllegalArgumentException.class, ()->is.getAmountInStore(currStore+2, 1));
        //need to add a cleanup method
    }

    @org.junit.jupiter.api.Test
    void removeStore() {
        //first
        Integer[] start = new ArrayList<Integer>(is.getStoreIDs()).toArray(new Integer[0]);
        int store = is.addStore();
        int cat = is.addCategory("TestCategory", 0).getID();
        int prod = is.newProduct("TestProduct", cat,2,2,"testManu").getId();
        is.addProductToStore(store, Arrays.asList(1), Arrays.asList(1), prod, 5,5);
        assertDoesNotThrow(()->is.getAmountInStore(store, prod));

        is.removeStore(store);
        Integer[] actual = is.getStoreIDs().toArray(new Integer[0]);
        assertArrayEquals(actual, start);
        assertThrows(IllegalArgumentException.class, ()->is.getAmountInStore(store, prod));
        assertThrows(IllegalArgumentException.class, () -> is.removeStore(store));
//        is.removeStore(1);
//        actual = is.getStoreIDs().toArray(new Integer[0]);
//        expected = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9};
//        assertArrayEquals(actual, expected);
//        assertThrows(IllegalArgumentException.class, ()->is.getAmountInStore(1, 3));
//
//        is.removeStore(2);
//        actual = is.getStoreIDs().toArray(new Integer[0]);
//        expected = new Integer[]{3, 4, 5, 6, 7, 8, 9};
//        assertArrayEquals(actual, expected);
//        assertThrows(IllegalArgumentException.class, ()->is.getAmountInStore(2, 3));
//
//        is.removeStore(6);
//        actual = is.getStoreIDs().toArray(new Integer[0]);
//        expected = new Integer[]{3, 4, 5, 7, 8, 9};
//        assertArrayEquals(actual, expected);
//        assertThrows(IllegalArgumentException.class, ()->is.getAmountInStore(6, 3));
//
//        assertThrows(IllegalArgumentException.class, () -> is.removeStore(6));
//        actual = is.getStoreIDs().toArray(new Integer[0]);
//        expected = new Integer[]{3, 4, 5, 7, 8, 9};
//        assertArrayEquals(actual, expected);
//        for (int i=0; i<expected.length; i++) {
//            int finalI = expected[i];
//            assertDoesNotThrow(()->is.getAmountInStore(finalI, 3));
//        }
    }

    @org.junit.jupiter.api.Test
    void getExpiredItemReportsByProductIllegalEntries() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate beforeTwoDays = LocalDate.now().minusDays(2);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate afterTwoDays = LocalDate.now().plusDays(2);
        List<Integer> pIDs = new ArrayList<>();
        pIDs.add(1);
        //empty
        assertIterableEquals(new ArrayList<>(), is.getExpiredItemReportsByProduct(yesterday, today, pIDs));
        //illegal - future
        assertThrows(IllegalArgumentException.class, () -> is.getExpiredItemReportsByProduct(tomorrow, afterTwoDays, pIDs));
        //illegal - start>end
        assertThrows(IllegalArgumentException.class, () -> is.getExpiredItemReportsByProduct(today, yesterday, pIDs));
    }

    @Test
    void getAmountsForMinOrdersTest() {
        int store1 = is.addStore();
        int store2 = is.addStore();
        int cat = is.addCategory("TestCategory", 0).getID();
        Product prod1 = is.newProduct("TestProduct", cat,2,2,"testManu");
        Product prod2 = is.newProduct("TestProduct", cat,2,2,"testManu");
        assertEquals(new HashMap<>(), is.getAmountsForMinOrders(prod1));
        assertEquals(new HashMap<>(), is.getAmountsForMinOrders(prod2));

        is.addProductToStore(store1,Arrays.asList(1), Arrays.asList(1),prod1.getId(), 100, 200);
        Map<Integer, Integer> singletonMap = new HashMap<>(Collections.singletonMap(store1, 200));
        assertEquals(singletonMap,is.getAmountsForMinOrders(prod1));
        is.addProductToStore(store2,Arrays.asList(1), Arrays.asList(1),prod1.getId(), 200, 300);
        singletonMap.put(store2, 300);
        assertEquals(singletonMap,is.getAmountsForMinOrders(prod1));

        prod1.addDelivery(store1, 100);
        singletonMap.remove(store1);
        assertEquals(singletonMap,is.getAmountsForMinOrders(prod1));
        prod1.addDelivery(store2, 100);
        singletonMap.put(store2, 200);
        assertEquals(singletonMap,is.getAmountsForMinOrders(prod1));
        prod1.addDelivery(store2, 100);
        singletonMap.remove(store2);
        assertEquals(singletonMap,is.getAmountsForMinOrders(prod1));
    }

}