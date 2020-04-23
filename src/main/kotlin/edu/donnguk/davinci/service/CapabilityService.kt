package edu.donnguk.davinci.service

import edu.donnguk.davinci.domain.capabilities.CapabilityRepository
import edu.donnguk.davinci.web.DTOs.capabilityDTO.CapabilitySaveRequestDTO
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CapabilityService {

    lateinit var capabilityRepository: CapabilityRepository

    @Transactional
    fun save(requestDTO: CapabilitySaveRequestDTO): Long? {
        return capabilityRepository.save(requestDTO.toEntity()).id
    }
}