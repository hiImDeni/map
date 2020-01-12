package Model;


public class heapElement {
    private Integer address;
    private String value;

    public heapElement(int addr, String v) { address = addr; value = v; }

    public int getAddress() {
        return address;
    }

    public String getValue() {
        return value;
    }
}
