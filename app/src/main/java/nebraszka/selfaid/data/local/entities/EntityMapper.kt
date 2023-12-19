package nebraszka.selfaid.data.local.entities

interface EntityMapper <T, EntityModel>{
    fun mapToEntityModel(model: T): EntityModel

    fun mapToEntityModelList(models: List<T>): List<EntityModel>

    fun mapFromEntityModel(domainModel: EntityModel): T

    fun mapFromEntityModelList(domainModels: List<EntityModel>): List<T>
}