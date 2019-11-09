package Model;

public class boolType implements Type {
    @Override
    public boolean equals(Object obj){
        if (obj instanceof boolType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "bool";
    }

    @Override
    public Value defaultValue() { return new boolValue(false); }
}
