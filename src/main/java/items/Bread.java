package items;

import helpers.CurrentTime;
import helpers.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Bread implements Item{

    public final LocalDate productionTime;
    public final static Integer BREAD_MAX_AGE = 6;
    public final static Double BREAD_PRICE_EUR = 1.8;

    public Bread(LocalDate productionTime) {
        this.productionTime = productionTime;
    }

    @Override
    public String getId() {
        return getType().name() + "_" + productionTime.toString() ;
    }

    @Override
    public Money getPrice() {
        return new Money(BigDecimal.valueOf(BREAD_PRICE_EUR));
    }

    @Override
    public ItemType getType() {
        return ItemType.BREAD;
    }

    @Override
    public void verifyItem() {
        if (age() > BREAD_MAX_AGE) {
            throw new RuntimeException("This item is expired !");
        }
    }

    /**
     * Time difference in days between the production time of the bread and the current time
     * @return age of bread in days as Integer
     */
    public Integer age() {
        return Period.between(productionTime, CurrentTime.now()).getDays();
    }
}
