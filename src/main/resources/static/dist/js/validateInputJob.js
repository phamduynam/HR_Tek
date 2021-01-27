
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
});