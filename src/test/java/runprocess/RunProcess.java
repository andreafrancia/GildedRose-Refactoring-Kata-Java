package runprocess;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class RunProcess {
    public static ProcessResults runProcess(String executable, String... arguments) throws Exception {
        return runProcess(new CommandLine(executable).addArguments(arguments));
    }

    private static ProcessResults runProcess(CommandLine cmdLine) throws IOException, InterruptedException {
        DefaultExecutor executor = DefaultExecutor.builder().get();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        ByteArrayOutputStream stderr = new ByteArrayOutputStream();
        PumpStreamHandler psh = new PumpStreamHandler(stdout, stderr);
        executor.setStreamHandler(psh);

        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
        executor.execute(cmdLine, handler);
        handler.waitFor();
        String output = stdout.toString();
        String error = stderr.toString();
        int exitCode = handler.getExitValue();
        return new ProcessResults(output, error, exitCode);
    }

    public static String java() throws IOException {
        String javaHome = System.getProperty("java.home");
        for (String javaExecutable : new String[]{"java.exe", "java"}) {
            var path = Paths.get(javaHome, "bin", javaExecutable);
            if (path.toFile().canExecute()) {
                return path.toAbsolutePath().toString();
            }
        }
        throw new IOException("java non found");
    }

    public static String basename(String java) {
        return Paths.get(java).getFileName().toString();
    }
}
