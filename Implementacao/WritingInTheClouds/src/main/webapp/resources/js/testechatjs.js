/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function enviandomsn(usu, amigo, msn) {
//    alert(usu + " " + amigo + " " + msn);
    $('input#idsend').attr('value', usu);
    $('input#idreceive').attr('value', amigo);
    $('input#messengermsn').attr('value', msn.value);
    msn.value = "";
//    var link = document.getElementById('frmMsn:idmsn');
    var link = $('#idmsn');
    link.click();
}
//
//$(function () {
//    $("#addClass").click(function () {
//        $('#sidebar_secondary').addClass('popup-box-on');
//    });
//    $("#removeClass").click(function (e) {
//        $('#sidebar_secondary_addClass' + e).removeClass('popup-box-on');
//    });
//});
//$(document).on('click', '#new_chat', function (e) {
//    var size = $( ".chat-window:last-child" ).css("margin-left");
//     size_total = parseInt(size) + 400;
//    alert(size_total);
//    var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
//    clone.css("margin-left", size_total);
//});
//
//function chat_on(e) {
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
//    $('#sidebar_secondary_chat' + e).addClass('popup-box-on').appendTo(".amigo_online");
//}

function messenger_off(e) {
    //$('#sidebar_secondary_chat' + e).removeClass('popup-box-on');
    //$('#box_messenger').children('#box_messenger_chat' + e).remove();
}

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

function messenger_create(chatusu, chatami) {
//$('#'+idChat ).append( '<div class="'+classChat+'"><div class="panel panel-'+panelColor+'"><div class="panel-heading"><span class="glyphicon glyphicon-comment"></span>'+lblTitulChat+" : "+Nombre+'<div class="btn-group pull-right"><button type="button" onclick="alert(\''+textoAyuda+'\')" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-chevron-down"></span></button></div></div><div class="panel-body"><ul class="chatpluginchat"></ul></div><div class="panel-footer"><div class="input-group"><input id="'+lblTxtEnviar+'" type="text" class="form-control input-sm" placeholder="'+lblCampoEntrada+'" /><span class="input-group-btn"><button  class="btn btn-warning btn-sm" id="'+btnEnviar+'">'+lblEnviar+'</button></span></div></div></div></div><li class="left clearfix itemtemplate" style="display:none"><span class="chat-img pull-left"><img src="'+urlImg+'" alt="User Avatar" class="img-circle" id="Foto"/></span><div class="chat-body clearfix"><div class="header"><strong class="primary-font" id="Nombre">Nombre</strong><small class="pull-right text-muted"><span class="glyphicon glyphicon-asterisk"></span><span id="Tiempo">12 mins ago</span></small></div> <p id="Contenido">Contenido</p></div></li>');
    var fotoUsuario = $('#chatfotouser').attr('src');
    var fotoAmigo = $('#chaton' + chatami).children('li').children('img');
    var html = $('#box_messenger_chat').clone();
//    html.attr('id', 'box_messenger_chat' + chatami + ' ' + $('#box_messenger_chat').html());
    alert(chatusu + ' ' + chatami + ' ' + html.html());
    $(html.children('chat-aside')).addClass('popup-box-on');
    $(html.children('chat-aside').children('div').children('div').children('h3').children('span')).text(' ' + fotoAmigo.attr('alt'));
    $(html.children('chat-aside').children('div').children('div').children('form').children('div').children('input')).attr('id', 'txtmsn' + chatami);
    $(html.children('chat-aside').children('div').children('div').children('form').children('span').children('a')).attr('onclick', 'enviandomsn(' + chatusu + ', ' + chatami + ', txtmsn' + chatami + ')');
//    $(html.children('chat-aside').children('div').children('div').children('form#j_idt105').children('input')).attr('value', chatami);
    $(html).find('#removeClass').attr('onclick', 'fecharBox(' + chatami + ')');
    alert(html.html());

    //alert(fotoUsuario);
//    $('#box_messenger')
//            .append(
//                    '<div id="sidebar_secondary_chat' + chatami + '" class="col-md-2" style="width: 260px;">' +
//                    '<chat-aside class="tabbed_sidebar ng-scope chat_sidebar popup-box-on" style="margin-left:10px;">' +
//                    '<div class="popup-head">' +
//                    '<div class="popup-head-left pull-left">' +
//                    '<h3>' +
//                    '<span class="glyphicon glyphicon-comment"></span>' +
//                    ' ' + fotoAmigo.attr('alt') +
//                    '</h3>' +
//                    '</div>' +
//                    '<div class="popup-head-right pull-right">' +
//                    '<button class="chat-header-button" type="button">' +
//                    '<i class="glyphicon glyphicon-facetime-video"></i>' +
//                    '</button>' +
//                    '<button class="chat-header-button" type="button">' +
//                    '<i class="glyphicon glyphicon-earphone"></i>' +
//                    '</button>' +
//                    '<div class="btn-group gurdeepoushan">' +
//                    '<button class="chat-header-button" data-toggle="dropdown" type="button" aria-expanded="false">' +
//                    '<i class="glyphicon glyphicon-paperclip"></i>' +
//                    '</button>' +
//                    '<ul role="menu" class="dropdown-menu pull-right">' +
//                    '<li>' +
//                    '<a href="#">' +
//                    '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span>' +
//                    'Gallery' +
//                    '</a>' +
//                    '</li>' +
//                    '<li>' +
//                    '<a href="#">' +
//                    '<span class="glyphicon glyphicon-camera" aria-hidden="true"></span>' +
//                    'Photo' +
//                    '</a>' +
//                    '</li>' +
//                    '<li>' +
//                    '<a href="#">' +
//                    '<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>' +
//                    'Location' +
//                    '</a>' +
//                    '</li>' +
//                    '<li>' +
//                    '<a href="#">' +
//                    '<span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span>' +
//                    'Video' +
//                    '</a>' +
//                    '</li>' +
//                    '<li>' +
//                    '<a href="#">' +
//                    '<span class="glyphicon glyphicon-headphones" aria-hidden="true"></span>' +
//                    'Audio' +
//                    '</a>' +
//                    '</li>' +
//                    '<li>' +
//                    '<a href="#">' +
//                    '<span class="glyphicon glyphicon-user" aria-hidden="true"></span>' +
//                    'Contact' +
//                    '</a>' +
//                    '</li>' +
//                    '</ul>' +
//                    '</div>' +
//                    '<button data-widget="remove" id="removeClass" onclick="messenger_off(' + chatami + ')" class="chat-header-button pull-right" type="button">' +
//                    '<i class="glyphicon glyphicon-remove"></i>' +
//                    '</button>' +
//                    '</div>' +
//                    '</div>' +
//                    '<div id="chat" class="chat_box_wrapper chat_box_small chat_box_active" style="opacity: 1; display: block; transform: translateX(0px);">' +
//                    '<div class="chat_box touchscroll chat_box_colors_a">' +
//                    '<div class="chat_message_wrapper">' +
//                    '<div class="chat_user_avatar">' +
//                    '<img id=messenger' + chatami + ' src=' + fotoAmigo.attr('src') + ' alt="" class="md-user-image">' +
//                    '</div>' +
//                    '<ul class="chat_message">' +
//                    '<li>' +
//                    '<p>' +
//                    'Testo que o usuario escreve.' +
//                    '<span class="chat_message_time">13:38</span>' +
//                    '</p>' +
//                    '</li>' +
//                    '<li>' +
//                    '<p>' +
//                    'Testo que o usuario escreve.' +
//                    '<span class="chat_message_time">13:38</span>' +
//                    '</p>' +
//                    '</li>' +
//                    '</ul>' +
//                    '</div>' +
//                    '<div class="chat_message_wrapper chat_message_right">' +
//                    '<div class="chat_user_avatar">' +
//                    '<img id=messenger' + chatusu + ' src=' + fotoUsuario + ' alt="" class="md-user-image">' +
//                    '</div>' +
//                    '<ul class="chat_message">' +
//                    '<li>' +
//                    '<p>' +
//                    'Testo que o amigo escreve.' +
//                    '<span class="chat_message_time">13:34</span>' +
//                    '</p>' +
//                    '</li>' +
//                    '<li>' +
//                    '<p>' +
//                    'Testo que o amigo escreve.' +
//                    '<span class="chat_message_time">13:34</span>' +
//                    '</p>' +
//                    '</li>' +
//                    '</ul>' +
//                    '</div>' +
//                    '</div>' +
//                    '</div>' +
//                    '<div class="chat_submit_box">' +
//                    '<div class="uk-input-group">' +
//                    '<div class="gurdeep-chat-box">' +
//                    '<span style="vertical-align: sub;" class="uk-input-group-addon">' +
//                    '<a href="#">' +
//                    '<i class="fa fa-smile-o"></i>' +
//                    '</a>' +
//                    '</span>' +
//                    '<input type="text" placeholder="Type a message" id="submit_message" name="submit_message" class="md-input" />' +
//                    '<span style="vertical-align: sub;" class="uk-input-group-addon">' +
//                    '<a href="#">' +
//                    '<i class="fa fa-camera"></i>' +
//                    '</a>' +
//                    '</span>' +
//                    '</div>' +
//                    '<span class="uk-input-group-addon">' +
//                    '<a href="#">' +
//                    '<i class="glyphicon glyphicon-send"></i>' +
//                    '</a>' +
//                    '</span>' +
//                    '</div>' +
//                    '</div>' +
//                    '</chat-aside>' +
//                    '</div>');
    //alert(html.attr('id').toString());
    html.addClass('popup-box-on');
    //var t = html.html();
    //alert(t.toString().substring(64));
    $('#box_messenger').append(
            '<div id="box_messenger_chat' + chatami + '" class="col-md-2" style="width: 260px;">' +
            html.html() +
            '</div>');

}

//$.fn.extend({ChatSocket: function (opciones) {
//        var ChatSocket = this;
//
//        var idChat = $(ChatSocket).attr('id');
//        defaults = {
//            ws,
//            Room: "RoomDeveloteca",
//            pass: "1234",
//            lblTitulChat: " Chat Develoteca.com ",
//            lblCampoEntrada: "Menssage",
//            lblEnviar: "Send",
//            textoAyuda: "Develoteca",
//            Nombre: "Anónimo",
//
//            urlImg: "http://placehold.it/50/55C1E7/fff&text=U",
//            btnEntrar: "btnEntrar",
//            btnEnviar: "btnEnviar",
//            lblBtnEnviar: "Enviar",
//            lblTxtEntrar: "txtEntrar",
//            lblTxtEnviar: "txtMensaje",
//            lblBtnEntrar: "Enter",
//            idDialogo: "DialogoEntrada",
//            classChat: "",
//            idOnline: "ListaOnline",
//            lblUsuariosOnline: "Users joined",
//            lblEntradaNombre: "Name:",
//            panelColor: "success"
//        }
//
//        var opciones = $.extend({}, defaults, opciones);
//
//        var ws;
//        var Room = opciones.Room;
//        var pass = opciones.pass;
//        var lblTitulChat = opciones.lblTitulChat;
//        var lblCampoEntrada = opciones.lblCampoEntrada;
//        var lblEnviar = opciones.lblBtnEnviar;
//        var textoAyuda = opciones.textoAyuda;
//        var Nombre = opciones.Nombre;
//
//        var urlImg = opciones.urlImg;
//        var btnEntrar = opciones.btnEntrar;
//        var btnEnviar = opciones.btnEnviar;
//        var lblBtnEnviar = opciones.lblBtnEnviar;
//        var lblTxtEntrar = opciones.lblTxtEntrar;
//        var lblTxtEnviar = opciones.lblTxtEnviar;
//        var lblBtnEntrar = opciones.lblBtnEntrar;
//        var idDialogo = opciones.idDialogo;
//        var classChat = opciones.classChat;
//        var idOnline = opciones.idOnline;
//        var lblUsuariosOnline = opciones.lblUsuariosOnline;
//        var lblEntradaNombre = opciones.lblEntradaNombre;
//        var panelColor = opciones.panelColor;
//        if ($('#' + idOnline).length == 0)
//        {
//            idOnline = idChat + "listaOnline";
//            $('#' + idChat).append('<br/><div id="' + idOnline + '"></div>');
//
//        }
//
//
//
//        function IniciarConexion() {
//            conex = '{"setID":"' + Room + '","passwd":"' + pass + '"}';
//            ws = new WebSocket("ws://achex.ca:4010");
//            ws.onopen = function () {
//                ws.send(conex);
//            }
//            ws.onmessage = function (Mensajes) {
//                var MensajesObtenidos = Mensajes.data;
//                var obj = jQuery.parseJSON(MensajesObtenidos);
//                AgregarItem(obj);
//
//                if (obj.sID != null) {
//
//
//                    if ($('#' + obj.sID).length == 0)
//                    {
//
//                        $('#listaOnline').append('<li class="list-group-item" id="' + obj.sID + '"><span class="label label-success">&#9679;</span> - ' + obj.Nombre + '</li>');
//
//                    }
//
//                }
//
//            }
//            ws.onclose = function () {
//                alert("Conexión cerrada");
//            }
//        }
//        IniciarConexion();
//        function iniciarChat() {
//            Nombre = $('#' + lblTxtEntrar).val();
//            $('#' + idDialogo).hide();
//            $('#' + idOnline).show();
//
//            CrearChat();
//            UsuarioOnline();
//            getOnline();
//        }
//
//        function CrearEntrada() {
//            $('#' + idChat).append('<div id="' + idDialogo + '" class="' + classChat + '" id="InputNombre"><div class="panel-footer" style="margin-top:100px;"><div class="input-group"><input id="' + lblTxtEntrar + '" type="text" class="form-control input-sm" placeholder="' + lblEntradaNombre + '"><span class="input-group-btn"><button id="' + btnEntrar + '" class="btn btn-success btn-sm" >' + lblBtnEntrar + '</button></span></div></div></div>');
//            $('#' + idOnline).append(' <div class="panel panel-' + panelColor + '"><div class="panel-heading"><span class="glyphicon glyphicon-user"></span> ' + lblUsuariosOnline + '</div><div class="panel-body"><ul class="list-group" id="listaOnline"></ul></div><div class="panel-footer"><div class="input-group"><div><a href="http://develoteca.com">by develoteca.com</a></div></span></div></div></div>');
//            $("#" + lblTxtEntrar).keyup(function (e) {
//                if (e.keyCode == 13) {
//                    iniciarChat();
//                }
//            });
//            $("#" + btnEntrar).click(function () {
//                iniciarChat();
//            });
//        }
//        function CrearChat() {
//            $('#' + idChat).append('<div class="' + classChat + '"><div class="panel panel-' + panelColor + '"><div class="panel-heading"><span class="glyphicon glyphicon-comment"></span>' + lblTitulChat + " : " + Nombre + '<div class="btn-group pull-right"><button type="button" onclick="alert(\'' + textoAyuda + '\')" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-chevron-down"></span></button></div></div><div class="panel-body"><ul class="chatpluginchat"></ul></div><div class="panel-footer"><div class="input-group"><input id="' + lblTxtEnviar + '" type="text" class="form-control input-sm" placeholder="' + lblCampoEntrada + '" /><span class="input-group-btn"><button  class="btn btn-warning btn-sm" id="' + btnEnviar + '">' + lblEnviar + '</button></span></div></div></div></div><li class="left clearfix itemtemplate" style="display:none"><span class="chat-img pull-left"><img src="' + urlImg + '" alt="User Avatar" class="img-circle" id="Foto"/></span><div class="chat-body clearfix"><div class="header"><strong class="primary-font" id="Nombre">Nombre</strong><small class="pull-right text-muted"><span class="glyphicon glyphicon-asterisk"></span><span id="Tiempo">12 mins ago</span></small></div> <p id="Contenido">Contenido</p></div></li>');
//
//            $("#" + lblTxtEnviar).keyup(function (e) {
//                if (e.keyCode == 13) {
//                    EnviarMensaje();
//                }
//            });
//            $("#" + btnEnviar).click(function () {
//                EnviarMensaje();
//            });
//
//        }
//
//        function EnviarMensaje() {
//            ws.send('{"to":"' + Room + '","Nombre":"' + Nombre + '","Contenido":"' + $('#' + lblTxtEnviar).val() + '"}');
//            $("#" + lblTxtEnviar).val('');
//
//        }
//        ;
//        function UsuarioOnline() {
//            ws.send('{"to":"' + Room + '","Nombre":"' + Nombre + '"}');
//        }
//        function AgregarItem(Obj) {
//
//            if ((Obj.Contenido != null) && (Obj.Nombre != null)) {
//
//                $(".itemtemplate").clone().appendTo(".chatpluginchat");
//                $('.chatpluginchat .itemtemplate').show(10);
//                $('.chatpluginchat .itemtemplate #Nombre').html(Obj.Nombre);
//                $('.chatpluginchat .itemtemplate #Contenido').html(Obj.Contenido);
//
//                var formattedDate = new Date();
//                var d = formattedDate.getUTCDate();
//                var m = formattedDate.getMonth();
//                var y = formattedDate.getFullYear();
//                var h = formattedDate.getHours();
//                var min = formattedDate.getMinutes();
//
//                Fecha = d + "/" + m + "/" + y + " " + h + ":" + min;
//
//                $('.chatpluginchat .itemtemplate #Tiempo').html(Fecha);
//                $('.chatpluginchat .itemtemplate').removeClass("itemtemplate");
//            }
//        }
//        function getOnline() {
//            setInterval(UsuarioOnline, 3000);
//        }
//
//
//        CrearEntrada();
//        // Fin
//
//    }
//});

function listarmsn(txt) {
    alert("oi");
    var msnamigo =
            '<div class="chat_message_wrapper">'
            + '<div class="chat_user_avatar">'
            + '<img id="fotoAmigo" src="#" alt="" class="md-user-image" />'
            + '</div>'
            + '<ul class="chat_message">'
            + '<li>'
            + '<p>Testo que o usuario escreve.'
            + '<span class="chat_message_time">13:38</span>'
            + '</p>'
            + '</li>'
            + '<li>'
            + '<p>Testo que o usuario escreve.'
            + '<span class="chat_message_time">13:38</span>'
            + '</p>'
            + '</li>'
            + '</ul>'
            + '</div>'
    var msnusuario =
            '<div class="chat_message_wrapper chat_message_right">'
            + '<div class="chat_user_avatar">'
            + '<img id="fotousu" src="#" alt="" class="md-user-image" />'
            + '</div>'
            + '<ul class="chat_message">'
            + '<li>'
            + '<p>Testo que o amigo escreve.'
            + '<span class="chat_message_time">13:34</span>'
            + '</p>'
            + '</li>'
            + '<li>'
            + '<p>'
            + 'Testo que o amigo escreve.'
            + '<span class="chat_message_time">13:34</span>'
            + '</p>'
            + '</li>'
            + '</ul>'
            + '</div>'
    $('#box_messenger').append(txt);
}