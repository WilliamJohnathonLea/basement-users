package basement

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class Error(code: Int, message: String)

trait ErrorJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val errorSupport = jsonFormat2(Error)
}
