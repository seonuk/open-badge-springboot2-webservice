package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.dtos.CurriculumDTO
import edu.dongguk.openBadge.domain.repository.Curriculum
import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.exception.InternalServerException
import edu.dongguk.openBadge.service.PortfolioCurriculumService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/portfolio/curri")
class PortfolioCurriculumController(
    private val portfolioCurriculumService: PortfolioCurriculumService
) {

    @GetMapping("/error")
    fun error() {
        throw InternalServerException("Internal Error", 101)
    }

    @GetMapping("")
    fun getAllCurriculum(
        @AuthenticationPrincipal
        customUser: CustomUser
    ): List<Curriculum> = portfolioCurriculumService.getCurriculumActivities(customUser)

    @GetMapping("/{curriculumId}")
    fun getOneCurriculum(
        @PathVariable
        curriculumId: Long,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): Curriculum = portfolioCurriculumService.getOne(curriculumId, customUser)

    @PostMapping("/create")
    fun createCurriculum(
        @RequestBody
        curriculumDTO: CurriculumDTO,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): Curriculum = portfolioCurriculumService.postCurriculumActivity(curriculumDTO, customUser)

    @PutMapping("/modify/{curriculumId}")
    fun modifyCurriculum(
        @PathVariable
        curriculumId: Long,
        @RequestBody
        curriculumDTO: CurriculumDTO,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): Curriculum = portfolioCurriculumService.modifyCurriculumActivity(curriculumId, curriculumDTO, customUser)

    @DeleteMapping("/delete/{curriculumId}")
    fun deleteCurriculum(
        @PathVariable
        curriculumId: Long,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): Unit = portfolioCurriculumService.deleteCurriculumActivity(curriculumId, customUser)
}
