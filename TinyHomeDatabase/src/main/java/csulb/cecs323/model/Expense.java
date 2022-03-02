package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Models Expenses.
 * This is a one-to-one relationship to Community.
 *
 * Resources Used:
 * The following link was referenced to make the enumerated type.
 * https://tomee.apache.org/examples-trunk/jpa-enumerated/
 */

@Entity
public class Expense {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    // Foreign key
    @PrimaryKeyJoinColumn(referencedColumnName = "Community_ID")
    private Community community;

    @Id
    @Column(nullable = false)
    private int month;

    @Id
    @Column(name = "Billed_Year", nullable = false)
    private int billedYear;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private BillType typeOfBill;

    private double amount;

    // Constructors ======================

    /**
     * Default constructor
     */
    public Expense() { }

    /**
     * Constructor to initialize the data
     * @param communityID the ID of the community
     * @param month the month
     * @param billedYear the year of the bill
     * @param billType the type of bill
     * @param amount amount of the bill
     */
    public Expense(long communityID, int month, int billedYear, String billType, double amount) {
        this.community = new Community();
        this.community.setCommunityID(communityID);

        this.month = month;
        this.billedYear = billedYear;

        this.typeOfBill = new BillType();
        typeOfBill.setBill(billType);

        this.amount = amount;
    }
    // End Constructors ==================

    // Getters & Setters =================

    /**
     * Getter method
     * @return
     */
    public Community getCommunity() {
        return community;
    }

    /**
     * Getter method
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Setter method
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Getter method
     * @return billed year
     */
    public int getBilledYear() {
        return billedYear;
    }

    /**
     * Setter method
     * @param billedYear year that was billed
     */
    public void setBilledYear(int billedYear) {
        this.billedYear = billedYear;
    }

    /**
     * Setter method
     * @param amount amount of money
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Getter method
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter method
     * @param typeOfBill bill type
     */
    public void setBillType(BillType typeOfBill) {
        this.typeOfBill = typeOfBill;
    }

    /**
     * Getter method
     * @return bill type
     */
    public BillType getBillType() {
        return typeOfBill;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format(
                "<Community ID = %d Month = %d Year Billed = %d Type of Bill = %s Amount = %.2f>",
               community.getCommunityID(), month, billedYear, (typeOfBill == null) ? null : typeOfBill.getBill(), amount);
    }
}
