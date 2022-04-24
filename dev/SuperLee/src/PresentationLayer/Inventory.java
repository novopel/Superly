package PresentationLayer;

import ServiceLayer.InventoryService;
import ServiceLayer.Objects.*;
import ServiceLayer.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    public static InventoryService is = new InventoryService();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to the Inventory Manager!");
        System.out.println("Would you like to load test data?");
        System.out.println("(yes/no)");
        boolean testData=true;
        while (testData) {
            String ans = scanner.nextLine();
            if (ans.equals("yes") || ans.equals("no")) {
                testData = false;
                if (ans.equals("yes"))
                    is.loadTestData();
            } else
                System.out.println("Please only type yes or no");
        }
        boolean quit = false;
        System.out.println("System ready: At any point enter q to quit");
        System.out.println("Before you begin, here is the help menu with the possible commands: \n");
        help();
        System.out.println();
        while (!quit) {
            System.out.print("Enter command: ");
            String command  = scanner.nextLine();
            if (command.equals("q"))
                break;
            doCommand(command);
        }
        System.out.println("Shutting down - Thanks!");
    }

    private static Date getDate() {
        while (true) {
            try {
                System.out.println("Please insert date in format: DD/MM/YYYY");
                String dateInput = scanner.nextLine();
                if (dateInput.equals("c")) {
                    System.out.println("Cancelling command");
                    return null;
                }
                return new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
            } catch (ParseException p) {
                System.out.println("Date in wrong format! please try again. c to cancel command");
            }
        }
    }

    private static int getStoreID() {
        System.out.println("Please insert store ID of store you are interested in.");
        System.out.println("Current store IDs are:");
        System.out.println(is.getStoreIDs().getValue());
        return scanner.nextInt();
    }

    private static void listCategoryIDs() {
        List<Integer> cIDs = getCatIDs();
        System.out.println("Current category IDs are:");
        System.out.println(cIDs);
    }

    private static List<Integer> getCatIDs() {
        List<Integer> cIDs = new ArrayList<>();
        List<Category> c = is.getCategories().getValue();
        for (Category cat: c) {
            cIDs.add(cat.getID());
        }
        return cIDs;
    }
    private static void isUnderMin(int store, int product) {
        Result<Boolean> r = is.isUnderMin(store, product);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("WARNING: product with ID " + product + " is in low stock in store " + store);
        }
    }

    private static void doCommand(String command) {
        if (command.equals("list products"))
            listProducts();
        else if (command.equals("list categories"))
            listCategories();
        else if (command.equals("store stock report"))
            storeStockReport();
        else if (command.equals("add product"))
            addProduct();
        else if (command.equals("change product name"))
            changeProductName();
        else if (command.equals("change product price"))
            changeProductPrice();
        else if (command.equals("change product min"))
            changeProductMin();
        else if (command.equals("change product min"))
            changeProductMax();
        else if (command.equals("delete product"))
            deleteProduct();
        else if (command.equals("add category"))
            addCategory();
        else if (command.equals("delete category"))
            deleteCategory();
        else if (command.equals("change category parent"))
            changeCatParent();
        else if (command.equals("change category name"))
            changeCatName();
        else if (command.equals("move items"))
            moveItems();
        else if (command.equals("change product category"))
            changeProductCategory();
        else if (command.equals("sale history by product"))
            saleHistoryByProduct();
        else if (command.equals("sale history by category"))
            saleHistoryByCategory();
        else if (command.equals("purchase from supplier history"))
            getPurchaseFromSupplierHistory();
        else if (command.equals("discount from supplier history"))
            getDiscountFromSupplierHistory();
        else if (command.equals("add sale"))
            addSale();
        else if (command.equals("list products in category"))
            listProductsByCategory();
        else if (command.equals("return items"))
            returnItems();
        else if (command.equals("buy items"))
            buyItems();
        else if (command.equals("add items"))
            addItems();
        else if (command.equals("report expired"))
            reportExpired();
        else if (command.equals("expired items"))
            expiredItems();
        else if (command.equals("report damaged"))
            reportDamaged();
        else if (command.equals("damaged items"))
            damagedItems();
        else if (command.equals("defective items"))
            defectiveItems();
        else if (command.equals("add store"))
            addStore();
        else if (command.equals("remove store"))
            removeStore();
        else if (command.equals("remove sale"))
            removeSale();
        else if (command.equals("add product to store"))
            addProductToStore();
        else if (command.equals("remove product from store"))
            removeProductFromStore();
        else if (command.equals("min stock report"))
            getMinStockReport();
        else if (command.equals("add supplier to product"))
            addSupplierToProduct();
        else if (command.equals("remove supplier from product"))
            removeSupplierFromProduct();
        else if (command.equals("help"))
            help();
        else if (command.equals("load data"))
            System.out.println("Persistence Layer is not implemented yet");
        else
            System.out.println("Command not found. please use 'help' for info or 'q' to quit");
    }

    private static void deleteCategory() {
        System.out.println("What category would you like to delete?");
        listCategoryIDs();
        int catID = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result r = is.deleteCategory(catID);
        if (r.isError()) {
            System.out.println(r.getError());
        }
        else
            System.out.println("Category deleted successfully");
    }

    private static void changeProductMin() {
        System.out.println("What Product would you like to edit?");
        int product = scanner.nextInt();
        int store = getStoreID();
        System.out.println("What would you like the new min amount to be?");
        int min = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result<Product> r = is.changeProductMin(store, product, min);
        if (r.isError()) {
            System.out.println(r.getError());
        }
        else {
            System.out.println("Min changed successfully");
            System.out.println(r.getValue());
        }
    }

    private static void changeProductMax() {
        System.out.println("What Product would you like to edit?");
        int product = scanner.nextInt();
        int store = getStoreID();
        System.out.println("What would you like the new max amount to be?");
        int max = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result<Product> r = is.changeProductMin(store, product, max);
        if (r.isError()) {
            System.out.println(r.getError());
        }
        else {
            System.out.println("Max changed successfully");
            System.out.println(r.getValue());
        }
    }

    private static void storeStockReport() {
        System.out.println("Please insert store IDs, separated by commas without spaces");
        System.out.println("Current store IDs are:");
        System.out.println(is.getStoreIDs().getValue());
        System.out.println("If you would like all stores, you can enter a blank list");
        String input = scanner.nextLine();
        List<Integer> storeIDs;
        if (input.equals(""))
            storeIDs = is.getStoreIDs().getValue();
        else
            storeIDs = Arrays.asList(input.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert category IDs, separated by commas without spaces");
        listCategoryIDs();
        System.out.println("If you would like all categories, you can enter a blank list");
        input = scanner.nextLine();
        List<Integer> categoryIDs;
        if (input.equals(""))
            categoryIDs = getCatIDs();
        else
            categoryIDs = Arrays.asList(input.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        Result<List<StockReport>> r = is.storeStockReport(storeIDs, categoryIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<StockReport> stockReports = r.getValue();
            for (StockReport s : stockReports)
                System.out.println(s);
            if (stockReports.isEmpty())
                System.out.println("the store has no products registered to it in the system or it has been removed");
        }
    }

    private static void removeSupplierFromProduct() {
        System.out.println("Which product would you like to remove a supplier from? (insert ID)");
        int productID = scanner.nextInt();
        System.out.println("Which supplier would you like to remove? (insert ID)");
        int supplierID = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result<Product> r = is.removeSupplierFromProduct(productID, supplierID);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Supplier successfully removed");
            System.out.println(r.getValue());
        }
    }

    private static void addSupplierToProduct() {
        System.out.println("Which product would you like to add a supplier to? (insert ID)");
        int productID = scanner.nextInt();
        System.out.println("Which supplier would you like to add? (insert ID)");
        int supplierID = scanner.nextInt();
        System.out.println("What is the supplier's ID for the product?");
        int productIDWithSupplier = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result<Product> r = is.addSupplierToProduct(productID, supplierID, productIDWithSupplier);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Supplier successfully added");
            System.out.println(r.getValue());
        }
    }

    private static void removeSale() {
        System.out.println("Which sale would you like to remove? (insert ID)");
        PrintRemovableSales();
        int saleID = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result r = is.removeSale(saleID);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("sale successfully removed");
        }
    }

    private static void PrintRemovableSales() {
        Result<List<Sale>> removableSales = is.getRemovableSales();
        for (Sale sale: removableSales.getValue())
            System.out.println(sale);
    }

    private static void addProductToStore() {
        int store = getStoreID();
        System.out.println("Which product would you like to add? (insert ID)");
        int product = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        System.out.println("What will be the product's shelves in the store? (please insert shelf numbers, separated by commas without spaces)");
        List<Integer> inStore = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("What will be the product's shelves in the warehouse? (please insert shelf numbers, separated by commas without spaces)");
        List<Integer> inWareHouse = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("What will be the min amount in the store?");
        int min = scanner.nextInt();
        System.out.println("What will be the max amount in the store?");
        int max = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result<Product> r = is.addProductToStore(store, inStore, inWareHouse, product, min, max);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Product added");
            System.out.println(r.getValue());
        }
    }

    private static void removeProductFromStore() {
        int store = getStoreID();
        System.out.println("What product would you like to remove?");
        int product = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result<Product> r = is.removeProductFromStore(store, product);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Product removed");
            System.out.println(r.getValue());
        }
    }

    private static void getMinStockReport() {
        Result<List<StockReport>> r = is.getMinStockReport();
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<StockReport> stockReports = r.getValue();
            for (StockReport s : stockReports)
                System.out.println(s);
            if (stockReports.isEmpty())
                System.out.println("There are no products under the min amount");
        }
    }

    private static void changeProductCategory() {
        System.out.println("Which product would you like to edit? (insert ID)");
        int productID = scanner.nextInt();
        System.out.println("To which category would you like to move it? (insert ID)");
        int category = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result<Product> r = is.moveProductToCategory(productID, category);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("product successfully moved to new category");
            Product p = r.getValue();
            System.out.println(p);
        }
    }

    private static void changeCatName() {
        System.out.println("Which category would you like to edit?");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        System.out.println("Please insert new category name");
        String name = scanner.nextLine();
        Result<Category> r = is.editCategoryName(id, name);
        if (r.isError())
            System.out.println(r.getError());
        else {
            Category c = r.getValue();
            System.out.println(c);
        }
    }

    private static void moveItems() {
        int store = getStoreID();
        System.out.println("Which product's items are you moving? (insert ID)");
        int productID = scanner.nextInt();
        System.out.println("How much is being moved?");
        int amount = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result r = is.moveItems(store, productID, amount);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Moving of items successful");
        }
    }

    private static void addItems() {
        int storeID = getStoreID();
        System.out.println("Which product was purchased? (insert ID)");
        int productID = scanner.nextInt();
        System.out.println("Which supplier was the purchase from? (insert ID)");
        int supplier = scanner.nextInt();
        System.out.println("How much of the product was purchased?");
        int amount = scanner.nextInt();
        System.out.println("How much was the original price?");
        int originalPrice = scanner.nextInt();
        System.out.println("How much was paid?");
        int finalPrice = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        System.out.println("Please insert a general description of the purchase");
        String description = scanner.nextLine();
        Result<PurchaseFromSupplierReport> r = is.addItems(storeID, productID, supplier, description, amount, finalPrice, originalPrice);
        if (r.isError())
            System.out.println(r.getError());
        else {
            PurchaseFromSupplierReport dr = r.getValue();
            System.out.println("Purchase added to system successfully");
            System.out.println(dr);
        }
    }

    private static void reportExpired() {
        int store = getStoreID();
        System.out.println("Which product is expired? (insert ID)");
        int productID = scanner.nextInt();
        System.out.println("How much of the product is expired?");
        int amount = scanner.nextInt();
        System.out.println("Please enter your ID");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        System.out.println("Please add a description (not mandatory)");
        String description = scanner.nextLine();
        Result<DefectiveItemReport> r = is.reportExpired(store, productID, amount, employeeID, description);
        if (r.isError())
            System.out.println(r.getError());
        else {
            DefectiveItemReport eir = r.getValue();
            System.out.println(eir);
            isUnderMin(store, productID);
        }
    }

    private static void expiredItems() {
        System.out.println("Please insert for which items you would like to see expired item history: (choose the corresponding number)");
        System.out.println("1: A product/products");
        System.out.println("2: A category/categories");
        System.out.println("3: A store/number of stores");
        System.out.println("4: all products");
        int expireCase = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        switch (expireCase) {
            case (1):
                expiredItemsByProduct();
                break;
            case (2):
                expiredItemsByCategory();
                break;
            case (3):
                expiredItemsByStore();
                break;
            case (4):
                expiredItemsAll();
                break;
            default:
                System.out.println("Incorrect command, please try again");
        }
    }

    private static void expiredItemsAll() {
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getExpiredItemReportsByStore(start, end, new ArrayList<>());
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport eir : reportList)
                System.out.println(eir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void expiredItemsByStore() {
        System.out.println("Please insert store IDs, separated by commas without spaces");
        List<Integer> storeIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getExpiredItemReportsByStore(start, end, storeIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport eir : reportList)
                System.out.println(eir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void expiredItemsByCategory() {
        System.out.println("Please insert category IDs, separated by commas without spaces");
        List<Integer> categoryIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getExpiredItemReportsByCategory(start, end, categoryIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport eir : reportList)
                System.out.println(eir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void expiredItemsByProduct() {
        System.out.println("Please insert product IDs, separated by commas without spaces");
        List<Integer> productIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getExpiredItemReportsByProduct(start, end, productIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport eir : reportList)
                System.out.println(eir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void reportDamaged() {
        int store = getStoreID();
        System.out.println("Which product is damaged? (insert ID)");
        int productID = scanner.nextInt();
        System.out.println("How much of the product is damaged?");
        int amount = scanner.nextInt();
        System.out.println("Please enter your ID");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        System.out.println("Please describe the damage");
        String description = scanner.nextLine();
        Result<DefectiveItemReport> r = is.reportDamaged(store, productID, amount, employeeID, description);
        if (r.isError())
            System.out.println(r.getError());
        else {
            DefectiveItemReport dir = r.getValue();
            System.out.println(dir);
            isUnderMin(store, productID);
        }
    }

    private static void defectiveItems() {
        System.out.println("Please insert for which items you would like to see defect item history: (choose the corresponding number)");
        System.out.println("1: A product/products");
        System.out.println("2: A category/categories");
        System.out.println("3: A store/number of stores");
        System.out.println("4: all products");
        int defectCase = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        switch (defectCase) {
            case (1):
                defectiveItemsByProduct();
                break;
            case (2):
                defectiveItemsByCategory();
                break;
            case (3):
                defectiveItemsByStore();
                break;
            case (4):
                defectiveItemsAll();
                break;
            default:
                System.out.println("Incorrect command, please try again");
        }
    }
    private static void defectiveItemsAll() {
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDefectiveItemsByStore(start, end, new ArrayList<>());
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void defectiveItemsByStore() {
        System.out.println("Please insert store IDs, separated by commas without spaces");
        List<Integer> storeIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDefectiveItemsByStore(start, end, storeIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void defectiveItemsByCategory() {
        System.out.println("Please insert category IDs, separated by commas without spaces");
        List<Integer> categoryIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDefectiveItemsByCategory(start, end, categoryIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void defectiveItemsByProduct() {
        System.out.println("Please insert product IDs, separated by commas without spaces");
        List<Integer> productIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDefectiveItemsByProduct(start, end, productIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void damagedItems() {
        System.out.println("Please insert for which items you would like to see damaged item history: (choose the corresponding number)");
        System.out.println("1: A product/products");
        System.out.println("2: A category/categories");
        System.out.println("3: A store/number of stores");
        System.out.println("4: all products");
        int damagedCase = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        switch (damagedCase) {
            case (1):
                damagedItemsByProduct();
                break;
            case (2):
                damagedItemsByCategory();
                break;
            case (3):
                damagedItemsByStore();
                break;
            case (4):
                damagedItemsAll();
                break;
            default:
                System.out.println("Incorrect command, please try again");
        }
    }

    private static void damagedItemsAll() {
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDamagedItemsReportByStore(start, end, new ArrayList<>());
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void damagedItemsByStore() {
        System.out.println("Please insert store IDs, separated by commas without spaces");
        List<Integer> storeIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDamagedItemsReportByStore(start, end, storeIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void damagedItemsByCategory() {
        System.out.println("Please insert category IDs, separated by commas without spaces");
        List<Integer> categoryIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDamagedItemsReportByCategory(start, end, categoryIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void damagedItemsByProduct() {
        System.out.println("Please insert product IDs, separated by commas without spaces");
        List<Integer> productIDs = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<List<DefectiveItemReport>> r = is.getDamagedItemsReportByProduct(start, end, productIDs);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<DefectiveItemReport> reportList = r.getValue();
            for (DefectiveItemReport dir : reportList)
                System.out.println(dir);
            if (reportList.isEmpty())
                System.out.println("There were no reports matching search");
        }
    }

    private static void addStore() {
        Result<Integer> r = is.addStore();
        if (r.isError())
            System.out.println(r.getError());
        else {
            int id = r.getValue();
            System.out.println("new store created, ID is: " + id);
        }
    }

    private static void removeStore() {
        System.out.println("Which store would you like remove?");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result r = is.removeStore(id);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Store removed");
        }
    }

    private static void changeCatParent() {
        System.out.println("Which category would you like to edit?");
        int id = scanner.nextInt();
        System.out.println("Please insert new category parent ID");
        int parent = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<Category> r = is.changeCategoryParent(id, parent);
        if (r.isError())
            System.out.println(r.getError());
        else {
            Category c = r.getValue();
            System.out.println(c);
        }
    }

    private static void deleteProduct() {
        System.out.println("Which product would you like remove? (insert ID)");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result r = is.deleteProduct(id);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Product removed");
        }
    }

    private static void changeProductPrice() {
        System.out.println("Which product would you like to edit? (insert ID)");
        int id = scanner.nextInt();
        System.out.println("Please insert new product price");
        double price = scanner.nextDouble();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<Product> r = is.editProductPrice(id, price);
        if (r.isError())
            System.out.println(r.getError());
        else {
            Product p = r.getValue();
            System.out.println(p);
        }
    }

    private static void changeProductName() {
        System.out.println("Which product would you like to edit? (insert ID)");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        System.out.println("Please insert new product name");
        String name = scanner.nextLine();
        Result<Product> r = is.editProductName(id, name);
        if (r.isError())
            System.out.println(r.getError());
        else {
            Product p = r.getValue();
            System.out.println(p);
        }
    }

    private static void addCategory() {
        System.out.println("Please insert category name");
        String name = scanner.nextLine();
        System.out.println("Please insert parent category ID, or 0 if there is none");
        int parent = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<Category> r = is.addNewCategory(name, parent);
        if (r.isError())
            System.out.println(r.getError());
        else {
            Category c = r.getValue();
            System.out.println(c);
        }
    }

    private static void listProducts() {
        Result<List<Product>> r = is.getProducts();
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<Product> productList = r.getValue();
            for (Product p : productList)
                System.out.println(p);
            if (productList.isEmpty())
                System.out.println("there are no products in the system");
        }
    }

    private static void listCategories() {
        Result<List<Category>> r = is.getCategories();
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<Category> categoryList = r.getValue();
            for (Category c : categoryList)
                System.out.println(c);
            if (categoryList.isEmpty())
                System.out.println("there are no categories in the system");
        }
    }

    private static void addProduct() {
        System.out.println("Please insert product name, categoryID, weight, price, List of <supplierID><space><productID> with spaces between entries, and manufacturerID. Separated by commas, no spaces");
        String[] info = scanner.nextLine().split(",");
        Map<Integer, Integer> suppliersMap = new HashMap<>();
        String[] suppliersStringArray = info[4].split(" ");
        for (int i=0; i< suppliersStringArray.length; i=i+2) {
            suppliersMap.put(Integer.parseInt(suppliersStringArray[i]), Integer.parseInt(suppliersStringArray[i + 1]));
        }
        Result<Product> r = is.newProduct(info[0],Integer.parseInt(info[1]), Integer.parseInt(info[2]), Double.parseDouble(info[3]), suppliersMap, Integer.parseInt(info[5]));
        if (r.isError())
            System.out.println(r.getError());
        else {
            Product p = r.getValue();
            System.out.println(p);
        }
    }

    private static void saleHistoryByProduct() {
        System.out.println("Please insert product ID for which you would like to see history");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<List<Sale>> r = is.getSaleHistoryByProduct(id);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<Sale> saleReport = r.getValue();
            for (Sale s : saleReport)
                System.out.println(s);
            if (saleReport.isEmpty())
                System.out.println("there are no sales for this product in the system");
        }
    }

    private static void saleHistoryByCategory() {
        System.out.println("Please insert category ID for which you would like to see history");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<List<Sale>> r = is.getSaleHistoryByCategory(id);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<Sale> saleReport = r.getValue();
            for (Sale s : saleReport)
                System.out.println(s);
            if (saleReport.isEmpty())
                System.out.println("there are no sales for this category in the system");
        }
    }

    private static void getPurchaseFromSupplierHistory() {
        System.out.println("Please insert product ID for which you would like to see history");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<List<PurchaseFromSupplierReport>> r = is.getPurchaseFromSupplierHistory(id);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<PurchaseFromSupplierReport> purchaseFromSupplierReports = r.getValue();
            for (PurchaseFromSupplierReport dr : purchaseFromSupplierReports)
                System.out.println(dr);
            if (purchaseFromSupplierReports.isEmpty())
                System.out.println("there are no purchases from suppliers for this product in the system");
        }
    }

    private static void getDiscountFromSupplierHistory() {
        System.out.println("Please insert product ID for which you would like to see history");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<List<PurchaseFromSupplierReport>> r = is.getDiscountFromSupplierHistory(id);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<PurchaseFromSupplierReport> purchaseFromSupplierReports = r.getValue();
            for (PurchaseFromSupplierReport dr : purchaseFromSupplierReports)
                System.out.println(dr);
            if (purchaseFromSupplierReports.isEmpty())
                System.out.println("there are no discounts from suppliers for this product in the system");
        }
    }

    private static void addSale() {
        List<Integer> categories;
        List<Integer> products;
        System.out.println("Please insert category IDs, separated by ',' without spaces");
        try {
            categories = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        } catch (Exception e) { categories = new ArrayList<>(); }
        System.out.println("Please insert product IDs, separated by ',' without spaces");
        try {
            products = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        } catch (Exception e) { products = new ArrayList<>(); }
        System.out.println("Please insert percent of sale: (more than 0, less than 100)");
        int percent = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        System.out.println("Please insert start date");
        Date start = getDate();
        if (start==null)
            return;
        System.out.println("Please insert end date");
        Date end = getDate();
        if (end==null)
            return;
        Result<Sale> r = is.addSale(categories, products, percent, start, end);
        if (r.isError())
            System.out.println(r.getError());
        else
            System.out.println(r.getValue());
    }

    private static void listProductsByCategory() {
        System.out.println("Which categories would you like to examine? (Please insert categories IDs separated by ',' without spaces)");
        listCategoryIDs();
        List<Integer> categories = Arrays.asList(scanner.nextLine().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        Result<List<Product>> r = is.getProductsFromCategory(categories);
        if (r.isError())
            System.out.println(r.getError());
        else {
            List<Product> productList = r.getValue();
            for (Product p : productList)
                System.out.println(p);
            if (productList.isEmpty())
                System.out.println("there are no products in specified categories");
        }
    }

    private static void returnItems() {
        int storeID = getStoreID();
        System.out.println("Please insert product ID of the product's items you would like to return");
        int productId = scanner.nextInt();
        System.out.println("Please insert amount of items you would like to return");
        int amount = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        System.out.println("Please insert the date the items were bought");
        Date dateBought = getDate();
        if (dateBought==null)
            return;
        Result<Double> r = is.returnItems(storeID, productId, amount, dateBought);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Total refund is " + round(r.getValue()) + "NIS");
        }
    }
    private static double round(double price) {
        price = (int)(price*100);
        return price/100;
    }
    private static void buyItems() {
        int storeID = getStoreID();
        System.out.println("Please insert product ID of product you would like to buy");
        int productId = scanner.nextInt();
        System.out.println("Please insert amount of product you would like to buy");
        int amount = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<Double> r = is.buyItems(storeID, productId, amount);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("Purchase successful! Total price is " + round(r.getValue()) + "NIS");
            isUnderMin(storeID, productId);
        }
    }

    private static void help() {
        //addSupplier
        //removeSupplier
        System.out.println("Welcome to help session");
        System.out.println("Please notice that commands are case sensitive");
        System.out.println("Possible commands are:");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "list products", "list of all the products in catalog");
        System.out.printf("%-30.30s %-30s\n", "list categories", "list of all the different categories");
        System.out.printf("%-30.30s %-30s\n", "list products in category", "list all products in specified category/ies");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "add items", "add items to a store/receive delivery from supplier");
        System.out.printf("%-30.30s %-30s\n", "move items", "move product's items from the warehouse to the store");
        System.out.printf("%-30.30s %-30s\n", "buy items", "buy items from a store");
        System.out.printf("%-30.30s %-30s\n", "return items", "return a previously purchased product's items to the store");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "add product to store", "add a product to specific store in system");
        System.out.printf("%-30.30s %-30s\n", "remove product from store", "add a product to specific store in system");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "add sale", "create a new sale");
        System.out.printf("%-30.30s %-30s\n", "remove sale", "remove future or current sale from the system");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "add product", "add a new product");
        System.out.printf("%-30.30s %-30s\n", "delete product", "delete product from catalog");
        System.out.printf("%-30.30s %-30s\n", "change product name", "change product name");
        System.out.printf("%-30.30s %-30s\n", "change product price", "change product price");
        System.out.printf("%-30.30s %-30s\n", "change product min", "change min amount of product in a store before warning");
        System.out.printf("%-30.30s %-30s\n", "change product max", "change max recommended amount of product in a store");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "add category", "create a new category");
        System.out.printf("%-30.30s %-30s\n", "delete category", "delete an existing category");
        System.out.printf("%-30.30s %-30s\n", "change category parent", "change a category's \"parent\" category");
        System.out.printf("%-30.30s %-30s\n", "change category name", "change a category's name");
        System.out.printf("%-30.30s %-30s\n", "change product category", "move a product to a new category");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "min stock report", "prints stock report of items under the min amount");
        System.out.printf("%-30.30s %-30s\n", "store stock report", "report of stock in specified stores and categories");
        System.out.printf("%-30.30s %-30s\n", "sale history by product", "see history of sales on a specific product");
        System.out.printf("%-30.30s %-30s\n", "sale history by category", "see history of sales on a specific category");
        System.out.printf("%-30.30s %-30s\n", "purchase from supplier history", "see history of all purchases from suppliers");
        System.out.printf("%-30.30s %-30s\n", "discount from supplier history", "see history of all discounts from suppliers");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "report expired", "report finding of expired items");
        System.out.printf("%-30.30s %-30s\n", "report damaged", "report finding of damaged items");
        System.out.printf("%-30.30s %-30s\n", "expired items", "print a report of expired items");
        System.out.printf("%-30.30s %-30s\n", "damaged items", "print a report of damaged items");
        System.out.printf("%-30.30s %-30s\n", "defective items", "print a report of defective (damaged and expired together) items");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "add supplier to product", "adds a supplier as one of the product's suppliers");
        System.out.printf("%-30.30s %-30s\n", "remove supplier from product", "removes a supplier from list of product's suppliers");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "add store", "add a new store to the system");
        System.out.printf("%-30.30s %-30s\n", "remove store", "remove store from the system");

        System.out.println();
        System.out.printf("%-30.30s %-30s\n", "help", "prints this menu");
        System.out.printf("%-30.30s %-30s\n", "q", "quits program");
        System.out.println();

//        System.out.println("load data", "Persistence Layer is not implemented yet");
    }
}
