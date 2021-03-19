package com.chrynan.project.template.runner.core

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.json.Json
import java.io.File

class JsonAndYamlProjectLoader : ProjectLoader {

    private val json = Json.Default
    private val yaml = Yaml.default

    override fun invoke(path: String): Project {
        val file = File(path)

        if (!file.exists()) throw error("Project file with provided path doesn't exist. path = $path")
        if (file.isDirectory) throw error("Project file with provided path cannot be a directory.")

        val fileText = file.readText()

        return if (path.isYamlFilePath()) {
            fileText.decodeProjectYaml()
        } else {
            fileText.decodeProjectJson()
        }
    }

    private fun String.decodeProjectJson(): Project = json.decodeFromString(Project.serializer(), this)

    private fun String.decodeProjectYaml(): Project = yaml.decodeFromString(Project.serializer(), this)

    private fun String.isYamlFilePath(): Boolean = endsWith(".yaml") or endsWith(".yml")
}
