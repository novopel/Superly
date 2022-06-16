package Presentation.WebPresentation.Screens.ViewModels.InventoryScreens;

import Domain.Service.Objects.InventoryObjects.Product;
import Domain.Service.util.Result;
import Presentation.WebPresentation.Screens.Models.HR.Employee;
import Presentation.WebPresentation.Screens.Models.HR.Logistics_Manager;
import Presentation.WebPresentation.Screens.Screen;
import Presentation.WebPresentation.Screens.ViewModels.HR.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Categories extends Screen{

    private static final String greet = "Categories";
    private static final String viewButton = "View category";
    private static final String addButton = "Add category";
    private static final String removeButton = "Remove category";

    public static final Set<Class<? extends Employee>> ALLOWED = new HashSet<>(0);

    public Categories() {
        super(greet, ALLOWED);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!isAllowed(req, resp)) {
            redirect(resp, Login.class);
        }
        header(resp);
        greet(resp);
        printForm(resp, new String[] {"ID"}, new String[]{"Category ID"}, new String[]{viewButton});
        printForm(resp, new String[] {"ID"}, new String[]{"Category ID"}, new String[]{removeButton});
        printForm(resp, new String[] {"category name", "parent category ID"}, new String[]{"Category name", "Parent category ID"}, new String[]{addButton});
        printCategories(resp);
        handleError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleHeader(req, resp);
        if (isButtonPressed(req, removeButton)){
            if (!isAllowed(req, resp, new HashSet<>(Arrays.asList(Logistics_Manager.class)))) {
                setError("You have no permission to delete category");
                refresh(req, resp);
                return;
            }
            try {
                int categoryID = Integer.parseInt(req.getParameter("ID"));
                if(controller.deleteCategory(categoryID).getValue()) {
                    PrintWriter out = resp.getWriter();
                    out.println(String.format("<p style=\"color:green\">%s</p><br><br>", String.format("Removed category %d", categoryID)));

                    //setError(String.format("Removed supplier %d", supplierId));
                    refresh(req, resp);
                }
                else{
                    setError("Category ID " + categoryID + " doesn't exist!");
                    refresh(req, resp);
                }
            }catch (NumberFormatException e1){
                setError("Please enter a number!");
                refresh(req, resp);
            }
            catch (Exception e) {
                setError(e.getMessage());
                refresh(req, resp);
            }
        }
        else if(isButtonPressed(req, addButton)){
            if (!isAllowed(req, resp, new HashSet<>(Arrays.asList(Logistics_Manager.class)))) {
                setError("You have no permission to add category");
                refresh(req, resp);
                return;
            }
            try {
                String categoryName = req.getParameter("category name");
                int parentCategoryID = Integer.parseInt(req.getParameter("parent category ID"));

                if(controller.addNewCategory(categoryName, parentCategoryID).isOk()) {
                    PrintWriter out = resp.getWriter();
                    out.println(String.format("<p style=\"color:green\">%s</p><br><br>", String.format("Added new category %d", categoryName)));

                    //setError(String.format("Removed supplier %d", supplierId));
                    refresh(req, resp);
                }
                else{
                    setError("Category wasn't added");
                    refresh(req, resp);
                }
            }catch (NumberFormatException e1){
                setError("Please enter a number!");
                refresh(req, resp);
            }
            catch (Exception e) {
                setError(e.getMessage());
                refresh(req, resp);
            }
        }
        else if(isButtonPressed(req, viewButton)){
            if (!isAllowed(req, resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Category.ALLOWED)) {
                setError("You have no permission to view category");
                refresh(req, resp);
                return;
            }
            try {
                redirect(resp, Presentation.WebPresentation.Screens.ViewModels.InventoryScreens.Category.class);
            }catch (NumberFormatException e1){
                setError("Please enter a number!");
                refresh(req, resp);
            }
            catch (Exception e) {
                setError(e.getMessage());
                refresh(req, resp);
            }
        }
    }
    /*private void printCategories(HttpServletResponse resp) {
        try {
            Result<List<Domain.Service.Objects.InventoryObjects.Category>> categories = controller.getCategories();
            PrintWriter out = resp.getWriter();
            out.println("number of categories exist: " + categories.getValue().size());
            for (Domain.Service.Objects.InventoryObjects.Category c: categories.getValue()) {
                out.println(c.getName() + ": " + c.getID() + ", Parent category: " + c.getParentCategory());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    private void printCategories(HttpServletResponse resp) {
        try {
            Result<List<Domain.Service.Objects.InventoryObjects.Category>> categories = controller.getCategories();
            PrintWriter out = resp.getWriter();
            List<Domain.Service.Objects.InventoryObjects.Category> sortedProducts = sort(categories.getValue());
            for (Domain.Service.Objects.InventoryObjects.Category c: sortedProducts) {
                out.println(c.getName() + ": " + c.getID() + "<br>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Domain.Service.Objects.InventoryObjects.Category> sort(List<Domain.Service.Objects.InventoryObjects.Category> categories) {
        List<Integer> ids = new ArrayList<>();
        for (Domain.Service.Objects.InventoryObjects.Category c: categories) {
            ids.add(c.getID());
        }
        Collections.sort(ids);
        List<Domain.Service.Objects.InventoryObjects.Category> sortedList = new ArrayList<>();
        for (Integer id : ids) {
            Domain.Service.Objects.InventoryObjects.Category c = findProduct(categories, id);
            if (c!=null)
                sortedList.add(c);
        }
        return sortedList;
    }
    private Domain.Service.Objects.InventoryObjects.Category findProduct(List<Domain.Service.Objects.InventoryObjects.Category> categories, int id) {
        for (Domain.Service.Objects.InventoryObjects.Category c : categories) {
            if (c.getID()==id)
                return c;
        }
        return null;
    }
}