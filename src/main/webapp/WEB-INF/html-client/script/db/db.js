window.db = {
json: "{\"balanceSheetItems\":[{\"name\":\"Моя зарплата\",\"value\":30000,\"dateDate\":\"05\"},{\"name\":\"Мой аванс\",\"value\":8000,\"dateDate\":\"15\"},{\"name\":\"Ксюнина зарплата\",\"value\":12000,\"dateDate\":\"01\"},{\"name\":\"Ксюнина аванс\",\"value\":5000,\"dateDate\":\"16\"}],\"transactions\":{\"26.02.2013\":[],\"25.02.2013\":[],\"04.03.2013\":[]}}",
	dump: function(){
		delete db.json;
		window.prompt ("Copy to clipboard: Ctrl+C, Enter", "json: \"" + JSON.stringify(db).replace(/"/g, "\\\"") + "\"," );
	},
	restore: function(){
		var r = JSON.parse(db.json);
		db.balanceSheetItems = r.balanceSheetItems;
		db.transactions = r.transactions;
	},
	
	balanceSheetItems: [
		{name:"", value:"0", dateDate:"01"}
	],

	transactions: {
		"01.01.2013":[
			{name:"", value:"0"}
		]
	}
}