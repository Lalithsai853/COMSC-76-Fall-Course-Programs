// Lalithsai Posam
// Binary Search Trees
// This program fully implements the binary search tree class.
import java.util.*;
public class FullTestBST {
    public static void main(String[] args) {
        System.out.println("Array-Arg Constructor:");
        Integer[] num_array = {8, 4, 3, 10, 1, 5, 2, 9};
        BST<Integer> integerTree = new BST<>(num_array); 
        System.out.print("\nInorder (sorted): ");
        integerTree.inorder();
        
        System.out.println(" ");
        // Create a BST using no arg constructor
        System.out.println("\nNo-Arg Constructor:");
        BST<String> nameTree = new BST<>();
        
        // Inserting elements
        nameTree.insert("Liam");
        nameTree.insert("Amelia");
        nameTree.insert("John");
        nameTree.insert("Ben");
        nameTree.insert("Jacob");
        nameTree.insert("Noah");
        nameTree.insert("Olivia");
        nameTree.insert("Will");

        // Searching for an element
        System.out.println("Is Noah in the tree? " + nameTree.search("Noah") + "\n");

        
        // Traversing Tree
        System.out.print("Inorder (sorted): ");
        nameTree.inorder();
        System.out.print("\nPostorder: ");
        nameTree.postorder();
        System.out.print("\nPreorder: ");
        nameTree.preorder();
        System.out.println("\nThe number of nodes in the tree is " + nameTree.getSize() + "\n");

        // Displaying root
        BST.TreeNode<String> root = nameTree.getRoot();
        System.out.println("The root of the tree is " + root.element);

        // Displaying the path to an element
        System.out.print("\nThe path from the root of the tree to Ben is: ");
        ArrayList<BST.TreeNode<String>> path = nameTree.path("Ben");
        for (int i = 0; path != null && i < path.size(); i++) {
            System.out.print(path.get(i).element + " ");
        }

        // Deleting an element
        nameTree.delete("Jacob");
        System.out.print("\nThe tree after deleting Jacob: ");
        nameTree.inorder();

        // Implementing the cursor
        System.out.println("\nTesting the Traversor:\n");
        Iterator<String> traversor = nameTree.iterator();
        int count  = 1;
        while (traversor.hasNext()) {
            System.out.print(traversor.next() + " ");
            if (count  == 5) {
                System.out.print("(Removing the 6th element that the iterator traverses) ");
                traversor.remove();
            }
            count += 1;
        }


        System.out.println(" ");

        // Checking if list is empty and clearing the list
        System.out.println("Is it Empty? " + nameTree.isEmpty());
        System.out.println("Clearing the list");
        nameTree.clear();
        nameTree.inorder();
        System.out.println("Is it Empty? " + nameTree.isEmpty());

    }
}


class BST<E extends Comparable<E>> extends AbstractTree<E> {
  protected TreeNode<E> root;
  protected int size = 0;

  /** Create a default binary tree */
  public BST() {
  }

  /** Create a binary tree from an array of objects */
  public BST(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      insert(objects[i]);
  }

  @Override /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return true; // Element is found
    }

    return false;
  }

  @Override /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e); // Create a new root
    else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          return false; // Duplicate node not inserted

      // Create the new node and attach it to the parent node
      if (e.compareTo(parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }

    size++;
    return true; // Element inserted successfully
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<>(e);
  }

  @Override /** Inorder traversal from the root */
  public void inorder() {
    inorder(root);
  }

  /** Inorder traversal from a subtree */
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  @Override /** Postorder traversal from the root */
  public void postorder() {
    postorder(root);
  }

  /** Postorder traversal from a subtree */
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  @Override /** Preorder traversal from the root */
  public void preorder() {
    preorder(root);
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  /** This inner class is static, because it does not access 
      any instance members defined in its outer class */
  public static class TreeNode<E extends Comparable<E>> {
    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  @Override /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode<E> getRoot() {
    return root;
  }

  /** Returns a path from the root leading to the specified element */
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list =
      new java.util.ArrayList<>();
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      list.add(current); // Add the node to the list
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else
        break;
    }

    return list; // Return an array list of nodes
  }

  @Override /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // Element is in the tree pointed at by current
    }

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left child
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;     
    }

    size--;
    return true; // Element deleted successfully
  }

  @Override /** Obtain an iterator. Use inorder. */
  public java.util.Iterator<E> iterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  private class InorderIterator implements java.util.Iterator<E> {
    // Store the elements in a list
    private java.util.ArrayList<E> list =
      new java.util.ArrayList<>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root*/
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null)return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    @Override /** More elements for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;

      return false;
    }

    @Override /** Get the current element and move to the next */
    public E next() {
      return list.get(current++);
    }

    @Override /** Remove the current element */
    public void remove() {
      delete(list.get(current)); // Delete the current element
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }
}


interface Tree<E> extends Iterable<E> {
    /** Return true if the element is in the tree */
    public boolean search(E e);
  
    /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e);
  
    /** Delete the specified element from the tree
     * Return true if the element is deleted successfully */
    public boolean delete(E e);
  
    /** Inorder traversal from the root*/
    public void inorder();
  
    /** Postorder traversal from the root */
    public void postorder();
  
    /** Preorder traversal from the root */
    public void preorder();
  
    /** Get the number of nodes in the tree */
    public int getSize();
  
    /** Return true if the tree is empty */
    public boolean isEmpty();
}
  

abstract class AbstractTree<E> implements Tree<E> {
    @Override /** Inorder traversal from the root*/
    public void inorder() {
    }
  
    @Override /** Postorder traversal from the root */
    public void postorder() {
    }
  
    @Override /** Preorder traversal from the root */
    public void preorder() {
    }
  
    @Override /** Return true if the tree is empty */
    public boolean isEmpty() {
      return getSize() == 0;
    }
}