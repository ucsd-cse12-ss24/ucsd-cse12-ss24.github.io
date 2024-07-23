package BSTMap;
/**
 * @param <K> The type of the keys in this DefaultMap
 * @param <V> The type of the values in this DefaultMap
 */
public interface DefaultMap<K, V> {
	String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";
	
	/**
	 * @return the value corresponding to the specified key
	 * @throws IllegalArgumentException if the key is null
	 */
	V get(K key) throws IllegalArgumentException;
}