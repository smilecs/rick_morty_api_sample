package com.smile.data.mappers

import com.past3.ketro.kcore.model.KMapper
import com.smile.data.db.OriginEntity
import com.smile.domain.entities.Origin

class OriginEntityMapper : KMapper<OriginEntity, Origin>() {
    override fun mapFrom(from: OriginEntity): Origin =
        Origin(
            name = from.name,
            url = from.url
        )

    fun mapTo(to: Origin): OriginEntity =
        OriginEntity(
            name = to.name,
            url = to.url
        )


}