package Domain.DAL.Controllers.InventoryAndSuppliers;

import Domain.Business.Objects.Supplier.*;
import Domain.DAL.Abstract.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrderItemDAO extends DAO {

    private final static Map<String, OrderItem> ORDER_ITEM_IDENTITY_MAP = new HashMap<>();



    private final static int PRODUCT_ID_COLUMN = 1;
    private final static int ORDER_ID_COLUMN = 2;
    private final static int ID_BY_SUPPLIER_COLUMN = 3;
    private final static int QUANTITY_COLUMN = 4;
    private final static int PPU_COLUMN = 5;
    private final static int DISCOUNT_COLUMN = 6;
    private final static int FINAL_PRICE_COLUMN = 7;
    private final static int MISSING_ITEMS_PRICE_COLUMN = 8;
    private final static int DEFECTIVE_ITEMS_PRICE_COLUMN = 9;
    private final static int DESCRIPTION_COLUMN = 10;
    private final static int WEIGHT_COLUMN = 10;



    public OrderItemDAO() {
        super("OrderItem");
    }


    public void addItem(int orderId, OrderItem orderItem) throws SQLException {

        insert(Arrays.asList(String.valueOf(orderItem.getProductId()), String.valueOf(orderId),
                String.valueOf(orderItem.getIdBySupplier()), String.valueOf(orderItem.getQuantity()), String.valueOf(orderItem.getPricePerUnit())
                , String.valueOf(orderItem.getDiscount()), String.valueOf(orderItem.getFinalPrice()),
                String.valueOf(orderItem.getMissingItems()), String.valueOf(orderItem.getDefectiveItems()),
                String.valueOf(orderItem.getDescription()), String.valueOf(orderItem.getWeight())));

        ORDER_ITEM_IDENTITY_MAP.put(String.valueOf(orderItem.getProductId()), orderItem);
    }

    public ArrayList<OrderItem> uploadAllItemsFromOrder(int orderId, AgreementItemDAO agreementItemDAO){
        ArrayList<OrderItem> output = new ArrayList<>();
        try(Connection connection = getConnection()) {
            ResultSet instanceResult = select(connection, Arrays.asList(ORDER_ID_COLUMN), Arrays.asList(orderId));

            while (instanceResult.next()) {
                String itemName = agreementItemDAO.getNameOfItem(instanceResult.getInt(PRODUCT_ID_COLUMN));

                OrderItem currItem = new OrderItem(instanceResult.getInt(PRODUCT_ID_COLUMN), instanceResult.getInt(ID_BY_SUPPLIER_COLUMN),
                        itemName, instanceResult.getInt(QUANTITY_COLUMN), instanceResult.getFloat(PPU_COLUMN),
                        instanceResult.getInt(DISCOUNT_COLUMN), instanceResult.getDouble(FINAL_PRICE_COLUMN),
                        instanceResult.getDouble(WEIGHT_COLUMN));

                currItem.setMissingItems(instanceResult.getInt(MISSING_ITEMS_PRICE_COLUMN));
                currItem.setDefectiveItems(instanceResult.getInt(DEFECTIVE_ITEMS_PRICE_COLUMN));
                currItem.setDescription(instanceResult.getString(DESCRIPTION_COLUMN));

                ORDER_ITEM_IDENTITY_MAP.put(String.valueOf(currItem.getProductId()), currItem);
                output.add(currItem);
            }
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }

        return output;
    }

    public void removeItem(int orderId, int productId) throws SQLException {
        remove(Arrays.asList(PRODUCT_ID_COLUMN, ORDER_ID_COLUMN ), Arrays.asList(productId, orderId ));
    }

    public void updateItemQuantity(int orderId, int productId, int quantity) throws SQLException {
        update(Arrays.asList(QUANTITY_COLUMN), Arrays.asList(quantity), Arrays.asList(PRODUCT_ID_COLUMN,ORDER_ID_COLUMN), Arrays.asList(productId, orderId));
    }

    public void updateItemDiscount(int orderId, int productId, int discount) throws SQLException {
        update(Arrays.asList(DISCOUNT_COLUMN), Arrays.asList(discount), Arrays.asList(PRODUCT_ID_COLUMN,ORDER_ID_COLUMN), Arrays.asList(productId, orderId));
    }

    public void updateItemFinalPrice(int orderId, int productId, double finalPrice) throws SQLException {
        update(Arrays.asList(FINAL_PRICE_COLUMN), Arrays.asList(finalPrice), Arrays.asList(PRODUCT_ID_COLUMN,ORDER_ID_COLUMN), Arrays.asList(productId, orderId));
    }

    public void removeOrders(List<Integer> ordersIds) throws SQLException {
        for(Integer id : ordersIds){
            remove(Arrays.asList(ORDER_ID_COLUMN), Arrays.asList(id));
        }
    }


    public void updateItems(int orderId, ArrayList<OrderItem> orderItems) throws SQLException {
        for(OrderItem orderItem : orderItems){
            removeItem(orderId, orderItem.getProductId());
            addItem(orderId, orderItem);
        }
    }

}
