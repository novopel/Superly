package Domain.DAL.Controllers.EmployeeMappers;

import Domain.Business.Objects.Employee.Transport_Manager;
import Domain.DAL.Controllers.EmployeeLinks.EmployeeCertificationDAO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransportManagerDAO extends AbstractEmployeeDAO<Transport_Manager> {
    private static Map<String, Transport_Manager> TRANSPORT_MANAGER_IDENTITY_MAP = new ConcurrentHashMap<>();


    public TransportManagerDAO() {
        super("TransportManagers");
    }



    @Override
    protected Map<String, Transport_Manager> getMap() {
        return TRANSPORT_MANAGER_IDENTITY_MAP;
    }

    @Override
    protected Transport_Manager buildObject(ResultSet instanceResult) throws Exception {
        return new Transport_Manager(instanceResult.getString(1),instanceResult.getString(2),instanceResult.getString(3),instanceResult.getInt(4),instanceResult.getDate(6).toLocalDate(),employeeCertificationController.get(instanceResult.getString(1)));
    }
}
