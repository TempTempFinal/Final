package OpinionMining;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class OpinionMining {
   private Map<String, Double> dictionary;
   private double positiveSentiment, negativeSentiment;
   
   public OpinionMining() {
      dictionary = new HashMap<String, Double>();
   }

   public void create(File diction) throws IOException {
      BufferedReader file = null;
      try {
         file = new BufferedReader(new FileReader(diction));

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
   
   public void calculateFile(String testPath) throws IOException {
      String line = null;
      double sumNegative = 0.0, sumPositive = 0.0;
      int countNegative = 0, countPositive = 0;
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
                        if(dictionary.get(tokens[x].substring(y, y+2)) > 0)
                        {
                            sumPositive += dictionary.get(tokens[x].substring(y, y+2));
                            countPositive++;
                        }
                        else
                        {
                            sumNegative += dictionary.get(tokens[x].substring(y, y+2));
                            countNegative++;
                        }
                        break;
                     }
                  }                  
               }
            }
         }
         if((countPositive != 0))
             setPositiveSentiment((double)Math.round(sumPositive / countPositive * Math.pow(10.0, 3)) / Math.pow(10.0, 3));
         else
             setPositiveSentiment(0);

         if((countNegative != 0))
             setNegativeSentiment((double)Math.round(sumNegative / countNegative * Math.pow(10.0, 3)) / Math.pow(10.0, 3));
         else
             setNegativeSentiment(0);       
     
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
   public void calculatePnn(String test) {
       double sumNegative = 0.0, sumPositive = 0.0;
       int countNegative = 0, countPositive = 0;
       
       StringTokenizer line = new StringTokenizer(test , " " );
          
       while (line.hasMoreTokens()) {
          String tokens = line.nextToken();
          if(tokens.length() >= 2){
//           System.out.println(tokens[x]);
             for (int y=0; y<tokens.length()-1;y++){
//              System.out.println(tokens[x].substring(y, y+2));

                if (dictionary.containsKey(tokens.substring(y, y+2))){
//                   System.out.println(tokens.substring(y, y+2));
//                   System.out.println(dictionary.get(tokens.substring(y, y+2)));
                   if(dictionary.get(tokens.substring(y, y+2)) > 0)
                   {
                       sumPositive += dictionary.get(tokens.substring(y, y+2));
                       countPositive++;
                   }
                   else
                   {
                       sumNegative += dictionary.get(tokens.substring(y, y+2));
                       countNegative++;
                   }
                   break;
                }
             }
          }
       }
       if((countPositive != 0))
           setPositiveSentiment((double)Math.round(sumPositive / countPositive * Math.pow(10.0, 3)) / Math.pow(10.0, 3));
       else
           setPositiveSentiment(0);

       if((countNegative != 0))
           setNegativeSentiment((double)Math.round(sumNegative / countNegative * Math.pow(10.0, 3)) / Math.pow(10.0, 3));
       else
           setNegativeSentiment(0);       
    }
   public double getPositiveSentiment()
   {
       return positiveSentiment;
   }
   public void setPositiveSentiment(double positiveSentiment)
   {
       this.positiveSentiment = positiveSentiment;
   }
   public double getNegativeSentiment()
   {
       return negativeSentiment;
   }
   public void setNegativeSentiment(double negativeSentiment)
   {
       this.negativeSentiment = negativeSentiment;
   }
}