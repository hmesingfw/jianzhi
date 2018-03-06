// JavaScript Document
  //*** 查看显示课程问答，名师，回复待信息       ***//
var chatttype = 0;
  var flagtype=0;
  function loadks(action, id, page) {
    
   var url="/plus/course/CourseAjax.ashx?flagtype="+flagtype+"&action=" + action + "&rnd=" + Math.random() + "&courseid=" + id + "&page=" + page;
   if (flagtype == 1) {
       url = "/plus/course/CourseAjax.ashx?flagtype=1&action=" + action + "&rnd=" + Math.random() + "&courseid=" + id + "&page=" + page;
   }   
	    $.ajax({
	        url: url,
	        context: document.body,
	        type: "GET",
	        success: function (data) { 
	        	 if (action == "coursepjlist") {
			      if (flagtype==1 && page>1)
	             { 
					 if (data!='over')
					 {
					 $("#toploadpf").show(); 
					 $("#con_comment").append(data);
					 }	                
				 }else
				 {
					  $("#con_comment").html(data); 
				 }	 
	            }
	            else if (action == "teacherlist") {
	                $("#teacherlist").html(data);
	            }
	            else if (action == "asklistpost") {
					
					if(flagtype == 1 && data.indexOf("empty") != -1){
				       $("#topload").hide();
					}
		      if (flagtype==1 && page>1)
	             { 
					 if (data!='over')
					 {
					 $("#topload").show(); 
					 $("#asklist").append(data);
					 }
				 }else
				 {					   
					   $("#asklist").html(data); 
				 }
	            }
	            else if (action == "askpostlist01") {
					if (data !=''){
					$('#apost' + id).show();	
	                $('#apost' + id).html(data);
					}
					
	            }
	            else if (action == "coursetype") {
	                
	                $("#kss").html(data);
	            }
			   else if (action == "askcount") {
	                $("#askcount").html(data);
	            }
			   else if (action == "TeacherViewCourse")
			   {
			       //alert(data);
				   if(flagtype == 1 && data.indexOf("empty") != -1){
				       $("#toploadcourse").hide();
					}
			       if (flagtype == 1 && page > 1) {
					   
					   
			           if (data != 'over') {
			               $("#toploadcourse").show();
			               $("#TeacherViewCourse").append(data);
			           }
					   
			       }
			       else {
			           $("#TeacherViewCourse").html(data);
			       }
			   }
			   else if (action == "TeacherViewFaceCourse") {
				   if(flagtype == 1 && data.indexOf("empty") != -1){
				       $("#toploadface").hide();
					}
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#toploadface").show();
			               $("#TeacherViewFaceCourse").append(data);
			           }
			       }
			       else {
			           $("#TeacherViewFaceCourse").html(data);
			       }
			   }
			   else if (action == "TeacherViewLiveCourse") {
				   if(flagtype == 1 && data.indexOf("empty") != -1){
				       $("#toploadlive").hide();
					}
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#toploadlive").show();
			               $("#TeacherViewLiveCourse").append(data);
			           }
			       }
			       else {
			           $("#TeacherViewLiveCourse").html(data);
			       }
			   }
			   else if (action == "TeacherViewAskList") {
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#toploadask").show();
			               $("#TeacherViewAskList").append(data);
			           }
			       }
			       else {
			           $("#TeacherViewAskList").html(data);
			       }
			   }
			   else if (action == "classroomview") {
			       //班级课程
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#toploadcourse").show();
			            
			               $("#classroomview").append(data);
			           }
			       }
			       else {
			           $("#classroomview").html(data);
			       }
			   }
			   else if (action == "classroomhomework") {
			       //班级作业
	
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			             
			               $("#toploadcourse").show();
			               $("#classroomhomework").append(data);
			           }
			       }
			       else {
			          
			           $("#classroomhomework").html(data);
			       }
			   }
			   else if (action == "classroomasklist") {
			       //班级问答
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#topload").show();
			               $("#classroomasklist").append(data);
			           }
			       } else {
			           $("#classroomasklist").html(data);
			       }
			   }
			   else if (action == "classroomstudent") {
			       //班级学员
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#topload").show();
			               $("#classroomstudent").append(data);
			           }
			       } else {
			           $("#classroomstudent").html(data);
			       }
			   }
			   else if (action == "classroomteacher") {
			       //班级教师
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#topload").show();
			               $("#classroomteacher").append(data);
			           }
			       } else {
			           $("#classroomteacher").html(data);
			       }
			   }
			   else if (action == "classroomgroup") {
			       //班级教师
			       if (flagtype == 1 && page > 1) {
			           if (data != 'over') {
			               $("#topload").show();
			               $("#classroomgroup").append(data);
			           }
			       } else {
			           $("#classroomgroup").html(data);
			       }
			   } else if (action == "getblogcos") {//个人中心课程列表
			       $("#courselist").html(data);
			   } else if (action == "getblogsj") {//个人中心试卷列表
			       $("#sjlist").html(data);
			   }
			   else if (action == "getblogmsg") {//个人中心留言列表
			       $("#messagelist").html(data);
			   } else if (action == "getblogask") {//个人中心提问列表
			    //   alert(data);
			       $("#asklists").html(data);
			   } else if (action == "chatlistpost")//聊天记录列表输出
			   { 
			       $("#chatlists").html(data);

			       //回复记录执行完成函数
	                try {
		           		if(chatlistpostBack){
				    		chatlistpostBack();
				    	};
		           	} catch(e) {
		           		
		           	};


			   }
	        }
	    });
	}
//*********评价************/
function subpj(courseid)
{
var content=escape($("#content0").val());
var star=$(".description").html().substring(0,1);

if (content == '') {
    alert('请输入评价内容');
    return false;
} 
    $.ajax({
	        url: "/plus/course/CourseAjax.ashx?action=subpj&courseid=" + courseid + "&content=" + content+"&score="+star,
	        context: document.body,
	        type: "GET",
	        success: function (data) {
	            content = $("#content0").val('');
	            if (data == "success") {
	                loadks("coursepjlist", courseid, 1);
	            } else {
	                alert(data);
	            }
	        }
        });
} 

//显示回复信息
function ajaxshowreplay(chatid,type,page)
{
    $.ajax({
        url: "/plus/course/CourseAjax.ashx?action=showreplay&chatid=" + chatid + "&rnd=" + Math.random() + "&type=" + type + "&page=" + page,
        context: document.body,
        success: function (d) {  
			$("#replaylist" + chatid).html(d);
			$("#replaylist" + chatid).show();
			$("#replay" + chatid).show(); 
        }
    });
}
 
 
//添加聊天记录信息coursechat
function addchat(courseid,periodid,chattype,ispublic,content,tcid,coursetype)
{
    $.ajax({
        url: "/plus/course/CourseAjax.ashx?action=addchat&courseid=" + courseid + "&content=" + escape(content) + "&tcid=" + tcid + "&periodid=" + periodid + "&chattype=" + chattype + "&ispublic=" + ispublic + "&coursetype=" + coursetype + "&rnd=" + Math.random(),
        context: document.body,
        success: function (d) { 
            if (d == "login") {
                showuserlogin();//前往登录
            } else if (d.indexOf("ok")!=-1) { 
                if (periodid == "") {
                    alert("问题发表成功，请耐心等待，名师将很快为您解答!");
                }

                var strs = new Array();//数据存储
                strs = d.split("&&");
                $("#chatlists").html(strs[1]);//替换数据信息

                //回复记录执行完成函数
                try {
	           		if(showplaychatFun){
			    		showplaychatFun();
			    	};
	           	} catch(e) {
	           		
	           	}
            }
        }
    });
}
//播放页面的显示记录信息
function showplaychat(courseid, periodid, coursetype, page)
{
    coursetype = 0;
    $.ajax({
        url: "/plus/course/CourseAjax.ashx?action=chatlistpost&courseid=" + courseid + "&periodid=" + periodid + "&coursetype=" + coursetype + "&page=" + page + "&rnd=" + Math.random(),
        context: document.body,
        success: function (d) {
            //显示内容信息
            $("#chatlists").empty().append(d);
            //执行加载完成后事件
           	try {
           		if(showplaychatFun){
		    		showplaychatFun();
		    	};
           	} catch(e) {
           		
           	}
		    
        }
    });

}


  
//*****提问问答*******///课程id   回复内容   infoid 课时id  大分类
function subaskgo(id, content, tcid) {
    chatttype = 1;
    $.ajax({
        url: "/plus/course/CourseAjax.ashx?action=subask&courseid=" + id + "&content=" + escape(content) + "&tcid=" + tcid ,
        context: document.body,
        success: function (d) {
            if (d == "ok") {
                alert("问题发表成功，请耐心等待，名师将很快为您解答!");
                //editor0.execCommand('cleardoc');					
                loadks("asklistpost", id, 1);
                var count = parseInt($("#askcount").html()) + 1;
                $("#askcount").html(count);
            } 
        }
    });
}

//******回复问答信息    *********/
function subasktopic(id)
{
var content=escape($("#content"+id).val());
if (content=='')
{
alert('请输入回复内容');
return false;
}
  $.ajax({
	    url: "/plus/course/CourseAjax.ashx?action=subasktopic&courseid=" + id + "&content=" + content,
	    context: document.body,
	    type: "GET",
	    success: function (data) {
		content=$("#content"+id).val('');
		if (data=="ok")
		{
            loadks("askpostlist01", id, 1);
			var count=parseInt($(".rcount"+id).html())+1;			
		    $(".rcount"+id).html(count); 
			}else
			{
			alert(data);
			}
        }
    });
}

	function askpost(val){				 			  
	    loadks("askpostlist01",val,1);				   				   
	}
	function rtalklist(val)
	{	
	 $("#rtalklist"+val).toggle();
	  $("#apost"+val).toggle();
	 loadks("askpostlist01",val,1);	
    }
	function shixian(values,type,date,hours)
    {
        if (type == "0" && (values == 0 || values == '')) {
            values = "永久";
            document.getElementById("shixian").innerHTML = values;
        }
        else if (type == "0") {
            document.getElementById("shixian").innerHTML = values + "天";
        }
        else if (type == "1") {
            document.getElementById("shixian").innerHTML = date;
        }
        else {
            document.getElementById("shixian").innerHTML = hours+"小时";
        }

	  
	}

	function xiangou(values) {
	    if (values == 0 || values == '') {
	        values = "不限";
	        document.getElementById("xiangou").innerHTML = "<img src=/SysImg/course/xgicon.png align=texttop />限购：" + values + "";
	    } else {
	        document.getElementById("xiangou").innerHTML = "<img src=/SysImg/course/xgicon.png align=texttop />限购：" + values + "人";
	    }
	}
  function xiangou_wap(values) {
	    if (values == 0 || values == '') {
	        values = "不限";
	        document.getElementById("xiangou").innerHTML = "<img src=/SysImg/course/xgicon.png align=texttop /> 限购：" + values + "";
	    } else {
	        document.getElementById("xiangou").innerHTML = "<img src=/SysImg/course/xgicon.png align=texttop /> 限购：" + values + "人";
	    }
	}
function jiage_wap(type,money,jifen,qgmoney) {
	  
	    if (type == 3) {
	        document.getElementById("jiage").innerHTML = "" + jifen + "积分";
	    } else if (type == 0) {
			if (money==0)
			{
				 document.getElementById("jiage").innerHTML = "<i class='free_cor'>免费</i>";
			}else{
	        document.getElementById("jiage").innerHTML = "<font>¥</font>"+parseFloat(money).toFixed(2)+"";
			}
	    } else if (type == 4) {
	        document.getElementById("jiage").innerHTML = "" + jifen + "积分+" + parseFloat(money).toFixed(2) + "元";
	    }
	    else {
	        qgmoney = Math.round(qgmoney * 100.0)/100.0;
		
	      document.getElementById("jiage").innerHTML = "¥" + qgmoney + "";
	    }
	}
	function jiage(type,money,jifen,qgmoney) {
	  
	    if (type == 3) {
	        document.getElementById("jiage").innerHTML = "" + jifen + "积分";
	    } else if (type == 0) {
			if (money==0)
			{
				 document.getElementById("jiage").innerHTML = "<i class='free_cor'>免费</i>";
			}else{
	        document.getElementById("jiage").innerHTML = "<font>¥</font>"+money+"";
			}
	    }
	    else if (type == 4)
	    {
	        document.getElementById("jiage").innerHTML = "" + jifen + "积分+" + parseFloat(money).toFixed(2) + "元";
	    }
	    else {
	        qgmoney = Math.round(qgmoney * 100.0) / 100.0;

	        document.getElementById("jiage").innerHTML = "<span class=qgbg>抢购</span><font>¥</font>" + qgmoney + "";
	    }
	}
	

	function  dobuyCourse()
	{
		if (confirm('收费课时，购买后观看'))
		 {
		   $("#form1").submit();   
		 }else
		 {
			 
		 }
	}
		
	function  gocourse(str)
	{
		if (confirm('收费课时，购买后观看'))
		 {
		  localtion.href=str;
		 }else
		 {
			 
		 }
	}

/*购物车*/
	function doDelete(from3g) {
	   // alert("ddd");
	    var sid = GetMyIds();
	   // alert("ids====" + ids);
	    if (sid == null) {
	        if (from3g == 1) {
	            alert("请选择要删除的订单信息");
	        }
	        else {
	            KesionJS.Alert("请选择要删除的订单信息");
	        }
	        return false;
	    } else {
	        if (from3g == 1) {
	           //alert(88887897);
	            if (confirm("确定删除选的课程？")) {
	                //alert("ddd");
	                // alert("ids====" + sid);
	                location.href = 'index.aspx?c=course&a=shopcart&action=del&ids=' + sid;
	            }
	          // Confirm("确定删除选的课程?", 'location.href = "?action=del&ids=' + sid + "\";", "");
	        }
	        else {
	            KesionJS.Confirm("确定删除选的课程?", 'location.href="index.aspx?c=course&a=shopcart&action=del&ids=' + sid + "\";", "");
	        }
	    }
	}
	function GetMyIds() {
	   // alert("44444444");
	    var sid = null;
	    for (var i = 0; i < document.getElementsByName("ids").length; i++) {
	        var KS = document.getElementsByName("ids")[i];
	        if (KS.checked == true) {
	            if (sid == null)
	                sid = KS.value;
	            else
	                sid += "," + KS.value;
	        }
	    }
	    //alert(sid);
	    return sid;
	}

	function check(from3g) {
	 
		  jQuery.ajax({
               type: "POST",
               datatype: "xml",
               url: "/plus/ajaxs.ashx",
                data: "a=checkislogin",
                success: function (xml) {
                    var islogin = jQuery(xml).find("login islogin").text();
                    if (islogin == 'false') {
                        if (from3g == 1) {
                           location.href = 'index.aspx?c=user&a=login';
                        } else {
                            showuserlogin();
                        }
                        return false;
                    } else {
                        var sid = GetMyIds();
                        if (sid == null) {
                            KesionJS.Alert("请选择要要去支付的课程信息");
                            return false;
                        } else {
                            jQuery("form").submit();
                            return true;
                        }
                    }
                }			 
		  });
		  return false;
	}

	function onpay(sub) {
	    var price = 0;
	    var rate = 0;
	    var num = 0;
	    for (var i = 0; i < document.getElementsByName("ids").length; i++) {
	        var KS = document.getElementsByName("ids")[i];
	        if (KS.checked == true) {

	            price = price + parseFloat($("#pt_" + KS.value).val());
	            rate = rate + parseFloat($("#r_" + KS.value).html());
	            num++;
	        }
	    }
	    if (num == 0) {
	        $("#chkall").prop("checked", false);
	    } else if (num == document.getElementsByName("ids").length) {
	        $("#chkall").prop("checked", true);
	    }
	    if (sub != null&&sub!="")
	    {
	        price = price - sub;
	    }

	    $("#count_id").html(num);
	    $("#price_id").html(price);
	    $("#pay_id").html(rate);
	}
	function CheckpayAll(form) {
	    for (var i = 0; i < form.elements.length; i++) {
	        var e = form.elements[i];
	        if (e.name != 'chkall' && e.disabled == false && e.name.indexOf('$') < 0 && e.type == "checkbox") {
	            e.checked = document.getElementById("chkall").checked;
	        }
	    }
	    onpay();
	}
	
	//订单结算
	
	function shc(v, t) {
	    if (v == "0") {
	        jQuery("#dyq").css("display", "none");//抵用券隐藏
	        if (t == 2) {
	            jQuery("#yhj_money").val(0);
	            c_cahe();
	        } else {
	            jQuery("#hongbao_money").val(0);
	          }
	        var g = getprices();
	        jQuery("#sf_money").html("￥" + g);
	        c_cahe();
	    } else {

	        jQuery("#dyq").css("display", "");//抵用券显示 

	        checkcopnum(v, t);
	    }
	}
				
		
		function sear_cher()
		{
		 var copnum = jQuery(".write-number").val();
		 checkcopnum(copnum,2)
		}
		function c_cahe()
		{

		    if ($("#cash_m").attr("checked")) {
		      jQuery("#Voucher").html((parseFloat(jQuery("#cash_money").val()) + parseFloat(jQuery("#hongbao_money").val()) + parseFloat(jQuery("#yhj_money").val())).toFixed(2));
		    } else {
		        jQuery("#Voucher").html((parseFloat(jQuery("#hongbao_money").val()) + parseFloat(jQuery("#yhj_money").val())).toFixed(2));

		    }		    
			 var p = getprices();
			 jQuery("#sf_money").html("￥" + p);
		      payu();
	    }	
	
				
		  function checkcopnum(copnum,type) {         
           // var copnum = jQuery(".write-number").val();
			jQuery("#copnu_id").val();			 
	    	var	m= jQuery("#tl_money").val();  
	    	jQuery("#sf_money").html(m);
	    	jQuery("#user_pay_money").val(m);
            if (copnum == '') {
                alert(' 请输入优惠券号');
                jQuery(".write-number").focus();
				
            } else {		
                $.ajax({
                    url: "/index.aspx?c=course&a=orderinfo&action=check&rnd=" + Math.random() + "&as=" + m + "&copnum=" + copnum,
                    context: document.body,
                    type: "GET",
                    success: function (data) {                      
                        var ss=data.split('|');
                        if (ss[0] =='ok') {
                            var a = getprices(); //parseFloat(jQuery("#tl_money").val());
                      
                            if (a>parseFloat(ss[1])){						
							   if (type==2) {
							   	 // jQuery(".Voucher").html(copnum);
							       jQuery("#yhj_money").val(ss[1]);
								 jQuery("#copnu_id").val(copnum);						  
							     //jQuery("#Voucher").html("￥"+ss[1]);  
								}else{
							       jQuery("#hongbao_money").val(ss[1]);
								//  jQuery("#m_hongbao").html('使用红包抵用了￥'+ss[1]);
								}
							   var p = getprices();                              
							   jQuery("#sf_money").html("￥" + p);
							   c_cahe();
                            } else {
                              //  shc("0", type);
                                if (type == 1) {
                                    $("#hongfenxiao").val(0).change();                                
                                } else {
                                    $("#fenxiao").val(0).change();                               
                                }
                                alert('抵用金额大于课程价格不能抵用');
							}                            
                        } else {
                            alert('您输入优惠券号不存在');
                            jQuery(".write-number").focus();
                        }
                    }
                });
            }
		  }
//---------------------------------------------------------------------------------
//名师内容页js
		  function subask(i) {
if(i==1)
{
	if($("#contentask").val()=="")
	{
		  alert("请输入问题内容");
		}
		else
		{
			  teacheraskgo(id, $("#contentask").val(), id);
			}
	
	}else{
		      var content = editor0.getContent();
		      if (editor0.hasContents() == false) {
		          alert("请输入问题内容");
		          editor0.focus();
		      } else {
		          var tcid = 0;
		          editor0.execCommand('cleardoc');
		          teacheraskgo(id, content, id);
		      }
	}

		  }

		  //*****提问问答*******/
		  function teacheraskgo(id, content, tcid) {

		      $.ajax({
		          url: "/plus/course/CourseAjax.ashx?action=subask&courseid=" + id + "&content=" + escape(content) + "&tcid=" + tcid,
		          context: document.body,
		          success: function (d) {
		           //   alert(d);
		              if (d == "ok") {
		                  alert("问题发表成功，请耐心等待，名师将很快为您解答!");
		                  //editor0.execCommand('cleardoc');					
		                  loadks("TeacherViewAskList", id, 1);
		                  //var count = parseInt($("#askcount").html()) + 1;
		                  //$("#askcount").html(count);
		              }
		          }
		      });
		  }
		  function subaskclasstopic(id, RoomID) {
               
		      jQuery.get('/plus/Course/CourseAjax.ashx',
                   { action: "getclassstudent", RoomID: RoomID },
                     function (data) {
                         if (data == "error") {
                             //alert(getclassstudent())  ;            
                             KesionJS.Alert("对不起，您没有加入该班级，不能评价");
                         }
                         else if (data == "isnull") {
                             KesionJS.Alert('请先登录!', "showuserlogin()");
                         }
                         else if (data == "ok") {
                             var content = escape($("#content" + id).val());
                             if (content == '') {
                                 alert('请输入回复内容');
                                 return false;
                             }
                             $.ajax({
                                 url: "/plus/course/CourseAjax.ashx?action=subasktopic&courseid=" + id + "&content=" + content,
                                 context: document.body,
                                 type: "GET",
                                 success: function (data) {
                                     content = $("#content" + id).val('');
                                     if (data == "ok") {
                                         loadks("askpostlist01", id, 1);
                                         var count = parseInt($(".rcount" + id).html()) + 1;
                                         $(".rcount" + id).html(count);
                                     } else {
                                         alert(data);
                                     }
                                 }
                             });
                         }
                     });
		  }
		  function addPJ() {
		      var content = $("#content").val();
		      var s = $("#stexta").find("b").html();
		      var ss = s.split("分");
		      var PJstar = s[0];
		      if (PJstar != "") {
		          $.ajax({
		              type: 'post',
		              datatype: 'text',
		              url: "/index.aspx?c=course&a=teacherview&action=addPJ",
		              data: { content: content, tcid: id, PJstar: PJstar },
		              success: function (msg) {

		                  if (msg == "0") { alert("您未登录，请登录"); showuserlogin(); }
		                  else if (msg == "00") {
		                      alert("您已评价");
		                      $("#addPJ").css("display", "inline-block");
		                      $("#addPJ1").css("display", "none");
		                  }
		                  else if (msg == "no") { alert("您未报名相关课程，无法评价") }
		                  else if (msg == "noc") { alert("该教师没有课程给您评价"); }
		                  else {
		                      alert("评价成功!");
		                      location.reload();
		                      $("#xyPJ").css("class", "active");
		                  }
		              }
		          });
		      }
		  }
		  function getPJ(id, k) {
		      $.ajax({
		          type: 'post',
		          datatype: 'text',
		          url: "/index.aspx?c=course&a=teacherview&action=getPJ",
		          data: { tcid: id, type: k },
		          success: function (msg) {
		          
		              //if (msg == "0") { alert("您未登录，请登录"); }
		              //	else if (msg == "00") { alert("您已评价") }
		              //	else {
		              if (msg == "over") { }
		              else {
		                  
						  var s = msg.split("&&&");
						 
		                  $(".commentList_box").html(s[0]);
						  if(s[1]){
							  var ss = s[1].split("#");

							  $("#commentnum0").html(ss[0]);
							  $("#commentnum1").html(ss[1]);
							  $("#commentnum2").html(ss[2]);
							  $("#commentnum3").html(ss[3]);
						  }
		              }
		              //	getPJ(id);
		              //		}

		          }
		      });
		  }

        //教师内容页  留言发表
		  function getuserask(id) {
		      $.ajax({
		          type: 'post',
		          datatype: 'text',
		          url: "/index.aspx?c=course&a=teacherview&action=getliuyan&id=" + id + "",
		          data: {},
		          success: function (msg) {
		              if (msg == "over") { }
		              else {
		                  $("#liuyan").html(msg);
		              }
		          }
		      });
		  }
          //addask留言方法
		  function adduserask(id) {
		      var askcontent = $("#contentask").val() + "&#&" + $("#files").val();
		      if (askcontent != "") {
		          $.ajax({
		              type: 'post',
		              datatype: 'text',
		              url: "/index.aspx?c=course&a=teacherview&action=addask&id=" + id + "",
		              data: { content: askcontent, tcid: id },
		              success: function (msg) {
		                  if (msg == "0") { alert("您未登录，请登录"); showuserlogin(); }
		                  else if (msg == "maxcount") {
		                      alert("您今天的留言数量已达到最大");
		                  }
		                  else {
		                      alert("留言成功！");
		                      $("#contentask").val("");
		                      $("#files").val("");
		                      $(".upshow").empty();
		                      getuserask(id);
		                  }
		              }
		          });
		      }
		      else {
		          alert("请输入留言");
		      }
		  }
		  //function submitask(j, i) {
		  //    var askcontent = $("#liuyan #askmore" + i).val();
		  //    alert(askcontent);
		  //    if (askcontent != "") {
		  //        $.ajax({
		  //            type: 'post',
		  //            datatype: 'text',
		  //            url: "/course/teacherview.aspx?addmoreask=" + id + "&parentID=" + j + "",
		  //            data: { content: askcontent, tcid: id },
		  //            success: function (msg) {
		  //                if (msg == "0") { alert("您未登录，请登录"); showuserlogin(); }
		  //                else if (msg == "00") { }
		  //                else {
		  //                    //var s = msg.split('&_|&|');
		  //                    //$("#hf" + i).html("<div><span>" + s[0] + "</span> <div>" + askcontent + "</div></div>");
		  //                    getuserask(id);
		  //                }
		  //            }
		  //        });
		  //    }
		  //    else alert("请输入回复内容");
		  //}
		  function loadCmt(a, b) {
		      $(".cmttop .cmtlist .clearfix").find("span").attr("class", "cmtlistnormal");
		      $(a).attr("class", "active");

		      getPJ(id, b);

		  }
	
		  function getmore(i, j) {

		      if (j == 0) {
		          $.ajax({
		              type: 'post',
		              datatype: 'text',
		              url: "/index.aspx?c=course&a=teacherview&action=getliuyan&id=" + id + "&LYPage=" + i + "",
		              data: {},
		              success: function (msg) {
		                  //	alert(msg);
		                  if (msg == "over") {
		                      $("#showmore1").css("display", "none");
		                      $("#showmore" + (i - 1) + "").css("display", "none");
		                  }
		                  else {
		                      $("#liuyan").append(msg);
		                      $("#showmore" + (i - 1) + "").css("display", "none");
		                  }
		              }
		          });
		      }
		      else if (j == 1) {

		          var s = $('.cmttop .cmtlist').find('.active').find("em").attr("id");
		          var k = 0;
		          switch (s) {
		              case "commentnum0": k = 0; break;
		              case "commentnum1": k = 1; break;
		              case "commentnum2": k = 2; break;
		              case "commentnum3": k = 3; break;
		          }
		          $.ajax({
		              type: 'post',
		              datatype: 'text',
		              url: "/index.aspx?c=course&a=teacherview&action=getPJ&PJPage=" + i + "",
		              data: { tcid: id, type: k },
		              success: function (msg) {
		                  if (msg == "over") {
		                      $("#showPJ").css("display", "none");
		                      $("#showPJ" + (i - 1) + "").css("display", "none");

		                  }
		                  else {
		                      var s = msg.split("&&&");
		                      var ss = s[1].split("#");
		                      $("#commentnum0").html(ss[0]);
		                      $("#commentnum1").html(ss[1]);
		                      $("#commentnum2").html(ss[2]);
		                      $("#commentnum3").html(ss[3]);
		                      $(".commentList_box").append(s[0]);
		                      $("#showPJ" + (i - 1) + "").css("display", "none");
		                  }

		              }
		          });
		      }
		  }
	  function IndexPrice3G(type, money, jifen, qgmoney,courseid)
		  {
		          if (type == 3) {
		              document.getElementById("jiage"+courseid).innerHTML = "" + jifen + "积分";
		          }
		          else if (type == 0) {
		              if (money == 0) {
		                  document.getElementById("jiage" + courseid).innerHTML = "<i class='free_cor'>免费</i>";
		              }
		              else {

		                  document.getElementById("jiage" + courseid).innerHTML = "<font>¥</font>" + money + "";
		              }
		          }
		          else if (type == 4) {
		              document.getElementById("jiage" + courseid).innerHTML = "" + jifen + "积分+" + parseFloat(money).toFixed(2) + "元";
		          }
		          else {
		              qgmoney = Math.round(qgmoney * 100.0) / 100.0;

		              document.getElementById("jiage" + courseid).innerHTML = "<span class=qgbg>抢购</span><font>¥</font>" + qgmoney + "";
		          }
	  }
	  function pricejudge1(type, money, jifen, qgmoney, courseid) {
	      if (type == 3) {
	          document.getElementById("p1jiage" + courseid).innerHTML = "" + jifen + "积分";
	      }
	      else if (type == 0) {
	          if (money == 0) {
	              document.getElementById("p1jiage" + courseid).innerHTML = "<i class='free_cor'>免费</i>";
	          }
	          else {

	              document.getElementById("p1jiage" + courseid).innerHTML = "<font>¥</font>" + money + "";
	          }
	      }
	      else if (type == 4) {
	          document.getElementById("p1jiage" + courseid).innerHTML = "" + jifen + "积分+" + parseFloat(money).toFixed(2) + "元";
	      }
	      else {
	          qgmoney = Math.round(qgmoney * 100.0) / 100.0;

	          document.getElementById("p1jiage" + courseid).innerHTML = "<span class=qgbg>抢购</span><font>¥</font>" + qgmoney + "";
	      }
	  }
	  //继续学习
	  function golearn(courseid, type) {
	  
	      $.ajax({
	          type: 'Post',
	          datatype: 'text',
	          url: "/plus/course/CourseAjax.ashx?action=golearn",
	          data: { courseid: courseid, type: type },
	          success: function (msg) {
	              location.href = msg;
	          }
	      });
	  }
	  function dobuykeshi(pid, cid, uname) {
	      if (uname != "") {
	          $.dialog.open("/Plus/Course/BuyPeriod.aspx?pid=" + pid + "&cid=" + cid + "", { id: "loginbox", lock: true, fixed: true, title: "课时购买", width: 580, height: 500, max: false, min: false });
	      }
	      else {
	          showuserlogin();
	      }
	  }

	  function ShowFacePeriod(pid, costype) {
	      if (costype == "2") {
	          $.dialog.open("/Plus/Course/FacePeriod.aspx?pid=" + pid + "&costype="+costype+"", { id: "loginbox", lock: true, fixed: true, title: "面授详情", width: 360, height: 240, max: false, min: false });
	      }
	    
	  }
	  function classroomAsk(id, content, tcid) {

	      $.ajax({
	          url: "/plus/course/CourseAjax.ashx?action=subask&flag=1&courseid=" + id + "&content=" + escape(content) + "&tcid=" + tcid,
	          context: document.body,
	          success: function (d) {
	              if (d == "ok") {
	                  alert("问题发表成功，请耐心等待，名师将很快为您解答!");

	                  loadks("classroomasklist", id, 1);
	                  var count = parseInt($("#askcount").html()) + 1;
	                  $("#askcount").html(count);


	              }
	          }
	      });
	  }

	  function GetCoupon(id,i)//获取课程的优惠券
	  {
	      var flag = 0;
	      if (i != null && i == 1)
	      {
	          flag=1
	      }
	      $.ajax({
	          type: "Post",
	          url: '/plus/course/CourseAjax.ashx',
	          data: { courseid: id, action: "couponview",flag:flag },
	          success: function (msg) {
	              $("#CouponList").html(msg);
	          }
	      })
	  }
	
	 
	