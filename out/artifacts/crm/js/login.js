$(function () {
    /*给整个浏览器窗口添加键盘添加事件*/
    $(window).on("keydown", function (event) {
        /*如果是回车键，则提交登录事件*/
        if (event.keyCode === 13) {
            /*点击登录按钮*/
            $("#loginBtn").click();
        }
    })
    /*给登录按钮添加点击事件*/
    $("#loginBtn").on("click", function () {
        /*获取参数*/
        let loginAct = $("#loginAct").val().trim();
        let loginPwd = $("#loginPwd").val().trim();
        let idRemPwd = $('#idRemPwd').prop('checked');
        /*表单验证*/
        if (notEmpty(loginAct)) {
            $("#loginAct").attr("placeholder", "用户名不能为空");
            /*将边框变红*/
            $("#loginAct").css("border-color", "red");
            return false;
        }
        if (notEmpty(loginPwd)) {
            $("#loginPwd").attr("placeholder", "密码不能为空");
            /*将边框变红*/
            $("#loginPwd").css("border-color", "red");
            return false;
        }
        /*发送Ajax请求*/
        $.ajax({
            url: 'settings/qx/user/login.do',
            data: {
                loginAct: loginAct,
                loginPwd: loginPwd,
                idRemPwd: idRemPwd
            },
            type: 'post',
            dataType: "json",
            success: function (result) {
                console.log(result)
                if (result.code === 1) {
                    /*跳转主页面*/
                    window.location.href = 'workbench/index.do';
                } else {
                    $('#msg').text(result.message);
                }
            }
            , error: function (error) {
                console.log('出错了！')
            }, beforeSend: function () {
                /*显示*/
                $("#msg").text("正在验证中...");
                return true;
            }
        })
    })

})

/**
 * 非空函数
 */
function notEmpty(data) {
    if (data === null || data === '' || data === undefined) {
        return true;
    }
}