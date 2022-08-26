import helpers.Country;
import helpers.CurrentTime;
import items.Bread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import items.Vegetable;
import items.beers.Beer;
import order.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;

public class DiscountTest {

    @Test
    void testDiscountCalculation(){
        try (MockedStatic<CurrentTime> mockedTime = mockStatic(CurrentTime.class)) {
            mockedTime.when(CurrentTime::now).thenReturn(LocalDate.of(2022, 8, 18));
        }
        Order order = new Order();
        // 6 pack beer discount
        addSixPack(order);
        // take 2 pay 1 discount
        order.addItem(new Bread(LocalDate.of(2022, 8, 15)));
        order.addItem(new Bread(LocalDate.of(2022, 8, 15)));
        // take 3 pay 1 discount
        order.addItem(new Bread(LocalDate.of(2022, 8, 12)));
        order.addItem(new Bread(LocalDate.of(2022, 8, 12)));
        order.addItem(new Bread(LocalDate.of(2022, 8, 12)));
        // 600 grans of vegetable
        order.addItem(new Vegetable(50));
        order.addItem(new Vegetable(250));
        order.addItem(new Vegetable(300));

        String OutputResult = order.receipt().printAll();

        String expectedResult = "BEER_BELGIUM x 6 = 21.00 EUR\n" +
                "BREAD_2022-08-12 x 3 = 1.80 EUR\n" +
                "BREAD_2022-08-15 x 2 = 1.80 EUR\n" +
                "VEGETABLE x 1 = 9.72 EUR\n" +
                "Total = 34.32 EUR";

        assertEquals(expectedResult, OutputResult);


    }

    private void addSixPack(Order order){
        for (int nbrBeers = 0; nbrBeers < 6; nbrBeers ++) {
            order.addItem(Beer.fromCountry(Country.BELGIUM));
        }
    }
}
