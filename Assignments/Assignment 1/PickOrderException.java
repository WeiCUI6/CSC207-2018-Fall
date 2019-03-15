package warehouse.orders;

public class PickOrderException extends Throwable {
    public PickOrderException(String name, String sku) {
        super(name + " " + sku);
    }
}
