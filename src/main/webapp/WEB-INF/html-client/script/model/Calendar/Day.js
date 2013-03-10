function Day(parentCalendar){
	var self = this;

	this.parentCalendar = parentCalendar;
	this.date = ko.observable(moment());
	this.dateKey = ko.computed(function(){
		return self.date().format("DD.MM.YYYY");
	});
	this.isAnotherMonth = ko.computed(function(){
		return self.date().format("MM") != self.parentCalendar.base().format("MM");
	});
	this.isToday = ko.computed(function(){
		return self.date().format("DD.MM.YYYY") == moment().format("DD.MM.YYYY")
	});
	this.detailsVisible = ko.observable(false);
	this.toggleDetailsVisibility = function(){ self.detailsVisible(!self.detailsVisible()) };

	this.transactions = ko.observableArray();
	this.loadTransactions = function(){
		$.getJSON(
			"/budget-server/rest/transaction/findByDate", 
			{date: self.dateKey(), ukey: window.ukey}, 
			function(data){
				console.log(data);
			}
		);
	}

	this.date.subscribe(this.loadTransactions);

	this.sum = ko.computed(function(){
		return self.transactions().reduce(function(acc, transaction){
			return acc + parseInt(transaction.value);
		}, 0);
	});

	this.addTransaction = function(){
		var transaction = {name: prompt("Имя"), value: prompt("Значение"), ukey: window.ukey, date: self.date().toJSON()};
/*
		$.post("/budget-server/rest/transaction/save", JSON.stringify(transaction), function(transaction){
			console.log(transaction);
		});
*/
		$.ajax({
		  url: "/budget-server/rest/transaction/save",
		  type: "POST",
		  dataType: "json",
		  contentType: "application/json",
		  data: JSON.stringify(transaction),
		  success: function(transaction){
			console.log(transaction);
		  }
		});
	};
	this.removeTransaction = function(transaction){

	};
}