/**
 * Abstract class representing a gym member with basic membership functionality.
 * This class serves as a base class for different types of gym memberships.
 */
public abstract class GymMember {
    // Member personal information
    protected int id;                    
    protected String name;              
    protected String location;          
    protected String phone;               
    protected String email;              
    protected String gender;            
    protected String DOB;               
    protected String membershipStartDate; 
    
    // Membership tracking attributes
    protected int attendance;             
    protected double loyaltyPoints;    
    protected boolean activeStatus; 
    
    /**
     * Constructor to initialize a new gym member with basic information.
     * @param id Unique identifier for the member
     * @param name Full name of the member
     * @param location Residential address
     * @param phone Contact number
     * @param email Email address
     * @param gender Member's gender
     * @param DOB Date of Birth
     * @param membershipStartDate Start date of membership
     */
    public GymMember(int id, String name, String location, String phone, String email, 
                    String gender, String DOB, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;        
    }
    
    // Accessor Methods
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getDOB() { return DOB; }
    public String getMembershipStartDate() { return membershipStartDate; }
    public int getAttendance() { return attendance; }
    public double getLoyaltyPoints() { return loyaltyPoints; }
    public boolean getActiveStatus() { return activeStatus; }

    /**
     * Abstract method to be implemented by subclasses for marking attendance.
     * Different types of memberships may have different attendance rules.
     */
    public abstract void markAttendance();
    
    /**
     * Activates the membership for the member.
     * Sets the activeStatus to true.
     */
    public void activateMembership() {
        this.activeStatus = true;
    }
    
    /**
     * Deactivates the membership if it's currently active.
     * Prints a message if membership is not activated.
     */
    public void deactivateMembership() {
        if(this.activeStatus) {
            this.activeStatus = false;
        } else {
            System.out.println("Membership is not activated yet.");
        }
    }
    
    /**
     * Resets member's status to default values.
     * Sets activeStatus to false, attendance to 0, and loyalty points to 0.
     */
    public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }
    
    /**
     * Displays all member information including:
     * personal details, attendance, loyalty points, and membership status.
     */
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + activeStatus);
    }
}
