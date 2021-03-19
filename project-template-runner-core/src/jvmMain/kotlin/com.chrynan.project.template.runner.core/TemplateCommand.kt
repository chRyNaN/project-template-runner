package com.chrynan.project.template.runner.core

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.file

class TemplateCommand : CliktCommand(),
    Logger {

    override val commandHelp: String = """
        |This tool will run through all of the files belonging to a project directory and rename the files and their 
        |content if they have Mustache templating.
        |
        |Please provide the path to the Project JSON or YAML file to get started.
    """.trimMargin()

    private val projectPath by argument(
        name = "projectPath",
        help = """
            |This should be the path to the Project JSON or YAML file. An of a Project JSON file:
            |{
                |"projectBaseDirectoryPath": "...",
                |"projectName": "...",
                |"projectDescription": "...",
                |"targetJvm": true,
                |"targetJs": false,
                |"targetAndroid": false,
                |"targetIos": false,
                |"enableTemplateData": false
            |}
        """.trimMargin()
    ).file(mustExist = true, mustBeReadable = true, canBeFile = true, canBeDir = false)

    private val projectLoader = JsonAndYamlProjectLoader()
    private val templateRunner = MustacheProjectTemplateRunner(logger = this)

    override fun run() {
        val project = projectLoader.invoke(projectPath.path)

        templateRunner.invoke(projectPath.parentFile?.absolutePath ?: "", project)
    }

    override fun echoMessage(message: String) = echo(message)

    override fun echoWarning(message: String, throwable: Throwable?) =
        echo(formatMessageWithThrowable(message, throwable))

    override fun echoError(message: String, throwable: Throwable?) =
        echo(formatMessageWithThrowable(message, throwable), err = true)

    private fun formatMessageWithThrowable(message: String, throwable: Throwable? = null) =
        if (throwable == null) {
            message
        } else {
            "$message\n\n${throwable.message}\n\n${throwable.stackTraceToString()}"
        }
}
