app.controller('MessagesController',['$scope', 'messagesService',function($scope,messagesService){

  $scope.messages = [];
  $scope.text = "";
  $scope.chat = messagesService('/rest');

  $scope.chat.connect("", "", function(){
      $scope.chat.subscribe("/topic/messages", function(message) {
          var body = JSON.parse(message.body)
          $scope.messages.push(body.text);
      });
  });
  
  $scope.sendName = function(){
      $scope.chat.send("/app/sendMessage", {}, JSON.stringify({ 'text': $scope.text }));
  }

}])