package Domain.Business.Objects;

import Domain.Business.Objects.Enums.EmployeeJob;
import Domain.DAL.Objects.DEmployee;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Employee {

    // properties
    protected int id;
    protected String name;
    protected String bankDetails;
    protected int salary;
    protected String employmentConditions;
    protected Date startingDate;
    protected Set<Constraint> constraint;
    protected DEmployee dEmployee; // represent of this object in the DAL

    public Employee(int id, String name, String bankDetails, int salary, String employmentConditions, Date startingDate) {
        this.id = id;
        this.name = name;
        this.bankDetails = bankDetails;
        this.salary = salary;
        this.employmentConditions = employmentConditions;
        this.startingDate = startingDate;
        this.dEmployee = new DEmployee(id,name,bankDetails,salary,employmentConditions,startingDate,getJobTitle().name());
        constraint = new HashSet<>();
    }

    public Employee(DEmployee dEmployee) {
        this.id = dEmployee.id;
        this.name = dEmployee.name;
        this.bankDetails = dEmployee.bankDetails;
        this.salary = dEmployee.salary;
        this.employmentConditions = dEmployee.employmentConditions;
        this.startingDate = dEmployee.startingDate;
        this.dEmployee = dEmployee;
    }

    public DEmployee getDEmployee() {
        return dEmployee;
    }

    public Integer getId() {
        return id;
    }

    public void addConstraint(Date date, String type){
        constraint.add(new Constraint(date, type));
    }

    public abstract EmployeeJob getJobTitle();
}
