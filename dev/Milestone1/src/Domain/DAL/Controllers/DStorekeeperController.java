package Domain.DAL.Controllers;

import Domain.DAL.Objects.DStorekeeper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class DStorekeeperController extends DTOControllers<DStorekeeper> {
    public DStorekeeperController() {
        super("tableName");
    }

    @Override
    public Set<DStorekeeper> loadData() {
        Set<DStorekeeper> output = new HashSet<>();
        for (int i=50; i<60; i++) {
            try {
                output.add(new DStorekeeper(""+i,"Storekeeper " + i,"bd",i,"con" +i,  new SimpleDateFormat("dd-MM-yyyy").parse("15-06-1198")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(DStorekeeper toDelete) {

    }
}
