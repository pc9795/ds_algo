package gfg.ds.hashing;

import gfg.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.*;

class TestApplications {
  @Test
  void testGetVerticalOrder() {
    BinaryTree bt =
        new BinaryTree()
            .insertAtRoot(1)
            .insertAtPos("0", 2)
            .insertAtPos("1", 3)
            .insertAtPos("00", 4)
            .insertAtPos("01", 5)
            .insertAtPos("10", 6)
            .insertAtPos("11", 7)
            .insertAtPos("110", 8)
            .insertAtPos("111", 9);
    Map<Integer, List<Integer>> ans = Applications.getVerticalOrder(bt);
    assert ans.get(-2).equals(Collections.singletonList(4));
    assert ans.get(-1).equals(Collections.singletonList(2));
    assert ans.get(0).equals(Arrays.asList(1, 5, 6));
    assert ans.get(1).equals(Arrays.asList(3, 8));
    assert ans.get(2).equals(Collections.singletonList(7));
    assert ans.get(3).equals(Collections.singletonList(9));
  }

  @Test
  void testIsArraySubsetOfAnother() {
    int[] arr1 = {11, 1, 1, 13, 21, 3, 7};
    int[] arr2 = {11, 3, 7, 1, 1};
    assert Applications.isArraySubsetOfAnother(arr1, arr2);

    arr1 = new int[] {10, 5, 2, 23, 19};
    arr2 = new int[] {19, 5, 3};
    assert !Applications.isArraySubsetOfAnother(arr1, arr2);
  }

  @Test
  void testUnion() {
    assert Applications.union(Arrays.asList(1, 2, 2, 3), Arrays.asList(3, 4, 4, 5, 6))
        .equals(Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6));
    assert Applications.union(Arrays.asList(10, 15, 4, 20), Arrays.asList(8, 4, 2, 10))
        .equals(Arrays.asList(2, 4, 20, 8, 10, 15));
  }

  @Test
  void testIntersection() {
    assert Applications.intersection(Arrays.asList(1, 2, 3, 4, 4), Arrays.asList(3, 4, 4, 5))
        .equals(Arrays.asList(3, 4, 4));
  }

  @Test
  void testFindPairWithGivenSum() {
    int[] arr = {1, 4, 45, 6, 10, 8};
    int n = 16;
    Pair<Integer, Integer> ans = Applications.findPairWithGivenSum(arr, n);
    assert ans != null;
    assert ans.equals(new Pair<>(6, 10));
    assert Applications.findPairWithGivenSum(arr, 100) == null;
  }

  @Test
  void testIsDuplicateElementWithinKDistance() {
    int[] arr = {1, 2, 3, 4, 1, 2, 3, 4};
    assert !Applications.isDuplicateElementWithinKDistance(arr, 3);

    arr = new int[] {1, 2, 3, 1, 4, 5};
    assert Applications.isDuplicateElementWithinKDistance(arr, 3);
  }

  @Test
  void testFindItineraryFromGivenListOfTickets() {
    Map<String, String> fromToMap = new HashMap<>();
    fromToMap.put("Chennai", "Bangalore");
    fromToMap.put("Bombay", "Delhi");
    fromToMap.put("Goa", "Chennai");
    fromToMap.put("Delhi", "Goa");

    assert Applications.findItineraryFromGivenListOfTickets(fromToMap)
        .equals(Arrays.asList("Bombay", "Delhi", "Goa", "Chennai", "Bangalore"));
  }

  @Test
  void testNumberOfEmployeesUnder() {
    char[][] employeeManagerPair = {
      {'A', 'C'},
      {'B', 'C'},
      {'C', 'F'},
      {'D', 'E'},
      {'E', 'F'},
      {'F', 'F'}
    };
    assert Applications.numberOfEmployeesUnder(employeeManagerPair, 'A') == 0;
    assert Applications.numberOfEmployeesUnder(employeeManagerPair, 'B') == 0;
    assert Applications.numberOfEmployeesUnder(employeeManagerPair, 'C') == 2;
    assert Applications.numberOfEmployeesUnder(employeeManagerPair, 'D') == 0;
    assert Applications.numberOfEmployeesUnder(employeeManagerPair, 'E') == 1;
    assert Applications.numberOfEmployeesUnder(employeeManagerPair, 'F') == 5;
  }

  @Test
  void testGetAnagramsTogether() {
    List<String> words = Arrays.asList("cat", "dog", "tac", "god", "act", "gdo");

    List<String> anagramsTogether = Applications.getAnagramsTogether(words);

    assert anagramsTogether.size() == 6;
    assert isAnagram(anagramsTogether.get(0), "cat");
    assert isAnagram(anagramsTogether.get(1), "cat");
    assert isAnagram(anagramsTogether.get(2), "cat");
    assert isAnagram(anagramsTogether.get(3), "dog");
    assert isAnagram(anagramsTogether.get(4), "dog");
    assert isAnagram(anagramsTogether.get(5), "dog");
  }

  private boolean isAnagram(String word, String possibleAnagram) {
    Map<Character, Integer> wordCount = new HashMap<>();
    for (char ch : word.toCharArray()) {
      wordCount.put(ch, wordCount.getOrDefault(ch, 0) + 1);
    }
    for (char ch : possibleAnagram.toCharArray()) {
      if (!wordCount.containsKey(ch)) {
        return false;
      }
      wordCount.put(ch, wordCount.get(ch) - 1);
      if (wordCount.get(ch) == 0) {
        wordCount.remove(ch);
      }
    }

    return wordCount.isEmpty();
  }
}
