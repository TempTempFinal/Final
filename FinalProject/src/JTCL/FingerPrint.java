package JTCL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Thomas Hammerl
 * 
 * A FingerPrint maps so called NGrams to their number of occurences in the
 * corresponding text. It is able to categorize itself by comparing its
 * FingerPrint with the FingerPrints of a collection of categories. See
 * sdair-94-bc.pdf in the doc direcory of the jar-file for more information.
 *
 */

public class FingerPrint extends Hashtable<String, Integer> {
	private static final long serialVersionUID = -2790111752993314113L;
	private Pattern pattern = Pattern.compile("^_?[^0-9\\?!\\-_/]*_?$");
	private String category = "unknown";
	private HashMap<String,Integer> categoryDistances = new HashMap<String,Integer>();

	/**
	 * Set of NGrams sorted by the number of occurences in the text which was
	 * used for creating the FingerPrint.
	 * 
	 */
	private TreeSet<Entry<String, Integer>> entries;

	public FingerPrint() {
//		System.out.println("1");
	}

	/**
	 * creates a FingerPrint by reading the FingerPrint-file referenced by the
	 * passed path.
	 * 
	 * @param file
	 *            path to the FingerPrint-file
	 */

	public FingerPrint(String file) {
//		System.out.println("2");
		this.loadFingerPrintFromFile(file);
//		System.out.println("3");
	}

	/**
	 * creates a FingerPrint by reading it with the passed InputStream
	 * 
	 * @param is
	 *            InputStream for reading the FingerPrint
	 */

	public FingerPrint(InputStream is) {
//		System.out.println("4");
		this.loadFingerPrintFromInputStream(is);
//		System.out.println("5");
	}

	/**
	 * creates a FingerPrint by analysing the content of the given file.
	 * 
	 * @param file file to be analysed
	 */

	public void create(File file) {
//		System.out.println("6");		
		char[] data = new char[1024];
		String s = "";
		int read;
		
		try {
			FileReader fr = new FileReader(file);
			while ((read = fr.read(data)) != -1) {
				s += new String(data, 0, read);
			}
			fr.close();
//			System.out.println("7");
			this.create(s);
//			System.out.println("8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * fills the FingerPrint with all the NGrams and their numer of occurences
	 * in the passed text.
	 * 
	 * @param text
	 *            text to be analysed
	 */
// entrySet�� finger print�� ngram�� �󵵼��� ����.
	public void create(String text) {
//		System.out.println("9");
		this.clear();	
//		System.out.println("10");
		this.computeNGrams(2, 5, text);
		if (this.containsKey("_")) {
			int blanksScore = this.remove("_");
			this.put("_", blanksScore / 2);
		}
//		System.out.println("11");
		entries = new TreeSet<Entry<String, Integer>>(
				new NGramEntryComparator());
//		System.out.println("12");
		entries.addAll(this.entrySet());
//		System.out.println("13");
//		System.out.println(this.entrySet());
//		System.out.println();
	}

	/**
	 * adds all NGrams with the passed order occuring in the given text to the
	 * FingerPrint. For example:
	 * 
	 * text = "text" startOrder = 2, maxOrder = 2
	 * 
	 * so the NGrams added to the FingerPrint are:
	 * 
	 * "_t", "te", "ex", "xt", "t_"
	 * 
	 * all with a score (occurence) of 1
	 * 
	 * @param startOrder
	 * @param maxOrder
	 * @param text
	 */

	private void computeNGrams(int startOrder, int maxOrder, String text) {
//		System.out.println("14");
		String[] tokens = text.split("\\s");
		
		for(int order = startOrder; order <=maxOrder; ++order) {
			for (String token : tokens) {
//				token = "_" + token + "_";
				
				for (int i = 0; i < (token.length() - order + 1); i++) {
					String ngram = token.substring(i, i + order);
					Matcher matcher = pattern.matcher(ngram);

					if (!matcher.find()) {
						continue;
					} else if (!this.containsKey(ngram)) {
						this.put(ngram, 1);
					} else {
						int score = this.remove(ngram);
						this.put(ngram, ++score);
					}
				}
			}
		}
//		System.out.println("15");
	}

	/**
	 * categorizes the FingerPrint by computing the distance to the FingerPrints
	 * in the passed Collection. the category of the FingerPrint with the lowest
	 * distance is assigned to this FingerPrint.
	 * 
	 * @param categories
	 */

	public Map<String,Integer> categorize(Collection<FingerPrint> categories) {
//		System.out.println("16");
		int minDistance = Integer.MAX_VALUE;
		for (FingerPrint fp : categories) {
			int distance = this.getDistance(fp);
			this.getCategoryDistances().put(fp.getCategory(),distance);
			if (distance < minDistance) {
				minDistance = distance;
				this.category = fp.getCategory();
			}
		}
//		System.out.println("17");
		return this.getCategoryDistances();
	}

	public Map<String,Integer> getCategoryDistances() {
//		System.out.println("18");
		return this.categoryDistances;
	}

	/**
	 * computes and returns the distance of this FingerPrint to the FingerPrint
	 * passed to the method.
	 * 
	 * @param category
	 *            the FingerPrint to be compared to this one
	 * @return the distance of the passed FingerPrint to this FingerPrint
	 */

	private int getDistance(FingerPrint category) {
//		System.out.println("19");
		int distance = 0;
		int count = 0;
		
		for (Entry<String, Integer> entry : this.entries) {
			String ngram = entry.getKey();
			count++;
			if (count > 1000) {
				break;
			}

			if (!category.containsKey(ngram)) {
				distance += category.size();
				continue;
			}

			distance += Math.abs(this.getPosition(ngram)
					- category.getPosition(ngram));
		}
//		System.out.println("20");
		return distance;
	}

	/**
	 * reads a FingerPrint from the passed InputStream
	 * 
	 * @param is
	 *            InputStream to be read
	 */

	private void loadFingerPrintFromInputStream(InputStream is) {
//		System.out.println("21");
		entries = new TreeSet<Entry<String, Integer>>(
				new NGramEntryComparator());

//		System.out.println("22");
		MyProperties properties = new MyProperties();
//		System.out.println("23");
		properties.load(is);
//		System.out.println("24");
		for (Entry<String, String> entry : properties.entrySet()) {
			this.put(entry.getKey(), Integer.parseInt(entry.getValue()));
		}
		entries.addAll(this.entrySet());
//		System.out.println("25");
	}

	/**
	 * reads a FingerPrint from the file referenced by the passed path
	 * 
	 * @param file
	 *            FingerPrint file to be read
	 */

	private void loadFingerPrintFromFile(String file) {
//		System.out.println("26");
		File fpFile = new File(file);
		if (!fpFile.isDirectory()) {
			try {
				FileInputStream fis = new FileInputStream(file.toString());
				this.loadFingerPrintFromInputStream(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("27");
	}

	/**
	 * gets the position of the NGram passed to method in the FingerPrint. the
	 * NGrams are in descending order according to the number of occurences in
	 * the text which was used creating the FingerPrint.
	 * 
	 * @param key
	 *            the NGram
	 * @return the position of the NGram in the FingerPrint
	 */

	public int getPosition(String key) {
//		System.out.println("28");
		int pos = 1;

		int value = this.entries.first().getValue();

//		System.out.println("29");		
		for (Entry<String, Integer> entry : this.entries) {
			if (value != entry.getValue()) {
				value = entry.getValue();
				pos++;
			}

			if (entry.getKey().equals(key)) {
				return pos;
			}
		}

		return -1;
	}

	/**
	 * saves the fingerprint to a file named <categoryname>.lm in the execution
	 * path.
	 */

	public void save() {
//		System.out.println("30");
		File file = new File(this.getCategory() + ".lm");
		try {
			if (file.createNewFile()) {
//				System.out.println("31");		
				FileOutputStream fos = new FileOutputStream(file);
//				System.out.println("32");
				fos.write(this.toString().getBytes());
//				System.out.println("33");
				fos.close();
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * returns the category of the FingerPrint or "unknown" if the FingerPrint
	 * wasn't categorized yet.
	 * 
	 * @return the category of the FingerPrint
	 */

	public String getCategory() {
//		System.out.println("34");		
		return this.category;
	}

	/**
	 * returns the FingerPrint as a String in the FingerPrint file-format
	 */

	public String toString() {
//		System.out.println("35");
		String s = "";
		for (Entry<String, Integer> entry : entries) {
			s += entry.getKey() + "\t" + entry.getValue() + "\n";
		}
		return s;
	}


	/**
	 * sets the category of the FingerPrint
	 * 
	 * @param category
	 *            the category
	 */

	protected void setCategory(String category) {
//		System.out.println("37");		
		this.category = category;
	}
}