package Backend.BusinessLayer.Controllers;

import Backend.BusinessLayer.Objects.Driver;
import Backend.BusinessLayer.Objects.Truck;
import Globals.Enums.LicenseTypes;
import Globals.Enums.TruckModel;

import java.util.HashMap;

public class DriverController {
    private HashMap<LicenseTypes, HashMap<String, Driver>> drivers;
    private static DriverController instance = null;
    private DriverController() {
        drivers = new HashMap<>();
        //TODO: connect worker model
        drivers.put(LicenseTypes.B, new HashMap<>());
        drivers.get(LicenseTypes.B).put("Salala", new Driver("Salala", LicenseTypes.B));
    }

    public static DriverController getInstance(){
        if (instance == null) {
            instance = new DriverController();
        }
        return instance;
    }

    public Driver getDriver(String driver) throws Exception {
        for(LicenseTypes lt: drivers.keySet())
        {
            if(drivers.get(lt).containsKey(driver))
            {
                return drivers.get(lt).get(driver);
            }
        }
        throw new Exception("The driver doesn't exist!");
    }
}
