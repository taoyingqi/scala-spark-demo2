import sun.misc.Version

name := "scala-spark-demo2"

version := "0.1"

scalaVersion := "2.11.8"

javacOptions in Compile ++= Seq("-source", "1.7", "-target", "1.7", "-Xlint:unchecked", "-Xlint:deprecation")

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.10" % "2.1.0",
  "org.apache.spark" % "spark-streaming_2.10" % "2.1.0",
  "org.apache.spark" % "spark-mllib_2.10" % "2.1.0",
  "org.apache.hadoop" % "hadoop-client" % "2.6.0",
  "org.apache.hadoop" % "hadoop-common" % "2.6.0",
  "org.apache.hadoop" % "hadoop-hdfs" % "2.6.0",
  "org.apache.spark" % "spark-sql_2.10" % "2.1.0",
  "org.apache.spark" % "spark-hive_2.10" % "2.1.0"
)
