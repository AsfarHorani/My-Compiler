import java.util.ArrayList;


public class compiler {
       
       
       
       public ArrayList <String> breakWords(String fileInput){
        String temp="";
         ArrayList <String> allChars = new ArrayList();
         //"as"
        for(int i=0;i<fileInput.length();i++){
           
            if(fileInput.charAt(i)=='"' && i<fileInput.length()){
                    //string conditions
            
                     do{
                             
                            temp+= fileInput.charAt(i);
                            i++;
                            System.out.println(fileInput.charAt(i));
                     }while(i<fileInput.length() && fileInput.charAt(i)!='"');
                     temp+= fileInput.charAt(i);
                     allChars.add(temp);
                     temp="";
                    }
            
        else if(fileInput.charAt(i)=='\''){
             
                do{
                    
                          temp+= fileInput.charAt(i);
                          i++;
                           
                     }while(i<fileInput.length() && fileInput.charAt(i)!='\'');
                     temp+= fileInput.charAt(i);
                     allChars.add(temp);
                     temp="";
                    }
        
        else{
            //general
              if(fileInput.charAt(i)==' '){
                  
                if(temp.length()>0){
                    allChars.add(temp);
                    temp="";
                }
                  
        }
           else if(isPunctuator(fileInput.charAt(i))){
                    
                    if(temp.length()>0){
                    allChars.add(temp);
                    temp="";
                }
                    temp+= fileInput.charAt(i);
                    allChars.add(temp);
                    temp="";
                
            } else if(isOperator(fileInput.charAt(i)))
            {
                  if(temp.length()>0){
                    allChars.add(temp);
                    temp="";
                }
                    temp+= fileInput.charAt(i);
                    if(isOperator(fileInput.charAt(i++)))
                    {
                        temp+= fileInput.charAt(i);
                        allChars.add(temp);
                        temp=""; 
                    }
                      
                    
                    
                    
            }
             
            
            else{
            temp+=fileInput.charAt(i);
            }
            
                 }  
          
        }
        if(temp.length()>0)
        {
              allChars.add(temp);
              temp="";
        }
       
      return allChars;  
    }
    
       
       
         boolean isPunctuator(char c){
             char [] punc = {':', ';','(',')', '{', '}', '?', '[',']'};
             for(char ch : punc)
             {
                 if(c==ch)
                 {
                     
                     return true;
                     
                 }
             }
             return false;
        }
         
         boolean isOperator(char c)
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
}

    
    

