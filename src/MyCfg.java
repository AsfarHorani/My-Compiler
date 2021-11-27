
import java.util.ArrayList;

public class MyCfg {

    static ArrayList<Token> tokens = new ArrayList();
    static int i = 0;

    public static boolean parse(ArrayList<Token> tok) {
        tokens = tok;
        return s();
    }

    private static boolean s() {
        if (defs()) {
            if (tokens.get(i).value.equals("public")) {
                i++;
                if (tokens.get(i).classPart.equals("class")) {
                    i++;
                    if (tokens.get(i).classPart.equals("id")) {
                        i++;
                        if (inht()) {
                            if (tokens.get(i).classPart.equals("{")) {
                                i++;
                                if (tokens.get(i).value.equals("public")) {
                                    i++;
                                    if (tokens.get(i).value.equals("static")) {
                                        i++;
                                        if (tokens.get(i).value.equals("void")) {
                                            i++;
                                            if (tokens.get(i).value.equals("main")) {
                                                i++;
                                                if (tokens.get(i).classPart.equals("(")) {
                                                    i++;
                                                    if (tokens.get(i).classPart.equals(")")) {
                                                        i++;
                                                        if (tokens.get(i).classPart.equals("{")) {
                                                            i++;
                                                            if (mst()) {
                                                                if (tokens.get(i).classPart.equals("}")) {
                                                                    i++;
                                                                    if (c_body()) {
                                                                        if (tokens.get(i).classPart.equals("}")) {
                                                                            i++;
                                                                            if (defs()) {
                                                                                return true;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean defs() {
        if (class_defs()) {
            if (defs()) {
                return true;
            }
        }

        return true;
    }

    private static boolean inht() {
//        if(tokens.get(i).classPart.equals("extends"))
//        {
//           i++;
//           if(tokens.get(i).classPart.equals("id")){
//               return true;
//           }
//        
//        }
//        
        return true;
    }

    private static boolean mst() {
        //have to complete
        return true;
    }

    private static boolean c_body() {
        //have to conplete
        return true;
    }

    private static boolean class_defs() {
        if (abs_final()) {
            if (tokens.get(i).classPart.equals("class")) {
                i++;
                if (tokens.get(i).classPart.equals("id")) {
                    i++;
                    if (inht()) {
                        if (tokens.get(i).classPart.equals("{")) {
                            i++;
                            if (c_body()) {
                                if (tokens.get(i).classPart.equals("}")) {
                                    i++;
                                    return true;
                                }
                            }
                        }

                    }
                }
            }
        }
        return false;
    }

    private static boolean abs_final() {

        if (tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("abstract")) {
            i++;
            return true;
        }
        return true;
    }
}
