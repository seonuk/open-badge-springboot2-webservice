package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.dtos.NonCurriculumDTO
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/portfolio/noncurri")
class PortfolioNonCurriculumController(
    private val nonCurriculumService: PortfolioNonCurriculumService
) {
    @GetMapping
    fun getNoncurriculumActivities(
        @AuthenticationPrincipal
        customUser: CustomUser
    ): List<NonCurriculum> = nonCurriculumService.getNonCurriculumActivities(customUser)

    @GetMapping("/{nonCurriculumId}")
    fun getOneNonCurriculum(
        @PathVariable
        nonCurriculumId: Long,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): NonCurriculum = nonCurriculumService.getOne(nonCurriculumId, customUser)

    @PostMapping("/create")
    fun createNoncurriculumActivity(
        nonCurriculumDTO: NonCurriculumDTO,
        @RequestParam("file", required = false)
        file: List<MultipartFile>?,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): NonCurriculum {
        return nonCurriculumService.postNonCurriculumActivity(nonCurriculumDTO, customUser, file)
    }

    @PutMapping("/modify/{nonCurriculumId}")
    fun modifyNonCurriculum(
        @PathVariable
        nonCurriculumId: Long,
        @AuthenticationPrincipal
        customUser: CustomUser,
        nonCurriculumDTO: NonCurriculumDTO
    ): NonCurriculum = nonCurriculumService.modifyNonCurriculumActivity(nonCurriculumId, nonCurriculumDTO, customUser)

    @DeleteMapping("/delete/{nonCurriculumId}")
    fun deleteNonCurriculum(
        @PathVariable
        nonCurriculumId: Long,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): Unit = nonCurriculumService.deleteNonCurriculumActivity(nonCurriculumId, customUser)
}
