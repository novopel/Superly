package Domain.Service.Objects.Employee;

import Globals.Enums.JobTitles;
import Presentation.Screens.ScreenEmployeeFactory;
import WebPresentation.Screens.Models.HR.EmployeeFactory;

/**
 * Service model of the logistics manager
 */
public class Logistics_Manager extends Employee{
    public Logistics_Manager(Domain.Business.Objects.Employee.Logistics_Manager bLogistics_Manager){
        super(bLogistics_Manager);
    }

    @Override
    public Presentation.Screens.Employee accept(ScreenEmployeeFactory screenEmployeeFactory) {
        return screenEmployeeFactory.createScreenEmployee(this);
    }

    @Override
    public JobTitles getType() {
        return JobTitles.Logistics_Manager;
    }

    @Override
    public WebPresentation.Screens.Models.HR.Employee accept(EmployeeFactory employeeFactory) {
        return employeeFactory.createEmployee(this);
    }
}
