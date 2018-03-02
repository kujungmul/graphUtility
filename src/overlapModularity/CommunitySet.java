package overlapModularity;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;


import java.util.Set;

public class CommunitySet {
	private HashMap<Integer, HashSet<Node>> communityContainer;
	private HashMap<Integer, Integer> communitySelfEdges;
	private HashMap<Integer, HashMap<Integer, HashSet<Node>>> communityToNeighborNodes;
	
	public CommunitySet(){
		this.communityContainer = new HashMap<Integer, HashSet<Node>>();
		this.communitySelfEdges = new HashMap<Integer, Integer>();
		this.communityToNeighborNodes = new HashMap<Integer, HashMap<Integer, HashSet<Node>>>();
	}
	
	public HashMap<Integer, HashMap<Integer, HashSet<Node>>> getCommunityToNeighborNodes(){
		return communityToNeighborNodes;
	}

	public HashMap<Integer, Integer> getCommunitySelfEdges() {
		return communitySelfEdges;
	}

	public void setCommunitySelfEdges(HashMap<Integer, Integer> communitySelfEdges) {
		this.communitySelfEdges = communitySelfEdges;
	}

	public HashMap<Integer, HashSet<Node>> getCommunityContainer(){
		return this.communityContainer;
	}
	
	public int getCommunityInternalEdges(int cmty){
		// Check this one.
		if(communitySelfEdges.containsKey(cmty)){			
			return communitySelfEdges.get(cmty);
		}
		else{
			return 1;
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<<Community Nodes>>\n");

		for(Entry<Integer, HashSet<Node>> entry : communityContainer.entrySet()){
			sb.append("#cmtyID="+entry.getKey());
			sb.append("\t/ Nodes=");
			Iterator<Node> it = entry.getValue().iterator();
			while(it.hasNext()){
				sb.append(it.next().getElementId()+",");
			}
			sb.append("\n");
		}
		
		sb.append("\n");
		
		sb.append("<<SELF DENSITY>>\n");

		for(Entry<Integer, Integer> entry : communitySelfEdges.entrySet()){
			sb.append("#cmtyID="+entry.getKey());
			sb.append("\t #EDGES = "+entry.getValue()+"\t");
		}
		
		sb.append("\t\n<<CommunityNeighbor>>\n");
		for(Entry<Integer, HashMap<Integer, HashSet<Node>>> entry : communityToNeighborNodes.entrySet()){
			sb.append("#StartcmtyID="+entry.getKey());
			sb.append("\t/ target&neighbor="+entry.getValue()+"\n");
		}
		
		
		return sb.toString();
	}
	
	private void printX(HashMap<Integer, HashMap<Integer, HashSet<Node>>> ttt){
		for(Entry<Integer, HashMap<Integer, HashSet<Node>>> entry1 : ttt.entrySet()){
			System.out.print("Start = "+entry1.getKey()+" ");
			for(Entry<Integer, HashSet<Node>> entry2 : ttt.get(entry1.getKey()).entrySet()){
				System.out.print("Target = " + entry2.getKey()+" ");
				HashSet<Node> value = entry2.getValue();
				Iterator<Node> kkk = value.iterator();
				while(kkk.hasNext()){
					System.out.print(kkk.next().getElementId()+" ");
					
				}
				System.out.println("");
			}
		}
	}
	
	public void setupCommunity(HashMap<Integer, Node> container) {
		
		// SETUP COMMUNITY TO NODES
		for(Entry<Integer, Node> t : container.entrySet()){
			Iterator<Integer> ccm = t.getValue().getCommunitySet().iterator();
			while(ccm.hasNext()){
				int currentCmty = ccm.next();
				if(!communityContainer.containsKey(currentCmty)){
					HashSet<Node> newSet = new HashSet<Node>();
					newSet.add(t.getValue());
					communityContainer.put(currentCmty, newSet);
				}
				else{
					communityContainer.get(currentCmty).add(t.getValue());
				}
			}
		}
		
		if(Main.printOn==true){
			System.out.println("Part#1"+container);
		}
		
		// SETUP COMMUNITY TO NUMBER OF EDGES
		for(Entry<Integer, HashSet<Node>> entry : communityContainer.entrySet()){
			
			int sumOfWeight = 0;
			Iterator<Node> it = entry.getValue().iterator();
			while(it.hasNext()){	// the value it means node
				Node t = it.next();
				if(t.getCommunityToNeighbors().containsKey(entry.getKey()))
					sumOfWeight = sumOfWeight + t.getCommunityToNeighbors().get(entry.getKey()).size();
			}
			communitySelfEdges.put(entry.getKey(), sumOfWeight);
		}
		
		if(Main.printOn==true){
			System.out.println("Part2\t"+this);
		}
		
		
		for(Entry<Integer, HashSet<Node>> entry1 : communityContainer.entrySet()){
			HashSet<Node> entry1Set = entry1.getValue();
			int startCommunity = entry1.getKey();
			Iterator<Node> it = entry1Set.iterator();
			
			int endCommunity = -1;
			
			HashMap<Integer, HashSet<Node>> hmhs = new HashMap<Integer, HashSet<Node>>();
			
			while(it.hasNext()){
				
				HashSet<Node> subSet = new HashSet<Node>();

				Node currentNode = it.next();
				
				HashMap<Integer, HashSet<Node>> currentNodeCmtyToNeighbor = currentNode.getCommunityToNeighbors();
				
				for(Entry<Integer, HashSet<Node>> entry2 : currentNodeCmtyToNeighbor.entrySet()){

					if(startCommunity==entry2.getKey()){
						continue;
					}
					else{
						endCommunity = entry2.getKey();
						UnionOperation(subSet, entry2.getValue());
					}
				}	
				if(hmhs.containsKey(endCommunity)){
					HashSet<Node> kk = hmhs.get(endCommunity);
					UnionOperation(kk, subSet);
					hmhs.put(endCommunity, kk);
				}
				else{					
					hmhs.put(endCommunity, subSet);
				}

			}
			communityToNeighborNodes.put(startCommunity, hmhs);

		}
		
		if(Main.printOn==true){
			System.out.println("Part3\t"+this);
			printX(communityToNeighborNodes);
		}
		
		// Community Breaking
//		for(Entry<Integer, HashSet<Node>> entry : communityContainer.entrySet()){
//			
//			double tempDouble = 0.0f;
//			Iterator<Node> it = entry.getValue().iterator();
//			while(it.hasNext()){	// the value it means node
//				Node t = it.next();
//				tempDouble = tempDouble + t.getClusteringCoefficient();
//			}
//			cmtyToCC.put(entry.getKey(), tempDouble/entry.getValue().size());
//			
//			if(Main.printOn==true){
//				System.out.println("Community="+entry.getKey()+"\tCC="+tempDouble/entry.getValue().size());
//			}
//		}
	}

//	public double getCommunityCC(int originalCmtyID) {
//		return cmtyToCC.get(originalCmtyID);
//	}

	public void refreshNeighborSet(Node node, int newLabelCommunity) {
		
		//To current community, add itself
		Set<Integer> communitySet = node.getCommunitySet();
		
		Iterator<Integer> it = communitySet.iterator();
		while(it.hasNext()){
			int communityIndex = it.next();
			
			if(newLabelCommunity==communityIndex){
				continue;
			}
			
			HashMap<Integer, HashSet<Node>> temp = communityToNeighborNodes.get(communityIndex);
			if(temp.containsKey(newLabelCommunity) && temp.get(newLabelCommunity) != null){
				temp.get(newLabelCommunity).add(node);
			}
			else {
				HashSet<Node> kk = new HashSet<Node>();
				kk.add(node);
				temp.put(newLabelCommunity, kk);
			}
			communityToNeighborNodes.put(communityIndex, temp);
		}
		
		//To newly assigned community, add all the neighbors of node
		
		{
			
			HashMap<Integer, HashSet<Node>> communityToNeighbor = node.getCommunityToNeighbors();
			
			for(Entry<Integer, HashSet<Node>> entry : communityToNeighbor.entrySet()){
				// same community
				if(entry.getKey() == newLabelCommunity){
					//  ignore
				}
				
				else{
					HashSet<Node> temp = communityToNeighborNodes.get(newLabelCommunity).get(entry.getKey());

					int communityIdx = entry.getKey();
					HashSet<Node> t = entry.getValue();
					//temp.add(t);
					UnionOperation(temp, t);
					
					communityToNeighborNodes.get(newLabelCommunity).put(entry.getKey(), temp);
					
				}
			}
		}
	}

	private void UnionOperation(HashSet<Node> target, HashSet<Node> beSumed) {

		Iterator<Node> it = beSumed.iterator();
		while(it.hasNext()){
			Node tt = it.next();
			
			if(target==null){
				target = new HashSet<Node>();
				target.add(tt);
			}
			
			if(!target.contains(tt)){
				target.add(tt);				
			}
		}
	}
}
