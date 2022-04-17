package SuperLee.BusinessLayer;

import SuperLee.BusinessLayer.Agreement.Agreement;
import SuperLee.BusinessLayer.Agreement.ByOrderAgreement;
import SuperLee.BusinessLayer.Agreement.NotTransportingAgreement;
import SuperLee.BusinessLayer.Agreement.RoutineAgreement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Supplier {

    private int id;
    private int bankNumber;
    private String address;
    private String name;
    private ArrayList<Contact> contacts;
    private String payingAgreement;
    private Agreement agreement;
    private ArrayList<String> manufacturers;

    private final int ROUTINE  = 1;
    private final int BY_ORDER  = 2;
    private final int NOT_TRANSPORTING  = 3;



    public Supplier(int id, String name, int bankNumber, String address,String payingAgreement, ArrayList<Contact> contacts, ArrayList<String> manufacturers){
        this.id = id;
        this.name = name;
        this.bankNumber = bankNumber;
        this.address = address;
        this.payingAgreement = payingAgreement;
        this.contacts = contacts;
        this.manufacturers = manufacturers;

        //this.agreement = new Agreement();
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPayingAgreement() {
        return payingAgreement;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public boolean removeContact(Contact contact) {
        return contacts.remove(contact);
    }

    public void addAgreement(int agreementType, String agreementDays) throws Exception {
        //"[0-9]+"  checks if all the agreement days chars are digits
        if(agreementType > 2 || agreementType < 0)
            throw new Exception("Invalid agreement type!");
        if( !agreementDays.matches("[0-9]+"))
            throw new Exception("Invalid agreement days!");
        createAgreement(agreementType, agreementDays);
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateBankNumber(int bankNumber) {
        this.bankNumber = bankNumber;
    }

    public void updateId(int newId) {
        this.id = newId;
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void changePayingAgreement(String payingAgreement) {
        this.payingAgreement = payingAgreement;
        //If we add this payingAgreement to the agreement,  need to update there too
    }

    public void addManufacturer(String manufacturer) {
        manufacturers.add(manufacturer);
    }

    public String getName() {
        return name;
    }

    public List<String> getOrderedItems() {
        return agreement.getItemsInMapFormat();
    }

    public void updateBulkPriceForItem(int itemID, Map<Integer, Integer> newBulkPrices) throws Exception {
        agreement.getItem(itemID).setBulkPrices(newBulkPrices);
    }

    public void updatePricePerUnitForItem(int itemID, float newPrice) throws Exception {
        agreement.getItem(itemID).setPrice(newPrice);
    }

    public void addItem(int itemId, String itemName, String itemManu, float itemPrice, Map<Integer, Integer> bulkPrices) throws Exception {
        if(agreement.itemExists(itemId))
            throw new Exception("item with this ID already exists!");
        agreement.addItem(new AgreementItem(itemId, itemName, itemManu, itemPrice, bulkPrices));
    }

    public void deleteItem(int itemId) throws Exception {
        agreement.removeItem(itemId);
    }

    public boolean isTransporting() {
        return agreement.isTransporting();
    }

    /*
    public void updateItemId(int oldItemId, int newItemId) throws Exception {
        agreement.getItem(oldItemId).setId(newItemId);
    }*/

    public void updateItemId(int oldItemId, int newItemId) throws Exception {
        agreement.setItemId(oldItemId, newItemId);
    }


    public void updateItemName(int itemId, String newName) throws Exception {
        agreement.getItem(itemId).setName(newName);
    }

    public void updateItemManufacturer(int itemId, String manufacturer) throws Exception {
        agreement.getItem(itemId).setManufacturer(manufacturer);
    }

    public void addAgreementItems(List<String> itemsString) throws Exception {
        agreement.setItemsFromString(itemsString);
    }

    public void updateAgreementType( int agreementType, String agreementDays) throws Exception {
        List<AgreementItem> items = agreement.getItems();
        createAgreement(agreementType, agreementDays);
        agreement.setItems(items);
    }

    private void createAgreement(int agreementType, String agreementDays){
        switch(agreementType){
            case ROUTINE -> agreement = new RoutineAgreement(agreementDays);
            case BY_ORDER -> agreement = new ByOrderAgreement(Integer.parseInt(agreementDays));
            case NOT_TRANSPORTING -> agreement = new NotTransportingAgreement();
        }
    }

    public String getSupplyingDays() {
        // TODO: 16/04/2022 SAGI  , how do you want to return the delivery items?
        return "temp";
    }

    // < id , name , bankAccount , address , payingAgreement , Contact1Name , Contact1Phone ,  Contact2Name , Contact2Phone ... >
    public ArrayList<String> getSupplierInfo() {
        ArrayList<String> result = new ArrayList<>();

        result.add(String.valueOf(getId()));
        result.add(getName());
        result.add(String.valueOf(getBankNumber()));
        result.add(getAddress());
        result.add(getPayingAgreement());
        for(Contact contact : contacts){
            result.add(contact.getName());
            result.add(contact.getPhone());
        }
        return result;
    }
}