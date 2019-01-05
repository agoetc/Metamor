package models.entity
import java.time.ZonedDateTime

case class Character(id: String,
                     creatorId: String,
                     name: String,
                     profile: Option[String],
                     icon: Option[String],
                     deleteAt: Option[ZonedDateTime],
                     createdAt: ZonedDateTime,
                     updatedAt: ZonedDateTime)
