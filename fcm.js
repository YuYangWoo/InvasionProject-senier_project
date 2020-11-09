const firebase = require('firebase');
var firebaseConfig = {
  apiKey: "AIzaSyAdoflUdlFVW4o7tB_xQ9JAsBmABJ8BMpk",
  authDomain: "cerberus-592f9.firebaseapp.com",
  databaseURL: "https://cerberus-592f9.firebaseio.com",
  projectId: "cerberus-592f9",
  storageBucket: "cerberus-592f9.appspot.com",
  messagingSenderId: "213036694754",
  appId: "1:213036694754:web:239615e2bb106259b072bb",
  measurementId: "G-Q0HFH3Y4QG"
};
firebase.initializeApp(firebaseConfig);
const request = require('request');

const options = {
    uri:'https://fcm.googleapis.com/fcm/send', 
    method: 'POST',
    headers: {
        "content-type": "application/json",
        "Authorization": "key=AAAAMZn5_OI:APA91bEa17m4brjjK48PdNJy6GIGWSC57G89fLU9dzWLNK4bZmPvdC1eHwcpYugch_2IMfunGbV6kjhnTJJt6S4ChO4r-ij0QAWjH1MEHxk-_0Pn__PzEjlE5xC4SiV5BEOjCubliZ25"
    },
    json: {
        'to': "fGKtMPS-XFg:APA91bFdHJOAOEMYixKCs1IvbJI1wa73xDQ4HFXhNuKyNUuI03kGieNxLF3BpP3ur4lV9zR44sOr2KfNqHD3unCzrlWcOVqVV-JiTCGOKtPL7YouPPrLt0QzMGe3IkJnRgfqPEcxQSl9",
        'notification': {
            'body': '',
            'title': '',
        },
        "message": {
          "data" : {
             "title" : "",
             "body"  : ""
          }
        }
    }
  }
//request.post(options, function(err,httpResponse,body){ /* ... */ })

// 파베 접근해서 token을 받아온다.
var key=[];
var token=[];
// 파베 접근해서 token을 받아온다.
firebase.database().ref("Token").on('value', (snapshot)=>{
  //console.log(snapshot.val());
  val = snapshot.val();
  console.log(val);
  //키값들을 받는다.
  key = Object.keys(val);
  // 토큰값을 받는다.
  token = Object.values(val);
  console.log(key);
  console.log(token);
})


User = {
  id:"cerberusTable",
  door: 0,
  door_open: 0,
  danger: 0,
  alarm:0
};
//데이터 쓰기
//firebase.database().ref('cerb1/door').set(1);

// firebase를 통해서 값 실시간 모니터링
firebase.database().ref(User.id+"/door").on('value', (snapshot)=>{
    //console.log(snapshot.val());
      val = snapshot.val();
      if(val == 1){//door opened
        //서버에 알려주기
        User.door = val;
        console.log("door opend!!")
        options.json.notification.title = "문열림 알림"
        options.json.notification.body = "현관문이 열렸습니다."
      }else{
        User.door = val;
        console.log("door closed");
        options.json.notification.body = "현관문이 닫혔습니다."
        options.json.notification.title = "문닫힘 알림"
      }
      token.forEach(function(element){
        options.json.to = element;
        request.post(options, function(err,httpResponse,body){ /* ... */ });
      })
})
firebase.database().ref(User.id+"/danger").on('value', (snapshot)=>{
  //console.log(snapshot.val());
    val = snapshot.val();
    if(val == 1){//danger!!
      User.danger = val;
      console.log("Danger!!!!!!")
      options.json.notification.title = "위험수준 : 높음"
      options.json.notification.body = "신발을 벗지 않은 내부에 침입자가 있습니다."
      token.forEach(function(element){
        options.json.to = element;
        request.post(options, function(err,httpResponse,body){ /* ... */ });
      })
    }
    else{
      User.danger = val;
      console.log("normal")
    }
})
firebase.database().ref(User.id+"/alarm").on('value', (snapshot)=>{
  //console.log(snapshot.val());
    val = snapshot.val();
    if(val == 1){//danger!!
      User.alarm = val;
      console.log("alarm on!!!!!!");

    }
    else{
      User.alarm = val;
      console.log("alarm off")

    }
})