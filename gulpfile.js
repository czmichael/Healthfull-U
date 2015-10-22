// grab our packages
var gulp   = require('gulp'),
    jshint = require('gulp-jshint'),
    livereload = require('gulp-livereload');

// define the default task and add the watch task to it
gulp.task('default', ['watch']);

// ======== Gulp tasks ==========
gulp.task('jshint', function() {
	
	return gulp.src('src/main/webapp/static/js/healthfull_u/*')
    	.pipe(jshint())
    	.pipe(jshint.reporter('jshint-stylish'));
});

gulp.task('scripts-healthfull_u', function() {
	return gulp.src('src/main/webapp/static/js/healthfull_u/*')
		.pipe(gulp.dest('/usr/local/apache-tomcat-7.0.59/webapps/ROOT/static/js/healthfull_u'));
});

gulp.task('scripts-healthfull_u-controllers', function() {
	return gulp.src('src/main/webapp/static/js/healthfull_u/controllers/*')
		.pipe(gulp.dest('/usr/local/apache-tomcat-7.0.59/webapps/ROOT/static/js/healthfull_u/controllers'));
});

gulp.task('scripts-healthfull_u-services', function() {
	return gulp.src('src/main/webapp/static/js/healthfull_u/services/*')
		.pipe(gulp.dest('/usr/local/apache-tomcat-7.0.59/webapps/ROOT/static/js/healthfull_u/services'));
});





// // ======== Gulp watch ==========
gulp.task('watch', function() {
	livereload.listen();
	gulp.watch('src/main/webapp/static/js/healthfull_u/*.js', ['jshint', 'scripts-healthfull_u']);
	gulp.watch('src/main/webapp/static/js/healthfull_u/controllers/*.js', ['jshint', 'scripts-healthfull_u-controllers']);
	gulp.watch('src/main/webapp/static/js/healthfull_u/services/*.js', ['jshint', 'scripts-healthfull_u-services']);
});


