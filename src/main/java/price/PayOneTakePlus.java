package price;

import helpers.Money;
import items.Item;
import order.OrderedItem;

public class PayOneTakePlus implements PriceStrategy{

    private final Item item;
    private final Integer quantity;
    private final Integer plus;

    public PayOneTakePlus(OrderedItem orderedItem, Integer plus){
        this.item = orderedItem.item;
        this.quantity = orderedItem.quantity;
        this.plus = plus;
    }

    @Override
    public Money getPrice() {
        int itemNotIncludedInDiscount = quantity % plus;
        int itemsToPayFor = (quantity - itemNotIncludedInDiscount) / plus;
        int totalItemsToPayFor = itemNotIncludedInDiscount + itemsToPayFor;
        return item.getPrice().multiply(totalItemsToPayFor);
    }
}
