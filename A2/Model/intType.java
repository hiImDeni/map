package Model;

public class intType implements Type {
    @Override
    public boolean equals(Object obj) {
        if (obj  instanceof  intType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public Value defaultValue() { return new intValue(0); }
}
