package com.naresh.consul;

import java.io.File;

public class ExtractKeyValuePairs {

    public static void main(String[] args) {
        if (args.length == 3) {
            File file = new File(args[2]);
            if (!file.exists()) {
                System.out.println("File not found");
                return;
            }
            new FileToConsul().load(args[0], args[1],args[2]);
        }
    }

}
