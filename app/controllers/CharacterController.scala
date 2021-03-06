package controllers

import javax.inject.{ Inject, Singleton }
import auth.AuthAction
import forms.{ CharacterCreateForm, CharacterDeleteForm }
import play.api.mvc._
import io.circe.generic.auto._
import io.circe.syntax._
import play.api.libs.circe.Circe
import models.service.{ MixInCharacterService, MixInCreatorService, MixInWorldService }
import scalaz.{ Failure, Success }
import scalaz.Scalaz._

@Singleton
class CharacterController @Inject()(cc: ControllerComponents, authAction: AuthAction)
    extends AbstractController(cc)
    with Circe
    with MixInCharacterService
    with MixInCreatorService
    with MixInWorldService {

  /**
    * idからキャラクターを1件取得
    *
    * @param id
    * @return
    */
  def find(id: String) = Action {
    characterService.find(id) match {
      case Left(e) =>
        println(e)
        BadGateway
      case Right(s) =>
        s match {
          case None    => NotFound
          case Some(s) => Ok(s.asJson)
        }
    }
  }

  /**
    * キャラクターを作成する
    *
    * @return 成功 Created
    *         失敗 BadRequest バリデーションエラー
    *             BadGateway ioエラー
    */
  def create() = authAction(circe.json[CharacterCreateForm]) { implicit request =>
    request.body.validate() match {
      case Failure(e) =>
        BadRequest(e.toVector.asJson)
      case Success(s) =>
        characterService.create(s.id, s.creatorId, s.name) match {
          case Left(e)  => BadGateway
          case Right(s) => Created(s.asJson)
        }
    }
  }

  /**
    * キャラクターを削除
    *
    * @return 成功 { status : ok }
    *         失敗(idが存在しない時) 存在しないキャラクターidです
    */
  def delete() = authAction(circe.json[CharacterDeleteForm]) { implicit request =>
    request.body.validate() match {
      case Failure(e) =>
        BadRequest(e.toVector.asJson)
      case Success(a) =>
        try {
          characterService.delete(a)
          Ok(("status" -> "ok").asJson)
        } catch {
          case e: Exception =>
            BadGateway(("status" -> "ng").asJson)
        }

    }
  }

  /**
    * 指定した創作者のキャラクター一覧取得
    * @param creatorId
    * @param line ページ番号
    * @return 成功　指定した創作者のキャラクター一覧
    *         失敗　NotFound 存在しない創作者IDが来た場合
    */
  def getByCreatorId(creatorId: String, line: Long) = Action { implicit request =>
    if (!creatorService.existsById(creatorId))
      NotFound
    else
      try {
        val characters = characterService.getByCreatorId(creatorId, line)
        Ok(characters.asJson)
      } catch {
        case e: Exception => BadGateway
      }
  }

  def getByWorldIdAndCreatorId(worldId: Long, creatorId: String) = Action { implicit request =>
    if (!creatorService.existsById(creatorId)
        || !worldService.exists(worldId))
      NotFound
    else
      characterService.getByWorldIdAndCreatorId(worldId, creatorId) match {
        case Left(_)  => BadGateway
        case Right(s) => Ok(s.asJson)
      }
  }

  def getNonEntry(worldId: Long, creatorId: String) = Action { implicit request =>
    if (!creatorService.existsById(creatorId)
        || !worldService.exists(worldId))
      NotFound
    else
      characterService.getNonEntry(worldId, creatorId) match {
        case Left(_)  => BadGateway
        case Right(s) => Ok(s.asJson)
      }
  }
}
