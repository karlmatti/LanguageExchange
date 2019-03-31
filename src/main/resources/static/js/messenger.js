
var targetsID = [];
var targetsNames = [];
var nicknameofthisuser;
var thisUserID;


$.ajax({
    url:"/friends",
    success: function (data) {
        data.forEach(stream => {
            if (stream.id == googleID) {
                nicknameofthisuser = stream.firstName + ' ' + stream.lastName;
                thisUserID = googleID;
                return;
            }
            targetsID.push(stream.id);
            targetsNames.push(stream.firstName + ' ' + stream.lastName);
        });
    },async: false
});

console.log(targetsID);
console.log(targetsNames);
console.log(nicknameofthisuser);
console.log(thisUserID);

