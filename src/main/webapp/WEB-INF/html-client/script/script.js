window.onload = function(){

	$.post("/budget-server/rest/auth/login", {email:"pomeshikov@gmail.com", password: "123456"}, function(ukey){
		window.ukey = ukey;

		var calendar = new Calendar();
		window.calendar = calendar;
		ko.applyBindings(calendar, $("#calendar")[0])
	})
}