
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class mycompiler {

    public static void main(String[] args) throws IOException {
        ArrayList<String>  words = new ArrayList();
        compiler c = new compiler();

 
         String  data =readFile();
    
      words =   c.breakWords(data);
    
      for(String w : words){
  System.out.println(w);
  }
    
    }
  
    static String readFile() throws IOException
    {
           String  data = new String(Files.readAllBytes(Paths.get("C:\\Users\\hp\\Documents\\NetBeansProjects\\Compiler Project - by Asfar and Adeell\\src\\input.txt")));
       return data;
    }
   

}
