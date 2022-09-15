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
                /*遍历result*/
                let str = '';
                $.each(result, function (index, obj) {
                    str += ' <tr>\n' +
                        '<td><input type="checkbox" value="' + obj.id + '"/></td>\n' +
                        '<td>' + obj.name + '</td>\n' +
                        '<td>' + obj.startDate + '</td>\n' +
                        '<td>' + obj.endDate + '</td>\n' +
                        '<td>' + obj.owner + '</td>\n' +
                        '</tr>'
                })
                $('#tBody').html(str);
            }, error: function (error) {
                console.log('出错了！');
                // console.log(error)
            }
        });
    });
    /**
     * 关联市场活动全选按钮点击事件
     */
    $('#checkboxAll').on('click', function () {
        /*判断全选是否被选中*/
        $('#tBody input[type=checkbox]').prop('checked', this.checked);
    })
    $('#tBody').on('click', 'input[type=checkbox]', function () {
        /*全选*/
        if ($('#tBody input[type=checkbox]').size() === $('#tBody input[type=checkbox]:checked').size()) {
            $('#checkboxAll').prop("checked", true);
        } else {
            $('#checkboxAll').prop("checked", false);
        }
    });
    /**
     * 关联市场活动保存按钮点击事件
     */
    $('#saveClueActivityBtn').on('click', function () {
        /*收集参数*/
        let checkboxVal = $('#tBody input[type=checkbox]:checked');
        console.log(checkboxVal);
        if (checkboxVal.size() <= 0) {
            alert("请选择要关联的市场活动！");
            return false;
        }
        let activityIdList = [];
        $.each(checkboxVal, function (index, obj) {
            console.log(this.value);
            activityIdList.push(this.value);
        })
        console.log(activityIdList);
        let id = $('#clueId').text();
        /*发送请求*/
        $.ajax({
            url: 'workbench/clue/saveBund.do',
            type: 'post',
            data: {
                activityIds: activityIdList,
                clueId: id
            },
            dataType: 'json',
            success: function (result) {
                console.log(result);
                let htmlStr = ''
                $.each(result.data, function (index, obj) {
                    htmlStr += '<tr>\n' +
                        '<td>' + obj.name + '</td>\n' +
                        '<td>' + obj.startDate + '</td>\n' +
                        '<td>' + obj.endDate + '</td>\n' +
                        '<td>' + obj.owner + '</td>\n' +
                        '<td><a href="javascript:void(0);"  style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td></tr>';
                });
                $('#activityList').html(htmlStr);
            }, error: function (error) {
                console.log('出错了')
            }
        })
    })
})