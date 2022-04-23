package Domain.Business.Objects;

import Domain.DAL.Objects.DEmployee;
import Domain.Service.ServiceEmployeeFactory;
import Globals.Enums.Certifications;
import Globals.Enums.JobTitles;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Business model of the Carrier
 */
public class Carrier extends Employee {
    private Set<String> licenses;

    public Carrier(int id, String name, String bankDetails, int salary, String employmentConditions, Date startingDate, Set<Certifications> certifications, Set<String> licenses) {
        super(id, name, bankDetails, salary, employmentConditions, startingDate, certifications);
        this.licenses = licenses;
    }

    public Carrier(DEmployee dEmployee) {
        super(dEmployee);
    }

    @Override
    public JobTitles getJobTitle() {
        return JobTitles.Carrier;
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