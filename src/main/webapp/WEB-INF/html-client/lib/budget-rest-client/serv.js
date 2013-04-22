window.serv = {
	_doPost: function(url, data, success, error){
		return $.ajax({type: 'POST', url: url, data: data, success: success, error: error || serv._error});
	},
	_doPostJson: function(url, data, success, error){
		return $.ajax({type: 'POST', url: url, contentType: 'application/json', data: data, success: success, error: error || serv._error});
	},
	util:{
		/*  */
		client: function(success, error){
			return serv._doPost('/budget-server/rest/util/client', {}, success, error);
		}
	},
	transactionCategory:{
		/* cookie String ukey */
		save: function(/* TransactionCategoryDO */ transactionCategoryDO, success, error){
			return serv._doPostJson('/budget-server/rest/transactionCategory/save', transactionCategoryDO, success, error);
		},
		/* cookie String ukey */
		delete: function(/* Long */ id, success, error){
			return serv._doPost('/budget-server/rest/transactionCategory/delete', {id:id}, success, error);
		},
		/* cookie String ukey */
		findById: function(/* Long */ id, success, error){
			return serv._doPost('/budget-server/rest/transactionCategory/findById', {id:id}, success, error);
		}
	},
	transaction:{
		/* cookie String ukey */
		save: function(/* TransactionDO */ transactionDO, success, error){
			return serv._doPostJson('/budget-server/rest/transaction/save', transactionDO, success, error);
		},
		/* cookie String ukey */
		delete: function(/* Long */ id, success, error){
			return serv._doPost('/budget-server/rest/transaction/delete', {id:id}, success, error);
		},
		/* cookie String ukey */
		findByDate: function(/* Date */ date, success, error){
			return serv._doPost('/budget-server/rest/transaction/findByDate', {date:date}, success, error);
		}
	},
	auth:{
		/*  */
		register: function(/* String */ email, /* String */ password, success, error){
			return serv._doPost('/budget-server/rest/auth/register', {email:email, password:password}, success, error);
		},
		/* optional cookie String ukey */
		login: function(/* optional String */ email, /* optional String */ password, success, error){
			return serv._doPost('/budget-server/rest/auth/login', {email:email, password:password}, success, error);
		}
	}
}