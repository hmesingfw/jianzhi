function gotozan(id, modelid, list, action) {

    if ((action == null || action == "") && action != "setfont") {
        action = "点赞#已赞"
    }
    $.ajax({
        type: 'post',
        datatype: 'text',
        url: "/plus/ajaxs.ashx?a=docorz&id=" + id + "&modelid=" + modelid + "&optype=1&isgetinfo=0",
        data: { act: action },
        success: function (msg) {
            if (msg == "0") { alert("对不起，您未登录"); showuserlogin(); }
            if (list == null || list == "") {
                if (msg == "-1") {
                    if (parseInt($("#zancount").html()) > 0) {
                        $("#zancount").html(parseInt($("#zancount").html()) - 1);
                    }
                    $("#zan").html(action.split('#')[0]);
                }
                else if (msg == "+1") {
                    $("#zancount").html(parseInt($("#zancount").html()) + 1);
                    $("#zan").html(action.split('#')[1]);
                }
            }
            else if (list == "list") {
                
                if (msg == "-1") {
                    $("#zan" + id + "").removeClass("zan-hover");
                    if (parseInt($("#zancount" + id).html()) > 0) {
                        $("#zancount" + id).html(parseInt($("#zancount" + id).html()) - 1);
                    }
                    if (action == "setfont") {
                        $("#zan" + id + "").html("点赞");
                    }
                }
                else if (msg == "+1") {
                    $("#zan" + id + "").addClass("zan-hover");
                    $("#zancount" + id).html(parseInt($("#zancount" + id).html()) + 1);
                    if (action == "setfont") {
                        $("#zan" + id + "").html("已赞");
                    }
                }
            }

        }
    });
}
function getzan(id, modelid, list, action) {
    if ((action == null || action == "") && action != "setfont") {
        action = "点赞#已赞"
    }
    $.ajax({
        type: 'post',
        datatype: 'text',
        url: "/plus/ajaxs.ashx?a=docorz&id=" + id + "&modelid=" + modelid + "&optype=1&isgetinfo=1&list=" + list + "",
        data: { act: action },
        success: function (msg) {
            //alert(msg);
            if (list == "list") {
                var k = msg.split(',');
                for (var i = 0; i < k.length; i++) {
                    $("#zan" + k[i]).addClass("zan-hover");
                    if (action == "setfont") {
                        $("#zan" + k[i]).html("已赞")
                    }
                }
            }
            else {

                $("#zan").html(msg);
            }
        }
    });
}
function getcollect(id, modelid, list, action) {
    if ((action == null || action == "") && action != "setfont") {
        action = "收藏#已收藏"
    }
    $.ajax({
        type: 'post',
        datatype: 'text',
        url: "/plus/ajaxs.ashx?a=docorz&id=" + id + "&modelid=" + modelid + "&optype=0&isgetinfo=1&list=" + list + "",
        data: { act: action },
        success: function (msg) {
           //  alert(msg);
            if (list == "list") {
                var k = msg.split(',');
                for (var i = 0; i < k.length; i++) {
                    $("#collect" + k[i]).addClass("collect-hover");
                    if (action == "setfont") {
                        $("#collect" + k[i]).html("已收藏")
                    }
                }

            }
            else {
                $("#collect").html(msg);
            }
        }
    });
}
function collect(id, modelid, list, action) {
    if ((action == null || action == "") && action != "setfont") {
        action = "收藏#已收藏";
    }
    $.ajax({
        type: 'post',
        datatype: 'text',
        url: "/plus/ajaxs.ashx?a=docorz&id=" + id + "&modelid=" + modelid + "&optype=0&isgetinfo=0",
        data: { act: action },
        success: function (msg) {
            //alert(msg);
            if (msg == "0") { alert("对不起，您未登录"); showuserlogin(); }
            if (list == "" || list == null) {
                if (msg == "-1") {

                    if (parseInt($("#ccount").html()) > 0) {
                        $("#ccount").html(parseInt($("#ccount").html()) - 1);
                    }
                    $("#collect").html(action.split('#')[0]);
                }
                else if (msg == "+1") {
                    $("#ccount").html(parseInt($("#ccount").html()) + 1);
                    $("#collect").html(action.split('#')[1]);

                }
            }
            else if (list == "list") {
                if (msg == "-1") {
                    $("#collect" + id + "").removeClass("collect-hover");
                    if (parseInt($("#ccount" + id).html()) > 0) {
                        $("#ccount" + id).html(parseInt($("#ccount" + id).html()) - 1);
                    }
                    if (action == "setfont") {
                        $("#collect" + id + "").html("收藏");
                    }
                }
                else if (msg == "+1") {
                    $("#collect" + id + "").addClass("collect-hover");
                    $("#ccount" + id).html(parseInt($("#ccount" + id).html()) + 1);
                    if (action == "setfont") {
                        $("#collect" + id + "").html("已收藏");
                    }

                }
            }
        }
    });
}