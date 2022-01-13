
public class Token {
    String classPart;
   String value;
   int lineNo;
 Token(String value, String classPart, int lineNo){
    this.value=value;
    this.classPart = classPart;
    this.lineNo= lineNo;
    }
    @Override
 public String toString() {
    return "(" + this.value +","+ this.classPart +","+ this.lineNo +")";
}
   
}
