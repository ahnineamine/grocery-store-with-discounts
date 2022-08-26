package helpers;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
//import java.util.Currency;

public class Money {
    // Handle operations on the amounts as double values
    private BigDecimal amount = BigDecimal.ZERO;
    // Currency type of the money used
    private Currency currency = Currency.EUR;
    //private Currency currency = Currency.getInstance(CurrencyType.EUR.toString());

    public Money() {
    }

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }


    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return getAmount().setScale(2, RoundingMode.HALF_EVEN) + " " + getCurrency().name();
    }

    // Operations on the amount

    /**
     * Multiply the amount of a Money object by a given integer number
     * @param multiplyNumber
     * @return The Money object with the new amount
     */
    public Money multiply(int multiplyNumber) {
        return new Money(
                amount.multiply(BigDecimal.valueOf(multiplyNumber))
        );
    }

    /**
     * Multiply the amount of a Money object by a given float number
     * @param multiplyNumber
     * @return The Money object with the new amount
     */
    public Money multiply(float multiplyNumber) {
        return new Money(
                amount.multiply(BigDecimal.valueOf(multiplyNumber))
        );
    }

    /**
     * Add an amount to Money object
     * @param additionalMoney
     */
    public void add (@NotNull Money additionalMoney) {
        amount = amount.add(additionalMoney.amount);
    }

    /**
     * Subtract an amount from Money object
     * @param amountToSubtract
     */
    public void subtract(@NotNull Money amountToSubtract) {
        amount = amount.subtract(amountToSubtract.amount);
    }


    // One could add a currency type such as USD. As long as a function to validate the currencies when adding different Money Amounts is created and used.
    public enum Currency { EUR }
}
