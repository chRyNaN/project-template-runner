package com.chrynan.project.template.runner.core

interface TemplateRunner<T> {

    fun invoke(basePath: String, value: T)
}
