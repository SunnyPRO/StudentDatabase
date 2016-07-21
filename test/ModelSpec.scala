import models.StudentService
import org.scalatestplus.play._

class ModelSpec extends PlaySpec with OneAppPerSuite {

  var studentService: StudentService = app.injector.instanceOf(classOf[StudentService])

  import models._

  // -- Date helpers
  
  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  }
  
  // --
  
  "student model" should {
    
    "be retrieved by id" in {
        val macintosh = studentService.findById(21).get
      
        macintosh.name must equal("TI-121")
        macintosh.introduced.value must matchPattern {
          case date:java.util.Date if dateIs(date, "1992-01-01") =>
        }
    }
    
    "be listed along its groups" in {

        val students = studentService.list()

        students.total must equal(18)
        students.items must have length(10)
    }
    
    "be updated if needed" in {

        studentService.update(21, Student(name="TI-121",
          introduced=None,
          discontinued=None,
          groupId=Some(1)))
        
        val macintosh = studentService.findById(21).get
        
        macintosh.name must equal("TI-121")
        macintosh.introduced mustBe None
    }
    
  }
  
}