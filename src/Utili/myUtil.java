package Utili;



public class myUtil {
    
   
      public static Boolean isAlphaNumeric(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') &&
                    !(c >= 'a' && c <= 'z') &&
                    !(c >= '0' && c <= '9')) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
           
         public static boolean isPunctuator(char c){
             char [] punc = {':', ';','(',')', '{', '}', '?', '[',']','.',',' };
             for(char ch : punc)
             {
                 if(c==ch)
                 {
                     
                     return true;
                     
                 }
             }
             return false;
        }
         
        public static boolean isOperator(char c)
         {
             char [] opr = {'+','-','/','*', '=','%','<','>','!','&','|'};
              for(char op : opr)
             {
                 if(c==op)
                 {
                  
                     return true;
                     
                 }
             }
             
           return false;
         }
         
         
         public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        int d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
         
         
         public static boolean ifDoubQuo(String input , int i)
         {
             if (i<input.length())
             {
               if(input.charAt(i)=='"' || input.charAt(i)=='\'')
             {
                 return true;
             }
             }
             return false;
         }
         
         public static boolean shouldConcatNum(String temp , char i, char j)
         {
             // == ++ -- += -= >= <= && || /= *= !=
              switch(i){
                  case '+':        //a+b a+1 1+1 ;+1   //  ++ +=   +5tigbj +for -for a +b  
                      //a+b return -1
                      if((temp.isEmpty() || !isAlphaNumeric(temp) )  && (j=='+' || j== '=' ||  Character.isDigit(j) ))
                      {
                          return true;
                      }
                      
                  break;
                       case '-':        //a+b a+1 1+1 ;+1   //  ++ +=   +5tigbj +for -for a +b
                      if((temp.isEmpty() || !isAlphaNumeric(temp) ) && (j=='-' || j== '=' || Character.isDigit(j) ))
                      {
                          return true;
                      }
                      
                  break;
                  
                  case '=':
                      if(j=='=')
                      {
                          return true;
                      }
                      break;
                  case'>' :
                      if(j=='=')
                      {
                          return true;
                      }
                      break;
                      case'<' :
                      if(j=='=')
                      {
                          return true;
                      }
                      break;
                      
                           case'&' :
                      if(j=='&')
                      {
                          return true;
                      }
                      break;
                               
                       case'|' :
                      if(j=='|')
                      {
                          return true;
                      }
                      break;  
                      
                            case'!' :
                      if(j=='=')
                      {
                          return true;
                      }
                      break;
                                 case'/' :
                      if(j=='=')
                      {
                          return true;
                      }
                      break;
                       case'*' :
                      if(j=='=')
                      {
                          return true;
                      }
                      break;
                  default: 
                      return false;
              }
       
         
          
         return false;
         }
         
    
           
  
}


