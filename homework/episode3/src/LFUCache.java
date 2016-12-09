import java.util.LinkedHashMap;
import java.util.Map;

public class LFUCache <T> {

    class CacheEntry
    {
        private T data;
        private int frequency;

        private CacheEntry()
        {}

        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }

        public int getFrequency() {
            return frequency;
        }
        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

    }

    private static int initialCapacity = 10;

    private LinkedHashMap<Integer, CacheEntry> cacheMap = new LinkedHashMap<Integer, CacheEntry>();

    public LFUCache(int initialCapacity)
    {
        this.initialCapacity = initialCapacity;
    }

    public void addCacheEntry(int key, T data)
    {
        if(!isFull())
        {
            CacheEntry temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
        }
        else
        {
            int entryKeyToBeRemoved = getLFUKey();
            cacheMap.remove(entryKeyToBeRemoved);

            CacheEntry temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
        }
    }

    public int getLFUKey()
    {
        int key = 0;
        int minFreq = Integer.MAX_VALUE;

        for(Map.Entry<Integer, CacheEntry> entry : cacheMap.entrySet())
        {
            if(minFreq > entry.getValue().frequency)
            {
                key = entry.getKey();
                minFreq = entry.getValue().frequency;
            }
        }

        return key;
    }

    public T getCacheEntry(int key)
    {
        if(cacheMap.containsKey(key))  // cache hit
        {
            CacheEntry tempEntry = cacheMap.get(key);
            tempEntry.frequency++;
            cacheMap.put(key, tempEntry);
            return tempEntry.data;
        }
        return null; // cache miss
    }

    public boolean isFull()
    {
        if(cacheMap.size() == initialCapacity)
            return true;

        return false;
    }
}