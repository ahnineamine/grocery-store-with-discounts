package items.beers;

import helpers.Country;
import helpers.Money;
import items.Item;
import items.ItemType;

import java.util.UUID;

public class Beer implements Item {
    // origin country of the beer
    private Country origin;
    // price of 1 beer can
    private Money price;
    // the discounted priced of 1 beer can when bought in a 6 pack
    private Money packDiscount;

    Beer() {
    }

    public static Beer fromCountry(Country origin){
        return BeerParser.load(origin);
    }


    @Override
    public String getId() {
        return getType().name() + "_" + getOrigin().name() ;
    }

    @Override
    public Money getPrice() {
        return price;
    }

    @Override
    public ItemType getType() {
        return ItemType.BEER;
    }

    @Override
    public void verifyItem() {
    }

    public Country getOrigin() {
        return origin;
    }

    public void setOrigin(Country origin) {
        this.origin = origin;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Money getPackDiscount() {
        return packDiscount;
    }

    public void setPackDiscount(Money packDiscount) {
        this.packDiscount = packDiscount;
    }
}
