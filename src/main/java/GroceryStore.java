import helpers.Country;
import helpers.CurrentTime;
import items.Bread;
import items.Item;
import items.Vegetable;
import items.beers.Beer;
import order.Order;
import java.util.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroceryStore {
    static final Logger logger = Logger.getLogger(GroceryStore.class.getName());
    public static void main(String[] args) {

        logger.info("creating order number 1");
        Order order1 = new Order();
        // Add Newly made bread x 2
        order1.addItem(new Bread(CurrentTime.now()));
        order1.addItem(new Bread(CurrentTime.now()));
        // Add German beer x 3
        for (int nbrBeers = 0; nbrBeers < 3; nbrBeers ++) {
            order1.addItem(Beer.fromCountry(Country.GERMANY));
        }
        // add 100g of vegetables
        order1.addItem(new Vegetable(100));
        System.out.println("---------Receipt---------");
        System.out.println(order1.receipt().printAll());
        System.out.println("---------End Receipt---------");
        logger.info("receipt for order number 1 has been printed !");

        logger.info("creating order number 2");
        Order order2 = new Order();
        // Add 3 days old bread x 3
        order2.addItem(new Bread(CurrentTime.now().minusDays(3)));
        order2.addItem(new Bread(CurrentTime.now().minusDays(3)));
        order2.addItem(new Bread(CurrentTime.now().minusDays(3)));
        // Add German beer x 3
        for (int nbrBeers = 0; nbrBeers < 3; nbrBeers ++) {
            order2.addItem(Beer.fromCountry(Country.GERMANY));
        }
        // Add Dutch beer x 2
        for (int nbrBeers = 0; nbrBeers < 2; nbrBeers ++) {
            order2.addItem(Beer.fromCountry(Country.NETHERLANDS));
        }
        // add 250g of vegetables
        order2.addItem(new Vegetable(250));
        System.out.println("---------Receipt---------");
        System.out.println(order2.receipt().printAll());
        System.out.println("---------End Receipt---------");
        logger.info("receipt for order number 2 has been printed !");

        logger.info("creating order number 3");
        Order order3 = new Order();
        // Add 6 days old bread x 4
        order3.addItem(new Bread(CurrentTime.now().minusDays(6)));
        order3.addItem(new Bread(CurrentTime.now().minusDays(6)));
        order3.addItem(new Bread(CurrentTime.now().minusDays(6)));
        order3.addItem(new Bread(CurrentTime.now().minusDays(6)));
        // Add Dutch beer x 4
        for (int nbrBeers = 0; nbrBeers < 4; nbrBeers ++) {
            order3.addItem(Beer.fromCountry(Country.NETHERLANDS));
        }
        // add 250g of vegetables
        order3.addItem(new Vegetable(250));
        System.out.println("---------Receipt---------");
        System.out.println(order3.receipt().printAll());
        System.out.println("---------End Receipt---------");
        logger.info("receipt for order number 3 has been printed !");

        logger.info("creating the combined order");
        Order combinedOrder = new Order();
        List<Item> combinedItems = Stream.concat(Stream.concat(order1.getItems().stream(),order2.getItems().stream()),order3.getItems().stream()).collect(Collectors.toList());
        for(Item item : combinedItems) {
            combinedOrder.addItem(item);
        }
        System.out.println("---------Receipt---------");
        System.out.println(combinedOrder.receipt().printAll());
        System.out.println("---------End Receipt---------");
        logger.info("receipt for combined order has been printed !");

    }
}
