package warehouse.orders;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of 8 SKUs in the order they should be sequenced, and a counter to make sure that happens.
 */
public class SequencerOrderList {

    /**
     * The list of skus in sequencing order.
     */
    private List<String> sequencerSKUOrder = new ArrayList<>();

    /**
     * The index of the next SKU to process.
     */
    private int nextSKUToScan = 0;

    /**
     * A sequential order number.
     */
    private int orderID;


    /**
     * The unique sequential order id for this set of orders.
     *
     * @param orderID the unique order id
     */
    public SequencerOrderList(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Add the front and back SKUs to this list.
     *
     * @param o the order from which to extract the SKUs
     */
    public void addOrder(Order o) {
        sequencerSKUOrder.add(o.getFrontSKU());
        sequencerSKUOrder.add(o.getBackSKU());
    }

    /**
     * Return how many SKUs are in the list.
     *
     * @return the number of SKUs.
     */
    public int size() {
        return sequencerSKUOrder.size();
    }

    /**
     * Return whether sku is the next SKU to process
     *
     * @param sku the next expected SKU
     * @return whether sku matches the next SKU to process
     */
    public boolean sequencerSKUMatches(String sku) {
        return sku.equals(sequencerSKUOrder.get(nextSKUToScan));
    }

    /**
     * Process the scanning of sku.
     * Precondition: sku is the next SKU to process.
     */
    public void processSKU(String sku) {
        assert sequencerSKUMatches(sku);
        nextSKUToScan++;
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
        return "SequencerOrderList{" +
                "skus=" + sequencerSKUOrder +
                ", nextSKUToScan=" + nextSKUToScan +
                ", orderID=" + orderID +
                '}';
    }

    /**
     * Return the ids in this order list.
     *
     * @return the ids in this order list.
     */
    public String[] getIDs() {
        String[] result = new String[sequencerSKUOrder.size()];
        int i = 0;
        for (String s : sequencerSKUOrder) {
            result[i++] = s;
        }
        return result;
    }
}
