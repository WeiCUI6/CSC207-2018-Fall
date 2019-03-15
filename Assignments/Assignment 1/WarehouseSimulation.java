package simulation;

import warehouse.TranslationTable;
import warehouse.WarehouseManager;
import warehouse.orders.Order;
import warehouse.orders.PickerOrderList;
import warehouse.orders.SequencerOrderList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Simulate interactions with the warehouse.
 */
public class WarehouseSimulation {

    /**
     * The name of the events file.
     */
    private final static String EVENT_FILENAME = "A1/events.txt";
    /**
     * The name of the translation table file.
     */
    private final static String TRANSLATION_FILENAME = "A1/translation.csv";

    /**
     * Start the simluation, reading events from warehouseEventFile. Valid events are:
     * <p>
     * Order COLOUR MODEL
     * Picker NAME ready
     * Picker NAME pick NUMBER  <-- NUMBER is 1–8
     * Picker NAME to Marshaling
     * Sequencer NAME ready
     * Sequencer NAME scan SKU
     * Loader NAME loads
     *
     * @param warehouseManager the warehouseManager
     * @param translationTable the translation table
     */
    private void start(
            WarehouseManager warehouseManager, TranslationTable translationTable) {
        try {
            Scanner eventScanner = new Scanner(new File(WarehouseSimulation.EVENT_FILENAME));
            // The next (sequential) id for the list of orders.
            int nextOrdersID = 0;
            // The current set of orders. When 8 SKUs are here, we can start picking.
            PickerOrderList pickerOrderList = new PickerOrderList(nextOrdersID);
            SequencerOrderList sequencerOrderList = new SequencerOrderList(nextOrdersID++);
            while (eventScanner.hasNext()) {
                String nextEvent = eventScanner.nextLine();
                System.out.println(String.format("Event: %s", nextEvent));
                if (nextEvent.startsWith("Order")) {
                    String[] orderParts = nextEvent.split(" ");
                    Order order = new Order(nextOrdersID++, orderParts[2], orderParts[1], translationTable);
                    pickerOrderList.addOrder(order);
                    sequencerOrderList.addOrder(order);
                    // When we've reached a full set of 8 orders, send it off be picked.
                    if (pickerOrderList.size() == 8) {
                        warehouseManager.addPickingOrders(pickerOrderList);
                        warehouseManager.addSequencingOrders(sequencerOrderList);
                        pickerOrderList = new PickerOrderList(nextOrdersID);
                        sequencerOrderList = new SequencerOrderList(nextOrdersID++);
                    }
                } else if (nextEvent.startsWith("Picker")) {
                    String[] eventStuff = nextEvent.split(" ");
                    String name = eventStuff[1];
                    if (nextEvent.endsWith("ready")) {
                        warehouseManager.addPicker(name);
                    } else if (nextEvent.endsWith("marshaling")) {
                        // to the marshaling area!
                        warehouseManager.pickerToMarshaling(name);
                    } else {
                        // It's a pick event.
                        String sku = eventStuff[3];
                        warehouseManager.processPickEvent(name, sku);
                    }
                } else if (nextEvent.startsWith("Sequencer")) {
                String[] eventStuff = nextEvent.split(" ");
                String name = eventStuff[1];
                if (nextEvent.endsWith("ready")) {
                    warehouseManager.addSequencer(name);
                } else if (nextEvent.endsWith("sequences")) {
                    warehouseManager.sequencerSequenced(name);
                } else {
                    // It's a scan event.
                    String sku = eventStuff[3];
                    warehouseManager.processScanEvent(name, sku);
                }
            }

        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the whole thing.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        WarehouseManager warehouseManager = new WarehouseManager();
        TranslationTable translationTable = new TranslationTable(TRANSLATION_FILENAME);

        WarehouseSimulation simulation = new WarehouseSimulation();
        simulation.start(warehouseManager, translationTable);
    }

}
