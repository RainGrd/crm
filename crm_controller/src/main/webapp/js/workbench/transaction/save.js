$(function () {
    /**
     * 阶段下拉列表change事件
     */
    $('#create-transactionStage').change(function () {
        let text = $('#create-transactionStage option:selected').text();
        if (text === '') {
            $("#create-possibility").val();
            return false;
        }
        $.ajax({
            url: 'workbench/transaction/getPossibilityByStage.do',
            data: {
                stage: text
            },
            dataType: 'json',
            type: 'post',
            success: function (result) {
                $('#create-possibility').val(result + '%');
            }, error: function (error) {
                console.log('出错了')
            }
        });
    });
    /**
     * 客户名称自动补全事件
     */
    $('#create-accountName').typeahead({
        source: function (jquery, process) {
            /*收集参数*/
            let name = $('#create-accountName').val();
            /*发送Ajax请求*/
            $.ajax({
                url: 'workbench/transaction/queryAllCustomerName.do',
                type: 'get',
                dataType: 'json',
                data: {
                    name: name,
                },
                success: function (data) {
                    /*赋值*/
                    process(data);
                }, error: function (error) {
                    console.log('出错了')
                }
            })
        }
    });
    /**
     * 预计成交日期,下次联系时间,日历插件安装
     */
    $('.transactionDate').datetimepicker({
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
    });
    /**
     * 创建交易按钮点击事件
     */
    $('#createCustomerBtn').on('click', function () {
        /*要保存的交易对象*/
        let $customer = {
            owner: '',
            name: '',
            money: '',
            expectedDate: '',
            customerName: '',
            stage: '',
            type: '',
            possibility: '',
            source: '',
            activityId: '',
            contactsId: '',
            description: '',
            contactSummary: '',
            nextContactTime: '',
        }
        /*收集参数*/
        $customer.name = $('#create-transactionName').val();
        $customer.owner = $('#create-transactionOwner').val();
        $customer.expectedDate = $('#create-expectedDate').val();
        $customer.money = $('#create-amountOfMoney').val();
        $customer.customerName = $('#create-accountName').val();
        $customer.stage = $('#create-transactionStage').val();
        $customer.type = $('#create-transactionType').val();
        $customer.possibility = $('#create-possibility').val();
        $customer.source = $('#create-clueSource').val();
        $customer.activityId = $('#activityId').val();
        $customer.contactsId = $('#contactsId').val();
        $customer.description = $('#create-description').val();
        $customer.contactSummary = $('#create-contactSummary').val();
        $customer.nextContactTime = $('#create-nextContactTime').val();
        /*表单验证*/
        //非空判断
        if (notEmpty($customer.money)) {
            alert("金额不能为空");
            return false;
        }
        if (notEmpty($customer.name)) {
            alert("名字不能为空");
            return false;
        }
        if (notEmpty($customer.expectedDate)) {
            alert("预计成交日期不能为空");
            return false;
        }
        if (notEmpty($customer.stage)) {
            alert("阶段不能为空");
            return false;
        }
        if (notEmpty($customer.type)) {
            alert("类型不能为空");
            return false;
        }
        if (notEmpty($customer.possibility)) {
            alert("可能性不能为空");
            return false;
        }
        if (notEmpty($customer.source)) {
            alert("来源不能为空");
            return false;
        }
        if (notEmpty($customer.activityId)) {
            alert("市场活动源不能为空");
            return false;
        }
        if (notEmpty($customer.customerName)) {
            alert("联系人名称不能为空");
            return false;
        }
        if (notEmpty($customer.description)) {
            alert("描述不能为空");
            return false;
        }
        if (notEmpty($customer.contactSummary)) {
            alert("联系纪要不能为空");
            return false;
        }
        if (notEmpty($customer.nextContactTime)) {
            alert("下次联系时间不能为空");
            return false;
        }
        //正则判断
        if (!checkDate($customer.nextContactTime)) {
            alert("下次联系时间格式不正确");
            return false;
        }
        if (!checkDate($customer.expectedDate)) {
            alert("预计成交日期格式不正确");
            return false;
        }
        if (!/^(([1-9]\d*)|0)$/.test($customer.money)) {
            alert("金额不能为负整数");
            return false;
        }
        console.log($customer);
        /*发送Ajax请求*/
        $.ajax({
            url: 'workbench/transaction/saveCreateCustomer.do',
            type: 'post',
            data: JSON.stringify($customer),
            dataType: 'json',
            contentType: 'application/json',
            success: function (result) {
                if (result.code === '1') {
                    /*跳转至交易页面*/
                    window.location.href = 'workbench/transaction/toIndex.do'
                } else {
                    /*弹出提示*/
                    alert(result.message);
                }
            }, error: function () {
                console.log('出错了');
            }
        })
    });
    /**
     * 联系人名称模态窗口事件
     */
    $('#contactsModal').on('click', function () {
        /*清除参数*/
        $('#contactsName').val('');
        /*打开模态窗口*/
        $('#findContacts').modal("show");
    });
    /**
     * 取消按钮点击事件
     */
    $('#cancel').on('click', function () {
        window.history.back();
    })
    /**
     * 联系人名称键盘弹起事件
     */
    $('#contactsName').on('keyup', function () {
        /*收集参数*/
        let val = $(this).val();
        /*发送参数*/
        $.ajax({
            url: 'workbench/transaction/queryContactsListByContactsName.do',
            type: 'post',
            dataType: 'json',
            data: {contactsName: val},
            success: function (result) {
                let htmlStr = ''
                $.each(result, function (index, obj) {
                    htmlStr += '<tr>\n' +
                        '<td><input type="radio"  value="' + obj.id + '" name="contacts" contactsName="' + obj.fullName + '"/></td>\n' +
                        '<td >' + obj.fullName + '</td>\n' +
                        '<td>' + obj.email + '</td>\n' +
                        '<td>' + obj.mPhone + '</td>\n' +
                        '</tr>'
                });
                $('#contactsList').html(htmlStr);
            }, error: function (error) {
                console.log('出错了！');
            }
        });
    });
    /**
     * 联系人表格后面表格radio的点击事件
     */
    $('#contactsTable').on('click', 'input[type=radio]', function () {
        /*获取值*/
        let contactsId = $(this).val();
        let contactsName = $(this).attr('contactsName');
        /*关闭模态窗口*/
        $('#findContacts').modal('hide');
        /*放置参数*/
        $('#contactsId').val(contactsId);
        $('#create-contactsId').val(contactsName);
    });
    /**
     * 联系活动人活动源点击事件
     */
    $('#activityModal').on('click', function () {
        /*清除参数*/
        $('#activityName').val('');
        /*打开模态窗口*/
        $('#findMarketActivity').modal('show');
    });
    /**
     * 市场活动搜索框键盘弹起事件
     */
    $('#activityName').on('keyup', function () {
        /*收集参数*/
        let value = $(this).val();
        /*发送请求*/
        $.ajax({
            url: 'workbench/transaction/queryAssociatedActivityByActivityName.do',
            type: 'psot',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                activityName: value,
            }),
            success: function (result) {
                let str = '';
                $.each(result, function (index, obj) {
                    str += '<tr>\n' +
                        '<td><input type="radio" value="' + obj.id + '" name="activity" activityName="' + obj.name + '"/></td>\n' +
                        '<td>' + obj.name + '</td>\n' +
                        '<td>' + obj.startDate + '</td>\n' +
                        '<td>' + obj.endDate + '</td>\n' +
                        '<td>' + obj.owner + '</td>\n' +
                        '</tr>'
                })
                $('#activityList').html(str);
            },
            error: function (error) {
                console.log('出错了')
            }
        })
    });
    /**
     * 市场活动活动源点击事件
     */
    $('#activityTable').on('click', 'input[type=radio]', function () {
        /*获取值*/
        let activityId = $(this).val();
        console.log(activityId)
        let activityName = $(this).attr('activityName');
        /*关闭模态窗口*/
        $('#findMarketActivity').modal('hide');
        /*放置参数*/
        $('#activityId').val(activityId);
        $('#create-activityName').val(activityName);
    })
});

/**
 * 非空函数
 */
function notEmpty(data) {
    if (data === null || data === '' || data === undefined) {
        return true;
    }
}

/**
 * 时间格式判断函数
 * @param dateStr
 * @returns {boolean}
 */
function checkDate(dateStr) {
    var a = /^(\d{4})-(\d{2})-(\d{2})$/
    if (!a.test(dateStr)) {
        return false
    } else {
        return true;
    }
}
