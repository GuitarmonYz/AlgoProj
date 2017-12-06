# README

This is a algorithm group project for solving Minium Vertex Cover problem.

**Four Algorithms implemented**

- Construction Heuristics with approximation guarantees.
- Exact algorithm using Branch-and-Bound.
- Local Search: Hill Climbing Algorithm.
- Local Search2: 2-improvement.

**reference book:**

https://algs4.cs.princeton.edu/code/

using intelliJ, build a MVC.jar based on Launcher as main class
output the jar file under AlgoProj folder
Run: java -jar MVC.jar -inst jazz.graph -alg LS1 -time 600 -seed 1

the runLS.sh is in src folder
run test : chomd +x runLS.sh
./runLS.sh

.m file to analyze data for tabel is in output folder

In the launcher, the order of argument is not fixed
The argument of inst alg time is must, seed is optional.
