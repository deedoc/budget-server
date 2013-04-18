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
		serv.transaction.findByDate(self.dateKey(), function(data){
			self.transactions([]);
			data.forEach(function(item){
				self.transactions.push(item);
			});
		});
	}

	this.date.subscribe(this.loadTransactions);

	this.sum = ko.computed(function(){
		return self.transactions().reduce(function(acc, transaction){
			return acc + parseInt(transaction.value);
		}, 0);
	});

	this.addTransaction = function(){
		var transaction = {name: prompt("Имя"), value: prompt("Значение"), date: self.date().toJSON()};

		serv.transaction.save(JSON.stringify(transaction), function(transaction){
			self.transactions.push(transaction);
		});
	};
	this.removeTransaction = function(transaction){
		serv.transaction.delete(transaction.id, function(){
			self.transactions.remove(function(item) { return item.id == transaction.id })
		});
	};
}