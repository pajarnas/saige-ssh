<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- javascripts -->
  <script src="/lyn-ssh/js/jquery.js"></script>
  <script src="/lyn-ssh/js/jquery-ui-1.10.4.min.js"></script>
  <script src="/lyn-ssh/js/jquery-1.8.3.min.js"></script>
  <script type="/lyn-ssh/text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
  <!-- bootstrap -->
  <script src="/lyn-ssh/js/bootstrap.min.js"></script>
  <!-- nice scroll -->
  <script src="/lyn-ssh/js/jquery.scrollTo.min.js"></script>
  <script src="/lyn-ssh/js/jquery.nicescroll.js" type="text/javascript"></script>
  <!-- charts scripts -->
  <script src="/lyn-ssh/assets/jquery-knob/js/jquery.knob.js"></script>
  <script src="/lyn-ssh/js/jquery.sparkline.js" type="text/javascript"></script>
  <script src="/lyn-ssh/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
  <script src="/lyn-ssh/js/owl.carousel.js"></script>
  <!-- jQuery full calendar -->
  <script src="/lyn-ssh/js/fullcalendar.min.js"></script>
    <!-- Full Google Calendar - Calendar -->
    <script src="/lyn-ssh/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
    <!--script for this page only-->
    <script src="/lyn-ssh/js/calendar-custom.js"></script>
    <script src="/lyn-ssh/js/jquery.rateit.min.js"></script>
    <!-- custom select -->
    <script src="/lyn-ssh/js/jquery.customSelect.min.js"></script>
    <script src="/lyn-ssh/assets/chart-master/Chart.js"></script>

    
    <!-- custom script for this page-->
    <script src="/lyn-ssh/js/sparkline-chart.js"></script>
    <script src="/lyn-ssh/js/easy-pie-chart.js"></script>
    <script src="/lyn-ssh/js/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/lyn-ssh/js/jquery-jvectormap-world-mill-en.js"></script>
    <script src="/lyn-ssh/js/xcharts.min.js"></script>
    <script src="/lyn-ssh/js/jquery.autosize.min.js"></script>
    <script src="/lyn-ssh/js/jquery.placeholder.min.js"></script>
    <script src="/lyn-ssh/js/gdp-data.js"></script>
    <script src="/lyn-ssh/js/morris.min.js"></script>
    <script src="/lyn-ssh/js/sparklines.js"></script>
    <script src="/lyn-ssh/js/charts.js"></script>
    <script src="/lyn-ssh/js/jquery.slimscroll.min.js"></script>
      <!-- container section end -->



  <!-- jquery ui -->
  <script src="/lyn-ssh/js/jquery-ui-1.9.2.custom.min.js"></script>

  <!--custom checkbox & radio-->
  <script type="/lyn-ssh/text/javascript" src="js/ga.js"></script>
  <!--custom switch-->
  <script src="/lyn-ssh/js/bootstrap-switch.js"></script>
  <!--custom tagsinput-->
  <script src="/lyn-ssh/js/jquery.tagsinput.js"></script>

  <!-- colorpicker -->
<script src="/lyn-ssh/js/bootstrap-colorpicker.js"></script>

  <!-- bootstrap-wysiwyg -->
  <script src="/lyn-ssh/js/jquery.hotkeys.js"></script>
  <script src="/lyn-ssh/js/bootstrap-wysiwyg.js"></script>
  <script src="/lyn-ssh/js/bootstrap-wysiwyg-custom.js"></script>
  <script src="/lyn-ssh/js/moment.js"></script>
  <!-- date picker -->
  <script src="/lyn-ssh/js/daterangepicker.js"></script>
  <script src="/lyn-ssh/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="/lyn-ssh/js/bootstrap-datetimepicker.min.js"></script>
   <script src="/lyn-ssh/js/locale/zh-cn.js" charset="UTF-8"></script>
   
  <!-- ck editor -->
  <script type="text/javascript" src="/lyn-ssh/assets/ckeditor/ckeditor.js"></script>
  <!-- custom form component script for this page-->
  <script src="/lyn-ssh/js/form-component.js"></script>
  
  <!-- custome script for all page -->
  <script src="/lyn-ssh/js/scripts.js"></script>
 
    
    <script>
   
    moment.locale('zh-cn');
        $(function () {
        	 $('#datetimepicker1').datetimepicker({
                 locale: 'zh-cn'
             });
        });
   
      //knob
      $(function() {
        $(".knob").knob({
          'draw': function() {
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
        $("#owl-slider").owlCarousel({
          navigation: true,
          slideSpeed: 300,
          paginationSpeed: 400,
          singleItem: true

        });
      });

      //custom select box

      $(function() {
        $('select.styled').customSelect();
      });

      /* ---------- Map ---------- */
      $(function() {
        $('#map').vectorMap({
          map: 'world_mill_en',
          series: {
            regions: [{
              values: gdpData,
              scale: ['#000', '#000'],
              normalizeFunction: 'polynomial'
            }]
          },
          backgroundColor: '#eef3f7',
          onLabelShow: function(e, el, code) {
            el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
          }
        });
      });
      ko.bindingHandlers.dateTimePicker = {
    		    init: function (element, valueAccessor, allBindingsAccessor) {
    		        //initialize datepicker with some optional options
    		        var options = allBindingsAccessor().dateTimePickerOptions || {};
    		        $(element).datetimepicker(options);

    		        //when a user changes the date, update the view model
    		        ko.utils.registerEventHandler(element, "dp.change", function (event) {
    		            var value = valueAccessor();
    		            if (ko.isObservable(value)) {
    		                if (event.date != null && !(event.date instanceof Date)) {
    		                    value(event.date.toDate());
    		                } else {
    		                    value(event.date);
    		                }
    		            }
    		        });

    		        ko.utils.domNodeDisposal.addDisposeCallback(element, function () {
    		            var picker = $(element).data("DateTimePicker");
    		            if (picker) {
    		                picker.destroy();
    		            }
    		        });
    		    },
    		    update: function (element, valueAccessor, allBindings, viewModel, bindingContext) {

    		        var picker = $(element).data("DateTimePicker");
    		        //when the view model is updated, update the widget
    		        if (picker) {
    		            var koDate = ko.utils.unwrapObservable(valueAccessor());

    		            //in case return from server datetime i am get in this form for example /Date(93989393)/ then fomat this
    		            koDate = (typeof (koDate) !== 'object') ? new Date(parseFloat(koDate.replace(/[^0-9]/g, ''))) : koDate;

    		            picker.date(koDate);
    		        }
    		    }
    		};
      
    </script>
    