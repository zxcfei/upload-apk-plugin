# UploadApkPlugin

#### 介绍
Pgyer apk打包上传插件

```
plugins {
    id 'fei.upload.apk.pgyer'
}
upPgyer {
    apiKey = "****"
    apk {
        name = "upDebug"
        build = "assembleDebug"
        desc = "***"
        apk = file("./build/outputs/apk/debug/app-debug.apk")
    }
    apk {
        name = "upRelease"
        build = "assembleRelease"
        desc = "***"
        apk = file("./build/outputs/apk/release/app-release.apk")
    }
```