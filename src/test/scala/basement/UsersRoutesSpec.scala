package basement

import akka.http.scaladsl.model.{HttpEntity, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class UsersRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest {



  "Sending a GET request to /users/:id" when {

    "the user with the given ID is found" should {

      "return a 200 OK status" in new UsersRoutes {
        Get("/users/1") ~> usersRoutes ~> check {
          status shouldBe StatusCodes.OK
        }
      }

      "return the user data" in new UsersRoutes {
        Get("/users/1") ~> usersRoutes ~> check {
          responseAs[User] shouldBe User("1", "Harvey", "Spectre")
        }
      }

    }

    "the user with the given ID is not found" should {

      "return a 404 Not Found status" in new UsersRoutes {
        Get("/users/2") ~> usersRoutes ~> check {
          status shouldBe StatusCodes.NotFound
        }
      }

      "return the user data" in new UsersRoutes {
        Get("/users/2") ~> usersRoutes ~> check {
          responseAs[Error] shouldBe Error(404, "User not found.")
        }
      }

    }

  }

  "Sending a POST request to /users" when {

    "valid user data is received" should {

      "return a 201 Created status" in new UsersRoutes {
        Post("/users", User("2", "Mike", "Ross")) ~> usersRoutes ~> check {
          status shouldBe StatusCodes.Created
        }
      }

      "return the newly created user" in new UsersRoutes {
        Post("/users", User("2", "Mike", "Ross")) ~> usersRoutes ~> check {
          responseAs[User] shouldBe User("2", "Mike", "Ross")
        }
      }

    }

  }

  "Sending a PUT request to /users/:id" when {

    "valid user data is received" should {

      "return a 200 OK status" in new UsersRoutes {
        Put("/users/1", User("1", "Lewis", "Litt")) ~> usersRoutes ~> check {
          status shouldBe StatusCodes.OK
        }
      }

      "return the updated user data" in new UsersRoutes {
        Put("/users/1", User("1", "Lewis", "Litt")) ~> usersRoutes ~> check {
          responseAs[User] shouldBe User("1", "Lewis", "Litt")
        }
      }

    }

  }

  "Sending a DELETE request to /users/:id" when {

    "valid user data is received" should {

      "return a 204 Nn Content status" in new UsersRoutes {
        Delete("/users/1", User("1", "Lewis", "Litt")) ~> usersRoutes ~> check {
          status shouldBe StatusCodes.NoContent
        }
      }

      "return the updated user data" in new UsersRoutes {
        Delete("/users/1", User("1", "Lewis", "Litt")) ~> usersRoutes ~> check {
          response.entity shouldBe HttpEntity.Empty
        }
      }

    }

  }

}
