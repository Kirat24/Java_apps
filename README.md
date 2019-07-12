  # Java Grep App
   ## Introduction

 
The app searches for a text pattern recursively in a given directory, and output matched lines to a file. 
The app takes three arguments:
- Regex - a special text string for describing a search pattern
- rootPath - root directory path
- outFile - output file name



It searches all files in ` ~/dev/jrvs/bootcamp/linux_sql directory`, and output lines contain data
keyword to the output file `/tmp/grep.out`


 
 ## Usage
 In order to understand better lets take use case. for instance , we want to search for a word for eg. data in the your directory named `linux_sql` and then you output those lines that contains 
  `data`in it and we gonna name the file as grep.out. In this we are going to give exact root path to the files or directories. so the positional arguments
  that are going to pass in this example will be and these all three arguments are of `String` type.
  
 

 ```
 .*data.* ~/dev/jrvs/bootcamp/linux_sql /tmp/grep.out
 ```

- regex:- The first argument `.*data.*` that we are going to pass in the function is nothing 
but a sequence of characters that define te search pattern.

- rootPath:- The second argument the root path to the directory from which we want to search from.
In this case it is `~/dev/jrvs/bootcamp/linux_sql`.
- outFile:- The argument is the root path to the output file where we want to sae the lines that contains
  word `data ` in it, which is `/tmp/grep.out` in this case.
  
 
 ## Design and Implementation
    
- Pseudo code: Sample pseudo for this app is gonna be like 
```
matchedLines = []
for file in listFiles(rootDir)
    for line in readLines(file)
        if containsPattern(line)        
         matchedLines.add(line)
writeToFile(matchedLines) 
```
- Workflow: When we run our program it is going to grab those arguments first and gonna initialise them in the program.Then it is going in 
rootDir file and it gonna go through each and every file in that directory and even in the files in this sub-directories.
Then for every line in the file its going to match  the `regex pattern` in this case its data with every line
 in the files. For every line that contains that pattern is going to be added in the list `matchedLines`.The matchedLines
 are gonna written in the outFile `grep.out` in this case.
    
- Libraries:- The Libraries that we used for this app are:
    - Java.io.File
    - Java.io.BufferedReader:
    - Java.io.OutputStreamWriter
    - java.io.FileOutputStream 
    - java.nio.charset.StandardCharsets
    - java.util.List
    - java.nio.file.Path
  

  
## Enhancements and Issues:- 
Java 8 comes with a pretty handy tool  that is "Lambda Expression " .Lambda expressions basically express
 instances of functional interfaces (An interface with single abstract method is called functional interface. An example is java.lang.Runnable). lambda 
expressions implement the only abstract function and therefore
 implement functional interfaces So we can we make use of lambda in '
in our methods in order to reduce the number of lines. for example our `readLines` method looks something
like this
```
public List<String> readLines(File inputFile) {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("Not a file");
    }
    List<String> lines = new ArrayList<>();
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(inputFile));
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }

``` 
 
  But with the use of lambda we can reduce this into one-two lines code like this:
  ```
  public List<String> readLines(File inputFile) throws IOException {
      return Files.lines(Paths.get(inputFile.getPath())).collect(Collectors.toList());
    }
  
  ```
  The issue with using lambda expresiions is that they are hard to debug and have longer call stack

#Maven

Maven is a build automation tool used primarily for Java projects.Maven addresses two aspects of building software: first, it 
describes how software is built, and second, it describes its dependencies.So we
 can we our project as java_apps as Maven project. All you need to know is on your IntelliJ
 go on your terminal and type the following code.
 ```
 
 
 mkdir-p src/main/java src/main/resources
 tree ./src
 #move if neccessary
 mv src/ca src/main/java/
 touch pom.xml
 
 ```
 
 pom.xml content
 
 ```
 <project xmlns="http://maven.apache.org/POM/4.0.0"xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
 <modelVersion>4.0.0</modelVersion>
 <groupId>ca.jrvs.apps</groupId>
 <artifactId>java_apps</artifactId>
 <version>1.0-SNAPSHOT</version>
 <properties>
 <maven.compiler.source>1.8</maven.compiler.source>
 <maven.compiler.target>1.8</maven.compiler.target>
 </properties>
 <dependencies>
    <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId
    ><version>4.12</version>
    <scope>test</scope>
    </dependency>
 </dependencies>
 </project>
 
 
 ```
 Go on terminal and type this command 
 
 `rm -rf java_apps.iml .idea/`
 
 - close IDE window and delete java_apps project
 - `Import Project`> `Maven` > check `Import Maven Projects automatically`> `next` > `jdk 1.8` > `Finish` 
 - Run `ca.jrvs.apps.grep.JavaGrepImp` to verify project imported ok 
 `mvn clean package`
 Run `tree ~/.m2/repository/`
 
 
 #Java DataBase Connectivity(JDBC)
##Introduction
JDBC is an acronym for Java Database Connectivity. 
Its an advancement for ODBC ( Open Database Connectivity ). 
JDBC is an standard API specification developed in order to move data from 
frontend to backend. This API consists of classes and interfaces written in Java.
 It basically acts as an interface (not the one we use in Java) or channel 
 between your Java program and databases i.e it establishes a link between the
  two so that a programmer could send data from Java code and store it in the 
  database for future use.
 ##Basic Flow

