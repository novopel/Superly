package Domain.Business.Objects;

import Domain.DAL.Objects.DCarrier;
import Domain.Service.ServiceEmployeeFactory;
import Globals.Enums.Certifications;
import Globals.Enums.JobTitles;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Business model of the Carrier
 */
public class Carrier extends Employee {
    private Set<String> licenses;

    public Carrier(String id, String name, String bankDetails, int salary, String employmentConditions, LocalDate startingDate, Set<Certifications> certifications, Set<String> licenses) throws Exception {
        super(id, name, bankDetails, salary, employmentConditions, startingDate,certifications,new DCarrier(id,name,bankDetails,salary,employmentConditions,startingDate, licenses));
        this.licenses = licenses;
    }

    public Carrier(DCarrier dEmployee) {
        super(dEmployee);
        this.licenses = new HashSet<>(dEmployee.getLicenses());
    }

    protected void updateEmploymentConditions() {
        super.updateEmploymentConditions(JobTitles.Carrier);
    }

    @Override
    public Domain.Service.Objects.Employee accept(ServiceEmployeeFactory factory) {
        return factory.createServiceEmployee(this);
    }

    public Set<String> getLicenses() {
        return licenses;
    }

    public void setLicences(Set<String> licences) {
        this.licenses = new HashSet<>(licences);
    }
}
