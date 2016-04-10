package JTCL;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author audumbar
 *
 */

public class TextCatDriver {
	/**
	 * @param args
	 */
	
	
	/*
	public static void main(String[] args) {
		//createFP();
		printCategory();

	}
	
	
*/
	
	public static HashMap getCategory(List<String> meaningWordList) {
			
		String s = "";
		for(int i=0; i<meaningWordList.size(); i++){
			s += meaningWordList.get(i);
			s += "\n";
		}

		HashMap<String,Double> concernWeight = new HashMap();
		TextCategorizer guesser = new TextCategorizer();  
		//C:\Users\KHM\git\Final\FinalProject\resource\fingerPrinter
		guesser.setConfFiles("/Users/bagjeongho/Desktop/Final-master/Final-master/FinalProject/resource/fingerPrinter/textcat.conf");
		//System.out.println(guesser.categorize(s));
		//System.out.println(guesser.getCategoryDistances(s));

		
		Map<String, Integer> a = guesser.getCategoryDistances(s);
		//String b = a.toString();
		//System.out.println(a.keySet());
		//System.out.println(b);
		String c = a.values().toString();
		//System.out.println(b);
		String e = (String) c.subSequence(1, c.length()-1);
		String[] d = e.split(", ");
		 String interest[] = {"미식","단풍","낚시","역사","바다","캠핑","등산","섬","축제","도심","레저"};
		int realWeight[] = {1,2,3,4,5,6,7,8,9,10,11};
		double df[] ={1,2,3,4,5,6,7,8,9,10,11};
		double sum=0;
		double sum2=0;
		double temp[] = {1,2,3,4,5,6,7,8,9,10,11};
		for(int i=0;i<d.length;i++){
			d[i].trim();
			//System.out.println(d[i]);
			realWeight[i] = Integer.parseInt(d[i]);
			sum+=realWeight[i];
			//System.out.println(realWeight.length);
		}

		for(int i=0;i<realWeight.length;i++)
		{


			df[i] = (1/((realWeight[i]/sum)*100.0))*100;
			//System.out.println(realWeight[i]+" "+ df[i]);
			sum2+=df[i];

		}
		sum=0;
		for(int i=0;i<realWeight.length;i++)
		{
			df[i] = (df[i]/sum2)*100;
			//System.out.println(interest[i]+" "+ df[i]);
			sum+=df[i];
		}
		//System.out.println(sum/realWeight.length);


		double m = sum/realWeight.length;
		int su =0;
		for(int i=0;i<df.length;i++)
			su+= (m-df[i])*(m-df[i]);

		for(int i=0;i<df.length;i++)
			temp[i] = df[i];

		double var = su/df.length;
		double dev = Math.sqrt(var);
		double sum22 =0;
		double sum3 =0;
		double sum4 =0;
		double sum5 =0;
		double dev2n3;
		double dev3n4;
		double dev4n5;
		//2개냐 3개냐 4개냐
		sort(temp,temp.length);
		//System.out.println();
		
		
		//for(int i=0;i<temp.length;i++)
		//	System.out.print(temp[i]+" ");
		
		for(int i=0;i<2;i++)
			sum22+= temp[i];
		for(int i=0;i<3;i++)
			sum3+= temp[i];
		dev2n3 = (sum22/2)-(sum3/3);
		sum3=0;
	
		
		for(int i=0;i<3;i++)
			sum3+= temp[i];
		for(int i=0;i<4;i++)
			sum4+= temp[i];
		
		dev3n4 = (sum3/3)-(sum4/4);
		sum4=0;
		for(int i=0;i<4;i++)
			sum4+= temp[i];
		for(int i=0;i<5;i++)
			sum5+= temp[i];

		
		dev4n5 = (sum4/4)-(sum5/5);
		/*
		System.out.println();
		System.out.println("2�� 3�� ����"+dev2n3);
		System.out.println("3�� 4�� ����" +dev3n4);
		System.out.println("4�� 5�� ���� " + dev4n5);
		System.out.println("��ü ���� ǥ������"+dev);
		*/
		int picknum = pickdev(dev2n3,dev3n4,dev4n5,dev);
		
		for(int i=0;i<picknum;i++)
		{
			int k=0;
			for(int j=0;j<interest.length;j++)
			{
				if(temp[i] == df[j])
				{
					k++;
					concernWeight.put(interest[j], df[j]);
					System.out.println("개인관심사: "+interest[j]+"/ "+df[j]);
					if(k==picknum)
						break;
				}
				if(k==picknum)
					break;
				
			}
			if(k==picknum)
				break;
			
			
		}
	
		return concernWeight; 
		
	}
	public static int pickdev(double a, double b, double c, double origin)
	{
		if(origin<1.1 && origin >0.9)
			return 3;
		else if(a>b && a>c)
			return 2;
		else if(b>a && b>c)
			return 3;
		else if(c>a && c>b)
			return 4;
		else if(origin==0)
			return 0;
		else
			return 3;
	}


	public static void sort(double a[],int n)
	{
		double temp1;

		for(int i=0;i<n-1;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if(a[i] <=a[j])
				{
					temp1 = a[i];
					a[i] = a[j];
					a[j] = temp1;
				}
			}
		}
		

	}

/*
	public static void createFP() {
		FingerPrint fp = new FingerPrint();

		try {
			fp.create(new File("����.txt"));
			fp.setCategory("����");
			fp.save();
		}

		catch(Exception e) {
		}
	}
	*/
}