package runprocess;

public record ProcessResults(
    String stdout,
    String stderr,
    int exitValue) {
    public String wholeOutput() {
        return stderr + stdout;
    }
}


