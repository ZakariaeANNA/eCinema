  $(function () {

    //Date picker
    $('#reservationdate').datetimepicker({
        format: 'L',
        minDate: new Date(),
    })
    //Timepicker
    $('#timepicker').datetimepicker({
        pickSeconds: false,
        pick12HourFormat: false 
    })
    $('#timepickerfin').datetimepicker({
        pick12HourFormat: false,
    })
  })

