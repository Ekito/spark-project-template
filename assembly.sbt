import AssemblyKeys._

assemblySettings

assembleArtifact in packageScala := false

// https://github.com/sbt/sbt-assembly#-provided-configuration

// This line makes it possible to run the spark job locally with 'sbt run', although dependencies were defined as
// being provided.
// Note, when running a spark job locally from within an IDE, always spawn it as an SBT task.
run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, runner in (Compile, run))