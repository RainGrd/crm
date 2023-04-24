$(function () {

    $("#saveDicType").on('click', function () {
        //获取数据
        let data = {
            code: $('#create-code').val(),
            name: $('#create-name').val(),
            description: $('#create-describe').val(),
        }
        if (notNull(data.code)) {
            alert("编码不能为空")
            return;
        }
        if (notNull(data.name)) {
            alert("名称不能为空")
            return;
        }
        if (notNull(data.description)) {
            alert("描述不能为空")
            return;
        }
        $.ajax({
            url: "settings/dictionary/type/saveDicType.do",
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(data),
            type: "post",
            success: function (result) {
                if (result.code === "1") {
                    //跳转页面
                    window.location.href = 'settings/dictionary/index.do';
                } else {
                    //弹出提示
                    alert(result.message)
                }

            }, error: function (error) {
                console.log('出错了')
                // console.log(error)
            }
        })
    })

    /**
     * 点击全选按钮，将所有按钮选中！
     */
    $('#checkAll').on('click', function () {
        /*将所有按钮选中*/
        $('#tableData input[type=checkbox]').prop('checked', this.checked);
    });

    $('#editBtn').on('click', function () {
            /*获取CheckBox*/
            let $tableData = $('#tableData input[type=checkbox]:checked');

            /*判断是否选择了*/
            if ($tableData.size() === 0) {
                alert("请选择要编辑的数据字典类型")
                return false;
            }
            if ($tableData.size() > 1) {
                alert("一次只能编辑一个!");
                return false;
            }
            //跳转页面
            window.location.href = 'settings/dictionary/type/toEdit.do?code=' + $tableData[0].value;
        }
    )

    /**
     * 全选按钮根据选择的按钮数量来进行判断
     */
    $('#tableData').on('click', 'input[type=checkbox]', function () {
        if ($('#tableData input[type=checkbox]').size() === $('#tableData input[type=checkbox]:checked').size()) {
            $('#checkAll').prop('checked', true);
        } else {
            $('#checkAll').prop('checked', false);
        }
    });

    $("#editDicType").on("click", function () {
        // 获取数据
        let data = {
            code: $('#edit-code').val(),
            name: $('#edit-name').val(),
            description: $('#edit-describe').val(),
        }
        //判断是否为空
        if (notNull(data.code)) {
            alert("编码不能为空")
            return;
        }
        if (notNull(data.name)) {
            alert("名称不能为空")
            return;
        }
        if (notNull(data.description)) {
            alert("描述不能为空")
            return;
        }
        //发送请求
        $.ajax({
            url: "settings/dictionary/type/editDicType.do",
            contentType: "application/json",
            type: "post",
            data: JSON.stringify(data),
            dataType: 'json',
            success: (result) => {
                if (result.code === "1") {
                    //跳转页面
                    window.location.href = 'settings/dictionary/index.do';
                } else {
                    //弹出提示
                    alert(result.message)
                }
            }, error: (error) => {
                console.log('出错了')
            }
        })

    })

    $("#deleteBtn").on('click', function () {
        /*获取CheckBox*/
        let $tableData = $('#tableData input[type=checkbox]:checked');
        /*判断是否选择了*/
        if ($tableData.size() === 0) {
            alert("请选择要删除的数据字典类型")
            return false;
        }
        let code = '';
        for (let i = 0; i < $tableData.length; i++) {
            code += $tableData[i].value + ','
        }
        //发送请求
        $.ajax({
            url: "settings/dictionary/type/deleteDicType.do?code=" + code,
            type: "post",
            success: (result) => {
                if (result.code === '1') {
                    //刷新列表
                    init();
                } else if (result.code === '0') {
                    alert(result.message)
                }
            }, error: (error) => {
                init();
            }
        })
    })

    //刷新列表

})

function init() {
    $.ajax({
        url: "settings/dictionary/type/deleteDicType.do",
        type: "get",
        dataType: 'json',
        success: (result) => {
            if (result.code === '1') {
                let data = result.data;
                //清空列表
                $("#tableData").html('');
                let htmlStr = ''
                for (let i = 0; i < data.length; i++) {
                    //拼接数据
                    htmlStr += '<tr class="active">\n' +
                        '                <td><input type="checkbox" value="' + data[i].code + '"/></td>\n' +
                        '                <td>' + i + 1 + '</td>\n' +
                        '                <td>' + data[i].code + '</td>\n' +
                        '                <td>' + data[i].name + '</td>\n' +
                        '                <td>' + data[i].description + '</td>\n' +
                        '            </tr>'
                }
                $("#tableData").append(htmlStr);
            }
        }, error: (error) => {

        }
    })
}


/**
 * 非空函数
 * @param value
 * @returns {boolean}
 */
function notNull(value) {
    if (value === null || value === '' || value === undefined) {
        return true;
    }
}