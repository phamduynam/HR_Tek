
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
    // $("#add-job").validate({
    //     rules: {
    //         jobTitle: {
    //             // required: {
    //             //     depends:function(){
    //             //         $(this).val($.trim($(this).val()));
    //             //         return true;
    //             //     }
    //             // },
    //             required: true,
    //             // specialCharacters: true,
    //         },
    //         quantity: {
    //             required: true,
    //         },
    //         dateStart: {
    //             // required: {
    //             //     depends:function(){
    //             //         $(this).val($.trim($(this).val()));
    //             //         return true;
    //             //     }
    //             // },
    //             required: true,
    //             // specialCharacters: true,
    //         },
    //         dateEnd: {
    //             required: true
    //         },
    //         startSalary: {
    //             required: true
    //         },
    //         endSalary: {
    //             required: true
    //         },
    //         levels: {
    //             required:  true,
    //         }
    //     },
    //     messages: {
    //         jobTitle: {
    //             required: "Tiêu đề không được để trống !",
    //             alphanumeric: "Tiêu đề không chứa kí tự đặc biệt"
    //         },
    //         quantity: {
    //             required: "Hãy nhập số lượng"
    //         },
    //         dateStart: {
    //             required: "Hãy nhập ngày bắt đầu"
    //         },
    //         dateEnd: {
    //             required: "Hãy nhập ngày kết thúc"
    //         },
    //         startSalary: {
    //             required: "Hãy nhập lương khởi điểm"
    //         },
    //         endSalary: {
    //             required: "Hãy nhập lương kết thúc"
    //         },
    //         level: {
    //             required: "Hãy chọn level"
    //         }
    //     }
    // })

    // Validate chỉ bắt theo tên của trường th:field
    // $("#add-user").validate({
    //     rules: {
    //         name: {
    //             // required: {
    //             //     depends:function(){
    //             //         $(this).val($.trim($(this).val()));
    //             //         return true;
    //             //     }
    //             // },
    //             required: true,
    //             // specialCharacters: true,
    //         },
    //         birthDay: {
    //             required: true,
    //         },
    //         gmail: {
    //             // required: {
    //             //     depends:function(){
    //             //         $(this).val($.trim($(this).val()));
    //             //         return true;
    //             //     }
    //             // },
    //             required: true,
    //             pattern: "[a-z0-9]+@toprate.io",
    //         },
    //         phone: {
    //             required: true
    //         }
    //     },
    //     messages: {
    //         name: {
    //             required: "Tên không được để trống",
    //             alphanumeric: "Tên không chứa các ký tự đặc biệt"
    //         },
    //         birthDay: {
    //             required: "Hãy nhập ngày sinh"
    //         },
    //         gmail: {
    //             required: "Hãy nhập Email",
    //             pattern: "Email không đúng định dạng"
    //         },
    //         phone: {
    //             required: "Hãy nhập số điện thoại"
    //         }
    //     }
    // })

});