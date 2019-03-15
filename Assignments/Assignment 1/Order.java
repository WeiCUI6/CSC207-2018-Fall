package warehouse.orders;

import warehouse.TranslationTable;

import java.util.Arrays;

/**
 * An order from the automotive factory. Contains two SKUs.
 */
public class Order {

    /**
     * The front and back SKUs, in that order.
     */
    private final String[] skus;
    /**
     * The unique order number.
     */
    private final int orderID;

    /**
     * A new order for a vehicle with colour and model.
     *
     * @param orderID the unique order number
     * @param colour  the colour
     * @param model   the model
     */
    public Order(int orderID, String colour, String model, TranslationTable translationTable) {
        this.orderID = orderID;
        skus = translationTable.getSKUs(colour, model);
    }

    /**
     * Return the front SKU for this order.
     *
     * @return the front SKU
     */
    String getFrontSKU() {
        return skus[0];
    }

    /**
     * Return the back SKU for this order.
     *
     * @return the back SKU
     */
    String getBackSKU() {
        return skus[1];
    }

    @Override
    public String toString() {
        return "Order{" +
                "skus=" + Arrays.toString(skus) +
                ", orderID=" + orderID +
                '}';
    }
}
