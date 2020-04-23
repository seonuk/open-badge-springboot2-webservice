package edu.donnguk.davinci.web.DTOs.capabilityDTO

import edu.donnguk.davinci.domain.capabilities.Capabilities


class CapabilitySaveRequestDTO(builder : CapabilitySaveRequestDTO.Builder) {
    var title: String

    init {
        title = builder.title
    }

    class Builder {
        lateinit var title:String
            private set

        fun title(title:String) = apply { this.title = title }
        fun build() = CapabilitySaveRequestDTO(this)
    }

    fun toEntity(): Capabilities {
        return Capabilities.Builder()
            .title(title)
            .build()
    }
}