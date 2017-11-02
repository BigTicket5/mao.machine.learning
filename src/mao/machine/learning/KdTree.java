package mao.machine.learning;

import java.util.Arrays;
import java.util.Comparator;
public class KdTree {
	
	class KdTreeNode{
		KdNode nodevalue;
		KdTreeNode left;
		KdTreeNode right;
		KdTreeNode(KdNode value){
			this.nodevalue = value;
		}
	}
	
	public KdTreeNode root = null;
	
	public KdTreeNode createTree(KdNode[] arr){
		return createHelper(arr,0,0,arr.length-1);
	}
	
	/* build tree recursive */
	public KdTreeNode createHelper(KdNode[] arr, int direct,int start,int end){
		if(start>end) return null;
		Arrays.sort(arr,new KdNodeComparator(direct) );
		int mid = (end-start+1)/2;
		KdTreeNode root = new KdTreeNode(arr[mid]);
		if(start==end) return root;
		KdNode[] leftarr = Arrays.copyOfRange(arr, start, mid);
		KdNode[] rightarr = Arrays.copyOfRange(arr, mid+1, end+1);
		root.left = createHelper(leftarr,(direct+1)%2,0,leftarr.length-1);
		root.right = createHelper(rightarr,(direct+1)%2,0,rightarr.length-1);
		return root;
	}
	
	public static class KdNodeComparator implements Comparator<KdNode> {
		private static int dir = 0;
	    @SuppressWarnings("static-access")
		public KdNodeComparator(int direct) {
			this.dir = direct;
		}
		@Override
	    public int compare(KdNode a, KdNode b) {
			if(dir==0)return a.getX1() - b.getX1();
			else return a.getX2() - b.getX2();
	    }
	}
}
