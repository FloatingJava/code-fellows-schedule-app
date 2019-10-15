// Gantt Chart source;
// https://developers.google.com/chart/interactive/docs/gallery/ganttchart


// console.log('hi');
// const requestURL = 'https://s3-us-west-2.amazonaws.com/static.codefellows.org/courses/schedule.json';
// var request = new XMLHttpRequest();
// request.open('Get', requestURL);
// requestURL.responseType = 'json';
// request.send();
// request.onload = function() {
//   const courseJSONstr = request.response;
//   const courseJSONobj = JSON.parse(courseJSONstr);
//   let courseArr = [];
//   let calendarArr = [];
//   courseArr = courseJSONobj.courses
//   courseArr.forEach(course => {
//     calendarElement = [];
//     console.log('***************************')
//     console.log(course)
//     calendarElement.push(course.code);
//     calendarElement.push(course.code);
//     calendarElement.push(new Date(course.startDate));
//     calendarElement.push(new Date(course.endDate));
//     calendarArr.push(calendarElement);
//   })
//   console.log(courseJSONobj.courses);


//   // ******** Chart Land ***********
//   google.charts.load('current', {'packages':['gantt']});
//   google.charts.setOnLoadCallback(drawChart);

//   function daysToMilliseconds(days) {
//     return days * 24 * 60 * 60 * 1000;
//   }

//   function drawChart() {

//     var data = new google.visualization.DataTable();
//     data.addColumn('string', 'Course ID');
//     data.addColumn('string', 'Course Name');
//     data.addColumn('date', 'Start Date');
//     data.addColumn('date', 'End Date');
//     data.addColumn('string', 'Dependencies');

//     data.addRows([
//       ['Research', 'Find sources',
//       new Date(2015, 0, 1), new Date(2015, 0, 5), null],
//       ['Write', 'Write paper',
//       null, new Date(2015, 0, 9), daysToMilliseconds(3), 25, 'Research,Outline'],
//       ['Cite', 'Create bibliography',
//       null, new Date(2015, 0, 7), daysToMilliseconds(1), 20, 'Research'],
//       ['Complete', 'Hand in paper',
//       null, new Date(2015, 0, 10), daysToMilliseconds(1), 0, 'Cite,Write'],
//       ['Outline', 'Outline paper',
//       null, new Date(2015, 0, 6), daysToMilliseconds(1), 100, 'Research']
//     ]);

//     var options = {
//       height: 275
//     };

//     var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

//     chart.draw(data, options);
//   }
// }

// ******** Chart Land ***********
google.charts.load('current', {'packages':['gantt']});
google.charts.setOnLoadCallback(drawChart);

function daysToMilliseconds(days) {
  return days * 24 * 60 * 60 * 1000;
}

function drawChart() {

  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Task ID');
  data.addColumn('string', 'Task Name');
  data.addColumn('date', 'Start Date');
  data.addColumn('date', 'End Date');
  data.addColumn('number', 'Duration');
  data.addColumn('number', 'Percent Complete');
  data.addColumn('string', 'Dependencies');

  data.addRows([
    ['Research', 'Find sources',
     new Date(2015, 0, 1), new Date(2015, 0, 5), null,  100,  null],
    ['Write', 'Write paper',
     null, new Date(2015, 0, 9), daysToMilliseconds(3), 25, 'Research,Outline'],
    ['Cite', 'Create bibliography',
     null, new Date(2015, 0, 7), daysToMilliseconds(1), 20, 'Research'],
    ['Complete', 'Hand in paper',
     null, new Date(2015, 0, 10), daysToMilliseconds(1), 0, 'Cite,Write'],
    ['Outline', 'Outline paper',
     null, new Date(2015, 0, 6), daysToMilliseconds(1), 100, 'Research']
  ]);

  var options = {
    height: 275
  };

  var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

  chart.draw(data, options);
}
