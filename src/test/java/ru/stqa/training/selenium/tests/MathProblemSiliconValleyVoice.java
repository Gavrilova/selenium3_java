package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;
import static java.util.Arrays.sort;

/**
 * Created by irinagavrilova on 3/26/17.
 * Задача:
 * даны 8 неповторяющихся цифр, надо составить из них два четырехзначных числа так,
 * чтобы разница между ними была минимальна.
 */
public class MathProblemSiliconValleyVoice {

  @Test
  public void eigthNumbers() {
    int[] eightArray = {0, 1, 3, 2, 6, 7, 8, 9};
    sort(eightArray);
    int[] arr = new int[6];
    int numberMax = 0;
    int numberMin = 0;
    int difference = Integer.MAX_VALUE;

    for (int i = 0; i < (eightArray.length - 1); i++) {
      if ((eightArray[i + 1] - eightArray[i] == 1) && (eightArray[i] != 0)) {
        subArray(eightArray, arr, i);
        int number1 = eightArray[i + 1] * (1000) + (100) * arr[0] + (10) * arr[1] + arr[2];
        int number2 = (1000) * eightArray[i] + (100) * arr[5] + (10) * arr[4] + arr[3];
        if (difference > (number1 - number2)) {
          difference = (number1 - number2);
          numberMax = number1;
          numberMin = number2;
        }
      }
    }
    System.out.println(numberMax + "-" + numberMin + " = " + (numberMax - numberMin));
  }

  private void subArray(int[] eightArray, int[] arr, int i) {
    int k = 0;
    for (int j = 0; j < 8; j++) {
      if ((j == i) || (j == (i + 1))) {
      } else {
        arr[k] = eightArray[j];
        k++;
      }
    }
  }

  private void printArray(int[] eightArray) {
    for (int i = 0; i < eightArray.length; i++) {
      System.out.print(" " + eightArray[i]);
    }
    System.out.println("\n");
  }

}
