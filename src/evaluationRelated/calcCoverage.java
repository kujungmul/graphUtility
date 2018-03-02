package evaluationRelated;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class calcCoverage {
    public static void main(String[] args) throws IOException{
        
        
        // read file1
        // read file2
        HashMap<String, Integer> c1Cnt = new HashMap<String, Integer>();
        HashMap<String, Integer> c2Cnt = new HashMap<String, Integer>();
        
        BufferedReader br1 = new BufferedReader(new FileReader(args[0]));
        
        String temp="";
        int line = 1;
        while((temp = br1.readLine()) != null){
            if(temp.equals("")){
                continue;
            }
            StringTokenizer st = new StringTokenizer(temp);
            while(st.hasMoreTokens()){
                String nodeID = st.nextToken();
                if(c1Cnt.containsKey(nodeID)){
                    int tttt = c1Cnt.get(nodeID);
                    c1Cnt.put(nodeID, tttt+1);
                }
                else{
                    c1Cnt.put(nodeID, 1);
                }
            }
            line++;
        }
        
        line = 1;
        BufferedReader br2 = new BufferedReader(new FileReader(args[1]));
        while((temp = br2.readLine()) != null){
            if(temp.equals("")){
                continue;
            }
            StringTokenizer st = new StringTokenizer(temp);
            while(st.hasMoreTokens()){
                String nodeID = st.nextToken();
                if(c2Cnt.containsKey(nodeID)){
                    int tttt = c2Cnt.get(nodeID);
                    c2Cnt.put(nodeID, tttt+1);
                }
                else{
                    c2Cnt.put(nodeID, 1);
                }
            }
            line++;
        }
        
        
        br1.close();
        br2.close();
        
        
        
        for(Iterator<Entry<String, Integer>> it = c1Cnt.entrySet().iterator(); it.hasNext(); ){
            Map.Entry<String, Integer> entry = it.next();
            if(entry.getValue()<2){
                it.remove();
            }
        }
        
        for(Iterator<Entry<String, Integer>> it = c2Cnt.entrySet().iterator(); it.hasNext(); ){
            Map.Entry<String, Integer> entry = it.next();
            if(entry.getValue()<2){
                it.remove();
            }
        }
        
        Set<String> c1 = c1Cnt.keySet();
        Set<String> c2 = c2Cnt.keySet();
        
        int c1Size = c1Cnt.size();
        int c2Size = c2Cnt.size();
        
        c2.retainAll(c1);
        
        double precision = ((double)c2.size()/(double)c1Size);
        double recall = (double)c2.size()/(double)c2Size;
        double fmeasure = (2 * precision * recall) / (precision + recall);
        System.out.print(args[0]+"\t");
        System.out.print("c1Size_"+c1Size+"\tc2Size_"+c2Size+"\t");
        System.out.println("intersectionSize_"+c2.size() +"\tprecision_"+precision
                           +"\trecall_"+recall+"\tfmeasure_"+fmeasure);
    }
}
