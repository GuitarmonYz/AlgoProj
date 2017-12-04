#!/bin/bash


algo=LS1

for s in {1..10}

do

java -jar MVC.jar -inst jazz.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst karate.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst football.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst as-22july06.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst hep-th.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst star.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst star2.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst netscience.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst email.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst delaunay_n10.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst power.graph -alg $algo -time 600 -seed $s

done

algo=LS2

for s in {1..10}

do

java -jar MVC.jar -inst jazz.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst karate.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst football.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst as-22july06.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst hep-th.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst star.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst star2.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst netscience.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst email.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst delaunay_n10.graph -alg $algo -time 600 -seed $s
java -jar MVC.jar -inst power.graph -alg $algo -time 600 -seed $s

done


