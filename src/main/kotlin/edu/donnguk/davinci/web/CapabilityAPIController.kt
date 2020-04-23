package edu.donnguk.davinci.web

import edu.donnguk.davinci.service.CapabilityService
import edu.donnguk.davinci.web.DTOs.capabilityDTO.CapabilitySaveRequestDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CapabilityAPIController {

    lateinit var capabilityService: CapabilityService


    @PostMapping("/api/admin/capability/create")
    fun save(@RequestBody requestDTO: CapabilitySaveRequestDTO): Long? {
        return capabilityService.save(requestDTO)
    }
}