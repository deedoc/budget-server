function Calendar(){
	var self = this;

	this.goBackward = function(){};
	this.goForward = function(){};

	this.base = ko.observable(moment());

	this.weeks = [];

	var d = moment(this.base());
	d.date(1).day(1);

	for(var i = 0; i < 6; i++){
		var week = {days:[]};
		for(var j = 0; j < 7; j++, d.add("d", 1)){
			var day = new Day(self);
			day.date(moment(d));
			week.days[j] = day;
		}
		this.weeks[i] = week;
	}
}