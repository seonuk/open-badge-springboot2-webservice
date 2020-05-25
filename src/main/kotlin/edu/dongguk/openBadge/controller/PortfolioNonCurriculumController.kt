package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.DTOS.NonCurriculumDTO
import edu.dongguk.openBadge.repository.NonCurriculum
import edu.dongguk.openBadge.service.PortfolioCurriculumService
import edu.dongguk.openBadge.service.PortfolioNonCurriculumService
import org.springframework.web.bind.annotation.*


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
        fun postNoncurriculumActivity(
                @RequestBody
                nonCurriculum: NonCurriculum
        ): NonCurriculum = nonCurriculumService.postNonCurriculumActivity(nonCurriculum)

        @PutMapping("/create/{noncurriculumId}")
        fun modifyNonCurriculum(
                @PathVariable
                nonCurriculumId: Long,
                @RequestBody
                nonCurriculumDTO: NonCurriculumDTO
        ): NonCurriculum? = nonCurriculumService.modifyNonCurriculumActivity(nonCurriculumId, nonCurriculumDTO)


}