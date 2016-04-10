package JTCL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelFeature;



public class JTCLHelper {

	public Map<String,Double> getConcern(List<String> meaingWordList){
	      
	      Map<String, Double> concern = TextCatDriver.getCategory(meaingWordList);
	      
	      return concern;
	   }   
	   
	   public Map<String,Double> getFeature(List<String> meaningWordList){
	      
	      Map<String,Double> feature = TextCatDriver.getCategory(meaningWordList);
	      
	      return feature;
	   }
	
}
		