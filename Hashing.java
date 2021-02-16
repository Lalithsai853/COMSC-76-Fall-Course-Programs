// Lalithsai Posam
// Hashing Assignment
// This program implements the MyMap interface using open addressing qith quadratic probing
import java.util.*;

public class Hashing {
  public static void main(String[] args) {
    NewHashMap<String, Integer> scores = new NewHashMap<>();
    // Inserts elements into hashmap
    scores.put("Liam", 98);
    scores.put("Olivia", 82);
    scores.put("Amelia", 92);
    scores.put("John", 77);
    scores.put("Jared", 86);
    scores.put("Ava", 90);
    scores.put("Ben", 62);
    scores.put("Noah", 75);

    // Displays map
    System.out.println("This is the map: " + scores);

    // Displays the entry set
    System.out.println("Entries within a set: " + scores.entrySet());

    // Displays value for on e key
    System.out.println("Amelia's score is " + scores.get("Amelia"));

    // Checks if the map contains a key
    System.out.println("Is Jared's score in the list? " + scores.containsKey("Jared"));

    System.out.println("Is Jason's score in the list? " + scores.containsKey("Jason"));

    // Checks if the map contains a specific value
    System.out.println("Did anyone get a 100 percent? " + scores.containsValue(100));

    System.out.println("Did anyone get a 90 percent? " + scores.containsValue(90));

    // Prints the people in the map (keys)
    System.out.print("People who are in map: ");
    
    for (String key : scores.keySet()) {
        System.out.print(key + " ");
    }

    System.out.println(" ");

    // Prints the scores in the map (values)
    System.out.print("Scores that are in map: ");

    for (int value : scores.values()) {
      System.out.print(value + " ");
    }

    System.out.println(" ");

    // Removes am emtry
    System.out.println("Removing Olivia's score because it is incorrect");

    scores.remove("Olivia");

    System.out.println("New map: " + scores);

    // Clears the map
    System.out.println("Clearing the map");

    scores.clear();

    System.out.println("Map after the clearing of all scores: " + scores);

  }
}

class NewHashMap<K, V> implements MyMap<K, V> {
    // Define the default hash table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;
    
    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30; 
    
    // Current hash table capacity. Capacity is a power of 2
    private int capacity;
    
    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f; 
  
    // Specify a load factor used in the hash table
    private float loadFactorThreshold; 
       
    // The number of entries in the map
    private int size = 0; 
    
    // Hash table is an array with each cell that is a linked list
    ArrayList<MyMap.Entry<K,V>> table;
  
    /** Construct a map with the default capacity and load factor */
    public NewHashMap() {  
      this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a map with the specified initial capacity and 
     * default load factor */
    public NewHashMap(int initialCapacity) { 
      this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a map with the specified initial capacity 
     * and load factor */
    public NewHashMap(int initialCapacity, float loadFactorThreshold) { 
      if (initialCapacity > MAXIMUM_CAPACITY)
        this.capacity = MAXIMUM_CAPACITY;
      else
        this.capacity = trimToPowerOf2(initialCapacity);
      
      this.loadFactorThreshold = loadFactorThreshold;    
      table = new ArrayList<>();
      for (int i = 0; i < capacity; i++) {
          table.add(null);
      }
    }
    
    @Override /** Remove all of the entries from this map */ 
    public void clear() {
      size = 0;
      removeEntries();
    }
  
    @Override /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {    
      if (get(key) != null)
        return true;
      else
        return false;
    }
    
    @Override /** Return true if this map contains the value */ 
    public boolean containsValue(V value) {
      for (int i = 0; i < capacity; i++) {
        if (table.get(i) !=  null && table.get(i).getValue() == value) {
            return true;
        }
      }
      
      return false;
    }
    
    @Override /** Return a set of entries in the map */
    public java.util.Set<MyMap.Entry<K,V>> entrySet() {
      java.util.Set<MyMap.Entry<K, V>> set = 
        new java.util.HashSet<>();
      
      for (int i = 0; i < capacity; i++) {
        if (table.get(i) != null) {
            set.add(table.get(i)); 
        }
      }
      
      return set;
    }
  
    @Override /** Return the value that matches the specified key */
    public V get(K key) {
        int listIndex = hash(key.hashCode());
        double power = 0;
        while (table.get(listIndex) != null) {
            if (table.get(listIndex).getKey().equals(key)) {
                return table.get(listIndex).getValue();
            }
           
            listIndex += Math.pow(power++, 2.0);
            listIndex %= capacity;

        }
      
      return null;
    }
    
    @Override /** Return true if this map contains no entries */
    public boolean isEmpty() {
      return size == 0;
    }  
    
    @Override /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet() {
      java.util.Set<K> set = new java.util.HashSet<K>();
      
      for (int i = 0; i < capacity; i++) {
        if (table.get(i) != null) {
            set.add(table.get(i).getKey()); 
        }
      }
      
      return set;
    }
        
    @Override /** Add an entry (key, value) into the map */
    public V put(K key, V value) {
        int listIndex = hash(key.hashCode());
        int power = 0;
        while (table.get(listIndex) != null) {
          if (table.get(listIndex).getKey().equals(key)) {
            Entry <K,V> entry = table.get(listIndex);
            V oldValue = entry.getValue();
            // Replace old value with new value
            entry.value = value; 
            table.set(listIndex, entry);
            // Return the old value for the key
            return oldValue;
          }

          listIndex += Math.pow(power++, 2);
          listIndex %= capacity;

        }
      
    
      // Check load factor
      if (size >= capacity * loadFactorThreshold) {
        if (capacity == MAXIMUM_CAPACITY)
          throw new RuntimeException("Exceeding maximum capacity");
        
        rehash();
      }
      
      
  
      // Add a new entry (key, value) to hashTable
      table.set(listIndex, new MyMap.Entry<K,V>(key, value));
  
      size++; // Increase size
      
      return value;  
    } 
   
    @Override /** Remove the entries for the specified key */
    public void remove(K key) {
      int listIndex = hash(key.hashCode());
      double power = 0;
      
      // Remove the first entry that matches the key from a bucket
      while (table.get(listIndex) != null) {
        if (table.get(listIndex).getKey().equals(key)) {
          table.remove(listIndex);
          size--; // Decrease size
          break; // Remove just one entry that matches key
        }
        listIndex += Math.pow(power++, 2);
        listIndex %= capacity;
      }
    }
    
    @Override /** Return the number of entries in this map */
    public int size() {
      return size;
    }
    
    @Override /** Return a set consisting of the values in this map */
    public java.util.Set<V> values() {
      java.util.Set<V> set = new java.util.HashSet<>();
      
      for (int i = 0; i < capacity; i++) {
        if (table.get(i) != null) {
            set.add(table.get(i).getValue()); 
        }
      }
      
      return set;
    }
    
    /** Hash function */
    private int hash(int hashCode) {
      return supplementalHash(hashCode) & (capacity - 1);
    }
    
    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
      h ^= (h >>> 20) ^ (h >>> 12);
      return h ^ (h >>> 7) ^ (h >>> 4);
    }
  
    /** Return a power of 2 for initialCapacity */
    private int trimToPowerOf2(int initialCapacity) {
      int capacity = 1;
      while (capacity < initialCapacity) {
        capacity <<= 1;
      }
      
      return capacity;
    }
    
    /** Remove all entries from the map */
    private void removeEntries() {
          table.clear();
    }
    
    /** Rehash the map */
    private void rehash() {
      java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
      capacity <<= 1; // Double capacity    
      size = 0; // Reset size to 0
      table.clear();
      
      for (int i = 0; i < capacity; i++) {
        table.add(null);
      }
      for (Entry<K, V> entry: set) {
        put(entry.getKey(), entry.getValue()); // Store to new table
      }
    }
  
    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder("[");
      
          for (Entry<K, V> entry: table) {
            if (entry != null && table.size() > 0) {
              builder.append(entry);
            }
          }
      
      builder.append("]");
      return builder.toString();
    }
  }

interface MyMap<K, V> {
    /** Remove all of the entries from this map */ 
    public void clear();
    
    /** Return true if the specified key is in the map */
    public boolean containsKey(K key);
    
    /** Return true if this map contains the specified value */ 
    public boolean containsValue(V value);
  
    /** Return a set of entries in the map */
    public java.util.Set<Entry<K, V>> entrySet();
  
    /** Return the first value that matches the specified key */
    public V get(K key);
    
    /** Return true if this map contains no entries */
    public boolean isEmpty();
  
    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet();
    
    /** Add an entry (key, value) into the map */
    public V put(K key, V value);
  
    /** Remove the entries for the specified key */
    public void remove(K key);
  
    /** Return the number of mappings in this map */
    public int size();
  
    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values();
    
    /** Define inner class for Entry */
    public static class Entry<K, V> {
      K key;
      V value;
      
      public Entry(K key, V value) {
        this.key = key;
        this.value = value;
      }
      
      public K getKey() {
        return key;
      }
      
      public V getValue() {
        return value;
      }
      
      @Override
      public String toString() {
        return "[" + key + ", " + value + "]";
      }
    }
  }    
  