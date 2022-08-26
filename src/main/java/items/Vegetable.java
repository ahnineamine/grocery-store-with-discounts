package items;

import helpers.Money;

import java.math.BigDecimal;

public class Vegetable implements Item{
    public final static Double VEGETABLE_PRICE_EUR_FOR_100G = 1.8;
    // Weight of the vegetables ordered in grams
    public final Integer weight;

    public Vegetable(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String getId() {
        return getType().name();
        //return getType().name() + "_" + weight.toString();
    }

    @Override
    public Money getPrice() {
        double totalPrice = (VEGETABLE_PRICE_EUR_FOR_100G * weight) / 100 ;
        return new Money(BigDecimal.valueOf(totalPrice));
    }

    @Override
    public ItemType getType() {
        return ItemType.VEGETABLE;
    }

    @Override
    public void verifyItem() {
    }
}
