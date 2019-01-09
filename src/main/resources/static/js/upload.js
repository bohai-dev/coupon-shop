//公司oss账号
accessid= 'LTAICdHS3UrXF0jt';
accesskey= '6W3Ks3AVctu7h3Qd8wtJeYqNmcMdTE';
host = 'https://zhuanyoyo.oss-us-west-1.aliyuncs.com/';

g_dirname = ''
g_object_name = ''
g_object_name_type = ''
now = timestamp = Date.parse(new Date()) / 1000;

var policyText = {
    "expiration": "2020-01-01T12:00:00.000Z", //设置该Policy的失效时间，超过这个失效时间之后，就没有办法通过这个policy上传文件了
    "conditions": [
    ["content-length-range", 0, 1048576000] // 设置上传文件的大小限制
    ]
};

var policyBase64 = Base64.encode(JSON.stringify(policyText))
message = policyBase64
var bytes = Crypto.HMAC(Crypto.SHA1, message, accesskey, { asBytes: true }) ;
var signature = Crypto.util.bytesToBase64(bytes);
//选择是否随机名字
function check_object_radio() {
    var tt = document.getElementsByName('myradio');
    for (var i = 0; i < tt.length ; i++ )
    {
        if(tt[i].checked)
        {
            g_object_name_type = tt[i].value;
            break;
        }
    }
}
//选择上传文件名
//菜品图片路径
function get_dirname()
{
    dir = document.getElementById("dirname").value;
    if (dir != '' && dir.indexOf('/') != dir.length - 1)
    {
        dir = dir + '/'
    }
    //alert(dir)
    g_dirname = dir
}
//顶部图片路径
function get_dirname1()
{
    dir = document.getElementById("dirname1").value;
    if (dir != '' && dir.indexOf('/') != dir.length - 1)
    {
        dir = dir + '/'
    }
    //alert(dir)
    g_dirname = dir
}
//分享图片路径
function get_dirname2()
{
    dir = document.getElementById("dirname2").value;
    if (dir != '' && dir.indexOf('/') != dir.length - 1)
    {
        dir = dir + '/'
    }
    //alert(dir)
    g_dirname = dir
}

function random_string(len) {
　　len = len || 32;
　　var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';   
　　var maxPos = chars.length;
　　var pwd = '';
　　for (i = 0; i < len; i++) {
    　　pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function get_suffix(filename) {
    pos = filename.lastIndexOf('.')
    suffix = ''
    if (pos != -1) {
        suffix = filename.substring(pos)
    }
    return suffix;
}

function calculate_object_name(filename)
{
    if (g_object_name_type == 'local_name')
    {
        g_object_name += "${filename}"
    }
    else if (g_object_name_type == 'random_name')
    {
        suffix = get_suffix(filename)
        g_object_name = g_dirname + random_string(10) + suffix
    }
    return ''
}

function get_uploaded_object_name(filename)
{
    if (g_object_name_type == 'local_name')
    {
        tmp_name = g_object_name
        tmp_name = tmp_name.replace("${filename}", filename);
        return tmp_name
    }
    else if(g_object_name_type == 'random_name')
    {
        return g_object_name
    }
}
//上传事件
function set_upload_param(up, filename, ret)
{
    g_object_name = g_dirname;
    if (filename != '') {
        suffix = get_suffix(filename)
        calculate_object_name(filename)
    }
    new_multipart_params = {
        'key' : g_object_name,
        'policy': policyBase64,
        'OSSAccessKeyId': accessid, 
        'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
        'signature': signature,
    };

    up.setOption({
        'url': host,
        'multipart_params': new_multipart_params
    });

    up.start();
}

//招牌菜品9张图片
var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectfiles', 
    multi_selection: true,
	container: document.getElementById('container'),
	flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',
	filters: {
	  mime_types : [ //只允许上传图片
	    { title : "Image files", extensions : "jpg,gif,png" }
	  ],
	  max_file_size : '1M'
	},
	init: {
		PostInit: function() {
			document.getElementById('ossfile').innerHTML = '';
			document.getElementById('postfiles').onclick = function() {
				set_upload_param(uploader, '', false);
				return false;
			};
		},

		FilesAdded: function(up, files) {
			var length = $('#ossfile>div').length;
			var hasLength = $('.dataImg').length;
			if((length+files.length)>9) {
                showModelMes('最多只能上传9张图片！');
                up.files.splice((length-hasLength),files.length)
                return;
            }
			plupload.each(files, function(file) {
				$('#ossfile').append('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b><div class="progress"><div class="progress-bar" style="width: 0%"></div></div></div>')
//				document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
//				+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
//				+'</div>';
			});
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            get_dirname();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			var d = document.getElementById(file.id);
			d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
			var progBar = prog.getElementsByTagName('div')[0]
			progBar.style.width= 2*file.percent+'px';
			progBar.setAttribute('aria-valuenow', file.percent);
		},

		FileUploaded: function(up, file, info) {
            if (info.status == 200)
            {
//          	console.log(host + get_uploaded_object_name(file.name))
            	var imgSrc = host + get_uploaded_object_name(file.name);
				document.getElementById(file.id).innerHTML="<img src="+imgSrc+"><a href='javascript:void(0);' class='btn delBtn'>删除</a><div class='dishesbox'><div class='dishesView'><span class='dishesTitle'>菜名：</span><input class='l-formMiddleInput' name='dishName' type='text' value=''></div><div class='dishesView'><span class='dishesTitle'>价格：</span><input class='l-formMiddleInput' name='dishPrice' type='number' value=''></div></div>";
				if ($('#ossfile img').length==9) {
					$('#container').addClass('dn')
				}
            }
            else
            {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            } 
		},

		Error: function(up, err) {
			document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}
});

uploader.init();

//顶部图片1张
var uploader1 = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectfiles1', 
    multi_selection: false,
	container: document.getElementById('container1'),
	flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',
	filters: {
	  mime_types : [ //只允许上传图片
	    { title : "Image files", extensions : "jpg,gif,png" }
	  ],
	  max_file_size : '1M'
	},
	init: {
		PostInit: function() {
			document.getElementById('ossfile1').innerHTML = '';
			document.getElementById('postfiles1').onclick = function() {
				set_upload_param(uploader1, '', false);
            	return false;
			};
		},

		FilesAdded: function(up, files) {
			plupload.each(files, function(file) {
				document.getElementById('ossfile1').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
				+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
				+'</div>';
			});
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            get_dirname1();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			var d = document.getElementById(file.id);
			d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
			var progBar = prog.getElementsByTagName('div')[0]
			progBar.style.width= 2*file.percent+'px';
			progBar.setAttribute('aria-valuenow', file.percent);
		},

		FileUploaded: function(up, file, info) {
            if (info.status == 200)
            {
//          	console.log(host + get_uploaded_object_name(file.name))
            	var imgSrc = host + get_uploaded_object_name(file.name);
				document.getElementById(file.id).innerHTML="<img src="+imgSrc+"><a href='javascript:void(0);' class='btn delBtn'>删除</a>";
				$('#container1').addClass('dn')
            }
            else
            {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            } 
		},

		Error: function(up, err) {
			document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}
});

uploader1.init();

//分享卡图片1张
var uploader2 = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectfiles2', 
    multi_selection: false,
	container: document.getElementById('container2'),
	flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',
	filters: {
	  mime_types : [ //只允许上传图片
	    { title : "Image files", extensions : "jpg,gif,png" }
	  ],
	  max_file_size : '1M'
	},
	init: {
		PostInit: function() {
			document.getElementById('ossfile2').innerHTML = '';
			document.getElementById('postfiles2').onclick = function() {
				set_upload_param(uploader2, '', false);
            	return false;
			};
		},

		FilesAdded: function(up, files) {
			plupload.each(files, function(file) {
				document.getElementById('ossfile2').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
				+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
				+'</div>';
			});
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            get_dirname2();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			var d = document.getElementById(file.id);
			d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
			var progBar = prog.getElementsByTagName('div')[0]
			progBar.style.width= 2*file.percent+'px';
			progBar.setAttribute('aria-valuenow', file.percent);
		},

		FileUploaded: function(up, file, info) {
            if (info.status == 200)
            {
//          	console.log(host + get_uploaded_object_name(file.name))
            	var imgSrc = host + get_uploaded_object_name(file.name);
				document.getElementById(file.id).innerHTML="<img src="+imgSrc+"><a href='javascript:void(0);' class='btn delBtn'>删除</a>";
				$('#container2').addClass('dn')
            }
            else
            {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            } 
		},

		Error: function(up, err) {
			document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}
});

uploader2.init();