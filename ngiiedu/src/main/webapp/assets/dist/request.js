const apiSvr = 'http://localhost:8080/ngiiedu/api/v1';

const contextPath = '/ngiiedu';

function ajaxJson( [method, url], data, successFnc, errorFnc ) {
  if (errorFnc == null) {
    errorFnc = function(xhr, status, err) {
      console.log('ajaxJson error(xhr, status, err)');
      console.log(xhr);
      alert('Error: 500');
    }
  }

  let req_setting = {
    url: url,
    method: method,
    data: data,
    dataType: 'json',
    // headers: {
    //   'X-CSRFToken': $('meta[name=csrf-token]').attr('content')
    // },
    success: successFnc,
    error: errorFnc
  };

  if (method=='PUT' || method=='DELETE') {
    if (req_setting.data == null) {
      req_setting.data = {};
    }

    req_setting.data._method__ = method;
    req_setting.method = 'POST';
  }

  $.ajax(req_setting);
}


function arrayToObject(arr) {
  let properties = {};
  for (var i in arr) {
    properties[arr[i].name] = arr[i].value;
  }
  return properties;
}


jQuery.fn.serializeObject = function() {
  var obj = null;
  try {
    if ( this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
      var arr = this.serializeArray();
      if ( arr ) {
        obj = {};
        jQuery.each(arr, function() {
          obj[this.name] = this.value;
        });
      }//if ( arr ) {
 		}
  }
  catch(e) {alert(e.message);}
  finally  {}

  return obj;
};
