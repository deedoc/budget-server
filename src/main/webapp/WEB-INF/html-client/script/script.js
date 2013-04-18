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
	serv.auth.login(undefined, undefined, initVm, function(){
		serv.auth.login(prompt("email"), prompt("password"), initVm, function(){alert("Not authorized")});
	});
}