package evaluationRelated;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class communityToExtractInfo {
    
    public static void main(String[] args) throws IOException{
        
      
        
        HashMap<String, Integer> results = new HashMap<String, Integer>();
        
        String filename = args[0];
        BufferedReader reader = null;
        String line;
        String[] token;
        reader = new BufferedReader(new FileReader(filename));
        
        while((line = reader.readLine()) != null){
            
            token = line.split("\\s+");
            for(int j = 0; j < token.length; j++){
                String nodeID = token[j];
                if(results.containsKey(nodeID)){
                    int k = results.get(nodeID);
                    results.put(nodeID, k+1);
                }
                else{
                    results.put(nodeID, 1);
                }
            }
        }
        
        int om = 0;
        int on = 0;
        for(Entry<String, Integer> entry : results.entrySet()){
            int nodeOm = entry.getValue();
            if( nodeOm > om){
                om = nodeOm;
            }
            
            if( nodeOm > 1){
                on++;
            }
        }
        
        System.out.println("On_"+on+"\tOm_"+om);
        
        reader.close();
        
    }
    
    
    //
    //		HashMap<String, HashSet<Integer>> results = new HashMap<String, HashSet<Integer>>();
    //
    //		//args �뒗 community.dat �뙆�씪
    //		String filename = args[0];
    //		BufferedReader reader = null;
    //		String line;
    //		String[] token;
    //		reader = new BufferedReader(new FileReader(filename));
    //
    //		int cmtyID = 1;
    //		while((line = reader.readLine()) != null){
    //
    //			token = line.split("\\s+");
    //			for(int j = 0; j < token.length; j++){
    //				String nodeID = token[j];
    //				if(results.containsKey(nodeID)){
    //					results.get(nodeID).add(cmtyID);
    //				}
    //				else{
    //					HashSet<Integer> newHS = new HashSet<Integer>();
    //					newHS.add(cmtyID);
    //					results.put(nodeID, newHS);
    //				}
    //			}
    //			cmtyID++;
    //		}
    //		
    //		int om = 0;
    //		int on = 0;
    //		for(Entry<String, HashSet<Integer>> entry : results.entrySet()){
    //			int nodeOm = entry.getValue().size();
    //			if( nodeOm > om){
    //				om = nodeOm;
    //			}
    //			
    //			if( nodeOm > 1){
    //				on++;
    //			}
    //		}
    //
    //		System.out.println("On_"+on+"\tOm_"+om);
    //		
    //		reader.close();
    //
    //	}
}
