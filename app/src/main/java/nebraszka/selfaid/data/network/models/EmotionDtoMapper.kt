package nebraszka.selfaid.data.network.models

import nebraszka.selfaid.data.domain.DomainMapper
import nebraszka.selfaid.data.domain.model.Emotion
import nebraszka.selfaid.data.local.entities.EmotionEntity
import nebraszka.selfaid.data.local.entities.EntityMapper

class EmotionDtoMapper : DomainMapper<EmotionDto, Emotion>,
    EntityMapper<EmotionDto, EmotionEntity> {

    // DomainMapper
    override fun mapToDomainModel(dto: EmotionDto): Emotion {
        return Emotion().apply {
            emotion = dto.name
            description = dto.description
        }
    }

    override fun mapToDomainModelList(models: List<EmotionDto>): List<Emotion> {
        return models.map { mapToDomainModel(it) }
    }

    // TODO May be to remove
    override fun mapFromDomainModel(emotion: Emotion): EmotionDto {
        return EmotionDto(
            name = emotion.emotion ?: "",
            description = emotion.description ?: ""
        )
    }

    override fun mapFromDomainModelList(domainModels: List<Emotion>): List<EmotionDto> {
        return domainModels.map { mapFromDomainModel(it) }
    }

    // EntityMapper
    override fun mapToEntityModel(model: EmotionDto): EmotionEntity {
        return EmotionEntity(
            emotion = model.name ?: "",
            description = model.description ?: ""
        )
    }

    override fun mapToEntityModelList(models: List<EmotionDto>): List<EmotionEntity> {
        return models.map { mapToEntityModel(it) }
    }

    override fun mapFromEntityModel(domainModel: EmotionEntity): EmotionDto {
        return EmotionDto(
            name = domainModel.emotion ?: "",
            description = domainModel.description ?: ""
        )
    }

    override fun mapFromEntityModelList(domainModels: List<EmotionEntity>): List<EmotionDto> {
        return domainModels.map { mapFromEntityModel(it) }
    }
}