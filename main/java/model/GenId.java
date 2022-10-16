package model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/** Class for generating a random ID from an alpha numeric string.*/
public class GenId {
  private ArrayList<String> allIds = new ArrayList<String>();

  /** Method responsible for generating a random Unique ID.*/
  public String getAlphaNumericString(int n) {
    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
    StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      int index = ThreadLocalRandom.current().nextInt(0, alphaNumericString.length()); 
      sb.append(alphaNumericString.charAt(index));
    }
    for (int i = 0; i < allIds.size(); i++) {
      if (allIds.get(i).equals(sb.toString())) {
        getAlphaNumericString(6);
      }
    }
    allIds.add(sb.toString());
    return sb.toString();
  }
}




  