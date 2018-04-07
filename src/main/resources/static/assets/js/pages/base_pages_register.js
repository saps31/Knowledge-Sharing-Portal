/*
 *  Document   : base_pages_register.js
 *  Author     : pixelcave
 *  Description: Custom JS code used in Register Page
 */

var BasePagesRegister = function() {
    // Init Register Form Validation, for more examples you can check out https://github.com/jzaefferer/jquery-validation
    var initValidationRegister = function(){
        jQuery('.js-validation-register').validate({
            errorClass: 'help-block text-right animated fadeInDown',
            errorElement: 'div',
            errorPlacement: function(error, e) {
                jQuery(e).parents('.form-group > div').append(error);
            },
            highlight: function(e) {
                jQuery(e).closest('.form-group').removeClass('has-error').addClass('has-error');
                jQuery(e).closest('.help-block').remove();
            },
            success: function(e) {
                jQuery(e).closest('.form-group').removeClass('has-error');
                jQuery(e).closest('.help-block').remove();
            },
            rules: {
                'username': {
                    required: true,
                    minlength: 3
                },
				'firstname': {
                    required: true,
                    minlength: 3
                },
				'lastname': {
                    required: true,
                    minlength: 3
                },
                'emailid': {
                    required: true,
                    email: true
                },
                'password': {
                    required: true,
                    minlength: 5
                },
                'cpwd': {
                    required: true,
                    equalTo: '#pwd'
                },
				'institute': {
                    required: true,
                    minlength: 3
                },
				'department': {
                    required: true,
                    minlength: 3
                },
                'terms': {
                    required: true
                },
                'useridcard': {
                    required: true
                },
                'enroll': {
                    required: true,
                    minlength: 5                   
                }
            },
            messages: {
                'username': {
                    required: 'Please enter a username',
                    minlength: 'Your username must consist of at least 3 characters'
                },
				'firstname': {
                    required: 'Please enter your firstname',
                    minlength: 'Your firstname must consist of at least 3 characters'
                },
				'lastname': {
                    required: 'Please enter your lastname',
                    minlength: 'Your lastname must consist of at least 3 characters'
                },
                'email': 'Please enter a valid email address',
                'password': {
                    required: 'Please provide a password',
                    minlength: 'Your password must be at least 5 characters long'
                },
                'cpwd': {
                    required: 'Please provide a password',
                    minlength: 'Your password must be at least 5 characters long',
                    equalTo: 'Please enter the same password as above'
                },
				'institute': {
                    required: 'Please enter your institution',
                    minlength: 'Your institution name must consist of at least 3 characters'
                },
				'department': {
                    required: 'Please enter your department',
                    minlength: 'Your department name must consist of at least 3 characters'
                },
                'terms': 'You must agree to the service terms!',
                'useridcard': 'Please upload your college id card',
                'enroll': {
                    required: 'Please enter your enrollment no.',
                    minlength: 'Your enrollment must consist of at least 5 characters'
                }
            }
        });
    };

    return {
        init: function () {
            // Init Register Form Validation
            initValidationRegister();
        }
    };
}();

// Initialize when page loads
jQuery(function(){ BasePagesRegister.init(); });