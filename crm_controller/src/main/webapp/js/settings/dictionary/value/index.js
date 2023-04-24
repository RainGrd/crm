$(function () {
    init(1, 10);
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
                alert("请选择要编辑的数据字典值")
                return false;
            }
            if ($tableData.size() > 1) {
                alert("一次只能编辑一个!");
                return false;
            }
            //跳转页面
            window.location.href = 'settings/dictionary/value/toEditValue.do?id=' + $tableData[0].value;
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

    $("#saveDicValueBtn").on('click', function () {
        //获取数据
        let data = {
            typeCode: $("#create-dicTypeCode").val(),
            value: $('#create-dicValue').val(),
            text: $('#create-text').val(),
            orderNo: $('#create-orderNo').val(),
        }
        if (notNull(data.typeCode)) {
            alert("编码不能为空")
            return;
        }
        if (notNull(data.value)) {
            alert("字典值不能为空")
            return;
        }
        if (notNull(data.text)) {
            alert("文本不能为空")
            return;
        }
        if (notNull(data.orderNo)) {
            alert("排序号不能为空")
            return;
        }
        $.ajax({
            url: "settings/dictionary/value/saveDivValue.do",
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(data),
            type: "post",
            success: function (result) {
                if (result.code === "1") {
                    //跳转页面
                    window.location.href = 'settings/dictionary/value/toValueIndex.do';
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

    $("#editDicValueBtn").on("click", function () {
        // 获取数据
        //获取数据
        let data = {
            typeCode: $("#edit-dicTypeCode").val(),
            value: $('#edit-dicValue').val(),
            text: $('#edit-text').val(),
            orderNo: $('#edit-orderNo').val(),
        }
        if (notNull(data.typeCode)) {
            alert("编码不能为空")
            return;
        }
        if (notNull(data.value)) {
            alert("字典值不能为空")
            return;
        }
        if (notNull(data.text)) {
            alert("文本不能为空")
            return;
        }
        if (notNull(data.orderNo)) {
            alert("排序号不能为空")
            return;
        }
        //发送请求
        $.ajax({
            url: "settings/dictionary/value/editDicValue.do",
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
        /*询问是否删除*/
        if (window.confirm("确定删除吗?")) {
            let ids = [];
            /*获取id值*/
            $.each($tableData, function (i, obj) {
                ids.push(this.value);
            });
            console.log(ids)
            //发送请求
            $.ajax({
                url: "settings/dictionary/value/removeDicValueById.do",
                type: "post",
                data: {
                    ids: ids
                },
                dataType: 'json',
                success: (result) => {
                    if (result.code === '1') {
                        //刷新列表
                        init(1, $("#page").bs_pagination('getOption', 'rowsPerPage'));
                    } else if (result.code === '0') {
                        alert(result.message)
                    }
                }, error: (error) => {
                    console.log('出错了')
                }
            })
        }
    })

    //刷新列表

})

/**
 * 初始化
 * @param beginNo
 * @param pageSize
 */
function init(beginNo, pageSize) {
    $.ajax({
        url: "settings/dictionary/type/queryDicValueList.do?beginNo=" + beginNo + "&pageSize=" + pageSize,
        type: "get",
        dataType: 'json',
        success: (result) => {
            if (result.code === '1') {
                let data = result.data.list;
                //清空列表
                let htmlStr = ''
                let active = '';
                for (let i = 0; i < data.length; i++) {
                    active = i % 2 !== 0 ? 'active' : '';
                    //拼接数据
                    htmlStr += '<tr class="' + active + '">\n' +
                        '            <td><input type="checkbox" value="' + data[i].id + '"/></td>\n' +
                        '            <td>' + Number(i + 1) + '</td>\n' +
                        '            <td>' + data[i].value + '</td>\n' +
                        '            <td>' + data[i].text + '</td>\n' +
                        '            <td>' + data[i].orderNo + '</td>\n' +
                        '            <td>' + data[i].typeCode + '</td>\n' +
                        '        </tr>'
                }
                $("#tableData").html(htmlStr);
                if ($("#page").length > 0) {
                    /**
                     * 分页条件
                     */
                    /*计算总页数 总条数/每页显示条数*/
                    let number = Math.ceil(result.data.total / pageSize);
                    $('#page').bs_pagination({
                        currentPage: beginNo,//当前页号,相当于pageNo
                        rowsPerPage: pageSize,//每页显示条数,相当于pageSize
                        totalRows: data.total,//总条数
                        totalPages: number,  //总页数,必填参数.
                        visiblePageLinks: 5,//最多可以显示的卡片数
                        showGoToPage: true,//是否显示"跳转到"部分,默认true--显示
                        showRowsPerPage: true,//是否显示"每页显示条数"部分。默认true--显示
                        showRowsInfo: true,//是否显示记录的信息，默认true--显示
                        itemTexts: function (type, page, current) {//文字翻译可有可无
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "尾页";
                                case "page":
                                    return page;
                            }
                        },
                        //用户每次切换页号，都自动触发本函数;
                        //每次返回切换页号之后的pageNo和pageSize
                        onChangePage: function (event, pageObj) { // returns page_num and rows_per_page after a link has clicked
                            //js代码
                            // console.log(pageObj.currentPage);
                            // console.log(pageObj.rowsPerPage);
                            init(pageObj.currentPage, pageObj.rowsPerPage);
                        }
                    });
                }
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

