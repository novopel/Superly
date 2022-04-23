package Domain.Service.Objects;

import Globals.Enums.Certifications;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * Abstract class representing employee for service purposes
 */
public abstract class Employee {
    public final int id;
    public final String name;
    public final String bankDetails;
    public final int salary;
    public final String employmentConditions;
    public final Date startingDate;
    public final Set<Certifications> certifications;

    /**
     * Service Employee basic constructor, private
     *
     * @param id ID of the employee
     * @param name Name of the employee
     * @param bankDetails Bank details of the employee
     * @param salary Salary of the employee
     * @param employmentConditions Employment conditions of the employee
     * @param startingDate Employee's work starting date
     * @param certifications Employee's certifications
     */
    private Employee(int id, String name, String bankDetails, int salary, String employmentConditions, Date startingDate, Set<Certifications> certifications){
        this.id = id;
        this.name = name;
        this.bankDetails = bankDetails;
        this.salary = salary;
        this.employmentConditions = employmentConditions;
        this.startingDate = startingDate;
        this.certifications = Collections.unmodifiableSet(certifications);
    }

    /**
     * Service Employee constructor based on his business type counterpart
     *
     * @param bEmployee Business type representing the employee
     */
    public Employee(Domain.Business.Objects.Employee bEmployee){
        this(bEmployee.getId(), bEmployee.getName(), bEmployee.getBankDetails(), bEmployee.getSalary(), bEmployee.getEmploymentConditions(), bEmployee.getStartingDate(), bEmployee.getCertifications());
    }
}