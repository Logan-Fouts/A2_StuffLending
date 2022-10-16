package model.domain;

import java.util.ArrayList;
import model.Time4;

/** The Stuff System. Hardcode some members here.*/
public class StuffSys {

  public ArrayList<Member.Mutable> allMembers = new ArrayList<Member.Mutable>();
  public ArrayList<Item.Mutable> allItems = new ArrayList<Item.Mutable>();
  public ArrayList<Contract.Mutable> allContracts = new ArrayList<Contract.Mutable>();
  public Time4 time = new Time4();

  /**Public method in stuff system.*/
  public StuffSys() {
    allMembers = new ArrayList<>();
    addMember(new Member.Mutable("Bob", "Smith", "bobsmith@gmail.com", 
        "+04401675549", time.getCurrentDay(), 300, -1));
    addMember(new Member.Mutable("Chad", "Brad", "chadbraddington@outlook.com", 
        "+04405821579", time.getCurrentDay(), 100, -1));
    addMember(new Member.Mutable("Logan", "Fouts", "loganflogan@hotmail.com", 
        "+04402643396", time.getCurrentDay(), 0, -1));

    addItem(new Item.Mutable("Knife", 1, "For Cutting", time.getCurrentDay(),  
        50, true, allMembers.get(0), -1), allMembers.get(0), null);
    addItem(new Item.Mutable("Cat", 6, "For Support", time.getCurrentDay(),  
        10, true, allMembers.get(0), -1), allMembers.get(0), null);
    addItem(new Item.Mutable("Ball", 5, "New Basketball", time.getCurrentDay(),  
        4.2, true, allMembers.get(2), -1), allMembers.get(2), null);

  }

  /*Adds a member to the stuff system. */
  public void addMember(Member.Mutable members) {
    allMembers.add(new Member.Mutable(members.getFirstName(), members.getLastName(), members.getEmail(), 
        members.getPhoneNum(), time.getCurrentDay(), members.getCredits(), allMembers.size()));
  }

  /*Removes a member and all their info from the system. */
  public void removeMember(int memberIndex) {
    allMembers.remove(memberIndex);
  }

  /**
   * Adds a new item to the owner, and the array list of all items.  
   *
   * @param item The item to be added.
   * @param owner The owner of the item.
   * @param borrower  The borrower of the item.
   */
  public void addItem(Item.Mutable item, Member.Mutable owner, Member.Mutable borrower) {
    Item.Mutable itemToAdd = new Item.Mutable(item.getName(), item.getCategory(), 
        item.getDescription(), item.getDayCreated(), 
        item.getCostPerDay(), item.isAvailable(), owner, allItems.size());
    //itemToAdd.setOwner(owner);
    owner.addItemToMember(itemToAdd, owner, borrower, owner.getCredits());
    allItems.add(itemToAdd);
  }

  /*Removes the item from both the system and the member. */
  public void removeItemSys(int memberIndex, int itemIndex) {
    int localIndex = allMembers.get(memberIndex).getItem().get(itemIndex).getIndex();
    allMembers.get(memberIndex).removeItem(itemIndex);
    allItems.remove(localIndex);
  }

  /*Returns the current day in the system. */
  public int getCurrentDay() {
    return time.getCurrentDay();
  }

  /**Updates the members info with the new info. */
  public void updateMemberInfo(Member.Mutable memberToUpdate, Member tempMember) {
    memberToUpdate.setFirstName(tempMember.getFirstName());
    memberToUpdate.setLastName(tempMember.getLastName());
    memberToUpdate.setEmail(tempMember.getEmail());
    memberToUpdate.setPhoneNum(tempMember.getPhoneNum());
  }

  /**Updates the items info with the new info. */
  public void updateItemInfo(Item.Mutable itemToUpdate, Item tempItem) {
    itemToUpdate.setName(tempItem.getName());
    itemToUpdate.setCategory(tempItem.getCategory());
    itemToUpdate.setDescription(tempItem.getDescription());
    itemToUpdate.setCostPerDay(tempItem.getCostPerDay());;
  }

  /**Adds and removes credits from the owner and borrower members. */
  public void manageCredits(double credits, Member.Mutable owner, Member.Mutable borrower) {
    owner.setCredits(credits - (credits * 2));
    borrower.setCredits(credits);
  }

  public ArrayList<Member.Mutable> getAllMembers() {
    return allMembers;
  }

  public ArrayList<Item.Mutable> getAllItems() {
    return allItems;
  }

  public ArrayList<Contract.Mutable> getAllContracts() {
    return allContracts;
  }

  /**Advances the day and checks for changes in contracts. */
  public void advanceDay() {
    time.advanceDay();
    for (Item.Mutable item : allItems) {
      item.checkContract(time.getCurrentDay());
    }
  }

  /**Adds the new contract to the system, and the items contracts. */
  public void addContractSys(Contract.Mutable newContract, Item.Mutable item, 
      Member.Mutable owner, int index) {
    if (owner.addNewContract(newContract, time.getCurrentDay(), item, index)) {
      allContracts.add(newContract);
      manageCredits((item.getCostPerDay() * (newContract.getStartDay() 
          - newContract.getEndDay())), owner, newContract.getBorrower());
    }
  }
}
