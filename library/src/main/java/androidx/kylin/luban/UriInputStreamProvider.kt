package androidx.kylin.luban

import android.content.Context
import android.net.Uri
import androidx.annotation.RestrictTo
import java.io.InputStream

/**
 * Uri流
 *
 * @author RAE
 * @date 2024/09/20
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal class UriInputStreamProvider(private val context: Context, private val uri: Uri) :
    BaseInputStreamProvider() {

    override val path: String? get() = uri.path

    override fun openInternal(): InputStream? = context.contentResolver.openInputStream(uri)
}
