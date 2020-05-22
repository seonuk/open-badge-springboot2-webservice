package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.repository.Curriculum
import edu.dongguk.openBadge.service.PortfolioCurriculumService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/portfolio/curri")
class PortfolioCurriculumController(
        private val portfolioCurriculumService: PortfolioCurriculumService
) {

    @GetMapping("/get")
    fun getCurriculum(
    ) : List<Curriculum>? {
        return portfolioCurriculumService.getCurriculumActivities()
    }


    @PostMapping("/create")
    fun postCurriculum(
            @RequestBody
            curriculum: Curriculum
    ) : Curriculum? {
        return portfolioCurriculumService.postCurriculumActivities(curriculum)
    }


}