
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
        if (tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("final") || tokens.get(i).classPart.equals("class")) {
            if (class_defs()) {
                return defs();
            }
        } else if (tokens.get(i).classPart.equals("$") || tokens.get(i).value.equals("public")) {
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

        if (tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("final")) {
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
                i++;
                return true;
            }

        } else if (tokens.get(i).classPart.equals("{")) {
            return true;
        }
        return false;
    }

    private static boolean c_body() {
        //have to conplete
        if (tokens.get(i).value.equals("public")
                || tokens.get(i).value.equals("private")) {
            i++;
            return c_body1();
        } else if (tokens.get(i).classPart.equals("final")) {
            i++;
            return c_body6();
        } else if (tokens.get(i).classPart.equals("abstract")) {
            i++;
            return c_body4();

        } else if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            return c_body7();
        } else if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body11();
        } else if (tokens.get(i).classPart.equals("}")) {
            return true;
        }
        return false;
    }

    private static boolean c_body1() {
        if (tokens.get(i).classPart.equals("final")) {
            i++;
            return c_body6();
        } else if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            return c_body();
        } else if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body2();
        } else if (tokens.get(i).classPart.equals("abstract")) {
            i++;
            return c_body4();
        }
        return false;
    }

    private static boolean c_body2() {
        if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            return c_body3();
        } else if (tokens.get(i).classPart.equals("(")) {
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
        }
        return false;
    }

    private static boolean c_body3() {
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
                                return c_body();
                            }
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
        if (tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("id") || tokens.get(i).value.equals("void")) {
            i++;
            return c_body5();
        }
        return false;
    }

    private static boolean c_body5() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body9();
        }

        return false;
    }

    private static boolean c_body9() {
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
        return false;
    }

    private static boolean c_body6() {
        if (tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body7();
        } else if (tokens.get(i).value.equals("void")) {
            i++;
            return c_body5();
        }

        return false;
    }

    private static boolean c_body7() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body8();
        }
        return false;
    }

    private static boolean c_body8() {
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

    public static boolean c_body10() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            return c_body11();
        }
        return false;
    }

    public static boolean c_body11() {
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
                                return c_body();
                            }
                        }
                    }
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
        } else if (tokens.get(i).classPart.equals(",") || tokens.get(i).classPart.equals(";")) {
            if (list()) {
                return c_body();
            }
        } else if (tokens.get(i).value.equals("=")) {
            i++;
            return c_body12();
        }

        return false;
    }

    private static boolean c_body12() {

        if (tokens.get(i).value.equals("this") || tokens.get(i).value.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).value.equals("string")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {
            return c_body13();
        } else if (tokens.get(i).classPart.equals("new")) {
            i++;
            return c_body14();
        }
        return false;
    }

    private static boolean c_body14() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
            if (tokens.get(i).classPart.equals("(")) {
                i++;
                if (args()) {
                    if (tokens.get(i).classPart.equals(")")) {
                        i++;
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

    private static boolean c_body13() {

        if (tokens.get(i).value.equals("this") || tokens.get(i).value.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).value.equals("string")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {
            if (oe()) {
                if (tokens.get(i).value.equals(";")) {
                    i++;
                    return c_body();
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
            return pl1();
        } else if (tokens.get(i).classPart.equals(")")) {
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

        }
        return false;
    }

    private static boolean pl2() {
        if (tokens.get(i).classPart.equals(",") || tokens.get(i).classPart.equals(")")) {

            return pl3();

        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("]")) {
                i++;
                if (arr()) {
                    return pl3();
                }
            }

        }
        return false;
    }

    private static boolean pl3() {
        if (tokens.get(i).classPart.equals(",")) {
            i++;
            return pl4();
        } else if (tokens.get(i).classPart.equals(")")) {
            return true;
        }
        return false;
    }

    private static boolean pl4() {
        if (tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("id")) {
            i++;
            return pl1();
        }
        return false;
    }

    private static boolean arr() {
        if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("]")) {
                i++;
                return arr();
            }
        } else if (tokens.get(i).classPart.equals("=") || tokens.get(i).classPart.equals(")") || tokens.get(i).classPart.equals(",")) {
            return true;

        }
        return false;
    }

    private static boolean args() {
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

            if (oe()) {
                return args1();
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

        } else if (tokens.get(i).classPart.equals("&&")
                || tokens.get(i).classPart.equals("||")
                || tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {
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

        } else if (tokens.get(i).classPart.equals("RO")
                || tokens.get(i).classPart.equals("&&")
                || tokens.get(i).classPart.equals("||")
                || tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {

            return true;
        }
        return false;

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

        } else if (tokens.get(i).classPart.equals("pm")
                || tokens.get(i).classPart.equals("RO")
                || tokens.get(i).classPart.equals("||")
                || tokens.get(i).classPart.equals("&&")
                || tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {
            return true;
        }
        return false;

    }

    private static boolean f() {
        if (tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("super") || tokens.get(i).classPart.equals("id")) {

            if (th()) {
                if (tokens.get(i).classPart.equals("id")) {
                    i++;
                    return opt();

                }
            }
        } else if (tokens.get(i).classPart.equals("string")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("int")) {

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
                        return ref();
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

    private static boolean ref() {
        if (tokens.get(i).classPart.equals(".")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                i++;
                return ref();
            }
        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("id")
                    || tokens.get(i).classPart.equals("int")
                    || tokens.get(i).classPart.equals("double")) {
                i++;
                if (tokens.get(i).classPart.equals("]")) {
                    i++;
                    return ref();
                }
            }
        } else if (tokens.get(i).classPart.equals("(")) {
            i++;
            if (args()) {
                if (tokens.get(i).classPart.equals(")")) {
                    i++;
                    return x();
                }
            }

        } else if (tokens.get(i).classPart.equals("RO")
                || tokens.get(i).classPart.equals(";")
                || tokens.get(i).classPart.equals("=")
                || tokens.get(i).classPart.equals("CO")
                || tokens.get(i).classPart.equals("mdm")
                || tokens.get(i).classPart.equals("pm")
                || tokens.get(i).classPart.equals("||")
                || tokens.get(i).classPart.equals("&&")
                || tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {
            return true;
        }
        return false;
    }

    private static boolean x() {
        if (tokens.get(i).classPart.equals(".")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                i++;
                return ref();
            }
        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("id") || tokens.get(i).classPart.equals("int") || tokens.get(i).classPart.equals("double")) {
                i++;
                if (tokens.get(i).classPart.equals("]")) {
                    i++;
                    return ref();
                }
            }
        }

        return false;
    }

    private static boolean cnst() {
        if (tokens.get(i).classPart.equals("string") || tokens.get(i).classPart.equals("int") || tokens.get(i).classPart.equals("char") || tokens.get(i).classPart.equals("double")) {
            i++;
            return true;
        }
        return false;
    }

    private static boolean inc_dec() {
        if (tokens.get(i).classPart.equals("inc-dec") || tokens.get(i).classPart.equals("inc-dec")) {
            i++;
            return true;
        }
        return false;
    }

    private static boolean init() {
        if (tokens.get(i).classPart.equals("=")) {
            i++;
            return oe();

        } else if (tokens.get(i).classPart.equals(";") || tokens.get(i).classPart.equals(",")) {
            return true;
        }
        return false;
    }

    private static boolean list() {
        if (tokens.get(i).classPart.equals(";")) {
            i++;
            return true;
        } else if (tokens.get(i).classPart.equals(",")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                i++;
                if (init()) {
                    return list();
                }
            }
        }
        return false;
    }

    private static boolean val() {
        if (tokens.get(i).classPart.equals("=")) {
            i++;
            return arr_dec();
        }
        return false;
    }

    private static boolean arr_dec() {
        if (tokens.get(i).classPart.equals("new")) {
            i++;
            if (tokens.get(i).classPart.equals("dataType")) {
                i++;
                if (tokens.get(i).classPart.equals("[")) {
                    i++;
                    if (oe()) {
                        if (tokens.get(i).classPart.equals("]")) {
                            i++;
                            return arr_dec4();
                        }
                    }
                }
            }
        } else if (tokens.get(i).classPart.equals("{")) {   
            i++;
            if (arr1()) {
                if (tokens.get(i).classPart.equals("}")) {
                    i++;
                    return arr2();
                }
            }
        }
        return false;
    }

    private static boolean arr_dec4() {
        if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (oe()) {
                if (tokens.get(i).classPart.equals("]")) {
                    i++;
                    return arr_dec4();
                }
            }
        } else if (tokens.get(i).classPart.equals(";")) {

            return true;
        }
        return false;
    }

    private static boolean arr1() {
        if (tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).classPart.equals("string")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("double")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")) {
            return args();
        } else if (tokens.get(i).classPart.equals("{")) {
            i++;
            if (args()) {
                if (tokens.get(i).classPart.equals("}")) {
                    i++;
                    return arr2();
                }
            }
        } else if (tokens.get(i).classPart.equals("}")) {
            return true;
        }
        return false;
    }

    private static boolean arr2() {
        if (tokens.get(i).classPart.equals(",")) {
            i++;
            return arr3();
        } else if (tokens.get(i).classPart.equals(";") || tokens.get(i).classPart.equals("}")) {
            return true;
        }
        return false;
    }

    private static boolean arr3() {
        if (tokens.get(i).classPart.equals("{")) {
            i++;
            if (args()) {
                if (tokens.get(i).classPart.equals("}")) {
                    i++;
                    return arr2();
                }
            }
        }
        return false;
    }

    private static boolean returnn() {
        if (tokens.get(i).classPart.equals("return")) {
            i++;
            return return1();
        }
        return false;
    }

    private static boolean return1() {
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
            if (oe()) {
                if (tokens.get(i).classPart.equals(";")) {
                    i++;
                    return true;
                }

            }
        }
        return false;
    }

    private static boolean obj_dec() {
        if (tokens.get(i).classPart.equals("id")) {
            i++;
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

    private static boolean multi_arr() {
        if (tokens.get(i).classPart.equals("dataType")
                || tokens.get(i).value.equals("public")
                || tokens.get(i).value.equals("private")
                || tokens.get(i).classPart.equals("final")) {
            if (acess_mod()) {
                if (finall()) {
                    if (tokens.get(i).classPart.equals("dataType")) {
                        i++;
                        if (tokens.get(i).classPart.equals("id")) {
                            i++;
                            if (tokens.get(i).classPart.equals("[")) {
                                i++;
                                if (tokens.get(i).classPart.equals("]")) {
                                    i++;
                                    if (arr()) {
                                        if (val()) {
                                            if (tokens.get(i).classPart.equals(";")) {
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

        }
        return false;
    }

    private static boolean finall() {
        if (tokens.get(i).classPart.equals("final")) {
            i++;
            return true;
        } else if (tokens.get(i).classPart.equals("dataType")) {
            return true;
        }
        return false;
    }

    private static boolean dec() {
        if (tokens.get(i).value.equals("public")
                || tokens.get(i).value.equals("private")
                || tokens.get(i).classPart.equals("final")
                || tokens.get(i).classPart.equals("dataType")) {

            if (acess_mod()) {
                if (finall()) {
                    if (tokens.get(i).classPart.equals("dataType")) {
                        i++;
                        if (tokens.get(i).classPart.equals("id")) {
                            i++;
                            if (init()) {
                                return list();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean acess_mod() {
        if (tokens.get(i).value.equals("public") || tokens.get(i).value.equals("private")) {
            i++;
            return true;
        } else if (tokens.get(i).classPart.equals("abstract") || tokens.get(i).classPart.equals("class") || tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("final")) {
            return true;
        }
        return false;
    }

    private static boolean if_else() {

        if (tokens.get(i).classPart.equals("if")) {
            i++;
            if (tokens.get(i).classPart.equals("(")) {
                i++;
                if (oe()) {
                    if (tokens.get(i).classPart.equals(")")) {
                        i++;
                        if (bodyy()) {

                            return if_else2();
                        }
                    }
                }

            }

        }
        return false;
    }

    private static boolean if_else2() {

        if (tokens.get(i).classPart.equals("else")) {
            i++;
            return if_else3();
        }
        return false;
    }

    private static boolean if_else3() {
        if (tokens.get(i).classPart.equals("if")) {
            i++;
            if (tokens.get(i).classPart.equals("(")) {
                i++;
                if (oe()) {
                    if (tokens.get(i).classPart.equals(")")) {
                        i++;
                        if (bodyy()) {
                            return if_else2();
                        }
                    }
                }

            }

        } else if (tokens.get(i).classPart.equals(";")
                || tokens.get(i).classPart.equals("dataType")
                || tokens.get(i).classPart.equals("inc-dec")
                || tokens.get(i).classPart.equals("while")
                || tokens.get(i).classPart.equals("if")
                || tokens.get(i).classPart.equals("return")
                || tokens.get(i).classPart.equals("for")
                || tokens.get(i).classPart.equals("{")) {

            return bodyy();
        }
        return false;
    }

    private static boolean mst() {
        if (tokens.get(i).classPart.equals("id") || tokens.get(i).classPart.equals("super") || tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("do") || tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("inc-dec") || tokens.get(i).classPart.equals("while") || tokens.get(i).classPart.equals("if") || tokens.get(i).classPart.equals("return") || tokens.get(i).classPart.equals("for")) {
            if (sst()) {
                return mst();

            }
        } else if (tokens.get(i).classPart.equals("}")) {
            return true;
        }

        return false;
    }

    private static boolean bodyy() {
        if (tokens.get(i).classPart.equals(";")) {
            i++;
            return true;
        } else if (tokens.get(i).classPart.equals("do") || tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("inc-dec") || tokens.get(i).classPart.equals("while") || tokens.get(i).classPart.equals("if") || tokens.get(i).classPart.equals("return") || tokens.get(i).classPart.equals("for")) {
            return sst();
        } else if (tokens.get(i).classPart.equals("{") || tokens.get(i).classPart.equals("dataType") || tokens.get(i).classPart.equals("inc-dec") || tokens.get(i).classPart.equals("while") || tokens.get(i).classPart.equals("if") || tokens.get(i).classPart.equals("return") || tokens.get(i).classPart.equals("for")) {
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
        return false;
    }

    private static boolean elsee() {
        if (tokens.get(i).classPart.equals("else")) {
            i++;
            return bodyy();

        }
        return false;
    }

    private static boolean do_while() {
        if (tokens.get(i).classPart.equals("do")) {
            i++;
            if (tokens.get(i).classPart.equals("{")) {
                i++;
                if (mst()) {
                    if (tokens.get(i).classPart.equals("}")) {
                        i++;
                        if (tokens.get(i).classPart.equals("while")) {
                            i++;
                            if (tokens.get(i).classPart.equals("(")) {
                                i++;
                                if (oe()) {
                                    if (tokens.get(i).classPart.equals(")")) {
                                        i++;
                                        if (tokens.get(i).classPart.equals(";")) {
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

    private static boolean forr() {
        if (tokens.get(i).classPart.equals("for")) {
            i++;
            if (tokens.get(i).classPart.equals("(")) {
                i++;

                if (c1()) {
                    if (c2()) {
                        if (tokens.get(i).classPart.equals(";")) {
                            i++;

                            if (c3()) {
                                if (tokens.get(i).classPart.equals(")")) {
                                    i++;
                                    return bodyy();

                                }
                            }
                        }
                    }
                }

            }
        }

        return false;
    }

    private static boolean c1() {
        if (tokens.get(i).classPart.equals("dataType")) {

            return dec();

        } else if (tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("super") || tokens.get(i).classPart.equals("id")) {

            if (asgn_st()) {
                if (tokens.get(i).classPart.equals(";")) {
                    i++;
                    return true;
                }

            }
        }

        return false;
    }

    private static boolean c2() {
        if (tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("!")
                || tokens.get(i).classPart.equals("inc-dec")
                || tokens.get(i).classPart.equals("this")
                || tokens.get(i).classPart.equals("super")
                || tokens.get(i).classPart.equals("id")
                || tokens.get(i).classPart.equals("string")
                || tokens.get(i).classPart.equals("char")
                || tokens.get(i).classPart.equals("int")
                || tokens.get(i).classPart.equals("double")) {
            return oe();
        } else if (tokens.get(i).classPart.equals(";")) {
            return true;
        }
        return false;
    }

    private static boolean c3() {
        if (tokens.get(i).classPart.equals("inc-dec")) {
            return inc_dec_st();
        } else if (tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("super") || tokens.get(i).classPart.equals("id")) {
            return asgn_st();
        } else if (tokens.get(i).classPart.equals(")")) {
            return true;
        }
        return false;
    }

    private static boolean asgn_st() {
        if (tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("super") || tokens.get(i).classPart.equals("id")) {
            if (th()) {
                if (tokens.get(i).classPart.equals("id")) {
                    i++;
                    if (ref()) {
                        if (asgn_op()) {
                            if (oe()) {

                                return true;
                            }
                        }

                    }
                }
            }
        }
        return false;
    }

    private static boolean inc_dec_st() {
        if (tokens.get(i).classPart.equals("inc-dec")) {
            i++;
            if (th()) {
                if (tokens.get(i).classPart.equals("id")) {
                    i++;

                    if (ref()) {
                        if (tokens.get(i).classPart.equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean while_st() {
        if (tokens.get(i).classPart.equals("while")) {
            i++;
            if (tokens.get(i).classPart.equals("(")) {
                i++;
                if (oe()) {
                    if (tokens.get(i).classPart.equals(")")) {
                        i++;

                        return bodyy();
                    }
                }

            }

        }

        return false;
    }

    private static boolean asgn_op() {
        if (tokens.get(i).classPart.equals("=") || tokens.get(i).classPart.equals("CO")) {
            i++;
            return true;
        }
        return false;
    }

    private static boolean sst() {
        if (tokens.get(i).classPart.equals("dataType")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                i++;

                return sst1();
            }

        } else if (tokens.get(i).classPart.equals("inc-dec")) {
            return inc_dec_st();

        } else if (tokens.get(i).classPart.equals("while")) {

            return while_st();
        } else if (tokens.get(i).classPart.equals("if")) {

            return if_else();
        } else if (tokens.get(i).classPart.equals("return")) {

            return returnn();
        } else if (tokens.get(i).classPart.equals("for")) {

            return forr();
        } else if (tokens.get(i).classPart.equals("do")) {
            return do_while();
        } else if (tokens.get(i).classPart.equals("this") || tokens.get(i).classPart.equals("super") || tokens.get(i).classPart.equals("id")) {
            if (th()) {
                if (tokens.get(i).classPart.equals("id")) {
                    i++;
                    if (sst2()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean sst1() {
        if (tokens.get(i).classPart.equals(";")
                || tokens.get(i).classPart.equals("=")
                || tokens.get(i).classPart.equals("'")) {
            if (init()) {
                return list();
            }

        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("]")) {
                i++;
                if (arr()) {
                    if (val()) {
                        if (tokens.get(i).classPart.equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }

        } else if (tokens.get(i).classPart.equals("(")) {
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

        return false;
    }

    private static boolean sst2() {
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
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } else if (tokens.get(i).classPart.equals(".")
                || tokens.get(i).classPart.equals("[")
                || tokens.get(i).classPart.equals("(")
                || tokens.get(i).classPart.equals("=")
                || tokens.get(i).classPart.equals(";")
                || tokens.get(i).classPart.equals("CO")
                || tokens.get(i).classPart.equals("mdm")
                || tokens.get(i).classPart.equals("pm")) {
            if (ref()) {
                if (asgn_op()) {
                    if (oe()) {
                        if (tokens.get(i).classPart.equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean opt() {
        if (tokens.get(i).classPart.equals(".")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                i++;
                return opt();
            }
        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("id")
                    || tokens.get(i).classPart.equals("int")
                    || tokens.get(i).classPart.equals("double")) {
                i++;
                if (tokens.get(i).classPart.equals("]")) {
                    i++;
                    return opt();
                }
            }
        } else if (tokens.get(i).classPart.equals("(")) {
            i++;
            if (args()) {
                if (tokens.get(i).classPart.equals(")")) {
                    i++;
                    return fop();
                }
            }

        } else if (tokens.get(i).classPart.equals("RO")
                || tokens.get(i).classPart.equals(";")
                || tokens.get(i).classPart.equals("=")
                || tokens.get(i).classPart.equals("CO")
                || tokens.get(i).classPart.equals("mdm")
                || tokens.get(i).classPart.equals("pm")
                || tokens.get(i).classPart.equals("||")
                || tokens.get(i).classPart.equals("&&")
                || tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {
            return true;
        }
        return false;
    }

    private static boolean fop() {
        if (tokens.get(i).classPart.equals(".")) {
            i++;
            if (tokens.get(i).classPart.equals("id")) {
                i++;
                return opt();
            }
        } else if (tokens.get(i).classPart.equals("[")) {
            i++;
            if (tokens.get(i).classPart.equals("id") || tokens.get(i).classPart.equals("int") || tokens.get(i).classPart.equals("double")) {
                i++;
                if (tokens.get(i).classPart.equals("]")) {
                    i++;
                    return opt();
                }
            }
        } else if (tokens.get(i).classPart.equals("RO")
                || tokens.get(i).classPart.equals(";")
                || tokens.get(i).classPart.equals("=")
                || tokens.get(i).classPart.equals("CO")
                || tokens.get(i).classPart.equals("mdm")
                || tokens.get(i).classPart.equals("pm")
                || tokens.get(i).classPart.equals("||")
                || tokens.get(i).classPart.equals("&&")
                || tokens.get(i).classPart.equals(",")
                || tokens.get(i).classPart.equals("]")
                || tokens.get(i).classPart.equals(")")
                || tokens.get(i).classPart.equals(";")) {
            return true;
        }

        return false;
    }

}
