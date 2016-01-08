/**
 * Created by alo on 7/8/14.
 */
module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        clean: ["dist", "views/compiled"],
        concat: {
            js: {
                src: [
                    'views/compiled/templates.js'
                ],
                dest: 'dist/js/<%= pkg.name %>-<%= pkg.version %>.js'
            },
            css: {
                src: 'css/*.css',
                dest: 'dist/css/<%= pkg.name %>-<%= pkg.version %>.css'
            }
        },
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            build: {
                src: 'dist/js/<%= pkg.name %>-<%= pkg.version %>.js',
                dest: 'dist/js/<%= pkg.name %>-<%= pkg.version %>.min.js'
            }
        },
        cssmin: {
            css: {
                src: 'dist/css/<%= pkg.name %>-<%= pkg.version %>.css',
                dest: 'dist/css/<%= pkg.name %>-<%= pkg.version %>.min.css'
            }
        },
        handlebars: {
            compile: {
                options: {
                    namespace: "JST"
                },
                files: {
                    "views/compiled/templates.js": "views/**/*.hbs"
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-handlebars');

    // Default task(s).
    grunt.registerTask('default', ['clean', 'handlebars', 'concat','uglify', 'cssmin']);
};
