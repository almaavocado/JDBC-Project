package csulb.cecs323.model;

import javax.persistence.*;

/**
 * TinyHome class to model a tiny home.
 * Has a 0 to One relationship to Person.
 * Has a 1 to 1 relationship to Community.
 *
 * Resources Used:
 *
 * ------ 8. Slides About JPA ------------
 * https://bbcsulb.desire2learn.com/d2l/le/content/600590/viewContent/7428987/View
 *
 * ------ 9. WikiBooks --------------
 * https://en.wikibooks.org/wiki/Java_Persistence
 */


@Entity
public class TinyHome {
    @Id
    @Column(name = "House_Num", nullable = false)
    private int houseNum;

    @Column(nullable = false)
    private boolean availablie;

    @OneToOne
    private Person resident;

    @ManyToOne
    @JoinColumn(nullable = false)

    private Community community;

    public TinyHome() { }

    public TinyHome(int houseNum, boolean available, Long ApplicantID, Long communityID){
        this();

        this.houseNum = houseNum;
        this.availablie = available;

        this.resident = new Person();
        this.resident.setApplicantID(ApplicantID);

        this.community = new Community();
        this.community.setCommunityID(communityID);
    }

    /**
     * Getter method
     * @return community
     */
    public Community getCommunity() {
        return this.community;
    }

    /**
     * Setter method
     * @param community sets the community parameter
     */
    public void setCommunity(Community community) {
        this.community = community;
    }

    /**
     * Getter method
     * @return the house number
     */
    public int getHouseNum() {
        return this.houseNum;
    }

    /**
     * Setter method
     * @param houseNum sets the house number
     */
    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    /**
     * Getter method
     * @return returns whether the tiny home is available or not
     */
    public boolean isAvailablie() {
        return this.availablie;
    }

    /**
     * Setter method
     * @param availablie sets whether the tiny home is available or not
     */
    public void setAvailablie(boolean availablie) {
        this.availablie = availablie;
    }

    /**
     * Getter method
     * @return a reference to the Person object living in the Tiny Home
     */
    public Person getResident() {
        return this.resident;
    }

    /**
     * Setter method
     * @param resident sets the resident of who will be living at the tiny home
     */
    public void setResident(Person resident) {
        this.resident = resident;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "<HouseNum = %d, Available = %s, Applicant ID = %d, Location ID = %d >",
                houseNum, availablie, (resident == null) ? null : resident.getApplicantID(), community.getCommunityID());
    }
}
