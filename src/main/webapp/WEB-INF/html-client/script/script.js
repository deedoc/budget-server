window.onload = function(){
	var initVm = function(){
		window.vm = {
			calendar: new Calendar(),
			navbar: {
				email: ko.observable("dummy-email@not-implemented.yet")
			}
		}
		ko.applyBindings(window.vm);
	};
	$.post("/budget-server/rest/auth/login", {}, initVm).error(function(){
		$.post("/budget-server/rest/auth/login", {email:prompt("email"), password: prompt("password")}, initVm);
	});
}