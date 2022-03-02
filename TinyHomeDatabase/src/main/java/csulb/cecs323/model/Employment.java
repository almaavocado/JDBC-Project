package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Models an Employment for a Person. This is a many-to-one relationship to Person.
 *
 * Resources Used:
 * ----   Generating Keys ------
 * https://thorben-janssen.com/jpa-generate-primary-keys/
 * ---  Overloading Constructors -----
 * https://stackoverflow.com/questions/27305950/jpa-foreign-key-that-is-also-a-primary-key-mapping
 * --- JAVA Persistance API Explained Introduction ------
 * https://www.youtube.com/watch?v=LEgkdCBnjcM
 *
 */

@Entity
public class Employment {
    @Id
    @OneToOne
    @JoinColumn(nullable = false)
    @PrimaryKeyJoinColumn(referencedColumnName = "Applicant_ID")
    // Imported foreign Key
    private Person person;

    @Id
    @Column(name = "type_Of_Work", nullable = false, length = 100)
    private String typeOfWork;

    @Column(name = "hourly_Wage", nullable = false)
    private double hourlyWage;

    @Column(name = "start_Date", nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private String company;

    /**
     * Default constructor
     */
    public Employment() { }

    /**
     * Constructor to initialize all the data
     * @param personID the id of the Person
     * @param typeOfWork the type of work
     * @param hourlyWage amount of money per hour
     * @param startDate the start date
     * @param company the company of employment
     */
    public Employment(long personID, String typeOfWork, double hourlyWage, LocalDate startDate, String company) {
        this.person = new Person();
        this.person.setApplicantID(personID);

        this.typeOfWork = typeOfWork;
        this.hourlyWage = hourlyWage;
        this.startDate = startDate;
        this.company = company;
    }

    /**
     * Getter method
     * @return reference to the Person
     */
    public Person getPerson() {
        return this.person;
    }

    /**
     * Getter method
     * @return the type of work
     */
    public String getTypeOfWork() {
        return typeOfWork;
    }

    /**
     * Setter methods
     * @param typeOfWork the type of work to set
     */
    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    /**
     * Getter method
     * @return the hourly wage
     */
    public double getHourlyWage() {
        return hourlyWage;
    }

    /**
     * Setter method
     * @param hourlyWage the hourly wage to set
     */
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    /**
     * Getter method
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Setter method
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter method
     * @return gets the company
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * Setter method
     * @param company sets the company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Setter method
     * @param person the Person reference
     */
	public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "<Applicant ID = %d Type of Work = %s Hourly Wage = %.2f Start Date = %s Company = %s>",
                person.getApplicantID(), typeOfWork, hourlyWage, startDate, company);
    }
}
