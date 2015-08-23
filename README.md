# spring-boot-frontend-maven-plugin

[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/AlbanSeurat/spring-boot-frontend-maven-plugin.svg?branch=master)](https://travis-ci.org/AlbanSeurat/spring-boot-frontend-maven-plugin)

## Description

A Maven plugin allowing to integrate web resources into a spring-boot project.
Following "Convention over configuration" approach, you can start-up building your front-end application without worrying about configuring the build.

## Pre-requisites

You have to have installed ``nodejs`` and ``npm`` on your machine.

``Java 8`` and ``Maven 3.3`` also also required to build and use the plugin.

## Configuration

* npmDependencies
    * List of node dependencies, this is where you want to configure your build dependencies
    * Default: `bower@1.4.1, grunt-cli@0.1.13, grunt-contrib-jshint@0.11.2, grunt-lesslint@1.1.14, grunt-contrib-copy@0.8.0, 
    grunt-contrib-less@1.0.1,grunt-contrib-uglify@0.9.1, grunt-contrib-concat@0.5.1, grunt-contrib-cssmin@0.13.0, grunt-filerev@2.3.1, grunt-usemin@2.6.1`
  
* bowerDependencies
    * List of bower dependencies, this is where you want to configure your development dependencies
    * Default: `angular#1.4.3, bootstrap#3.3.5, angular-ui-bootstrap#0.13.3`

* GruntFile
    * Where to find the Gruntfile.js to direct your build
    * Default : plugin generate one in ``src/main/web/.tmp`` directory
     
## Goals

* prepare-deps
    * Allow to download grunt and bower deps, this could be done during a specific profile to speed regular build
    * Default phase : generate-resources
    
* build
    * run grunt build 
    * Default phase : compile
    
## Example

``` xml
    <build>
        <plugins>
            <plugin>
                <artifactId>spring-boot-frontend-maven-plugin</artifactId>
                <configuration>
                    <!-- default value -->
                    <workingDirectory>src/main/web</workingDirectory>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-deps</goal>
                        </goals>
                    </execution>
                    <execution>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

You put your less files into ``less`` subdirectory, javascript file into ``scripts`` directory and [grunt](http://gruntjs.com/) under the hood will uglify, minify and concat all resources for production system.
I use [grunt-usemin](https://github.com/yeoman/grunt-usemin) from [yeoman](http://yeoman.io/) to minify resources and html files. 

Here an example of html file with [blocks](https://github.com/yeoman/grunt-usemin#blocks):

``` html
<!doctype html>
<html lang="en" ng-app>
<head>
    <meta charset="utf-8">
    <title>My HTML File</title>
    <!-- build:less css/main.css -->
    <link rel="stylesheet/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet/less" href="less/main.less">
    <!-- endbuild -->
    <!-- build:js js/main.js -->
    <script src="bower_components/angular/angular.js"></script>
    <script src="scripts/main.js"></script>
    <!-- endbuild -->
</head>
<body>

<p>Nothing here {{'yet' + '!'}}</p>

</body>
</html>
```
This example is using angular.
All resources (css, javascript, images _not yet_) will be generated into ``src/main/resources/static`` by default to allow spring-boot to expose it naturally.

## Reporting Issues

All issues can be reported at [https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin/issues](https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin/issues)

## Code

All code can be found at [https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin](https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin)

## License

All code distributed under [ASL 2.0](LICENSE).

## Copyright

© 2015 [Alban Seurat](http://www.albanseurat.com). All rights reserved.
