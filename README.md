StackOverflow wordcount example with Hadoop

```
$ hdfs dfs -mkdir stackoverflow
$ hdfs dfs -put Users10.xml stackoverflow/Users10.xml

# make sure maven is installed and on the application path
$ wget http://mirror.ox.ac.uk/sites/rsync.apache.org/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.zip
$ unzip apache-maven-3.5.2-bin.zip
$ echo "PATH=\"\$PATH:$HOME/apache-maven-3.5.2/bin" >> ~/.profile
$ source ~/.profile

# now ready to build with maven
$ mvn package

# removal of folder is to allow the command to be re-run easily if required
$ hdfs dfs -rm -r -f about_me_wordcount && hadoop jar target/about-me-wordcount-1.0-SNAPSHOT.jar com/mycompany/app/AvgDriver stackoverflow/Users10.xml about_me_wordcount

$ hdfs dfs -cat aboutme_word_count/part-r-00000
a	39
abandoned	1
account	2
actually	1
all	1
am	1
and	4
anonymous	1
...
```


## some errors

```
# NullPointer Exception
map.get("AboutMe") can return nulls

# java.lang.RuntimeException: java.lang.ClassNotFoundException: org.gamma.WordCount$Map
https://stackoverflow.com/questions/21373550/class-not-found-exception-in-mapreduce-wordcount-job
Use job.setJar("wordcount.jar"); in Driver


# org.apache.hadoop.mapred.FileAlreadyExistsException
https://stackoverflow.com/questions/4913212/org-apache-hadoop-mapred-filealreadyexistsexception change arg indexes in driver

# use the right data type in Driver
Error: java.io.IOException: Type mismatch in key from map: expected org.apache.hadoop.io.NullWritable, received org.apache.hadoop.io.Text
	at org.apache.hadoop.mapred.MapTask$MapOutputBuffer.collect(MapTask.java:1074)
	at org.apache.hadoop.mapred.MapTask$NewOutputCollector.write(MapTask.java:715)
	at org.apache.hadoop.mapreduce.task.TaskInputOutputContextImpl.write(TaskInputOutputContextImpl.java:89)
	at org.apache.hadoop.mapreduce.lib.map.WrappedMapper$Context.write(WrappedMapper.java:112)

[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.3:compile (default-compile) on project about-me-wordcount: Compilation failure
[ERROR] /home/local/mdac073/LSDP/about-me/about-me-wordcount/src/main/java/com/mycompany/app/AvgDriver.java:[21,43] cannot find symbol
[ERROR]   symbol:   class Text
[ERROR]   location: class com.mycompany.app.AvgDriver
```
