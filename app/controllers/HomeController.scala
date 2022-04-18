package controllers

import play.api.libs.json.Json

import javax.inject._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  def welcome(alternative: Option[String]) = Action { implicit request =>
    val languages = request.headers.get("accept-language")
    alternative match {
      case None => Ok(Json.obj(
              "message" -> ("Hello, world !"),
                     "languages" -> languages))
      case Some(e) => Ok(Json.obj(
               "message" -> ("Hello, world !"),
                      "languages" -> languages,
                      "alternative" -> (e)))
    }
  }
}
