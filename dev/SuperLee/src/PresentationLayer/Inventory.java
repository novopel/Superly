package PresentationLayer;

import ServiceLayer.InventoryService;
import ServiceLayer.Objects.Category;
import ServiceLayer.Objects.Product;
import ServiceLayer.Objects.SaleReport;
import ServiceLayer.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        System.out.println(help());
        while (!quit) {
            String command  = scanner.nextLine();
            if (command.equals("q"))
                break;
            doCommand(command);
        }
        System.out.println("Shutting down - Thanks!");
    }

    private static void doCommand(String command) {
        if (command.equals("list products")) {
            Result<List<Product>> r = is.getProducts();
            if (r.isError())
                System.out.println(r.getError());
            else {
                List<Product> productList = r.getValue();
                for (Product p : productList)
                    System.out.println(p);
            }
        }
        else if (command.equals("list categories")) {
            Result<List<Category>> r = is.getCategories();
            if (r.isError())
                System.out.println(r.getError());
            else {
                List<Category> categoryList = r.getValue();
                for (Category c : categoryList)
                    System.out.println(c);
            }
        }
        else if (command.equals("list products by category")) {
            System.out.println("Which category would you like to examine?");
            int categoryID = scanner.nextInt();
            Result<List<Product>> r = is.getProductsFromCategory(categoryID);
            if (r.isError())
                System.out.println(r.getError());
            else {
                List<Product> productList = r.getValue();
                for (Product p : productList)
                    System.out.println(p);
            }
        }
        else if (command.equals("add product")) {
            System.out.println("Please insert product name, categoryID, weight, and price. Separated by commas, no spaces");
            String[] info = scanner.nextLine().split(",");
            Result<Product> r = is.newProduct(info[0],Integer.parseInt(info[1]), Integer.parseInt(info[2]),Double.parseDouble(info[3]),new ArrayList<>());
            if (r.isError())
                System.out.println(r.getError());
            else {
                Product p = r.getValue();
                System.out.println(p);
            }
        }
        else if (command.equals("sale history by product")) {
            System.out.println("Please insert product ID");
            int id = scanner.nextInt();
            Result<SaleReport> r = is.getSaleHistoryByProduct(id);
            if (r.isError())
                System.out.println(r.getError());
            else {
                SaleReport report = r.getValue();
                System.out.println(report);
            }
        }
        else if (command.equals("add sale")) {
//            System.out.println("Please insert category IDs, separated by ',' without spaces");
//            System.out.println("For example: 12,3,4,1");
//            String[] categories = scanner.nextLine().split(",");
//            System.out.println("Please insert product IDs, separated by ',' without spaces");
//            System.out.println("For example: 12,3,4,1");
//            Result<SaleReport> r = is.addSale(categories, products, percent, start, end);
        }
        else if (command.equals("list products in category")) {
            System.out.println("Please insert product ID");
            int id = scanner.nextInt();
            Result<List<Product>> r = is.getProductsFromCategory(id);
            if (r.isError())
                System.out.println(r.getError());
            else {
                List<Product> productList = r.getValue();
                for (Product p : productList)
                    System.out.println(p);
            }
        }
        else if (command.equals("return item")) {
        }
        else if (command.equals("buy items")) {
            System.out.println("Please insert store ID of store you are in. Current store IDs are:");
            System.out.println(is.getStoreIDs());
            int storeID = scanner.nextInt();
            System.out.println("Please insert product ID of product you would like to buy");
            int productId = scanner.nextInt();
            System.out.println("Please insert amount of product you would like to buy");
            int amount = scanner.nextInt();
            Result<Double> r = is.buyItems(productId, storeID, amount);
            if (r.isError())
                System.out.println(r.getError());
            else {
                System.out.println("Purhase successful! Total price is " + r.getValue() + "NIS");
            }
        }
        else if (command.equals("add sale")) {
        }
    }

    private static String help() {
        return "This will be the guide to what commands are available";
    }
}
