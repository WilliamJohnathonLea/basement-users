package basement

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class UsersRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest {

  import UsersRoutes._

  "Sending a GET request to /users/:id" when {

    "the user with the given ID is found" should {

      "return a 200 OK status" in {
        Get("/users/1") ~> usersRoutes ~> check {
          status shouldBe StatusCodes.OK
        }
      }

      "return the user data" in {
        Get("/users/1") ~> usersRoutes ~> check {
          responseAs[User] shouldBe User("1", "Harvey", "Spectre")
        }
      }

    }

    "the user with the given ID is not found" should {

      "return a 404 Not Found status" in {
        Get("/users/2") ~> usersRoutes ~> check {
          status shouldBe StatusCodes.NotFound
        }
      }

      "return the user data" in {
        Get("/users/2") ~> usersRoutes ~> check {
          responseAs[Error] shouldBe Error(404, "User not found.")
        }
      }

    }

  }

}
