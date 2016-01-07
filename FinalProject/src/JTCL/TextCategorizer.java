package JTCL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Thomas Hammerl
 *
 * TextCategorizer is able to categorize texts by computing the similarity of
 * the FingerPrint of a text with a collection of the FingerPrints of the
 * categories.
 * 
 * ChangeLog:
 * @author audumbar
 * changed 'jarConfFile' value. 
 * changed the 'getDistance' method 
 *      - to increse the number of token comparisons
 *  - to return 'GENERAL' as DEFAULT category  
 *  
 * 
 */

public class TextCategorizer {
	private File confFile = null;

	private final static int UNKNOWN_LIMIT = 10;
	private final String jarConfFile = "C:\\Users\\park\\Documents\\Final\\FinalProject\\resource\\fingerPrinter\\textcat.conf";
//	private String webAppPath = "";
	private ArrayList<FingerPrint> categories = new ArrayList<FingerPrint>();

	public TextCategorizer() {
//		System.out.println("5-1");		
	}
	
	/**
	 * creates a new TextCategorizer with the given configuration file. the
	 * configuration file maps paths to FingerPrint files to categories which
	 * are used to categorize the texts passed to the TextCategorizer.
	 * 
	 * @param confFile
	 *            the path to the configuration file
	 */

	public TextCategorizer(String confFile) {
//		System.out.println("5-3");		
		setConfFiles(confFile);
//		System.out.println("5-4");				
	}

	/**
	 * sets the configuration file path.
	 * 
	 * @param confFile
	 *            the path to the configuration file
	 */

	public void setConfFiles(String confFile) {
	//	System.out.println("5-5");
		this.confFile = new File(confFile);
		loadCategories();
//		System.out.println("5-6");				
	}

	/**
	 * clears the categories-collection and fills it with the FingerPrints given
	 * in the configuration file.
	 */

	private void loadCategories() {
//		System.out.println("5-7");
		this.categories.clear();
		
		String path = "C:\\Users\\park\\Documents\\Final\\FinalProject\\resource\\fingerPrinter\\";

		try {
			MyProperties properties = new MyProperties();
//			System.out.println("5-8");		

			if (confFile == null) {
//				System.out.println("5-9");		
				properties.load(new FileInputStream(new File(jarConfFile)));
			} else {
//				System.out.println("5-10");		
				properties.load(new FileInputStream(confFile.toString()));
			}
			for (Entry<String, String> entry : properties.entrySet()) {
//				System.out.println("5-11");					
				FingerPrint fp;
//				System.out.println(entry.getKey());
//				System.out.println(entry.getValue());

				fp = new FingerPrint(new FileInputStream(new File(path + entry.getKey())));
//				System.out.println("5-12");		
				fp.setCategory(entry.getValue());
//				System.out.println("5-13");	
				this.categories.add(fp);
//				System.out.println("5-14");
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	/**
	 * categorizes the text passed to it
	 * 
	 * @param text
	 *            text to be categorized
	 * @return the category name given in the configuration file
	 */

	public String categorize(String text) {
//		System.out.println("5-15");		
		if(text.length() < UNKNOWN_LIMIT) {
//			System.out.println("5-16");		
			return "GENERAL";
		}
//		System.out.println("5-17");		
		FingerPrint fp = new FingerPrint();
//		System.out.println("5-18");		
		fp.create(text);
//		System.out.println("5-19");		
		fp.categorize(categories);
//		System.out.println("5-20");		
		return fp.getCategory();
	}

	/**
	 * categorizes only a certain amount of characters in the text. recommended
	 * when categorizing large texts in order to increase performance.
	 * 
	 * @param text text to be analysed
	 * @param limit number of characters to be analysed
	 * @return the category name given in the configuration file
	 */

	public String categorize(String text,int limit) {
		if(limit > (text.length()-1)) {
//			System.out.println("5-21");		
			return this.categorize(text);
		}
//		System.out.println("5-22");		
		return this.categorize(text.substring(0,limit));
	}

	/**
	 * categorizes a text but returns a map containing all categories and their
	 * distances to the text.
	 * 
	 * @param text text to be categorized
	 * @return HashMap with categories as keys and distances as values
	 */

	public Map<String,Integer> getCategoryDistances(String text) {
		if (this.categories.isEmpty()) {
//			System.out.println("5-23");		
			loadCategories();
//			System.out.println("5-24");		
		}
		
		FingerPrint fp = new FingerPrint();
//		System.out.println("5-25");		
		fp.create(text);
//		System.out.println("5-26");		
		fp.categorize(categories);
//		System.out.println("5-27");		
		return fp.getCategoryDistances();
	}

	/**
	 * reads from stdin til EOF is read. prints the determined category of the
	 * input and terminates afterwards.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
//		System.out.println("5-28");		
		byte[] data = new byte[1024];
		int read;
		String s = "";
		
		if (args.length == 0 || args[0].equals("-categorize")) {
			try {
				while ((read = System.in.read(data)) != (-1)) {
					s += new String(data, 0, read);
//					System.out.println(s);
					
					
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

//			System.out.println("5-29");		
			TextCategorizer guesser = new TextCategorizer();
			System.out.println(guesser.categorize(s));

		} else if(args.length > 1 && args[0].equals("-createfp")) {
//			System.out.println("5-30");					
			FingerPrint fp = new FingerPrint();

			fp.create(new File(args[1]));

			if(args.length > 2) {
				fp.setCategory(args[2]);
			} else {
				fp.setCategory(args[1].replaceAll("\\..*",""));
			}
			fp.save();
		} else {
			System.out.println("Options:\n\n\t-categorize\tdetermines language of stdin\n\t-createfp <file> <category>\tcreates fingerprint from file");
		}
	}
}