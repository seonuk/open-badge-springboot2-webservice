package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.DTOS.CurriculumDTO
import edu.dongguk.openBadge.domain.repository.Curriculum
import edu.dongguk.openBadge.domain.repository.CustomUser
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
import javax.servlet.ServletContext

@RestController
@RequestMapping("/api/portfolio/curri")
class PortfolioCurriculumController(
    private val portfolioCurriculumService: PortfolioCurriculumService,
    val servletContext: ServletContext
) {

    @GetMapping("")
    fun getAllCurriculum(): List<Curriculum> {

        return portfolioCurriculumService.getCurriculumActivities()
    }

    @GetMapping("/{curriculumId}")
    fun getOneCurriculum(
        @PathVariable
        curriculumId: Long
    ): Curriculum? = portfolioCurriculumService.getOne(curriculumId)

    @PostMapping("/create")
    fun createCurriculum(
        @RequestBody
        curriculum: Curriculum,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): Long? = portfolioCurriculumService.postCurriculumActivity(curriculum, customUser)

    @PutMapping("/modify/{curriculumId}")
    fun modifyCurriculum(
        @PathVariable
        curriculumId: Long,
        @RequestBody
        curriculum: CurriculumDTO
    ): Curriculum? = portfolioCurriculumService.modifyCurriculumActivity(curriculumId, curriculum)

    @DeleteMapping("/delete/{curriculumId}")
    fun deleteCurriculum(
        @PathVariable
        curriculumId: Long
    ): Unit = portfolioCurriculumService.deleteCurriculumActivity(curriculumId)
}
