

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;



public class CcProject {

    public static void main(String[] args) throws IOException {
 

        
       ArrayList<String>  words = new ArrayList();
       myCompiler c = new myCompiler();

      String  data =readFile();
      words =   c.breakWords(data);
      System.out.println("Total number of words: " + words.size()+ "\n");
      for(String w : words){
         if(w.contains("\n"))
          {
              System.out.println("line change detected ");
          }
         else{
         System.out.println(w);
         }
  
  }


      
    
    }
  
    static String readFile() throws IOException
    {
       String  data = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\src\\data\\input.txt")));
       return data;
    }

    
  
   

}
