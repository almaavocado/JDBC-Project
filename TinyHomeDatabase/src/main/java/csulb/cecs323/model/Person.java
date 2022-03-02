package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * Person class to model basic information about a homeless person.
 *
 * This is for practice. (First Java class we created)
 *
 * Resources Used:
 * --- 1. Dr. Monge's Lectures & Student Class -------
 *
 * --- 2. JAVA Persistance API Explained Persistance Operation ------
 * https://www.youtube.com/watch?v=70cPmHd-D9c&t=402s
 *
 * --- 3. JAVA Persistance API Explained Introduction ------
 * https://www.youtube.com/watch?v=LEgkdCBnjcM
 *
 * --- 4. JAVA Persistance API Basics ------
 * https://www.youtube.com/watch?v=HsMBMym4lD4
 */

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames={"first_name, last_name, dob"})
)
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Applicant_ID")
    private Long applicantID;
    
    //added annotations
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;

    private String gender;

    @Column(name = "Days_W_O_Permanent_Housing")
    private int daysWOPermanentHousing;

    @Column(name = "Current_Housing_Situation")
    private String currentHousingSituation;

    @Column(name = "Housing_Offered")
    private boolean housingOffered;

    @OneToOne(mappedBy = "resident")
    private TinyHome tinyHome;

    @OneToMany(mappedBy = "person")
    private Set<Employment> employments;

    @OneToMany(mappedBy = "person")
    private Set<ProgramEnrollment> enrolledPrograms;

    @OneToMany(mappedBy = "person")
    private Set<AfterStay> afterStays;

    /**
     * Default constructor
     */
    public Person() {
        employments = new HashSet<Employment>();
        enrolledPrograms = new HashSet<ProgramEnrollment>();
        afterStays = new HashSet<AfterStay>();
    }

    /**
     * Contstructor to initialize data
     * @param applicantID the applicant's id
     * @param firstName the first name
     * @param lastName the last name
     * @param dob date of birth
     * @param gender the gender of the person
     * @param daysWOPermenantHousing days without permenant housing
     * @param currentHousingSituation the current housing situation
     * @param housingOffered whether housing was offered
     * @param houseNum the house number
     */
    public Person(Long applicantID, String firstName, String lastName, LocalDate dob, String gender, int daysWOPermenantHousing, String currentHousingSituation, boolean housingOffered, int houseNum) {
        // Call the default constructor
        this();

        this.applicantID = applicantID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.daysWOPermanentHousing = daysWOPermenantHousing;
        this.currentHousingSituation = currentHousingSituation;
        this.housingOffered = housingOffered;
    
        this.tinyHome = new TinyHome();
        this.tinyHome.setHouseNum(houseNum);
    }

    /**
     * Getter method
     * @return the applicant id
     */
    public Long getApplicantID() {
        return applicantID;
    }

    /**
     * Setter method
     * @param applicantID the applicant id to set
     */
    public void setApplicantID(Long applicantID) {
        this.applicantID = applicantID;
    }

    /**
     * Getter method
     * @return returns the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter method
     * @return gets the date of birth for the person
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Setter method
     * @param dob the date of birth for the person
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Setter method
     * @param lastName the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method
     * @return the TinyHome reference
     */
    public TinyHome getTinyHome() {
        return this.tinyHome;
    }

    /**
     * Setter method
     * @param tinyHome the TinyHome reference to the Person
     */
    public void setTinyHome(TinyHome tinyHome) {
        this.tinyHome = tinyHome;
    }

    /**
     * Getter method
     * @return references to the Employments
     */
    public Set<Employment> getEmployments() {
        return this.employments;
    }

    /**
     * Setter method
     * @param employments references to the Employments
     */
    public void setEmployments(Set<Employment> employments) {
        this.employments = employments;
    }

    /**
     * Getter method
     * @return gets references to all ProgramEnrollments
     */
    public Set<ProgramEnrollment> getEnrolledPrograms() {
        return this.enrolledPrograms;
    }

    /**
     * Setter method
     * @param enrolledPrograms sets the references to ProgramEnrollments
     */
    public void setEnrolledPrograms(Set<ProgramEnrollment> enrolledPrograms) {
        this.enrolledPrograms = enrolledPrograms;
    }

    /**
     * Getter method
     * @return references to the AfterStays
     */
    public Set<AfterStay> getAfterStays() {
        return this.afterStays;
    }

    /**
     * Setter method
     * @param afterStays the AfterStays references to set
     */
    public void setAfterStays(Set<AfterStay> afterStays) {
        this.afterStays = afterStays;
    }

    /**
     * Adds an Employment reference
     * @param employment the Employment to add
     * @return whether the Employment was added or not
     */
    public boolean addEmployment(Employment employment) {
        return employments.add(employment);
    }

    /**
     * Removes an Employment reference
     * @param employment the Employment to remove
     * @return whether the Employment was removed or not
     */
    public boolean removeEmployment(Employment employment) {
        return employments.remove(employment);
    }

    /**
     * Adds an ProgramEnrollment reference
     * @param enrolledProgram the ProgramEnrollment to add
     * @return whether the ProgramEnrollment was added or not
     */
    public boolean addEnrolledProgram(ProgramEnrollment enrolledProgram) {
        return enrolledPrograms.add(enrolledProgram);
    }

    /**
     * Removes an ProgramEnrollment reference
     * @param enrolledProgram the ProgramEnrollment to remove
     * @return whether the ProgramEnrollment was removed or not
     */
    public boolean removeEnrolledProgram(ProgramEnrollment enrolledProgram) {
        return enrolledPrograms.remove(enrolledProgram);
    }

    /**
     * Adds an AfterStay reference
     * @param afterStay the AfterStay to add
     * @return whether the AfterStay was added or not
     */
    public boolean addAfterStay(AfterStay afterStay) {
        return afterStays.add(afterStay);
    }

    /**
     * Removes an AfterStay reference
     * @param afterStay the AfterStay to remove
     * @return whether the AfterStay was removed or not
     */
    public boolean removeAfterStay(AfterStay afterStay) {
        return afterStays.remove(afterStay);
    }

    /**
     * Getter method
     * @return the gender of the person
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Setter method
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter method
     * @return days without permanent housing
     */
    public int getDaysWOPermanentHousing() {
        return this.daysWOPermanentHousing;
    }

    /**
     * Setter method
     * @param daysWOPermanentHousing days without permanent housing
     */
    public void setDaysWOPermanentHousing(int daysWOPermanentHousing) {
        this.daysWOPermanentHousing = daysWOPermanentHousing;
    }

    /**
     * Getter method
     * @return the current housing situation
     */
    public String getCurrentHousingSituation() {
        return this.currentHousingSituation;
    }

    /**
     * Setter method
     * @param currentHousingSituation the current housing situation
     */
    public void setCurrentHousingSituation(String currentHousingSituation) {
        this.currentHousingSituation = currentHousingSituation;
    }

    /**
     * Checks the housing offered boolean
     * @return whether housing is offered
     */
    public boolean isHousingOffered() {
        return this.housingOffered;
    }

    /**
     * Setter method
     * @param housingOffered whether housing was offered
     */
    public void setHousingOffered(boolean housingOffered) {
        this.housingOffered = housingOffered;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "< ApplicantID = %d FirstName = %s lastName= %s, Date of Birth= %s, Gender= %s, Days Without Permenant Housing = %d, Current Housing Situation= %s, Housing Offered= %b>",
                applicantID, firstName, lastName, dob, gender, daysWOPermanentHousing, currentHousingSituation, housingOffered);
    }
}
