package controller;

/** Responsible for staring the application.*/
public class App {
  /** Application starting point.*/
  public static void main(String[] args) {
    model.domain.StuffSys s = new model.domain.StuffSys();
    view.ConsoleUi c = new view.ConsoleUi();
    view.Output o = new view.Output();
    Menu m = new Menu(c, s, o);

    m.startStuffSys();
  }
}
