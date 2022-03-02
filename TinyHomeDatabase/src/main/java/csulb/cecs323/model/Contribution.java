package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Models a FunderCommunity for a Funder.
 * This is a many-to-one relationship to Funder.
 *
 * Resources Used:
 * ----- Getting Started & Understanding Annotations ------
 * https://dzone.com/refcardz/getting-started-with-jpa?chapter=2
 */

@Entity
public class Contribution {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    // Imported foreign Key
    @PrimaryKeyJoinColumn(referencedColumnName = "Phone_Number")
    private Funder funder;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    // Imported foreign Key
    @PrimaryKeyJoinColumn(referencedColumnName = "Community_ID")
    private Community community;

    @Id
    @Column(name = "Date_Funded", nullable = false)
    private LocalDate dateFunded;

    @Column(name = "Amount_Funded", nullable = false)
    private double amountFunded;

    /**
     * Default constructor
     */
    public Contribution() { }

    /**
     * Constructor to intialize all the variables
     * @param phoneNumber the phone number
     * @param communityID community ID the contribution belongs to
     * @param dateFunded the date it was funded
     * @param amountFunded the amount of money funded
     */
    public Contribution(String phoneNumber, long communityID, LocalDate dateFunded, double amountFunded) {
        this.funder = new Funder();
        this.funder.setPhoneNumber(phoneNumber);

        this.community = new Community();
        this.community.setCommunityID(communityID);

        this.dateFunded = dateFunded;
        this.amountFunded = amountFunded;
    }

    /**
     * Getter method
     * @return gets the Funder reference
     */
    public Funder getFunder() {
        return funder;
    }

    /**
     * Getter method
     * @return gets the Community reference
     */
    public Community getCommunity() {
        return community;
    }

    /**
     * Getter method
     * @return the amount funded
     */
    public double getAmountFunded() {
        return amountFunded;
    }

    /**
     * Setter method
     * @param amountFunded the amount of money funded
     */
    public void setAmountFunded(double amountFunded) {
        this.amountFunded = amountFunded;
    }

    /**
     * Getter method
     * @return the data it was funded
     */
    public LocalDate getDateFunded() {
        return dateFunded;
    }

    /**
     * Setter method
     * @param dateFunded the date it was funded
     */
    public void setDateFunded(LocalDate dateFunded) {
        this.dateFunded = dateFunded;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "<Phone Number = %s Community ID = %d  Date Funded = %s Amount Funded = %.2f>",
                funder.getPhoneNumber(),community.getCommunityID(), dateFunded, amountFunded);
    }
}
