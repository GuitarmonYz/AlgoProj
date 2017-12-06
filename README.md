# README
Group 20

This is a algorithm group project for solving Minium Vertex Cover problem.

**Four Algorithms implemented**

- Construction Heuristics with approximation guarantees.
- Exact algorithm using Branch-and-Bound.
- Local Search: Hill Climbing Algorithm.
- Local Search2: 2-improvement.

**Compile and run**
The submission includes precompiled MVC.jar
Run: <br />
java -jar MVC.jar -inst /path/to/graph/jazz.graph -alg LS1 -time 600 -seed 1

-alg: LS1, LS2 BnB, Approx <br />
-seed: long type, optional<br />
-time: long type <br />
-inst: path to graph <br />

The order of argument is not fixed

The output file is in current directory

For example, if the graph is in current folder <br />
java -jar MVC.jar -inst ./jazz.graph -alg LS1 -time 600 -seed 1

If the graph is in Data folder, which must be a subdirectory of current folder, you can use <br />
java -jar MVC.jar -inst jazz.graph -alg LS1 -time 600 -seed 1

Method to build jar (No need to do this, as jar file is included with submission):<br />
using intelliJ, build a MVC.jar based on Launcher as main class
put the jar file outside of src folder


**reference**

https://algs4.cs.princeton.edu/code/