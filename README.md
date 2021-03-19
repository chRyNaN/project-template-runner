# project-template-runner

A utility to iterate through a project directory and replace file names and text values with their appropriate project
counterparts using Mustache templating.

This utility was created to work in companion with my Kotlin Multi-platform Library Template on Github.

Note that this utility isn't meant to be a robust and reusable tool.

## Usage

Define a Project JSON or YAML file with the following format (refer to
the [Project.kt](project-template-runner-core/src/commonMain/kotlin/com.chrynan.project.template.runner.core/Project.kt)
file for more information):

```
{
  "projectBaseDirectoryPath": "src",
  "projectName": "sample",
  "projectDescription": "Sample of using this utility.",
  "targetJvm": true,
  "targetJs": false,
  "targetAndroid": false,
  "targetIos": false,
  "enableTemplateData": false
}
```

Then run the command with the path to that project file:

```
[command] [projectPath]
```

## Sample

Refer to the [project-template-runner-sample](project-template-runner-sample) folder for an example.

## Status

This project is still in the early stages of development.

## Getting the Utility

Currently, you would have to clone the repository and build to generate the utility.

```
./gradlew build
./gradlew installDist
```

If you perform the above commands, the utility should be generated in
the `project-template-runner-app/build/install/project-template-runner-app/bin` directory.

## LICENSE

```
Copyright 2021 chRyNaN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
