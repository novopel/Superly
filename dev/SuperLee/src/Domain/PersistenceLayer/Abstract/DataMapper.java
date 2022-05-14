package Domain.PersistenceLayer.Abstract;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class DataMapper<T> extends DAO {

    public DataMapper(String tableName) {
        super(tableName);
    }

    public T get(String id) throws SQLException {
        Map<String,T> map = getMap();
        T output = map.get(id);
        if (output != null)
            return output;

        try(Connection connection = getConnection()) {
            ResultSet instanceResult = select(connection,id);
            if (!instanceResult.next())
                return null;


            output = buildObject(instanceResult);
            map.put(id,output);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("this is bad");
    }

    /**
     *
     * @param id the id of instance you want to update
     * @param propertyColumnNumber the columnNumber you want to update
     * @param toUpdate the value to update
     * @param <K> whatever
     * @throws RuntimeException
     * @throws SQLException
     */
    protected <K> void updateProperty (String id, int propertyColumnNumber,K toUpdate) throws RuntimeException, SQLException {
       /* T instance = get(id); // throw exception if not found
        getUpdateFunction(propertyColumnNumber).update(instance,toUpdate); */
        super.update(Arrays.asList(propertyColumnNumber),Arrays.asList(toUpdate),Arrays.asList(1),Arrays.asList(id));
    }
    // protected abstract <K>UpdateFunction<T,K> getUpdateFunction(int propertyColumnNumber);

    protected  <K> void addToSet(String id, String listName, K toAdd) throws SQLException{
        getLinkDTO(listName).add(id,toAdd);
    }

    protected  <K> void removeFromSet(String id, String listName, K toRemove) throws SQLException{
        getLinkDTO(listName).remove(id,toRemove);
    }

    protected <K> void replaceSet(String id, String listName, Set<K> toReplace) throws SQLException{
        getLinkDTO(listName).replaceSet(id,toReplace);
    }

    public void save(String id, T instance) throws SQLException{
        insert(instance);
        getMap().put(id,instance);
    }

    protected abstract Map<String, T> getMap();
    protected abstract  LinkDAO getLinkDTO(String setName);
    protected abstract T buildObject(ResultSet instanceResult) throws Exception;
    public abstract void insert(T instance) throws SQLException;
}
