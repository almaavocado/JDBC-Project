package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ProgramEnrollment {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    @PrimaryKeyJoinColumn(referencedColumnName = "Applicant_ID")
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    @PrimaryKeyJoinColumn(referencedColumnName = "Program_Name")
    private RecoveryProgram program;

    @Id
    @Column(nullable = false)
    private LocalDate enrollmentDate;

    /**
     * Default constructor
     */
    public ProgramEnrollment() { }

    /**
     * Constructor to initialize the data
     * @param personID id of the person
     * @param program the program
     * @param enrollmentDate the date of enrollment
     */
    public ProgramEnrollment(long personID, String program, LocalDate enrollmentDate){
        this();

        this.person = new Person();
        this.person.setApplicantID(personID);

        this.program = new RecoveryProgram();
        this.program.setProgramName(program);

        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Getter method
     * @return the person
     */
    public Person getPerson() {
        return this.person;
    }

    /**
     * Setter method
     * @param person the person reference to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Getter method
     * @return the recovery program reference
     */
    public RecoveryProgram getProgram() {
        return this.program;
    }

    /**
     * Setter method
     * @param program the RecoveryProgram reference
     */
    public void setProgram(RecoveryProgram program) {
        this.program = program;
    }

    /**
     * Getter method
     * @return the date of enrollment
     */
    public LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }

    /**
     * Setter method
     * @param enrollmentDate the enrollment date
     */
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "< Applicant ID = %d, Program = %s, Enrollment Date = %s>",
                person.getApplicantID(), program.getProgramName(), enrollmentDate);
    }
}
