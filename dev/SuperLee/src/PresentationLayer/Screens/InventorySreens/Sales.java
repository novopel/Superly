package PresentationLayer.Screens.InventorySreens;

import Domain.ServiceLayer.InventoryObjects.Sale;
import Domain.ServiceLayer.Result;
import PresentationLayer.Screens.Screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sales extends Screen {

    private static final String[] menuOptions = {
            "Add Sale",  //1
            "Remove Sale",                  //2
            "Sale History by Product",          //3
            "Sale History by Category",      //4
            "exit",       //5
    };

    public Sales(Screen caller, String[] extraMenuOptions) {
        super(caller, Stream.concat(Arrays.stream(menuOptions), Arrays.stream(extraMenuOptions)).toArray(String[]::new));
    }

    public void run() {
        System.out.println("\nWelcome to the Management Menu of Sales");
        int option = 0;
        while (option != 5) {
            option = runMenu();
            try {
                if (option <= 5)
                    handleBaseOptions(option);
                else if (option == 9)
                    endRun();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Please try again");
            }
        }
    }


    protected void handleBaseOptions(int option) throws Exception {
        switch (option) {
            case 1:
                addSale();
                break;
            case 2:
                removeSale();
                break;
            case 3:
                saleHistoryByProduct();
                break;
            case 4:
                saleHistoryByCategory();
                break;
            case 5:
                endRun();
        }
    }

    private void addSale() {
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
        Result<Sale> r = controller.addSale(categories, products, percent, start, end);
        if (r.isError())
            System.out.println(r.getError());
        else
            System.out.println(r.getValue());
    }

    public void removeSale() {
        System.out.println("Which sale would you like to remove? (insert ID)");
        PrintRemovableSales();
        int saleID = scanner.nextInt();
        scanner.nextLine(); //to remove extra \n
        Result r = controller.removeSale(saleID);
        if (r.isError())
            System.out.println(r.getError());
        else {
            System.out.println("sale successfully removed");
        }
    }

    public void PrintRemovableSales() {
        Result<List<Sale>> removableSales = controller.getRemovableSales();
        for (Sale sale: removableSales.getValue())
            System.out.println(sale);
    }

    public void saleHistoryByProduct() {
        System.out.println("Please insert product ID for which you would like to see history");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<List<Sale>> r = controller.getSaleHistoryByProduct(id);
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

    public void saleHistoryByCategory() {
        System.out.println("Please insert category ID for which you would like to see history");
        int id = scanner.nextInt();
        scanner.nextLine(); //without this line the next scanner will be passed without the user's input.
        Result<List<Sale>> r = controller.getSaleHistoryByCategory(id);
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
}
