package Domain.PersistenceLayer.Controllers;

import Domain.BusinessLayer.Supplier.Contact;
import Domain.BusinessLayer.Supplier.Supplier;
import Domain.PersistenceLayer.Abstract.DataMapper;
import Domain.PersistenceLayer.Abstract.LinkDAO;
import Domain.BusinessLayer.Supplier.Agreement.Agreement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SuppliersDAO extends DataMapper<Supplier> {


    private final static Map<String, Supplier> SUPPLIER_IDENTITY_MAP = new HashMap<>();

    private final ContactDAO contactDAO;
    private final ManufacturerDAO manufacturerDAO;
    private final AgreementController agreementController;



    private final static int ID_COLUMN = 1;
    private final static int BANK_COLUMN = 2;
    private final static int ADDRESS_COLUMN = 3;
    private final static int NAME_COLUMN = 4;
    private final static int PAYMENT_COLUMN = 5;
    private final static int AGREEMENTTYPE_COLUMN = 6;

    public SuppliersDAO(){
        super("Supplier");
        contactDAO = new ContactDAO();
        manufacturerDAO = new ManufacturerDAO();
        agreementController = new AgreementController();
    }


    @Override
    protected Map<String, Supplier> getMap() {
        return SUPPLIER_IDENTITY_MAP;
    }

    @Override
    protected LinkDAO getLinkDTO(String setName) {
        return null;
    }

    @Override
    protected Supplier buildObject(ResultSet instanceResult) throws Exception {
        return new Supplier(instanceResult.getInt(ID_COLUMN),instanceResult.getInt(BANK_COLUMN),instanceResult.getString(ADDRESS_COLUMN),instanceResult.getString(NAME_COLUMN)
                ,instanceResult.getString(PAYMENT_COLUMN));
    }

    @Override
    public void insert(Supplier supplier) throws SQLException {

        insert(Arrays.asList(String.valueOf(supplier.getId()), String.valueOf(supplier.getBankNumber()),
                String.valueOf(supplier.getAddress()), String.valueOf(supplier.getName()), String.valueOf(supplier.getPayingAgreement()), String.valueOf(supplier.getAgreementType())));

        List<Contact> contacts = supplier.getAllContact();
        if(contacts != null && contacts.size()> 0){
            for(Contact contact : contacts){
                contactDAO.addContact(supplier.getId(), contact);
            }
        }

        List<String> manufacturers = supplier.getManufacturers();
        if(manufacturers != null && manufacturers.size() > 0){
            for(String manufacturer : manufacturers){
                manufacturerDAO.addManufacturer(supplier.getId(), manufacturer);
            }
        }

    }



    public Supplier getSupplier(int id){

        try {
            return get(String.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        /*
        Supplier output = SUPPLIER_IDENTITY_MAP.get(String.valueOf(id));
        if (output != null)
            return output;

        try(Connection connection = getConnection()) {
            ResultSet instanceResult = select(connection,id);
            if (!instanceResult.next())
                return null;

            //    (int id, int bankNumber, String address, String name, String payingAgreement)
            output = new Supplier(instanceResult.getInt(1),instanceResult.getInt(2),instanceResult.getString(3),instanceResult.getString(4)
                    ,instanceResult.getString(5));
            SUPPLIER_IDENTITY_MAP.put( String.valueOf(id) ,output);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return output;

         */
    }


    public ArrayList<Supplier> getAllSuppliers(){
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for(Supplier supplier : SUPPLIER_IDENTITY_MAP.values()){
            suppliers.add(supplier);
        }
        return suppliers;
    }



    //DON'T NEED THIS FOR NOW, Using save
    /*
    public void addSupplier(Supplier supplier, ArrayList<Contact> contacts, ArrayList<String> manufacturers) throws SQLException {
        insert(Arrays.asList(String.valueOf(supplier.getId()), String.valueOf(supplier.getBankNumber()),
                String.valueOf(supplier.getAddress()), String.valueOf(supplier.getName()), String.valueOf(supplier.getPayingAgreement()), String.valueOf(supplier.getAgreementType())));
        if(contacts != null && contacts.size()> 0){
            for(Contact contact : contacts){
                contactDAO.addContact(supplier.getId(), contact);
            }
        }
        if(manufacturers != null && manufacturers.size() > 0){
            for(String manufacturer : manufacturers){
                manufacturerDAO.addManufacturer(supplier.getId(), manufacturer);
            }
        }
        SUPPLIER_IDENTITY_MAP.put(String.valueOf(supplier.getId()) , supplier);
    }
     */

    public void removeSupplier(int id){
        try {

            manufacturerDAO.remove(id);
            contactDAO.remove(id);
            agreementController.removeSupplier(id);

            remove(id);
            SUPPLIER_IDENTITY_MAP.remove(id);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public boolean supplierExist(int id){
        //If we upload all the suppliers from the beginning we should have all of them in the map
        if(SUPPLIER_IDENTITY_MAP.containsKey(String.valueOf(id)))
            return true;
        return false;
    }

    public boolean isEmpty() {
        return (getAllSuppliers().size() == 0);

    }

    public void addSupplierContact(int id, Contact contact) {
        contactDAO.addContact(id, contact);
    }

    public void removeSupplierContact(int supID, String name) {
        contactDAO.removeContact(supID, name);
    }

    public ArrayList<Contact> getAllSupplierContacts(int supID) {
        return contactDAO.getAllSupplierContact(supID);
    }

    public void addSupplierManufacturer(int id, String name){
        manufacturerDAO.addManufacturer(id, name);
    }

    public void removeSupplierManufacturer(int id, String name){
        manufacturerDAO.removeManufacturer(id, name);
    }

    public ArrayList<String> getAllSupplierManufacturers(int id){
        return manufacturerDAO.getAllSupplierManufacturer(id);
    }


    public void updateSupplierAddress(int id, String address) {

        try {

            updateProperty(String.valueOf(id), ADDRESS_COLUMN , address);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void updateSupplierBankNumber(int id, int bankNumber) {
        try {
            updateProperty(String.valueOf(id), BANK_COLUMN, bankNumber);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void updateSupplierName(int id, String newName) {
        try {
            updateProperty(String.valueOf(id), NAME_COLUMN, newName);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void updateSupplierPayingAgreement(int id, String payingAgreement) {
        try {
            updateProperty(String.valueOf(id), PAYMENT_COLUMN, payingAgreement);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }



    public void updateAgreementType(int id, int agreementType) {
        try {
            updateProperty(String.valueOf(id), AGREEMENTTYPE_COLUMN, agreementType);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }





    /*
    public void addAgreement(int supplierId, int agreementType, String agreementDays) throws Exception {
        if(agreementType > NOT_TRANSPORTING || agreementType < ROUTINE)
            throw new Exception("Invalid agreement type!");
        Supplier supplier = getSupplier(supplierId);
        createAgreement(supplier, agreementType, agreementDays);
    }


    private void createAgreement(Supplier supplier, int agreementType, String agreementDays) throws Exception {

        updateAgreementType(supplier.getId(), agreementType);

        switch(agreementType){
            case ROUTINE :
                if(!RoutineAgreement.hasDays(agreementDays)){
                    throw new Exception("You provided no days!");
                }
                routineDAO.addDaysOfDelivery(supplier.getId(), agreementDays);
                break;
            case BY_ORDER :
                byOrderDAO.addTime(supplier.getId(), agreementDays);
                break;
            case NOT_TRANSPORTING :
                selfTransportDAO.updateSupplier(supplier.getId());
                break;
        }
    }
     */



    public boolean isTransporting(int supplierId) {
        try(Connection connection = getConnection()) {
            ResultSet result = select(connection,Arrays.asList(6), Arrays.asList(1), Arrays.asList(supplierId) );
            if(result.next()){
                int resultInt = result.getInt(1);
                //return resultInt == BY_ORDER || resultInt == ROUTINE;
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean hasAgreement(int supID) {
        try(Connection connection = getConnection()) {
            ResultSet result = select(connection,Arrays.asList(6), Arrays.asList(1), Arrays.asList(supID) );
            if(result.next()){
                int resultInt = result.getInt(1);
                return resultInt != -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public AgreementController getAgreementController() {
        return agreementController;
    }

    public AgreementItemDAO getAgreementItemDAO() {
        return agreementController.getAgreementItemDAO();
    }


    public int loadAllSuppliersInfo() throws SQLException {
        int largestId = loadAllSuppliers();
        loadAllAgreementsAndAgreementItems();
        return largestId;
    }

    private void loadAllAgreementsAndAgreementItems()  {
        try(Connection connection = getConnection()) {
            for(String id : SUPPLIER_IDENTITY_MAP.keySet()){
                ResultSet resultSet = select(connection, Arrays.asList(AGREEMENTTYPE_COLUMN), Arrays.asList(ID_COLUMN), Arrays.asList(Integer.parseInt(id)));
                if(resultSet.next()){
                    int agreementType = resultSet.getInt(1);
                    Agreement currAgreement = agreementController.loadAgreementAndItems(Integer.parseInt(id), agreementType);
                    SUPPLIER_IDENTITY_MAP.get(id).addAgreementFromDB(currAgreement);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private int loadAllSuppliers() {
        ArrayList<Integer> supplierIds = new ArrayList<>();  // get the largest one and set global id to be biggest+1
        try(Connection connection = getConnection()) {
            ResultSet instanceResult = select(connection);
            while (instanceResult.next()) {
                //Load without contacts and manufacturers
                //Supplier currSupplier = new Supplier(instanceResult.getInt(1), instanceResult.getInt(2),
                //        instanceResult.getString(3), instanceResult.getString(4), instanceResult.getString(5));
                //SUPPLIER_IDENTITY_MAP.put(String.valueOf(currSupplier.getId()), currSupplier);

                //Load everything!
                int id = instanceResult.getInt(1);
                supplierIds.add(id);
                Supplier currSupplier = new Supplier(id, instanceResult.getInt(2),
                        instanceResult.getString(3), instanceResult.getString(4), instanceResult.getString(5)
                        , contactDAO.getAllSupplierContact(id), manufacturerDAO.getAllSupplierManufacturer(id));
                SUPPLIER_IDENTITY_MAP.put(String.valueOf(currSupplier.getId()), currSupplier);
            }
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }

        Collections.sort(supplierIds, Collections.reverseOrder());
        if(supplierIds.isEmpty())
            return 0;
        return supplierIds.get(0);
    }
}