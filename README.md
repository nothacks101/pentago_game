# Pentago Game

## Overview
Pentago is a two-player abstract strategy game where players take turns placing marbles on a 6x6 board and rotating sections of the board to try to form a line of five marbles.
This implentaion is to let the player fight against the computer using state machine and java gui

## Features
- ONE-player mode
- Graphical User Interface (GUI)
- Easy-to-understand rules
- Fun and engaging gameplay

## Installation

To run this project, you need to have **Java** and **Maven** installed.

## Local-Run
Please insure you have the following steps:
- Download Maven: https://maven.apache.org/download.cgi
  - importent, download -bin.zip file under Binary zip archive + Link
  - add bin path of downloaded maven to your ENV VARIABLE PATH
  
- Download Java JDK: https://adoptium.net/
  - add C:\Program Files\Eclipse Adoptium\jdk-25.0.2.10-hotspot\bin to your ENV VARIABLE PATH
  - add JAVA_HOME - C:\Program Files\Eclipse Adoptium\jdk-25.0.2.10-hotspot to system variables in ENV VARIABLE

1. Clone the repository:
   git clone https://github.com/nothacks101/pentago_game.git
2. Write those Commands in terminal:
  - mvn clean compile
  - mvn exec:java