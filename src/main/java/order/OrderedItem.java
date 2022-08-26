package order;

import items.Item;

public class OrderedItem {
    public Item  item;
    public Integer quantity;

    public OrderedItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
