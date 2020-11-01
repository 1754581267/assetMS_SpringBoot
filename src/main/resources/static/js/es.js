Vue.config.devtools = true;
var app = new Vue({
    el : '#box',
    data : {
		assetClass : '',
		dataList : [],
	},
    methods: {
		find : function () {
			if (this.assetClass == '') {
				layer.alert("请输入需要搜索的信息")
			} else {
				$.ajax({
					url : "/es",
					type : "POST",
					data : {
						str : 'find',
						assetClass: this.assetClass
					},
					dataType : "JSON",
					success : function (code) {
						console.log(code);
					},
					error : function () {
						alert("获取失败，请联系管理员");
					}
				});
			}
		},
		getData : function () {
			$.ajax({
				url : "/es",
				type : "POST",
				dataType : "JSON",
				data : {
					str : "paging"
				},
				success : function (data) {
					app.dataList = data.list;
				},
				error : function () {
					layer.alert("翻页失败，请联系管理员");
				}
			});
		}
	}
});
app.getData();