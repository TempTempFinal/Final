package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelFrontTravlePost;
import com.lecture.finalproject.model.ModelHash;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelTravelPost;

public class DaoTravlePlaceTest {

	@Test
	public void test() {
		DaoTravlePlace one = new DaoTravlePlace();
		List<ModelFrontTravlePost> result = null;
		int result1 = 0;
	
		result = one.getFrontTravlePostByHashTag("바다", 1, 9);
		
		for(int i= 0;i<result.size(); i++){
			System.out.println(result);
		}
	}


}
