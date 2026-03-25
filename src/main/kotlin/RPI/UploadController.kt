package RPI

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

@Controller
class UploadController {

    @Value("\${upload.directory}")
    private lateinit var uploadDirectory: String

    @GetMapping("/app/upload.jsp")
    fun showUploadForm(): String {
        return "app/upload"
    }

    @PostMapping("/DB_Upload")
    fun handleUpload(
        @RequestParam("file") file: MultipartFile,
        request: HttpServletRequest
    ): String {
        val fileName = file.originalFilename
        println("the FileName was $fileName")
        var resultString: String

        try {
            BufferedOutputStream(FileOutputStream(File("$uploadDirectory/$fileName")), 1_000_000).use { out ->
                BufferedInputStream(file.inputStream, 1_000_000).use { input ->
                    val bytes = ByteArray(8192)
                    var read: Int
                    while (input.read(bytes).also { read = it } != -1) {
                        out.write(bytes, 0, read)
                    }
                }
            }
            resultString = "New File Created at $uploadDirectory/$fileName"
        } catch (e: Exception) {
            resultString = "Did You Choose a FILE?: "
        }

        request.setAttribute("result", resultString)
        return "app/upload"
    }
}
