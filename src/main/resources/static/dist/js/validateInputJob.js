
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

    jQuery.validator.addMethod("regexEmail", function(value, element) {
        // allow any non-whitespace characters as the host part
        return this.optional( element ) || /^[A-z-0-9]+@toprate.io/.test( value );
    },"Không nên chứa kí tự đặc biệt");

    jQuery.validator.addMethod("regexUsername", function(value, element) {
        // allow any non-whitespace characters as the host part
        return this.optional( element ) || /^(?![_ -])(?:(?![_ -]{2})[\w -]){5,16}$/.test( value );
    },"Không nên chứa kí tự đặc biệt");

    // Validate chỉ bắt theo tên của trường th:field
    $("#add-job").validate({
        rules: {
            jobTitle: {
                required: true,
                regexUsername: true,
            },
            quantity: {
                required: true,
            },
            dateStart: {
                required: true,
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
            level: {
                required:  true,
            },
            positionList: {
                required: true
            }
        },
        messages: {
            jobTitle: {
                required: "Tiêu đề không được để trống !",
                alphanumeric: "Tiêu đề không chứa kí tự đặc biệt",
                regexUsername: "Tiêu đề không chứa các ký tự đặc biệt"
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
            },
            positionList: {
                required: "Hãy chọn vị trí tuyển dụng"
            }
        }
    })

    // Validate chỉ bắt theo tên của trường th:field
    $("#add-user").validate({
        rules: {
            name: {
                required: true,
                minlength: 5,
                regexUsername: true,
            },
            birthDay: {
                required: true,
            },
            gmail: {
                required: true,
                email: true,
                regexEmail: true
            },
            phone: {
                required: true,
                number: true,
                minlength: 10,
            }
        },
        messages: {
            name: {
                required: "Hãy nhập tên người dùng",
                minlength: "Tên phải chứa ít nhất 5 ký tự",
                regexUsername: "Tên không chứa các ký tự đặc biệt"
            },
            birthDay: {
                required: "Hãy nhập ngày sinh"
            },
            gmail: {
                required: "Hãy nhập Email",
                email: "Hãy nhập đúng định dạng email",
                regexEmail: "Email phải có dạng abc@toprate.io"
            },
            phone: {
                required: "Hãy nhập số điện thoại",
                number: "SĐT chỉ chứa các ký tự số",
                minlength: "Số điện thoại vừa nhập không hợp lệ"
            }
        }
    })

});