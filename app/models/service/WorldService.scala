package models.service

import java.time.ZonedDateTime

import models.entity.World
import models.repository.{ MixInWorldRepository, UsesWorldRepository }
import scalikejdbc.DB

import scala.util.{ Failure, Success, Try }

trait WorldService extends UsesWorldRepository {

  /**
   * ワールドを作成する
   * @param name 作成したワールドの名前
   * @param creatorId　ワールドを作成した創造者ID
   * @param detail　ワールド詳細
   * @param startedAt　開始日
   * @return　作成したワールドの主キー
   */
  def create(name: String, creatorId: String, detail: String, startedAt: ZonedDateTime): Long = {
    worldRepository.create(name, creatorId, detail, startedAt)
  }

  /**
   * ワールド一覧を取得する
   * @return 存在するワールドの一覧
   */
  def exists(worldId: Long): Boolean =
    worldRepository.exists(worldId)

  def getWorlds(): List[World] = {
    worldRepository.getWorlds()
  }

  /**
   * 開催中のワールド一覧を取得する
   * @return 存在する開催中のワールドの一覧
   */
  def getEnable(): List[World] = {
    worldRepository.getEnable()
  }

  /**
   * ワールドに参加
   * @param characterId
   * @param worldId
   * @return
   */
  def entry(characterId: String, worldId: Long): Long = {
    worldRepository.entry(characterId, worldId)
  }

  /**
   * ワールドに参加済みか確認
   * @param characterId
   * @param worldId
   * @return
   */
  def existsEntry(characterId: String, worldId: Long): Boolean = {
    worldRepository.existsEntry(characterId, worldId)
  }

  /**
   * 指定した創作者のワールドを取得する
   * @param creatorId
   * @return 指定した創作者のワールド
   */
  def getByCreatorId(creatorId: String): List[World] = {
    worldRepository.getByCreatorId(creatorId)
  }

  def find(id: Int) =
    DB readOnly { implicit s =>
      for {
        worldOpt <- worldRepository.find(id)
        world <- Try(worldOpt.get)
      } yield world
    } match {
      case Failure(e) => Left(e)
      case Success(s) => Right(s)
    }

}

trait UsesWorldService {
  val worldService: WorldService
}

trait MixInWorldService {
  val worldService: WorldService = new WorldService with MixInWorldRepository
}
