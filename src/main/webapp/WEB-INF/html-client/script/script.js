window.onload = function(){
	db.restore();
	var balanceSheet = new BalanceSheet();
	window.balanceSheet = balanceSheet;
	
	db.balanceSheetItems.forEach(function(item){
		var bsi = new BalanceSheetItem();
		bsi.name(item.name); bsi.value(item.value);bsi.dateDate(item.dateDate);
		balanceSheet.items.push(bsi);
	})
	ko.applyBindings(balanceSheet, $("#balanceSheet")[0]);

	var calendar = new Calendar();
	window.calendar = calendar;
	ko.applyBindings(calendar, $("#calendar")[0])
}