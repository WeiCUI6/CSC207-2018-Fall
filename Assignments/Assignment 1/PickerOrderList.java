package warehouse.orders;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of 8 SKUs in the order they should be processed, and a counter to make sure that happens.
 */
public class PickerOrderList {

    /**
     * The list of skus in picking order.
     */
    private List<String> pickerSKUOrder = new ArrayList<>();

    /**
     * The index of the next SKU to process.
     */
    private int nextSKUToPick = 0;

    /**
     * A sequential order number.
     */
    private int orderID;


    /**
     * The unique sequential order id for this set of orders.
     *
     * @param orderID the unique order id
     */
    public PickerOrderList(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Add the front and back SKUs to this list.
     *
     * @param o the order from which to extract the SKUs
     */
    public void addOrder(Order o) {
        pickerSKUOrder.add(o.getFrontSKU());
        pickerSKUOrder.add(o.getBackSKU());
    }

    /**
     * Return how many SKUs are in the list.
     *
     * @return the number of SKUs.
     */
    public int size() {
        return pickerSKUOrder.size();
    }

    /**
     * Return whether sku is the next SKU to process
     *
     * @param sku the next expected SKU
     * @return whether sku matches the next SKU to process
     */
    public boolean pickerSKUMatches(String sku) {
        return sku.equals(pickerSKUOrder.get(nextSKUToPick));
    }

    /**
     * Process the picking of sku.
     * Precondition: sku is the next SKU to process.
     */
    public void processSKU(String sku) {
        assert pickerSKUMatches(sku);
        nextSKUToPick++;
    }

    /**
     * Return this order list's unique id.
     *
     * @return this order list's unique id
     */
    public int getID() {
        return orderID;
    }

    @Override
    public String toString() {
        return "PickerOrderList{" +
                "skus=" + pickerSKUOrder +
                ", nextSKUToPick=" + nextSKUToPick +
                ", orderID=" + orderID +
                '}';
    }

    /**
     * Return the ids in this order list.
     *
     * @return the ids in this order list.
     */
    public String[] getIDs() {
        String[] result = new String[pickerSKUOrder.size()];
        int i = 0;
        for (String s : pickerSKUOrder) {
            result[i++] = s;
        }
        return result;
    }
}
