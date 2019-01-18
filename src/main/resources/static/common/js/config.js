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
}

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
}