function BalanceSheet() {
	this._serverMapping = ["items:Array<BalanceSheetItem>"];

	var self = this;
	
	this.items = ko.observableArray();
	
	this.switchBalanceSheetVisibility = function(){
		self.balanceSheetVisible(!self.balanceSheetVisible());
	}
	this.balanceSheetVisible = ko.observable(false);
	
	this.assets = ko.computed(function(){
		return self.items().filter(function(item){
			return parseInt(item.value()) >= 0;
		});
	});
	this.assetsSum = ko.computed(function(){
		return self.assets().reduce(function(acc, item){
			return acc + parseInt(item.value());
		}, 0)
	});

	this.liabilities = ko.computed(function(){
		return self.items().filter(function(item){
			return item.value() < 0;
		});
	});
	this.liabilitiesSum = ko.computed(function(){
		return self.liabilities().reduce(function(acc, item){
			return acc + parseInt(item.value());
		}, 0)
	});
	
	this.sum = ko.computed(function(){
		return self.liabilitiesSum() + self.assetsSum();
	});
	
	this.nameToAdd = ko.observable("");
	this.valueToAdd = ko.observable("");
	this.dateDateToAdd = ko.observable("");
	
	this.add = function(){
		var newItem = new BalanceSheetItem();
		newItem.name(self.nameToAdd()); newItem.value(self.valueToAdd()); newItem.dateDate(self.dateDateToAdd());
		self.nameToAdd(""); self.valueToAdd("");self.dateDateToAdd("");
		self.items.push(newItem);
		$("[data-bind='value: nameToAdd']").focus();
	}
}