package model.domain;

/** Contracts for item borrowing.*/
public class Contract {
    
  protected int startDay;
  protected int endDay;
  protected boolean status;
  protected Member borrower;
  
  /**The constructor for contract objects. */
  public Contract(int startDay, int endDay, 
      double costPerDay, Member borrower) {  
    this.startDay = startDay;
    this.endDay = endDay;
    this.borrower = borrower;
  }

  public boolean getStatus() {
    return this.status;
  }

  public Member.Mutable getBorrower() {
    return (model.domain.Member.Mutable) this.borrower;
  }

  public int getStartDay() {
    return this.startDay;
  }

  public int getEndDay() {
    return this.endDay;
  }

  /**Defines mutable contract objects and the setters. */
  public static class Mutable extends Contract {

    public Mutable(int startDay, int endDay, 
        double costPerDay, Member borrower) {
      super(startDay, endDay, costPerDay, borrower);
    }

    public void setEndDay(int endDay) {
      this.endDay = endDay;
    }

    public void setStartDay(int startDay) {
      this.startDay = startDay;
    }

    /**Sets the status of the item for the item and the owners item. */
    public void setStatus(boolean contractStatus) {
      this.status = contractStatus;
    }
  }
}
