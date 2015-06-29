// Store, retrieve and remove
localStorage.setItem("name", "Franz");
localStorage.setItem("birth", time.today());
console.log(localStorage.getItem("name"));
localStorage.removeItem("name");

// The same, but temporary
sessionStorage.clickCount = 1;
console.log("Step #" + sessionStorage.clickCount);
sessionStorage.clickCount += 1;
console.log("Step #" + sessionStorage.clickCount);
