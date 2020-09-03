package edu.dongguk.openBadge.exception

class InternalServerException(
    override val message: String?,
    val code: Int
) : RuntimeException()
