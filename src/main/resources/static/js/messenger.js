

var targetsID = [];
var targetsNames = [];
var nicknameofthisuser;
var thisUserID = googleID;


$.ajax({
    url:"/friends/" + thisUserID,
    success: function (data) {
        data.forEach(stream => {
            targetsID.push(stream)
        });
    },async: false
});


for (var i = 0; i < targetsID.length; i++) {
    $.ajax({
        url: "/users/" + targetsID[i],
        success: function (data) {
            targetsNames.push(data.firstName + ' ' + data.lastName);
        }, async: false
    });
}

console.log(targetsID);
console.log(targetsNames);
console.log(thisUserID);


