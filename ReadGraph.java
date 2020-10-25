import java.util.Arrays;
import java.util.Scanner;

public class ReadGraph {
	
	private static int len;
	private static String[] verts;
	private static AdjListNode [] fin;
	
	public static String[] readVertices(Scanner fileIn){
		len = fileIn.nextInt();
		fin = new AdjListNode[len];
		String [] vertices = new String[len];
		for(int i = 0; i < len; i++){
			vertices[i] = fileIn.next();
		}
		Arrays.sort(vertices);
		verts = vertices;
		return verts;
	}
	
	public static AdjListNode[] readEdgesAdjList(Scanner fileIn){
		AdjListNode[] list = new AdjListNode[len];
		
		for(int k = 0; k < len; k++){
			list[k] = new AdjListNode(-1, -1, null);
		}
		while (fileIn.hasNext()){
			String a = fileIn.next();
			String b = fileIn.next();
			int w = fileIn.nextInt();
			int aIndex = -1;
			int bIndex = -1;
			for (int x = 0; x < len; x++){
				if (verts[x].equals(a)){
					aIndex = x;
				}else if (verts[x].equals(b)){
					bIndex = x;
					
				}
			}
			AdjListNode end = list[aIndex];
			while(end.next != null){
				end = end.next;
			}
			AdjListNode next = new AdjListNode(-1, w, null);
			end.v = bIndex;
			end.next = next;
		}
		fin = list;
		return fin;
	}
	//need test for the readvertices and readedges.
	
}
