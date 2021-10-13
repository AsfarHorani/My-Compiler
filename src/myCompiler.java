import Utili.myUtil;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class myCompiler {
        ArrayList <String> words = new ArrayList();
     String [][] keywords = {
         {"for","for"},
         {"int","dataType"},
         {"float","dataType"},
         {"String","dataType"},
         {"double","dataType"},
         {"char","dataType"},
         { "class","class"},
         { "void","void"},
         {"do","do"},
        {"while","while"},
        {"if","if"},
        {"else","else"},
    {"return","return"},
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
        }
        ,{
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
        }
  
     };
     public String[][] operators={
         {"+","pm"},
         {"-","pm"},
         {"*","mdm"},
         {"/","mdm"},
         {"%","mdm"},
         {"<","RO"},
         {">","RO"},
         {"<=","RO"},
         {">=","RO"},
         {"==","RO"},
         {"!=","RO"},
         {"&&","&&"},
         {"||","||"},
         {
         "!","!"
         },
         {"++","inc/dec"},
         {"--","inc/dec"}
     };
     public String[] punctuators={";",":",",","(",")","{","}","[","]","."};

    
 public ArrayList <String> breakWords(String fileInput){
        String temp="";

         //"as"
        for(int i=0;i<fileInput.length();i++){
           
            if(fileInput.charAt(i)=='"'){
                    //string conditions
                if(!temp.isEmpty())
                {
                     words.add(temp);
                               temp="";
                }
                             
                     do{
                             
                            temp+= fileInput.charAt(i);
                            i++;
                            
                            if(i>=fileInput.length())
                            {
                               words.add(temp);
                               temp="";
                                break;
                            }
            }while(fileInput.charAt(i)!='"');
                      
                       if( myUtil.ifDoubQuo(fileInput, i)) //end on "
                    {
                       
                        temp+= fileInput.charAt(i);
                        words.add(temp);
                        temp="";
                     }
                    
                
                    }
                else if(fileInput.charAt(i)=='\''){
                      //char conditions

                     if(!temp.isEmpty())
                      {
                               words.add(temp);
                                temp="";
                      }
             
                           temp+= fileInput.charAt(i);
                           i++;
                           
                           if(fileInput.charAt(i)=='\\')
                           {
                               temp+= fileInput.charAt(i);
                               i++;
                               temp+= fileInput.charAt(i);
                               i++;
                    
                           }
                           else{
                                   temp+= fileInput.charAt(i);
                                   i++;

                                   }
                      
                        temp+= fileInput.charAt(i);
                        words.add(temp);
                        temp="";
                     
                
                    }
                else if(fileInput.charAt(i)=='/')
                { //comment conditions
                
                    if(fileInput.charAt(i+1)=='*')
                    {  
                         if(i>0)  // /   0
                           {
                             char a = fileInput.charAt(i-1);
                             System.out.print(fileInput.charAt(i-1));
                             if(fileInput.charAt(i-1)!=' ' && fileInput.charAt(i-1)!='\r'   )
                             {
                                 
                                  temp+= fileInput.charAt(i-1);
                            words.add(temp);
                           temp="";
                             }
                           }
                       
                           
                             
                           
                            temp+= fileInput.charAt(i); 
                            i++;
                            temp+= fileInput.charAt(i); 
                            i++;
                        while(i<fileInput.length()-1){
                            // /*
                    
                           
                            temp+= fileInput.charAt(i); 
                             i++;
                             if(i>=fileInput.length()-1)
                             {
                                 break;
                             }
                    if(fileInput.charAt(i)=='*')
                   {
                      
                       
                   if(fileInput.charAt(i=i+1)=='/')
                    { 
                         temp+= fileInput.charAt(i=i-1); 
                         i++;
                         temp+= fileInput.charAt(i); 
                          i++;
                         
                          break;
                    }
               }
                        };
                      
                        //words.add(temp);
                           temp="";
                       
                    }else if(fileInput.charAt(i)=='/')
                    {
                          if(i>0)  // /   0
                           {
                             char a = fileInput.charAt(i-1);
                             System.out.print(fileInput.charAt(i-1));
                             if(fileInput.charAt(i-1)!=' ' && fileInput.charAt(i-1)!='\r'   )
                             {
                                 
                                  temp+= fileInput.charAt(i-1);
                            words.add(temp);
                           temp="";
                             }
                           }
                       
                           temp+= fileInput.charAt(i); 
                            i++;
                            temp+= fileInput.charAt(i); 
                            i++;
                     while(i<fileInput.length()){
                           temp+= fileInput.charAt(i); 
                             i++;
                  if(i<fileInput.length())
                  {
                      if(fileInput.charAt(i)=='\n')
                    { 
                         
                         break;
                    }
                  }
                   
               
                        }
                         // words.add(temp);
                          temp="";
                        
                    }
                    
                    
                    
                    
                   
                }
            
        else{
            //general
              if(fileInput.charAt(i)=='\r' || fileInput.charAt(i)==' '){
               if(fileInput.charAt(i+1)=='\n')
            {
         if(!temp.isEmpty())
        {
              words.add(temp);
              temp="";
         
        }
         i++;
         temp+= fileInput.charAt(i);
         words.add(temp);
         temp="";
       
            }   
                  
               if(!temp.isEmpty() ){
                    
                    words.add(temp);
                    temp="";
                }
                  
        }
              else if(fileInput.charAt(i)=='\n')
            {
         if(!temp.isEmpty())
        {
              words.add(temp);
              temp="";
        }
         
         temp+= fileInput.charAt(i);
         System.out.println(temp);
         words.add(temp);
              temp="";
       
            }

           else if(myUtil.isPunctuator(fileInput.charAt(i))){
                    
               
               if(fileInput.charAt(i)=='.' && myUtil.isNumeric(temp))
               {
                     temp+= fileInput.charAt(i);
               }
               else{
               if(!temp.isEmpty() ){
                     
                    words.add(temp);
                    temp="";
                }
                  
                    temp+= fileInput.charAt(i);
                  
                    words.add(temp);
                    temp="";
               }
                
                
            } else if(myUtil.isOperator(fileInput.charAt(i)))
            {
                
                
                if(i<fileInput.length()-1)   //a+   1 2
                {
                        if(myUtil.shouldConcatNum(temp,fileInput.charAt(i),fileInput.charAt(i+1)))
                  {
                            if(!temp.isEmpty() && temp!=" ")
                    {
                     words.add(temp);
                      temp="";
                      
                    }
                      
                    if((fileInput.charAt(i)=='+' || fileInput.charAt(i)=='-') && ( Character.isLetter(fileInput.charAt(+1)) || myUtil.isNumeric(String.valueOf(fileInput.charAt(i+1))))) 
                    {
                        temp+=fileInput.charAt(i);
                    i++;
                    temp+= fileInput.charAt(i);
                    
                    }
                    else{
                         temp+=fileInput.charAt(i);
                    i++;
                    temp+= fileInput.charAt(i);
                    words.add(temp);
                    temp="";
                    }  
                   
                   
                    

                  } 
                    
                }
             
                  else
                  {
                     if(!temp.isEmpty() && temp!=" ")
                    {
                     words.add(temp);
                      temp="";
                      
                    }
                     
                    temp+= fileInput.charAt(i);
                    words.add(temp);
                    temp="";

                            
                      
                  }
        }  
            else
            {
              
                     temp+= fileInput.charAt(i);
                
            }
        }
        }
        if(!temp.isEmpty())
        {
              words.add(temp);
              temp="";
        }
       
      return words;  
    }
      
 
 public  ArrayList<Token> generateTokens()
 {
     ArrayList<Token> tokenList = new ArrayList<Token>();
     int lineNo = 1;
     for(String w : words)
     { System.out.println(w);
         if("\n".equals(w))
         {
             lineNo++;
         }
      else if(isOp(w))
       {
               String classPart;
           String value =w;
           for (int i=0;i<keywords.length;i++) {
             if (w.equals(keywords[i][0])) {
                   classPart = keywords[i][1];
                   tokenList.add(new Token(value,classPart,lineNo));
                   break;
             }
         }
           
       }
       else if(isPunctuator(w))
       {
            String classPart=w;
           String value =w;
        tokenList.add(new Token(value,classPart,lineNo));
       }
       else if(isKW(w))
       {
           String classPart;
           String value =w;
           for (int i=0;i<keywords.length;i++) {
             if (w.equals(keywords[i][0])) {
                  classPart = keywords[i][1];
                  tokenList.add(new Token(value,classPart,lineNo));
                   break;
             }
         }
       }
       else if(isIdentifier(w))
       {
           String classPart="id";
           String value =w;
           
         tokenList.add(new Token(value,classPart,lineNo));
       }
       else if(isChar(w))
       {
                String classPart="char";
           String value =w;
           
         tokenList.add(new Token(value,classPart,lineNo));
       }
       else if(isDouble(w))
       {
                String classPart="double";
            String value =w;
           
         tokenList.add(new Token(value,classPart,lineNo));
       }
       else if(isString(w))
       {
                String classPart="String";
           String value =w;
           
         tokenList.add(new Token(value,classPart,lineNo));
        
       }
       else if (isIntconst(w))
       {
             String classPart="int";
           String value =w;
           
         tokenList.add(new Token(value,classPart,lineNo));
           
       }
       else{
         String classPart="Error";
           String value =w;
           
         tokenList.add(new Token(value,classPart,lineNo));
       }
     }
     
  
     return tokenList;
 }
 
 
 

public boolean isKW(String s){
         for (int i=0;i<keywords.length;i++) {
       
             if (s.equals(keywords[i][0])) {
        
                 return true;
             }
         }
         return false;
     };
public boolean isOp(String s){
     for(int i=0;i<operators.length;i++){
     if(s.equals(operators[i][0])){
     return true;
     }
     }
     return false;
     };



public boolean isPunctuator(String s){
         for (String punctuator : punctuators) {
             if (s.equals(punctuator)) {
                 return true;
             }
         }
return false;
};  

public boolean isIdentifier(String s){
Pattern p=Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isChar(String s){
Pattern p=Pattern.compile("'(\\\\[tvrnafb\\\\]|[^\\\\'])'");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isDouble(String s){
Pattern p=Pattern.compile("^[+-]?(0|([1-9][0-9]*))(\\.[0-9]+)?$");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isString(String s){
Pattern p=Pattern.compile("\".*?\"");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isIntconst(String s){
Pattern p=Pattern.compile("[+-]?[0-9]+");
Matcher m=p.matcher(s);
return m.matches();
};

 
}


    
    
