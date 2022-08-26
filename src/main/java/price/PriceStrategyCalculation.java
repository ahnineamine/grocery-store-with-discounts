package price;

import items.Bread;
import items.Item;
import items.ItemType;
import items.Vegetable;
import order.OrderedItem;

public class PriceStrategyCalculation {

    public static PriceStrategy execute(OrderedItem orderedItem) {
        Item item = orderedItem.item;
        if (item.getType() == ItemType.BEER){
           return executeBeerStrategy(orderedItem);
        } else if (item.getType() == ItemType.BREAD) {
            return executeBreadStrategy(orderedItem);
        } else {
            return executeVegetableStrategy(orderedItem);
        }
    }

    /**
     * execute the discount strategy for beer
     * @param orderedItem
     * @return price strategy outcome
     */
    private static PriceStrategy executeBeerStrategy(OrderedItem orderedItem) {
        if (orderedItem.quantity >= 6) {
            return new SixPackDiscount(orderedItem);
        }else {
            return new DefaultPrice(orderedItem);
        }
    }

    /**
     * execute the discount strategy for bread
     * @param orderedItem
     * @return price strategy outcome
     */
    private static PriceStrategy executeBreadStrategy(OrderedItem orderedItem) {
        Bread item = (Bread) orderedItem.item;
        // An error is automatically launched when the age of the bread is more than 6 days
        if (item.age() > 1 && item.age() <= 3) {
            return  new PayOneTakePlus(orderedItem, 2);
        } else if(item.age() <= 6) {
            return  new PayOneTakePlus(orderedItem, 3);
        } else {
            return new DefaultPrice(orderedItem);
        }
    }

    /**
     * execute the discount strategy for vegetable
     * @param orderedItem
     * @return price strategy outcome
     */
    private static PriceStrategy executeVegetableStrategy(OrderedItem orderedItem) {
        Vegetable item = (Vegetable) orderedItem.item;
        if (item.weight <= 100) {
            return new FixedDiscount(orderedItem, 5);
        } else if (item.weight <= 500) {
            return new FixedDiscount(orderedItem, 7);
        } else {
            return new FixedDiscount(orderedItem, 10);
        }
    }
}
