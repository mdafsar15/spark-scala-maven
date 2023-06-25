package org.spark.learning

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext

import org.apache.spark.sql.SparkSession
import io.minio.MinioClient
import org.apache.commons.io.IOUtils
import org.apache.spark.sql.{ SparkSession, DataFrame }

object Demo {
  def main(args: Array[String]) {

    println("Hello World")

    //    val minioClient = new MinioClient("http://192.168.0.167", "y5gpN8aNuo79SJOL", "iz2IFQlHlgDPtyUH9oJkHsZHRcf0MBeL")
    //
    //    val stream = minioClient.getObject("recon", "EWALLET_SAMPLE_1.csv")
    //
    //    val blob = IOUtils.toByteArray(stream)
    //
    //    val stat = minioClient.statObject("recon", "EWALLET_SAMPLE_1.csv")
    //
    //    println(blob.length)
    //
    //    println(stat.bucketName())

    val spark = SparkSession.builder()
      .appName("Spark MinIO Integration")
      .master("local[*]")
      .config("spark.hadoop.fs.s3a.endpoint", "http://192.168.0.167") // Replace with your MinIO server URL
      .config("spark.hadoop.fs.s3a.access.key", "y5gpN8aNuo79SJOL") // Replace with your MinIO access key
      .config("spark.hadoop.fs.s3a.secret.key", "iz2IFQlHlgDPtyUH9oJkHsZHRcf0MBeL") // Replace with your MinIO secret key
      .config("spark.hadoop.fs.s3a.path.style.access", "true")
      .config("spark.hadoop.fs.s3a.connection.ssl.enabled", "false") // Set to true if using HTTPS
      .getOrCreate()

    // Read CSV file from MinIO
    val df: DataFrame = spark.read
      .format("csv")
      .option("header", "true")
      .load("s3a://recon/EWALLET_SAMPLE_2.csv")

   println( df.show())

    spark.stop()

  }

}