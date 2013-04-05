window.onload = function(){

	$.post("/budget-server/rest/auth/login", {}, function(){
		var calendar = new Calendar();
		window.calendar = calendar;
		ko.applyBindings(calendar, $("#calendar")[0])
	}).error(function(){
		$.post("/budget-server/rest/auth/login", {email:prompt("email"), password: prompt("password")}, function(){
			var calendar = new Calendar();
			window.calendar = calendar;
			ko.applyBindings(calendar, $("#calendar")[0])
		});		
	});
}