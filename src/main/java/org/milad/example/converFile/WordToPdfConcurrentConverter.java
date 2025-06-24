package org.milad.example.converFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordToPdfConcurrentConverter {

    public static void main(String[] args) throws Exception {
        String docxPath = "/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/LetterContent.docx";

        // Thread pool with 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 1; i++) {
            final int threadNumber = i;
            executor.submit(() -> {
                String outputDir = "/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/";

                try {
                    File inputFile = new File(docxPath);
                    if (!inputFile.exists()) {
                        System.err.println("Thread " + threadNumber + ": Input file does not exist.");
                        return;
                    }

                    // unoconv command:
                    // unoconv -f pdf -o <outputDir> <inputFile>
                    ProcessBuilder pb = new ProcessBuilder(
                            "unoconv",
                            "-f", "pdf",
                            "-o", outputDir,
                            inputFile.getAbsolutePath()
                    );
                    pb.redirectErrorStream(true); // merge stdout and stderr

                    Process process = pb.start();

                    // Read output from unoconv process
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println("Thread " + threadNumber + ": " + line);
                        }
                    }

                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        System.out.println("Thread " + threadNumber + ": Conversion done.");
                    } else {
                        System.err.println("Thread " + threadNumber + ": Conversion failed with exit code " + exitCode);
                    }

                    // Rename output file to output_<threadNumber>.pdf
                    // unoconv saves output in same dir with same basename but pdf extension
                    File originalPdf = new File(outputDir,
                            inputFile.getName().replaceAll("\\.docx$", ".pdf"));
                    File renamedPdf = new File(outputDir, "output_" + threadNumber + ".pdf");
                    if (originalPdf.exists()) {
                        boolean renamed = originalPdf.renameTo(renamedPdf);
                        if (!renamed) {
                            System.err.println("Thread " + threadNumber + ": Failed to rename output PDF");
                        }
                    } else {
                        System.err.println("Thread " + threadNumber + ": Output PDF not found");
                    }

                } catch (Exception e) {
                    System.err.println("Thread " + threadNumber + ": Exception: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
