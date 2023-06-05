package com.themovieguide.domain.features.searchtv

import com.themovieguide.domain.states.television.StateTvMeta

interface SearchTelevision {
    suspend fun searchTelevision(query: String): StateTvMeta
}
