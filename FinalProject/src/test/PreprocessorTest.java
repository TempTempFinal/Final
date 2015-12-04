package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Komoran.Preprocessor;

public class PreprocessorTest {

	@Test
	public void test() {
		Preprocessor one = new Preprocessor();
		String result = null;
		
		result = one.preProcess("구조로 보이게 하려면 어떻게 해야 하나요?");
		//result = one.getMeaningWord(result);
		
		if(result != null){
			System.out.println(result);
			assertTrue(true);
		}else{
			
			assertTrue(false);
		}
		
	}

}
