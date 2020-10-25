import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Queue;
import java.util.Stack;

public class Main {

	
	static String[] vertices; // The sorted list of vertex names
	static AdjListNode[] adjList; // The adjacency list
	
	/*
	* You are to write the following method. You will need to use a stack
	* or queue of integers here (you may use java.util.{Stack,Queue}
	*/
	public static String[] topSort() {
		//need to find out how to pass in indegree information
		Stack<String> s = new Stack<String>();
		String[] answer = new String[adjList.length];
		int count = 0;
		int[] indegree = new int[adjList.length];
		//will send in information regarding indegree to enqueue and dequeue creating proper order in
		//queue to be given as final topSort...need to also create check for cyclic graph
		for(int i = 0; i < adjList.length; i++){
			AdjListNode temp = adjList[i];
			while(temp.v != -1){
				indegree[temp.v]++;
				temp = temp.next;
			}
		}
		for(int i = 0; i < (adjList.length/2+1); i++){
			//System.out.println("loop one");
			for(int j = 0; j < adjList.length; j++){
				//System.out.println("loop two");
				AdjListNode next = adjList[j];
				if(indegree[j] == 0){
					//System.out.println(vertices[j]);
					s.push(vertices[j]);
					//System.out.println("push complete");
					count++;
					//System.out.println("count changed");
					indegree[j]--;
					//System.out.println("indegree changed");
					while (next.v != -1){
						indegree[next.v]--;
						//System.out.println(adjList[j].v);
						//System.out.println("second indegree changed");
						next = next.next;
						//System.out.println("next traversed");
					}
					//System.out.println("while loop finished");
				}
				//System.out.println("if passed");
			}
		}
		for(int i = adjList.length-1; i > -1; i--){
			if(count != adjList.length){
				answer[i] = null;
				
			}else{
				answer[i] = s.pop();
			}
		}
		return answer;
	}
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Incorrect number of args passed");
			System.exit(-1);
		}
		Scanner fileIn = new Scanner(new File(args[0]));
		vertices = ReadGraph.readVertices(fileIn);
		adjList = ReadGraph.readEdgesAdjList(fileIn);
		String[] sorted = topSort();
		/*
		 * At this point, "sorted" contains the vertices in topologically-sorted
		 * order (or all NULLs if the graph is not acyclic
		 */
		for(int i = 0; i < vertices.length; i++){
			System.out.println(sorted[i]);
		}
	}
}
