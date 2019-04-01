var targetNames = [];
var realNames = [];
var realChats = [];
var nicknameOfThisUser = googleID;


$.ajax({
    url:"/friends/" + nicknameOfThisUser,
    success: function (data) {
       for ([userId, chatID] of Object.entries(data)) {
           targetNames.push(userId);
           realChats.push(chatID);
       }
    },async: false
});


for (let i = 0; i < targetNames.length; i++) {
    $.ajax({
        url: "/users/" + targetNames[i],
        success: function (data) {
            realNames.push(data.firstName + ' ' + data.lastName);
        }, async: false
    });
}

console.log(targetNames);
console.log(realNames);
console.log(realChats);
console.log(nicknameOfThisUser);

var activeChat = "";

for (i = 1; i < targetNames.length + 1; i++) {

    $('<div>', {idx:"" + i, id:"m" + i, class:"message-card"}).appendTo('.left-body');
    //$('<div>', {class:"m-card-icon", style:"background: url("+ realPictures[i - 1] +"); background-size: 100% 100%;"}).appendTo('#m' + i );
    $('<div>', {id:"s" + i, class:"m-card-content"}).appendTo('#m' + i );
    $('<div>', {class:"m-c-name active"}).text(realNames[i - 1]).appendTo('#s'+ i);
    $('<div>', {class:"m-c-time"}).appendTo('#s' + i);
    $('<div>', {class:"m-c-last-message"}).text("Perfect Imperfection").appendTo('#s'+ i);
}

var targetUser = "";
var numberOfStringsinCertainChat = 0;
var timeId = 0;

getFirstUserChat();

$(document).ready(function () {


    $('.r-f-input-wrp .input-wrp input').on('keydown', function (ev) {
        if (ev.keyCode == 13) {
            send();
        }
    });

    $('.send').on('click', function (ev) {
        send();
    });



    $('.left-body .message-card').on('click', function (ev) {
        console.log(ev);
        numberOfStringsinCertainChat = 0;
        clearInterval(timeId);
        var idx = parseInt($(this).attr('idx'));
        //var obj = list[idx-1];
        var $target = $(this).find('.m-c-name');
        targetUser = $target.text();

        console.log(realChats[idx-1]);

        console.log("return haven't worked");

        activeChat = nicknameOfThisUser + " " + targetNames[idx-1] + " messages/" + realChats[idx-1] + " " + realNames[idx-1];
        console.log(activeChat);
        $.ajax({
            type: "POST",
            url:"/chatbox/" + activeChat,
            success: function (data) {
                console.log(data);
                tempVar = data;
            },async: false
        });

        if (tempVar.length < 3) {
            $('.right-header .name-wrp .name').text(realNames[idx-1]);
            numberOfStringsinCertainChat = 0;
        } else {
            numberOfStringsinCertainChat = JSON.parse(tempVar).detail.length;
        }



        obj = JSON.parse(tempVar);

        var bgSrc = $target.attr('src');
        $('.m-c-name').removeClass('active');
        $target.addClass('active');
        createMessages(bgSrc, obj);

        timerId = setInterval(function() {
            var stringsCurrent = 0;
            $.ajax({
                type: "POST",
                url:"http://dijkstra.cs.ttu.ee/~igkozl/prax4/server.php",
                data: {getChat: activeChat},
                success: function (data) {
                    if (data.length < 3) {
                        stringsCurrent = 0;
                    } else {
                        stringsCurrent = JSON.parse(data).detail.length;
                    }
                },async: false
            });
            if (stringsCurrent > numberOfStringsinCertainChat) {
                numberOfStringsinCertainChat++;
                dataFromNextLine = "";
                $.ajax({
                    type: "POST",
                    url:"http://dijkstra.cs.ttu.ee/~igkozl/prax4/server.php",
                    data: {getChat: activeChat},
                    success: function (data) {
                        console.log(data);
                        innerObj = JSON.parse(data).detail[numberOfStringsinCertainChat - 1];
                    },async: false
                });

                console.log(innerObj);
                if (innerObj.owner != "self") {
                    sendRobot(innerObj);
                }
            }
        }, 500);
    });

    scrollBottom();


});


function getFirstUserChat() {

    numberOfStringsinCertainChat = 0;
    clearInterval(timeId);
    var idx = parseInt($('#m1').attr('idx'));
    //var obj = list[idx-1];
    var $target = $('#m1').find('.m-c-name');
    targetUser = $target.text();

    console.log(realChats[idx-1]);

    activeChat = nicknameOfThisUser + " " + targetNames[idx-1] + " " + realChats[idx-1] + " " +  realNames[idx-1];
    console.log(activeChat);
    $.ajax({
        url:"/chatbox/" + activeChat,
        type: 'POST',
        success: function (data) {
            console.log(data);
            tempVar = data;
        },async: false
    });

    if (tempVar.length < 3) {
        $('.right-header .name-wrp .name').text(realNames[idx-1]);
        numberOfStringsinCertainChat = 0;
    } else {
        numberOfStringsinCertainChat = JSON.parse(tempVar).detail.length;
    }


    obj = JSON.parse(tempVar);


    var bgSrc = $target.attr('src');
    $('.m-c-name').removeClass('active');
    $target.addClass('active');
    createMessages(bgSrc, obj);

    timerId = setInterval(function() {
        var stringsCurrent = 0;
        $.ajax({
            type: "POST",
            url:"http://dijkstra.cs.ttu.ee/~igkozl/prax4/server.php",
            data: {getChat: activeChat},
            success: function (data) {
                if (data.length < 3) {
                    stringsCurrent = 0;
                } else {
                    stringsCurrent = JSON.parse(data).detail.length;
                }
            },async: false
        });
        if (stringsCurrent > numberOfStringsinCertainChat) {
            numberOfStringsinCertainChat++;
            dataFromNextLine = "";
            $.ajax({
                type: "POST",
                url:"http://dijkstra.cs.ttu.ee/~igkozl/prax4/server.php",
                data: {getChat: activeChat},
                success: function (data) {
                    console.log(data);
                    innerObj = JSON.parse(data).detail[numberOfStringsinCertainChat - 1];
                },async: false
            });

            console.log(innerObj);
            if (innerObj.owner != "self") {
                sendRobot(innerObj);
            }
        }
    }, 500);
}


function send() {
    var d = new Date();
    var $input = $('.r-f-input-wrp .input-wrp input');
    var $li = $('<li>', {class: 'self'});
    var $msg = $('<div>', {class: 'message'}).text($input.val());
    $li.append('\n').append($msg);

    activeChatNumber = activeChat.split(" ");
    activeChatNumber = activeChatNumber[2];


    var sendMessage = activeChatNumber + "$" + '\n{ "owner": ' + '"' + "<?php echo $nickname ?>" + '"' + ', "content": ' + '"' + $input.val() + '"},'
    console.log(sendMessage);
    $.ajax({
        type: "POST",
        url:"http://dijkstra.cs.ttu.ee/~igkozl/prax4/server.php",
        data: {writeMessage: sendMessage},
        success: function (data) {
            console.log(data);
        },async: false
    });


    $('.message-ul').append('\n').append($li);
    $input.val('');

    setTimeout(function () {
        $li.addClass('init');
    }, 0);
    scrollBottom();

}


function createMessages(bgSrc, data) {
    $('.message-ul').children().remove();

    console.log("I AM IN createMessages");
    console.log(data);

    $('.right-header .name-wrp .name').text(data.name);


    if (jQuery.isEmptyObject(data)) {
        return 0;
    }

    var $input = $('.r-f-input-wrp .input-wrp input');
    for (var i=0; i<data.detail.length; i++) {
        var detail = data.detail[i];
        var $li = $('<li>', {class: detail.owner});
        var $msg = $('<div>', {class: 'message'}).text(detail.content);

        if (detail.owner == 'other') {
            var $icon = $('<div>', {class: 'icon'}).css({background: "url(" + bgSrc + ") 0 /cover"});
            $li.append('\n').append($icon).append('\n').append($msg).append('\n');
        } else {
            $li.append('\n').append('\n').append($msg);
        }
        $('.message-ul').append('\n').append($li);

    }

    setTimeout(function () {
        $('.message-ul .other').addClass('init');
        $('.message-ul .self').addClass('init');
    }, 0);
    scrollBottom();
}

function scrollBottom() {
    var $ul = $('.message-ul');
    var offset = $ul[0].scrollHeight - $ul[0].clientHeight;
    if (offset > 0) {
        $ul[0].scrollTop += offset;
    }
}

function sendRobot(pushData) {
    $('.typing').removeClass('typing');

    var $li = $('<li>', {class: 'other typing'});
    var $msg = $('<div>', {class: 'message'}).append('<span>●</span><span>●</span><span>●</span>');
    $li.append('\n').append($msg)
    $('.message-ul').append('\n').append($li);
    scrollBottom();

    $li.css({transform: 'scale(1)'});

    setTimeout(function () {
        $msg.text(pushData.content);
    }, 2300);
}

!function(a){"use strict";a.fn.bigSlide=function(b){var c=a.extend({menu:"#menu",push:".push",side:"left",menuWidth:"15.625em",speed:"300"},b),d=this,e=a(c.menu),f=a(c.push),g=c.menuWidth,h={position:"fixed",top:"0",bottom:"0","settings.side":"-"+c.menuWidth,width:c.menuWidth,height:"100%"},i={"-webkit-transition":c.side+" "+c.speed+"ms ease","-moz-transition":c.side+" "+c.speed+"ms ease","-ms-transition":c.side+" "+c.speed+"ms ease","-o-transition":c.side+" "+c.speed+"ms ease",transition:c.side+" "+c.speed+"ms ease"};return e.css(h),f.css(c.side,"0"),e.css(i),f.css(i),e._state="closed",e.open=function(){e._state="open",e.css(c.side,"0"),f.css(c.side,g)},e.close=function(){e._state="closed",e.css(c.side,"-"+g),f.css(c.side,"0")},d.on("click.bigSlide",function(a){a.preventDefault(),"closed"===e._state?e.open():e.close()}),d.on("touchend",function(a){d.trigger("click.bigSlide"),a.preventDefault()}),e}}(jQuery);

$(document).ready(function() {
    $('.menu-link').bigSlide();
});

document.querySelector( "#nav-toggle" ).addEventListener( "click", function() {
    this.classList.toggle( "active" );
});