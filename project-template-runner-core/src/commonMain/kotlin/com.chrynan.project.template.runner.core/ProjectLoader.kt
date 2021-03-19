package com.chrynan.project.template.runner.core

interface ProjectLoader {

    fun invoke(path: String): Project
}
