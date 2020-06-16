// package edu.dongguk.openBadge.controller
//
// import edu.dongguk.openBadge.dtos.FileUploadResponse
// import edu.dongguk.openBadge.service.FileUploadDownloadService
// import org.springframework.web.bind.annotation.PostMapping
// import org.springframework.web.bind.annotation.RequestMapping
// import org.springframework.web.bind.annotation.RequestParam
// import org.springframework.web.bind.annotation.RestController
// import org.springframework.web.multipart.MultipartFile
//
// @RestController
// @RequestMapping("/api/file/")
// class FileUploadDownloadController(
//    private val fileUploadDownloadService: FileUploadDownloadService
// ) {
//    @PostMapping("/uploadFile")
//    fun fileUpload(
//        @RequestParam("file")
//        file: MultipartFile
//
//    ): FileUploadResponse
//
// }
