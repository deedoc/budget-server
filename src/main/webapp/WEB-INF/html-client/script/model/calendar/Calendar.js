function Calendar(){
	var self = this;

	this.selectedDay = ko.observable();

	this.changeMonth = function(d){
		d.date(1).day(1);
		this.weeks.forEach(function(week){
			week.days.forEach(function(day){
				day.date(moment(d));
				d.add("d", 1);
			});
		});
	};
	this.goBackward = function(){
		this.base(this.base().add("M", -1));
		this.changeMonth(moment(this.base()));
	};
	this.goForward = function(){
		this.base(this.base().add("M", 1));
		this.changeMonth(moment(this.base()));
	};

	this.base = ko.observable(moment());

	this.weeks = [];

	for(var i = 0; i < 6; i++){
		var week = {days:[]};
		for(var j = 0; j < 7; j++){
			week.days[j] = new Day(self);
		}
		this.weeks[i] = week;
	}

	this.changeMonth(moment(this.base()));
}