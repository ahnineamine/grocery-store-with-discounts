package order;

import helpers.Money;
import items.Item;
import items.Vegetable;
import price.PriceStrategy;
import price.PriceStrategyCalculation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Receipt {

    private final List<OrderedItem> orderedItems;

    public Receipt(List<Item> items) {
        orderedItems = createOrderedItems(items);
    }

    /**
     * create orderedItems from Items
     * @param items
     * @return list of [OrderedItem]
     */
    private List<OrderedItem> createOrderedItems(List<Item> items) {
        // Group items by Id
        Map<String, List<Item>> itemsGroupedById = items.stream().collect(Collectors.groupingBy(Item::getId));
        // create OrderedItem for every group of items
        return itemsGroupedById.values().stream().map(this:: itemsToOrderedItem).collect(Collectors.toList());
    }

    private OrderedItem itemsToOrderedItem (List<Item> items) {
        if (items.get(0) instanceof Vegetable) {
            List<Vegetable> vegetableList = items.stream().map(element -> (Vegetable) element).collect(Collectors.toList());
            Vegetable allVegetable = new Vegetable(vegetableList.stream().map(vegetable -> vegetable.weight).reduce(0, Integer::sum));
            return new OrderedItem(allVegetable, 1);
        }
        return new OrderedItem(items.get(0), items.size());
    }

    /**
     * Print the details of the purchase of the ordered item (ex: Bread x 5 = 9)
     * @param orderedItem
     * @return String representing the purchased item.
     */
    public String printItem(OrderedItem orderedItem) {
        return orderedItem.item.getId() + " x " + orderedItem.quantity + " = " + PriceStrategyCalculation.execute(orderedItem).getPrice();
    }

    /**
     * Print receipt containing all the items of an order
     * @return receipt in String Format
     */

    public String printAll() {
        String printedItems =  orderedItems
                .stream()
                .map(this::printItem)
                .sorted()
                .collect(Collectors.joining("\n"));
        
        return printedItems + "\n" + "Total = " + totalPrice();
    }

    /**
     * calculate total price ot be paid for the order
     * @return [Money] with the full amount of the order
     */
    private Money totalPrice() {
        Money money = new Money();
        orderedItems.stream()
                .map(PriceStrategyCalculation::execute)
                .map(PriceStrategy::getPrice).forEach(money::add);
        return money;
    }
}
