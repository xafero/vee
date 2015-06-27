// Locale and date example
var locale = time.currentLocale();
console.log("User's locale --> " + locale);

var date = time.chooseDate();
console.log("He or she chooses --> " + date);
console.log("In islamic calendar --> " + time.convertInto(date, "Islamic"));

var today = time.today();
console.log("Today is --> " + today);

var days = time.days(today, date);
console.log("Difference in days --> " + days);
console.log("           in hours --> " + days.toStandardHours());
