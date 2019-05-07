var targetIDs = [];
var realNames = [];
var chatID = [];
var lastMessages = [];
var thisUsersID = googleID;

getInfoAboutFriends();
displayAllChats();

var activeChat = "";
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

    $('.search input').on('keydown', function (ev) {

        if (ev.keyCode == 13) {

            let targetName = $('.search input').val();
            getInfoAboutFriends();
            let targetIDsCopy = [];
            let realNamesCopy = [];
            let chatIDCopy = []
            let lastMessageCopy = [];
            for (let i = 0; i < targetIDs.length; i++) {
                console.log(targetIDs);
                if (realNames[i].toLowerCase().includes(targetName.toLowerCase())) {
                    console.log("WORKS");
                    targetIDsCopy.push(targetIDs[i]);
                    realNamesCopy.push(realNames[i]);
                    chatIDCopy.push(chatID[i]);
                    lastMessageCopy.push(lastMessages[i]);
                }
            }
            targetIDs = targetIDsCopy;
            realNames = realNamesCopy;
            chatID = chatIDCopy;
            lastMessages = lastMessageCopy;
            displayAllChats();
        }
    });


    $('.left-body .message-card').on('click', function (ev) {
        console.log(ev);
        numberOfStringsinCertainChat = 0;
        clearInterval(timeId);
        let idx = parseInt($(this).attr('idx'));
        let $target = $(this).find('.m-c-name');
        targetUser = $target.text();

        activeChat = thisUsersID + " " + targetIDs[idx-1] + " " + chatID[idx-1] + " " +  realNames[idx-1];
        let tempVar = '';
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
        let obj = JSON.parse(tempVar);

        let bgSrc = $target.attr('src');
        $('.m-c-name').removeClass('active');
        $target.addClass('active');
        createMessages(bgSrc, obj, idx);

        let timerId = setInterval(function() {
            let stringsCurrent = 0;
            $.ajax({
                type: "POST",
                url:"/chatbox/" + activeChat,
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
                let innerObj = '';
                $.ajax({
                    url:"/chatbox/" + activeChat,
                    type: "POST",
                    success: function (data) {
                        console.log(data);
                        innerObj = JSON.parse(data).detail[numberOfStringsinCertainChat - 1];
                    },async: false
                });

                console.log(innerObj);
                if (innerObj.owner != "self") {
                    sendRobot(innerObj, idx);
                }
            }
        }, 500);
    });

    scrollBottom();
});

function displayAllChats() {

    for (i = 0; i < targetIDs.length + 1; i++) {

        $('.message-card').remove();
        //$('<div>', {class:"m-card-icon", style:"background: url("+ realPictures[i - 1] +"); background-size: 100% 100%;"}).appendTo('#m' + i );
        $('.m-card-content').remove();
        $('.m-c-name active').remove();
        $('.m-c-time').remove();
        $('.m-c-last-message').remove();
    }

    for (i = 1; i < targetIDs.length + 1; i++) {

        $('<div>', {idx:"" + i, id:"m" + i, class:"message-card"}).appendTo('.left-body');
        //$('<div>', {class:"m-card-icon", style:"background: url("+ realPictures[i - 1] +"); background-size: 100% 100%;"}).appendTo('#m' + i );
        $('<div>', {id:"s" + i, class:"m-card-content"}).appendTo('#m' + i );
        $('<div>', {class:"m-c-name active"}).text(realNames[i - 1]).appendTo('#s'+ i);
        $('<div>', {class:"m-c-time"}).appendTo('#s' + i);
        $('<div>', {class:"m-c-last-message"}).text(lastMessages[i - 1]).appendTo('#s'+ i);
    }
}

function getInfoAboutFriends() {
    targetIDs = [];
    chatID = [];
    realNames = [];
    lastMessages = [];

    $.ajax({
        url:"/friends/" + thisUsersID,
        success: function (data) {
            console.log(123);
            for (let [userId, chatNumber] of Object.entries(data)) {
                targetIDs.push(userId);
                chatID.push(chatNumber);
            }
        },async: false
    });


    for (let i = 0; i < targetIDs.length; i++) {
        $.ajax({
            url: "/users/" + targetIDs[i],
            success: function (data) {
                realNames.push(data.firstName + ' ' + data.lastName);
            }, async: false
        });

        $.ajax({
            type: "POST",
            url: "/chatboxLastMessage/" + chatID[i],
            success: function (data) {
                console.log(data);
                lastMessages.push(data);
            }, async: false
        });
    }


}

function getFirstUserChat() {

    numberOfStringsinCertainChat = 0;
    clearInterval(timeId);
    let idx = parseInt($('#m1').attr('idx'));
    let $target = $('#m1').find('.m-c-name');
    targetUser = $target.text();

    console.log(chatID[idx-1]);

    activeChat = thisUsersID + " " + targetIDs[idx-1] + " " + chatID[idx-1] + " " +  realNames[idx-1];
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

    var obj = JSON.parse(tempVar);
    var bgSrc = $target.attr('src');
    $('.m-c-name').removeClass('active');
    $target.addClass('active');
    createMessages(bgSrc, obj, idx);

    let timerId = setInterval(function() {
        var stringsCurrent = 0;
        $.ajax({
            url:"/chatbox/" + activeChat,
            type: "POST",
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
            let innerObj = '';
            $.ajax({
                type: "POST",
                url:"/chatbox/" + activeChat,
                success: function (data) {
                    console.log(data);
                    innerObj = JSON.parse(data).detail[numberOfStringsinCertainChat - 1];
                },async: false
            });

            console.log(innerObj);
            if (innerObj.owner != "self") {
                sendRobot(innerObj, idx);
            }
        }
    }, 500);
}

function send() {
    let $input = $('.r-f-input-wrp .input-wrp input');
    let $li = $('<li>', {class: 'self'});
    let $msg = $('<div>', {class: 'message'}).text($input.val());
    $li.append('\n').append($msg);

    let activeChatNumber = activeChat.split(" ")[2];
    let sendMessage = activeChatNumber + "-" + '\n{ "owner": ' + '"' + thisUsersID + '"' + ', "content": ' + '"' + $input.val() + '"},';

    $.ajax({
        url:"/chatboxSend/" + sendMessage,
        type: "POST",
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




function createMessages(bgSrc, data, idx) {
    $('.message-ul').children().remove();

    console.log("I AM IN createMessages");
    console.log(data);
    console.log(idx);

    $('.right-header .name-wrp .name').text(data.name);

    if (jQuery.isEmptyObject(data)) {
        return 0;
    }

    for (let i=0; i<data.detail.length; i++) {
        let detail = data.detail[i];
        let $li = $('<li>', {class: detail.owner});
        let temporarylet = "";
        let $msg;

        if (detail.newContent === undefined) {
            $msg = $('<div>', {class: 'message'}).text(detail.content);
        } else {
            $msg = $('<div>', {class: 'message'}).text(detail.newContent).css("background-color", "green")
                .append($('<div>', {class: 'description'}).text(detail.content)).mouseover(function () {
                $(this).children().show();
            }).mouseout(function () {
                $(this).children().hide();
            })
        }

        console.log($msg.children());


        if (detail.owner == 'other') {

            let $icon = $('<div>', {class: 'icon'}).css({background: "url(" + bgSrc + ") 0 /cover"});
            $li.append('\n').append($icon).append('\n').append($msg).append('\n').click(function () {
                console.log("meow");
                console.log(i);
                temporarylet = Swal.mixin({
                    input: 'text',
                    confirmButtonText: 'Next &rarr;',
                    showCancelButton: true,
                }).queue([
                    {
                        title: 'Correct message',
                        text: $msg.clone().children().remove().end().text()
                    }
                ]).then((result) => {

                    if (result.value) {
                        temporarylet = result.value;
                        Swal.fire({
                            title: 'All done!',
                            html:
                                'Correct message is: <pre><code>' +
                                JSON.stringify(result.value).substring(1,JSON.stringify(result.value).length - 1) +
                                '</code></pre>',
                            confirmButtonText: 'Lovely!'
                        })
                        //$li = $('<li>', {class: detail.owner});

                        let getInitialText = $(this).text();
                        if ($msg.css("background-color").toString() === "rgb(0, 128, 0)") {
                            getInitialText = $msg.children().text();
                        }

                        $(this).text("");
                        $msg.text("");

                        $msg = $('<div>', {class: 'message'}).text(result.value).css("background-color", "green")
                            .append($('<div>', {class: 'description'}).text(getInitialText)).mouseover(function () {
                                $(this).children().show();
                        }).mouseout(function () {
                                $(this).children().hide();
                        });

                        $(this).append('\n').append($icon).append('\n').append($msg).append('\n');


                        let sendVar = "{'chatNumber': " + idx + ", 'messageNumber': " + i + ", 'newMessage':'" + result.value + "'}";
                        console.log(sendVar);

                        $.ajax({
                            type: "POST",
                            url: "/chatboxFixMessage/" + sendVar,
                            success: function (data) {
                            }, async: false
                        });

                        //var textByLine = fs.readFileSync( 3 + idx + '.txt').toString().split("\n");
                        //console.log(textByLine);
                    }
                })

            });
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

function temporarty() {
    console.log("PLUMP");
}

function scrollBottom() {
    var $ul = $('.message-ul');
    var offset = $ul[0].scrollHeight - $ul[0].clientHeight;
    if (offset > 0) {
        $ul[0].scrollTop += offset;
    }
}


function sendRobot(pushData, idx) {
    let $target = $(this).find('.m-c-name');
    let bgSrc = $target.attr('src');
    let $icon = $('<div>', {class: 'icon'}).css({background: "url(" + bgSrc + ") 0 /cover"});
    let activeChat = thisUsersID + " " + targetIDs[idx-1] + " " + chatID[idx-1] + " " +  realNames[idx-1];
    let stringsCurrent = "";
    $.ajax({
        url:"/chatbox/" + activeChat,
        type: "POST",
        success: function (data) {
            if (data.length < 1) {
                stringsCurrent = 0;
            } else {
                stringsCurrent = JSON.parse(data).detail.length;
            }
        },async: false
    });

    console.log(stringsCurrent);


    $('.typing').removeClass('typing');

    var $li = $('<li>', {class: 'other typing'});
    var $msg = $('<div>', {class: 'message'}).append('<span>●</span><span>●</span><span>●</span>');
    $li.append('\n').append($msg).click(function () {
        console.log("meow");

        temporarylet = Swal.mixin({
            input: 'text',
            confirmButtonText: 'Next &rarr;',
            showCancelButton: true,
        }).queue([
            {
                title: 'Correct message',
                text: $msg.clone().children().remove().end().text()
            }
        ]).then((result) => {

            if (result.value) {
                temporarylet = result.value;
                Swal.fire({
                    title: 'All done!',
                    html:
                        'Correct message is: <pre><code>' +
                        JSON.stringify(result.value).substring(1,JSON.stringify(result.value).length - 1) +
                        '</code></pre>',
                    confirmButtonText: 'Lovely!'
                })
                //$li = $('<li>', {class: detail.owner});

                let getInitialText = $(this).text();
                if ($msg.css("background-color").toString() === "rgb(0, 128, 0)") {
                    getInitialText = $msg.children().text();
                }

                $(this).text("");
                $msg.text("");

                $msg = $('<div>', {class: 'message'}).text(result.value).css("background-color", "green")
                    .append($('<div>', {class: 'description'}).text(getInitialText)).mouseover(function () {
                        $(this).children().show();
                    }).mouseout(function () {
                        $(this).children().hide();
                    });

                $(this).append('\n').append($icon).append('\n').append($msg).append('\n');


                let sendVar = "{'chatNumber': " + idx + ", 'messageNumber': " + stringsCurrent + ", 'newMessage':'" + result.value + "'}";
                console.log(sendVar);

                $.ajax({
                    type: "POST",
                    url: "/chatboxFixMessage/" + sendVar,
                    success: function (data) {
                    }, async: false
                });

                //var textByLine = fs.readFileSync( 3 + idx + '.txt').toString().split("\n");
                //console.log(textByLine);
            }
        })

    });
    $('.message-ul').append('\n').append($li);
    scrollBottom();

    $li.css({transform: 'scale(1)'});

    setTimeout(function () {
        $msg.text(pushData.content);
    }, 2300);
}