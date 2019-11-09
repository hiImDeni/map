package Model;

public class boolValue implements Value {
    boolean val;
    public boolValue(boolean v) { val = v; }

    public boolean getValue() { return val; }

    @Override
    public String toString(){
        return "" + val;
    }

    @Override
    public Type getType() { return new boolType(); }
}
