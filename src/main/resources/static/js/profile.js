
var temp = $("#googleID").text();
console.log(temp);
var userApi = Vue.resource('/users{/id}');

/*Vue.component('message-row', {
    props: ['finalMessage'],
    template: '<div>{{finalMessage}}</div>'
});

Vue.component('messages-list', {
    props: ['get'],
    template:
        '<div>' +
        '<message-row v-for="message in get" v-bind:finalMessage="message" :key="message.id"></message-row>' +
        '</div>'

});*/

var app = new Vue ({
    el: '#pData',
    data: {
        messages: ''
    },
    created: function () {
        userApi.get().then(result =>
        result.json().then(data =>
            data.forEach(user => {
                if (user.id == temp) {
                    console.log('some')
                    console.log("console");
                this.messages = user;
        }}))
    )},
});
console.log("right right right");