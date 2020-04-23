package edu.donnguk.davinci.domain.capabilities

import javax.persistence.*


@Entity
class Capabilities (builder : Capabilities.Builder) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(length = 500, nullable = false)
    var title:String? = null

    init {
        title = builder.title
    }

    class Builder {
        var title:String? = null
            private set

        fun title(title:String) = apply { this.title = title }
        fun build() = Capabilities(this)
    }

}