// const cityId = 726050;
// const authId = '3b6f820c751efb61d199e8b6650a5885';
//
// const weatherSection = document.getElementById('weatherSection');
//
// async function getWeatherData() {
//     return await fetch("https://api.openweathermap.org/data/2.5/forecast?id="+cityId+"&appid="+authId);
// }
//
// async function run() {
//     const data = await getWeatherData()
//         .then(response => response.json());
//
//     const weatherDetails = data.list;
//
//     console.log(weatherDetails);
// }
//
// run();