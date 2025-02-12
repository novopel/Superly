package Domain.Service.Objects;

import Globals.Enums.JobTitles;
import Presentation.Screens.ScreenEmployeeFactory;

/**
 * Service model of the HR manager
 */
public class HR_Manager extends Employee{
    public HR_Manager(Domain.Business.Objects.Employee.HR_Manager bHR_Manager){
        super(bHR_Manager);
    }

    @Override
    public Presentation.Screens.Employee accept(ScreenEmployeeFactory screenEmployeeFactory) {
        return screenEmployeeFactory.createScreenEmployee(this);
    }

    @Override
    public JobTitles getType() {
        return JobTitles.HR_Manager;
    }
}
