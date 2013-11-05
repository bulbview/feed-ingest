package uk.co.bbc.travelnews.incident

import javax.servlet.{ ServletContextListener, ServletContextEvent }
import akka.actor.{ ActorSystem, Props }
import org.apache.camel.component.http.HttpComponent
import akka.camel.CamelExtension
import uk.co.bbc.HttpFeedConsumer

class ServletInitialiser extends ServletContextListener {
val system = ActorSystem("some-system")

  def contextDestroyed(e: ServletContextEvent): Unit = system.shutdown
  def contextInitialized(e: ServletContextEvent): Unit = {
    
    val camel = CamelExtension(system)
    val camelContext = camel.context
    camelContext.addComponent("activemq", new HttpComponent())
    val routeActor = system.actorOf(Props[HttpFeedConsumer])
    //     system.boot (true, new BootableActorLoaderService {}) // If you don't need akka-remote
  }

}