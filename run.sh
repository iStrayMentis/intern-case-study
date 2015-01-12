#!/bin/sh
mvn clean compile exec:java \
    -Dexec.mainClass="net.peakgames.pisti.PistiBootstrap" \
    -Dexec.args="\
    1 \
    10 \
    net.peakgames.pisti.SmartBot \
    net.peakgames.pisti.SuperDummyBot\
    net.peakgames.pisti.SmartBot \
    net.peakgames.pisti.SuperDummyBot\
    "