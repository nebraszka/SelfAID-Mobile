package nebraszka.selfaid.data.local.entities

import nebraszka.selfaid.data.domain.DomainMapper
import nebraszka.selfaid.data.domain.model.Emotion

class EmotionEntityMapper : DomainMapper<EmotionEntity, Emotion> {
    override fun mapToDomainModel(model: EmotionEntity): Emotion {
        return Emotion().apply {
            id = model.id
            emotion = model.emotion
            description = model.description
        }
    }

    override fun mapToDomainModelList(models: List<EmotionEntity>): List<Emotion> {
        return models.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainModel(domainModel: Emotion): EmotionEntity {
        return EmotionEntity(
            emotion = domainModel.emotion ?: "",
            description = domainModel.description ?: ""
        )
    }

    override fun mapFromDomainModelList(domainModels: List<Emotion>): List<EmotionEntity> {
        return domainModels.map { mapFromDomainModel(it) }
    }
}