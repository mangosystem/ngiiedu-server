
//google drive
var CLIENT_ID = '553092736855-9e47tfrpiktcaud7ug8sd0voepn31n8d.apps.googleusercontent.com';
var API_KEY = 'AIzaSyAKSsWoEvZAOpEjt2vSShua2rXRtDGf8HI';
var DISCOVERY_DOCS = [
  "https://www.googleapis.com/discovery/v1/apis/sheets/v4/rest",
  "https://www.googleapis.com/discovery/v1/apis/drive/v3/rest"
];

var discoveryUrl ='https://sheets.googleapis.com/$discovery/rest?version=v4';

var SCOPES = 'https://www.googleapis.com/auth/drive.metadata.readonly https://www.googleapis.com/auth/spreadsheets.readonly';

/**
 *  On load, called to load the auth2 library and API client library.
 */
function handleClientLoad() {
    console.log('handleClientLoad')
    gapi.load('client:auth2', initClient);
}

function initClient() {
    gapi.client.init({
      apiKey: API_KEY,
      clientId: CLIENT_ID,
      discoveryDocs: DISCOVERY_DOCS,
      scope: SCOPES
    }).then(function () {
      console.log('initClient()')
      if(gapi.auth2.getAuthInstance().isSignedIn.get()){
        gapi.auth2.getAuthInstance().disconnect();
      }
    });
  }


   var googleSuccess = function(){
     console.log('success');
   };
