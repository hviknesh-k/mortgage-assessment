package stubs;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

public class FileUtils {

    public static String getJsonFromFile(final String fileName) {
        return getJson(fileName, FileUtils.class);
    }

    private static String getJson(String fileName,final Class<?> callerClass) {
        final Optional<InputStream> resourceAsStream = Optional.ofNullable(callerClass.getClassLoader().getResourceAsStream(fileName));
        if (resourceAsStream.isPresent()) {
            try {
                return IOUtils.toString(resourceAsStream.get(), Charset.defaultCharset());
            } catch (final IOException exception) {
                throw new RuntimeException(String.format("Unable to parse file content %s", fileName));
            }
        } else {
            throw new RuntimeException(String.format("File resource not available %s", fileName));

        }
    }
}
