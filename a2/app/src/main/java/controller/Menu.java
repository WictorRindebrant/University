package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.Contract;
import model.ItemObject;
import model.Manager;
import model.Member;
import view.ContractInterfaceView;
import view.ItemInterfaceView;
import view.ItemInterfaceView.ItemCategoryMenuChoice;
import view.MemberInterfaceView;
import view.MenuInterfaceView;

/**
 * Controller that calls all the functions.
 */

public class Menu {

  /**
   * The Main menu that calls all the other fuction and classes.
   *
   * @param scan     Handles all inputs.
   * @param man      Contains all lists with data.
   * @param memView  Handles inputs and prints conected to members.
   * @param itemView Handles inputs and prints conected to items.
   * @param conView  Handles inputs and prints conected to contracts.
   * @param menuView Handles inputs and prints conected to the menu.
   */
  public void menu(Scanner scan, Manager man, MemberInterfaceView memView, ItemInterfaceView itemView,
      ContractInterfaceView conView, MenuInterfaceView menuView) {
    MenuInterfaceView.MainMenuChoice option = menuView.showMainMenu(scan);
    switch (option) {
      case MemberMenu:
        MemberInterfaceView.MemberMenuChoice memberMenuChoice = memView.showMemberMenu(scan);
        switch (memberMenuChoice) {
          case AddMember:
            Member newMember = memView.newMember(scan, man);
            man.addMember(newMember);
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          case MemberInfo:
            memView.chooseAmember();
            Member choosenMember = chooseMemberCheck(scan, man, memView, conView, menuView);
            memView.printName(choosenMember.getName());
            memView.memberInfoEmail(choosenMember.getEmail());
            memView.memberInfoPhoneNumber(choosenMember.getPhonenmr());
            memView.memberInfoId(choosenMember.getId());
            memView.memberInfoCash(choosenMember.getCash());
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          case DeleteMember:
            memView.chooseAmember();
            choosenMember = chooseMemberCheck(scan, man, memView, conView, menuView);

            for (Contract c : man.getContractlst()) {
              if (c.getLender().equals(choosenMember)
                  || c.getReceiver().equals(choosenMember)) {
                man.removeContract(man.getContractlst().indexOf(c));
              }
            }
            man.removeMember(choosenMember);
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          case ChangeMemberInfo:
            memView.chooseAmember();
            choosenMember = chooseMemberCheck(scan, man, memView, conView, menuView);

            MemberInterfaceView.MemberEditMenuChoice memberEditMenuChoice = memView.showMemberEditMenu(scan);
            switch (memberEditMenuChoice) {
              case NewName:
                choosenMember.setName(memView.newName(scan));
                break;
              case NewPhonenmr:
                choosenMember
                    .setPhonenmr(memView.phoneCheck(scan, choosenMember, man, memView.newPhonenmr(scan)));
                break;
              case NewEmail:
                choosenMember
                    .setPhonenmr(memView.emailCheck(scan, choosenMember, man, memView.newPhonenmr(scan)));
                break;
              default:
                menu(scan, man, memView, itemView, conView, menuView);
                break;
            }
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          case SimpleMemberList:
            for (Member i : memView.getSortedMemberList(man)) {
              memView.printName(i.getName());
              memView.memberInfoEmail(i.getEmail());
              memView.memberInfoCash(i.getCash());
              itemView.numberOfItemsowned(i.getOwnItems().size());
              itemView.lineBreaker();
            }
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          case VerboseMemberList:
            for (Member i : memView.getSortedMemberList(man)) {
              memView.printName(i.getName());
              memView.memberInfoEmail(i.getEmail());
              itemView.ownedItems();
              for (ItemObject z : i.getOwnItems()) {
                itemView.nameOfItem(z.getName());
                for (Contract x : man.getContractlst()) {
                  if (x.getItem().equals(z) && x.getEndDay() >= man.getToDay()) {
                    conView.lendTo(x.getReceiver().getName());
                    conView.lendBetweenDays(x.getStartDay(), x.getEndDay());
                  }
                }
              }
            }
            itemView.lineBreaker();
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          default:
            break;
        }
        break;
      case ItemMenu:
        ItemInterfaceView.ItemMenuChoice itemMenuChoice = itemView.showItemMenu(scan);
        switch (itemMenuChoice) {
          case AddItem:
            memView.chooseAmember();
            Member choosenMember = chooseMemberCheck(scan, man, memView, conView, menuView);
            ItemCategoryMenuChoice choosenType = itemView.whatTypeOfItem(scan);
            switch (choosenType) {
              case Tool:
                ItemObject newItem = itemView.newTool(man.getToDay(), scan);
                createItem(choosenMember, man, newItem, scan, itemView);
                break;
              case Vehicel:
                newItem = itemView.newVehicel(man.getToDay(), scan);
                createItem(choosenMember, man, newItem, scan, itemView);
                break;
              case Game:
                newItem = itemView.newGame(man.getToDay(), scan);
                createItem(choosenMember, man, newItem, scan, itemView);
                break;
              case Toy:
                newItem = itemView.newToy(man.getToDay(), scan);
                createItem(choosenMember, man, newItem, scan, itemView);
                break;
              case Sport:
                newItem = itemView.newSport(man.getToDay(), scan);
                createItem(choosenMember, man, newItem, scan, itemView);
                break;
              case Other:
                newItem = itemView.newOther(man.getToDay(), scan);
                createItem(choosenMember, man, newItem, scan, itemView);
                break;
              default:
                break;
            }
            menu(scan, man, memView, itemView, conView, menuView);
            break;

          case DeleteItem:
            memView.chooseAmember();
            choosenMember = chooseMemberCheck(scan, man, memView, conView, menuView);
            itemView.chooseAnItem();
            int itemIndex = choosenItemCheck(scan, man, choosenMember, itemView);
            for (Contract c : man.getContractlst()) {
              if (c.getItem().equals(choosenMember.getOwnItems().get(itemIndex))) {
                man.removeContract(man.getContractlst().indexOf(c));
              }

            }
            man.removeItem(itemIndex);
            choosenMember.removeItem(itemIndex);
            menu(scan, man, memView, itemView, conView, menuView);
            break;

          case ChangeItemInfo:
            memView.chooseAmember();
            choosenMember = chooseMemberCheck(scan, man, memView, conView, menuView);
            itemView.chooseAnItem();
            ItemObject choosenItem = choosenMember.getOwnItems()
                .get(choosenItemCheck(scan, man, choosenMember, itemView));
            ItemInterfaceView.ItemEditMenuChoice itemEditMenuChoice = itemView.showEditItemMenu(scan);
            switch (itemEditMenuChoice) {
              case NewName:
                String name = itemView.newName(scan);
                while (choosenItem.setName(name, choosenMember) == false) {
                  itemView.errorMessageDup();
                  name = itemView.newName(scan);
                }
                break;

              case NewDescription:
                choosenItem.setDescription(itemView.newDescription(scan));
                break;

              case NewCostDay:
                choosenItem.setCostDay(itemView.newCostDay(scan));
                break;

              default:
                break;
            }
            menu(scan, man, memView, itemView, conView, menuView);
            break;

          case ViewItemInfo:
            memView.chooseAmember();
            choosenMember = chooseMemberCheck(scan, man, memView, conView, menuView);
            itemView.chooseAnItem();
            choosenItem = choosenMember.getOwnItems().get(choosenItemCheck(scan, man, choosenMember, itemView));
            memView.printName(choosenItem.getName());
            itemView.description(choosenItem.getDescription());
            itemView.costPerDay(choosenItem.getCostDay());
            itemView.dayOfCreation(choosenItem.getCreationDay());
            itemView.historyAndFuture();
            for (Contract c : man.getContractlst()) {
              if (c.getItem().equals(choosenItem)) {
                conView.lendByAndBetweendays(c.getReceiver().getName(), c.getStartDay(), c.getEndDay());
              }
            }
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          default:
            break;
        }
        break;
      case ContractMenu:
        ContractInterfaceView.ContractMenuChoice contractInterface = conView.showContractMenu(scan);
        switch (contractInterface) {
          case AddContract:
            conView.whoWantsToLendItem();
            Member choosenmember = chooseMemberCheck(scan, man, memView, conView, menuView);

            conView.chooseWhatItem();
            for (Contract c : man.getContractlst()) {
              if (!c.getLender().equals(choosenmember)) {
                conView.infoAboutContract(c.getReceiver().getName(), c.getItem().getName(), c.getStartDay(),
                    c.getEndDay());
              }
            }

            ArrayList<ItemObject> temp = new ArrayList<>();

            for (ItemObject item : man.getItemlst()) {
              if (!item.getOwnedBy().equals(choosenmember)) {
                temp.add(item);
              }
            }

            for (ItemObject i : temp) {
              conView.indexAndName(temp.indexOf(i), i.getName());
            }

            int choosenItemIndex = Integer.parseInt(scan.nextLine());
            while (choosenItemIndex > temp.size() - 1 || choosenItemIndex < 0) {
              itemView.errorMessage();
              choosenItemIndex = Integer.parseInt(scan.nextLine());
            }

            conView.chooseStartOfTheDayContract();
            int startDay = Integer.parseInt(scan.nextLine());
            conView.chooseEndOfTheContract();
            int endDay = Integer.parseInt(scan.nextLine());
            boolean overlap = man.contractOverlap(startDay, endDay, temp.get(choosenItemIndex));
            while (overlap) {
              itemView.errorMessage();
              conView.chooseStartOfTheDayContract();
              startDay = Integer.parseInt(scan.nextLine());
              conView.chooseEndOfTheContract();
              endDay = Integer.parseInt(scan.nextLine());
              overlap = man.contractOverlap(startDay, endDay, temp.get(choosenItemIndex));
            }

            if (choosenmember.getCash() < temp.get(choosenItemIndex).getCostDay()
                * (endDay - startDay)) {
              conView.doesNotHaveFunds(temp.get(choosenItemIndex).getCostDay()
                  * (endDay - startDay),
                  choosenmember.getName(), choosenmember.getCash());
            }

            Contract newCon = conView.newContract(startDay, endDay, choosenmember, temp.get(choosenItemIndex));
            man.addContract(newCon);
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          case ListContract:
            for (Contract i : man.getContractlst()) {
              conView.infoAboutContract(i.getReceiver().getName(), i.getItem().getName(), i.getStartDay(),
                  i.getEndDay());
            }
            menu(scan, man, memView, itemView, conView, menuView);
            break;
          default:
            break;
        }
        break;
      case TimeMenu:
        man.setToDay(man.getToDay() + 1);
        dailyPay(man);
        menu(scan, man, memView, itemView, conView, menuView);
        break;
      case Quit:
        break;
      default:
        itemView.errorMessage();
        menu(scan, man, memView, itemView, conView, menuView);
        break;
    }

  }

  private int choosenItemCheck(Scanner scan, Manager man, Member choosenMember, ItemInterfaceView itemView) {
    for (ItemObject i : choosenMember.getOwnItems()) {
      itemView.choosenMemberItems(choosenMember.getOwnItems().indexOf(i),
          choosenMember.getOwnItems().get(choosenMember.getOwnItems().indexOf(i)).getName());
    }
    Integer itemIndex = Integer.parseInt(scan.nextLine());
    while (itemIndex > choosenMember.getOwnItems().size() - 1 || itemIndex < 0) {
      itemView.errorMessage();
      itemIndex = Integer.parseInt(scan.nextLine());
    }
    return itemIndex;
  }

  private Member chooseMemberCheck(Scanner scan, Manager man, MemberInterfaceView memberView,
      ContractInterfaceView conView, MenuInterfaceView menuView) {
    ArrayList<Member> sortedMemberList = memberView.getSortedMemberList(man);
    for (Member i : sortedMemberList) {
      int index = sortedMemberList.indexOf(i);
      String name = i.getName();
      String id = i.getId();
      memberView.indexAndNameAndId(index, name, id);
    }
    int choosenMemberIndex = Integer.parseInt(scan.nextLine());
    while (choosenMemberIndex > sortedMemberList.size() - 1 || choosenMemberIndex < 0) {
      memberView.errorMessage();
      choosenMemberIndex = Integer.parseInt(scan.nextLine());
    }
    Member choosenMember = sortedMemberList.get(choosenMemberIndex);
    return choosenMember;
  }

  private void dailyPay(Manager man) {
    for (Contract c : man.getContractlst()) {
      if (c.getStartDay() <= man.getToDay() && c.getEndDay() >= man.getToDay()) {
        c.getReceiver().addCash(-c.getPriceDay());
        c.getLender().addCash(c.getPriceDay());
      }
    }
  }

  private void createItem(Member choosenMember, Manager man, ItemObject newItem, Scanner scan,
      ItemInterfaceView itemView) {
    while (choosenMember.addItem(newItem) == false) {
      itemView.errorMessageDup();
      newItem.setName(itemView.newName(scan), choosenMember);
    }
    man.addItem(newItem);
  }
}
