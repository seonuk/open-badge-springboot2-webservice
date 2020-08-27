package edu.dongguk.openBadge.dtos

data class FileUploadResponse(
    val fileName: String,
    val fileDownloadUri: String,
    val fileType: String,
    val size: Long
)
