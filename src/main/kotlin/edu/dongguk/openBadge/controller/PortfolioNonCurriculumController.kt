package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.DTOS.NonCurriculumDTO
import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.domain.repository.NonCurriculum
import edu.dongguk.openBadge.service.PortfolioNonCurriculumService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/portfolio/noncurri")
class PortfolioNonCurriculumController(
    private val nonCurriculumService: PortfolioNonCurriculumService
) {
    @GetMapping
    fun getNoncurriculumActivities(): List<NonCurriculum> = nonCurriculumService.getNonCurriculumActivities()

    @GetMapping("/{nonCurriculumId}")
    fun getOneNonCurriculum(
        @PathVariable
        nonCurriculumId: Long
    ): NonCurriculum? = nonCurriculumService.getOne(nonCurriculumId)

    @PostMapping("/create")
    fun createNoncurriculumActivity(
        nonCurriculumDTO: NonCurriculumDTO,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): NonCurriculum = nonCurriculumService.postNonCurriculumActivity(nonCurriculumDTO, customUser)

    @PutMapping("/modify/{nonCurriculumId}")
    fun modifyNonCurriculum(
        @PathVariable
        nonCurriculumId: Long,
        nonCurriculumDTO: NonCurriculumDTO

    ): NonCurriculum? = nonCurriculumService.modifyNonCurriculumActivity(nonCurriculumId, nonCurriculumDTO)

    @DeleteMapping("/delete/{nonCurriculumId}")
    fun deleteNonCurriculum(
        @PathVariable
        nonCurriculumId: Long
    ): Unit = nonCurriculumService.deleteNonCurriculumActivity(nonCurriculumId)
}
