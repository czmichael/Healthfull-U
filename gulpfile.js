// grab our packages
var gulp   = require('gulp'),
    jshint = require('gulp-jshint'),
    notify = require('gulp-notify'),
    less = require('gulp-less'),
    livereload = require('gulp-livereload');


livereload({ start: true });


// define the default task and add the watch task to it
gulp.task('default', ['watch']);

//======== Global Variables ==========
var HEALTHFULL_U_TOMCAT_LOCATION = '/usr/local/apache-tomcat-7.0.59/webapps/ROOT/static';
var HEALTHFULL_U_DEV_LOCATION = 'src/main/webapp/static';

// ======== Gulp tasks ==========
gulp.task('jshint', function() {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/*')
    	.pipe(jshint())
    	.pipe(jshint.reporter('jshint-stylish'))
    	.pipe(livereload());
});

gulp.task('scripts-healthfull_u', function() {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/*')
		.pipe(gulp.dest(HEALTHFULL_U_TOMCAT_LOCATION + '/js/healthfull_u'))
		.pipe(livereload())
		.pipe(notify('======== scripts-healthfull_u is done done. =========='));
});

gulp.task('scripts-healthfull_u-controllers', function() {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/js/healthfull_u/controllers/*')
		.pipe(gulp.dest(HEALTHFULL_U_TOMCAT_LOCATION + '/js/healthfull_u/controllers'))
		.pipe(livereload())
		.pipe(notify('======== scripts-healthfull_u-controllers done. =========='));
});

gulp.task('scripts-healthfull_u-services', function() {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/js/healthfull_u/services/*')
		.pipe(gulp.dest(HEALTHFULL_U_TOMCAT_LOCATION + '/js/healthfull_u/services'))
		.pipe(livereload())
		.pipe(notify('======== scripts-healthfull_u-services done. =========='));
});

gulp.task('admin-lte', function() {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/plugins/AdminLTE/js/*')
		.pipe(gulp.dest(HEALTHFULL_U_TOMCAT_LOCATION + '/plugins/AdminLTE/js'))
		.pipe(livereload())
		.pipe(notify('======== admin-lte done. =========='));
});

gulp.task('copy-html', function() {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/*.html')
		.pipe(gulp.dest(HEALTHFULL_U_TOMCAT_LOCATION + '/'))
		.pipe(livereload())
		.pipe(notify('======== copy-html done. =========='));
});

gulp.task('less', function () {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/less/healthfull_u/healthfullU.less')
		.pipe(less())
		.pipe(gulp.dest(HEALTHFULL_U_TOMCAT_LOCATION + '/css/healthfull_u'))
		.pipe(livereload())
		.pipe(notify('======== less done. =========='));
});

gulp.task('image', function () {
	return gulp.src(HEALTHFULL_U_DEV_LOCATION + '/img/*')
		.pipe(gulp.dest(HEALTHFULL_U_TOMCAT_LOCATION + '/img'))
		.pipe(livereload())
		.pipe(notify('======== image done. =========='));
});



// ======== Gulp watch ==========
gulp.task('watch', function() {
	livereload.listen();
	gulp.watch(HEALTHFULL_U_DEV_LOCATION + '/*.js', ['jshint', 'scripts-healthfull_u']);
	gulp.watch(HEALTHFULL_U_DEV_LOCATION + '/controllers/*.js', ['jshint', 'scripts-healthfull_u-controllers']);
	gulp.watch(HEALTHFULL_U_DEV_LOCATION + '/services/*.js', ['jshint', 'scripts-healthfull_u-services']);
	gulp.watch(HEALTHFULL_U_DEV_LOCATION + '/plugins/AdminLTE/js/*.js', ['jshint', 'admin-lte']);
	gulp.watch(HEALTHFULL_U_DEV_LOCATION + '/*.html', ['copy-html']);
	gulp.watch(HEALTHFULL_U_DEV_LOCATION + '/less/**/*.less', ['less']);
	gulp.watch(HEALTHFULL_U_DEV_LOCATION + '/img/*', ['image']);
});





