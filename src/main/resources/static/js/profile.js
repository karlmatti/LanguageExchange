var userApi = Vue.resource('/users');


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

let app = new Vue({
    el: '#pData',
    data: {
        messages: ''
    },
    created: function () {
        userApi.get().then(result =>
            result.json().then(data =>
                data.forEach(user => {

                    if (user.id == googleID) {

                        this.messages = user;
                    }
                }))
        )
    },
});


let req = new Vue({
    el: '#reqData',
    data: {
        users: []
    },
    created() {
        var vm = this
        this.$http
            .get('/friends/requests')
            .then(function (response) {
                vm.users = response.data
            })
    }
})