package Domain.PersistenceLayer.Controllers;

import Domain.PersistenceLayer.Abstract.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationToShelfDAO extends DAO {
    private final static int ID_COLUMN = 1;
    private final static int SHELF_COLUMN = 2;
    public LocationToShelfDAO() {
        super("LocationToShelf");
    }

    public List<Integer> getShelves(int id) {
        try {
            ResultSet rs = select(getConnection(), Arrays.asList(ID_COLUMN), Arrays.asList(id));
            List<Integer> shelves = new ArrayList<>();
            while (rs.next()) {
                shelves.add(rs.getInt(SHELF_COLUMN));
            }
            return shelves;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
