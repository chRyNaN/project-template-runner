package com.chrynan.project.template.runner.core

import com.github.jknack.handlebars.Handlebars
import java.io.File
import java.nio.file.*

class MustacheProjectTemplateRunner(private val logger: Logger? = null) : ProjectTemplateRunner {

    private val handlebars = Handlebars()

    override fun invoke(basePath: String, value: Project) {
        val baseProjectDirectory = if (value.isProjectBaseDirectoryPathAbsolute) {
            File(value.projectBaseDirectoryPath)
        } else {
            File(basePath, value.projectBaseDirectoryPath)
        }

        baseProjectDirectory.walkTopDown().forEach { file ->
            val fileNameResult = handlebars.compileInline(file.name)
            val resultFile = file.renameTo(fileNameResult.apply(value))

            val fileText = try {
                resultFile.readText()
            } catch (exception: Exception) {
                logger?.echoWarning("Error converting file ${resultFile.path}", exception)
                null
            }

            if (fileText != null) {
                val fileTextResult = handlebars.compileInline(fileText).apply(value)

                if (fileText != fileTextResult) {
                    resultFile.writeText(fileTextResult)
                }
            }
        }
    }

    private fun File.renameTo(otherName: String): File {
        if (this.name == otherName) return this

        val otherFile = if (parent != null) File(parent, otherName) else File(otherName)

        if (otherFile.exists()) throw error("Can't rename file to a file that already exists.")

        val path = Files.move(Paths.get(path), Paths.get(otherFile.path))

        return path.toFile()
    }
}
