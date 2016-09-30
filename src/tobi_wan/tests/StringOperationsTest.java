package tobi_wan.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import tobi_wan.support.StringOperations;



public class StringOperationsTest {
   private static StringOperations so;

   @BeforeClass
   public static void initialise() {
      so = new StringOperations();
   }

   @Test
   public void stringIsNumber() {
      assertTrue(so.stringIsNumber("0"));
      assertFalse(so.stringIsNumber("x"));
   }

   @Test
   public void parseStringToInt() {
      assertEquals(so.parseStringToInt("33"), 33);
   }

   @Test
   public void makeRandomString() {
      assertEquals(so.makeRandomString(33).length(), 33);
      assertTrue(so.makeRandomString(33).getClass().equals(String.class));
   }

   @Test
   public void cleanUpString() {
      assertEquals("Ae", so.cleanUpString("Ä"));
      assertEquals("Oe", so.cleanUpString("Ö"));
      assertEquals("Ue", so.cleanUpString("Ü"));
   }

   @Test
   public void stringsAreEqual() {
      String a = "abc";
      String b = "abc";
      assertEquals(a.equals(b), so.stringsAreEqual(a, b));
   }
}
