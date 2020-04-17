import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiTest {

    @Test
    public void currentDateTest() {
        System.out.println("Execution of currentDateTest");
        String expectedDate = "17 April 2020";
        String actualDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM YYYY"));

        Assert.assertEquals(actualDate, expectedDate, "date is wrong");
    }



}
