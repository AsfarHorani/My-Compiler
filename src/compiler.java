import java.util.ArrayList;


public class compiler {
       
       
       
       public ArrayList <String> breakWords(String fileInput){
        String temp="";
         ArrayList <String> allChars = new ArrayList();
         //"as"
        for(int i=0;i<fileInput.length();i++){
           
            if(fileInput.charAt(i)=='"'){
                    //string conditions
            
                     do{
                             
                            temp+= fileInput.charAt(i);
                            i++;
                       
                     }while(i<fileInput.length() && fileInput.charAt(i)!='"' && fileInput.charAt(i)!='\n');
                      
                       if(ifDoubQuo(fileInput, i))
                    {
                       
                        temp+= fileInput.charAt(i);
                     }
                     allChars.add(temp);
                     temp="";
                    }
                else if(fileInput.charAt(i)=='\''){
                      //char conditions
                   do{
                             
                            temp+= fileInput.charAt(i);
                            i++;
                       
                     }while(i<fileInput.length() && fileInput.charAt(i)!='\'' && fileInput.charAt(i)!='\n');
                      
                       if(ifDoubQuo(fileInput, i))
                    {
                      
                        temp+= fileInput.charAt(i);
                     }
                     allChars.add(temp);
                     temp="";
                    }
                else if(fileInput.charAt(i)=='/')
                { //comment conditions
                
                    if(fileInput.charAt(i=i+1)=='*')
                    {  
                           temp+= fileInput.charAt(i=i-1); 
                          
                            i++;
                            temp+= fileInput.charAt(i); 
                            i++;
                        do{
                            
                    
                           
                            temp+= fileInput.charAt(i); 
                             i++;
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
                        }while(i<fileInput.length());
                        allChars.add(temp);
                           temp="";
                       
                    }else if(fileInput.charAt(i)=='/')
                    {
                          temp+= fileInput.charAt(i-1); 
                          temp+= fileInput.charAt(i); 
                          i++;
                     do{
                           temp+= fileInput.charAt(i); 
                             i++;
                  if(i<fileInput.length())
                  {
                      if(fileInput.charAt(i)=='\n')
                    { 
                         
                         break;
                    }
                  }
                   
               
                        }while(i<fileInput.length());
                          allChars.add(temp);
                          temp="";
                        
                    }
                    
                    
                    
                    
                   
                }
            
        else{
            //general
              if(fileInput.charAt(i)==' '){
                  
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
       
            }

           else if(isPunctuator(fileInput.charAt(i))){
                    
               
               if(fileInput.charAt(i)=='.' && isNumeric(temp))
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
                
                
            } else if(isOperator(fileInput.charAt(i)))
            {
                    if((fileInput.charAt(i)=='+' || fileInput.charAt(i)=='-') && shouldConcatNum(fileInput,i))
               {
                    allChars.add(temp);
                    temp="";
                    temp+= fileInput.charAt(i);
                    i++;
                    temp+= fileInput.charAt(i);
                    
               } else{
                            if(!temp.isEmpty() ){
                    allChars.add(temp);
                    temp="";
                }
                    temp+= fileInput.charAt(i);
                    if(isOperator(fileInput.charAt(i++)))
                    {
                        temp+= fileInput.charAt(i);
                    
                    }
                    
                        allChars.add(temp);
                        temp=""; 
                      
                    
                    
                  
                    
                    }
                
            
            }
             
            
            else{
         
                 temp+=fileInput.charAt(i);
            
           
            }
            
                 }  
          
        }
        if(!temp.isEmpty() )
        {
           
              allChars.add(temp);
              temp="";
        }
       
      return allChars;  
    }
    
       
       
         private boolean isPunctuator(char c){
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
         
        private boolean isOperator(char c)
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
         
         
         private static boolean isNumeric(String strNum) {
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
         
         
         private static boolean ifDoubQuo(String input , int i)
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
         
         private static boolean shouldConcatNum(String input , int i)
         {
          
         return false;
         }
         
}

    
    

