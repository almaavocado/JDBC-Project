package csulb.cecs323.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

/**
 * Recovery class to model the program assigned to a person.
 * Has a One to One relationship to Person.
 *
 * Resources Used:
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
public class RecoveryProgram {
    @Id
    @Column(name = "Program_Name", nullable = false, length = 100)
    private String programName;

    @OneToMany(mappedBy = "program", orphanRemoval=true)
    private Set<ProgramEnrollment> enrollment;

    /**
     * Default constructor
     */
    public RecoveryProgram() {
        enrollment = new HashSet<ProgramEnrollment>();
    }

    /**
     * Contructor to set the program name
     * @param programName the name of the program
     */
    public RecoveryProgram(String programName){
        this.programName = programName;
    }

    /**
     * Getter method
     * @return the program name
     */
    public String getProgramName() {
        return this.programName;
    }

    /**
     * Setter method
     * @param ProgramName the program name to set
     */
    public void setProgramName(String ProgramName) {
        this.programName = ProgramName;
    }

    /**
     * Getter method
     * @return references to the ProgramEnrollments
     */
    public Set<ProgramEnrollment> getEnrollment() {
        return this.enrollment;
    }

    /**
     * Setter method
     * @param enrollment the referneces of ProgramEnrollments to set
     */
    public void setEnrollment(Set<ProgramEnrollment> enrollment) {
        this.enrollment = enrollment;
    }

    /**
     * Adds an EnrolledProgram to the references
     * @param enrolledProgram the EnrolledProgram to add
     * @return whether the EnrolledProgram was added or not
     */
    public boolean addEnrolledProgram(ProgramEnrollment enrolledProgram) {
        return enrollment.add(enrolledProgram);
    }

    /**
     * Removes an EnrolledProgram to the references
     * @param enrolledProgram the EnrolledProgram to remove
     * @return whether the EnrolledProgram was removed or not
     */
    public boolean removeEnrolledProgram(ProgramEnrollment enrolledProgram) {
        return enrollment.remove(enrolledProgram);
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format("<Program Name = %s >", programName);
    }
}
