package order;

import items.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<Item> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(@NotNull Item item) {
        item.verifyItem();
        items.add(item);
    }

    public Receipt receipt() {
        return new Receipt(items);
    }

    public List<Item> getItems() {
        return items;
    }
}
