package view;

import java.util.Scanner;
import model.domain.Item;
import model.domain.Member;

/**ConsoleUi gets low level input and prints to command line. */
public class ConsoleUi {
  view.Output output = new view.Output();
  Scanner scanner = new Scanner(System.in, "utf-8");
  int index = 0;

  /**The events that can be returned. */
  public static enum Event {
    SelectMember,
    AddMember,
    ListAllMemberSimple,
    ListAllMemberVerbose,
    CreateContract,
    AdvanceDay,
    ViewMemberInfo,
    DeleteItem,
    ChangeItemInfo,
    ChangeMemberInfo,
    GoBack,
    AddNewItem,
    SelectItem,
    ListMemberInfo,
    DeleteMember,
    ViewItemInfo,
    Error,
    Quit
  }

  /**
   * AddItem Method.
   *
   * @return is to return the user's choice.
   */
  public Event showMainMenu(int currentDay) {
    System.out.println("\nMain Menu: Day " + currentDay + "\n~~~~~~~~~~~~~~~~~");
    System.out.println("0- Select A Member\n1- Add New Member\n2- List All Members (Simple)\n" 
        + "3- List All Members (Verbose)\n4- Create Lending Contract\n5- Advance Day\n6- Quit\n");
    int selection = scanner.nextInt();
    if (selection == 0) {
      return Event.SelectMember;
    } else if (selection == 1) {
      return Event.AddMember;
    } else if (selection == 2) {
      return Event.ListAllMemberSimple;
    } else if (selection == 3) {
      return Event.ListAllMemberVerbose;
    } else if (selection == 4) {
      return Event.CreateContract;
    } else if (selection == 5) {
      return Event.AdvanceDay;
    } else if (selection == 6) {
      return Event.Quit;
    } else {
      return printInfoError();
    }
  }

  /**
  * Shows the member action options and gets user input.
  *
  * @return is to return the users choice.
  */
  public Event memberActionMenu() {
    System.out.println("\n~~~~~~~~~~~~~~\n0- Add New Item To The Member"
        + "\n1- Select Member Item\n2- List Member Info\n3- Change Member Info" 
        + "\n4- Delete Member\n5- Go Back\n6- Quit");
    int actionChoice = getInput();
    if (actionChoice == 0) {
      return Event.AddNewItem;
    } else if (actionChoice == 1) {
      return Event.SelectItem;
    } else if (actionChoice == 2) {
      return Event.ListMemberInfo;
    } else if (actionChoice == 3) {
      return Event.ChangeMemberInfo;
    } else if (actionChoice == 4) {
      return Event.DeleteMember;
    } else if (actionChoice == 5) {
      return Event.GoBack;
    } else if (actionChoice == 6) {
      return Event.Quit;
    } else {
      return printInfoError();
    }
  }

  /**
   * Shows the item action options and gets user input.
   *
   * @return is to return the users choice.
   */
  public Event showItemActionMenu() {
    System.out.println("\n0- View Item Info\n1- Delete An Item\n2- Change Item Info\n3- Go back");
    int actionChoice = getInput();
    if (actionChoice == 0) {
      return Event.ViewItemInfo;
    } else if (actionChoice == 1) {
      return Event.DeleteItem;
    } else if (actionChoice == 2) {
      return Event.ChangeItemInfo;
    } else if (actionChoice == 3) {
      return Event.GoBack;
    } else {
      return printInfoError();
    }
  }

  /**
   * Returns a new item to be added.
   *
   * @param currentDay the current day in the system.
   * @return The new item to be added.
   */
  public Item addItem(int currentDay) {
    String itemName;
    System.out.println("\nEnter a name for the item : ");
    scanner.nextLine();
    itemName = scanner.nextLine();
    int itemCategory;
    System.out.print("Choose a category for the item : \n 1 - Tools\n");
    System.out.println(" 2 - Vehicle\n 3 - Game\n 4 - Toy\n 5 - Sports\n 6 - Other ");
    itemCategory = scanner.nextInt();
    while (itemCategory < 1 || itemCategory > 6) {
      itemCategory = scanner.nextInt();
    }
    String itemDescription;
    System.out.println("Write a short description for your item : ");
    scanner.nextLine();
    itemDescription = scanner.nextLine();
    float itemDailyCost;
    System.out.println("Set the daily cost for borrowing the item : ");
    itemDailyCost = scanner.nextFloat();
    boolean itemAvailability = true;
    return (new Item(itemName, itemCategory, itemDescription, currentDay, itemDailyCost, 
        itemAvailability, null, -1));
  }

  /**
   * Creates and returns a new member. 
   *
   * @param currentDay The current Day in the system.
   * @param currentMembers An iterable of all members in the system.
   * @return The new member to be added.
   */
  public Member createMember(int currentDay, Iterable<? extends Member> currentMembers) {
    System.out.println("Enter first name: ");
    scanner.nextLine();
    final String firstName = scanner.nextLine();
    System.out.println("Enter last name: ");
    final String lastname = scanner.nextLine();
    System.out.println("Enter Email: ");
    final String email = scanner.nextLine();
    System.out.println("Enter Phone Number: ");
    final String phoneNum = scanner.nextLine();
    for (Member tempMember : currentMembers) {
      if (email.equals(tempMember.getEmail()) || phoneNum.equals(tempMember.getPhoneNum())) {
        printInfoError();
        createMember(currentDay, currentMembers);
      }
    }
    return (new Member(firstName, lastname, email, phoneNum, currentDay, 0, -1));
  }

  /**User can change the members info. */
  public Member changeMemberInfo(Member member, Iterable<? extends Member> members) {
    System.out.println("Enter first name (previosly : " + member.getFirstName() + ")");
    scanner.nextLine();
    final String firstName = scanner.nextLine();
    System.out.println("Enter last name (previosly : " + member.getLastName() + ")");
    final String lastName = scanner.nextLine();
    System.out.println("Enter Email (previosly : " + member.getEmail() + ")");
    final String email = scanner.nextLine();
    System.out.println("Enter phone number (previosly : " + member.getPhoneNum() + ")");
    final String phoneNum = scanner.nextLine();
    for (Member checkMember : members) {
      if (checkMember.getEmail().equals(email) || checkMember.getPhoneNum().equals(phoneNum)) {
        System.out.println("Error- Updated Member Information is Not Unique. Try Again");
        changeMemberInfo(member, members);
      }
    }
    return new Member(firstName, lastName, email, phoneNum, -1, 0, -1);
  }

  /**User can change items info. */
  public Item changeItemInfo(Item selectedItem) {
    System.out.println("Enter name (previosly : " + selectedItem.getName() + ")");
    scanner.nextLine();
    final String name = scanner.nextLine();
    System.out.println("Enter category (previosly : " + selectedItem.getCategory() + ")");
    System.out.print("Choose a category for the item : \n 1 - Tools\n");
    System.out.println(" 2 - Vehicle\n 3 - Game\n 4 - Toy\n 5 - Sports\n 6 - Other ");    
    final int category = scanner.nextInt();
    System.out.println("Enter description (previosly : " + selectedItem.getDescription() + ")");
    scanner.nextLine();
    final String description = scanner.nextLine();
    System.out.println("Enter cost per day (previosly : " + selectedItem.getCostPerDay() + ")");
    final double costPerDay = scanner.nextDouble();
    return new Item(name, category, description, selectedItem.getDayCreated(), costPerDay, 
        selectedItem.isAvailable(),  null, -1);
  }

  /**Shows members and returns borrowers index. */
  public int getBorrowerIndex(Iterable<? extends Member> members, int size) {
    System.out.print("\nChoose the Borrower");
    int borrowerIndex = showMemberMenu(members, size);
    return borrowerIndex;
  }

  //**Gets the user input contract start day */
  public int getStart() {
    System.out.print("Enter the Starting Day of the Contract: ");
    return scanner.nextInt();
  }

  //**Gets the user input contract end day */
  public int getEnd() {
    System.out.print("Enter the Ending Day of the Contract: ");
    return scanner.nextInt();
  }

  //**Gets a users int input. */
  public int getInput() {
    int number = scanner.nextInt();
    return number;
  }

  //**If there was an error with an input. Prints error message. */
  public Event printInfoError() {
    System.out.println("\nEntered Information Does Not Work. Try Again");
    return Event.Error;
  }

  /**
   * Shows the members and gets user input.
   *
   * @param allMembers is the list of memebrs in the system.
   * @return is the users member choice.
   */
  public int showMemberMenu(Iterable<? extends Member> allMembers, int size) {
    System.out.println("\nType The Number of the Member\n~~~~~~~~~~~~~~");
    index = 0;
    for (Member member : allMembers) {
      output.listMemberSimple(member, index);
      index++; 
    }
    int choice = scanner.nextInt();
    if (choice < 0 || choice > size - 1) {
      choice =  showMemberMenu(allMembers, size);
    }
    return choice;
  }
}
