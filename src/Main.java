import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class BST {
        Node root;

        Node insert(Node node, int value) {
            if (node == null) {
                return new Node(value);
            }

            if (value < node.value) {
                node.left = insert(node.left, value);
            } else if (value > node.value) {
                node.right = insert(node.right, value);
            }

            return node;
        }

        boolean search(Node node, int value) {
            if (node == null) {
                return false;
            }

            if (value == node.value) {
                return true;
            }

            if (value < node.value) {
                return search(node.left, value);
            }

            return search(node.right, value);
        }

        Node delete(Node node, int value) {
            if (node == null) {
                return null;
            }

            if (value < node.value) {
                node.left = delete(node.left, value);
            } else if (value > node.value) {
                node.right = delete(node.right, value);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node smallest = node.right;

                while (smallest.left != null) {
                    smallest = smallest.left;
                }

                node.value = smallest.value;
                node.right = delete(node.right, smallest.value);
            }

            return node;
        }
    }

    static class HashTable {
        ArrayList<LinkedList<Integer>> table = new ArrayList<>();
        int capacity;

        HashTable(int capacity) {
            this.capacity = capacity;

            for (int i = 0; i < capacity; i++) {
                table.add(new LinkedList<>());
            }
        }

        void insert(int value) {
            int index = Math.floorMod(value, capacity);

            if (!table.get(index).contains(value)) {
                table.get(index).add(value);
            }
        }

        boolean search(int value) {
            int index = Math.floorMod(value, capacity);
            return table.get(index).contains(value);
        }

        void delete(int value) {
            int index = Math.floorMod(value, capacity);
            table.get(index).remove(Integer.valueOf(value));
        }
    }

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};

        for (int size : sizes) {
            ArrayList<Integer> numbers = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                numbers.add(i);
            }

            Collections.shuffle(numbers);

            BST bst = new BST();
            HashTable hash = new HashTable(size / 2);

            System.out.println("\nElements: " + size);
            System.out.println("Total time in milliseconds:");

            long start = System.nanoTime();

            for (int number : numbers) {
                bst.root = bst.insert(bst.root, number);
            }

            long end = System.nanoTime();
            System.out.println("BST insert: " + (end - start) / 1000000.0);

            int found = 0;
            start = System.nanoTime();

            for (int number : numbers) {
                if (bst.search(bst.root, number)) {
                    found++;
                }
            }

            end = System.nanoTime();
            System.out.println("BST search: " + (end - start) / 1000000.0);
            System.out.println("BST found: " + found);

            // BST deletion
            start = System.nanoTime();

            for (int number : numbers) {
                bst.root = bst.delete(bst.root, number);
            }

            end = System.nanoTime();
            System.out.println("BST delete: " + (end - start) / 1000000.0);

            start = System.nanoTime();

            for (int number : numbers) {
                hash.insert(number);
            }

            end = System.nanoTime();
            System.out.println("Hash insert: " + (end - start) / 1000000.0);

            found = 0;
            start = System.nanoTime();

            for (int number : numbers) {
                if (hash.search(number)) {
                    found++;
                }
            }

            end = System.nanoTime();
            System.out.println("Hash search: " + (end - start) / 1000000.0);
            System.out.println("Hash found: " + found);

            start = System.nanoTime();

            for (int number : numbers) {
                hash.delete(number);
            }

            end = System.nanoTime();
            System.out.println("Hash delete: " + (end - start) / 1000000.0);
        }
    }
}