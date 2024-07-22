import static org.junit.Assert.assertEquals;

import java.beans.Transient;

import org.junit.Test;

public class TestTree {

	public static void main(String[] args) {

		Node<String, Integer> node1 = new Node<>("a", 10,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						new Node<>("g", 200, null, null)),
				null);

		Node<String, Integer> node2 = new Node<>("a", 10,
				null,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						new Node<>("g", 200, null, null)));

		Node<String, Integer> node3 = new Node<>("a", 10,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						null),
				new Node<>("g", 200, null, null));

		Node<String, Integer> node4 = new Node<>("a", 10, null,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						new Node<>("g", 200, null, null)));

		Tree<String, Integer> t = new Tree<>(node1);
		System.out.println(node1);
		// System.out.println(t.countNodes()); // what do we expect to see???
		// System.out.println(t.get("a"));
		// System.out.println(t.get("b"));
		// System.out.println(t.get("g"));
		// System.out.println(t.get("c"));
		// System.out.println(t.get("not here"));

		BST<String, Integer> bst = new BST<>(node4);
		System.out.println(bst.get("c"));
	}

	@Test
	public void testCountNodes() {

		Node<String, Integer> node1 = new Node<>("a", 10,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						new Node<>("g", 200, null, null)),
				null);

		Tree<String, Integer> tree1 = new Tree<>(node1);

		int count = tree1.countNodes();

		// TASK
		assertEquals(count, 4);

		// TASK: add one more test with one more tree different from the tree above

	}

	// TASK: add a test for the get method
	@Test
	public void testGet() {
		Node<String, Integer> node1 = new Node<>("a", 10,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						new Node<>("g", 200, null, null)),
				null);

		Tree<String, Integer> tree1 = new Tree<>(node1);

		int value = tree1.get("c");
		assertEquals(value, 80);

	}

	// test binary search tree height
	@Test
	public void testBSTHeight() {
		Node<String, Integer> node1 = new Node<>("a", 10, null,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						new Node<>("g", 200, null, null)));

		BST<String, Integer> tree1 = new BST<>(node1);

		int height = tree1.height();
		assertEquals(height, 3);
	}

	// test binary search tree get
	@Test
	public void testBSTGet() {
		Node<String, Integer> node1 = new Node<>("a", 10, null,
				new Node<>("c", 80,
						new Node<>("b", 200, null, null),
						new Node<>("g", 200, null, null)));

		BST<String, Integer> tree1 = new BST<>(node1);

		int value = tree1.get("g");
		assertEquals(value, 200);

	}

}