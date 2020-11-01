var app = new Vue({
    el : '#box',
    data : {
        inputUName: '',
        inputPassword: ''
    },
    methods: {
        newLogin: function () {
            var uname = this.inputUName;
            var pwd = this.inputPassword;
            if (uname.length == 0) {
                alert("用户名不能为空");
            } else if ( pwd.length == 0) {
                alert("密码不能为空");
            } else {
                $.ajax({
                    url : "/login.ajax",
                    type : "post",
                    data : {
                        uname : uname,
                        password : pwd
                    },
                    dataType : "JSON",
                    success : function (resp) {
                        if (resp.code === -1) {
                            layer.alert("非法操作!");
                        } else if (resp.code === -2) {
							layer.alert("该账户被锁定，无法登陆!");
						} else if (resp.code === 0) {
                            layer.alert("用户名不存在!");
                        } else if (resp.code === 1) {
                            layer.alert("密码不正确!");
                        } else if (resp.code === 2){
                            layer.alert("登陆成功!")
                            window.location.href = "main.html";
                        }
                    },
                    error : function () {
                        layer.alert("服务器错误，请联系管理员！");
                    }
                })
            }
        }
    }
});