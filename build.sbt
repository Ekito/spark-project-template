name := "spark-project-template"

version := "1.0"

scalaVersion := "2.10.4"

// | @see https://github.com/jrudolph/sbt-dependency-graph
net.virtualvoid.sbt.graph.Plugin.graphSettings

val sparkVersion = "1.6.0"

 libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"

 libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

 libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion % "provided"


// | Extra libraries

// resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"

// libraryDependencies += "ua_parser" % "ua-parser" % "1.3.0" withSources()

// libraryDependencies += "com.maxmind.geoip" % "geoip-api" % "1.2.14" withSources() withJavadoc()

// libraryDependencies += "joda-time" % "joda-time" % "2.5" withSources() withJavadoc()

// libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.3.11" withSources() withJavadoc()