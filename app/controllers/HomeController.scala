package controllers

import javax.inject.Inject

import models._
import play.api.data.Forms._
import play.api.data._
import play.api.i18n._
import play.api.mvc._
import views._

/**
 * Manage a database of students
 */
class HomeController @Inject() (studentService: StudentService,
                                groupsqlService: GroupsqlService,
                                val messagesApi: MessagesApi)
  extends Controller with I18nSupport {

  /**
   * This result directly redirect to the application home.
   */
  val Home = Redirect(routes.HomeController.list(0, 2, ""))


  /**
   * Describe the student form (used in both edit and create screens).
   */
  val studentForm = Form(
    mapping(
      "id" -> ignored(None:Option[Long]),
      "name" -> nonEmptyText,
      "introduced" -> optional(date("yyyy-MM-dd")),
      "group" -> optional(longNumber)
    )(Student.apply)(Student.unapply)
  )

  // -- Actions

  /**
   * Handle default path requests, redirect to students list
   */
  def index = Action { Home }


  def generatePage = Action { implicit request =>
    Ok(html.ordin.generate("Pagina de generare a documentelor"))
  }

  /**
   * Display the paginated list of students.
   *
   * @param page Current page number (starts from 0)
   * @param orderBy Column to be sorted
   * @param filter Filter applied on student names
   */
  def list(page: Int, orderBy: Int, filter: String) = Action { implicit request =>
    Ok(html.list(
      studentService.list(page = page, orderBy = orderBy, filter = ("%"+filter+"%")),
      orderBy, filter
    ))
  }

  /**
   * Display the 'edit form' of a existing Student.
   *
   * @param id Id of the student to edit
   */
  def edit(id: Long) = Action {
    studentService.findById(id).map { student =>
      Ok(html.editForm(id, studentForm.fill(student), groupsqlService.options))
    }.getOrElse(NotFound)
  }

  /**
   * Handle the 'edit form' submission
   *
   * @param id Id of the student to edit
   */
  def update(id: Long) = Action { implicit request =>
    studentForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.editForm(id, formWithErrors, groupsqlService.options)),
      student => {
        studentService.update(id, student)
        Home.flashing("success" -> "Student %s has been updated".format(student.name))
      }
    )
  }

  /**
   * Display the 'new student form'.
   */
  def create = Action {
    Ok(html.createForm(studentForm, groupsqlService.options))
  }

  /**
   * Handle the 'new student form' submission.
   */
  def save = Action { implicit request =>
    studentForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.createForm(formWithErrors, groupsqlService.options)),
      student => {
        studentService.insert(student)
        Home.flashing("success" -> "Student %s has been created".format(student.name))
      }
    )
  }

  /**
   * Handle student deletion.
   */
  def delete(id: Long) = Action {
    studentService.delete(id)
    Home.flashing("success" -> "Student has been deleted")
  }

}
            
