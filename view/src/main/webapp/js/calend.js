 $(function () {
            window.prettyPrint && prettyPrint();
            var nowTemp = new Date();
            var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
                    .getDate(), 0, 0, 0, 0);
            $('.dp3.span6').datepicker({
                endDate: now,
                autoclose: true,
                startView: 2,
                format: 'mm-dd-yyyy'
            });
        });
 
 $(function () {
     $('.redactor_content .span12').redactor({
         focus: true,
         toolbarFixed: true,
         autoresize: false,
         linebreaks: true,
         css: '/redactor/redactor-iframe.css'
     });
 });