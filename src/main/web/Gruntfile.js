/* global module */
module.exports = function (grunt) {
    'use strict';

    var staticConfig = {
        pkg: grunt.file.readJSON('package.json'),
        jshint: {
            dist: {
                src: [
                    'Gruntfile.js',
                    'scripts/**/*.js'
                ]
            }
        },
        uglify: {
            dist: {
                options: {
                    banner: '/*! <%= pkg.name %> - v<%= pkg.version %> - ' +
                    '<%= grunt.template.today("yyyy-dd-mm HH:MM") %> */\n',
                    compress: true
                },
                files: {
                    '../resources/static/scripts/main.js': [
                        'scripts/**/*.js'
                    ]
                }
            }
        },
        lesslint: {
            dist: {
                src: [
                    'less/**/*.less'
                ],
                options: {
                    csslint: {
                        csslintrc: '.csslintrc'
                    }
                }
            }
        },
        less: {
            dist: {
                options: {
                    banner: '/*! <%= pkg.name %> - v<%= pkg.version %> - ' +
                    '<%= grunt.template.today("yyyy-dd-mm HH:MM") %> */\n',
                    cleancss: true,
                    concat: true,
                    compress: true
                },
                files: {
                    '../resources/static/css/main.css': [
                        'less/**/*.less'
                    ]
                }
            }
        }
    };

    grunt.initConfig(staticConfig);

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-lesslint');
    grunt.loadNpmTasks('grunt-contrib-less');

    grunt.registerTask('default', [
        'jshint:dist',
        'uglify:dist',
        'lesslint:dist',
        'less:dist'
    ]);
};