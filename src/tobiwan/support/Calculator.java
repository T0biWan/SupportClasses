package tobiwan.support;

public class Calculator {
   public int decimalToBinary(int decimal) {
      int binary = 0;
      int potenz = 0;

      while (decimal > 0) {
         binary = (int) (binary + ((decimal % 2) * (Math.pow(10, potenz))));
         decimal = decimal / 2;
         potenz++;
      }
      return binary;
   }

   public int binaryToDecimal(int binary) {
      int decimal = 0;
      int potenz = 0;
      while (binary > 0) {
         decimal = decimal + (int) ((binary % 2) * (Math.pow(2, potenz)));
         binary /= 10;
         potenz++;
      }
      return decimal;
   }

   public double calculateAverage(double... values) {
      double average = 0;
      for (double index : values) {
         average += index;
      }
      return average / values.length;
   }

}
