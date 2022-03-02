package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class BillType {
    @Id
    @Column(nullable = false, length = 20)
    private String bill;

    /**
     * Default constructor
     */
    public BillType() { }

    /**
     * Constructor used to initialize the data
     * @param bill the type of bill this is
     */
    public BillType(String bill) {
        // Call the default constructor
        this();

        this.bill = bill;
    }

    /**
     * Getter method for the bill type
     * @return bill type to set
     */
    public String getBill() {
        return this.bill;
    }

    /**
     * Setter method for the bill type
     * @param bill
     */
    public void setBill(String bill) {
        this.bill = bill;
    }

    /**
     * Overrides the toString method to define how this class should be represented
     * @return string representation of the class data
     */
    @Override
    public String toString() {
        return String.format("<BillType %s>", bill);
    }
}
