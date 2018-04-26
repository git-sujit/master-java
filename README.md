# master-java
Multithreading &amp; Parallel computing in Java, Java Memory Management, Java 8 &amp; Java 9

# Java Memory Management
	- How to analyze memoty
		1. jvisualvm command on mac
		2. Eclipse MAT (Memory Analyzer Tool)
			- Get heap dump, may be from jvisualvm
			- Upload the heap dump to MAT
		3. Tuning the Java Virtual Machine: means providing command line argument/parameter to JVM runtime
			=> -Xmx (e.g -Xmx512m) : set Max Heap Size, Default Heap Size= Quarter of computer's memory
			=> -Xms (e.g -Xms128m) : set the starting heap size, generally it is 1/64th of computer's memory
			=> k=kilobytes, m=megabytes, g=gigabytes
			=> There is performance impact each time heap tries to get more memory 
				because it starts from starting heap size to its total/max size
			=> Performance critical circumstances set lower -Xms
			=> For server application(Backend), set -Xms & -Xmx as same
			=> Runtime argument
				- changes with virtual machine implementations
				- X arguments are non standard and changes from VM to VM
			=> -XX:MaxPermSize=256m (Note =) : To set the PermGen space: [REMOVED IN JAVA 8] 
			=> All runtime arguments are case sensitive
			=> -verbose:gc print to console when garbage collection takes place
			=> Young Generation: One third of heap size, oracle suggests [1/4, 1/2]
				-Xmn256m (Both Start heap size and max heap size should be greater than 256m)
			=> -XX:HeapDumpOnOutOfMemory - Creates a heap dump file
				- upload on Eclipse MAT and analyze
				- Use Apache memory analyzer tool
			=> Type of Garbage Collection: 5 types of Garbage Collector
				- Oracle VM has three types of Collectors
					1. Serial: Uses a single thread, good for small app All threads will be on hold
						-XX:+UseSerialGC
					2. Parallel
						-XX:+UseParallelGC	- GC on Young Generation in parallel(If we have multiple processor on computer)
					3. Mostly Concurrent: Real time GC, application is not paused while GC runs
						-XX:+UseConcMarSweepGC (Mark & Sweep Collector)
						-XX:+UseG1GC (G1 Collector)
				- Which GC to use: Java makes the decision that which GC will be used based on hardware
					To find out: java -XX:+PrintCommandLineFlags
					My local mac has : -XX:+UseParallelGC, so its using Parallel GC
			=> PermGenSize: Till Java 7
				Removed in Java 8
				-XX:MaxPermSize=256m Set the size of permgen
			=> To monitor how often GC is running
				-verbose:gc
			
# Multi-Threading & Parallel Computing
 


# Java 8


# Java 9



