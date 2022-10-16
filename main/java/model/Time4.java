package model;

/** Time for the system.*/
public class Time4 {
  private int currentDay;

  /** Time object constructor.*/
  public Time4() {
    this.currentDay = 0;
  }

  public int getCurrentDay() {
    return currentDay;
  }

  /**Advances the day by one. */
  public int advanceDay() {
    return this.currentDay++;
  }
}
