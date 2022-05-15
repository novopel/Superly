package Domain.DAL.Controllers.EmployeeMappers;
import Domain.Business.Objects.*;
import Domain.DAL.Controllers.EmployeeLinks.EmployeeTypeLink;
import Globals.Enums.JobTitles;

import java.sql.SQLException;
import java.util.*;

public class EmployeeDataMapper  {

    private static String RUNTIME_ERROR_MSG = "FATAL ERROR WITH DB CONNECTION. STOP WORK IMMEDIATELY!";
    // properties
    private final CarrierDataMapper carrierDataMapper;
    private final CashierDataMapper cashierDataMapper;
    private final HR_ManagerDataMapper hR_managerDataMapper;
    private final Logistics_ManagerDataMapper logistics_managerDataMapper;
    private final SorterDataMapper sorterDataMapper;
    private final StorekeeperDataMapper storekeeperDataMapper;
    private final TransportManagerDataMapper transportManagerDataMapper;
    private final EmployeeTypeLink employeeTypeLink;


    // constructor
    public EmployeeDataMapper() {
        carrierDataMapper = new CarrierDataMapper();
        cashierDataMapper = new CashierDataMapper();
        hR_managerDataMapper = new HR_ManagerDataMapper();
        logistics_managerDataMapper = new Logistics_ManagerDataMapper();
        sorterDataMapper = new SorterDataMapper();
        storekeeperDataMapper = new StorekeeperDataMapper();
        employeeTypeLink = new EmployeeTypeLink();
        transportManagerDataMapper = new TransportManagerDataMapper();
    }

    public Employee get(String id) throws Exception {
        try {
            Set<JobTitles> type = employeeTypeLink.get(id);
            if (type.isEmpty())
                return null;

            switch (new ArrayList<>(type).get(0)){
                case Carrier:
                    return carrierDataMapper.get(id);

                case Cashier:
                    return cashierDataMapper.get(id);
                case HR_Manager:
                    return hR_managerDataMapper.get(id);

                case Logistics_Manager:
                    return logistics_managerDataMapper.get(id);

                case Sorter:
                    return sorterDataMapper.get(id);

                case Storekeeper:
                    return storekeeperDataMapper.get(id);

                case Transport_Manager:
                    return transportManagerDataMapper.get(id);

                default:
                    throw new IllegalArgumentException("Illegal employee type saved in the db");
            }
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void save(Employee toSave) throws Exception{
        try {
            toSave.save(this);

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    public void save(Carrier toSave) throws Exception {
        try {
            employeeTypeLink.add(toSave.getId(),JobTitles.Carrier);
            carrierDataMapper.save(toSave.getId(),toSave);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }

    }

    public void save(Cashier toSave) throws Exception {
        try {
            employeeTypeLink.add(toSave.getId(),JobTitles.Cashier);
            cashierDataMapper.save(toSave.getId(),toSave);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }

    }

    public void save(HR_Manager toSave) throws Exception {
        try {
            employeeTypeLink.add(toSave.getId(),JobTitles.HR_Manager);
            hR_managerDataMapper.save(toSave.getId(),toSave);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }

    }

    public void save(Logistics_Manager toSave) throws Exception {
        try {
            employeeTypeLink.add(toSave.getId(),JobTitles.Logistics_Manager);
            logistics_managerDataMapper.save(toSave.getId(),toSave);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    public void save(Sorter toSave) throws Exception {
        try {
            employeeTypeLink.add(toSave.getId(),JobTitles.Sorter);
            sorterDataMapper.save(toSave.getId(),toSave);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    public void save(Storekeeper toSave) throws Exception {
        try {
            employeeTypeLink.add(toSave.getId(),JobTitles.Storekeeper);
            storekeeperDataMapper.save(toSave.getId(),toSave);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    public void save(Transport_Manager toSave) throws Exception {
        try {
            employeeTypeLink.add(toSave.getId(),JobTitles.Transport_Manager);
            transportManagerDataMapper.save(toSave.getId(),toSave);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    public void update(Employee employee) throws Exception {
        try {
            employee.update(this);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    public void update(Carrier employee) throws Exception {
        try {
            carrierDataMapper.insert(employee);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void update(Cashier employee) throws Exception {
        try {
            cashierDataMapper.insert(employee);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void update(HR_Manager employee) throws Exception {
        try {
            hR_managerDataMapper.insert(employee);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void update(Logistics_Manager employee) throws Exception {
        try {
            logistics_managerDataMapper.insert(employee);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void update(Sorter employee) throws Exception {
        try {
            sorterDataMapper.insert(employee);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void update(Storekeeper employee) throws Exception {
        try {
            storekeeperDataMapper.insert(employee);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void update(Transport_Manager employee) throws Exception {
        try {
            transportManagerDataMapper.insert(employee);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    //TODO
    public Carrier getCarrier(String id) throws Exception {
        try {
            return carrierDataMapper.get(id);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Cashier getCashier(String id) throws Exception {
        try {
           return cashierDataMapper.get(id);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Sorter getSorter(String id) throws Exception {
        try {
           return sorterDataMapper.get(id);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Storekeeper getStorekeeper(String id) throws Exception {
        try {
          return   storekeeperDataMapper.get(id);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public HR_Manager getHR_Manager(String id) throws Exception {
        try {
          return   hR_managerDataMapper.get(id);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Logistics_Manager getLogistics_Manager(String id) throws Exception {
        try {
           return logistics_managerDataMapper.get(id);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Transport_Manager getTransport_Manager(String id) throws Exception {
        try {
           return transportManagerDataMapper.get(id);
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }

        //return ALL of type
    }
    public Set<Employee> get() throws Exception {
        try {
            Set<Employee> output =new HashSet<>();
            output.addAll(getCarrier());
            output.addAll(getCashier());
            output.addAll(getSorter());
            output.addAll(getStorekeeper());
            output.addAll(getHR_Manager());
            output.addAll(getLogistics_Manager());
            output.addAll(getTransport_Manager());
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Set<Carrier> getCarrier() throws Exception {
        try {

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Set<Cashier> getCashier() throws Exception {
        try {

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Set<Sorter> getSorter() throws Exception {
        try {

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Set<Storekeeper> getStorekeeper() throws Exception {
        try {

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Set<HR_Manager> getHR_Manager() throws Exception {
        try {

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Set<Logistics_Manager> getLogistics_Manager() throws Exception {
        try {

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public Set<Transport_Manager> getTransport_Manager() throws Exception {
        try {

        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }

    public boolean validateId(String id) throws Exception{
        try {
            return !employeeTypeLink.get(id).isEmpty();
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
    public void delete(String id) throws Exception{
        try {
        }catch (SQLException e){
            throw new RuntimeException(RUNTIME_ERROR_MSG);
        }
    }
}
