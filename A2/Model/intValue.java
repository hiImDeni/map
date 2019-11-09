package Model;

public class intValue implements Value{
    int val;
    public intValue(int v) { val = v; }

    int getValue() { return val; }

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() { return new intType(); }
}
