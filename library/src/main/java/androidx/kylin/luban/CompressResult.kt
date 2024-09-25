package androidx.kylin.luban

import java.io.File

/**
 * 压缩结果
 *
 * @author RAE
 * @date 2024/09/20
 */
sealed class CompressResult {

    /** 开始压缩 */
    class Start(val path: String?, val current: Int, val total: Int) : CompressResult()

    /** 压缩成功，逐个回调 */
    class Success(val file: File?, val current: Int, val total: Int) : CompressResult()

    /** 压缩失败 */
    class Error(val path: String?, val e: Throwable) : CompressResult()

    /** 所有任务完成 */
    data object Completed : CompressResult()
}