package toolkit;

public class Tool {
    public boolean isNumeric(String str){
        for (char c: str.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
