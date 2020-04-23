package edu.donnguk.davinci.domain.capabilities

import org.springframework.data.jpa.repository.JpaRepository


interface CapabilityRepository :JpaRepository<Capabilities,Long> {
}