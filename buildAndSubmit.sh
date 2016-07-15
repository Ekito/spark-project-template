#!/usr/bin/env bash

PLATFORM_HOME=../sparkassandra-dockerized/platform

sbt assembly

cp -R src/main/resources/* $PLATFORM_HOME/data

cp ./target/scala-2.10/spark-project-template-assembly-1.0.jar $PLATFORM_HOME/jobs

$PLATFORM_HOME/submitJob.sh --job spark-project-template-assembly-1.0.jar --class io.basilic.myproject.Main --args whatEver