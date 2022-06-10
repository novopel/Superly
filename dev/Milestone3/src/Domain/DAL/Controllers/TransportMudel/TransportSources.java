package Domain.DAL.Controllers.TransportMudel;
import Domain.DAL.Abstract.LinkDAO;

import java.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportSources extends LinkDAO<Integer> {
    public TransportSources() {
        super("TransportSources");
    }

    @Override
    protected Integer buildObject(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(2);
    }

    public Set<Integer> get(int id) throws SQLException {
        return super.get(Integer.toString(id));
    }
}