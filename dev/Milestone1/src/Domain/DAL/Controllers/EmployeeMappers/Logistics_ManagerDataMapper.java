package Domain.DAL.Controllers.EmployeeMappers;
import Domain.Business.Objects.Employee.Logistics_Manager;
import Domain.DAL.Controllers.EmployeeLinks.EmployeeCertificationDAO;

import java.sql.ResultSet;
import java.util.*;

public class Logistics_ManagerDataMapper extends AbstractEmployeeDataMapper<Logistics_Manager> {
    private static Map<String, Logistics_Manager> LOGISTIC_MANAGERS_MAP = new HashMap<>();
    EmployeeCertificationDAO employeeCertificationController ;


    public Logistics_ManagerDataMapper() {
        super("Logistics_Manager");
        employeeCertificationController = new EmployeeCertificationDAO();
    }



    @Override
    protected Map<String, Logistics_Manager> getMap() {
        return LOGISTIC_MANAGERS_MAP;
    }


    @Override
    protected Logistics_Manager buildObject(ResultSet instanceResult) throws Exception {
        return new Logistics_Manager(instanceResult.getString(1),instanceResult.getString(2),instanceResult.getString(3),instanceResult.getInt(4),instanceResult.getString(5),instanceResult.getDate(6).toLocalDate(),employeeCertificationController.get(instanceResult.getString(1)));
    }

}
