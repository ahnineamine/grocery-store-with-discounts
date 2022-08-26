package price;

import helpers.Money;
import items.beers.Beer;
import order.OrderedItem;

public class SixPackDiscount extends DefaultPrice{

    public SixPackDiscount(OrderedItem orderedItem) {
        super(orderedItem);
    }

    @Override
    public Money getPrice() {
        Money currentPrice = super.getPrice();
        int  numberOfSixPacksOrdered = super.quantity/6;
        Beer itemToBeer = (Beer) super.item;
        Money discountValue = itemToBeer.getPackDiscount().multiply(numberOfSixPacksOrdered);
        currentPrice.subtract(discountValue);
        return currentPrice;
    }

}
