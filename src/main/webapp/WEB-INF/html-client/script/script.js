window.onload = function(){
	$.post("/budget-server/rest/auth/login", {}, function(){
		window.vm = {
			calendar: new Calendar()
		}
		ko.applyBindings(window.vm);
	}).error(function(){
		$.post("/budget-server/rest/auth/login", {email:prompt("email"), password: prompt("password")}, function(){
			window.vm = new Calendar();
			window.calendar = window.vm;
			ko.applyBindings(window.wm);
		});		
	});
}