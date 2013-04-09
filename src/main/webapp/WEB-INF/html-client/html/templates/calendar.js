<table class="table table-bordered">
	<thead>
		<tr>
			<th colspan="7" style="text-align:center;">
				<a class="btn btn-large" data-bind="click: goBackward" href="#"><i class="icon-backward"></i>&nbsp;</a>
				<span style="text-align:center; font-size: 33px;" data-bind="text:base().format('MMMM')"></span>
				<a class="btn btn-large" data-bind="click: goForward" href="#"><i class="icon-forward"></i>&nbsp;</a>
			</th>
		</tr>
		<tr>
			<th>Пн</th>
			<th>Вт</th>
			<th>Ср</th>
			<th>Чт</th>
			<th>Пт</th>
			<th>Сб</th>
			<th>Вс</th>
		</tr>
	</thead>
	<tbody data-bind="foreach: weeks">
		<tr data-bind="foreach: days">
			<td data-bind="style:{opacity: isAnotherMonth() ? '0.5' : '1', border: isToday() ? '2px dashed black' : ''}">
				<div style="font-size:40px; opacity: 0.9; position: absolute" data-bind="text: date().format('DD')"></div>
				<div style="text-align: right; font-size:20px;" data-bind="click: toggleDetailsVisibility, text: sum() != 0 ? sum() : '&nbsp;', attr: {'class': sum() > 0 ? 'text-success' : sum() == 0 ? '' : 'text-error'}"></div>
				<div class="btn-toolbar">
					<div class="btn-group">
						<a class="btn btn-mini" href="#" data-bind="click: addTransaction"><i class="icon-plus-sign"></i></a>
					</div>
				</div>
				<div data-bind="visible: detailsVisible">
					<table style="width: 100%">
						<tbody data-bind="foreach: transactions">
							<tr data-bind="attr: {'class': value > 0 ? 'text-success' : value == 0 ? '' : 'text-error'}">
								<td data-bind="text: name" />
								<td data-bind="text: value" />
								<td><button class="btn btn-mini" type="button" data-bind="visible: true, click: function(){$parent.removeTransaction($data)}"><i class="icon-minus-sign"></i></button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</tbody>
</table>