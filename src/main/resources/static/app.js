// Gantt Chart source;
// https://developers.google.com/chart/interactive/docs/gallery/ganttchart

//Getting JSON 
// const requestURL = 'https://s3-us-west-2.amazonaws.com/static.codefellows.org/courses/schedule.json';
const requestURL = '/calendarSource'
var request = new XMLHttpRequest();
request.open('Get', requestURL);
requestURL.responseType = 'json';
request.send();
request.onload = function() {
  //Parsing JSON 
  const courseJSONstr = request.response;
  const courseJSONarr = JSON.parse(courseJSONstr);
  let calendarArr = [];

  courseJSONarr.forEach(course => {
    calendarElement = [];

    //Converting data types
    calendarElement.push(course.code);
    calendarElement.push(course.code);
    calendarElement.push(new Date(Date.parse(course.startDate)));
    calendarElement.push(new Date(Date.parse(course.endDate)));

    calendarArr.push(calendarElement);
  })

  // ******** Chart Land ***********
  google.charts.load('current', {'packages':['timeline']});
  google.charts.setOnLoadCallback(drawChart);

  function daysToMilliseconds(days) {
    return days * 24 * 60 * 60 * 1000;
  }

  function drawChart() {

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Course ID');
    data.addColumn('string', 'Course Name');
    data.addColumn('date', 'Start Date');
    data.addColumn('date', 'End Date');

    data.addRows(calendarArr);

    var options = {
      height: 2000,
      gantt: {
        percentEnabled: false
      }
    };

    var chart = new google.visualization.Timeline(document.getElementById('chart_div'));

    chart.draw(data, options);
  }
}
