import play.api.test._
import play.api.test.Helpers._
import org.fluentlenium.core.filter.FilterConstructor._
import org.scalatestplus.play.PlaySpec

import org.scalatestplus.play._

class IntegrationSpec extends PlaySpec {
  
  "Application" should {
    
    "work from within a browser" in {
      running(TestServer(3333), HTMLUNIT) { browser =>
        browser.goTo("http://localhost:3333/")
        
        browser.$("header h1").first.getText must equal("Play sample application — student database")
        browser.$("section h1").first.getText must equal("18 students found")
        
        browser.$("#pagination li.current").first.getText must equal("Displaying 1 to 10 of 18")
        
        browser.$("#pagination li.next a").click()
        
        browser.$("#pagination li.current").first.getText must equal("Displaying 11 to 18 of 18")
        browser.$("#searchbox").text("Ion")
        browser.$("#searchsubmit").click()
        
        browser.$("section h1").first.getText  must equal("13 students found")
        browser.$("a", withText("Ion")).click()
        
        browser.$("section h1").first.getText  must equal("Edit student")

        browser.$("#discontinued").text("")
        browser.$("input.primary").click()

        browser.$("section h1").first.getText must equal("18 students found")
        browser.$(".alert-message").first.getText must equal("Done! student Ion has been updated")
        
        browser.$("#searchbox").text("Ion")
        browser.$("#searchsubmit").click()
        
        browser.$("a", withText("TI-122")).click()
        browser.$("input.danger").click()

        browser.$("section h1").first.getText must equal("18 students found")
        browser.$(".alert-message").first.getText must equal("Done! student has been deleted")
        
        browser.$("#searchbox").text("TI-121")
        browser.$("#searchsubmit").click()
        
        browser.$("section h1").first.getText must equal("3 students found")

      }
    }
    
  }
  
}