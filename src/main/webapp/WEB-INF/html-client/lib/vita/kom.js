kom = {
	toJS: function(viewModel){
		var serverMapping = viewModel._serverMapping || [];
		var result = {};
		serverMapping.forEach(function(fieldDescriptionStr){
			var fieldName = kom._parseFieldDescription(fieldDescriptionStr).name;
			var field = viewModel[fieldName]();
			if(field._serverMapping){
				field = kom.toJS(field);
			} else if(field instanceof Array){
				field = field.map(function(item){
					return kom.toJS(item);
				});
			}
			result[fieldName] = field;

		})
		return result;
	},

	toJSON: function(viewModel){
		return JSON.stringify(kom.toJS(viewModel));
	},

	fromJS: function(viewModel, o){
		var serverMapping = viewModel._serverMapping || [];
		serverMapping.forEach(function(fieldDescriptionStr){
			var fieldDescription = kom._parseFieldDescription(fieldDescriptionStr);
			var fieldName = fieldDescription.name;
			var field = viewModel[fieldName];
			if(fieldDescription.type && fieldDescription.type.name == "Array"){
				o[fieldName].forEach(function(item){
					field.push(kom.fromJS(new window[fieldDescription.type.additionalInfo[0]](), item));
				});
			} else if(fieldDescription.type){
				field(kom.fromJS(new window[fieldDescription.type.name](), o[fieldName]));
			} else {
				field(o[fieldName]);
			}
		});
		return viewModel;
	},

	fromJSON: function(viewModel, s){
		return kom.fromJS(viewModel, JSON.parse(s));
	},

	_parseFieldDescription: function(s){
		return {name: s.split(":")[0].trim(), type: kom._parseType(s)};
	},
	_parseType: function(s){
		return s.indexOf(":") < 0 ? undefined : {name: s.split(":")[1].split("<")[0].trim(), additionalInfo: kom._parseAdditionalInfo(s)};
	},
	_parseAdditionalInfo: function(s){
		return s.indexOf("<") < 0 ? undefined : s.split("<")[1].split(">")[0].split(",").map(function(str){return str.trim()});
	}
}