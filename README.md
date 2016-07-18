# Steps

* Install SBT: `brew install sbt`
* Copy this directory
* Remove the .git setup: `rm -rf .git`
* Change the project name and version in *build.sbt*
* Change the project name in *src/main/scala/myproject/Main.scala*
* Run an sbt console just to check if everything's ok: `sbt console`. It could take a few minutes the first time. It will will create the directories *project/target* and *target* (which are .gitignored). The result is a scala 2.10.4 console, with all the project dependencies loaded.
* Optionnaly intitialize a new git project: `git init`

# Import the project in IntelliJ IDEA

* Start IntelliJ IDEA. In the Welcome window, click on "Open Project"
* Enter the project path
* Choose the external model *SBT*
* Check
  * *Use auto-import*
  * *Download sources and docs*
* Choose a Project SDK with a version >= 1.8.
* Move the `Main` and `Schema` classes to your package name
* Define a Launcher with an SBT task `run`

# Build and run your Spark job on a Spark cluster

We use [sbt-assembly](https://github.com/sbt/sbt-assembly) to bundle the application in a fat JAR, ready to be 
submitted to a Spark cluster. The JAR must not include the Spark components (spark-core, spark-sql, hadoop-client, etc) 
and their dependencies.

## To build the JAR:
* run `sbt assembly`. The generated jar is in *target/scala-2.10/{projectname}-assembly-{version}.jar

## To submit the JAR:

This project is a companion of the [sparkassandra-dockerized](https://github.com/Ekito/sparkassandra-dockerized) project

* check out this project
* go into the platform directory and start a spark-cassandra cluster
```
$> cd platform
$> docker compose up -d
```
* now within this project, configure the `buildAndSubmit.sh` script
* Finally, run `buildAndSubmit.sh` in order to submit the job to the cluster


# Treats

* Visualize the [graph of dependencies](https://github.com/jrudolph/sbt-dependency-graph) with the command `sbt dependency-graph`.