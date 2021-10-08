import Utili.myUtil;
import java.util.ArrayList;



public class myCompiler {
     
     String [][] keywords = {
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


    
 public ArrayList <String> breakWords(String fileInput){
        String temp="";
         ArrayList <String> allChars = new ArrayList();
         //"as"
        for(int i=0;i<fileInput.length();i++){
           
            if(fileInput.charAt(i)=='"'){
                    //string conditions
                if(!temp.isEmpty())
                {
                     allChars.add(temp);
                               temp="";
                }
                             
                     do{
                             
                            temp+= fileInput.charAt(i);
                            i++;
                            
                            if(i>=fileInput.length())
                            {
                               allChars.add(temp);
                               temp="";
                                break;
                            }
                            else if(fileInput.charAt(i)=='\r' && fileInput.charAt(i+1)=='\n')
                            {
                                
                                allChars.add(temp);
                                temp="";
                                i++;
                                temp+= fileInput.charAt(i);
                                allChars.add(temp);
                                temp="";
                                break;
                            }
                     }while(fileInput.charAt(i)!='"');
                      
                       if( myUtil.ifDoubQuo(fileInput, i)) //end on "
                    {
                       
                        temp+= fileInput.charAt(i);
                        allChars.add(temp);
                        temp="";
                     }
                    
                
                    }
                else if(fileInput.charAt(i)=='\''){
                      //char conditions
               
                     do{
                             if(!temp.isEmpty())
                {
                            allChars.add(temp);
                               temp="";
                }
                            
                            if(i>=fileInput.length())
                            {
                               allChars.add(temp);
                               temp="";
                                break;
                            }
                            else if(fileInput.charAt(i)=='\r' && fileInput.charAt(i+1)=='\n')
                            {
                                
                                allChars.add(temp);
                                temp="";
                                i++;
                                temp+= fileInput.charAt(i);
                                allChars.add(temp);
                                temp="";
                                break;
                            }
                     }while(fileInput.charAt(i)!='\'');
                      
                       if( myUtil.ifDoubQuo(fileInput, i)) //end on "
                    {
                       
                        temp+= fileInput.charAt(i);
                        allChars.add(temp);
                        temp="";
                     }
                    
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
                            allChars.add(temp);
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
                      
                        //allChars.add(temp);
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
                            allChars.add(temp);
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
                         // allChars.add(temp);
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
              allChars.add(temp);
              temp="";
         
        }
         i++;
         temp+= fileInput.charAt(i);
         allChars.add(temp);
         temp="";
       
            }   
                  
               if(!temp.isEmpty() ){
                    
                    allChars.add(temp);
                    temp="";
                }
                  
        }
              else if(fileInput.charAt(i)=='\n')
            {
         if(!temp.isEmpty())
        {
              allChars.add(temp);
              temp="";
        }
         
         temp+= fileInput.charAt(i);
         System.out.println(temp);
         allChars.add(temp);
              temp="";
       
            }

           else if(myUtil.isPunctuator(fileInput.charAt(i))){
                    
               
               if(fileInput.charAt(i)=='.' && myUtil.isNumeric(temp))
               {
                     temp+= fileInput.charAt(i);
               }
               else{
               if(!temp.isEmpty() ){
                     
                    allChars.add(temp);
                    temp="";
                }
                  
                    temp+= fileInput.charAt(i);
                  
                    allChars.add(temp);
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
                     allChars.add(temp);
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
                    allChars.add(temp);
                    temp="";
                    }  
                   
                   
                    

                  } 
                    
                }
             
                  else
                  {
                     if(!temp.isEmpty() && temp!=" ")
                    {
                     allChars.add(temp);
                      temp="";
                      
                    }
                     
                    temp+= fileInput.charAt(i);
                    allChars.add(temp);
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
              allChars.add(temp);
              temp="";
        }
       
      return allChars;  
    }
      
 
 public  ArrayList<Token> generateTokens(ArrayList<String> words)
 {
     ArrayList<Token> tokenList = new ArrayList<Token>();
     
      return tokenList;
 }
 
}


    
    

