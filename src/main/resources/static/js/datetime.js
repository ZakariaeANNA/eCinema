  $(function () {

    //Date picker
    $('#reservationdate').datetimepicker({
        format: 'L',
        minDate: new Date(),
    })
    //Timepicker

    $('#timepickerfin').datetimepicker({
		format: 'HH:mm',
        pick12HourFormat: false,
    })
  })

