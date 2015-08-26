# spring-boot-frontend-maven-plugin

[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/AlbanSeurat/spring-boot-frontend-maven-plugin.svg?branch=master)](https://travis-ci.org/AlbanSeurat/spring-boot-frontend-maven-plugin)

## Description

The ``spring-boot-frontend-maven-plugin`` is intended for Spring boot developers 
that develop a frontend application as part of their ``Spring boot`` application.

The ``spring-boot-frontend-maven-plugin`` enables to build a frontend application and package it for deployment together with a ``Spring boot`` application. 

Currently, the plugin supports the ``Angular`` and ``Bootstrap`` frameworks for building frontend applications. 

## Prerequisites

Please make sure the following components are installed on your machine before using the plugin:

* ``maven 3.3`` [download](https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/)
* ``Java 8`` [download](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* ``nodejs`` [download](https://nodejs.org)
* ``npm`` [download](https://www.npmjs.com/package/download)

## Configuration

The following options are available for configuring the plugin:

* **``npmDependencies``**
    * The list of ``npm`` dependencies. This is where you can configure the ``npm`` dependencies.
    * By default the following dependencies are configured: 
        * ``bower@1.4.1``
        * ``grunt-cli@0.1.13``
        * ``grunt-contrib-jshint@0.11.2`` 
        * ``grunt-lesslint@1.1.14``
        * ``grunt-contrib-copy@0.8.0`` 
        * ``grunt-contrib-less@1.0.1``
        * ``grunt-contrib-uglify@0.9.1``
        * ``grunt-contrib-concat@0.5.1``
        * ``grunt-contrib-cssmin@0.13.0``
        * ``grunt-filerev@2.3.1``
        * ``grunt-usemin@2.6.1``
  
* **``bowerDependencies``**
    * The list of ``bower`` dependencies. This is where you can configure the ``bower`` dependencies.
    * By default the following dependencies are configured: 
        * ``angular#1.4.3``
        * ``bootstrap#3.3.5``
        * ``angular-ui-bootstrap#0.13.3``

* **``GruntFile``**
    * Indicates the location of the ``Gruntfile.js`` file used to build the frontend application.
    * By default the ``spring-boot-frontend-maven-plugin`` plugin creates a ``Gruntfile.js`` in the spring-boot project's ``src/main/web/.tmp`` subdirectory. 
    
## Examples

### Example using a simple configuration
The following example demonstrates a simple configuration of the ``spring-boot-frontend-maven-plugin``:

``` xml
    <build>
        <plugins>
            <plugin>
                <artifactId>spring-boot-frontend-maven-plugin</artifactId>
                <configuration>
                    <!-- default value -->
                    <workingDirectory>src/main/web</workingDirectory>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

With the above configuration, the pluging looks for files in the following subdirectories under the ``workingDirectory``  
directory specified in the pluging configuration section, in this example ``src/main/web``:

* ``less`` files located in the ``less`` subdirectory 
* ``javascript`` files in the ``scripts`` subdirectory 

The plugin uses [grunt](http://gruntjs.com/) to uglify, minify and concatenate the frontend application resources.
The pluging uses [grunt-usemin](https://github.com/yeoman/grunt-usemin) from [yeoman](http://yeoman.io/) to minify the resources and html files. 

### Example using yeoman blocks 

Here is an example of a html file using [blocks](https://github.com/yeoman/grunt-usemin#blocks):

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

### Example using angular

This is an example using angular.

All the resources (css, javascript, images _not yet_) will be generated into the ``src/main/resources/static`` directory by default, following spring-boot convention for publishing static Web content.

**TBD**

## Reporting Issues

Issues can be reported at [https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin/issues](https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin/issues)

## Code

All the code is available at [https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin](https://github.com/AlbanSeurat/spring-boot-frontend-maven-plugin)

## License

All the code distributed under the [ASL 2.0](LICENSE) license.

## Copyright

Copyright © 2015 [Alban Seurat](http://www.albanseurat.com). All rights reserved.
