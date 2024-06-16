package ResultAPIImpl;

import javax.xml.transform.Result;

public class ResultAPIImpl implements ResultAPI {
    private ResultCache resultCache;
    private ResultDB resultDB;

    public ResultAPIImpl(ResultCache resultCache, ResultDB resultDB) {
        this.resultCache = resultCache;
        this.resultDB = resultDB;
    }

    @Override
    public void cacheResult(Result result) {
        if (result.isReadOnly()) {
            resultCache.cacheResult(result);
        }
    }

    @Override
    public Result loadResult(String id) {
        Result result = resultCache.loadResult(id);
        if (result == null) {
            result = resultDB.loadResult(id);
        }
        return result;
    }

    @Override
    public void storeResult(Result result) {
        if (!result.isReadOnly()) {
            resultDB.storeResult(result);
        }
    }

    @Override
    public void exportResult(Result result, String filePath) {
        resultDB.exportResult(result, filePath);
    }
}