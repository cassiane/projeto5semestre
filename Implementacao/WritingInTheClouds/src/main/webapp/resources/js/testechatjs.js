/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("#addClass").click(function () {
        $('#sidebar_secondary').addClass('popup-box-on');
    });

    $("#removeClass").click(function (e) {
        $('#sidebar_secondary_addClass'+e).removeClass('popup-box-on');
    });
});

//$(document).on('click', '#new_chat', function (e) {
//    var size = $( ".chat-window:last-child" ).css("margin-left");
//     size_total = parseInt(size) + 400;
//    alert(size_total);
//    var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
//    clone.css("margin-left", size_total);
//});
//
function chat_on(e) {
//    var size = $( ".chat_sidebar:last-child" ).css("margin-left");
//     size_total = parseInt(size) + 400;
//    var clone = $( "#sidebar_secondary" ).clone();
//    Object.prototype.getIdentity = function() {
//        if(!this.hasOwnProperty('__identity__')) {
//            Object.defineProperty(this, '__identity__', { enumerable: false, value: ++id });
//        }
//        alert(this.__identity__);
//        return this.__identity__;
//    };
//    alert(e);
    
//    clone.addClass('popup-box-on').appendTo( ".amigo_online" );
    //clone.css("margin-left", size_total);
    $('#sidebar_secondary_chat'+e).addClass('popup-box-on').appendTo( ".amigo_online" );
}

function chat_off(e) {
    $('#sidebar_secondary_chat'+e).removeClass('popup-box-on');
}
//
//$(document).on('click', '.panel-heading span.icon_minim', function (e) {
//    var $this = $(this);
//    if (!$this.hasClass('panel-collapsed')) {
//        $this.parents('.panel').find('.panel-body').slideUp();
//        $this.addClass('panel-collapsed');
//        $this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
//    } else {
//        $this.parents('.panel').find('.panel-body').slideDown();
//        $this.removeClass('panel-collapsed');
//        $this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
//    }
//});
//$(document).on('focus', '.panel-footer input.chat_input', function (e) {
//    var $this = $(this);
//    if ($('#minim_chat_window').hasClass('panel-collapsed')) {
//        $this.parents('.panel').find('.panel-body').slideDown();
//        $('#minim_chat_window').removeClass('panel-collapsed');
//        $('#minim_chat_window').removeClass('glyphicon-plus').addClass('glyphicon-minus');
//    }
//});
//$(document).on('click', '#new_chat', function (e) {
//    var size = $( ".chat-window:last-child" ).css("margin-left");
//     size_total = parseInt(size) + 400;
//    alert(size_total);
//    var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
//    clone.css("margin-left", size_total);
//});
//$(document).on('click', '.icon_close', function (e) {
//    //$(this).parent().parent().parent().parent().remove();
//    $( "#chat_window_1" ).remove();
//});