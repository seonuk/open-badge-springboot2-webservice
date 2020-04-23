package edu.donnguk.davinci.domain.capabilities


import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import java.util.List

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

@RunWith(SpringRunner::class)
@SpringBootTest
class CapabilityRepositoryTest {

        @Autowired
        lateinit var capabilityRepository: CapabilityRepository

        @After
        fun cleanup() {
            capabilityRepository.deleteAll()
        }

        @Before
        fun setup() {
            val title = "테스트 게시글"

            capabilityRepository.save(Capabilities.Builder().title(title).build())
        }

        @Test
        fun 도메인_역량_불러오기() {

            val title = "테스트 게시글"
            var capabilityList = capabilityRepository.findAll()
            var capabilities :Capabilities = capabilityList.get(0)

            assertThat(capabilities.title).isEqualTo(title)
        }
    }
