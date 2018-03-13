package basement

import akka.http.scaladsl.server.{HttpApp, Route}

object WebServer extends HttpApp {

  override protected def routes: Route = UsersRoutes.usersRoutes

  def main(args: Array[String]): Unit = {
    startServer("0.0.0.0", 9000)
  }

}
