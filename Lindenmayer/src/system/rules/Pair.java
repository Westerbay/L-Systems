package system.rules;


/**
 * Classe représentant une structure de données de pairs
 * @author dubuiss212
 */
public class Pair<K, V> {

    private final K key;
    private final V value;

    /**
     * Constructeur
     * @param key key
     * @param value value
     */
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    /**
     * Accesseur key
     * @return l'attribut key
     */
    public K getKey(){
        return key;
    }

    /**
     * Accesseur value
     * @return l'attribut value
     */
    public V getValue(){
        return value;
    }

}

