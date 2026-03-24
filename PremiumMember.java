/**
 * PremiumMember class represents a premium tier gym membership.
 * Extends GymMember class with additional premium-specific features and benefits.
 */
public class PremiumMember extends GymMember
{
    // Premium membership specific attributes
    private final double premiumCharge = 12500;   
    private String personalTrainer;            
    private boolean isFullPayment;               
    private double paidAmount;                   
    private double discountAmount;                
    
    /**
     * Constructor for creating a new premium member.
     * Initializes member with a personal trainer and default payment values.
     * @param id Unique identifier for the member
     * @param name Member's full name
     * @param location Member's address
     * @param phone Contact number
     * @param email Email address
     * @param gender Member's gender
     * @param DOB Date of Birth
     * @param membershipStartDate Start date of membership
     * @param personalTrainer Name of assigned personal trainer
     */

    public PremiumMember(int id, String name, String location, String phone, String email, 
                        String gender, String DOB, String membershipStartDate, String personalTrainer) 
    {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;     
        this.paidAmount = 0;            
        this.discountAmount = 0;        
    }
    
    // Accessor methods
    public double getPremiumCharge() { return premiumCharge; }
    public String getPersonalTrainer() { return personalTrainer; }
    public boolean getIsFullPayment() { return isFullPayment; }
    public double getPaidAmount() { return paidAmount; }
    public double getDiscountAmount() { return discountAmount; }

    /**
     * Overrides the parent class method to mark attendance.
     * Increments attendance count and adds loyalty points.
     */
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
    }

    /**
     * Processes payment for premium membership.
     * Updates payment status and tracks remaining amount.
     * 
     * @param amount The payment amount to be processed
     * @return Status message indicating payment result and remaining amount
     */
    public String payDueAmount(double amount) {
        if (this.isFullPayment) {
            return "Payment is already done.";
        }

        if (this.paidAmount + amount > premiumCharge) {
            return "Payment amount exceeds the premium charge.";
        }

        this.paidAmount += amount;
        if (this.paidAmount == premiumCharge) {
            this.isFullPayment = true;
        }

        double remainingAmount = premiumCharge - this.paidAmount;
        return "Payment successful. Remaining amount: " + remainingAmount;
    }
    
    /**
     * Calculates discount for members who have completed full payment.
     * Applies 10% discount on premium charge if eligible.
     */
    public void calculateDiscount() {
        if (isFullPayment) {
            this.discountAmount = premiumCharge * 0.10; 
            System.out.println("Discount calculated: " + discountAmount);
        } else {
            System.out.println("No discount available. Complete payment first.");
        }
    }

    /**
     * Reverts premium membership to initial state.
     * Resets all premium-specific attributes to default values.
     */
    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }
    
    /**
     * Overrides parent display method to include premium member specific details.
     * Displays trainer info, payment status, and discount information.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Payment Status: " + (isFullPayment ? "Complete" : "Incomplete"));
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount: " + remainingAmount);
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}
