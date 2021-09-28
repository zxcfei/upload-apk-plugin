package fei.upload.apk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File

/**
 * author : fei
 * date   : 2021/9/28
 * desc   : Pgyer 上传任务配置
 **/
class PgyerPlugin : Plugin<Project> {

    private var userOption: PgyerOption? = null

    override fun apply(target: Project) {
        userOption = target.extensions.create("upPgyer", PgyerOption::class.java)
        target.afterEvaluate {
            println("upPgyer:配置\n$userOption")
            userOption?.run { handleUploadTask(target, this) }
        }
    }

    private fun handleUploadTask(target: Project, option: PgyerOption) {
        option.apks.forEach {
            createTask(target, it.build, it.name, it.apk, option.apiKey, it.desc)
        }
    }

    private fun createTask(target: Project, build: String, name: String, apk: File, apiKey: String, desc: String) {
        val args = HashMap<String, Any>()
        args[Task.TASK_DEPENDS_ON] = build
        args[Task.TASK_GROUP] = "upPgyer"
        target.task(args, name).doLast {
            println("upPgyer:上传=>name:$name file:${apk.name}")
            val result = try {
                uploadPgyer(apiKey, desc, apk)
            } catch (e: Exception) {
                false
            }
            println("upPgyer:上传=>$result")
        }
    }
}