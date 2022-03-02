package csulb.cecs323.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;


/**
 * Models a Funder that will help fund a TinyHome Community or many.
 * This is a one-to-many relationship to FunderCommunity.
 *
 * Resources Used:
 * ----- Getting Started & Understanding Annotations ------
 * https://dzone.com/refcardz/getting-started-with-jpa?chapter=2
 */

@Entity
public class Funder {
    @Id
    @Column(name = "Phone_Number", nullable = false, length = 11)
    private String phoneNumber;

    @Column(name = "Funder_Name", nullable = false, length = 30)
    private String funderName;

    @Column(nullable = false, length = 30)
    private String affiliation;

    @Column(name = "Street_Address", nullable = false, length = 100)
    private String streetAddress;

    @Column(name = "Zip_Code", nullable = false, length = 12)
    private String zipCode;

    @Column(name = "Funder_State", nullable = false, length = 20)
    private String funderState;

    @OneToMany(mappedBy = "funder")
    @JoinColumn(nullable = false)
    private Set<Contribution> contributions;

    /**
     * Default constructor
     */
    public Funder() {
        this.contributions = new HashSet<>();
    }

    /**
     * Constructor to initialize the data
     * @param phoneNumber the phone number of the funder
     * @param funderName the name
     * @param affiliation funder's affiliation
     * @param streetAddress the street address of the funder
     * @param zipCode the zip code
     * @param funderState the state the funder lives in
     */
    public Funder(String phoneNumber, String funderName, String affiliation, String streetAddress, String zipCode, String funderState) {
        // Call the default constructor
        this();

        this.phoneNumber = phoneNumber;
        this.funderName = funderName;
        this.affiliation = affiliation;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.funderState = funderState;
    }

    /**
     * Setter method
     * @param phoneNumber phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter method
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter method
     * @return funder name
     */
    public String getFunderName() {
        return funderName;
    }

    /**
     * Setter method
     * @param funderName the name of the funder
     */
    public void setFunderName(String funderName) {
        this.funderName = funderName;
    }

    /**
     * Getter method
     * @return the affliation
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * Setter method
     * @param affiliation the affiliation of the funder
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * Getter method
     * @return street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Setter method
     * @param streetAddress the street address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Getter method
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Setter method
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Getter method
     * @return funders state
     */
    public String getFunderState() {
        return funderState;
    }

    /**
     * Setter method
     * @param funderState the state of the funder
     */
    public void setFunderState(String funderState) {
        this.funderState = funderState;
    }

    /**
     * Getter method
     * @return references of contributions
     */
    public Set<Contribution> getContributions() {
        return this.contributions;
    }

    /**
     * Setter method
     * @param contributions the contributions references
     */
    public void setContributions(Set<Contribution> contributions) {
        this.contributions = contributions;
    }

    /**
     * Adds a Contribution reference
     * @param contribution the Contribution reference
     * @return whether it was added or not
     */
    public boolean addContribution(Contribution contribution) {
        return contributions.add(contribution);
    }

    /**
     * Removes a Contribution reference
     * @param contribution the Contribution reference
     * @return whether it was removed or not
     */
    public boolean removeContribution(Contribution contribution) {
        return contributions.remove(contribution);
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "<Phone Number = %s Funder Name = %s Affiliation= %s Street Address = %s Zip Code = %s State = %s>",
                phoneNumber, funderName, affiliation, streetAddress, zipCode, funderState);
    }
}
