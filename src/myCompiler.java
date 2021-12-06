
import Utili.myUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class myCompiler {

    ArrayList<String> words = new ArrayList();
    ArrayList<Token> tokenList = new ArrayList<>();
    String[][] keywords = {
        {"for", "for"},
         {"extends", "extends"},
        {"boolean", "dataType"},
        {"int", "dataType"},
       
        {"string", "dataType"},
        {"double", "dataType"},
        {"char", "dataType"},
        {"class", "class"},
        {"void", "void"},
        {"do", "do"},
        {"while", "while"},
        {"if", "if"},
        {"else", "else"},
        {"return", "return"},
        {"public", "accessModifier"},
        {
            "private",
            "accessModifier"
        },
        {
            "protected",
            "accessModifier"
        },
        {
            "static",
            "static"
        },
        {
            "abstract",
            "abstract"
        }, {
            "final",
            "final"
        },
        {
            "new",
            "new"
        },
        {
            "this",
            "this"
        },
        {
            "super",
            "super"
        },
        {"enum", "enum"}

    };
    public String[][] operators = {
        {"+", "pm"},
        {"-", "pm"},
        {"*", "mdm"},
        {"/", "mdm"},
        {"%", "mdm"},
        {"<", "RO"},
        {">", "RO"},
        {"<=", "RO"},
        {">=", "RO"},
        {"==", "RO"},
        {"!=", "RO"},
        {"&&", "&&"},
        {"||", "||"},
        {
            "!", "!"
        },
        {"=", "="},
        {"++", "inc-dec"},
        {"--", "inc-dec"},
        {"-=", "CO"},
        {"+=", "CO"},
        {"*=", "CO"},
        {"/=", "CO"},};
    public String[] punctuators = {";", ":", ",", "(", ")", "{", "}", "[", "]", "."};

    public ArrayList<String> breakWords(String fileInput) {
        String temp = "";

        //"as"
        for (int i = 0; i < fileInput.length(); i++) {

            if (fileInput.charAt(i) == '"') {
                //string conditions
                if (!temp.isEmpty()) {
                    words.add(temp);
                    temp = "";
                }

                do {

                    temp += fileInput.charAt(i);
                    i++;

                    if (i >= fileInput.length()) {
                        words.add(temp);
                        temp = "";
                        break;
                    } else if (fileInput.charAt(i) == '\\' && fileInput.charAt(i + 1) == '\"') {
                        temp += fileInput.charAt(i);
                        i++;
                        temp += fileInput.charAt(i);
                        i++;
                    } else if (fileInput.charAt(i) == '\r' && fileInput.charAt(i + 1) == '\n') {

                        words.add(temp);
                        temp = "";
                        i++;
                        temp += fileInput.charAt(i);
                        words.add(temp);
                        temp = "";
                        break;
                    }

                } while (fileInput.charAt(i) != '"');

                if (myUtil.ifDoubQuo(fileInput, i)) //end on "
                {

                    temp += fileInput.charAt(i);
                    words.add(temp);
                    temp = "";
                }

            } else if (fileInput.charAt(i) == '\'') {
                //char conditions

                if (!temp.isEmpty()) {
                    words.add(temp);
                    temp = "";
                }
                temp += fileInput.charAt(i);

                int p = 1;
                boolean occ = false;
                do {
                    i++;
                    //'\a' 'a'
                    if (i > fileInput.length() - 1) {

                        break;
                    } else if (fileInput.charAt(i) == '\n') {
                        words.add(temp);
                        temp = "";
                        temp += fileInput.charAt(i);
                        break;
                    } else if (fileInput.charAt(i) == '\\' && !occ) {
                        temp += fileInput.charAt(i);

                        occ = true;

                    } else {
                        if (fileInput.charAt(i) != '\r') {
                            temp += fileInput.charAt(i);
                            p++;

                        }

                    }

                } while (p < 3);

                words.add(temp);
                temp = "";

            } else if (fileInput.charAt(i) == '/' && i != fileInput.length() - 1) //0 1
            { //comment conditions

                if (fileInput.charAt(i + 1) == '*') {

                    if (!temp.isEmpty()) {

                        words.add(temp);
                        temp = "";
                    }

                    temp += fileInput.charAt(i);
                    i++;
                    temp += fileInput.charAt(i);
                    i++;
                    while (i < fileInput.length() - 1) {
                            // /*

                        temp += fileInput.charAt(i);
                        i++;
                        if (i >= fileInput.length() - 1) {
                            break;
                        }
                        if (fileInput.charAt(i) == '*') {

                            if (fileInput.charAt(i = i + 1) == '/') {
                                temp += fileInput.charAt(i = i - 1);
                                i++;
                                temp += fileInput.charAt(i);
                                i++;

                                break;
                            }
                        }
                    };

                    //words.add(temp);
                    temp = "";

                } else if (fileInput.charAt(i + 1) == '/') {
                    if (!temp.isEmpty()) // /   0
                    {

                        words.add(temp);
                        temp = "";

                    }

                    temp += fileInput.charAt(i);
                    i++;
                    temp += fileInput.charAt(i);
                    i++;
                    while (i < fileInput.length()) {
                        temp += fileInput.charAt(i);
                        i++;
                        if (i < fileInput.length()) {
                            if (fileInput.charAt(i) == '\n') {
                                temp = "";
                                temp += fileInput.charAt(i);
                                words.add(temp);
                                temp = "";
                                break;
                            }
                        }

                    }

                    temp = "";

                } else {
                    // / is an operator
                    if (!temp.isEmpty() && temp != " ") {
                        words.add(temp);
                        temp = "";

                    }

                    if (myUtil.shouldConcatNum(temp, fileInput.charAt(i), fileInput.charAt(i + 1))) {

                        temp += fileInput.charAt(i);
                        i++;
                        temp += fileInput.charAt(i);
                        words.add(temp);
                        temp = "";

                    } else {

                        temp += fileInput.charAt(i);
                        words.add(temp);
                        temp = "";
                    }
                }

            } else {
                //general
                if (fileInput.charAt(i) == '\r' || fileInput.charAt(i) == ' ') {

                    if (i + 1 > fileInput.length()) {
                        if (fileInput.charAt(i + 1) == '\n') {
                            if (!temp.isEmpty()) {
                                words.add(temp);
                                temp = "";

                            }
                            i++;
                            temp += fileInput.charAt(i);
                            words.add(temp);
                            temp = "";

                        }
                    }

                    if (!temp.isEmpty()) {

                        words.add(temp);
                        temp = "";
                    }

                } else if (fileInput.charAt(i) == '\n') {
                    if (!temp.isEmpty()) {
                        words.add(temp);
                        temp = "";
                    }

                    temp += fileInput.charAt(i);

                    words.add(temp);
                    temp = "";

                } else if (myUtil.isPunctuator(fileInput.charAt(i))) {

                    if (fileInput.charAt(i) == '.' && myUtil.isNumeric(temp)) {
                        temp += fileInput.charAt(i);
                    } else if (fileInput.charAt(i) == '.' && Character.isDigit(fileInput.charAt(i + 1))) {
                        if (!temp.isEmpty()) {

                            words.add(temp);
                            temp = "";
                        }

                        temp += fileInput.charAt(i);
                    } else {
                        if (!temp.isEmpty()) {

                            words.add(temp);
                            temp = "";
                        }

                        temp += fileInput.charAt(i);

                        words.add(temp);
                        temp = "";
                    }

                } else if (myUtil.isOperator(fileInput.charAt(i))) {

                    if (i < fileInput.length() - 1) //a+   1 2
                    {

                        if (myUtil.shouldConcatNum(temp, fileInput.charAt(i), fileInput.charAt(i + 1))) {
                            if (!temp.isEmpty() && temp != " ") {
                                words.add(temp);
                                temp = "";

                            }
                            //a+b +a ;+a +a13123 a++1
                            if ((fileInput.charAt(i) == '+' || fileInput.charAt(i) == '-') && Character.isDigit(fileInput.charAt(i + 1))) {
                                temp += fileInput.charAt(i);
                                i++;
                                temp += fileInput.charAt(i);

                            } else {
                                temp += fileInput.charAt(i);
                                i++;
                                temp += fileInput.charAt(i);
                                words.add(temp);
                                temp = "";

                            }

                        } else {
                            if (!temp.isEmpty()) {
                                words.add(temp);
                                temp = "";
                            }
                            temp += fileInput.charAt(i);
                            words.add(temp);
                            temp = "";
                        }

                    } else {
                        if (!temp.isEmpty() && temp != " ") {
                            words.add(temp);
                            temp = "";

                        }

                        temp += fileInput.charAt(i);
                        words.add(temp);
                        temp = "";

                    }
                } else {

                    temp += fileInput.charAt(i);

                }
            }
        }
        if (!temp.isEmpty()) {
            words.add(temp);
            temp = "";
        }

        return words;
    }

    public ArrayList<Token> generateTokens() {

        int lineNo = 1;
        for (String w : words) {
            if ("\n".equals(w)) {
                lineNo++;
            } else if (isMyOperator(w)) {
                String classPart;
                String value = w;
                for (String[] operator : operators) {
                    if (w.equals(operator[0])) {
                        classPart = operator[1];
                        tokenList.add(new Token(value, classPart, lineNo));
                        break;
                    }
                }

            } else if (isMyPunctuator(w)) {
                String classPart = w;
                String value = w;
                tokenList.add(new Token(value, classPart, lineNo));
            } else if (isMyKeyword(w)) {
                String classPart;
                String value = w;
                for (String[] keyword : keywords) {
                    if (w.equals(keyword[0])) {
                        classPart = keyword[1];
                        tokenList.add(new Token(value, classPart, lineNo));
                        break;
                    }
                }
            } else if (isMyIdentifier(w)) {
                String classPart = "id";
                String value = w;

                tokenList.add(new Token(value, classPart, lineNo));
            } else if (isMyChar(w)) {
                String valuePart = "";
                String classPart = "char";
                String value = w;
                for (int x = 1; x < value.length() - 1; x++) {
                    valuePart += w.charAt(x);
                }
                tokenList.add(new Token(valuePart, classPart, lineNo));
            } else if (isMyString(w)) {
                String valuePart = "";
                String classPart = "string";
                String value = w;
                for (int x = 1; x < value.length() - 1; x++) {
                    valuePart += w.charAt(x);
                }
                tokenList.add(new Token(valuePart, classPart, lineNo));

            } else if (isMyIntConst(w)) {
                String classPart = "int";
                String value = w;

                tokenList.add(new Token(value, classPart, lineNo));

            } else if (isMyDouble(w)) {
                String classPart = "double";
                String value = w;

                tokenList.add(new Token(value, classPart, lineNo));
            } else {
                String classPart = "Error";
                String value = w;

                tokenList.add(new Token(value, classPart, lineNo));
            }
        }
        
        return tokenList;
    }

    public boolean isMyKeyword(String s) {
        for (int i = 0; i < keywords.length; i++) {

            if (s.equals(keywords[i][0])) {

                return true;
            }
        }
        return false;
    }

    ;
public boolean isMyOperator(String s) {
        for (String[] operator : operators) {
            if (s.equals(operator[0])) {
                return true;
            }
        }
        return false;
    }

    ;



public boolean isMyPunctuator(String s) {
        for (String punctuator : punctuators) {
            if (s.equals(punctuator)) {
                return true;
            }
        }
        return false;
    }

    ;  

public boolean isMyIdentifier(String s) {
        Pattern p = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    ;

public boolean isMyChar(String s) {
        Pattern p = Pattern.compile("'(\\\\[tvrnafb\\\\]|[^\\\\'])'");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    ;

public boolean isMyDouble(String s) {
        Pattern p = Pattern.compile("^[+-]?(0|([1-9][0-9]*))(\\.[0-9]+)?$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    ;

public boolean isMyString(String s) {
        Pattern p = Pattern.compile("\".*?\"");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    ;

public boolean isMyIntConst(String s) {
        Pattern p = Pattern.compile("[+-]?[0-9]+");
        Matcher m = p.matcher(s);
        return m.matches();
    }
;



}
