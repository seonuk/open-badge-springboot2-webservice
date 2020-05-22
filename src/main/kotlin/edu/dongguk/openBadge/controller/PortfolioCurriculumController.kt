package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.DTOS.CurriculumDTO
import edu.dongguk.openBadge.repository.Curriculum
import edu.dongguk.openBadge.service.PortfolioCurriculumService
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.Id

@RestController
@RequestMapping("/api/portfolio/curri")
class PortfolioCurriculumController(
        private val portfolioCurriculumService: PortfolioCurriculumService
) {

    @GetMapping("/get")
    fun getAllCurriculum(
    ) : List<Curriculum> {
        return portfolioCurriculumService.getCurriculumActivities()
    }

    @GetMapping("/get/{curriculumId}")
    fun getOneCurriculum(
            @PathVariable
            curriculumId: Long
    ) : Curriculum? {
        return portfolioCurriculumService.getOne(curriculumId)
    }

    @PostMapping("/create")
    fun postCurriculum(
            @RequestBody
            curriculum: Curriculum
    ) : Curriculum {
        return portfolioCurriculumService.postCurriculumActivity(curriculum)
    }

    @PutMapping("/create/{curriculumId}")
    fun modifyCurriculum(
            @PathVariable
            curriculumId: Long,
            @RequestBody
            curriculum: CurriculumDTO
    ) : Curriculum? {
        return portfolioCurriculumService.modifyCurriculumActivity(curriculumId, curriculum)
    }

    @DeleteMapping("/delete/{curriculumId}")
    fun delelteCurriculm(
            @PathVariable
            curriculumId: Long
    ) : Unit {
        return portfolioCurriculumService.deleteCurriculumActivity(curriculumId)
    }


}