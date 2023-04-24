$(function () {
    init(1, 10);
    $('#queryBtn').on('click', function () {
        init(1, $("#page").bs_pagination('getOption', 'rowsPerPage'))
    })

    $("#saveBtn").on('click', function () {
        //获取数据
        let data = {
            owner: $('#create-customerOwner').val(),
            name: $('#create-customerName').val(),
            phone: $('#create-phone').val(),
            website: $('#create-website').val(),
            description: $('#create-describe').val(),
            contactSummary: $('#create-contactSummary').val(),
            nextContactTime: $('#create-nextContactTime').val(),
            address: $('#create-address1').val(),
        }
        console.log(data)
        //发送请求
        $.ajax({
            url: "workbench/customer/saveCustomer.do",
            data: JSON.stringify(data),
            contentType: 'application/json',
            type: "post",
            success: (result) => {
                console.log(result)
                if (result.code === '1') {
                    //重新初始化
                    init(1, $("#page").bs_pagination('getOption', 'rowsPerPage'))
                } else {
                    //弹出提示
                    alert(result.message)
                }
            }, error: (error) => {

            }
        })
    })
    //定制字段
    $("#definedColumns > li").click(function (e) {
        //防止下拉菜单消失
        e.stopPropagation();
    });
    /**
     * 配置时间选项
     */
    $(".myDate").datetimepicker({
        language: 'zh-CN',   //语言
        format: 'yyyy-mm-dd',    //格式
        minView: 'month',    //可以选择的最小视图
        initialDate: new Date(), //初始化显示日期
        autoclose: true,//设置完选择日期或者时间之后，日历是否自动关闭
        todayBtn: true,//设置是否显示"今天按钮"，默认是false
        clearBtn: true,//设置是否显示"清空"按钮，默认是false
    })
})

function init(beginNo, pageSize) {
    let data = {
        owner: $("#owner").val(),
        phone: $("#phone").val(),
        name: $("#name").val(),
        website: $("#website").val(),
        beginNo,
        pageSize
    }
    console.log(data)
    $.ajax({
        url: "workbench/customer/queryCustomerByConditionForPage.do",
        contentType: "application/json",
        data: JSON.stringify(data),
        type: "post",
        dataType: 'json',
        success: (result) => {
            if (result.code === '1') {
                let data = result.data.list;
                //清空列表
                let htmlStr = ''
                let active = '';
                for (let i = 0; i < data.length; i++) {
                    active = i % 2 === 0 ? 'active' : '';
                    //拼接数据
                    htmlStr += '<tr class="' + active + '">\n' +
                        '            <td><input type="checkbox" value="' + data[i].id + '"/></td>\n' +
                        '            <td>' + data[i].owner + '</td>\n' +
                        '            <td>' + data[i].name + '</td>\n' +
                        '            <td>' + data[i].phone + '</td>\n' +
                        '            <td>' + data[i].website + '</td>\n' +
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