/**
 * RegularMember class represents a standard gym membership type.
 * Extends GymMember class with additional features specific to regular membership.
 */
public class RegularMember extends GymMember 
{
    // Constants and membership-specific attributes
    private final int attendanceLimit = 30;        
    private boolean isEligibleForUpgrade;         
    private String removalReason;                  
    private String referralSource;         
    private String plan;                        
    private double price;                        

    /**
     * Constructor for creating a new regular member.
     * Initializes member with basic plan and default values.
     * 
     * @param id Unique identifier for the member
     * @param name Member's full name
     * @param location Member's address
     * @param phone Contact number
     * @param email Email address
     * @param gender Member's gender
     * @param DOB Date of Birth
     * @param membershipStartDate Start date of membership
     * @param referralSource Source of referral for the member
     */
    public RegularMember(int id, String name, String location, String phone, String email, 
                        String gender, String DOB, String membershipStartDate, String referralSource) 
    {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }
    
    // Accessor Methods
    public int getAttendanceLimit() { return attendanceLimit; }
    public boolean getIsEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public String getReferralSource() { return referralSource; }
    public String getPlan() { return plan; }
    public double getPrice() { return price; }
    
    /**
     * Overrides the parent class method to mark attendance.
     * Increments attendance count and loyalty points.
     * Checks for upgrade eligibility based on attendance limit.
     */
    @Override 
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
        if (this.attendance >= attendanceLimit) {
            this.isEligibleForUpgrade = true;
        }
    }
    
    /**
     * Gets the price for a specific membership plan.
     * 
     * @param plan The plan type (basic/standard/deluxe)
     * @return The price of the plan, -1 if invalid plan
     */
    public double getPlanPrice(String plan) {
        switch(plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;
        }
    }
    
    /**
     * Upgrades the member's plan if eligible.
     * 
     * @param newPlan The plan to upgrade to
     * @return Status message indicating success or reason for failure
     */
    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Not eligible for upgrade. Need more attendance.";
        }
        
        if (newPlan.toLowerCase().equals(this.plan)) {
            return "Already subscribed to this plan.";
        }

        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected.";
        }

        this.plan = newPlan.toLowerCase();
        this.price = newPrice;
        return "Plan upgraded successfully to " + newPlan;
    }
    
    /**
     * Reverts member to basic status with reason.
     * Resets all membership parameters to default values.
     * 
     * @param removalReason The reason for reverting membership
     */
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 4500;
        this.removalReason = removalReason;
    }
    
    /**
     * Overrides parent display method to include regular member specific details.
     * Displays plan, price, and removal reason if exists.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }    
}


