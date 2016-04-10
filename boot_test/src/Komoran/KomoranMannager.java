package Komoran;

import com.mysql.jdbc.Connection;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;

public class KomoranMannager {
	
	private static Komoran komaran = null;
	
	public static synchronized Komoran getInstance() {
		
		if(komaran == null){
			try{
				komaran = new Komoran("/Users/HYUNMIN-KIM/git/Final/boot_test/resource/models-full");	
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}		
		return komaran;
	}

}
