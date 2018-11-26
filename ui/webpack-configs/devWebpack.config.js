const path = require('path');
const paths = require('../paths/config-paths');
const INPUT_JS = paths.INPUT_JS;
const INPUT_BUNDLE = paths.INPUT_BUNDLE;
const OUTPUT_DIR = paths.OUTPUT_DIR;
const CONTEXT_PATH = path.join(__dirname, '/../src/components/bundle-content');
const TS_CONFIG = path.join(__dirname, '../tsconfig.json');

const gulp = require('gulp');
const webpackStream = require('webpack-stream');
const named = require('vinyl-named');
const ForkTsCheckerWebpackPlugin = require('fork-ts-checker-webpack-plugin');
const TSLintPlugin = require('tslint-webpack-plugin');
const browserSync = require('browser-sync').create();


module.exports = function () {
    let options = {
        mode: 'development',
        context: CONTEXT_PATH,
        entry: {
            bundle: './bundle.ts',
        },
        devtool: 'inline-source-map',
        output: {
            path: path.join(__dirname, '/', OUTPUT_DIR),
            filename: '[name].js',
            library: '[name]'
        },

        module: {
            rules: [{
                test: /\.ts?$/,
                loader: 'ts-loader',
                exclude: /node_modules/,
                options: {
                    transpileOnly: true,
                    configFile: TS_CONFIG
                }
            }]
        },
        plugins: [
            new ForkTsCheckerWebpackPlugin({
                tsconfig: TS_CONFIG
            }),
            new TSLintPlugin({
                files: [INPUT_BUNDLE + '/*.ts'],
                format: 'codeFrame'
            })
        ],
        resolve: {
            extensions: ['.ts', '.js']
        }
    };
    return gulp.src(INPUT_JS)
        .pipe(named())
        .pipe(webpackStream(options))
        .pipe(gulp.dest(OUTPUT_DIR))
        .pipe(browserSync.stream())
};

