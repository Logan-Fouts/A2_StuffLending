package model.domain;

import java.util.ArrayList;

/**Class that defines item objects. */
public class Item { 
  
  protected String name;
  protected int category;
  protected String description;
  protected int dayCreated;
  protected int indexInSys;
  protected double costPerDay;
  protected boolean isAvailable;
  protected Member owner;
  protected ArrayList<Contract.Mutable> itemContracts = new ArrayList<>();

  /** The Item Constructor. */
  public Item(String name, int category, String description, 
      int dayCreated, double costPerDay, boolean isAvailable, Member owner, int indexInSys) {
    this.owner = owner;
    this.name = name;
    this.category = category;
    this.description = description;
    this.dayCreated = dayCreated;
    this.costPerDay = costPerDay;
    this.isAvailable = isAvailable;
    this.indexInSys = indexInSys;
  }

  /**Creates and returns an iterable of item contracts. */
  public ArrayList<Contract> getItemContracts() {
    ArrayList<Contract> clonedList = new ArrayList<Contract>();
    for (Contract.Mutable contract : itemContracts) {
      clonedList.add(contract);
    }
    return clonedList;
  }

  public int getIndex() {
    return indexInSys;
  }

  public Member getOwner() {
    return this.owner;
  }

  public String getName() {
    return this.name;
  }

  public int getCategory() {
    return this.category;
  }

  public String getDescription() {
    return this.description;
  }

  public int getDayCreated() { 
    return this.dayCreated;
  }

  public double getCostPerDay() {
    return this.costPerDay;
  }

  /** The state of availibility of the item.*/
  public boolean isAvailable() {
    return this.isAvailable;
  }

  /**Defines a mutable item, and its setters. */
  public static class Mutable extends Item {

    public Mutable(String name, int category, String description, int dayCreated, 
        double costPerDay, boolean isAvailable, Member owner, int indexInSys) {
      super(name, category, description, dayCreated, costPerDay, isAvailable, owner, indexInSys);
    }
    
    public void setIsAvailable(boolean isAvailable) {
      this.isAvailable = isAvailable;
    }

    /**This checks if the contract has started or not and sets the availabilities of the item and contract.  */
    public void checkContract(int currentDay) {
      for (Contract.Mutable contract : itemContracts) {
        if (contract.getStartDay() == currentDay) {
          contract.setStatus(true);
          this.setIsAvailable(false);
        }
        if (contract.getEndDay() == currentDay - 1) {
          contract.setStatus(false);
          this.setIsAvailable(true);
        }
      }
    }

    public void setCostPerDay(double costPerDay) {
      this.costPerDay = costPerDay;
    }

    public void setDayCreated(int dayCreated) {
      this.dayCreated = dayCreated;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public void setCategory(int category) {
      this.category = category;
    }

    public void setOwner(Member.Mutable owner) {
      this.owner = owner;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void addContractToItem(Contract.Mutable contract) {
      itemContracts.add(contract);
    }

  }
}