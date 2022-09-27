<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--导入JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>
    <link href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
    <script type="text/javascript">
        $(function () {
            /*首次打开页面时，默认时第一页且查询的条数为10条*/
            queryActivityByConditionForPage(1, 10);
            /*给创建按钮添加单击事件*/
            $('#createActivity').on("click", function () {
                /*清空表单里存留的数据（重置表单）*/
                $('#createActivityForm').get(0).reset();
                /*弹出创建市场活动的模态窗口*/
                $('#createActivityModal').modal("show");
            });
            /*给保存按钮添加单击事件*/
            $('#saveCreateActivityBtn').on("click", function () {

                /*收集参数*/
                let owner = $('#create-marketActivityOwner').val();
                let name = $('#create-marketActivityName').val().trim();
                let startDate = $('#create-startDate').val();
                let endDate = $('#create-endDate').val();
                let cost = $('#create-cost').val().trim();
                let description = $('#create-description').val().trim();
                /*参数校验*/
                if (checkParameter(name, owner, startDate, endDate, cost) != null) {
                    alert(checkParameter(name, owner, startDate, endDate, cost))
                    return false
                }
                /*发送请求*/
                $.ajax({
                    url: 'workbench/activity/saveCreateActivity.do',
                    data: {
                        owner: owner,
                        name: name,
                        startDate: startDate,
                        endDate: endDate,
                        cost: cost,
                        description: description
                    },
                    // contentType:"application/json",
                    type: 'post',
                    dataType: 'json',
                    success: function (result) {
                        if (result.code === "1") {
                            /*关闭模态窗口*/
                            $('#createActivityModal').modal("hide");
                            /*刷新市场活动列，(保留)*/
                            queryActivityByConditionForPage(1, $('#page').bs_pagination('getOption', 'rowsPerPage'));
                        } else {
                            /*提示信息*/
                            alert(result.message);
                            /*模态窗口不关闭*/
                            $('#createActivityModal').modal("show");
                        }
                    }, error: function (error) {
                        console.log('出错了');
                    }

                })
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
            /*给查询按钮添加单击事件*/
            $('#queryActivityBtn').on('click', function () {
                /*每查询一次，就要重新回到第一页*/
                queryActivityByConditionForPage(1, $('#page').bs_pagination('getOption', 'rowsPerPage'));
            });
            /**
             * 点击全选按钮，将所有按钮选中！
             */
            $('#checkAll').on('click', function () {
                /*将所有按钮选中*/
                $('#tableData input[type=checkbox]').prop('checked', this.checked);
            });
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
            /**
             * 删除按钮点击事件
             */
            $('#deleteActivity').on('click', function () {
                /*获取CheckBox*/
                let $tableData = $('#tableData input[type=checkbox]:checked');
                /*判断是否选择了*/
                if ($tableData.size() === 0) {
                    alert("请选择要删除的市场活动！")
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
                    /*发送Ajax请求*/
                    $.ajax({
                        url: 'workbench/activity/deleteActivityByIds.do',
                        type: 'post',
                        data: {
                            ids: ids
                        },
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            if (data.code === "1") {
                                /*重新刷新页面*/
                                queryActivityByConditionForPage(1, $('#page').bs_pagination('getOption', 'rowsPerPage'));
                            } else {
                                /*提示信息*/
                                alert(data.message);
                            }
                        }, error: function (error) {
                            console.log('出错了！')
                        }, beforeSend: function () {

                        }
                    })
                }
            })
            /**
             * 修改按钮点击事件
             */
            $('#updateActivity').on('click', function () {
                /*获取CheckBox*/
                let $tableData = $('#tableData input[type=checkbox]:checked');
                /*判断是否选择了*/
                if ($tableData.size() === 0) {
                    alert("请选择要修改的市场活动！")
                    return false;
                }
                if ($tableData.size() > 1) {
                    alert("一次只能修改一个！");
                    return false;
                }
                /*获取当前修改的id值*/
                console.log($tableData[0].value);
                let id = $tableData[0].value;
                /*发送参数*/
                $.ajax({
                    url: 'workbench/activity/queryActivityById.do'+id,
                    type: 'GET',
                    // data: {id: id},
                    async:false,
                    dataType: 'json',
                    success: function (result) {
                        /*拼接数据*/
                        $('#edit-id').val(result.id);
                        $('#edit-marketActivityOwner').val(result.owner);
                        $('#edit-marketActivityName').val(result.name);
                        $('#edit-startDate').val(result.startDate);
                        $('#edit-endDate').val(result.endDate);
                        $('#edit-cost').val(result.cost);
                        $('#edit-description').val(result.description);
                    }, error: function (error) {
                        console.log('出错了!')
                    }, beforeSend: function () {

                    }
                })
            })
            /**
             * 模态修改窗口保存按钮点击事件
             */
            $('#updateActivityBtn').on('click', function () {
                let name = $('#edit-marketActivityName').val().trim();
                let owner = $('#edit-marketActivityOwner').val().trim();
                let startDate = $('#edit-startDate').val();
                let endDate = $('#edit-endDate').val();
                let cost = $('#edit-cost').val().trim();
                let description = $('#edit-description').val().trim();
                /*参数校验*/
                if (checkParameter(name, owner, startDate, endDate, cost) != null) {
                    alert(checkParameter(name, owner, startDate, endDate, cost))
                    return false
                }
                /*收集参数！*/
                let $activity = {
                    id: $('#edit-id').val(),
                    owner: owner,
                    name: name,
                    startDate: startDate,
                    endDate: endDate,
                    cost: cost,
                    description: description,
                }
                /*发送请求*/
                $.ajax({
                    url: 'workbench/activity/updateActivityById.do',
                    type: 'post',
                    data: JSON.stringify($activity),
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code === "1") {
                            /*重新查询*/
                            queryActivityByConditionForPage(1, $('#page').bs_pagination('getOption', 'rowsPerPage'))
                        } else {
                            /*弹出错误信息*/
                            alert(data.message);
                        }
                    }, error: function (error) {
                        console.log('出错了');
                    }, beforeSend: function () {

                    }
                })
            })
            /**
             * 批量导出市场活动按钮点击事件
             */
            $('#exportActivityAllBtn').on('click', function () {
                /*发送请求*/
                window.location.href = 'workbench/activity/exportAllActivitys.do';
            });
            /**
             * 选择导出市场活动按钮点击事件
             */
            $('#exportActivityXzBtn').on('click', function () {
                /*判断是否选择以及选择的函数*/
                /*获取选中的checkbox*/
                let $tableData = $('#tableData input[type=checkbox]:checked');
                if ($tableData.size() <= 0) {
                    alert("请选择要导出的市场活动！")
                    return false;
                }
                /*获取选择checkbox参数中value值*/
                let ids = [];
                $.each($tableData, function (index, obj) {
                    ids.push(this.value);
                })
                /*发送参数*/
                window.location.href = 'workbench/activity/exportMarketingActivities.do?ids=' + ids;
                /*                $.ajax({
                                    url:"workbench/activity/exportMarketingActivities.do",
                                    type:'get',
                                    data:{
                                        ids:ids
                                    }
                                })*/
            });
            /**
             * 导入市场活动添加单击事件
             */
            $('#importActivityBtn').on('click', function () {
                /*收集参数*/
                let activityFileName = $('#activityFile').val();
                let xls = '.xls';
                if (activityFileName.lastIndexOf(xls.toLocaleLowerCase()) === -1) {
                    alert("只支持xls文件");
                    return false;
                }
                let file = $('#activityFile')[0].files[0];
                if (file.size > 1024 * 1024 * 5) {
                    alert("文件大小不能大于5MB");
                    return false;
                }
                let formData = new FormData();
                formData.append("multipartFile", file)
                /*发送请求*/
                $.ajax({
                    url: 'workbench/activity/importExcelActivity.do',
                    type: 'post',
                    data: formData,
                    processData: false,
                    contentType: false,
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (data.code === "1") {
                            /*提示成功导入记录条数*/
                            alert("成功导入" + data.data + "条记录");
                            /*关闭模态窗口*/
                            $("#importActivityModal").modal('hide');
                            /*刷新列表*/
                            queryActivityByConditionForPage(1, $('#page').bs_pagination('getOption', 'rowsPerPage'));
                        } else {
                            alert(data.message);
                            /*显示模态窗口*/
                            $('#editActivityModal').modal('show');
                        }
                    }, error: function (error) {
                        console.log('出错了');
                    }
                })
            });
        });

        /**
         * 保存参数和修改参数的校验方法
         */
        function checkParameter(name, owner, startDate, endDate, cost) {
            /*处理参数*/
            if (notEmpty(name)) {
                return "名称不能为空！";
            }
            if (notEmpty(owner)) {
                return "所有者不能为空！";
            }
            /*先判断开始事件和结束时间是否都为空*/
            if (!(notEmpty(startDate) && notEmpty(endDate))) {
                /*判断结束时间是否比开始时间小*/
                if (endDate < startDate) {
                    return "结束日期不能比开始日期小";
                }

            }
            /*判断成本的格式*/
            var regExp = /^(([1-9]\d*)|0)$/;
            if (!regExp.test(cost)) {
                return "成本只能为非负整数";
            }


        }

        /**
         * 非空函数
         */
        function notEmpty(data) {
            if (data === null || data === '' || data === undefined) {
                return true;
            }
        }

        /**
         * 封装查询函数
         * @param beginNo
         * @param pageSize
         */
        function queryActivityByConditionForPage(beginNo, pageSize) {
            if (notEmpty(pageSize)) {
                pageSize = 10;
            }
            /**
             * 查询分页收集参数
             */
            let $activity = {
                name: $('#queryName').val(),
                owner: $('#queryOwner').val(),
                startDate: $('#queryStartDate').val(),
                endDate: $('#queryEndDate').val(),
            }
            /*发送请求*/
            $.ajax({
                url: 'workbench/activity/queryActivityByConditionForPage.do?beginNo=' + beginNo + '&pageSize=' + pageSize,
                type: 'post',
                data: JSON.stringify($activity),
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                success: function (data) {
                    let dataList = data.list;
                    /*遍历数据,拼接行*/
                    let htmlStr = '';
                    $.each(dataList, function (index, obj) {
                        htmlStr += '<tr class=\"active\">'
                        htmlStr += '<td><input type=\"checkbox\" value="' + obj.id + '"/></td>'
                        htmlStr += "<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/activity/detailActivity.do?id=" + obj.id + "'\">" + obj.name + "</a></td>"
                        htmlStr += '<td>' + obj.owner + '</td>'
                        htmlStr += '<td>' + obj.startDate + '</td>'
                        htmlStr += '<td>' + obj.endDate + '</td>'
                        htmlStr += '</tr>'
                    })
                    /*取消全选按钮*/
                    $('#checkAll').prop('checked', false);
                    $('#tableData').html(htmlStr);
                    /*计算总页数 总条数/每页显示条数*/
                    let number = Math.ceil(data.total / pageSize);
                    /*分页条函数*/
                    $("#page").bs_pagination({
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
                            queryActivityByConditionForPage(pageObj.currentPage, pageObj.rowsPerPage);
                        }
                    });
                }, error: function (error) {

                }, beforeSend: function () {
                    return true;
                }
            });
        }
    </script>
</head>
<body>

<!-- 创建市场活动的模态窗口 -->
<div class="modal fade" id="createActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="createActivityForm" role="form">

                    <div class="form-group">
                        <label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="create-marketActivityOwner">
                                <%--<option>zhangsan</option>
                                <option>lisi</option>
                                <option>wangwu</option>--%>
                                <c:forEach items="${userList}" var="user">
                                    <option value="${user.id}">${user.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-marketActivityName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control myDate" id="create-startDate">
                        </div>
                        <label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control myDate" id="create-endDate">
                        </div>
                    </div>
                    <div class="form-group">

                        <label for="create-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-cost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="create-description"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveCreateActivityBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="edit-id">
                    <div class="form-group">
                        <label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="edit-marketActivityOwner">
                                <%-- <option>zhangsan</option>
                                 <option>lisi</option>
                                 <option>wangwu</option>--%>
                                <c:forEach items="${userList}" var="user">
                                    <option value="${user.id}">${user.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-startDate" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-startDate" value="2020-10-10">
                        </div>
                        <label for="edit-endDate" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-endDate" value="2020-10-20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-cost" value="5,000">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="edit-description">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="updateActivityBtn" class="btn btn-primary" data-dismiss="modal">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 导入市场活动的模态窗口 -->
<div class="modal fade" id="importActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
            </div>
            <div class="modal-body" style="height: 350px;">
                <div style="position: relative;top: 20px; left: 50px;">
                    请选择要上传的文件：<small style="color: gray;">[仅支持.xls]</small>
                </div>
                <div style="position: relative;top: 40px; left: 50px;">
                    <input type="file" id="activityFile">
                </div>
                <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;">
                    <h3>重要提示</h3>
                    <ul>
                        <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                        <li>给定文件的第一行将视为字段名。</li>
                        <li>请确认您的文件大小不超过5MB。</li>
                        <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                        <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                        <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                        <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
            </div>
        </div>
    </div>
</div>


<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>市场活动列表</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">名称</div>
                        <input class="form-control" type="text" id="queryName">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">所有者</div>
                        <input class="form-control" type="text" id="queryOwner">
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">开始日期</div>
                        <input class="form-control" type="text" id="queryStartDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">结束日期</div>
                        <input class="form-control" type="text" id="queryEndDate">
                    </div>
                </div>

                <button type="button" id="queryActivityBtn" class="btn btn-default">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar"
             style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <button type="button" class="btn btn-primary" id="createActivity">
                    <span class="glyphicon glyphicon-plus"></span> 创建
                </button>
                <button type="button" class="btn btn-default" id="updateActivity" data-toggle="modal"
                        data-target="#editActivityModal"><span
                        class="glyphicon glyphicon-pencil"></span> 修改
                </button>
                <button type="button" id="deleteActivity" class="btn btn-danger"><span
                        class="glyphicon glyphicon-minus"></span> 删除
                </button>
            </div>
            <div class="btn-group" style="position: relative; top: 18%;">
                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal">
                    <span class="glyphicon glyphicon-import"></span> 上传列表数据（导入）
                </button>
                <button id="exportActivityAllBtn" type="button" class="btn btn-default"><span
                        class="glyphicon glyphicon-export"></span> 下载列表数据（批量导出）
                </button>
                <button id="exportActivityXzBtn" type="button" class="btn btn-default"><span
                        class="glyphicon glyphicon-export"></span> 下载列表数据（选择导出）
                </button>
            </div>
        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td><input id="checkAll" type="checkbox"/></td>
                    <td>名称</td>
                    <td>所有者</td>
                    <td>开始日期</td>
                    <td>结束日期</td>
                </tr>
                </thead>
                <tbody id="tableData">
                <%--<tr class="active">
                    <td><input type="checkbox"/></td>
                    <td><a style="text-decoration: none; cursor: pointer;"
                           onclick="window.location.href='detail.jsp';">发传单</a></td>
                    <td>zhangsan</td>
                    <td>2020-10-10</td>
                    <td>2020-10-20</td>
                </tr>
                <tr class="active">
                    <td><input type="checkbox"/></td>
                    <td><a style="text-decoration: none; cursor: pointer;"
                           onclick="window.location.href='detail.jsp';">发传单</a></td>
                    <td>zhangsan</td>
                    <td>2020-10-10</td>
                    <td>2020-10-20</td>
                </tr>--%>
                </tbody>
            </table>
            <!--分页条-->
            <div id="page"></div>
        </div>

        <%--<div style="height: 50px; position: relative;top: 30px;">
            <div>
                <button type="button" id="" class="btn btn-default" style="cursor: default;">共<b id="totalRow">50</b>条记录
                </button>
            </div>
            <div class="btn-group" style="position: relative;top: -34px; left: 110px;">
                <button type="button" class="btn btn-default" style="cursor: default;">显示</button>
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        10
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" id="menu" role="menu">
                        <li><a href="#">20</a></li>
                        <li><a href="#">30</a></li>
                    </ul>
                </div>
                <button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
            </div>
            <div style="position: relative;top: -88px; left: 285px;">
                <nav>
                    <ul class="pagination">
                        <li class="disabled"><a href="#">首页</a></li>
                        <li class="disabled"><a href="#">上一页</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">下一页</a></li>
                        <li class="disabled"><a href="#">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>--%>


    </div>

</div>
</body>
</html>