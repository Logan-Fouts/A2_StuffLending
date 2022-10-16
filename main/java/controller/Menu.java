package controller;

import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;
import model.domain.Member.Mutable;
import view.ConsoleUi;


/** Tbd Main action.*/
public class Menu {
  view.ConsoleUi view;
  view.Output output;
  model.domain.StuffSys stuffSys;
  int index = 0;

  /**Creates the new menu object. */
  public Menu(view.ConsoleUi view, model.domain.StuffSys stuffSys, view.Output output) {
    this.view  =  view;
    this.stuffSys = stuffSys;
    this.output = output;

  }

  /** The loop that displays the menue and runs the Options choosen.*/
  public void startStuffSys() { 
    output.printStartMsg();
    boolean on = true;
    int selectedMemberIndex = -1;
    //Time4 time = new Time4();
    while (on) {
      ConsoleUi.Event option = view.showMainMenu(stuffSys.getCurrentDay());
      switch (option) {
        case SelectMember:
          selectedMemberIndex = view.showMemberMenu(stuffSys.getAllMembers(), stuffSys.getAllMembers().size());
          option = view.memberActionMenu();
          switch (option) {
            case AddNewItem:      
              Item itemToAdd = view.addItem(stuffSys.getCurrentDay());
              Member.Mutable owner = stuffSys.getAllMembers().get(selectedMemberIndex);
              stuffSys.addItem(new Item.Mutable(itemToAdd.getName(), itemToAdd.getCategory(), 
                  itemToAdd.getDescription(), itemToAdd.getDayCreated(), itemToAdd.getCostPerDay(), 
                  itemToAdd.isAvailable(), owner, -1), owner, null); 
              break;
            case SelectItem:
              if (stuffSys.getAllMembers().get(selectedMemberIndex).getItem().size() == 0) {
                view.printInfoError();
                startStuffSys();
              }
              output.listAllItemsVerbose(stuffSys.getAllMembers().get(selectedMemberIndex).getItem());
              int itemIndex = view.getInput();
              ConsoleUi.Event itemActionChoice = view.showItemActionMenu();
              Item.Mutable selectedItem = stuffSys.getAllMembers().get(selectedMemberIndex).getItem().get(itemIndex);
              switch (itemActionChoice) {
                case ViewItemInfo:
                  output.printItemInfo(stuffSys.getAllMembers().get(selectedMemberIndex).getItem().get(itemIndex));
                  break;
                case DeleteItem:
                  stuffSys.removeItemSys(selectedMemberIndex, itemIndex);
                  break;
                case ChangeItemInfo:
                  Item tempItem = view.changeItemInfo(selectedItem);
                  stuffSys.updateItemInfo(selectedItem, tempItem);
                  break;
                case GoBack:
                  break;
                case Error:
                  break;
                default:
                  break;
              }
              break;
            case ListMemberInfo:
              index = 0;
              output.listMemberVerbose(stuffSys.getAllMembers().get(selectedMemberIndex), index);
              output.listAllItemsVerbose(stuffSys.getAllMembers().get(selectedMemberIndex).getItem());
              break;
            case ChangeMemberInfo:
              Member tempMember = view.changeMemberInfo(stuffSys.getAllMembers().get(selectedMemberIndex), 
                  stuffSys.getAllMembers());
              stuffSys.updateMemberInfo(stuffSys.getAllMembers().get(selectedMemberIndex), tempMember);
              break;
            case DeleteMember:
              stuffSys.removeMember(selectedMemberIndex);
              break;
            case GoBack:
              break;
            case Quit:
              on = false;
              break;
            default:
              break;
          }
          break;
        case AddMember:
          Member memberToAdd = view.createMember(stuffSys.getCurrentDay(), stuffSys.getAllMembers());
          stuffSys.addMember(new Member.Mutable(memberToAdd.getFirstName(), 
              memberToAdd.getLastName(), memberToAdd.getEmail(), memberToAdd.getPhoneNum(), 
              memberToAdd.getDayJoined(), memberToAdd.getCredits(), -1));
          break;
        case ListAllMemberSimple:
          output.listAllMembersSimple(stuffSys.getAllMembers());
          break;
        case ListAllMemberVerbose:
          output.listAllMembers(stuffSys.getAllMembers());
          break;
        case CreateContract:
          output.listAllItemsSimple(stuffSys.getAllItems());
          int itemIndex = view.getInput();
          if (itemIndex < 0 || itemIndex > stuffSys.getAllItems().size() - 1) {
            view.printInfoError();
            startStuffSys();
          }
          Item.Mutable item = stuffSys.getAllItems().get(itemIndex);
          int startDay = view.getStart();
          int endDay = view.getEnd();
          double costPerDay = stuffSys.getAllItems().get(itemIndex).getCostPerDay();
          Member.Mutable owner = (Mutable) stuffSys.getAllItems().get(itemIndex).getOwner();
          Member borrower = stuffSys.getAllMembers().get(view.getBorrowerIndex(stuffSys.getAllMembers(), 
              stuffSys.getAllMembers().size()));
          stuffSys.addContractSys(new Contract.Mutable(startDay, endDay, costPerDay, borrower), item, owner, itemIndex);
          break;
        case AdvanceDay:
          stuffSys.advanceDay();
          break;
        case Quit:
          on = false;
          break;
        case Error:
          break;
        default:
          break;
      }
    }
  }
}
