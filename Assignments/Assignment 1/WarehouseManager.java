package warehouse;

import warehouse.orders.PickerOrderList;
import warehouse.orders.SequencerOrderList;
import warehouse.workers.Picker;
import warehouse.workers.Sequencer;

import java.util.*;

/**
 * Manage events in the warehouse.
 */
public class WarehouseManager {

    /**
     * The orders that are not yet assigned to a picker.
     */
    private Queue<PickerOrderList> pendingPickingOrders = new LinkedList<>();
    /**
     * The orders at marshaling that are not yet assigned to the sequencer.
     */
    private List<SequencerOrderList> pendingSequencingOrders = new ArrayList<>();
    /**
     * All sequencing order list id to sequencing orders.
     */
    private Map<Integer, SequencerOrderList> allSequencingOrderLists = new HashMap<>();
    /**
     * The index of the id of the next order list to sequence, or -1 if there is no next order list.
     */
    private int nextSequencingIDIndex = -1;
    /**
     * The list of order list ids to sequence.
     */
    private List<Integer> sequencingIDs = new ArrayList<>();
    /**
     * The pickers who are ready but do not yet have a picking order.
     */
    private Queue<Picker> availablePickers = new LinkedList<>();
    /**
     * The pickers and the order list they are currently processing.
     */
    private Map<Picker, PickerOrderList> pickersOrders = new HashMap<>();
    /**
     * Look up pickers by name.
     */
    private Map<String, Picker> pickers = new HashMap<>();

    /**
     * There is at most one sequencer ever.
     */
    private Sequencer sequencer;
    /**
     * The order list currently being sequenced; null if there is none.
     */
    private SequencerOrderList sequenceOrderList = null;

    /**
     * Add a new order list to the warehouse. Assign it to a picker if one is available.
     *
     * @param pickerOrderList the new list of orders.
     */
    public void addPickingOrders(PickerOrderList pickerOrderList) {
        GenericPathSoftware.setOrder(pickerOrderList);
        String event;
        if (availablePickers.isEmpty()) {
            pendingPickingOrders.add(pickerOrderList);
            event = String.format("-> Order list %s added.", pickerOrderList);
        } else {
            Picker p = availablePickers.remove();
            pickersOrders.put(p, pickerOrderList);
            event = String.format("-> Picker %s assigned to order list %s.", p.getName(), pickerOrderList);
        }
        System.out.println(event);
    }

    /**
     * Add sequencerOrderList to the list of sequencing lists to be procesed.
     *
     * @param sequencerOrderList the order list to sequence
     */
    public void addSequencingOrders(SequencerOrderList sequencerOrderList) {
        if (nextSequencingIDIndex == -1) nextSequencingIDIndex = sequencerOrderList.getID();
        sequencingIDs.add(sequencerOrderList.getID());
        allSequencingOrderLists.put(sequencerOrderList.getID(), sequencerOrderList);
    }

    /**
     * Add a picker named name to the list of ready pickers.
     * If there is an order list to be processed, assign it to them.
     *
     * @param name the picker name
     */
    public void addPicker(String name) {
        Picker p = new Picker(name);
        pickers.put(name, p);
        String event;
        if (pendingPickingOrders.isEmpty()) {
            availablePickers.add(p);
            event = String.format("-> Picker %s added to ready queue.", name);
        } else {
            PickerOrderList pickerOrderList = pendingPickingOrders.remove();
            pickersOrders.put(p, pickerOrderList);
            event = String.format("-> Picker %s assigned to order list %s.", name, pickerOrderList.getID());
        }
        System.out.println(event);
    }

    /**
     * Process picker named name to pick sku.
     *
     * @param name the picker's name
     * @param sku  the sku to pick
     */
    public void processPickEvent(String name, String sku) {
        Picker p = pickers.get(name);
        PickerOrderList pickerOrderList = pickersOrders.get(p);
        String event;
        if (pickerOrderList.pickerSKUMatches(sku)) {
            pickerOrderList.processSKU(sku);
            event = String.format("-> Picker %s picked %s.", name, sku);
        } else {
            event = String.format("-> Picker %s incorrectly picked %s and put it back.", name, sku);
        }

        System.out.println(event);
    }

    /**
     * Process picker arriving at marshaling: the order list goes to sequencing,
     * and picker named name goes back in the picker queue.
     * <p>
     * Precondition: picker has picked all the orders in their list.
     *
     * @param name the picker to process
     */
    public void pickerToMarshaling(String name) {
        Picker p = pickers.get(name);
        PickerOrderList completedPickerOrderList = pickersOrders.get(p);
        SequencerOrderList sol = allSequencingOrderLists.get(nextSequencingIDIndex);
        pendingSequencingOrders.add(sol);
        System.out.println("-> Picker " + name + " to marshaling for " + completedPickerOrderList);
        System.out.println("-> Sequencer to sequence " + sol);
        String event;
        if (pendingPickingOrders.isEmpty()) {
            availablePickers.add(p);
            event = String.format("-> Picker %s added to ready queue.", name);
        } else {
            PickerOrderList pickerOrderList = pendingPickingOrders.remove();
            pickersOrders.put(p, pickerOrderList);
            event = String.format("-> Picker %s assigned to order list %s.", name, pickerOrderList.getID());
        }

        System.out.println(event);
    }

    /**
     * Add a sequencer named name.
     * Precondition: name is unique and no other sequencers are working.
     *
     * @param name the name of the sequencer
     */
    public void addSequencer(String name) {
        sequencer = new Sequencer(name);
    }

    public void sequencerSequenced(String name) {
        String event;
        if (pendingSequencingOrders.isEmpty()) {
            sequenceOrderList = null;
            nextSequencingIDIndex = -1;
            event = String.format("-> Sequencer ready; no pending sequencing lists.");
        } else {
            nextSequencingIDIndex++;
            sequenceOrderList = allSequencingOrderLists.get(nextSequencingIDIndex);
            event = String.format("-> Sequencer to sequence %s", sequenceOrderList);
        }
    }

    /**
     * Process sequencer named name to sequence sku.
     *
     * @param name the sequencer's name
     * @param sku  the sku to scan
     * @param name
     * @param sku
     */
    public void processScanEvent(String name, String sku) {
        if (name.equals(sequencer.getName())) {
            SequencerOrderList sequencerOrderList = allSequencingOrderLists.get(nextSequencingIDIndex);
            String event;
            if (sequencerOrderList.sequencerSKUMatches(sku)) {
                sequencerOrderList.processSKU(sku);
                event = String.format("-> Sequencer %s scanned %s.", name, sku);
            } else {
                event = String.format("-> Sequencer %s incorrectly scanned %s and put it back.", name, sku);
            }

            System.out.println(event);
        }
    }

}
