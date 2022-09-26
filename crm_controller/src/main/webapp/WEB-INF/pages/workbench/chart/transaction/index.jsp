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
    <script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>
    <script type="text/javascript" src="js/workbench/transaction/detail.js"></script>
    <script type="text/javascript" src="jquery/echars/echarts.min.js"></script>
    <title>交易统计图表页面</title>
</head>
<body>

<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
<script type="text/javascript">
    $(function () {
        getCountOfTransactionGroupByStage();
    });

    /**
     * 查询所有交易统计阶段的图表数据
     */
    function getCountOfTransactionGroupByStage() {
        $.ajax({
            url: 'workbench/chart/transaction/queryCountOfTransactionGroupByStage.do',
            type: 'post',
            dataType: 'json',
            success: function (result) {
                let name=[];
                $.each(result,function (index, obj) {
                    name.push(obj.name);
                });
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '交易统计图表',
                        subtext: '交易表中各个阶段的数量',
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c}'
                    },
                    toolbox: {
                        feature: {
                            dataView: {readOnly: false},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    legend: {
                        data: name
                    },
                    series: [
                        {
                            name: '数据量',
                            type: 'funnel',
                            left: '10%',
                            width: '80%',
                            label: {
                                formatter: '{b}'
                            },
                            labelLine: {
                                show: true
                            },
                            itemStyle: {
                                opacity: 0.8
                            },
                            emphasis: {
                                label: {
                                    position: 'inside',
                                    formatter: '{b}: {c}'
                                }
                            },
                            data: result
                        }
                        /*, {
                            name: 'Actual',
                            type: 'funnel',
                            left: '10%',
                            width: '80%',
                            maxSize: '80%',
                            label: {
                                position: 'inside',
                                formatter: '{c}%',
                                color: '#fff'
                            },
                            itemStyle: {
                                opacity: 0.5,
                                borderColor: '#fff',
                                borderWidth: 2
                            },
                            emphasis: {
                                label: {
                                    position: 'inside',
                                    formatter: '{b}Actual: {c}%'
                                }
                            },
                            data: [
                                {value: 30, name: 'Visit'},
                                {value: 10, name: 'Inquiry'},
                                {value: 5, name: 'Order'},
                                {value: 50, name: 'Click'},
                                {value: 80, name: 'Show'}
                            ],
                            // Ensure outer shape will not be over inner shape when hover.
                            z: 100
                        }*/
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }, error: function (error) {
                console.log('出错了!')
            }
        })
    }
</script>
</html>
