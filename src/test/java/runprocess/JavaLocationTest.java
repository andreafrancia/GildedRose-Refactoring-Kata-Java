package runprocess;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;
import static runprocess.RunProcess.basename;

class JavaLocationTest {
    @Test
    void getJavaExecutable() throws IOException {
        String java = RunProcess.java();

        assertThat(asList("java", "java.exe")).contains(basename(java));
    }

}

