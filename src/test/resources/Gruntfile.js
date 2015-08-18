/* global module */
module.exports = function (grunt) {
    'use strict';

    var staticConfig = {
        jshint: {
            dist: {
                src: [
                    'Gruntfile.js',
                    'scripts/**/*.js'
                ]
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
                    cleancss: true
                },
                files: [
                    {
                        expand: true,
                        cwd: 'less',
                        src: ['*.less'],
                        dest: 'css',
                        ext: '.css'
                    }
                ]
            }
        },
        copy: {
            html: {
                src: 'index.html',
                dest: '../resources/static/index.html'
            }
        },
        useminPrepare: {
            html: 'index.html',
            options: {
                dest: '../resources/static'
            }
        },
        usemin: {
            html: '../resources/static/index.html'
        }
    };

    grunt.initConfig(staticConfig);

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-lesslint');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-filerev');
    grunt.loadNpmTasks('grunt-usemin');

    // simple build task
    grunt.registerTask('usemin-global', [
        'copy:html',
        'useminPrepare',
        'concat:generated',
        'cssmin:generated',
        'uglify:generated',
        //'filerev',
        'usemin'
    ]);

    grunt.registerTask('default', [
        'jshint:dist',
        'lesslint:dist',
        'less:dist',
        'usemin-global'
    ]);
};