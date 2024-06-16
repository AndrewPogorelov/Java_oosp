package ResultAPI;

import javax.xml.transform.Result;

public interface ResultAPI {
    void cacheResult(Result result);
    Result loadResult(String id);
    void storeResult(Result result);
    void exportResult(Result result, String filePath);
}