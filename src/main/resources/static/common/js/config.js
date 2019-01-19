/**
 * 定义layui组件
 */
var $ = layui.jquery,
	form = layui.form, 
	table = layui.table, 
	layer = layui.layer;

/**
 * 日期格式化
 * @param datetime Date类型时间
 * @param format 需要转换成的格式
 * @returns
 */
function dateFormat(datetime, format) {
   	if (parseInt(datetime) == datetime) {
   	    if (datetime.length == 10) {
   	      datetime = parseInt(datetime) * 1000;
   	    } else if (datetime.length == 13) {
   	      datetime = parseInt(datetime);
   	    }
   	}
   	datetime = new Date(datetime);
   	var o = {
    	"M+" : datetime.getMonth() + 1,                 	//月份   
    	"d+" : datetime.getDate(),                    		//日   
    	"h+" : datetime.getHours(),                   		//小时   
    	"m+" : datetime.getMinutes(),                 		//分   
    	"s+" : datetime.getSeconds(),                 		//秒   
    	"q+" : Math.floor((datetime.getMonth() + 3) / 3), 	//季度   
    	"S"  : datetime.getMilliseconds()             		//毫秒   
   	};   
   	if (/(y+)/.test(format)) {
   		format = format.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));   
   	}   
   	  
   	for (var k in o) {
   		if (new RegExp("("+ k +")").test(format)) {
   			format = format.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));  
   		}  
   	}  
   	return format;
};

function convertGender(gender) {
	var genderDesc = "";
	if (gender == 1) {
		genderDesc = '<button class="layui-btn layui-btn-sm">男性</button>';
	} else if (gender == 2) {
		genderDesc = '<button class="layui-btn layui-btn-sm layui-btn-danger">女性</button>';
	} else {
		genderDesc = '<button class="layui-btn layui-btn-sm layui-btn-warm">保密</button>';
	}
   	return genderDesc;
};

function convertStatus(status) {
	var statusDesc = "";
	if (status == -1) {
		statusDesc = '<button class="layui-btn layui-btn-sm layui-btn-danger">删除</button>';
	} else if (status == 0) {
		statusDesc = '<button class="layui-btn layui-btn-sm layui-btn-warm">禁用</button>';
	} else {
		statusDesc = '<button class="layui-btn layui-btn-sm">正常</button>';
	}
   	return statusDesc;
};

/**
 * 判断是否为空
 * @param str 字符串
 * @returns
 */
function isBlank(str) {
    if (typeof str === 'undefined' || str === null || $.trim(str) === '') {
        return true;
    }
    return false;
};

/**
 * 系统用户名校验
 * @returns
 */
function checkUsername() {
	var username = $("#username").val();
	if (username != null && username.length > 0) {
		if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(username)) {
			layer.tips("用户名不能有特殊字符", '#username', {tips: [3, 'red']});
			$('username').focus();
	      	return false;
	    }
	    if (/(^\_)|(\__)|(\_+$)/.test(username)) {
	    	layer.tips("用户名首尾不能出现下划线\'_\'", '#username', {tips: [3, 'red']});
			$('username').focus();
	      	return false;
	    }
	    if (/^\d+\d+\d$/.test(username)) {
	    	layer.tips("用户名不能为纯数字", '#username', {tips: [3, 'red']});
			$('username').focus();
	      	return false;
	    }
	    var flag = false;
		$.ajax({
        	url:"/sysUser/checkUsername",
        	data:{
        		username: username
        	},
        	type: "get",
        	async: false, 
        	dataType: "json",
        	success: function(result) {
	    		if (result && result.code == 1) {
	    			layer.tips("用户名已被注册，请重新输入", '#username', {tips: [3, 'red']});
	    			$('username').focus();
	    		} else {
	    			flag = true;
	    		}
        	},
        	error:function(e) {
        		layer.msg('网络异常！', new Function());
        	}
        });
		return flag;
	}
};

/**
 * 系统用户手机号码校验
 * @returns
 */
function checkPhone() {
	var phone = $("#phone").val();
	if (phone != null && phone.length > 0) {
		var flag = false;
		$.ajax({
        	url:"/sysUser/checkPhone",
        	data:{
        		phone: phone
        	},
        	type: "get",
        	async: false, 
        	dataType: "json",
        	success: function(result) {
	    		if (result && result.code == 1) {
	    			layer.tips("该手机号已被注册，请重新输入", '#phone', {tips: [3, 'red']});
	    			$('phone').focus();
	    		} else {
	    			flag = true;
	    		}
        	},
        	error:function(e) {
        		layer.msg('网络异常！', new Function());
        	}
        });
		return flag;
	}
};

/**
 * 系统用户电子邮箱校验
 * @returns
 */
function checkEmail() {
	var email = $("#email").val();
	if (email != null && email.length > 0) {
		var flag = false;
		$.ajax({
        	url:"/sysUser/checkEamil",
        	data:{
        		email: email
        	},
        	type: "get",
        	async: false, 
        	dataType: "json",
        	success: function(result) {
	    		if (result && result.code == 1) {
	    			layer.tips("该邮箱已被注册，请重新输入", '#email', {tips: [3, 'red']});
	    			$('email').focus();
	    		} else {
	    			flag = true;
	    		}
        	},
        	error:function(e) {
        		layer.msg('网络异常！', new Function());
        	}
        });
		return flag;
	}
};