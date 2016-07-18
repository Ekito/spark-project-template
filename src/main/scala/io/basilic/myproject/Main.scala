package io.basilic.myproject

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object Main {


  def sparkMaster(conf: SparkConf): String = conf.get("spark.master", "local[*]")

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SparkProjectTemplate")
    conf.setMaster(sparkMaster(conf))
    val sc = new SparkContext(conf)


    /* Using HiveContext instead of SparkContext adds a lot more SQL features (CAST(), PERCENTILE(), ...) */
    // val sqlc = new org.apache.spark.sql.hive.HiveContext(sc)
    val sqlc = new org.apache.spark.sql.SQLContext(sc)

    /*
  Sources are standard URIs.
  Wildcard support depends on the URI scheme (not supported by file://, supported by s3n://).
  Gzip support is automatic.
   */

    val source = sparkMaster(conf) match {
      case "local[*]" => getClass.getResource("/data.json").toURI.toString
      case _ => "file:///data/data.json"
    }

    // val source = "s3n://bucket.name/access_logs/*/130/*.gz"

    /*
  Load the source data in a SchemaRDD (ready for SQL queries)
  and persist it on RAM, and DISK if not enough RAM
   */

    val sourceRdd = sqlc
      .read
      .schema(Schemas.mySchema)
      .json(source)
      .persist(StorageLevel.MEMORY_AND_DISK_SER)
    println(sourceRdd.take(1).toList)

    /* Register the RDD as a table */
    sourceRdd.registerTempTable("mytable")

    /* Make an SQL query */
    val resultRdd = sqlc.sql("SELECT * FROM mytable LIMIT 1")
    println(resultRdd.take(1).toList)
  }
}