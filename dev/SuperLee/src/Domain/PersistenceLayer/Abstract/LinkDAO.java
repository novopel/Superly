package Domain.PersistenceLayer.Abstract;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class LinkDAO<T> extends DataMapper{
    public LinkDAO(String tableName) {
        super(tableName);
    }

    public Set<T> get(String id) throws SQLException {
        Set<T> output = new HashSet<>();
        try(Connection connection = getConnection()){
            ResultSet resultSet = select(connection,id);
            while (resultSet.next())
                output.add(buildObject(resultSet));
        }
        return output;
    }

    public void replaceSet(String mainObjectId , Set<? extends T> newValues) throws SQLException {
        remove(mainObjectId);
        for(Object instance : newValues)
            insert(Arrays.asList(mainObjectId,instance));
    }

    public void add(String id, T instance) throws SQLException {
        insert(Arrays.asList(id,instance));
    }

    public void remove(String mainObjectId, T secondInstanceID) throws SQLException{
        remove(Arrays.asList(1,2),Arrays.asList(mainObjectId,secondInstanceID));
    }


    protected abstract T buildObject(ResultSet resultSet) throws SQLException;
}