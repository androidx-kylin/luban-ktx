package androidx.kylin.luban

import androidx.annotation.RestrictTo
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

/**
 * 文件流
 *
 * @author RAE
 * @date 2024/09/20
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal class FileInputStreamProvider(private val file: File) : BaseInputStreamProvider() {

    override val path: String get() = file.absolutePath

    override fun openInternal(): InputStream = FileInputStream(file)
}
