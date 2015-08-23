healthfullUApp.controller('FoodCtrl', 
function($scope, $http) {

	$scope.getFoodInfo = function (date) {
		var requestURL = foodEntryUrl + '?date=' + date;
		$scope.foodEntryDate = date;
		$scope.myPromise = $http.get(requestURL).success(function(data) {
			$scope.foodEntries = data;
			$scope.foodEntryAggregate = aggregateByMealType($scope.foodEntries);
			$scope.dailyTotal = getDailyTotal($scope.foodEntries);
		});
	};
});


/**
 * Aggregate foodEntry by meal types
 * @param {Array} array of FoodEntry to aggregate by meal types
 * @return {Object} an object of foodEntries aggregated by meal types
 */
function aggregateByMealType(foodEntries) {
	var foodEntryAggregate = new Object();
	foodEntryAggregate.breakfast = new Array();
	foodEntryAggregate.lunch = new Array();
	foodEntryAggregate.dinner = new Array();
	foodEntryAggregate.other = new Array();

	for (var i = 0; i < foodEntries.length; i++) {	
		if (foodEntries[i].meal.toLowerCase() == "breakfast") {
			foodEntryAggregate.breakfast.push(foodEntries[i]);
			continue;
		}
		if (foodEntries[i].meal.toLowerCase() == "lunch") {
			foodEntryAggregate.lunch.push(foodEntries[i]);
			continue;
		}
		if (foodEntries[i].meal.toLowerCase() == "dinner") {
			foodEntryAggregate.dinner.push(foodEntries[i]);
			continue;
		}
		if (foodEntries[i].meal.toLowerCase() == "other") {
			foodEntryAggregate.other.push(foodEntries[i]);
			continue;
		}
	}
	return foodEntryAggregate;
}


/**
 * Calculate the total nutrients of the day
 * @param {Array} array of FoodEntry for the day
 * @return {Object} an object of summed nutrients
 */
function getDailyTotal(foodEntries) {
	var foodEntryTotal = new Object();
	var totalCalories = 0, totalFat = 0, totalSatFat = 0, totalTransFat = 0;
	var totalPolyFat = 0, totalMonoFat = 0, totalCholesterol = 0;
	var totalSodium = 0, totalCarb = 0, totalFiber = 0, totalSugar = 0;
	var totalProtein = 0, totalVA = 0, totalVC = 0, totalIron = 0, totalPotassium = 0;
	for (var i = 0; i < foodEntries.length; i++) {	
		totalCalories = totalCalories + foodEntries[i].calories;
		totalFat = totalFat + foodEntries[i].fat;
		totalCholesterol = totalCholesterol + foodEntries[i].cholesterol;
		totalSatFat = totalSatFat + foodEntries[i].saturated_fat;
		totalTransFat = totalTransFat + foodEntries[i].trans_fat;
		totalPolyFat = totalPolyFat + foodEntries[i].polyunsaturated_fat;
		totalMonoFat = totalMonoFat + foodEntries[i].monounsaturated_fat;
		totalSodium = totalSodium + foodEntries[i].sodium;
		totalCarb = totalCarb + foodEntries[i].carbohydrates;
		totalFiber = totalFiber + foodEntries[i].fiber;
		totalSugar = totalSugar + foodEntries[i].sugar;
		totalProtein = totalProtein + foodEntries[i].protein;
		totalVA = totalVA + foodEntries[i].vitamin_a;
		totalVC = totalVC + foodEntries[i].vitamin_c;
		totalIron = totalIron + foodEntries[i].iron;
		totalPotassium = totalPotassium + foodEntries[i].potassium;
		
		
	}

	foodEntryTotal.calories = totalCalories.toFixed(0);
	foodEntryTotal.fat = totalFat.toFixed(0);
	foodEntryTotal.cholesterol = totalCholesterol.toFixed(0);
	foodEntryTotal.saturated_fat = totalSatFat.toFixed(0);
	foodEntryTotal.trans_fat = totalTransFat.toFixed(0);
	foodEntryTotal.polyunsaturated_fat = totalPolyFat.toFixed(0);
	foodEntryTotal.monounsaturated_fat = totalMonoFat.toFixed(0);
	foodEntryTotal.sodium = totalSodium.toFixed(0);
	foodEntryTotal.carbohydrates = totalCarb.toFixed(0);
	foodEntryTotal.fiber = totalFiber.toFixed(0);
	foodEntryTotal.sugar = totalSugar.toFixed(0);
	foodEntryTotal.protein = totalProtein.toFixed(0);
	foodEntryTotal.vitamin_a = totalVA.toFixed(0);
	foodEntryTotal.vitamin_c = totalVC.toFixed(0);
	foodEntryTotal.iron = totalIron.toFixed(0);
	foodEntryTotal.potassium = totalPotassium.toFixed(0);

	return foodEntryTotal;
}
