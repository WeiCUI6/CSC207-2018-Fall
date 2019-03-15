package warehouse.orders;

import warehouse.workers.Picker;

import java.util.*;

/**
 * Manage the orders, including ensuring that they are processed in the correct order.
 */
public class OrderListManager {

    /**
     * The next orders ID to process.
     */
    private int nextOrdersIDToProcess = 0;

    /**
     * The set of orders yet to be processed.
     */
    private Set<PickerOrderList> orders = new HashSet<>();

    /**
     * The completed orders, in case we need it.
     */
    private List<PickerOrderList> completedOrders = new ArrayList();

    /**
     * The pickers currently on the floor.
     */
    private Set<Picker> pickers = new HashSet();

    /**
     * Add the list of orders to the order being managed.
     *
     * @param pickerOrderList the new list of orders
     */
    public void addOrders(PickerOrderList pickerOrderList) {
        orders.add(pickerOrderList);
    }

    /**
     * Return whether the sku picked by name was expected.
     *
     * @param name the picker name
     * @param sku  the ID the picker picks
     */
    public boolean pickExpected(String name, String sku) {
        return false;
    }

    /**
     * Record that name has picked sku.
     * <p>
     * Prerequisite: pickExpected(name, sku)
     *
     * @param name the picker name
     * @param sku the SKU the picker is picking
     */
    public void pick(String name, String sku) throws PickOrderException {
        if (!(pickExpected(name, sku))) {
            throw new PickOrderException(name, sku);
        }
    }
}