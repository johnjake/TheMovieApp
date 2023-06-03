package com.themovieguide.data.mapper

import com.themovieguide.data.model.dto.Casts
import com.themovieguide.data.model.dto.Crews
import com.themovieguide.data.model.meta.CastMeta
import com.themovieguide.domain.model.cast.Cast
import com.themovieguide.domain.model.cast.Crew
import com.themovieguide.domain.model.cast.MainCast

fun CastMeta.toCastMovie(): MainCast {
    val listCast = this.cast?.toListCast()
    val listCrew = this.crew?.toListCrew()
    return MainCast(
        cast = listCast,
        crew = listCrew,
        id = this.id,
    )
}

fun Casts.toCast(): Cast {
    return Cast(
        adult = this.adult,
        castId = this.cast_id,
        character = this.character,
        creditId = this.credit_id,
        gender = this.gender,
        id = this.id,
        knownForDepartment = this.known_for_department,
        name = this.name,
        order = this.order,
        originalName = this.original_name,
        popularity = this.popularity,
        profilePath = this.profile_path,
    )
}

fun Crews.toCrews(): Crew {
    return Crew(
        adult = this.adult,
        creditId = this.credit_id,
        department = this.department,
        gender = this.gender,
        id = this.id,
        job = this.job,
        knownForDepartment = this.known_for_department,
        name = this.name,
        originalName = this.original_name,
        popularity = this.popularity,
        profilePath = this.profile_path,
    )
}

fun List<Crews>.toListCrew(): List<Crew> {
    return this.map {
        it.toCrews()
    }
}

fun List<Casts>.toListCast(): List<Cast> {
    return this.map {
        it.toCast()
    }
}
