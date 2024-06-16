package ResultManager;

import javax.xml.transform.Result;

public class ResultManager {
    private ResultAPI resultAPI;

    public ResultManager() {
        ResultCache resultCache = new ResultCache();
        ResultDB resultDB = new ResultDB("jdbc:mysql://localhost:3306/voting");
        resultAPI = new ResultAPIImpl(resultCache, resultDB);
    }

    public void cacheResult(Result result) {
        resultAPI.cacheResult(result);
    }

    public Result loadResult(String id) {
        return resultAPI.loadResult(id);
    }

    public void storeResult(Result result) {
        resultAPI.storeResult(result);
    }

    public void exportResult(Result result, String filePath) {
        resultAPI.exportResult(result, filePath);
    }

    ResultManager resultManager = new ResultManager();

    Result result = new Result("1", "data");
resultManager.cacheResult(result);

    Result loadedResult = resultManager.loadResult("1");
System.out.println(loadedResult.getData());

resultManager.storeResult(result);

resultManager.exportResult(result, "result.txt");

}
