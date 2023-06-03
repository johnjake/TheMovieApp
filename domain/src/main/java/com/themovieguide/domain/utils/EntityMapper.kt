package com.themovieguide.domain.utils

interface EntityMapper<T, M> {
    fun mapFromEntity(entity: T): M
    fun mapToEntity(domain: M): T
}
