$(function () {
    /**
     * 阶段下拉列表change事件
     */
    $('#create-transactionStage').change(function () {
        console.log(this.value);
        let text = $('#create-transactionStage option:selected').text();
        if (text === '') {
            $("#create-possibility").val();
            return false;
        }

        let value = this.value;
        $.ajax({
            url: 'workbench/transaction/getPossibilityByStage.do',
            data: {
                stage: text
            },
            dataType: 'json',
            type: 'post',
            success: function (result) {
                console.log(result);
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
                    console.log(data)
                    /*赋值*/
                    process(data);
                }, error: function (error) {
                    console.log('出错了')
                }
            })
        }
    });
    /**
     * 下次联系时间日历插件安装
     */
    $('#create-nextContactTime').datetimepicker({
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

        /*发送Ajax请求*/
        $.post('workbench/transaction/', JSON.stringify($customer), function (result, status, xhr) {
            console.log(result);
            console.log(status);
            console.log(xhr);
            if (result.code === '1') {
                /*跳转到列表页面*/
            } else {
                /*页面不跳转，弹出提示*/
                alert(result.message);
            }
        }, 'json')
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
     * 联系人名称键盘弹起事件
     */
    $('#contactsName').on('keyup', function () {
        /*收集参数*/
        let val = $(this).val();
        console.log(val);
        /*发送参数*/
        $.ajax({
            url: 'workbench/transaction/queryContactsListByContactsName.do',
            type: 'post',
            dataType: 'json',
            data: {contactsName: val},
            success: function (result) {
                console.log(result);
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
     * 联系人表格后面表格CheckBox的点击事件
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
     * 市场活动活动源点击事件
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
        console.log(value);
        /*发送请求*/
        $.get('workbench/transaction/queryAssociatedActivityByActivityName.do', JSON.stringify({
            activityName: value,
        }), function (result) {
            console.log(result);
        }, 'json');
    });

});