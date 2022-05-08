package Domain.Business.Objects;

import Domain.DAL.Objects.DEmployee;
import Domain.DAL.Objects.DSorter;
import Domain.Service.ServiceEmployeeFactory;
import Globals.Enums.Certifications;
import Globals.Enums.JobTitles;

import java.time.LocalDate;
import java.util.Set;

/**
 * Business model of the Sorter
 */
public class Sorter extends Employee{
    public Sorter(String id, String name, String bankDetails, int salary, String employmentConditions, LocalDate startingDate, Set<Certifications> certifications) throws Exception {
        super(id, name, bankDetails, salary, employmentConditions, startingDate, certifications,new DSorter(id,name,bankDetails,salary,employmentConditions,startingDate));
    }

    public Sorter(DEmployee dEmployee) {
        super(dEmployee);
    }

    @Override
    protected void updateEmploymentConditions() {
       super.updateEmploymentConditions(JobTitles.Sorter);
    }

    @Override
    public Domain.Service.Objects.Employee accept(ServiceEmployeeFactory factory) {
        return factory.createServiceEmployee(this);
    }
}
