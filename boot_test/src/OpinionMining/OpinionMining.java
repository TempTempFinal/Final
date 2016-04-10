package OpinionMining;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OpinionMining {
   private Map<String, Double> dictionary;
   private double sentiment;
   
   public OpinionMining() {
      dictionary = new HashMap<String, Double>();
   }

   public void create(String dictionaryPath) throws IOException {
      BufferedReader file = null;
      try {
         file = new BufferedReader(new FileReader(dictionaryPath));

         String line;
         while ((line = file.readLine()) != null) {
            if (!line.trim().startsWith("\\s")) {
               String[] data = line.split(" ");
               String sentiword = data[0];
                Double score = Double.parseDouble(data[1]);
               
               dictionary.put(sentiword, score);
            }
         }
      }catch (Exception e) {
         e.printStackTrace();
      }finally {
         if (file != null) {
            file.close();
         }
      }
   }
   
   public void calculate(String testPath) throws IOException {
      String line = null;
      double sum = 0.0;
      int count = 0;
      BufferedReader file = null;

      try {
         file = new BufferedReader(new FileReader(testPath));

         while ((line = file.readLine()) != null) {
            String[] tokens = line.split("\\s");
            for (int x=0; x<tokens.length;x++)
            {
               if(tokens[x].length() >= 2){
//                  System.out.println(tokens[x]);
                  for (int y=0; y<tokens[x].length()-1;y++){
//                     System.out.println(tokens[x].substring(y, y+2));

                     if (dictionary.containsKey(tokens[x].substring(y, y+2))){
                        System.out.println(tokens[x].substring(y, y+2));
                        System.out.println(dictionary.get(tokens[x].substring(y, y+2)));
                        count++;
                        sum += dictionary.get(tokens[x].substring(y, y+2));
                        break;
                     }

                  }                  
               }
            }
         }
         if(count != 0)
            setSentiment((double)Math.round(sum / count * Math.pow(10.0, 3)) / Math.pow(10.0, 3));
         else
             setSentiment(0);
     
   //      if(count == 0){
   //         System.out.println("���� : " + getSentiment()*100 + "%");
  //          System.out.println("���� : " + getSentiment()*100 + "%");
 //        }
 //        else if(getSentiment() > 0.0)
//            System.out.println("���� : " + getSentiment()*100 + "%");
//         else
//            System.out.println("���� : " + -getSentiment()*100 + "%");
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (file != null) {
            file.close();
         }
      }
   }
   
   public double getSentiment()
   {
       return sentiment;
   }
   public void setSentiment(double sentiment)
   {
       this.sentiment = sentiment;
   }
}