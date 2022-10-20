  $(function () {


    //Date picker
    $('#reservationdate').datetimepicker({
        format: 'L'
    });

    //Timepicker
    $('#timepicker').datetimepicker({
        format: 'HH:mm',
        pickDate: false,
        pickSeconds: false,
        pick12HourFormat: false 
    })
    $('#timepickerfin').datetimepicker({
        format: 'HH:mm',
        pickDate: false,
        pick12HourFormat: false 
    })
  })

  // DropzoneJS Demo Code Start

  // Get the template HTML and remove it from the doumenthe template HTML and remove it from the doument



  // Update the total progress bar


  // Hide the total progress bar when nothing's uploading anymore

  // Setup the buttons for all transfers
  // The "add files" button doesn't need to be setup because the config
  // `clickable` has already been specified.
  // DropzoneJS Demo Code End
