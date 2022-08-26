package price;

import helpers.Money;
import items.Item;
import order.OrderedItem;

public class DefaultPrice implements PriceStrategy{

    protected final Item item ;
    protected final Integer quantity ;

    public DefaultPrice(OrderedItem orderedItem) {
        this.item = orderedItem.item;
        this.quantity = orderedItem.quantity;
    }

    @Override
    public Money getPrice() {
        return item.getPrice().multiply(quantity);
    }
}
