package BusinessLayer;

import java.util.*;

public class InventoryController {
    private List<Integer> storeIds;
    private Map<Integer, Category> categories;
    private List<SaleToCustomer> sales;
    private Map<Integer, Product> products;
    private long itemsAmount;
    public InventoryController() {
        storeIds = new ArrayList<>();
        categories = new HashMap<>();
        sales = new ArrayList<>();
        products = new HashMap<>();
        itemsAmount = 0;
    }

    public void testInit() {
        //initialize stuff for tests
    }

    public void loadData() {

    }

    public void newProduct(int id, String name, Category category, int weight, double price) {
    }

    public void deleteProduct(int id) {
    }

    public void addSale(List<Integer> categories, List<Integer> products, int percent, Date start, Date end) {
        SaleToCustomer sale = new SaleToCustomer(sales.size(), start, end, percent, categories, products);
        sales.add(sale);
    }

    public List<DiscountFromSupplier> getDiscountFromSupplierHistory(int productID) {
        return null;
    }

    public List<SaleToCustomer> getSaleHistoryByProduct(int productID) {
        return null;
    }

    public List<SaleToCustomer> getSaleHistoryByCategory(int categoryID) {
        return null;
    }

    public List<Product> getDamagedItems() {
        return null;
    }

    public List<Product> getProducts() {
        return null;
    }

    public List<Product> getProductsFromCategory(int categoryID) {
        return null;
    }


    public void removeProduct(int productID, List<Item> items) {
        //find product remove amount
        Product product = null;
        for (Product p: products)
            if (p.getId()==productID) {
                product = p;
                break;
            }
        if (product==null)
            throw new IllegalArgumentException("StoreController: returnProduct: no such product found");
        product.RemoveItems(items);
        itemsAmount -= items.size();
    }
    public void move(int productID, List<Item> items) {
        //find product move amount
        Product product = null;
        for (Product p: products)
            if (p.getId()==productID) {
                product = p;
                break;
            }
        if (product==null)
            throw new IllegalArgumentException("StoreController: returnProduct: no such product found");
        product.MoveItems(items);
    }
    public void AddItems(int storeID, int productID, Map<Date, Integer> expiryDates) {
        //find product add amount
        Product product = null;
        for (Product p: products)
            if (p.getId()==productID) {
                product = p;
                break;
            }
        if (product==null)
            throw new IllegalArgumentException("StoreController: returnProduct: no such product found");
        itemsAmount += product.AddItems(storeID, expiryDates, itemsAmount);
    }
    public void returnProduct(int storeID, int productID, Map<Date, Integer> expiryDates) {
        //find product add amount
        Product product = null;
        for (Product p: products)
            if (p.getId()==productID) {
                product = p;
                break;
            }
        if (product==null)
            throw new IllegalArgumentException("StoreController: returnProduct: no such product found");
        itemsAmount += product.ReturnItems(storeID, expiryDates, itemsAmount);
    }
}
