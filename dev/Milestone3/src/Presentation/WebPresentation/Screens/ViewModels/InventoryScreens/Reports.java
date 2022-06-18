package Presentation.WebPresentation.Screens.ViewModels.InventoryScreens;

import Domain.Service.Objects.InventoryObjects.DefectiveItemReport;
import Domain.Service.util.Result;
import Presentation.WebPresentation.Screens.Models.HR.Employee;
import Presentation.WebPresentation.Screens.Screen;
import Presentation.WebPresentation.Screens.ViewModels.HR.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Reports extends Screen {

    private static final String greet = "Reports";

    private static final String viewReportButton = "View defective reports";

    /*private static final String defectiveByStoreButton = "Get defective items report by store";
    private static final String defectiveByCategoryButton = "Get defective items report by category";
    private static final String defectiveByProductButton = "Get defective items report by product";
    private static final String damagedByStoreButton = "Get damaged items report by store";
    private static final String damagedByCategoryButton = "Get damaged items report by category";
    private static final String damagedByProductButton = "Get damaged items report by product";
    private static final String expiredByStoreButton = "Get expired items report by store";
    private static final String expiredByCategoryButton = "Get expired items report by category";
    private static final String expiredByProductButton = "Get expired items report by product";*/

    public static final Set<Class<? extends Employee>> ALLOWED = new HashSet<>();

    public Reports() { super(greet, ALLOWED); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!isAllowed(req, resp)) {
            redirect(resp, Login.class);
        }
        header(resp);
        greet(resp);
        printMenu(resp,new String[] {"Stock Reports"});
        printForm(resp, new String[] {"start date", "end date", "IDs", "type", "by"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Store IDs (3,8,1)", "defective/damaged/expired", "store/category/product"}, new String[]{viewReportButton});
        /*printForm(resp, new String[] {"start date", "end date", "store IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Store IDs (3,8,1)"}, new String[]{defectiveByStoreButton});
        printForm(resp, new String[] {"start date", "end date", "category IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Category IDs (3,8,1)"}, new String[]{defectiveByCategoryButton});
        printForm(resp, new String[] {"start date", "end date", "product IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Product IDs (3,8,1)"}, new String[]{defectiveByProductButton});
        printForm(resp, new String[] {"start date", "end date", "store IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Store IDs (3,8,1)"}, new String[]{damagedByStoreButton});
        printForm(resp, new String[] {"start date", "end date", "category IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Category IDs (3,8,1)"}, new String[]{damagedByCategoryButton});
        printForm(resp, new String[] {"start date", "end date", "product IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Product IDs (3,8,1)"}, new String[]{damagedByProductButton});
        printForm(resp, new String[] {"start date", "end date", "store IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Store IDs (3,8,1)"}, new String[]{expiredByStoreButton});
        printForm(resp, new String[] {"start date", "end date", "category IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Category IDs (3,8,1)"}, new String[]{expiredByCategoryButton});
        printForm(resp, new String[] {"start date", "end date", "product IDs"}, new String[]{"Start Date (yyyy-mm-dd)", "End Date (yyyy-mm-dd)", "Product IDs (3,8,1)"}, new String[]{expiredByProductButton});*/
        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (handleHeader(req, resp))
            return;
        switch (getIndexOfButtonPressed(req)) {
            case 0 :
                redirect(resp, StockReport.class);
                break;
        }
        /*String x = Integer.toString(getIndexOfButtonPressed(req));
        setError(x);
        refresh(req, resp);
        return;*/
        //redirect(resp, Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"defective", "by store", "1,2,3,4,5", "2022-01-01", "2023-12-31"});
        //redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"defective", "by store", IDsString, startDateStr, endDateStr});
        if(isButtonPressed(req, viewReportButton)){
            if (!isAllowed(req, resp, Report.ALLOWED)) {
                setError("You have no permission to view defective reports");
                refresh(req, resp);
                return;
            }
            try {
                String startDateStr = req.getParameter("start date");
                String endDateStr = req.getParameter("end date");
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);
                String IDsString = req.getParameter("IDs");
                String type = req.getParameter("type");
                String by = req.getParameter("by");
                List<Integer> IDs;
                switch (type + " by " + by) {
                    case "defective by store": {
                        if (IDsString==null) {
                            setError("1) NO IDS!!!!!!!!!!!!!");
                            refresh(req, resp);
                        }
                        List<String> str = (Arrays.asList(IDsString.split(",")));
                        if (str.size()==0) {
                            setError("2) NO IDS!!!!!!!!!!!!!");
                            refresh(req, resp);
                        }
                        IDs = str.stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> defectiveItemsByStore = controller.getDefectiveItemsByStore(startDate, endDate, IDs);
                        if (defectiveItemsByStore.isOk() && defectiveItemsByStore.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"defective", "by store", IDsString, startDateStr, endDateStr});
                        else if (defectiveItemsByStore.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get defective reports by store");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "defective by category": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> defectiveItemsByCategory = controller.getDefectiveItemsByCategory(startDate, endDate, IDs);
                        if (defectiveItemsByCategory.isOk() && defectiveItemsByCategory.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"defective", "by category", IDsString, startDateStr, endDateStr});
                        else if (defectiveItemsByCategory.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get defective reports by category");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "defective by product": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> defectiveItemsByProduct = controller.getDefectiveItemsByProduct(startDate, endDate, IDs);
                        if (defectiveItemsByProduct.isOk() && defectiveItemsByProduct.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"defective", "by product", IDsString, startDateStr, endDateStr});
                        else if (defectiveItemsByProduct.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get defective reports by product");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "damaged by store": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> damagedItemsByStore = controller.getDamagedItemsByStore(startDate, endDate, IDs);
                        if (damagedItemsByStore.isOk() && damagedItemsByStore.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"damaged", "by store", IDsString, startDateStr, endDateStr});
                        else if (damagedItemsByStore.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get damaged reports by store");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "damaged by category": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> damagedItemsByCategory = controller.getDamagedItemsByCategory(startDate, endDate, IDs);
                        if (damagedItemsByCategory.isOk() && damagedItemsByCategory.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"damaged", "by category", IDsString, startDateStr, endDateStr});
                        else if (damagedItemsByCategory.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get damaged reports by category");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "damaged by product": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> damagedItemsByProduct = controller.getDamagedItemsByProduct(startDate, endDate, IDs);
                        if (damagedItemsByProduct.isOk() && damagedItemsByProduct.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"damaged", "by product", IDsString, startDateStr, endDateStr});
                        else if (damagedItemsByProduct.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get damaged reports by product");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "expired by store": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> expiredItemsByStore = controller.getExpiredItemsByStore(startDate, endDate, IDs);
                        if (expiredItemsByStore.isOk() && expiredItemsByStore.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"expired", "by store", IDsString, startDateStr, endDateStr});
                        else if (expiredItemsByStore.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get expired reports by store");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "expired by category": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> expiredItemsByCategory = controller.getExpiredItemsByCategory(startDate, endDate, IDs);
                        if (expiredItemsByCategory.isOk() && expiredItemsByCategory.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"expired", "by category", IDsString, startDateStr, endDateStr});
                        else if (expiredItemsByCategory.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get expired reports by category");
                            refresh(req, resp);
                        }
                        break;
                    }
                    case "expired by product": {
                        IDs = (Arrays.asList(IDsString.split(","))).stream().map(Integer::parseInt).collect(Collectors.toList());
                        Result<List<DefectiveItemReport>> expiredItemsByProduct = controller.getExpiredItemsByProduct(startDate, endDate, IDs);
                        if (expiredItemsByProduct.isOk() && expiredItemsByProduct.getValue().size() > 0)
                            redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Report.class, new String[]{"defective or damaged or expired", "by store or by category or by product", "IDs", "start date", "end date"}, new String[]{"expired", "by product", IDsString, startDateStr, endDateStr});
                        else if (expiredItemsByProduct.isOk()) {
                            setError("no reports");
                            refresh(req, resp);
                        } else {
                            setError("Failed to get expired reports by product");
                            refresh(req, resp);
                        }
                        break;
                    }
                }
            } catch (NumberFormatException e1){
                setError("Please enter a number!");
                refresh(req, resp);
            } catch (Exception e) {
                setError(e.getMessage());
                refresh(req, resp);
            }
        }
    }
}