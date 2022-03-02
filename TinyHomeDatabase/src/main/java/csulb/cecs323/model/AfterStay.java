package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Models a class: AfterStay Tracks persons after stay. Has a One to Many
 * relationship to Person. Resources used: --- 1. JAVA Persistance API Basics
 * ------ https://www.youtube.com/watch?v=HsMBMym4lD4
 */

@Entity
public class AfterStay {
    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(referencedColumnName = "Applicant_ID")
    private Person person;

    @Id
    @Column(name = "date_Of_Departure", nullable = false)
    private LocalDate dateOfDeparture;

    @Column(name = "days_Of_Occupancy", nullable = false)
    private int daysOfOccupancy;

    @Column(name = "goal_Met", nullable = false)
    private boolean goalMet;

    @Column(name = "housing_Secured", nullable = false)
    private boolean housingSecured;

    // Constructors ======================

    /**
     * Default constructor
     */
    public AfterStay() { }

    /**
     * One of the constructors used to initialize all the data
     * @param personID ID of the person living there
     * @param dateOfDeparture The date the user left
     * @param daysOfOccupancy How many days the user occupied the tiny home
     * @param goalMet Whether the goal was met or not
     * @param housingSecured Whether the user was able to secure housing or not
     */
    public AfterStay(long personID, LocalDate dateOfDeparture, int daysOfOccupancy, boolean goalMet,
            boolean housingSecured) {
        // Call the default constructor
        this();

        this.person = new Person();
        this.person.setApplicantID(personID);

        this.dateOfDeparture = dateOfDeparture;
        this.daysOfOccupancy = daysOfOccupancy;
        this.goalMet = goalMet;
        this.housingSecured = housingSecured;
    }

    // End Constructors ==================

    /**
     * This method gets days of occupancy.
     * @return returns days of occupancy as an integer.
     */
    public int getDaysOfOccupancy() {
        return daysOfOccupancy;
    }

    /**
     * This method sets days of occupancy.
     * @param daysOfOccupancy takes in the number of days of occupancy as an integer.
     */
    public void setDaysOfOccupancy(int daysOfOccupancy) {
        this.daysOfOccupancy = daysOfOccupancy;
    }

    /**
     * This method gets date of departure.
     * @return returns date of departure as a Local Date
     */
    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    /**
     * This method sets date of departure.
     * @param dateOfDeparture takes in the LocalDate date of departure as a LocalDate.
     */
    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public boolean isGoalMet() {
        return goalMet;
    }

    /**
     * This method sets goal met.
     * @param goalMet takes in the boolean goal met.
     */
    public void setGoalMet(boolean goalMet) {
        this.goalMet = goalMet;
    }

    /**
     * This method gets whether housing was secured
     * @return whether the housing was secured or not
     */
    public boolean isHousingSecured() {
        return housingSecured;
    }

    /**
     * This method sets housing secured.
     * @param housingSecured takes in the boolean housing secured.
     */
    public void setHousingSecured(boolean housingSecured) {
        this.housingSecured = housingSecured;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "<AfterStay ApplicantID = %d Date Of Departure = %s Days of Occupancy %d Goal met = %b Housing secured = %b>",
                person.getApplicantID(), dateOfDeparture, daysOfOccupancy, goalMet, housingSecured);
    }
}
