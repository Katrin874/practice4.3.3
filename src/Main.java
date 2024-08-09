public class Main {
    public static void main(String[] args) {
        SeparateChaining<String, Integer> hashTable = new SeparateChaining<>();
        hashTable.put("John", 25);
        hashTable.put("Jane", 30);
        hashTable.put("Jake", 35);
        hashTable.put("Josh", 40);
        System.out.println("Size of hashTable: " + hashTable.size());
        System.out.println("Value for 'John': " + hashTable.get("John"));
        System.out.println("Value for 'Jane': " + hashTable.get("Jane"));
        hashTable.delete("Jake");
        System.out.println("Size of hashTable after deletion: " + hashTable.size());
        System.out.println("Contains 'Jake'? " + hashTable.contains("Jake"));
    }
}