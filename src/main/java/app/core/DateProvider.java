package app.core;

import java.time.LocalDate;

public class DateProvider {

  public int getMonthNumber(){
    return LocalDate.now().getMonthValue();
  }

  public int getDayOfMonthNumber(){
    return LocalDate.now().getMonthValue();
  }

}
