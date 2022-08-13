package com.back.com.back.miru.ai.tracking;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class HandTracking {

    public static void main(String[] args) throws Exception {
        tracking();
    }

    public static void tracking() throws Exception {
        System.out.println("tracking");
        String root = "src/main/java/com/back/miru/ai/tracking/";
        String[] command = new String[2];
        command[0] = "python";
        command[1] = root + "handtracking.py";

        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            commandLine.addArgument(command[i]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        System.out.println(commandLine);
        int result = executor.execute(commandLine);
        System.out.println("result: " + result);
//        System.out.println("output: " + outputStream);

        String[] word = new String(outputStream.toByteArray(), StandardCharsets.UTF_8).split("\n");
        if (word[2].equals("MJ\r")) {
            System.out.println("SUCCESS");
        }
    }
}
