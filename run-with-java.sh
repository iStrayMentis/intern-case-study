#!/bin/sh
pisti_classpath="$(pwd)/classes"
#echo $pisti_classpath
rm -rf $pisti_classpath
mkdir $pisti_classpath

cd src/main/java
javac -d $pisti_classpath \
	net/peakgames/pisti/*.java \
	net/peakgames/pisti/bot/*.java \
	net/peakgames/pisti/deck/*.java \
	net/peakgames/pisti/game/*.java \
	net/peakgames/pisti/utility/*.java \
	   
java -cp $pisti_classpath \
	net/peakgames/pisti/PistiBootstrap \
    1 \
    10 \
    net.peakgames.pisti.bot.DummyBot \
    net.peakgames.pisti.bot.DummyBot \
    net.peakgames.pisti.bot.RandomBot \
    net.peakgames.pisti.bot.RandomBot \
    