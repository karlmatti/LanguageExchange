
var temp = $("#googleID").text();
console.log(temp);
var userApi = Vue.resource('/users{/id}');

Vue.component('message-row', {
    props: ['finalMessage'],
    template: '<div>{{finalMessage.firstName}}</div>'
});

Vue.component('messages-list', {
    props: ['get'],
    template:
        '<div>' +
        '<message-row v-for="message in get" v-bind:finalMessage="message" :key="message.id"></message-row>' +
        '</div>'

});

var app = new Vue ({
    el: '#pData',
    template: '<messages-list v-bind:get="messages" ></messages-list>',
    data: {
        messages: []
    },
    created: function () {
        userApi.get().then(result =>
        result.json().then(data =>
            data.forEach(user => {
                if (user.googleId == temp) {
                return this.messages.push(user);
        }}))
    )},
    mounted: function() {
        console.log("bla");
        console.log(this.$refs.testElement);
    }
});
console.log("right right right");