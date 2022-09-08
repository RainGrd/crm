//默认情况下取消和保存按钮是隐藏的
var cancelAndSaveBtnDefault = true;

$(function () {
    $("#remark").focus(function () {
        if (cancelAndSaveBtnDefault) {
            //设置remarkDiv的高度为130px
            $("#remarkDiv").css("height", "130px");
            //显示
            $("#cancelAndSaveBtn").show("2000");
            cancelAndSaveBtnDefault = false;
        }
    });

    $("#cancelBtn").on("click", function () {
        //显示
        $("#cancelAndSaveBtn").hide();
        //设置remarkDiv的高度为130px
        $("#remarkDiv").css("height", "90px");
        cancelAndSaveBtnDefault = true;
    });
    $("#remarkDivList").on('mouseover', ".remarkDiv", function () {
        $(this).children("div").children("div").show();
    });
    $("#remarkDivList").on('mouseout', ".remarkDiv", function () {
        $(this).children("div").children("div").hide();
    });
    /*$(".remarkDiv").mouseover(function () {
        $(this).children("div").children("div").show();
    });*/

    /*$(".remarkDiv").mouseout(function () {
        $(this).children("div").children("div").hide();
    });*/
    $("#remarkDivList").on('mouseover', ".myHref", function () {
        $(this).children("span").css("color", "red");
    });
    $("#remarkDivList").on('mouseout', ".myHref", function () {
        $(this).children("span").css("color", "#E6E6E6");
    });
    /*    $(".myHref").mouseover(function () {
            $(this).children("span").css("color", "red");
        });

        $(".myHref").mouseout(function () {
            $(this).children("span").css("color", "#E6E6E6");
        });*/
    /*查询session对象的用户*/
    let sessionUser = querySessionUser();
    console.log(sessionUser);
    $('#saveActivityRemarkBtn').on('click', function () {
        /*判断内容是否为空*/
        let noteContent = $('#remark').val();
        if (notEmpty(noteContent)) {
            alert("添加备注不能为空！");
            return false;
        }
        let activityId = $('#activityId').text();
        /*收集参数*/
        $.ajax({
            url: "workbench/activity/saveCreateActivityRemark.do",
            data: {
                noteContent: noteContent,
                activityId: activityId,
            },
            dataType: 'json',
            type: 'post',
            success: function (result) {
                if (result.code === 1) {
                    /*清空输入框*/
                    $('#remark').val('');
                    let htmlStr = '';
                    htmlStr += '<div class="remarkDiv" style="height: 60px;">' +
                        '<img title="' + sessionUser.name + '" src="image/user-thumbnail.png" style="width: 30px; height:30px;">' +
                        '<div style="position: relative; top: -40px; left: 40px;">' +
                        '<h5>' + result.data.noteContent + '</h5>' +
                        '<font color="gray">市场活动</font> <font color="gray">-</font> <b>' + $('#activityName').text() + '</b> <small style="color: gray;">' + result.data.createTime +
                        '由' + sessionUser.name + '创建</small>' +
                        '<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">' +
                        '<a class="myHref" remarkId="' + result.data.id + '" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>  &nbsp;&nbsp;&nbsp;&nbsp;' +
                        '<a class="myHref" remarkId="' + result.data.id + '" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>' +
                        '</div>' +
                        '</div>' +
                        '</div>'
                    $('#remarkDiv').before(htmlStr)
                } else {
                    alert(result.message);
                }

            }, error: function (error) {
                console.log('出错了')
                console.log(error);
            }
        })
    });

    /**
     * 查询存放在session的用户的对象
     */
    function querySessionUser() {
        let user = {
            id: '',
            name: '',
        };
        $.ajax({
            url: 'settings/qx/user/getSessionUser.do',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                user.name = result.name;
            }, error: function (error) {
                console.log('出错了！');
                console.log(error);
            }
        });
        return user;
    }
});

/**
 * 非空函数
 */
function notEmpty(data) {
    if (data === null || data === '' || data === undefined) {
        return true;
    }
}