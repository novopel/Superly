package Domain.PersistenceLayer.Controllers;

import Domain.BusinessLayer.Inventory.Category;
import Domain.BusinessLayer.Supplier.Contact;
import Domain.BusinessLayer.Supplier.Order;
import Domain.BusinessLayer.Supplier.OrderItem;
import Domain.BusinessLayer.Supplier.Supplier;
import Domain.PersistenceLayer.Abstract.DataMapper;
import Domain.PersistenceLayer.Abstract.LinkDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class OrderDAO extends DataMapper<Order> {

    private final static Map<String, Order> ORDER_IDENTITY_MAP = new HashMap<>();

    private final OrderItemDAO orderItemDAO;


    private final static int ORDER_ID_COLUMN = 1;
    private final static int SUPPLIER_ID_COLUMN = 2;
    private final static int STORE_ID_COLUMN = 3;
    private final static int CREATION_TIME_COLUMN = 4;
    private final static int ARRIVAL_TIME_COLUMN = 5;


    public OrderDAO(){
        super("Orders");
        orderItemDAO = new OrderItemDAO();
    }

    public Order getOrder(int orderId) throws Exception {
        if(ORDER_IDENTITY_MAP.containsKey(String.valueOf(orderId)))
            return ORDER_IDENTITY_MAP.get(String.valueOf(orderId));
        return get(String.valueOf(orderId));
    }


    @Override
    protected Map<String, Order> getMap() {
        return ORDER_IDENTITY_MAP;
    }

    @Override
    protected LinkDAO getLinkDTO(String setName) {
        return null;
    }

    @Override
    protected Order buildObject(ResultSet instanceResult) throws Exception {

        return new Order(instanceResult.getInt(ORDER_ID_COLUMN),
                instanceResult.getInt(SUPPLIER_ID_COLUMN),
                LocalDate.parse(instanceResult.getString(CREATION_TIME_COLUMN)),
                LocalDate.parse(instanceResult.getString(ARRIVAL_TIME_COLUMN)),
                instanceResult.getInt(STORE_ID_COLUMN));
    }

    @Override
    public void insert(Order order) throws SQLException {
        insert(Arrays.asList(String.valueOf(order.getId()), String.valueOf(order.getSupplierId()),
                String.valueOf(order.getStoreID()), String.valueOf(order.getCreationTime()), String.valueOf(order.getArrivaltime())));

        ArrayList<OrderItem> items = order.getOrderItems();
        if(items != null && items.size() > 0){
            for(OrderItem orderItem : items){
                orderItemDAO.addItem(order.getId(), orderItem);
            }
        }
    }

    public void addOrder(Order order) throws SQLException {
        insert(order);
        ORDER_IDENTITY_MAP.put(String.valueOf(order.getId()), order);

    }

    public void addItem(int orderId, OrderItem orderItem) throws SQLException {

        orderItemDAO.addItem(orderId, orderItem);
    }

    public void removeOrder(int orderId) throws SQLException {
        remove(orderId);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(orderId);
        orderItemDAO.removeOrders(ids);
        ORDER_IDENTITY_MAP.remove(String.valueOf(orderId));
    }

    public void removeOrderItem(int orderId, int productId) throws SQLException {
        orderItemDAO.removeItem(orderId, productId);
    }

    public void updateItemQuantity(int orderId, int productId, int quantity) throws SQLException {
        orderItemDAO.updateItemQuantity(orderId, productId, quantity);
    }

    public void updateItemDiscount(int orderId, int productId, int discount) throws SQLException {
    orderItemDAO.updateItemDiscount(orderId, productId, discount);
    }

    public void updateItemFinalPrice(int orderId, int productId, double finalPrice) throws SQLException {
        orderItemDAO.updateItemFinalPrice( orderId, productId, finalPrice);
    }

    public boolean containsKey(int id) throws Exception {
        if(ORDER_IDENTITY_MAP.containsKey(String.valueOf(id)))
            return true;
        Order order = getOrder(id);

        return order != null;
    }

    public void updateOrder(Order newOrder) throws SQLException {
        removeOrder(newOrder.getId());
        addOrder(newOrder);
        //orderItemDAO.updateItems(newOrder.getId(), newOrder.getOrderItems());  its updates the items in the addOrder
        ORDER_IDENTITY_MAP.replace(String.valueOf(newOrder.getId()), newOrder);

    }

    public ArrayList<Order> getLastOrdersFromALlSuppliers(ArrayList<Integer> orderIds) {
        ArrayList<Order> result = new ArrayList<>();
        for(Integer orderId : orderIds){
            try(Connection connection = getConnection()) {
                ResultSet instanceResult = select(connection, orderId);
                while (instanceResult.next()) {
                    Order order = buildObject(instanceResult);
                    result.add(order);
                }
            } catch (Exception throwables) {
                System.out.println(throwables.getMessage());
            }
        }

        return result;
    }

    public int getGlobalId() {
        ArrayList<Integer> ids = new ArrayList<>();
        try(Connection connection = getConnection()) {
            ResultSet instanceResult = select(connection);
            while (instanceResult.next()) {
                ids.add(instanceResult.getInt(1));
            }
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
        Collections.sort(ids, Collections.reverseOrder());
        if(ids.isEmpty())
            return 0;
        return ids.get(0);
    }

    public void removeSupplierOrders(int supplierId) throws SQLException {
        List<Integer> ordersIds = getSupplierOrdersIds(supplierId);
        orderItemDAO.removeOrders(ordersIds);
        remove(Arrays.asList(SUPPLIER_ID_COLUMN), Arrays.asList(supplierId));
    }

    private ArrayList<Integer> getSupplierOrdersIds(int supplierId) {
        ArrayList<Integer> ids = new ArrayList<>();
        try(Connection connection = getConnection()) {
            ResultSet instanceResult = select(connection, Arrays.asList(ORDER_ID_COLUMN),  Arrays.asList(SUPPLIER_ID_COLUMN), Arrays.asList(supplierId));
            while (instanceResult.next()) {
                ids.add(instanceResult.getInt(1));
            }
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
        return ids;
    }

    public ArrayList<OrderItem> uploadAllItemsFromOrder(int orderId, AgreementItemDAO agreementItemDAO) {
        return orderItemDAO.uploadAllItemsFromOrder(orderId, agreementItemDAO);
    }



}

