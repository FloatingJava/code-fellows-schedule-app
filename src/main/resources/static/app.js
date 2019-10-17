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
    calendarElement.push(null);
    calendarElement.push(null);
    calendarElement.push(null);

    calendarArr.push(calendarElement);
  })

  // ******** Chart Land ***********
  google.charts.load('current', {'packages':['gantt']});
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
    data.addColumn('number', 'Duration');
    data.addColumn('number', 'Percent Complete');
    data.addColumn('string', 'Dependencies');

    data.addRows(calendarArr);

    var options = {
      height: 2000,
      gantt: {
        percentEnabled: false
      }
    };

    var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

    chart.draw(data, options);
  }
}


// // ******** Chart Land Original ***********

// google.charts.load('current', {'packages':['gantt']});
// google.charts.setOnLoadCallback(drawChart);

// function daysToMilliseconds(days) {
//   return days * 24 * 60 * 60 * 1000;
// }

// function drawChart() {

//   var data = new google.visualization.DataTable();
//   data.addColumn('string', 'Task ID');
//   data.addColumn('string', 'Task Name');
//   data.addColumn('date', 'Start Date');
//   data.addColumn('date', 'End Date');
//   data.addColumn('number', 'Duration');
//   data.addColumn('number', 'Percent Complete');
//   data.addColumn('string', 'Dependencies');

//   data.addRows([
//     ['Research', 'Find sources',
//      new Date(2015, 0, 1), new Date(2015, 0, 5), null,  100,  null],
//     ['Write', 'Write paper',
//      null, new Date(2015, 0, 9), daysToMilliseconds(3), 25, 'Research,Outline'],
//     ['Cite', 'Create bibliography',
//      null, new Date(2015, 0, 7), daysToMilliseconds(1), 20, 'Research'],
//     ['Complete', 'Hand in paper',
//      null, new Date(2015, 0, 10), daysToMilliseconds(1), 0, 'Cite,Write'],
//     ['Outline', 'Outline paper',
//      null, new Date(2015, 0, 6), daysToMilliseconds(1), 100, 'Research']
//   ]);

//   var options = {
//     height: 275
//   };

//   var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

//   chart.draw(data, options);
// }
