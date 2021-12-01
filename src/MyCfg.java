
import java.util.ArrayList;

public class MyCfg {

    static ArrayList<Token> tokens = new ArrayList();
    static int i = 0;

    public static boolean parse(ArrayList<Token> tok) {
        tokens = tok;
        return s();
    }

    private static boolean s() {

        if (tokens.get(i).value.equals("public") || tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("final") || tokens.get(i).classPart.equals("class")) {
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

        }

        return false;
    }

    private static boolean defs() {
        if (tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("final") || tokens.get(i).classPart.equals("class")) {
            if (class_defs()) {
                return defs();
            }
        } else if (tokens.get(i).classPart.equals("$") || tokens.get(i).classPart.equals("public")) {
            return true;
        }
        return false;
    }

    private static boolean class_defs() {
        if (tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("final") || tokens.get(i).classPart.equals("class")) {

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
        }

        return false;
    }

    private static boolean abs_final() {

        if (tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("abstract")) {
            i++;
            return true;
        } else if (tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("class")) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean inht() {

        if (tokens.get(i).classPart.equals("extends")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                return true;
            } else {
                return false;
            }

        } else if (tokens.get(i).classPart.equals("{")) {
            return true;
        }
        return false;
    }

    private static boolean mst() {
        //have to complete
        return true;
    }

    private static boolean c_body() {
        //have to conplete
        if (tokens.get(i).value.equals("public") || tokens.get(i).value.equals("private")) {
            i++;
            return c_body1();
        } else if (tokens.get(i).classPart.equals("final")) {
            i++;
            return c_body2();
        } else if (tokens.get(i).classPart.equals("abstract")) {
            i++;
            if (tokens.get(i).classPart.equals("dataType")) {
                i++;
                if (tokens.get(i).classPart.equals("id")) {
                    i++;
                    if (tokens.get(i).classPart.equals("(")) {
                        i++;
                        if (pl()) {
                            if (tokens.get(i).classPart.equals(")")) {
                                i++;

                                if (tokens.get(i).classPart.equals("{")) {
                                    i++;
                                    if (mst()) {
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
            }
            return false;
        } else if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            return c_body3();
        } else if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body8();
        } else if (tokens.get(i).classPart.equals("}")) {
            return true;
        }
        return false;
    }

    private static boolean c_body1() {
        if (tokens.get(i).classPart.equals("final")) {
            i++;
            return c_body();
        }
        if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            return c_body3();
        }
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body4();
        }
        if (tokens.get(i).classPart.equals("abstract")) {
            i++;
            if (tokens.get(i).classPart.equals("dataType")) {
                i++;
                if (tokens.get(i).classPart.equals("id")) {
                    i++;
                    if (tokens.get(i).classPart.equals("(")) {
                        i++;
                        if (pl()) {
                            if (tokens.get(i).classPart.equals(")")) {
                                i++;
                                if (tokens.get(i).classPart.equals("{")) {
                                    i++;
                                    if (mst()) {
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
            }
        }
        return false;
    }

    private static boolean c_body2() {
        if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            return c_body3();
        }
        return false;
    }

    private static boolean c_body8() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            if (tokens.get(i).classPart.equals("=")) {
                i++;
                if (tokens.get(i).classPart.equals("new")) {
                    i++;
                    if (tokens.get(i).classPart.equals("id")) {
                        i++;
                        if (tokens.get(i).classPart.equals("(")) {
                            i++;
                            if (args()) {
                                if (tokens.get(i).classPart.equals(")")) {
                                    i++;
                                    if (tokens.get(i).classPart.equals("{")) {
                                        i++;
                                        if (mst()) {
                                            if (tokens.get(i).classPart.equals("}")) {
                                                i++;
                                                return c_body();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (tokens.get(i).classPart.equals("(")) {
            i++;
            if (args()) {
                if (tokens.get(i).classPart.equals(")")) {
                    i++;
                    if (tokens.get(i).classPart.equals("{")) {
                        i++;
                        if (mst()) {
                            if (tokens.get(i).classPart.equals("}")) {
                                i++;
                                return c_body();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean c_body3() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body7();
        }
        return false;
    }

    private static boolean c_body7() {
        if (tokens.get(i).classPart.equals("(")) {
            i++;
            if (pl()) {
                if (tokens.get(i).classPart.equals("{")) {
                    i++;
                    if (mst()) {
                        if (tokens.get(i).classPart.equals("}")) {
                            i++;
                            return c_body();
                        }
                    }
                }
            }
        } else if (tokens.get(i).classPart.equals("=")) {
            if (init()) {
                if (list()) {
                    return c_body();
                }
            }
        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("]")) {
                i++;
                if (arr()) {
                    if (val()) {
                        if (tokens.get(i).classPart.equals(";")) {
                            i++;
                            return c_body();
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean c_body4() {
        if (tokens.get(i).classPart.equals("(")) {
            i++;
            if (args()) {
                if (tokens.get(i).classPart.equals(")")) {
                    i++;
                    if (tokens.get(i).classPart.equals("{")) {
                        i++;
                        if (mst()) {
                            if (tokens.get(i).classPart.equals("}")) {
                                i++;
                                return true;
                            }
                        }
                    }
                }

            }
        }
        return false;
    }

    private static boolean pl() {

        if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            return pl1();
        } else if (tokens.get(i).classPart.equals("id")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                i++;
                return true;

            }
            return false;
        } else if (tokens.get(i).classPart.equals(")")) {
            i++;
            return true;
        }
        return false;
    }

    private static boolean pl1() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            if (pl2()) {
                return true;
            }

        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("]")) {
                i++;
                if (arr()) {
                    if (tokens.get(i).classPart.equals("id")) {
                        i++;
                        return pl2();

                    }
                }

            }

        }
        return false;
    }

    private static boolean pl2() {
        if (tokens.get(i).classPart.equals(",")) {
            i++;
            return pl3();

        } else if (tokens.get(i).classPart.equals("(")) {
            return true;
        }
        return false;
    }

    private static boolean pl3() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;

            return pl4();

        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("]")) {
                i++;
                if (arr()) {
                    if (tokens.get(i).classPart.equals("id")) {
                        i++;
                        return pl2();

                    }
                }

            }

        }
        return false;
    }

    private static boolean pl4() {
        if (tokens.get(i).classPart.equals(",") || tokens.get(i).classPart.equals(")")) {

            if (pl2()) {
                return true;
            }
        } else if (tokens.get(i).classPart.equals("id")) {
            i++;
            return pl2();

        }
        return false;
    }

    private static boolean arr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean args() {
        if (tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).value.equals("string")
                || tokens.get(i).value.equals("int")
                || tokens.get(i).value.equals("char")
                || tokens.get(i).value.equals("double")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {
            i++;

            if (oe()) {
                return args1();
            } else {
                return false;
            }
        } else if (tokens.get(i).classPart.equals(")") || tokens.get(i).classPart.equals("}")) {
            return true;
        }
        return false;
    }

    private static boolean args1() {
        if (tokens.get(i).classPart.equals(",")) {
            i++;
            if (oe()) {
                return args1();
            }
            return false;

        } else if (tokens.get(i).classPart.equals(")") || tokens.get(i).classPart.equals("}")) {
            return true;
        }

        return false;
    }

    private static boolean oe() {
        if (tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).classPart.equals("string")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("int-dec")) {
            if (ae()) {
                return oe1();

            }
        }
        return false;
    }

    private static boolean oe1() {
        if (tokens.get(i).classPart.equals("||")) {
            if (ae()) {
                return oe1();
            } else {
                return false;
            }
        } else if (tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {
            return true;
        }

        return false;
    }

    private static boolean ae() {
        if (tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {

            if (re()) {
                return ae1();
            }
        }
        return false;
    }

    private static boolean ae1() {
        if (tokens.get(i).classPart.equals("&&")) {
            i++;
            if (re()) {
                return ae1();
            } else {
                return false;
            }
        } else if (tokens.get(i).classPart.equals("||")
                || tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {
            return true;
        }
        return false;
    }

    private static boolean re() {
        if (tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).classPart.equals("string")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {

            if (e()) {
                return re1();
            }
        }
        return false;
    }

    private static boolean re1() {
        if (tokens.get(i).classPart.equals("RO")) {
            i++;
            if (e()) {
                return re1();
            }
            return false;
        } else if (tokens.get(i).classPart.equals("&&") || tokens.get(i).classPart.equals("||")) {
            return true;
        }
        return false;
    }

    private static boolean e() {
        if (tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).classPart.equals("string")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {

            if (t()) {
                return e1();
            }
        }
        return false;

    }

    private static boolean e1() {
        if (tokens.get(i).classPart.equals("pm")) {
            i++;
            if (t()) {
                return e1();

            }
            return false;

        } else if (tokens.get(i).classPart.equals("RO")
                || tokens.get(i).classPart.equals("&&")) {

            return true;
        } else {
            return false;
        }
    }

    private static boolean t() {
        if (tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).classPart.equals("string")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {
            if (f()) {
                return t1();

            }
        }
        return false;
    }

    private static boolean t1() {
        if (tokens.get(i).classPart.equals("mdm")) {
            i++;
            if (f()) {
                return t1();

            }
            return false;

        } else if (tokens.get(i).classPart.equals("pm") || tokens.get(i).classPart.equals("RO")) {
            return true;
        } else {
            return true;
        }
    }

    private static boolean f() {
        if (tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("super") || tokens.get(i).classPart.equals("id")) {

            if (th()) {
                if (tokens.get(i).classPart.equals("id")) {
                    i++;
                    return opt();

                }
            } else if (tokens.get(i).classPart.equals("string") || tokens.get(i).classPart.equals("char") || tokens.get(i).classPart.equals("double") || tokens.get(i).classPart.equals("int")) {

                if (cnst()) {
                    return true;
                }
            } else if (tokens.get(i).classPart.equals("(")) {
                i++;
                if (oe()) {
                    if (tokens.get(i).classPart.equals(")")) {
                        i++;
                        return true;
                    }
                }

            } else if (tokens.get(i).classPart.equals("!")) {
                i++;
                return f();

            } else if (tokens.get(i).classPart.equals("inc-dec")) {

                if (inc_dec()) {
                    if (th()) {
                        if (tokens.get(i).classPart.equals("id")) {
                            i++;
                            return opt();
                        }
                    }
                }
            }

        }
        return false;
    }

    private static boolean th() {

        if (tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("super")) {

            i++;
            if (tokens.get(i).classPart.equals(".")) {

                i++;
                return true;
            } else {
                return false;
            }
        } //null
        else if (tokens.get(i).classPart.equals("id")) {

            return true;
        } else {

            return false;

        }
    }

    private static boolean opt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean cnst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean inc_dec() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean val() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
