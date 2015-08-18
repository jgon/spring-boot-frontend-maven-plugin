package com.github.albanseurat.springboot.plugin;

/**
 * Created by Alban on 15/08/2015.
 */
public class Defaults {

    public static String[] npmDependencies() {
        return new String[]{
                "bower@1.4.1",
                "grunt-cli@0.1.13",
                "grunt-contrib-jshint@0.11.2",
                "grunt-lesslint@1.1.14",
                "grunt-contrib-copy@0.8.0",
                "grunt-contrib-less@1.0.1",
                "grunt-contrib-uglify@0.9.1",
                "grunt-contrib-concat@0.5.1",
                "grunt-contrib-cssmin@0.13.0",
                "grunt-filerev@2.3.1",
                "grunt-usemin@2.6.1"
        };
    }

    public static String[] bowerDependencies() {
        return new String[]{
                "angular#1.4.3",
                "bootstrap#3.3.5",
                "angular-ui-bootstrap#0.13.3"
        };
    }
}



