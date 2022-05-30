package Domain.DAL.Controllers.TransportMudel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TransportTransportOrderDAO extends LinkDAO<Integer> {
    public TransportTransportOrderDAO() {
        super("TransportOrders");
    }

    @Override
    protected Integer buildObject(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(2);
    }

    public Set<Integer> get(int id) throws SQLException {
        return super.get(Integer.toString(id));
    }
}
