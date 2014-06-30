package core.configuration;

import com.google.gson.Gson;
import core.utils.TextFacility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 19/04/14 22:56
 */
public final class ConfigurationLoader {

    public static <T> T load(String jsonFilePath, Class<T> clazz) {
        try {
            String json = readJsonFile(jsonFilePath, Charset.defaultCharset());
            return parseJson(json, clazz);
        } catch (IOException exception) {
            throw new RuntimeException(TextFacility.Exception.LOCAL_CONFIGURATION_LOADING_ERROR);
        }
    }

    private static <T> T parseJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    private static String readJsonFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
