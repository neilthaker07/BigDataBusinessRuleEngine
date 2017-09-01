package big.data

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.SparkContext
import scala.util.parsing.json.JSON

//case class Record2(temperature: Double)

object t1 {
  def main(args : Array[String])
  {
    print("998888888888888")

    val conf = new SparkConf().setAppName("SampleApp").setMaster("local[2]")//.set("spark.ui.port","7077")
   /* val sparkSession = SparkSession.builder().master("local").config("","").getOrCreate()
    
    val df1 = sparkSession.read.json("/home/neil/Neil_Work/MS_SJSU/scala_spark_learning/Spark_Scala/git_ws_scala_spark_drools/BigDataBusinessRuleEngine/finaldemo/logs/log1.json")
    df1.select("TEMPERATURE").show()
    
    val tList = df1.select("TEMPERATURE").collectAsList()
    val t = tList.get(2).toString()
    val t2 = t.substring(1, t.length()-1)
    val temperatureLogFiles = t2.toDouble

    println("******************************************")
    println(temperatureLogFiles)
    
    val n = 150
    val nodeRedTemperature = n.toDouble
    
    if(temperatureLogFiles > nodeRedTemperature)
    {
      println("--------------+++++++++++++++++---------------+++++++++++")
      println(temperatureLogFiles)
    }
    */
    
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(5))
    val input = ssc.textFileStream("/home/neil/Neil_Work/MS_SJSU/scala_spark_learning/Spark_Scala/git_ws_scala_spark_drools/BigDataBusinessRuleEngine/finaldemo/logs/")
    val jsonValue = input.map(JSON.parseFull(_))
    .map(_.get.asInstanceOf[scala.collection.immutable.Map[String, Any]])

    //val fields = jsonValue.map(m=>Record2(m("temperature").toString.toDouble))  
    print("*-*-*-*-*-*-*--*-*-*--*--*--*-*-*-*--*--*-*-*-*-*-")
    //fields.print()
    jsonValue.print()
    
   /* val sqlContext= new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._
    
    jsonValue.foreachRDD { rdd =>
      val df = rdd.toDF()
      print("**********************1111111111111****************")
      df.show()
    }
    */
    
    
    print("/////////////////////////"+input.toString())
    ssc.start()
    ssc.awaitTermination()
    
    
    
  }
}