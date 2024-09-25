# Luban

鲁班(Luban)是一个图片压缩工具，仿微信朋友圈压缩策略。请先查看了解原项目的[使用说明](https://github.com/Curzibn/Luban/blob/master/README.md)。

本项目是基于[Luban](https://github.com/Curzibn/Luban)的[d1fca89提交版本](https://github.com/Curzibn/Luban/commit/d1fca89f2564e995cbdd9defb8000e5212ab5000)改写成Kotlin版本开发。

代码和使用方法有所调整，算法及其他与原来一致。



# 依赖

[![](https://jitpack.io/v/androidx-kylin/luban-ktx.svg)](https://jitpack.io/#androidx-kylin/luban-ktx)

```sh
repositories {
  maven { url 'https://jitpack.io' }
}
	
implementation 'com.github.androidx-kylin:luban-ktx:$jitpack_version'
```

# 使用

```kotlin
lifecycleScope.launch {
    val luban = Luban(context)
        .load(file)
        .ignoreSize(100)
        .quality(60)
        .keepAlpha(false)
        .keepSize(false)

    // 只处理一个
    val result: CompressResult = luban.single()

    // 逐个回调
    luban.collect { result ->
        // do something
    }

    // 全部一起回调
    val result: List<CompressResult> = luban.launch()
}
```