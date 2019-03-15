package warehouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The translation table of colour/model to front and back SKUs.
 */
public class TranslationTable {

    /**
     * (colour, name) to [front SKU, back SKU]
     */
    Map<String, String[]> translationTable = new HashMap();

    /**
     * Read the translation table from path filename.
     *
     * @param filename the path to the translation table.
     */
    public TranslationTable(String filename) {
        // Make the file
        File f = new File(filename);
        // Get ready to scan.
        Scanner scanner;
        try {
            // connect the scanner
            scanner = new Scanner(f);
            // Skip the first header line.
            String nextLine = scanner.nextLine();
            while (scanner.hasNext()) {
                nextLine = scanner.nextLine();
                String[] line = nextLine.split(",");
                String colourModel = line[0] + " " + line[1];
                translationTable.put(colourModel, new String[]{line[2], line[3]});
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the front and back SKUs for the (colour, model) pair.
     *
     * @param colour the colour
     * @param model  the model
     * @return the pair of SKUs, front and back
     */
    public String[] getSKUs(String colour, String model) {
        String colourModel = colour + " " + model;
        return translationTable.get(colourModel);
    }
}
