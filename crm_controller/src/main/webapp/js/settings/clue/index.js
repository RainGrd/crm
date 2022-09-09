$(function () {
    /**
     * 创建线索按钮点击事件
     */
    $('#saveCreateClueBtn').on('click', function () {
        console.log(1);
        /*收集参数*/
        let $clue = {
            fullName: $('#create-fullname').val().trim(),
            appellation: $('#create-appellation').val().trim(),
            owner: $('#create-owner').val().trim(),
            company: $('#create-company').val().trim(),
            job: $('#create-job').val().trim(),
            email: $('#create-email').val().trim(),
            phone: $('#create-phone').val().trim(),
            website: $('#create-website').val().trim(),
            mPhone: $('#create-mphone').val().trim(),
            state: $('#create-state').val().trim(),
            source: $('#create-source').val().trim(),
            create_by: $('#create-create_by').val().trim(),
            description: $('#create-description').val().trim(),
            contactSummary: $('#create-contactSummary').val().trim(),
            nextContactTime: $('#create-nextContactTime').val().trim(),
            address: $('#create-address').val().trim(),
            /**/
        }

    })

});