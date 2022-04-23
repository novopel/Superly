package Domain.DAL.Abstract;

public abstract class DTO {

    // properties
    private int id;
    private boolean persist; //true if and only if this object is saved in the DB
    private String tableName; // this field will be valid if we will save data in tables

    // constructors


    public DTO(int id, String tableName) {
        this.id = id;
        this.persist = false;
        this.tableName = tableName;
    }


    public void setPersist(boolean persist) {
        this.persist = persist;
    }

    public int getId() {
        return id;
    }

    public boolean isPersist() {
        return persist;
    }

    public String getTableName() {
        return tableName;
    }

    public abstract void save(); // need to set persistent to true
    public void delete(){
        //delete this object from DB code
    }



    protected void update(String columnName,Object newValue){
        if (persist) {
            //update values
        }
    }
}