package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming._;

object NetworkWordCount {
    def main(args: Array[String]): Unit = {
        // 创建一个具有两个工作线程(working thread)和批次间隔为1秒的本地 StreamingContext
        // master 需要 2 个核，以防止饥饿情况(starvation scenario)。
        val conf = new SparkConf()
        val ssc = new StreamingContext(conf, Seconds(1))
        // 创建一个将要连接到 hostname:port 的离散流，如 localhost:9999
        val lines = ssc.socketTextStream("localhost", 9999)
        // 将每一行拆分成单词 val words = lines.flatMap(_.split(" "))
        val words = lines.flatMap(_.split(" "))
        // 计算每一个批次中的每一个单词
        val pairs = words.map(word => (word, 1))
        val wordCounts = pairs.reduceByKey(_ + _)
        // 在控制台打印出在这个离散流（DStream）中生成的每个 RDD 的前十个元素
        // 注意 : 必需要触发 action（很多初学者会忘记触发action操作，导致报错：No output operations registered, so nothing to execute）
        wordCounts.print()

        ssc.start() // 启动计算
        ssc.awaitTermination() // 等待计算的终止
    }
}
