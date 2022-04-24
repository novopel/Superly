package Domain.Service.Objects;

import Presentation.Screens.ScreenEmployeeFactory;

import java.util.Collections;
import java.util.Set;

/**
 * Service model of the Carrier
 */
public class Carrier extends Employee {
    public final Set<String> licenses;

    public Carrier(Domain.Business.Objects.Carrier bCarrier){
        super(bCarrier);
        licenses = Collections.unmodifiableSet(bCarrier.getLicenses());
    }

    @Override
    public Presentation.Screens.Employee accept(ScreenEmployeeFactory screenEmployeeFactory) {
        return screenEmployeeFactory.createScreenEmployee(this);
    }
}
