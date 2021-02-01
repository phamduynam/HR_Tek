
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

    jQuery.validator.addMethod("specialCharacters", function(value, element) {
        // allow any non-whitespace characters as the host part
        return this.optional( element ) || /^[A-z-0-9]+@toprate.io/.test( value );
    },"Không nên chứa kí tự đặc biệt");
    


    // Validate chỉ bắt theo tên của trường th:field
    $("#add-job").validate({
        rules: {
            jobTitle: {
                // required: {
                //     depends:function(){
                //         $(this).val($.trim($(this).val()));
                //         return true;
                //     }
                // },
                required: true,
                // specialCharacters: true,
            },
            quantity: {
                required: true,
            },
            dateStart: {
                // required: {
                //     depends:function(){
                //         $(this).val($.trim($(this).val()));
                //         return true;
                //     }
                // },
                required: true,
                // specialCharacters: true,
            },
            dateEnd: {
                required: true
            },
            startSalary: {
                required: true
            },
            endSalary: {
                required: true
            },
            levels: {
                required:  true,
            }
        },
        messages: {
            jobTitle: {
                required: "Tiêu đề không được để trống !",
                alphanumeric: "Tiêu đề không chứa kí tự đặc biệt"
            },
            quantity: {
                required: "Hãy nhập số lượng"
            },
            dateStart: {
                required: "Hãy nhập ngày bắt đầu"
            },
            dateEnd: {
                required: "Hãy nhập ngày kết thúc"
            },
            startSalary: {
                required: "Hãy nhập lương khởi điểm"
            },
            endSalary: {
                required: "Hãy nhập lương kết thúc"
            },
            level: {
                required: "Hãy chọn level"
            }
        }
    })

    // Validate chỉ bắt theo tên của trường th:field
    $("#add-user").validate({
        rules: {
            name: {
                // required: {
                //     depends:function(){
                //         $(this).val($.trim($(this).val()));
                //         return true;
                //     }
                // },
                required: true,
                minlength: 5,
                // specialCharacters: true,
            },
            birthDay: {
                required: true,
            },
            gmail: {
                // required: {
                //     depends:function(){
                //         $(this).val($.trim($(this).val()));
                //         return true;
                //     }
                // },
                required: true,
                email: true,
                specialCharacters: true
            },
            phone: {
                required: true,
                number: true,
                minlength: 10,
            }
        },
        messages: {
            name: {
                required: "Tên không được để trống",
                minlength: "Tên phải chứa ít nhất 5 ký tự",
                alphanumeric: "Tên không chứa các ký tự đặc biệt"
            },
            birthDay: {
                required: "Hãy nhập ngày sinh"
            },
            gmail: {
                required: "Hãy nhập Email",
                email: "Hãy nhập đúng định dạng email",
                specialCharacters: "Email phải có dạng abc@toprate.io"
            },
            phone: {
                required: "Hãy nhập số điện thoại",
                number: "Chỉ chứa các ký tự số",
                minlength: "Số máy quý khách vừa nhập không hợp lệ"
            }
        }
    })

});