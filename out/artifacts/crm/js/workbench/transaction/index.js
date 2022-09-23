$(function () {


});
/**
 * 分页查询线索函数
 * @param beginNo 起始页
 * @param pageSize 页码
 */
function queryClueByConditionForPage(beginNo, pageSize) {
    /*分页条件*/
    let map = {
        owner:$('#owner').val(),
        name:$('#name').val(),
        customerName:$('#customerName').val(),
        stage:$('#stage').val(),
        transactionType:$('#transactionType').val(),
        source:$('#source').val(),
        contactName:$('#contactName').val(),
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