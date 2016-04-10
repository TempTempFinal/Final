package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.service.FriendsInfoHelper;

import Komoran.Preprocessor;

public class PreprocessorTest {

	@Test
	public void test() {
		
		FileReader fr = null;
		BufferedReader br = null;
		DaoTravlePlace db = new DaoTravlePlace();
		
		
		try{
			
			fr = new FileReader("/Users/bagjeongho/Documents/friendshipDataset.txt");
			br = new BufferedReader(fr);
			
			
			String s;
			
			while((s=br.readLine())!=null){
				String value[] = s.split("\\|");	
				db.test(Double.parseDouble(value[0]), Double.parseDouble(value[1]));
			}
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			System.out.println("Finish!!!");
		}
	}
}
