#!/bin/sh
mvn clean compile exec:java \
    -Dexec.mainClass="net.peakgames.pisti.PistiBootstrap" \
    -Dexec.args="\
    10 \
    1000 \
    net.peakgames.pisti.TestBot \
    net.peakgames.pisti.TestBot \
    net.peakgames.pisti.TestBot \
    net.peakgames.pisti.TestBot \
    "