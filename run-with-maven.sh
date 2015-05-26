#!/bin/sh
mvn clean compile exec:java \
    -Dexec.mainClass="net.peakgames.pisti.PistiBootstrap" \
    -Dexec.args="\
    1 \
    10 \
    net.peakgames.pisti.bot.SuperDummyBot \
    net.peakgames.pisti.bot.SuperDummyBot\
    net.peakgames.pisti.bot.SuperDummyBot \
    net.peakgames.pisti.bot.SuperDummyBot\
    "