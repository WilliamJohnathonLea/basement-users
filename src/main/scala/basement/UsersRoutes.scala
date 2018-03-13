package basement

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.RootJsonFormat

object UsersRoutes extends UserJsonSupport with ErrorJsonSupport {

  private val users = Seq(
    User("1", "Harvey", "Spectre")
  )

  private def withResponse[A](statusCode: StatusCode, body: A)(implicit format: RootJsonFormat[A]): HttpResponse = {
    HttpResponse(
      statusCode,
      entity = HttpEntity(`application/json`, format.write(body).prettyPrint)
    ).withHeaders(`Content-Type`(`application/json`))
  }

  val usersRoutes: Route =
//    path("users") {
//      post {
//        complete(withResponse(StatusCodes.OK, User("", "", "")))
//      }
//    } ~
    path("users" / Segment) { id =>
      get {
        users.find(_.id == id) match {
          case Some(user) => complete(withResponse(StatusCodes.OK, user))
          case None => complete(withResponse(StatusCodes.NotFound, Error(404, "User not found.")))
        }
      }
    }

}
