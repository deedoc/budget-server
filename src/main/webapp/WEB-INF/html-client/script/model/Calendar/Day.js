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

	this.transactionsTrigger = ko.observable();
	this.transactions = ko.computed(function(){
		self.transactionsTrigger();
		return (db.transactions[self.dateKey()] || []).map(function(item){return {name: item.name, value: item.value, undeletable: false}})
			.concat(db.balanceSheetItems.filter(function(item){return item.dateDate == self.date().format("DD")}).map(function(item){return {name: item.name, value: item.value, undeletable: true}}));
	});

	this.sum = ko.computed(function(){
		return self.transactions().reduce(function(acc, transaction){
			return acc + parseInt(transaction.value);
		}, 0);
	});

	this.addTransaction = function(){
		var transaction = {name: prompt("Наименование"), value: prompt("Значение")};
		if(!transaction.name || !transaction.value){
			return;
		}
		var dateKey = self.dateKey();
		if(!db.transactions[dateKey]){
			db.transactions[dateKey] = [];
		}
		db.transactions[dateKey].push(transaction);
		self.transactionsTrigger.notifySubscribers();
	}

	this.removeTransaction = function(transaction){
		var dateKey = self.dateKey();
		db.transactions[dateKey] = db.transactions[dateKey].filter(function(item){return item.name != transaction.name || item.value != transaction.value});
		self.transactionsTrigger.notifySubscribers();
	}
}