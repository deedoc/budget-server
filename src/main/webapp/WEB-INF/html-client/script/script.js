window.onload = function(){

	$.post("/budget-server/rest/auth/login", {email:prompt("email"), password: prompt("password")}, function(){
		var calendar = new Calendar();
		window.calendar = calendar;
		ko.applyBindings(calendar, $("#calendar")[0])
	})
}