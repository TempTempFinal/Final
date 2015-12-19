package Komoran;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;


public class Preprocessor {
	
	private static Komoran komoran = KomoranMannager.getInstance();
	

	public String timeLineToString(List<String> timeLineList){
		
		String str="";
		
		for(int i=0; i<timeLineList.size(); i++){
			str += timeLineList.get(i);
			str += "\n";
		}
		
		return str;
		
	}
	public  String preProcess(String timeLine){
	
		List<List<Pair<String,String>>> result = komoran.analyze(timeLine);
		String temp = "";
	
		try{
			for (List<Pair<String, String>> eojeolResult : result) {
				for (Pair<String, String> wordMorph : eojeolResult) {
					
						temp += wordMorph;
						temp += "\n";
					}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
			
		//System.out.println(temp);
		return temp;
	}
		
	public  List<String> getMeaningWord(String preProTileLine){
		
		BufferedReader reader = new BufferedReader(new StringReader(preProTileLine));
		String strLine = null;
		List<String> result = new ArrayList<String>();
		
		try{
			
			// ReadFile.txt 에서 한줄씩 읽어서 BufferedRaeder에 저장한다.
			while((strLine=reader.readLine())!=null){
				
				// 읽은 데이터(한줄)을 BufferedWriter에 쓴다.
				// 한줄씩 읽으므로, newLine() 메소드로 줄바꿈을 해준다.
				if (strLine.contains("NNP") || strLine.contains("NNG")) {//의미 있는 명사 단어만 추출해서 오브젝트 파일 작성 및 분류
					//필요없는 문자열 제거(Tokenization - align)
					strLine = strLine.replace("Pair", "");//,제거
					strLine = strLine.replace("[", "");//,제거
					strLine = strLine.replace("first", "");//,제거
					strLine = strLine.replace(",", "");// ,제거
					strLine = strLine.replace("second", "");// ,제거
					strLine = strLine.replace("=", "");// ,제거
					strLine = strLine.replace("]", "");// ,제거
					strLine = strLine.replace(".", "");// ,제거
					strLine = strLine.replace("NNG", "");// ,제거
					strLine = strLine.replace("NNP", "");// ,제거
					strLine = strLine.replace(" ", "");// ,제거
					result.add(strLine);
				}
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());	
		}
		
		return result;
	}
}
