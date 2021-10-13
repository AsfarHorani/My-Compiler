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
      
      
       ArrayList<Token>  tokens =  c.generateTokens();
              System.out.println("token Size: "+ tokens.size());
       for(Token t : tokens)
       {
           System.out.println(t);
       }
      
    
    }
  
    static String readFile() throws IOException
    {
       String  data = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\src\\data\\input.txt")));
       return data;
    }

    
  
   

}
