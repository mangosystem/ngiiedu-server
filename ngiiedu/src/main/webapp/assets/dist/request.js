var apiSvr = 'http://localhost:8080/ngiiedu/api/v1';
//const apiSvr = 'http://1.234.82.19:8083/ngiiedu/api/v1';

var contextPath = '/ngiiedu';

function ajaxJson(methodUrl, data, successFnc, errorFnc ) {
  if (errorFnc == null) {
    errorFnc = function(xhr, status, err) {
      console.log('ajaxJson error(xhr, status, err)');
      console.log(xhr);
      alert('Error: 500');
    }
  }
  var method = methodUrl[0];
  var url = methodUrl[1];
  var req_setting = {
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
	var properties = {};
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
