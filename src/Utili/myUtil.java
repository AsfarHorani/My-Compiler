package Utili;



public class myUtil {
    
    
           
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
                      if((temp.isEmpty() )  && (j=='+' || j== '=' || isNumeric(String.valueOf(j)) || Character.isLetter(j) ))
                      {
                          return true;
                      }
                      
                  break;
                       case '-':        //a+b a+1 1+1 ;+1   //  ++ +=   +5tigbj +for -for a +b
                      if(temp.isEmpty()  && (j=='-' || j== '=' || isNumeric(String.valueOf(j)) || Character.isLetter(j) ))
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


