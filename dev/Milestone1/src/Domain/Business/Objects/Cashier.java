package Domain.Business.Objects;

import Domain.DAL.Objects.DCashier;
import Domain.DAL.Objects.DEmployee;
import Domain.Service.ServiceEmployeeFactory;
import Globals.Enums.Certifications;
import Globals.Enums.JobTitles;

import java.time.LocalDate;
import java.util.Set;

/**
 * Business model of the Cashier
 */
public class Cashier extends Employee {

    public Cashier(String id, String name, String bankDetails, int salary, String employmentConditions, LocalDate startingDate, Set<Certifications> certifications) throws Exception {
        super(id, name, bankDetails, salary, employmentConditions, startingDate, certifications, new DCashier(id,name,bankDetails,salary,employmentConditions,startingDate));
    }

    public Cashier(DEmployee dEmployee) {
        super(dEmployee);
    }

    @Override
    protected void updateEmploymentConditions() {
       updateEmploymentConditions(JobTitles.Cashier);
    }

    @Override
    public Domain.Service.Objects.Employee accept(ServiceEmployeeFactory factory) {
        return factory.createServiceEmployee(this);
    }
}
