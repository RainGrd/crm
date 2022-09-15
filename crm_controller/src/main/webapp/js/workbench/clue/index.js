$(function () {


    /**
     * 给创建按钮添加单机事件
     */
    $('#createClueBtn').on('click', function () {
        /*清空表单*/
        $('#createClueForm')[0].reset();
        /*弹出模态窗口*/
        $('#createClueModal').modal('show');
    })
    /**
     * 给表单添加日历组件
     */
    $('.myTime').datetimepicker({
        /*指定语言*/
        language: 'zh-CN',
        /*指定格式*/
        format: 'yyyy-mm-dd',
        /*可以选择最小视图*/
        minView: 'month',
        /*初始化显示日期*/
        initialDate: new Date(),
        /*设置完选择日期或者时间之后，日历是否自动关闭*/
        autoclose: true,
        /*设置是否显示"今天按钮"，默认是false*/
        todayBtn: true,
        /*设置是否显示"清空"按钮，默认是false*/
        clearBtn: true,
    })
    /**
     * 创建线索按钮点击事件
     */
    $('#saveCreateClueBtn').on('click', function () {

        /*收集参数*/
        let $clue = {
            fullName: $('#create-fullName').val(),
            appellation: $('#create-appellation').val(),
            owner: $('#create-owner').val(),
            company: $('#create-company').val(),
            job: $('#create-job').val(),
            email: $('#create-email').val(),
            phone: $('#create-phone').val(),
            website: $('#create-website').val(),
            mPhone: $('#create-mphone').val(),
            state: $('#create-state').val(),
            source: $('#create-source').val(),
            create_by: $('#create-create_by').val(),
            description: $('#create-description').val(),
            contactSummary: $('#create-contactSummary').val(),
            nextContactTime: $('#create-nextContactTime').val(),
            address: $('#create-address').val(),
        }
        /*表单验证*/
        //非空判断
        /*if(notEmpty($clue.fullName)){
            alert("");
        }*/
        //邮箱正则验证
        /*    var emailReg='^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$';
                if('^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$'.test($clue.email)){
                    console.log(1)
                    alert("邮箱格式错误");
                    return false;
                }*/
        //手机正则验证
        /*发送请求*/
        $.ajax({
            url: 'workbench/clue/saveCreateClue.do',
            type: 'post',
            dataType: 'json',
            data: $clue,
            success: function (result) {
                console.log(result);
                if (result.code === '1') {
                    /*关闭模态窗口*/
                    $('#createClueModal').modal('hide');
                    /*刷新列表*/
                    queryClueByConditionForPage(1, $('#page').bs_pagination("getOption", "rowsPerPage"))
                } else {
                    alert(result.message);
                    $('#createClueModal').modal('show');
                }
            }, error: function (error) {
                console.log('出错了');
            }, beforeSend: function () {

            }
        })
    });
    
    queryClueByConditionForPage(1, 5);

    /**
     * 查询按钮点击事件
     */
    $('#queryClueConditionForPageBtn').on('click', function () {
        queryClueByConditionForPage(1, $('#page').bs_pagination('getOption', 'rowsPerPage'));
    })
});

/**
 * 分页查询线索函数
 * @param beginNo 起始页
 * @param pageSize 页码
 */
function queryClueByConditionForPage(beginNo, pageSize) {
    /*分页条件*/
    let map = {
        fullName: $('#fullName').val(),
        company: $('#company').val(),
        phone: $('#phone').val(),
        source: $('#source').val(),
        owner: $('#owner').val(),
        mPhone: $('#mPhone').val(),
        state: $('#state').val(),
        beginNo: beginNo,
        pageSize: pageSize
    }
    /*非空判断*/
    if (notEmpty(pageSize)) {
        alert("分页为空");
        return false;
    }
    $.ajax({
        contentType: 'application/json;charset=UTF-8',
        url: 'workbench/clue/queryClueByConditionForPage.do',
        dataType: "json",
        type: 'post',
        data: JSON.stringify(map),
        success: function (result) {
            console.log(result);
            let data = result.list;
            /*遍历数据*/
            let tableData = '';
            $.each(data, function (index, obj) {
                tableData += ' <tr>\n' +
                    '<td><input type="checkbox" value="' + obj.id + '"/></td>\n' +
                    '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=' + '\'workbench/clue/detailClue.do?id=' + obj.id + '\'"> ' + obj.fullName + '</a></td>\n' +
                    '<td>' + obj.company + '</td>\n' +
                    '<td>' + obj.mPhone + '</td>\n' +
                    '<td>' + obj.phone + '</td>\n' +
                    '<td>' + obj.source + '</td>\n' +
                    '<td>' + obj.owner + '</td>\n' +
                    '<td>' + obj.state + '</td>\n' +
                    '</tr>'
            });
            $('#tableData').html(tableData);
            /**
             * 分页条件
             */
            /*计算总页数 总条数/每页显示条数*/
            let number = Math.ceil(result.total / pageSize);
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
                    queryClueByConditionForPage(pageObj.currentPage, pageObj.rowsPerPage);
                }
            });
        }, error: function (error) {
            console.log('出错了！')
            // console.log(error);
        }
    })
}

/**
 * 非空函数
 */
function notEmpty(data) {
    if (data === null || data === '' || data === undefined) {
        return true;
    }
}