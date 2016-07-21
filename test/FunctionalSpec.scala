
import controllers.HomeController
import org.scalatest.concurrent.ScalaFutures
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._

class FunctionalSpec extends PlaySpec with OneAppPerSuite with ScalaFutures {

  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  }

  def homeController = app.injector.instanceOf(classOf[HomeController])

  "HomeController" should {

    "redirect to the student list on /" in {
      val result = homeController.index(FakeRequest())

      status(result) must equal(SEE_OTHER)
      redirectLocation(result) mustBe Some("/students")
    }

    "list students on the the first page" in {
      val result = homeController.list(0, 2, "")(FakeRequest())

      status(result) must equal(OK)
      contentAsString(result) must include("18 students found")
    }

    "filter student by name" in {
      val result = homeController.list(0, 2, "TI-121")(FakeRequest())

      status(result) must equal(OK)
      contentAsString(result) must include("3 students found")
    }

    //running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {

    "create new student" in {
      val badResult = homeController.save(FakeRequest())

      status(badResult) must equal(BAD_REQUEST)

      val badDateFormat = homeController.save(
        FakeRequest().withFormUrlEncodedBody("name" -> "Ion", "introduced" -> "badbadbad", "group" -> "1")
      )

      status(badDateFormat) must equal(BAD_REQUEST)
      contentAsString(badDateFormat) must include("""<option value="1" selected >TI-121</option>""")
      contentAsString(badDateFormat) must include("""<input type="date" id="introduced" name="introduced" value="badbadbad" """)
      contentAsString(badDateFormat) must include("""<input type="text" id="name" name="name" value="Ion" """)


      val result = homeController.save(
        FakeRequest().withFormUrlEncodedBody("name" -> "Ion", "introduced" -> "2011-12-24", "group" -> "1")
      )

      status(result) must equal(SEE_OTHER)
      redirectLocation(result) mustBe Some("/students")
      flash(result).get("success") mustBe Some("student Ion has been created")

      val list = homeController.list(0, 2, "Ion")(FakeRequest())

      status(list) must equal(OK)
      contentAsString(list) must include("One student found")
    }
  }
}