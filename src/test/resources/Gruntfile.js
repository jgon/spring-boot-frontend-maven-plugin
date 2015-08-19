/* global module */
var path = require('path');

var lessCreateConfig = function (context, block) {
    context.outFiles = [block.dest];
    return { files : [
        {
            dest : path.join(context.outDir, block.dest),
            src : context.inFiles.map(function (inFile) {
                return path.join(context.inDir, inFile);
            })
        }
    ]};
};

module.exports = function (grunt) {
    'use strict';

    var staticConfig = {
        jshint: {
            dist: {
                src: [
                    'Gruntfile.js',
                    'scripts/**/*.js'
                ],
                options: {
                    "bitwise": true,
                    "curly": true,
                    "eqeqeq": true,
                    "forin": true,
                    "immed": true,
                    "latedef": true,
                    "newcap": true,
                    "noarg": true,
                    "noempty": true,
                    "nonew": true,
                    "plusplus": true,
                    "regexp": true,
                    "undef": true,
                    "strict": true,
                    "trailing": true,
                    "asi": false,
                    "boss": false,
                    "debug": false,
                    "eqnull": false,
                    "es5": false,
                    "esnext": false,
                    "evil": false,
                    "expr": false,
                    "funcscope": false,
                    "globalstrict": false,
                    "iterator": false,
                    "lastsemic": false,
                    "laxbreak": false,
                    "laxcomma": false,
                    "loopfunc": false,
                    "multistr": false,
                    "onecase": false,
                    "proto": false,
                    "regexdash": false,
                    "scripturl": false,
                    "smarttabs": false,
                    "shadow": false,
                    "sub": false,
                    "supernew": false,
                    "validthis": false,
                    "browser": true,
                    "couch": false,
                    "devel": false,
                    "dojo": false,
                    "jquery": false,
                    "mootools": false,
                    "node": false,
                    "nonstandard": false,
                    "prototypejs": false,
                    "rhino": false,
                    "wsh": false,
                    "nomen": false,
                    "onevar": false,
                    "passfail": false,
                    "white": false,
                    "maxerr": 100,
                    "predef": [],
                    "indent": 4
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
                        "important": 2,
                        "adjoining-classes": 2,
                        "known-properties": 2,
                        "box-sizing": 2,
                        "box-model": 2,
                        "overqualified-elements": 2,
                        "display-property-grouping": 2,
                        "bulletproof-font-face": 2,
                        "compatible-vendor-prefixes": 2,
                        "regex-selectors": 2,
                        "errors": 2,
                        "duplicate-background-images": 2,
                        "duplicate-properties": 2,
                        "empty-rules": 2,
                        "selector-max-approaching": 2,
                        "gradients": 2,
                        "fallback-colors": 2,
                        "font-sizes": 2,
                        "font-faces": 2,
                        "floats": 2,
                        "star-property-hack": 2,
                        "outline-none": 2,
                        "import": 2,
                        "ids": 2,
                        "underscore-property-hack": 2,
                        "rules-count": 2,
                        "qualified-headings": 2,
                        "selector-max": 2,
                        "shorthand": 2,
                        "text-indent": 2,
                        "unique-headings": 2,
                        "universal-selector": 2,
                        "unqualified-attributes": 2,
                        "vendor-prefix": 2,
                        "zero-units": 2
                    }
                }
            }
        },
        copy: {
            html: {
                src: 'index.html',
                dest: '../resources/static/index.html'
            }
        },
        less: {
            generated : {
                options: {
                    compress: true
                }
            }
        },
        useminPrepare: {
            html: 'index.html',
            options: {
                dest: '../resources/static',
                flow: {
                    steps: {
                        'js': ['concat', 'uglifyjs'],
                        'less': [{
                            name: 'less',
                            createConfig: lessCreateConfig
                        }]
                    },
                    post: []
                }
            }
        },
        usemin: {
            html: '../resources/static/index.html',
            options: {
                blockReplacements: {
                    less: function (block) {
                        return '<link rel="stylesheet" href="' + block.dest + '" />';
                    }
                }
            }
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

    grunt.registerTask('default', [
        'jshint:dist',
        'lesslint:dist',
        'copy:html',
        'useminPrepare',
        'concat:generated',
        'less:generated',
        'uglify:generated',
        //'filerev',
        'usemin'
    ]);
};