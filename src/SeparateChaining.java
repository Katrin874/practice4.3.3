import java.util.ArrayList;
import java.util.List;

public class SeparateChaining <K, V>{
    private List<HashNode<K, V>> buckets;
    private int capacity;
    private int countOfItems;
    public SeparateChaining(){
        this.capacity = 15;
        this.buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++){
            buckets.add(null);
        }
        this.countOfItems = 0;
    }
    public int size(){
        return countOfItems;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public int hash(K key){
        return key.hashCode();
    }
    public int getBucketIndex(K key){
        int hashCode = hash(key);
        int index = hashCode % capacity;
        return  index < 0 ? index * -1 : index;
    }
    public void delete(K key){
        int bucketIndex = getBucketIndex(key);
        int hashCode = hash(key);
        HashNode<K, V> head = buckets.get(bucketIndex);
        HashNode<K, V> prev = null;
        while (head != null){
            if(head.key.equals(key) && head.hashCode == hashCode){
                break;
            }
            prev = head;
            head = head.next;
        }
        if (head == null){
            return;
        }
        countOfItems--;
        if (prev != null){
            prev.next = head.next;
        } else
            buckets.set(bucketIndex, head.next);
    }
    public V get(K key){
        int bucketIndex = getBucketIndex(key);
        int hashCode = hash(key);
        HashNode<K, V> head = buckets.get(bucketIndex);
        while (head != null){
            if (head.key.equals(key) && head.hashCode == hashCode){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    public void put(K key, V value){
        int bucketIndex = getBucketIndex(key);
        int hashCode = hash(key);
        HashNode<K, V> head = buckets.get(bucketIndex);
        while (head != null){
            if (head.key.equals(key) && head.hashCode == hashCode){
                head.value = value;
                return;
            }
        }
        countOfItems++;
        head = buckets.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);
        newNode.next = head;
        buckets.set(bucketIndex, newNode);
        if ((1.0 * countOfItems) / capacity >= 0.75){
            resize(2 * capacity);
        }
    }
    public boolean contains(K key){
        return get(key) != null;
    }
    private void resize(int newCapacity){
        List<HashNode<K, V>> temp = buckets;
        capacity = newCapacity;
        countOfItems = 0;
        buckets = new ArrayList<>(capacity);
        for(int i = 0; i < capacity; i++){
            buckets.add(null);
        }
        for (HashNode<K, V> headNode : temp){
            while (headNode != null){
                put(headNode.key, headNode.value);
                headNode = headNode.next;
            }
        }
    }

}
