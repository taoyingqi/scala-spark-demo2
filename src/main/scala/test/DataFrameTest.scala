package test

import org.apache.spark._

object DataFrameTest {
  // 创建一个表示用户的自定义类
  case class Person(name: String, age: Int)

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SparkSQL Demo")
    val sc = new SparkContext(conf)

    // 首先用已有的Spark Context对象创建SQLContext对象
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    // 导入语句，可以隐式地将RDD转化成DataFrame
    import sqlContext.implicits._

    // 用数据集文本文件创建一个Person对象的DataFrame
    val people = sc.textFile("/test/customers.txt").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt)).toDF()

    // 将DataFrame注册为一个表
    people.registerTempTable("people")

    // SQL查询
    val teenagers = sqlContext.sql("SELECT name, age FROM people WHERE age >= 13 AND age <= 19")

    // 输出查询结果，按照顺序访问结果行的各个列。
    teenagers.map(t => "Name: " + t(0)).collect().foreach(println)

    sc.stop()
  }
}
