package warehouse;

import warehouse.orders.PickerOrderList;

/**
 * A mock version of the generic shortest path software.
 */
class GenericPathSoftware {

    /**
     * Rearrange the items in the order list so that they are in picking order.
     * This is a stub. TODO: hook this up to the actual generic software.
     * Right now, we'll just leave the order as it is.
     * @param pickerOrderList the order list to rearrange
     */
    static void setOrder(PickerOrderList pickerOrderList) {
        System.out.println("-> Order list set by generic software.");
    }
}
