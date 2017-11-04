package mao.machineLearning.KDT;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KdNode n1 = new KdNode(2,3);
		KdNode n2 = new KdNode(5,4);
		KdNode n3 = new KdNode(9,6);
		KdNode n4 = new KdNode(4,7);
		KdNode n5 = new KdNode(8,1);
		KdNode n6 = new KdNode(7,2);
		KdNode t = new KdNode(9,3);
		KdNode[] nodearr = {n1,n2,n3,n4,n5,n6};
		KdTree tree = new KdTree();
		tree.root = tree.createTree(nodearr);
		System.out.println(tree.root.nodevalue.getX1()+","+tree.root.nodevalue.getX2());
		KdTree.KdTreeNode target = tree.new KdTreeNode(t);
		tree.searchKDTree(target, tree.root);
	}

}
