package com.themovieguide.domain.features.searchtv

import com.themovieguide.domain.states.television.StateTelevision

interface SearchTelevisionRepository {
    suspend fun fetchSearch(query: String): StateTelevision
}
