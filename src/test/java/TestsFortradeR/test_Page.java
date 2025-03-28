package TestsFortradeR;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class test_Page extends BaseFortradeR {

    @BeforeMethod
     public void setup(){
         baseSetup("Edge","134");
     }
    @Test
     public void visitPage(){
         driver.get("https://www.google.com");
     }
    @AfterMethod
     public void tearDown(){
        baseTearDown();
     }
}
