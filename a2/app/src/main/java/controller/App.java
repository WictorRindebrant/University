package controller;

import java.util.Scanner;
import model.Loader;
import model.Manager;
import view.ContractEngView;
import view.ContractInterfaceView;
import view.ContractSweView;
import view.ItemEngView;
import view.ItemInterfaceView;
import view.ItemSweView;
import view.MemberEngView;
import view.MemberInterfaceView;
import view.MemberSweView;
import view.MenuEngView;
import view.MenuInterfaceView;
import view.MenuSweView;

/**
 * Responsible for staring the application.
 */
public class App {

  /**
   * Application starting point.
   *
   * @param args command line arguments.
   *
   */
  public static void main(String[] args) {
    // adapt to start the application in your way
    Scanner scan = new Scanner(System.in, "utf-8");
    Manager man = new Manager();

    // English language
    MemberInterfaceView memberView = new MemberEngView();
    ItemInterfaceView itemView = new ItemEngView();
    ContractInterfaceView contractView = new ContractEngView();
    MenuInterfaceView menuView = new MenuEngView();

    //Swedish language
    // MemberInterfaceView memberView = new MemberSweView();
    // ItemInterfaceView itemView = new ItemSweView();
    // ContractInterfaceView contractView = new ContractSweView();
    // MenuInterfaceView menuView = new MenuSweView();

    // Handles all the hardcoded stuff
    Loader loader = new Loader();
    loader.loadEverything(man, scan);

    Menu m = new Menu();
    m.menu(scan, man, memberView, itemView, contractView, menuView);
  }
}