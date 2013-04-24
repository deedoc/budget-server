<table width="100%">
	<tr>
		<td style="text-align:center">
			<a style="height: 50%" class="btn btn-large" data-bind="click: goBackward" href="#"><i style="position: relative; top: 42%" class="icon-backward"></i>&nbsp;</a>
		</td>
		<td>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th colspan="7" style="text-align:center;">
							<span style="text-align:center; font-size: 33px;" data-bind="text:base().format('MMMM')"></span>
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
						<td style="padding:4px; width:60px; height:80px;" data-bind="click: select, style:{opacity: isAnotherMonth() ? '0.5' : '1', border: isToday() ? '2px dashed black' : ''}">
							<div>
								<div data-bind="text: date().format('DD')" style="opacity:0.2; overflow:hidden; font-size:60px; line-height:normal; position: absolute; z-index:100" />								
								<span style="font-size: 20px; margin-left: 3px;" data-bind="text: sum() != 0 ? sum() : '', css: {'text-success': sum() > 0, 'text-error': sum() < 0}"></span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</td>
		<td style="text-align:center">
			<a style="height: 50%" class="btn btn-large" data-bind="click: goForward" href="#"><i style="position: relative; top: 42%" class="icon-forward"></i>&nbsp;</a>
		</td>
	</tr>	
</table>
<div style="width:100%">
	<span data-bind="text: selectedDay() && selectedDay().dateKey()" />
	<ul data-bind="foreach: selectedDay() && selectedDay().transactions()">
		<li data-bind="text: name + ' ' + value, css: {'text-success': value > 0, 'text-error': value < 0}" />
	</ul>
</div>