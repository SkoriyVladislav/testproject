'use strict';

const devWebpackTask = require('./webpack-configs/devWebpack.config');
const prodWebpackTask = require('./webpack-configs/prodWebpack.config');

const paths = require('./paths/config-paths');
const INPUT_BUNDLE = paths.INPUT_BUNDLE;
const BROWSER_SYNC_RELOAD_DELAY = 500;

const gulp = require('gulp');
const less = require('gulp-less');
const concat = require('gulp-concat');
const minifyCss = require('gulp-clean-css');
const gzip = require('gulp-gzip');
const sourcemaps = require('gulp-sourcemaps');
const clean = require('gulp-clean');


const nodemon = require('gulp-nodemon');
const browserSync = require('browser-sync').create();


function styles(outputDir) {
  return gulp.src(INPUT_BUNDLE + '/*.less')
    .pipe(sourcemaps.init())
    .pipe(less())
    .pipe(concat('all.css'))
    .pipe(minifyCss())
    .pipe(sourcemaps.write())
    .pipe(gulp.dest(outputDir))
    .pipe(browserSync.stream())
    .pipe(gzip())
    .pipe(gulp.dest(outputDir));
}


function cleanTask() {
  return  gulp.src('public', {read: false, allowEmpty: true})
      .pipe(clean());
}

function browserSyncTask() {
  browserSync.init(null, {
    proxy: 'http://localhost:8080',
    browser: 'chrome',
    port: 8000,
  });
}

function nodemonTask(cb) {
  let started = false;
  return nodemon({
    script: 'server.js'
  }).on('start', function () {
    if (!started) {
      cb();
      started = true;
    }
  })
    .on('restart', function onRestart() {
      setTimeout(function reload() {
          browserSync.reload({
              stream: false
          });
      }, BROWSER_SYNC_RELOAD_DELAY);
  });
}

function watch() {
  gulp.watch(INPUT_BUNDLE + '/*.less', { usePolling: true }, gulp.series(styles));
  gulp.watch(INPUT_BUNDLE + '/*.ts', { usePolling: true }, gulp.series(devWebpackTask));
}

gulp.task('devBuild', gulp.series(cleanTask, gulp.parallel(() => styles(paths.OUTPUT_DIR), devWebpackTask)));
gulp.task('prodBuild', gulp.series(cleanTask), gulp.parallel(() => styles(paths.OUTPUT_DIR_PROD), prodWebpackTask));

gulp.task('devWebpackTask', devWebpackTask);
gulp.task('default',
  gulp.series('devBuild', gulp.parallel(watch, browserSyncTask, nodemonTask))
);

gulp.task('prod',
  gulp.series('prodBuild')
);