package overlapModularity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;


public class Main {	
	
	public static boolean printOn = false;
	public static void main(String[] args) throws Exception{
		
		if(args.length < 2){
			System.out.println("Instruction : \n"
					+ "[COMPILE] javac *.java"
					+ "[RUN] java network.file myfile.file 1(minimum community size option)"
					+ "\n modularity conductance on om coverage "
					);
			return;
		}
		
		boolean minimumSizeCut = false;
		
		if(args.length==3 && args[2].equals("1")){
			minimumSizeCut = true;
		}
		
		// ARGUMENT SETTING
		String networkFileName = args[0];
		String communityFileName = args[1];
		
		// Graph Load
		Graph totalGraph = new Graph();
		totalGraph.readFile(networkFileName);
		
		// Read Community File and Set community info
		totalGraph.readCommunity(communityFileName, minimumSizeCut);
		if(printOn==true) System.out.println(totalGraph);

		
				
		// build neighbor's community relationships
		totalGraph.buildCommunityRelationship();
		if(printOn==true) System.out.println("buildingCommunityRelation finished!\n"+totalGraph);

		// building communitySet
		CommunitySet cS = new CommunitySet();
		cS.setupCommunity(totalGraph.getContainer());
		if(printOn==true) System.out.println("cS work finished!\n"+cS);
		
	
		double mod = calculateModularity(totalGraph, cS);
		
		double cond = calculateConductance(totalGraph, cS);
		
		int on = calculateOn(totalGraph);
		int om = calculateOm(totalGraph);
		double coverage = calculateCoverage(totalGraph);
		
		
		
		System.out.println(args[0]+"\t"+args[1]+"\t"+mod+"\t"+cond+"\t"+on+"\t"+om+"\t"+coverage);
		
		
	}
	
	
	private static double calculateCoverage(Graph totalGraph) {
		
		int cnt = 0;
		HashMap<Integer, Node> container = totalGraph.getContainer();
		for(Entry<Integer, Node> entry : container.entrySet()){
			Node cur = entry.getValue();
			if(cur.getCommunitySet().size() >= 1){
				cnt++;
			}
		}
		
		return (double)cnt / (double) container.size();
	}


	private static int calculateOm(Graph totalGraph) {
		
		int max = 0;
		HashMap<Integer, Node> container = totalGraph.getContainer();
		for(Entry<Integer, Node> entry : container.entrySet()){
			Node cur = entry.getValue();
			if(cur.getCommunitySet().size() >= max){
				max = cur.getCommunitySet().size();
			}
		}
		
		return max;
	}


	private static int calculateOn(Graph totalGraph) {
		int realOn = 0;
		
		HashMap<Integer, Node> container = totalGraph.getContainer();
		for(Entry<Integer, Node> entry : container.entrySet()){
			Node cur = entry.getValue();
			if(cur.getCommunitySet().size() >= 2){
				realOn++;
			}
		}
		
		return realOn;
	}
	
	
	private static double calculateConductance(Graph totalGraph, CommunitySet cS) {

		int communitySize = cS.getCommunityContainer().size();
		
		double subSum = 0.0f;
		
		HashMap<Integer, HashSet<Node>> hm = cS.getCommunityContainer();
		
		for(Entry<Integer, HashSet<Node>> entry : hm.entrySet()){
			double subCond = calcSubConductance(totalGraph, entry, cS);
			subSum = subSum + subCond;
		}
		
		double cond = (1.0/(double)communitySize) * subSum;
		
		return cond;
		
	}
	private static double calcSubConductance(Graph totalGraph, Entry<Integer, HashSet<Node>> entry, CommunitySet cS) {

		HashSet<Node> nodeSet = entry.getValue();
		int currentCmty = entry.getKey();
		
		int totalNodeSize = totalGraph.getTotalDegree();
		int V1Size = 0;
		
		Iterator<Node> it = nodeSet.iterator();
		while(it.hasNext()){
			Node cur = it.next();
			V1Size = V1Size + cur.getDegree();
		}
		
		
		int Denominator = 0;
		if(V1Size < totalNodeSize - V1Size){
			Denominator = V1Size;
		}
		else{
			Denominator = totalNodeSize - V1Size;
		}
		
		int Numerator = 0;
		it = nodeSet.iterator();
		while(it.hasNext()){
			Node cur = it.next();
			HashMap<Integer, HashSet<Node>> neighborToCmty = cur.getCommunityToNeighbors();
			for(Entry<Integer, HashSet<Node>> entry1 : neighborToCmty.entrySet()){
				if(entry1.getKey()==currentCmty){
					continue;
				}
				else{
					Numerator = Numerator + entry1.getValue().size();
				}
			}
		}
		
		if(Main.printOn){			
			System.out.print("Numerator="+Numerator+"\tDenomi"+Denominator+"\tConductance="+(double) Numerator / Denominator);
		}
		
		return  (double) Numerator / Denominator;
	}
	
	
	private static double calculateModularity(Graph totalGraph, CommunitySet cS) {
		
		int communitySize = cS.getCommunityContainer().size();
		
		double subSum = 0.0f;
		
		HashMap<Integer, HashSet<Node>> hm = cS.getCommunityContainer();
		
		for(Entry<Integer, HashSet<Node>> entry : hm.entrySet()){
			double mcrov = calcMcrov(totalGraph, entry, cS);
			subSum = subSum + mcrov;
		}
		
		double mov = (1.0/(double)communitySize) * subSum;
		
		return mov;
	}
	
	
	private static double calcMcrov(Graph totalGraph, Entry<Integer, HashSet<Node>> entry, CommunitySet cS) {
		double mcrov = 0.0f;
		
		HashSet<Node> nodeSet = entry.getValue();
		int currentCmty = entry.getKey();
		
		int ncr = nodeSet.size();
		int ncr2 = ncr * (ncr-1) / 2;
		int necr = cS.getCommunitySelfEdges().get(entry.getKey());
		
		Iterator<Node> it = nodeSet.iterator();
		
		double subSum = 0.0;
		
		while(it.hasNext()){
			Node current = it.next();
			int di =  current.getDegree();
			int si = current.getCommunitySet().size();
			
			int aij1, aij2;
			
			HashSet<Node> sameCommunity;
			//TODO: Check this one
			if(current.getCommunityToNeighbors() != null && current.getCommunityToNeighbors().containsKey(currentCmty)){
				sameCommunity = current.getCommunityToNeighbors().get(currentCmty);
				aij1 = sameCommunity.size();
			}
			else{
				aij1 = 0;
			}
			aij2 = di - aij1;
			
			//System.out.println("di = "+di+"\t"+"si = "+si+"\t"+"aij1 = "+aij1+"\taij2 = "+aij2+"\tnecr = "+necr+"\tnecr2 = "+ncr2);
			
			subSum = (double)subSum + ((double)aij1 - (double)aij2) / ((double)di*(double)si) * ((double)necr/(double)ncr2);
			//System.out.println("subSum = "+subSum);
			
		}
		
		mcrov = subSum / (double)ncr;
		//System.out.println("mcrov="+mcrov);
		return mcrov;
	}
}
