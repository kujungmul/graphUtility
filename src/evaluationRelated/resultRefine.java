package evaluationRelated;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class resultRefine {

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
		outputFileName = outputFileName+"_refine.dat";
		
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(outputFileName));

		int cnt = 1;
		
		String fileName="";
		String settingName="";
		String LFKNMI="";
		String NMIMAX="";
		String NMISUM="";
		
		writer.write(
				"settingName"+"\t"+
						"fileName"+"\t"+
						"NMIMAX"+"\t"+
						"LFKNMI"+"\t"+
						"NMISUM"+"\n"
						);

		while((line = reader.readLine()) != null){
			//System.out.println(line);
			if(cnt % 5 == 1){
				//filename
				String[] K = line.split("/");
				settingName = K[K.length-2];
			
				if(settingName.contains("]")){
					settingName = settingName.split("]")[1];
				}	

				fileName = K[K.length-1];
				fileName = fileName.replace("network.", "");
				fileName = fileName.replace("algo.", "");
				fileName = fileName.replace(".dat", "");
				
				
			}

			if(cnt % 5 == 2){
				String[] K = line.split("\t");
				NMIMAX=K[K.length-1];
				
			}

			if(cnt % 5 == 3){
				//ignore
			}

			if(cnt % 5 == 4){
				// meaningfulNMI
				String[] K = line.split("\t");
				LFKNMI=K[K.length-1];
			}

			if(cnt % 5 == 0){
				//ignore
				//initialize
				
				String[] K = line.split("\t");
				NMISUM=K[K.length-1];
				
				
				
				writer.write(settingName+"\t"+
						fileName+"\t"+
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
