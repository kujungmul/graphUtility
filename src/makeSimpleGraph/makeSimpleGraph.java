package makeSimpleGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;



public class makeSimpleGraph {
	public static void main(String[] args) throws IOException {
		// [UserN] [SpatialN] [VisitingProbability(p)] [User relationship Probability(q)] [Spatial relationship Probability(r)]

		for(int i = 0; i < args.length; i++) {
			System.out.println("args["+i+"]="+args[i]);
		}
		
		TreeMap<String,HashSet<String>> userToSpatial = new TreeMap<String,HashSet<String>>();
		TreeMap<String,HashSet<String>> spatialToUser = new TreeMap<String,HashSet<String>>();

		TreeMap<String,HashSet<String>> userToUser = new TreeMap<String,HashSet<String>>();
		TreeMap<String,HashSet<String>> spatialToSpatial = new TreeMap<String,HashSet<String>>();
		
		HashSet<String> userSolution = new HashSet<String>();
		HashSet<String> spatialSolution = new HashSet<String>();
		
		
		int userN = Integer.parseInt(args[0]);
		int spatialN = Integer.parseInt(args[1]);
		
		double p = Double.parseDouble(args[2]);
		double q = Double.parseDouble(args[3]);
		double r = Double.parseDouble(args[4]);
		
		int workingNodes = -1;
		
		if(args.length==6) {
			workingNodes = Integer.parseInt(args[5]);
		}
		
		String[] userNElem = new String[userN];
		String[] spatialNElem = new String[spatialN];
		
		BufferedWriter userWriter = new BufferedWriter(new FileWriter("network.dat"));
		BufferedWriter spatialWriter = new BufferedWriter(new FileWriter("spatial.dat"));
		BufferedWriter checkInWriter = new BufferedWriter(new FileWriter("checkin.dat"));
		BufferedWriter solution = new BufferedWriter(new FileWriter("solution.dat"));
		
		
		initialize(userNElem, spatialNElem);
		
		initializeMapping(userNElem, spatialNElem, p, userToSpatial, spatialToUser);
		
		initializeUser(spatialToUser, userToUser, q);
		
		initializeSpatial(userToSpatial, spatialToSpatial, r);
		
		printStatus(userToSpatial);
		
		printStatus(userToUser);

		printStatus(spatialToSpatial);
		
		if(workingNodes >= 0) {
			int userNum = userNElem.length;
			int spatialNum = spatialNElem.length;
			
			ArrayList<Integer> userElem = getRandomNonRepeatingIntegers(workingNodes, 0, userNum-1);
			ArrayList<Integer> spatialElem = getRandomNonRepeatingIntegers(workingNodes, 0, spatialNum-1);
			
			System.out.println("Solution in user=");
			for(int i = 0; i < userElem.size(); i++) {
				String cur = userNElem[userElem.get(i)];
				System.out.print(cur+" ");
				userSolution.add(cur);
			}
			System.out.println("");
			
			System.out.println("Solution in spatial=");
			for(int i = 0; i < spatialElem.size(); i++) {
				String cur = spatialNElem[spatialElem.get(i)];
				System.out.print(cur+" ");
				spatialSolution.add(cur);
			}
			System.out.println("");
			
			
			makeClique(userToUser, userElem, userNElem);
			makeClique(spatialToSpatial, spatialElem, spatialNElem);
			makeClique(userElem, userNElem, spatialElem, spatialNElem, userToSpatial, spatialToUser);		
		}
		
		writeToFile(userToUser, userWriter);
		writeToFile(spatialToSpatial, spatialWriter);
		writeToFile(userToSpatial, checkInWriter);
		writeToFile(solution,spatialSolution, userSolution);
		
		printStatus(userToSpatial);
		
		printStatus(userToUser);

		printStatus(spatialToSpatial);
		
		userWriter.close();
		spatialWriter.close();
		checkInWriter.close();
		solution.close();

	}

	private static void writeToFile(BufferedWriter solution, HashSet<String> spatialSolution,
			HashSet<String> userSolution) throws IOException {
		
		for(String p : userSolution) {
			solution.write(p+" ");
		}
		solution.write("\n");
		
		for(String p : spatialSolution) {
			solution.write(p+" ");
		}
		solution.write("\n");
	}

	private static void makeClique(ArrayList<Integer> userElem, String[] userNElem, ArrayList<Integer> spatialElem,
			String[] spatialNElem, TreeMap<String, HashSet<String>> userToSpatial,
			TreeMap<String, HashSet<String>> spatialToUser) {
		
		int[] userList =  userElem.stream().mapToInt(Integer::intValue).toArray();
		int[] spatialList =  spatialElem.stream().mapToInt(Integer::intValue).toArray();
		
		for(int i = 0; i < userList.length; i++) {
			for(int j = 0; j < spatialList.length; j++) {
				String user = userNElem[userList[i]];
				String spatial = spatialNElem[spatialList[j]];
				userToSpatial.get(user).add(spatial);
				spatialToUser.get(spatial).add(user);
			}
		}
	}

	private static void makeClique(TreeMap<String, HashSet<String>> userToUser, ArrayList<Integer> userElem, String[] userNElem) {
		int[] ppp =  userElem.stream().mapToInt(Integer::intValue).toArray();
		for(int i = 0; i < ppp.length; i++) {
			for(int j = i+1; j < ppp.length; j++) {
				String userA = userNElem[ppp[i]];
				String userB = userNElem[ppp[j]];
				if(userToUser.containsKey(userA)) {
					userToUser.get(userA).add(userB);
				}
				else {
					HashSet<String> p = new HashSet<String>();
					p.add(userB);
					userToUser.put(userA, p);
				}
			}
		}
	}

	private static void writeToFile(TreeMap<String, HashSet<String>> data, BufferedWriter writer) throws IOException {
		for(Entry<String, HashSet<String>> entry : data.entrySet()) {
			for(String p : entry.getValue()) {
				writer.write(entry.getKey()+"\t"+p+"\n");
			}
		}
		
	}

	private static void initializeSpatial(TreeMap<String, HashSet<String>> userToSpatial,
			TreeMap<String, HashSet<String>> spatialToSpatial, double r) {

		double ranD;
		for(Entry<String, HashSet<String>> entry : userToSpatial.entrySet()) {
			HashSet<String> n1 = entry.getValue();
			String[] arr1 = n1.toArray(new String[n1.size()]);
			String[] arr2 = n1.toArray(new String[n1.size()]);
			for(int i = 0; i < arr1.length; i++) {
				for (int j = i+1; j < arr2.length; j++) {
					ranD = Math.random();
					if(ranD <= r) {
						if(spatialToSpatial.containsKey(arr1[i])) {
							HashSet<String> hs = spatialToSpatial.get(arr1[i]);
							hs.add(arr2[j]);
						}
						else {
							HashSet<String> hs = new HashSet<String>();
							hs.add(arr2[j]);
							spatialToSpatial.put(arr1[i], hs);
						}
					}
				}
			}
		}
	}

	private static void initializeUser(TreeMap<String, HashSet<String>> spatialToUser,
			TreeMap<String, HashSet<String>> userToUser, double q) {
		
		double ranD;
		
		for(Entry<String, HashSet<String>> entry : spatialToUser.entrySet()) {
			HashSet<String> n1 = entry.getValue();
			String[] arr1 = n1.toArray(new String[n1.size()]);
			String[] arr2 = n1.toArray(new String[n1.size()]);
			
			for(int i = 0; i < arr1.length; i++) {
				for (int j = i+1; j < arr2.length; j++) {
					ranD = Math.random();
					if(ranD <= q) {
						if(userToUser.containsKey(arr1[i])) {
							HashSet<String> hs = userToUser.get(arr1[i]);
							hs.add(arr2[j]);
						}
						else {
							HashSet<String> hs = new HashSet<String>();
							hs.add(arr2[j]);
							userToUser.put(arr1[i], hs);
						}
					}
				}
			}
		}
	}

	private static void printStatus(TreeMap<String, HashSet<String>> userToSpatial){
		
		System.out.println("================================================");
		for(Entry<String, HashSet<String>> entry : userToSpatial.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println("=========>");
			for(String st : entry.getValue()) {
				System.out.print(st+"  ");
			}
			System.out.println("\n");
		}
	}

	private static void initializeMapping(String[] userNElem, String[] spatialNElem, double p,
			TreeMap<String, HashSet<String>> userToSpatial, TreeMap<String, HashSet<String>> spatialToUser) {
		
		double ranD;
		for(int i = 0; i < userNElem.length; i++) {
			for(int j = 0; j < spatialNElem.length; j++) {
				ranD = Math.random();
				if(ranD <= p) {
					String currentUser = userNElem[i];
					String currentSpatial = spatialNElem[j];
					
					if(userToSpatial.containsKey(currentUser)) {
						HashSet<String> hs = userToSpatial.get(currentUser);
						hs.add(currentSpatial);
					}
					else {
						HashSet<String> hs = new HashSet<String>();
						hs.add(currentSpatial);
						userToSpatial.put(currentUser, hs);
					}
					
					if(spatialToUser.containsKey(currentSpatial)) {
						HashSet<String> hs = spatialToUser.get(currentSpatial);
						hs.add(currentUser);
					}
					else {
						HashSet<String> hs = new HashSet<String>();
						hs.add(currentUser);
						spatialToUser.put(currentSpatial, hs);
					}
				}
			}
		}
	}

	private static void initialize(String[] userNElem, String[] spatialNElem) {
		for(int i = 0; i < userNElem.length; i++) {
			userNElem[i] = ""+(i+1);
		}
		for(int j = 0; j < spatialNElem.length; j++) {
			spatialNElem[j] = "l_"+(j+1);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int getRandomInt(int min, int max) {
	    Random random = new Random();

	    return random.nextInt((max - min) + 1) + min;
	}

	public static ArrayList<Integer> getRandomNonRepeatingIntegers(int size, int min,
	        int max) {
	    ArrayList<Integer> numbers = new ArrayList<Integer>();

	    while (numbers.size() < size) {
	        int random = getRandomInt(min, max);

	        if (!numbers.contains(random)) {
	            numbers.add(random);
	        }
	    }

	    return numbers;
	}
	
}
