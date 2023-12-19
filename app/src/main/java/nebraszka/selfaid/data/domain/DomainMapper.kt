package nebraszka.selfaid.data.domain

interface DomainMapper <T, DomainModel>{
    fun mapToDomainModel(model: T): DomainModel

    fun mapToDomainModelList(models: List<T>): List<DomainModel>

    fun mapFromDomainModel(domainModel: DomainModel): T

    fun mapFromDomainModelList(domainModels: List<DomainModel>): List<T>
}