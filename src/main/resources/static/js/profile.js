
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
                if (user.googleId == temp) {
                this.messages = user;
        }}))
    )},
});
console.log("right right right");

new Vue({
    el: '#profilePicture',
    data: {
        items: [
            {
                image: false,
            },
        ],
    },
    methods: {
        onFileChange(item, e) {
            var files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            this.createImage(item, files[0]);
        },
        createImage(item, file) {
            var image = new Image();
            var reader = new FileReader();

            reader.onload = (e) => {
                item.image = e.target.result;
            };
            reader.readAsDataURL(file);
        },
        removeImage: function (item) {
            item.image = false;
        }
    }
})