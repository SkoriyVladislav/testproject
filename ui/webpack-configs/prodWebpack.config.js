const path = require('path');
const paths = require('../paths/config-paths');
const INPUT_JS = paths.INPUT_JS;
const OUTPUT_DIR = paths.OUTPUT_DIR;

const gulp = require('gulp');
const gzip = require('gulp-gzip');
const webpackStream = require('webpack-stream');
const named = require('vinyl-named');


module.exports = function () {
    let options = {
        mode: 'production',
        context: path.join(__dirname, '/../src/components/bundle-content'),
        entry: {
            bundle: './bundle',
        },
        output: {
            path: path.join(__dirname + '/' + OUTPUT_DIR),
            filename: '[name].js',
            library: '[name]'
        },
        module: {
            rules: [{
                test: /\.ts?$/,
                loader: 'ts-loader',
                exclude: /node_modules/,
                options: {
                    transpileOnly: true
                }
            }]
        },
        resolve: {
            extensions: ['.ts', '.tsx', '.js']
        },

    };
    return gulp.src(INPUT_JS)
        .pipe(named())
        .pipe(webpackStream(options))
        .pipe(gulp.dest(paths.OUTPUT_DIR_PROD))
        .pipe(gzip())
        .pipe(gulp.dest(paths.OUTPUT_DIR_PROD))
};
