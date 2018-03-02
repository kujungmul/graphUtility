package preProcessingRelated;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class initialRefine {
    public static HashMap<Integer, Integer> originalIDtoRefineID = new HashMap<Integer, Integer>();
    
    
    public static void main(String[] args) throws Exception{
        
        if(args.length==1){
            doIter1(args[0]);
        }
        
        if(args.length==2){
            doIter1(args[0]);
            doIter2(args[1]);
        }
    }
    
    
    private static void doIter2(String inputFileName) throws Exception {
        String filename = inputFileName;
        BufferedReader reader = null;
        String line;
        String[] token;
        reader = new BufferedReader(new FileReader(filename));
        
        String outputFileName = filename;
        outputFileName = outputFileName.substring(0, filename.length()-4);
        outputFileName = outputFileName+"_refine.dat";
        
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(outputFileName));
        
        while((line = reader.readLine())!=null){
            
            if(line=="" || line.contains("#")){
                continue;
            }
            token = line.split("\\s+");
            
            for(int i = 0; i < token.length; i++ ){
                String newLabel = token[i];
                if(newLabel.contains(":")) {
                	continue;
                }
                
                int label = Integer.parseInt(newLabel);
                //System.out.print(originalIDtoRefineID.get(label));
                //System.out.print("\t");
               	int s = originalIDtoRefineID.get(label);
                writer.write(""+s);
                writer.write("\t");
            }
            writer.write("\n");
        }
        reader.close();
        writer.close();
        
    }
    
    
    private static void doIter1(String inputFileName) throws Exception {
        String filename = inputFileName;
        BufferedReader reader = null;
        String line;
        String[] token;
        reader = new BufferedReader(new FileReader(filename));
        
        String outputFileName = filename;
        outputFileName = outputFileName.substring(0, filename.length()-4);
        outputFileName = outputFileName+"_refine.dat";
        
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(outputFileName));
        
        boolean IsThereZero = false;
        
        int startID = 1;
        
        while((line = reader.readLine())!=null){
            
            if(line=="" || line.contains("#")){
                continue;
            }
            token = line.split("\\s+");
            
            int t1 = Integer.parseInt(token[0]);
            int t2 = Integer.parseInt(token[1]);
            
            if(originalIDtoRefineID.containsKey(t1)){ }
            
            else{
                originalIDtoRefineID.put(t1, startID);
                startID++;
            }
            
            if(originalIDtoRefineID.containsKey(t2)){ }
            
            else{
                originalIDtoRefineID.put(t2, startID);
                startID++;
            }
            
            writer.write(originalIDtoRefineID.get(t1)+"\t"+originalIDtoRefineID.get(t2)+"\n");
            
        }
        reader.close();
        writer.close();		
    }
}
