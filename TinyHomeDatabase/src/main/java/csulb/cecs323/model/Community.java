package csulb.cecs323.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

/**
 * Models a Community.
 *
 * Has a One to One relationship to LocalCapacity.
 * Has a One to Many relationship to Expense.
 * Has a One to Many relationship to FunderCommunity.
 *
 * Resources Used:
 * ----- Getting Started & Understanding Annotations ------
 * https://dzone.com/refcardz/getting-started-with-jpa?chapter=2
 */

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames={"Community_State, city, Street_Address"})
)
public class Community {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Community_ID")
    private long communityID;

    @Column(name = "Community_State", nullable = false, length = 20)
    private String communityState;

    @Column(nullable = false, length = 40)
    private String city;

    @Column(name = "Street_Address", nullable = false, length = 40)
    private String streetAddress;

    @Column(name = "Service_Years", nullable = false)
    private int serviceYears;

    @Column(name = "Total_Capacity", nullable = false)
    private int totalCapacity;

    @Column(name = "Homes_Filled", nullable = false)
    private int homesFilled;

    @Column(name = "Vacant_Homes", nullable = false)
    private int vacantHomes;

    @Column(name = "Capacity_Met_Per_Year", nullable = false)
    private int capacityMetPerYear;

    @OneToMany(mappedBy = "community")
    @JoinColumn(nullable = false)
    private Set<TinyHome> homes;

    @OneToMany(mappedBy = "community")
    @JoinColumn(nullable = false)
    private Set<Contribution> contributions;

    @OneToMany(mappedBy = "community")
    private Set<Expense> expenses;

    /**
     * Default constructor
     */
    public Community() {
        homes = new HashSet<TinyHome>();
        contributions = new HashSet<Contribution>();
        expenses = new HashSet<Expense>();
    }

    /**
     * Constructor used to initialize all the data
     * @param communityID The id of the community
     * @param communityState the state the community is in
     * @param city the city the community is in
     * @param streetAddress the street address the community is in
     * @param serviceYears the amount of service years of the community
     * @param totalCapacity the total capacity of the community
     * @param homesFilled the number of homes filled in the community
     * @param vacantHomes the amount of vacant homes
     * @param capacityMetPerYear the amount of people met per year
     */
    public Community(long communityID, String communityState, String city, String streetAddress, int serviceYears, int totalCapacity, int homesFilled, int vacantHomes, int capacityMetPerYear) {
        // Call the default constructor
        this();

        this.communityID = communityID;
        this.communityState = communityState;
        this.city = city;
        this.streetAddress = streetAddress;
        this.serviceYears = serviceYears;
        this.totalCapacity = totalCapacity;
        this.homesFilled = homesFilled;
        this.vacantHomes = vacantHomes;
        this.capacityMetPerYear = capacityMetPerYear;
    }

    /**
     * Getter methods for all the expenses
     * @return a set of Expense references
     */
    public Set<Expense> getExpenses() {
        return this.expenses;
    }

    /**
     * Adds a new expense reference
     * @param expense the expense reference to add
     * @return whether the expense was added or not
     */
    public boolean addExpense(Expense expense) {
        return expenses.add(expense);
    }

    /**
     * Remove a new Expense reference
     * @param expense the Expense reference to remove
     * @return whether the Expense was removed or not
     */
    public boolean removeExpense(Expense expense) {
        return expenses.remove(expense);
    }

    /**
     * Adds a new TinyHome reference
     * @param tinyHome the TinyHome to reference
     * @return whether the TinyHome was added or not
     */
    public boolean addTinyHome(TinyHome tinyHome) {
        return homes.add(tinyHome);
    }

    /**
     * Removes a new TinyHome reference
     * @param tinyHome the TinyHome to reference
     * @return whether the TinyHome was removed or not
     */
    public boolean removeTinyHome(TinyHome tinyHome) {
        return homes.remove(tinyHome);
    }

    /**
     * Adds a contribution reference
     * @param contribution the Contribution to reference
     * @return
     */
    public boolean addContribution(Contribution contribution) {
        return contributions.add(contribution);
    }

    /**
     * Removes a Contribution reference
     * @param contribution the Contribution to remove
     * @return whether the Contribution was removed or not
     */
    public boolean removeContribution(Contribution contribution) {
        return contributions.remove(contribution);
    }

    /**
     * Getter method
     * @return the total capacity in the community
     */
    public int getTotalCapacity() {
        return this.totalCapacity;
    }

    /**
     * Setter method
     * @param totalCapacity the total capacity to set
     */
    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    /**
     * Getter method
     * @return the amount of homes filled
     */
    public int getHomesFilled() {
        return this.homesFilled;
    }

    /**
     * Setter method
     * @param homesFilled how many houses are filled
     */
    public void setHomesFilled(int homesFilled) {
        this.homesFilled = homesFilled;
    }

    /**
     * Getter method
     * @return gets the amount of vacant homes
     */
    public int getVacantHomes() {
        return this.vacantHomes;
    }

    /**
     * Setter method
     * @param vacantHomes the amount of homes that are vacant
     */
    public void setVacantHomes(int vacantHomes) {
        this.vacantHomes = vacantHomes;
    }

    /**
     * Getter method
     * @return how much of the capacity is met per year
     */
    public int getCapacityMetPerYear() {
        return this.capacityMetPerYear;
    }

    /**
     * Setter method
     * @param capacityMetPerYear how much of the capacity is met per year
     */
    public void setCapacityMetPerYear(int capacityMetPerYear) {
        this.capacityMetPerYear = capacityMetPerYear;
    }

    /**
     * Setter methods
     * @param expenses sets the reference to all the Expenses
     */
    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Getter method
     * @return gets the state of the community
     */
    public String getCommunityState() {
        return this.communityState;
    }

    /**
     * Setter method
     * @param communityState the state the community is in
     */
    public void setCommunityState(String communityState) {
        this.communityState = communityState;
    }

    /**
     * Getter method
     * @return the city of the community
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Setter method
     * @param city the city of the community
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter method
     * @return returns the street address of the community
     */
    public String getStreetAddress() {
        return this.streetAddress;
    }

    /**
     * Setter method
     * @param streetAddress the street address of the community
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Getter method
     * @return Gets the service years
     */
    public int getServiceYears() {
        return this.serviceYears;
    }

    /**
     * Setter method
     * @param serviceYears the amount of service years
     */
    public void setServiceYears(int serviceYears) {
        this.serviceYears = serviceYears;
    }

    /**
     * Getter method
     * @return Gets the references of the TinyHomes in the community
     */
    public Set<TinyHome> getHomes() {
        return this.homes;
    }

    /**
     * Setter method
     * @param homes the references of TinyHomes to the community
     */
    public void setHomes(Set<TinyHome> homes) {
        this.homes = homes;
    }

    /**
     * Getter method
     * @return gets the contributions of the community
     */
    public Set<Contribution> getContributions() {
        return this.contributions;
    }

    /**
     * Setter method
     * @param contributions references to the Contributions of the community
     */
    public void setContributions(Set<Contribution> contributions) {
        this.contributions = contributions;
    }

    /**
     * Gets the community id
     * @return the id of the communit
     */
    public long getCommunityID() {
        return this.communityID;
    }

    /**
     * Setter method
     * @param communityID the new community ID
     */
    public void setCommunityID(long communityID) {
        this.communityID = communityID;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "<Community ID = %d State = %s city = %s  Street Address = %s Service years = %d Total capacity = %d Homes filled = %d Vacant homes = %d Capacity met per year = %d>",
                communityID, communityState, city, streetAddress, serviceYears, totalCapacity, homesFilled, vacantHomes, capacityMetPerYear);
    }
}
