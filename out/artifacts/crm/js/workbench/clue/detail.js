$(function () {
    /**
     * 关联市场活动按钮点击事件
     */
    $('#bundActivityBtn').on('click', function () {
        console.log(1);
        /*初始化工作*/
        //清空搜索框
        $('#searchActivityTxt').val('');
        //清空市场列表
        $("#tBody").html("");
        /*弹出市场活动的模态窗口*/
        $('#bundModal').modal("show");
    });
    /**
     * 给市场活动添加键盘弹起事件
     */
    $('#searchActivityTxt').on('keyup', function () {
        let activityName = this.value;
        let id = $('#clueId').text();
        /*发送请求，查询数据*/
        $.ajax({
            url: 'workbench/clue/queryActivityByActivityNameAndClueId.do',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                activityName: activityName,
                clueId: id,
            }),
            success: function (result) {
                console.log(result);
                /*遍历result*/
                let str = '';
                $.each(result, function (index, obj) {
                    str = '<tr>\n' +
                        '<td>' + obj.name + '</td>\n' +
                        '<td>' + obj.startDate + '</td>\n' +
                        '<td>' + obj.endDate + '</td>\n' +
                        '<td>' + obj.owner + '</td>\n' +
                        '<td><a href="javascript:void(0);" style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td></tr>'
                })
                
            }, error: function (error) {
                console.log('出错了！');
                // console.log(error)
            }
        })
    });
})