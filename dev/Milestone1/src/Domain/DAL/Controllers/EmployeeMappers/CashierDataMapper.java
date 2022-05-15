package Domain.DAL.Controllers.EmployeeMappers;
import Domain.Business.Objects.Cashier;
import Domain.DAL.Abstract.EmployeeTypeDataMapper;
import Domain.DAL.Abstract.LinkDAO;
import Domain.DAL.Abstract.ObjectDateMapper;
import Domain.DAL.Controllers.EmployeeLinks.EmployeeCertificationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CashierDataMapper extends EmployeeTypeDataMapper<Cashier> {
    //static fields
    private final static Map<String, Cashier> CASHIER_IDENTITY_MAP = new HashMap<>();

    // fields
    private final EmployeeCertificationDAO employeeCertificationController;

    //constructor

    public CashierDataMapper() {
        super("Cashiers");
        employeeCertificationController = new EmployeeCertificationDAO();
    }


    @Override
    protected Map<String, Cashier> getMap() {
        return CASHIER_IDENTITY_MAP;
    }


    @Override
    protected Cashier buildObject(ResultSet instanceResult) throws Exception {
        return new Cashier(instanceResult.getString(1),instanceResult.getString(2),instanceResult.getString(3),instanceResult.getInt(4),instanceResult.getString(5),instanceResult.getDate(6).toLocalDate(),employeeCertificationController.get(instanceResult.getString(1)));
    }
}