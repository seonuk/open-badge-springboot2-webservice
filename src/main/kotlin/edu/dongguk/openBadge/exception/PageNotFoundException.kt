package edu.dongguk.openBadge.exception

import java.lang.RuntimeException

class PageNotFoundException(
    override val message: String?
) : RuntimeException()
