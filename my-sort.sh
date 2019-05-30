#!/bin/bash

# test the hadoop cluster by running wordcount
mvn "-Dmyproperty=Driver" clean && mvn compile "-Dmyproperty=Driver" && mvn package "-Dmyproperty=Driver"

# create input files 
mkdir input$1
echo "My sort - Try $1"
cp /root/secondarysort/dataset/* input$1

# create input directory on HDFS
hadoop fs -mkdir -p input$1

# put input files to HDFS
hdfs dfs -put ./input$1/* input$1

# run sort
yarn jar /root/secondarysort/target/MRSecondarySort-0.0.1.jar input$1 output$1

# print the output of sort
echo -e "\nsort output:"
hdfs dfs -cat output$1/part-r-00000
