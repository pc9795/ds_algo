package gfg.ds.tree.binary_search_tree;

import gfg.ds.tree.binary_tree.BinaryTree;
import utils.Pointer;
import utils.Pair;

import java.util.ArrayDeque;

/** @noinspection WeakerAccess */
public class BinarySearchTree extends BinaryTree {

  /** t=O(h) =O(n); skewed tree =O(log n); complete tree */
  public boolean search(int key) {
    for (BinaryTreeNode curr = root; curr != null; ) {
      if (curr.data == key) {
        return true;
      }
      curr = key < curr.data ? curr.left : curr.right;
    }
    return false;
  }

  public BinarySearchTree insert(int... data) {
    for (Integer val : data) {
      this.insert(val);
    }
    return this;
  }

  /**
   * t=O(h) =O(n); skewed tree =O(log n); complete tree For recursive implementation check
   * `AVLTree.insert`
   */
  public BinarySearchTree insert(int data) {
    if (isEmpty()) {
      root = new BinaryTreeNode(data);
      return this;
    }
    BinaryTreeNode curr = root;
    BinaryTreeNode prev = null;
    for (; curr != null; ) {
      assert curr.data != data : "Duplicate data";
      prev = curr;
      curr = data < curr.data ? curr.left : curr.right;
    }
    assert prev
        != null; // Prev is empty only if tree is empty but we are checking that before hand.

    if (data < prev.data) {
      prev.left = new BinaryTreeNode(data);
    } else {
      prev.right = new BinaryTreeNode(data);
    }
    return this;
  }

  @Override
  public BinarySearchTree insertAtPos(String pos, int data) {
    throw new UnsupportedOperationException("This method is not applicable for BST");
  }

  @Override
  public int getAtPos(String pos) {
    throw new UnsupportedOperationException("This method is not applicable for BST");
  }

  /**
   * t=O(h) =O(n); skewed tree =O(log n); complete tree For recursive implementation check
   * `AVLTree.insert`
   */
  public BinarySearchTree delete(int key) {
    assert !isEmpty() : "Empty tree";

    BinaryTreeNode curr = root;
    BinaryTreeNode prev = null;
    for (; curr != null; ) {
      if (curr.data == key) {
        break;
      }
      prev = curr;
      curr = key < curr.data ? curr.left : curr.right;
    }

    assert curr != null : "Element not found";

    if (curr.left == null) {
      if (prev == null) {
        root = curr.right;
      } else {
        if (prev.right == curr) {
          prev.right = curr.right;
        } else {
          prev.left = curr.right;
        }
      }
      return this;
    } else if (curr.right == null) {
      if (prev == null) {
        root = curr.left;
      } else {
        if (prev.right == curr) {
          prev.right = curr.left;
        } else {
          prev.left = curr.left;
        }
      }
      return this;
    }
    // Node with two children.
    // Finding in-order successor for the node.
    BinaryTreeNode inOrderSucc = BinaryTree.inOrderSucc(curr);
    delete(inOrderSucc.data);
    inOrderSucc.left = curr.left;
    inOrderSucc.right = curr.right;
    if (prev == null) {
      root = inOrderSucc;
    } else {
      if (curr == prev.right) {
        prev.right = inOrderSucc;
      } else {
        prev.left = inOrderSucc;
      }
    }
    return this;
  }

  /** t=O(h) =O(n); left skewed tree =O(log n); complete tree */
  public int getMin() {
    assert isEmpty() : "Empty tree";

    BinaryTreeNode temp = root;
    while (temp.left != null) {
      temp = temp.left;
    }
    return temp.data;
  }

  /** t=O(h) =O(n); skewed tree =O(log n); complete tree */
  public Pair<BinaryTreeNode, BinaryTreeNode> findPreSuc(int key) {
    BinaryTreeNode pred = null, succ = null, curr = this.root;

    for (; curr != null; ) {
      if (curr.data == key) {
        if (curr.left != null) {
          pred = BinaryTree.inOrderPred(curr);
        }
        if (curr.right != null) {
          succ = BinaryTree.inOrderSucc(curr);
        }
        break;
      }
      if (key < curr.data) {
        succ = curr;
        curr = curr.left;
      } else {
        pred = curr;
        curr = curr.right;
      }
    }
    return new Pair<>(pred, succ);
  }

  /** t=O(h) =O(n); skewed tree =O(log n); complete tree */
  public int lca(int n1, int n2) {
    // If elements are not found
    if (!search(n1) || !search(n2)) {
      return -1;
    }

    // n2 will hold max value.
    n2 = Math.max(n1, n2);
    n1 = Math.min(n1, n2);

    BinaryTreeNode curr = root;
    for (; curr != null; ) {
      if (curr.data >= n1 && curr.data <= n2) {
        return curr.data;
      }
      curr = curr.data < n1 ? curr.right : curr.left;
    }
    return -1;
  }

  /** t=O(h) */
  public int ceil(int number) {
    BinaryTreeNode curr = this.root;
    int ceil = Integer.MIN_VALUE;
    while (curr != null) {
      if (curr.data == number) {
        return curr.data;
      }
      if (number < curr.data) {
        ceil = curr.data;
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }
    return ceil;
  }

  /**
   * t=O(n) The approach for simply checking that left node is less than and right node is greater
   * than will not work It will mark the following tree as BST which is not the case 4 / \ 2 5 /\ 1
   * 3
   */
  public static boolean isBST(BinaryTree bt) {
    return isBSTUtil(bt.root, new Pointer<>());
  }

  /** Using in-order to check whether it is BST or not. */
  private static boolean isBSTUtil(BinaryTreeNode root, Pointer<BinaryTreeNode> prev) {
    if (root == null) {
      return true;
    }
    if (!isBSTUtil(root.left, prev)) {
      return false;
    }
    if (prev.data != null && prev.data.data > root.data) {
      return false;
    }
    prev.data = root;
    return isBSTUtil(root.right, prev);
  }

  public static BinarySearchTree fromBinaryTree(BinaryTree binaryTree) {
    if (binaryTree == null || !isBST(binaryTree)) {
      return null;
    }
    BinarySearchTree bst = new BinarySearchTree();
    bst.root = binaryTree.root;
    return bst;
  }

  /** t=O(n) In order traversal using Stack */
  public static int kthSmallest(BinarySearchTree tree, int k) {
    ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
    BinaryTreeNode root = tree.root;

    for (; root != null; root = root.left) {
      stack.push(root);
    }
    for (; !stack.isEmpty(); ) {
      k--;
      BinaryTreeNode curr = stack.pop();
      if (k == 0) {
        return curr.data;
      }
      for (BinaryTreeNode temp = curr.right; temp != null; temp = temp.left) {
        stack.push(temp);
      }
    }
    return -1;
  }
}
