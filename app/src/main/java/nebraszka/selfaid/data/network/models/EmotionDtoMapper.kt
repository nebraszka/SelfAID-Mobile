package nebraszka.selfaid.data.network.models

import nebraszka.selfaid.data.domain.DomainMapper
import nebraszka.selfaid.data.domain.model.Emotion

class EmotionDtoMapper : DomainMapper<EmotionDto, Emotion> {
    override fun mapToDomainModel(dto: EmotionDto): Emotion {
        return Emotion().apply {
            id = dto.id
            emotion = dto.emotion
            description = dto.description
        }
    }

    override fun mapToDomainModelList(models: List<EmotionDto>): List<Emotion> {
        return models.map { mapToDomainModel(it) }
    }

    // TODO May be to remove
    override fun mapFromDomainModel(emotion: Emotion): EmotionDto {
        return EmotionDto(
            id = emotion.id ?: 0,
            emotion = emotion.emotion ?: "",
            description = emotion.description ?: ""
        )
    }

    override fun mapFromDomainModelList(domainModels: List<Emotion>): List<EmotionDto> {
        return domainModels.map { mapFromDomainModel(it) }
    }
}