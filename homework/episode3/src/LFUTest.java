import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.assertEquals;

/**
 * Created by mikim on 16.12.16.
 */
public class LFUTest {

    public LFUCache<Integer> lfuCache;
    public int size = 10;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(final Description description) {
            System.err.println("=== Running " + description.getMethodName());
        }
    };

    @Before
    public void set(){
        lfuCache = new LFUCache<>(size);
    }

    @Test
    public void testSimple(){
        lfuCache.addCacheEntry(1, 1);
        lfuCache.addCacheEntry(2, 2);
        lfuCache.addCacheEntry(3, 3);
        assertEquals(2, (int)lfuCache.getCacheEntry(2));
        assertEquals(3, (int)lfuCache.getCacheEntry(3));
        assertEquals(1, lfuCache.getLFUKey());
    }

    @Test
    public void testGetLFUKey(){
        lfuCache.addCacheEntry(1, 1);
        lfuCache.addCacheEntry(2, 2);
        lfuCache.addCacheEntry(3, 3);
        for (int i = 0; i < 3; i++) {
            assertEquals(2, (int) lfuCache.getCacheEntry(2));
            assertEquals(3, (int) lfuCache.getCacheEntry(3));
        }
        assertEquals(1, lfuCache.getLFUKey());
    }

    @Test
    public void testEmptyCache(){
        assertEquals(null, lfuCache.getCacheEntry(1));
        assertEquals(0, lfuCache.getLFUKey());
    }

    @Test
    public void testFullCache(){
        for (int i = 1; i <= 11; i++) {
            lfuCache.addCacheEntry(i, i);
        }
    }
}
