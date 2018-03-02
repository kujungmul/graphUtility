package benchmarkLFR;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;


public class lfrCommunityToOverlap {

	public static void main(String[] args) throws IOException{

		HashMap<String, HashSet<String>> results = new HashMap<String, HashSet<String>>();
		
		String filename = args[0];
		BufferedReader reader = null;
		String line;
		String[] token;
		reader = new BufferedReader(new FileReader(filename));
		
		while((line = reader.readLine()) != null){

			token = line.split("\\s+");
			String nodeID = "";
			for(int i = 0; i < token.length; i++){
				if(i==0){
					nodeID = token[i];
				}
				else{
					if(results.containsKey(token[i])){
						results.get(token[i]).add(nodeID);
					}
					else{
						HashSet<String> newHS = new HashSet<String>();
						newHS.add(nodeID);
						results.put(token[i], newHS);
					}
				}
			}
		}

		
		String outputFileName = filename;
		outputFileName = outputFileName.substring(0, filename.length()-4);
		outputFileName = outputFileName+"_overlap.dat";
		
		reader.close();

		
		
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(outputFileName));
			
		
		for(Entry<String, HashSet<String>> Entry : results.entrySet()){
			StringBuilder kk = new StringBuilder();
			HashSet<String> pp = Entry.getValue();
			for(Iterator<String> it = pp.iterator(); it.hasNext();){
				kk.append(it.next()+" ");
			}
			kk.append("\n");
			writer.write(kk.toString());
		}
		
		writer.close();

		
	}
}
