package mao.machineLearning.KDT;

import java.util.Arrays;
import java.util.Comparator;
public class KdTree {
	
	class KdTreeNode{
		KdNode nodevalue;
		KdTreeNode left;
		KdTreeNode right;
		KdTreeNode(){}
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
	
	private double w = Double.MAX_VALUE;
	private KdTreeNode result = new KdTreeNode();
	public void searchKDTree(KdTreeNode targetNode,KdTreeNode root){
		searchHelper(targetNode,root,0);
		System.out.println(result.nodevalue.getX1()+","+result.nodevalue.getX2());
	}
	
	public void searchHelper(KdTreeNode target, KdTreeNode curNode, int dim){
		if(curNode.left==null&&curNode.right==null){
			double tmpw = Odistance(curNode,target);
			if(tmpw < w){
				w = tmpw;
				result = curNode;
			}
			return;
		}
		else{
			if(dim==0){
				if(target.nodevalue.getX1()<=curNode.nodevalue.getX1()){
					if(target.nodevalue.getX1()-w<=curNode.nodevalue.getX1()&&curNode.left!=null) 
						searchHelper(target,curNode.left,(dim+1)%2);
					if(target.nodevalue.getX1()+w>curNode.nodevalue.getX1()&&curNode.right!=null)
						searchHelper(target,curNode.right,(dim+1)%2);
				}
				else {
					if(target.nodevalue.getX1()+w>curNode.nodevalue.getX1()&&curNode.right!=null)
						searchHelper(target,curNode.right,(dim+1)%2);
					if(target.nodevalue.getX1()-w<=curNode.nodevalue.getX1()&&curNode.left!=null) 
						searchHelper(target,curNode.left,(dim+1)%2);
				}
			}
			else{
				if(target.nodevalue.getX2()<=curNode.nodevalue.getX2()){
					if(target.nodevalue.getX2()-w<=curNode.nodevalue.getX2()&&curNode.left!=null) 
						searchHelper(target,curNode.left,(dim+1)%2);
					if(target.nodevalue.getX2()+w>curNode.nodevalue.getX2()&&curNode.right!=null)
						searchHelper(target,curNode.right,(dim+1)%2);
				}
				else{
					if(target.nodevalue.getX2()+w>curNode.nodevalue.getX2()&&curNode.right!=null)
						searchHelper(target,curNode.right,(dim+1)%2);
					if(target.nodevalue.getX2()-w<=curNode.nodevalue.getX2()&&curNode.left!=null) 
						searchHelper(target,curNode.left,(dim+1)%2);
				}
			}
		}
	}
	
	public double Odistance(KdTreeNode node1, KdTreeNode node2){
		double x1 = Math.pow((node1.nodevalue.getX1()-node2.nodevalue.getX1()),2);
		double x2 = Math.pow((node1.nodevalue.getX2()-node2.nodevalue.getX2()),2);
		return Math.pow(x1+x2, 0.5);
	}
}
