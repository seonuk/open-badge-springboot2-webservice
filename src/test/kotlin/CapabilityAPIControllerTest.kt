//package edu.donnguk.davinci.domain.capabilities
//
//
//import edu.donnguk.davinci.web.DTOs.capabilityDTO.CapabilitySaveRequestDTO
//import org.junit.After
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.junit4.SpringRunner
//
//import java.util.List
//
//import org.assertj.core.api.Assertions.assertThat
//
//import org.junit.Before
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.boot.web.server.LocalServerPort
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//
//@RunWith(SpringRunner::class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class CapabilityAPIControllerTest {
//
//    @LocalServerPort
//    var port:Int? =null
//
//    @Autowired
//    lateinit var restTemplate: TestRestTemplate
//
//    @Autowired
//    lateinit var capabilityRepository: CapabilityRepository
//
//    @After
//    fun cleanup() {
//        capabilityRepository.deleteAll()
//    }
//
//    @Test
//    fun 역량_등록() {
//
//        //given
//        val title = "테스트 게시글"
//
//        var requestDTO: CapabilitySaveRequestDTO = CapabilitySaveRequestDTO.Builder().title(title).build()
//
//        val url: String = "http://localhost:"+port+"/api/admin/capability/create"
//
//        //when
//        var responseEntity = restTemplate.postForEntity(url, requestDTO, Float)
////        //then
////        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
//        assertThat(responseEntity.body).isGreaterThan(0L)
//
//        var all = capabilityRepository.findAll()
//
//        assertThat(all.get(0).title).isEqualTo(title)
//
//    }
//}
