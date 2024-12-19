package Project.Interfaces;

public interface Checker<t> {
    default  String checkType(t input){
        if (input instanceof Integer){
            return "int";
        }
        else if (input instanceof String){
            return "String";
        }
        else if (input instanceof Double){
            return "double";
        }
        return "object";
    }
}
