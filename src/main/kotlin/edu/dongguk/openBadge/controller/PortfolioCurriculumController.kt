package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.DTOS.CurriculumDTO
import edu.dongguk.openBadge.repository.Curriculum
import edu.dongguk.openBadge.service.PortfolioCurriculumService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/portfolio/curri")
class PortfolioCurriculumController(
        private val portfolioCurriculumService: PortfolioCurriculumService
) {

    @GetMapping("")
    fun getAllCurriculum(
    ) : List<Curriculum> = portfolioCurriculumService.getCurriculumActivities()


    @GetMapping("/{curriculumId}")
    fun getOneCurriculum(
            @PathVariable
            curriculumId: Long
    ) : Curriculum? = portfolioCurriculumService.getOne(curriculumId)


    @PostMapping("/create")
    fun postCurriculum(
            @RequestBody
            curriculum: Curriculum
    ) : Curriculum = portfolioCurriculumService.postCurriculumActivity(curriculum)

    @PutMapping("/create/{curriculumId}")
    fun modifyCurriculum(
            @PathVariable
            curriculumId: Long,
            @RequestBody
            curriculum: CurriculumDTO
    ) : Curriculum? = portfolioCurriculumService.modifyCurriculumActivity(curriculumId, curriculum)

    @DeleteMapping("/delete/{curriculumId}")
    fun deleteCurriculum(
            @PathVariable
            curriculumId: Long
    ) : Unit = portfolioCurriculumService.deleteCurriculumActivity(curriculumId)


}