

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;


public class CcProject {

    public static void main(String[] args) throws IOException {
        ArrayList<String>  words = new ArrayList();
        compiler c = new compiler();

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
       JSONParser parser = new JSONParser();
      try {
         Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\data\\keywords.json"));
         JSONObject jsonObject = (JSONObject)obj;
       
         JSONArray keyWordsList = (JSONArray)jsonObject.get("keywords");
       
         Iterator iterator = keyWordsList.iterator();
         while (iterator.hasNext()) {
            System.out.println(iterator.next());
         }
     
      } catch(Exception e) {
         e.printStackTrace();
      }

      
    
    }
  
    static String readFile() throws IOException
    {
       String  data = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\src\\data\\input.txt")));
       return data;
    }

    
  
   

}
