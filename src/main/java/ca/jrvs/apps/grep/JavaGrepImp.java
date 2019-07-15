package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep {


    private String regex;
    private String rootPath;
    private String outFile;


    public static void main(String[] args) {

        JavaGrepImp java = new JavaGrepImp();


        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: regex rootPath outFile");
        }
        java.setRegex(args[0]);
        java.setRootPath(args[1]);
        java.setOutFile(args[2]);


        try {
            java.process();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();
        List<File> file = listFiles(getRootPath());

        for (File files : file
        ) {

            List<String> read = readLines(files);
            for (String read_lines : read) {
                if (containsPattern(read_lines)) {
                    matchedLines.add(read_lines);


                }
            }
            writeToFile(matchedLines);
        }


    }

    @Override
    public List<File> listFiles(String rootDir) {
        File folder = new File(rootDir);
        File[] files = folder.listFiles();
        List<File> fileName = new ArrayList<>();


        for (File file : files) {


            if (file.isFile()) {
                fileName.add(file);
            } else if (file.isDirectory()) {

                fileName.addAll(listFiles(file.getAbsolutePath()));
            } else {

                System.out.println("folder is empty");
            }


        }
        return fileName;
    }

    @Override
    public List<String> readLines(File inputFile) {
        FileReader fStream;
        List<String> lines = new ArrayList<>();
        String line;

        try {
            fStream = new FileReader(inputFile);
            BufferedReader in = new BufferedReader(fStream);
            while ((line = in.readLine()) != null) {

                lines.add(line);
            }

            in.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return lines;

    }


    @Override


    public boolean containsPattern(String line) {
        boolean contains = Pattern.matches(getRegex(), line);
        return contains;

    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        FileWriter writeFile = new FileWriter(getOutFile());
        try (BufferedWriter writer = new BufferedWriter(writeFile)) {

            for (String line : lines) {
                writer.write(line);
                writer.newLine();

            }


        }


    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}

