$(function () {
    $("#isCreateTransaction").click(function () {
        if (this.checked) {
            $("#create-transaction2").show(200);
        } else {
            $("#create-transaction2").hide(200);
        }
    });
    /**
     * 搜索市场活动源按钮点击事件
     */
    $('#searchActivityBtn').on('click', function () {
        console.log(1)
        /*弹出搜索市场活动模态窗口说*/
        $('#searchActivityModal').modal('show');
        /*调用查询已关联的市场活动函数*/
        queryAssociatedActivityByActivityNameAndClueId();
    })

    /**
     * 搜索框市场活动框键盘弹起事件
     */
    $('#searchActivityName').on('keyup', function () {
        /*调用查询已关联的市场活动函数*/
        queryAssociatedActivityByActivityNameAndClueId();
    })

    function queryAssociatedActivityByActivityNameAndClueId() {
        /*收集参数*/
        let name = $('#searchActivityName').val();
        let id = $('#title').attr('clueId');
        console.log(id)
        /*发送请求*/
        $.ajax({
            url: 'workbench/clue/queryAssociatedActivityByActivityNameAndClueId.do',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                activityName: name, clueId: id
            }),
            success: function (result) {
                console.log(result);
                /*遍历数据*/
                let str = '';
                $.each(result, function (index, obj) {
                    str += '<tr>\n' + '<td><input type="radio" name="activity" activityName="' + obj.name + '" value="' + obj.id + '"/></td>\n' + '<td>' + obj.name + '</td>\n' + '<td>' + obj.startDate + '</td>\n' + '<td>' + obj.endDate + '</td>\n' + '<td>' + obj.owner + '</td></tr>'
                });
                $('#tableData').html(str);
            },
            error: function (error) {
                console.log("出错了！")
            }
        });
    }

    /**
     * 给选择已关联的市场活动单选框绑定点击事件
     */
    $('#tableData').on('click', 'input[type=radio]', function () {
        /*获取市场id的name*/
        let id = this.value;
        let activityName = $(this).attr('activityName');
        /*把市场活动的id写入隐藏域中*/
        $('#activityId').val(id);
        /*把name写到输入框中*/
        $('#activityName').val(activityName);
        //关闭搜索市场活动的模态窗口
        $("#searchActivityModal").modal("hide");
    })
    /**
     * 给交易按钮添加单击事件
     */
    $('#saveConvertBtn').on("click", function () {
        /*收集参数*/
        let clueId = $('#title').attr('clueId');
        let amountOfMoney = $("#amountOfMoney").val().trim();
        let tradeName = $("#tradeName").val().trim();
        let expectedClosingDate = $("#expectedClosingDate").val();
        let stage = $("#stage").val();
        let activityId = $('#activityId').val();
        console.log(activityId)
        let isCreateTran = $('#isCreateTransaction').prop("checked");
        let activityName = $("#activityName").val();
        /*表单验证*/
        var moneyReg = /^(([1-9]\d*)|0)$/;
        if (!moneyReg.test(amountOfMoney)) {
            alert("金钱只能为非负整数");
            return false;
        }
        /*发送请求*/
        $.ajax({
            url: 'workbench/clue/convertClue.do',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                clueId: clueId,
                amountOfMoney: amountOfMoney,
                tradeName: tradeName,
                expectedClosingDate: expectedClosingDate,
                stage: stage,
                activityId: activityId,
                isCreateTran: isCreateTran,
            }),
            success: function (result) {
                console.log(result);
                if (result.code === '1') {
                    /*跳转到线索主页面*/
                    window.location.href = 'workbench/clue/index.do';
                } else {
                    alert(result.message);
                }
            },
            error: function (error) {
                console.log('出错了');
            }
        })
    })
    /**
     * 取消转换按钮点击事件
     */
    $('#cancelBtn').on('click', function () {
        console.log(1);
        /*退回线索明细页面*/
        window.location.href = 'workbench/clue/detailClue.do?id=' + $('#title').attr('clueId');
    })
});