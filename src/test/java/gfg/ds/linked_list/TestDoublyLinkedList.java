package gfg.ds.linked_list;

import gfg.ds.tree.binary_search_tree.BinarySearchTree;
import gfg.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Test;

class TestDoublyLinkedList {
  @Test
  void testReverse() {
    DoublyLinkedList list = new DoublyLinkedList().append(1, 2, 3, 4, 5).reverse();
    DoublyLinkedList expected = new DoublyLinkedList().append(5, 4, 3, 2, 1);
    assert list.equals(expected);
  }

  @Test
  void testQuickSortInDLL() {
    DoublyLinkedList dll = new DoublyLinkedList().append(25, 12, 30, 10, 16);
    DoublyLinkedList.quickSort(dll);
    DoublyLinkedList expected = new DoublyLinkedList().append(10, 12, 16, 25, 30);
    assert dll.equals(expected);
  }

  @Test
  void testToBST() {
    DoublyLinkedList list = new DoublyLinkedList().append(1, 2, 3, 4, 5, 6);
    BinarySearchTree bst = DoublyLinkedList.toBST(list);
    BinaryTree expected = new BinaryTree().insertAtRoot(4);
    expected
        .insertAtPos("0", 2)
        .insertAtPos("00", 1)
        .insertAtPos("01", 3)
        .insertAtPos("1", 6)
        .insertAtPos("10", 5);
    expected = BinarySearchTree.fromBinaryTree(expected);
    assert expected.equals(bst);
  }
}
