package uk.co.bbc

import akka.actor._
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import akka.camel.{ CamelMessage, Consumer, CamelExtension }
import akka.actor.{ ActorSystem, Props }
import org.apache.camel.component.http.HttpComponent

class HttpFeedConsumer extends Consumer {
  def proxy = "?proxyHost=www-cache.reith.bbc.co.uk&proxyPort=80"
  def endpointUri = "http://delta.bbc.co.uk/nonborged/travnews/travelnews/tpeg/en/regions/rtm/wales_unplanned.xml"
  val serverDateFormat = "EEE, d MMM yyyy HH:mm:ss z"
  def lastModified(headers: Map[String, Any]) = headers.get("Last-Modified").get

  //    def toTime = ???

  def receive = {
    case msg: CamelMessage => {
      println(msg.bodyAs[String])
//      println("\n\n\n\n" + lastModified(msg.headers));
      Thread.sleep(1000)
    }
    case _ => new Exception("not a camel message")

  }
}

object Feedingest extends App {
  val system = ActorSystem("some-system")
  val camel = CamelExtension(system)
  val camelContext = camel.context
  camelContext.addComponent("http-component", new HttpComponent())
  val routeActor = system.actorOf(Props[HttpFeedConsumer])
  //  system.shutdown()
}
