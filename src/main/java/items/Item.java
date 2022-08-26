package items;

import helpers.Money;

public interface Item {
    String getId ();

    Money getPrice();

    ItemType getType();

    void verifyItem();
}
