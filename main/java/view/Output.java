package view;

import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**Creates the output which takes no input from the user. */
public class Output {
  int index = 0;

  public void printStartMsg() {
    System.out.println("\nThe Stuff Lending System Has Started");
  }

  /**Lists all members in the stuff system and their items. */
  public void listAllMembers(Iterable<? extends Member> currentMembers) {
    index = 0;
    int i = 0;
    for (Member thisMember : currentMembers) {
      listMemberVerbose(thisMember, index);
      i = 0;
      for (Item thisItem : thisMember.getItem()) {
        listItemVerbose(thisItem, i);
        i++;
      }
      index++;
    }
  }

  /**Lists all members in a simple way. */
  public void listAllMembersSimple(Iterable<? extends Member> currentMembers) {
    index = 0;
    for (Member member : currentMembers) {
      listMemberSimple(member, index);
      index++;
    }
  }
    
  /**Lists all items in a verbose way. */
  public void listAllItemsVerbose(Iterable<? extends Item> items) {
    int index = 0;
    for (Item item : items) {
      listItemVerbose(item, index);
      index++;
    }
  }
    
  /**Lists all items in a simple way. */
  public void listAllItemsSimple(Iterable<? extends Item> items) {
    int index = 0;
    for (Item item : items) {
      listItemsSimple(item, index);
      index++;
    }
  }

  /**Info on category of items. */
  public String itemCategoryVerbose(Item memberItem) {
    if (memberItem.getCategory() == 1) {
      return "1 - Tools";
    } else if (memberItem.getCategory() == 2) {
      return "2 - Vehicle";
    } else if (memberItem.getCategory() == 3) {
      return "3 - Game";
    } else if (memberItem.getCategory() == 4) {
      return "4 - Toy";
    } else if (memberItem.getCategory() == 5) {
      return "5 - Sports";
    } else {
      return "6 - Other";
    }
  }
    
  /**
  * Prints an items info in a verbose way.
  *
  * @param memberItem is the item to output the info from. 
  * @param i is to show the item index.
  */
  public void listItemVerbose(Item memberItem, int i) {
    System.out.println("\n" + i + ". Name: " + memberItem.getName() + "\nOwner: " 
        + memberItem.getOwner().getFirstName() + "\nCategory: " + itemCategoryVerbose(memberItem) 
        + "\nDescription: " + memberItem.getDescription() + "\nDay created: " + memberItem.getDayCreated() 
        + "\nCost Per Day: " + memberItem.getCostPerDay() + "\nAvailability: " + memberItem.isAvailable() + "\n");
    int x = 0;
    for (Contract thisContract : memberItem.getItemContracts()) {
      listContracts(thisContract.getBorrower(), thisContract, x);
    }
  }
    
  /**
  * Prints a members info simply.
  *
  * @param member is.
  * @param i is.
  */
  public void listMemberSimple(Member member, int i) {
    System.out.println(i + ". " + member.getFirstName() + " " + member.getLastName() + "\nEmail- " 
            + member.getEmail() + "\nCredits- " + member.getCredits() + "\nNumber Of Owned Items " 
            + member.getNumItems() + "\n");
  }
    
  /**
  * Prints a members info in a verbose way.
  *
  * @param members is.
  * @param i is.
  */
  public void listMemberVerbose(Member members, int i) {
    System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~\n" + i + ". " + members.getFirstName() + " " 
            + members.getLastName() + "\nCredits-  " + members.getCredits() + "\nID: " + members.getId() 
            + "\nEmail- " + members.getEmail() + "\nPhone Number- " + members.getPhoneNum() 
            + "\nDay Joined- " + members.getDayJoined() + "\n\nItems:");
  }
    
  /**Prints an items info simply. */
  public void listItemsSimple(Item memberItems, int i) {
    System.out.println(i + ". Name: " + memberItems.getName() + "\nOwner-" + memberItems.getOwner().getFirstName()
            + " " + memberItems.getOwner().getLastName());
  }
    
  /**Prints a contract and its information. */
  public void listContracts(Member borrower, Contract contract, int i) {
    System.out.println("Contract: " + contract.getStatus() + "\nBorrower- " 
        + borrower.getFirstName() + " " + borrower.getLastName() 
        + "\nStart Day- " + contract.getStartDay() + " End Day- " + contract.getEndDay() + "\n");
  }
    
  /**Prints an items info. */
  public void printItemInfo(Item newItem) {
    System.out.println("\nItem name- " + newItem.getName() + "\nCategory- " + itemCategoryVerbose(newItem) 
            + "\nCost per day- " + newItem.getCostPerDay() + "\nDescription- " + newItem.getDescription());
  }
    
  public void printContractMessage() {
    System.out.println("Select the Item To Borrow");
  }   
}
    

