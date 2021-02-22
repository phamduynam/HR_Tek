
$(function () {

    jQuery.validator.setDefaults({
        errorClass: 'invalid-feedback',
        highlight: function(element){
            $(element)
                .closest('.form-control')
                .addClass('is-invalid')
        },
        unhighlight: function(element){
            $(element)
                .closest('.form-control')
                .removeClass('is-invalid')
        }
    });

    jQuery.validator.addMethod("EmailToprate", function(value, element) {
        // allow any non-whitespace characters as the host part
        return this.optional( element ) || /[a-z0-9]+@toprate.io/.test( value );
    },"Email phải có @toprate.io");

    jQuery.validator.addMethod("Phone", function(value, element) {
        // allow any non-whitespace characters as the host part
        return this.optional( element ) || /[0]{1}[0-9]{9}/.test( value );
    },"Số điện thoại phải có 10 số");

    jQuery.validator.addMethod("specialCharacters", function(value, element) {
            // allow any non-whitespace characters as the host part
            return this.optional( element ) || /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@(?:\S{1,63})$/.test( value );
        },"Không nên chứa kí tự đặc biệt");

        // upload file check null
        $("#formSubmitFile").validate({
            rules: {
                multipartFile: {
                    required: true,
                    extension: "xlsx|csv"
                },
            },
            messages: {
                multipartFile: {
                    required: "Bạn chưa chọn file đấy nhé",
                    extension: "File upload phải là .xlsx, .csv"
                }
            }
        });

        // Validate chỉ bắt theo tên của trường th:field
        $("#submit-form").validate({
        rules: {
            candidateName: {
                // required: {
                //     depends:function(){
                //         $(this).val($.trim($(this).val()));
                //         return true;
                //     }
                // },
                required: true,
                // specialCharacters: true,
            },
            birthDay: {
                required: true,
            },
            address: {
                // required: {
                //     depends:function(){
                //         $(this).val($.trim($(this).val()));
                //         return true;
                //     }
                // },
                required: true,
                // specialCharacters: true,
            },
            sex: {
                required: true,
            },
            email1: {
                required: true,
                email: true
            },
            phone1: {
                required: {
                    depends:function(){
                        $(this).val($.trim($(this).val()));
                        return true;
                    }
                },
            },
            email2: {
                email: true
            },
            phone2: {
                email: true
            },
            yearExperience: {
                required:  true,
            },
            levels: {
                required:  true,
            }
        },
        messages: {
            candidateName: {
                required: "Tên không được để trống !",
                alphanumeric: "Tên không chứa kí tự đặc biệt"
            },
            birthDay: {
                required: "Hãy chọn ngày sinh"
            },
            address: {
                required: "Địa chỉ không được để trống !",
            },
            sex: {
                required: "Hãy chọn giới tính !"
            },

            phone1: {
                required: "Số điện thoại không được trống !",
            },
            phone2: {

            },
            email1: {
                required: "Email không được để trống !",
                email: "Email không đúng định dạng !"
            },
            email2: {
                email: "Email không đúng định dạng !"
            },
            yearExperience: {
                required:  "Hãy chọn kinh nghiệm !"
            },
            levels: {
                required:  "Hãy chọn level !"
            }
        }
    })

        // Validate Create user form
        $("#addUser").validate({
        rules: {
            name : {
                required: true,
            },
            birthDay: {
                required: true,
            },
            role : {
                required: true
            },
            gmail: {
                required: true,
                EmailToprate: true
            },
            phone: {
                required: true,
                Phone: true
            },
        },
        messages: {
            name : {
                required: "Bạn chưa nhập tên ",
            },
            birthDay: {
                required: "Hãy chọn ngày sinh",
            },
            role : {
                required: "Bạn chưa chọn quyền"
            },
            gmail: {
                required: "Hãy nhập email"
            },
            phone: {
                required: "Hãy nhập số điện thoại"
            },
        }
    });

});