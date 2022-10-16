package model.domain;

import java.util.ArrayList;
import model.GenId;

/** This is the member class.*/
public class Member {
  
  protected String firstName;
  protected String lastName;
  protected String email;
  protected String phoneNum;
  protected String id;
  protected double memberCredits;
  protected int dayJoined;
  private GenId newId = new GenId();
  protected ArrayList<Item.Mutable> items = new ArrayList<Item.Mutable>();
  protected int indexInSys;

  /**
   * Public method in member class.
   *
   * @param firstName is the member's firstname.
   * @param lastName is the member's lastname.
   * @param email is the member's email.
   * @param phoneNum is the member's phone number.
   * @param currentDay is the current date.
   */
  public Member(String firstName, String lastName, String email, String phoneNum, 
      int currentDay, double memberCredits, int indexInSys) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.memberCredits = memberCredits;
    this.phoneNum = phoneNum;
    this.dayJoined = currentDay;
    this.id = newId.getAlphaNumericString(6);
    this.indexInSys = indexInSys;
  }

  public int getNumItems() {
    return items.size();
  }

  public int getLocalIndex() {
    return indexInSys;
  }

  /**Creates and returns a cloned arraylist of the members items. */
  public ArrayList<Item.Mutable> getItem() {
    ArrayList<Item.Mutable> clonedList = new ArrayList<Item.Mutable>();
    for (Item.Mutable item : items) {
      clonedList.add(item);
    }
    return clonedList;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPhoneNum() {
    return this.phoneNum;
  }

  public String getId() {
    return this.id;
  }

  public int getDayJoined() {
    return this.dayJoined;
  }

  public double getCredits() {
    return memberCredits;
  }
  

  /**Defines Mutable object members and the setters. */
  public static class Mutable extends Member {

    public Mutable(String firstName, String lastName, String email, String phoneNum, 
        int dayJoined, double memberCredits, int indexInSys) {
      super(firstName, lastName, email, phoneNum, dayJoined, memberCredits, indexInSys);
    }

    public void setItemAvailibility(boolean status, Item.Mutable item) {
      item.setIsAvailable(status);
    }

    /**
     * Adds created item object to the member.
     *
     * @param item is the item.
     * @param owner is the item's owner.
     */
    public void addItemToMember(Item.Mutable item, Member.Mutable owner, Member.Mutable borrower, double credits) {
      items.add(item);
      memberCredits = memberCredits + 100;
    }

    public void setDayJoined(int dayJoined) {
      this.dayJoined = dayJoined;
    }
  
    public void setCredits(double credits) {
      this.memberCredits = this.memberCredits + credits;
    }

    public void setPhoneNum(String phoneNum) {
      this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    /**Removes an item and its contracts from the item array list.*/
    public void removeItem(int index) {
      this.items.remove(index);
    }

    /**Adds a contract to item, member, and stuffSys if the contract is. */
    public boolean addNewContract(Contract.Mutable newContract, int currentDay, Item.Mutable item, int index) {
      boolean contractValidity = true;
      double credits = (item.getCostPerDay()) 
          * (newContract.getEndDay() - newContract.getStartDay());
      for (Contract contract : item.getItemContracts()) {
        if (newContract.getStartDay() <= contract.getEndDay() 
            && newContract.getStartDay() >= contract.getStartDay()) {
          contractValidity = false;
        }
        if (newContract.getEndDay() <= contract.getEndDay() 
            && newContract.getEndDay() >= contract.getStartDay()) {
          contractValidity = false;
        }
        if (contract.getStartDay() <= newContract.getEndDay()
            && contract.getStartDay() >= newContract.getStartDay()) {
          contractValidity = false;
        }
        if (contract.getEndDay() <= newContract.getEndDay() 
            && contract.getEndDay() >= newContract.getStartDay()) {
          contractValidity = false;
        }
      }
      if (newContract.getStartDay() < currentDay) {
        contractValidity = false;
      }
      if (newContract.getBorrower().getCredits() < credits) {
        contractValidity = false;
      }
      if (contractValidity) {
        item.addContractToItem(newContract);
      }
      return contractValidity;
    }


  }
}

