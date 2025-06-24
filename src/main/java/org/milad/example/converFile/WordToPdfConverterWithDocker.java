package org.milad.example.converFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WordToPdfConverterWithDocker {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. مسیر موقت با UUID
        String jobId = UUID.randomUUID().toString();
        Path tempDir = Files.createTempDirectory("job_" + jobId);
        Path inputFile = tempDir.resolve("input.docx");
        Path outputFile = tempDir.resolve("input.pdf");

        // 2. کپی فایل Word به فولدر temp
        Files.copy(Paths.get("/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/LetterContent.docx"), inputFile, StandardCopyOption.REPLACE_EXISTING);

        // 3. اجرای دستور docker run
        ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "--rm",
                "-v", tempDir.toAbsolutePath() + ":/workspace",
                "lcrea/libreoffice-headless",
                "libreoffice", "--headless",
                "--convert-to", "pdf",
                "/workspace/input.docx",
                "--outdir", "/workspace"
        );

        pb.redirectErrorStream(true); // ترکیب stdout و stderr برای لاگ
        Process process = pb.start();

        // 4. خواندن خروجی برای دیباگ
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            reader.lines().forEach(System.out::println);
        }

        // 5. منتظر شدن تا کانتینر کامل بشه
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Docker process failed with exit code " + exitCode);
        }

        // 6. بررسی فایل خروجی
        if (!Files.exists(outputFile)) {
            throw new RuntimeException("PDF file was not created.");
        }

        System.out.println("PDF created at: " + outputFile.toAbsolutePath());

        // 7. در صورت نیاز: حذف فولدر temp
        // Files.walk(tempDir).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }
}














