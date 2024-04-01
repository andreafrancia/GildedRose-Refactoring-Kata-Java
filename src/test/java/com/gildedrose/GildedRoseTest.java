package com.gildedrose;

import org.junit.jupiter.api.Test;
import runprocess.RunProcess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.google.common.truth.Truth.assertThat;
import static runprocess.RunProcess.java;

class GildedRoseTest {
    @Test
    void goldenMaster() throws Exception {
        var result = RunProcess.runProcess(java(), "-cp",
            "target/gilded-rose-kata-0.0.1-SNAPSHOT.jar",
            "com.gildedrose.TexttestFixture", "30"
            );

        assertThat(result.wholeOutput()).isEqualTo(readReference());
    }

    private String readReference() throws IOException {
        Path workDir = Paths.get("", "texttests", "ThirtyDays", "stdout.gr").toAbsolutePath();
        return Files.readString(workDir);
    }

}

