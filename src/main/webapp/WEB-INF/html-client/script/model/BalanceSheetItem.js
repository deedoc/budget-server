function BalanceSheetItem() {
	this._serverMapping = ["name", "value"];
	var self = this;

	this.name = ko.observable("");
	this.value = ko.observable("");
	this.dateDate = ko.observable("");
}