package basement

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class User(id: String, firstname: String, lastname: String)

trait UserJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val userSupport = jsonFormat3(User)
}
