package ResultCache;

public class ResultCache {
    private Map<String, Result> cache;

    public ResultCache() {
        cache = new HashMap<>();
    }

    public void cacheResult(Result result) {
        cache.put(result.getId(), result);
    }

    public Result loadResult(String id) {
        return cache.get(id);
    }

    public void evictResult(String id) {
        cache.remove(id);
    }
}
