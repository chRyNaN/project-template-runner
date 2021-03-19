package com.chrynan.project.template.runner.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    @SerialName(value = "projectBaseDirectoryPath") val projectBaseDirectoryPath: String,
    @SerialName(value = "isProjectBaseDirectoryPathAbsolute") val isProjectBaseDirectoryPathAbsolute: Boolean = false,
    @SerialName(value = "projectName") val projectName: String,
    @SerialName(value = "projectDescription") val projectDescription: String,
    @SerialName(value = "targetJvm") val targetJvm: Boolean = true,
    @SerialName(value = "targetJs") val targetJs: Boolean = false,
    @SerialName(value = "targetAndroid") val targetAndroid: Boolean = false,
    @SerialName(value = "targetIos") val targetIos: Boolean = false,
    @SerialName(value = "enableTemplateData") val enableTemplateData: Boolean = true
)
