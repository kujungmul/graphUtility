package evaluationRelated;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class resultRefineLFR {

	public static void main(String[] args) throws IOException{


		//args is result file

		//String filename = "";
		String filename = args[0];
		BufferedReader reader = null;
		String line;
		String[] token;
		reader = new BufferedReader(new FileReader(filename));
		
		String outputFileName = filename;
		outputFileName = outputFileName.substring(0, filename.length()-4);
		outputFileName = outputFileName+"_LFR_refine.dat";
		
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(outputFileName));

		int cnt = 1;
		
		String category="";
		String fileName="";
		String settingName="[AI-1-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._2000._om._2._minc._20._maxc._150";
		String LFKNMI="";
		String NMIMAX="";
		String NMISUM="";
		
		String[] tttt = null;
		
		String L2 = null;
		String OMEGA = null;
		String N;
		String degree;
		String maxDegree;
		String mu;
		String on;
		String om;
		String minC;
		String maxC;
		String tt = "N"+"\t"+
					"Degree"+"\t"+
					"maxDegree"+"\t"+
					"mu"+"\t"+
					"on"+"\t"+
					"om"+"\t"+
					"minC"+"\t"+
					"maxC"+"\t";
		writer.write("category"+"\t");
		writer.write("fileName"+"\t");

		writer.write(tt);
		
		writer.write(
						"OMEGA"+"\t"+
									"L2"+"\t"+
						"NMIMAX"+"\t"+
						"LFKNMI"+"\t"+
						"NMISUM"+"\n"
						);

		while((line = reader.readLine()) != null){
//			System.out.println(line);
			if(cnt % 7 == 1){
				//filename
				System.out.println(line);
				String[] K = line.split("/");
				settingName = K[K.length-2];
				//System.out.println("-------"+settingName);
				if(settingName.contains("]")){
					category = settingName.split("]")[0];
					settingName = settingName.split("]")[1];
				}	
				//System.out.println("-------"+category);

				
				fileName = K[K.length-1];
				fileName = fileName.replace("network.", "");
				fileName = fileName.replace("algo.", "");
				fileName = fileName.replace(".dat", "");
				
				//[AI-1-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._2000._om._2._minc._20._maxc._150
				//System.out.println(fileName);
				System.out.println(settingName);
				tttt = settingName.split("_");

				System.out.println("fileName:"+fileName);

				for(int i = 0; i < tttt.length-1; i++){	// we should not remove last one
					tttt[i] = tttt[i].substring(0, tttt[i].length() - 1);
				}
				
//				System.out.println(tttt[1]);
//				System.out.println(tttt[3]);
//				System.out.println(tttt[5]);
//				System.out.println(tttt[7]);
//				System.out.println(tttt[9]);
//				System.out.println(tttt[11]);
//				System.out.println(tttt[13]);
//				System.out.println(tttt[15]); 	
				
				
				
			}
			

			if(cnt % 7 == 4){
				String[] K = line.split("\t");
				NMIMAX=K[K.length-1];
				
			}
			

			if(cnt % 7 == 2){
				String[] K = line.split(":");
				OMEGA=K[K.length-1];
				
			}
			
			

			if(cnt % 7 == 3){
				String[] K = line.split(":");
				L2=K[K.length-1];
				
			}

			if(cnt % 7 == 5){
				//ignore
			}

			if(cnt % 7 == 6){
				// meaningfulNMI
				String[] K = line.split("\t");
				LFKNMI=K[K.length-1];
			}

			if(cnt % 7 == 0){
				//ignore
				//initialize
				
				String[] K = line.split("\t");
				NMISUM=K[K.length-1];
				
				
				
				writer.write(category+"\t");

				writer.write(fileName+"\t");
				
				//TODO: �뿬湲곗뿉�꽌 network �젙蹂� �꽔�뼱以섏빞 �븿. 
				//write.write("")
				writer.write(tttt[1]+"\t");
				writer.write(tttt[3]+"\t");
				writer.write(tttt[5]+"\t");
				writer.write(tttt[7]+"\t");
				writer.write(tttt[9]+"\t");
				writer.write(tttt[11]+"\t");
				writer.write(tttt[13]+"\t");
				writer.write(tttt[15]+"\t"); 	
				
				
				writer.write(
						OMEGA+"\t"+
								L2+"\t"+
						NMIMAX+"\t"+
						LFKNMI+"\t"+
						NMISUM+"\n"
						);
			}
			cnt++;
		}
		reader.close();	
		writer.close();
		System.out.println("Refining is finished!");
	}
}
