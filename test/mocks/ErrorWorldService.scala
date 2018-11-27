package mocks

import java.time.ZonedDateTime

import models.entity.World
import models.repository.WorldRepository
import models.service.WorldService

object ErrorWorldRepositoryImpl extends WorldRepository {
  def create(name: String, creatorId: String, detail: String, startedAt: ZonedDateTime): Long =
    throw new Exception
  def getWorlds(): List[World] =
    throw new Exception
}

trait MixInErrorWorldRepository {
  val worldRepository: WorldRepository = ErrorWorldRepositoryImpl
}

trait MixInErrorWorldService {
  val mockWorldService: WorldService = new WorldService with MixInErrorWorldRepository
}