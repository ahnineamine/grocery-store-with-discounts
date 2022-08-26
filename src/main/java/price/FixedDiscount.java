package price;

import helpers.Money;
import items.Item;
import order.OrderedItem;

import java.math.BigDecimal;

public class FixedDiscount implements PriceStrategy{
    private final Item item;
    private final Integer quantity;
    private final Integer discountPercentage;

    public FixedDiscount(OrderedItem orderedItem, Integer discountPercentage){
        this.item = orderedItem.item;
        this.quantity = orderedItem.quantity;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public Money getPrice() {
        Money currentPrice =  item.getPrice();
        Money discountAmount = item.getPrice().multiply(discountPercentage/100.0f);
        currentPrice.subtract(discountAmount);
        return currentPrice.multiply(quantity);
    }
}
