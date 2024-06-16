package evs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final String DATA_FOLDER = "data";
    private static final String USER_FILE = DATA_FOLDER + File.separator + "users.json";
    private static final String VOTING_FILE = DATA_FOLDER + File.separator + "votings.json";
    private static final Gson gson = new Gson();

    public static List<User> loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            saveUsers(new ArrayList<>());
        }
        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveUsers(List<User> users) {
        try (Writer writer = new FileWriter(USER_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Voting> loadVotings() {
        File file = new File(VOTING_FILE);
        if (!file.exists()) {
            saveVotings(new ArrayList<>());
        }
        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, new TypeToken<List<Voting>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveVotings(List<Voting> votings) {
        try (Writer writer = new FileWriter(VOTING_FILE)) {
            gson.toJson(votings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}