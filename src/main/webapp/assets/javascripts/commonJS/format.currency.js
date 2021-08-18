function calculationFee(inputId, discountId, resultId) {
	var percent = parseFloat($(discountId).text()) / 100.0;
	$(resultId).html(
			"<b>"
					+ formatCurrency($(inputId).val() - $(inputId).val()
							* percent) + "</b>");
}
function formatCurrency(input) {
	var currency = parseInt(input).toString();

	function format(currency) {
		if (currency.length > 3) {
			var length = currency.length;
			var newCurrency;
			var remainPart;

			newCurrency = currency.substring(length - 3, length);
			remainPart = currency.substring(0, length - newCurrency.length);
			remainPart = format(remainPart);

			return remainPart + "." + newCurrency;
		} else {
			return currency;
		}
	}

	return format(currency);
}
function currencyToNumber(currency) {
	return currency.replace(/[^0-9]+/g, "");
}
$(".currency-input").on('input', function() {
	var numberVal = currencyToNumber($(this).val());
	if (numberVal !== "") {
		numberVal = formatCurrency(numberVal);
	}
	$(this).val(numberVal);
	$(this)[0].selectionStart = $(this)[0].selectionEnd = numberVal.length - 2;
});
$(".card-number-input").on('input', function() {
	var y = $(this).val().replace(/[-]+/g, "");
	var z = y.replace(/[^-]{4}(?!$)/g, "\$&" + "-");
	$(this).val(z);
});