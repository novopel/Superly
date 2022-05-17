package Domain.PersistenceLayer.Controllers;

import Domain.BusinessLayer.Supplier.Agreement.Agreement;
import Domain.BusinessLayer.Supplier.Agreement.RoutineAgreement;
import Domain.PersistenceLayer.Abstract.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoutineDAO extends DAO {

    public RoutineDAO() {
        super("Routine");
    }

    private final static int SUPPLIER_ID_COLUMN = 1;
    private final static int DAY_COLUMN = 2;
    private final static int LAST_ORDER_ID_COLUMN = 3;

    public void addDaysOfDelivery(int supplierId, List<Integer> daysOfDelivery) {
        for(Integer currDay : daysOfDelivery){
            try {
                insert(Arrays.asList(supplierId, currDay, -1));
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
    }


    public Agreement loadAgreement(int supplierId) {
        List<Integer> days = new ArrayList<>();
        ResultSet resultSet = null;
        int lastOrderId = -1;
        try(Connection connection = getConnection()) {
            resultSet = select(connection, supplierId);
            while(resultSet.next()){
                days.add(resultSet.getInt(DAY_COLUMN));
                lastOrderId = resultSet.getInt(LAST_ORDER_ID_COLUMN);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new RoutineAgreement(days, lastOrderId);
    }

    public void setLastOrderId(int supplierId, int orderId) throws SQLException {
        // (List<Integer> columnsLocationToUpdate,List<Object> valuesToUpdate,List<Integer> conditionColumnLocation,List<Object> conditionValues) throws SQLException {
        update(Arrays.asList(LAST_ORDER_ID_COLUMN), Arrays.asList(orderId), Arrays.asList(SUPPLIER_ID_COLUMN), Arrays.asList(supplierId));
    }
}