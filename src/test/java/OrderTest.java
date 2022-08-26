import helpers.Country;
import helpers.CurrentTime;
import items.Bread;
import items.Vegetable;
import items.beers.Beer;
import order.Order;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void testCreationOfReceiptFromOrder(){
        Order order = new Order();

        order.addItem(Beer.fromCountry(Country.NETHERLANDS));
        order.addItem(new Bread(LocalDate.of(2022, 8, 18)));
        order.addItem(new Vegetable(100));

        String OutputResult = order.receipt().printAll();

        String expectedResult = "BEER_NETHERLANDS x 1 = 5.00 EUR\n" +
                "BREAD_2022-08-18 x 1 = 1.80 EUR\n" +
                "VEGETABLE x 1 = 1.71 EUR\n" +
                "Total = 8.51 EUR";

        assertEquals(expectedResult, OutputResult);
    }

    @Test
    void testAddingExpiredBread(){
        Order order = new Order();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            order.addItem(new Bread(LocalDate.of(2022, 8, 5)));
        });

        String expectedMessage = "This item is expired !";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
